<template>
  <div id="App">
    <van-cell-group class="panel_block3">
      <van-cell title="公积金贷" icon="card" />
      <van-cell value="有公积金都可以申请" />
    </van-cell-group>
    <van-form :model="form" ref="form" class="panel_block3">
      <van-field
        v-model="form.productId"
        name="productId"
        label="产品id"
        placeholder="产品id"
        :rules="[{ required: true, message: '产品id' }]"
        v-show="false"
      />
      <van-field
        v-model="applyInfo.idcardCode"
        name="idcardCode"
        label="身份证"
        placeholder="身份证"
        :rules="[{ required: true, message: '请填写正确的身份证号码' }]"
      />
      <van-field
        v-model="applyInfo.liaisonMan"
        name="liaisonMan"
        label="申请人"
        placeholder="申请人"
        :rules="[{ required: true, message: '请填写申请人姓名' }]"
      />
      <van-field v-model="applyInfo.money" name="money" :label="lable1" :placeholder="lable1" />
      <van-field
        v-model="applyInfo.address"
        name="address"
        label="详细地址"
        placeholder="详细地址"
        :rules="[{ required: true, message: '请填写详细地址' }]"
      />
      <van-field
        v-model="applyInfo.phone"
        name="phone"
        label="手机号"
        placeholder="手机号"
        :rules="[{ required: true, message: '请填写手机号码' }]"
      />
      <van-field
        v-model="applyInfo.staffCode"
        name="staffCode"
        label="客户经理"
        placeholder="客户经理"
        :rules="[{ required: true, message: '柜员号' }]"
      />
      <van-field v-model="applyInfo.createTime" name="createTime" label="申请时间" placeholder="申请时间" />
      <van-field name="radio" label="状态">
        <template #input>
          <van-radio-group v-model="radio" direction="horizontal">
            <van-radio name="1">未处理</van-radio>
            <van-radio name="2">已受理</van-radio>
            <van-radio name="3">完成办理</van-radio>
            <van-radio name="4">关闭</van-radio>
          </van-radio-group>
        </template>
      </van-field>
      <van-field name="checkboxGroup" label="联系后反馈">
        <template #input>
          <van-checkbox-group v-model="checkboxGroup" direction="horizontal">
            <van-checkbox name="1" shape="square">真实需求</van-checkbox>
            <van-checkbox name="2" shape="square">随意填写</van-checkbox>
            <van-checkbox name="2" shape="square">不符合标准</van-checkbox>
            <van-checkbox name="2" shape="square">电话沟通</van-checkbox>
          </van-checkbox-group>
        </template>
      </van-field>
      <van-field name="bz" label="备注" placeholder="备注" />
      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit" >提交</van-button>
      </div>
    </van-form>
  </div>
</template>

<script>
import { Button } from "vant";
import { NavBar } from "vant";
import { Col, Row } from "vant";
import { Icon } from "vant";
import { Form } from "vant";
import { Field } from "vant";
import { RadioGroup, Radio, Ceckbox } from "vant";
import { Checkbox, CheckboxGroup } from "vant";
import { Cell, CellGroup } from "vant";
import { apply } from "@/api/crm/LoanApplication";
import { Notify } from "vant";
import { sendVerifyCode } from "@/api/crm/shop/msg.js";
import { apiGetProduct } from "@/api/crm/product";
import { getQueryString } from "@/utils/tool.js";
import { getUserInfo } from "@/api/system/user";
import { apiGetApplyOnline } from "@/api/crm/ApplyOnline";
export default {
  name: "ApplyInfo",
  components: {
    [Button.name]: Button,
    [NavBar.name]: NavBar,
    [Col.name]: Col,
    [Row.name]: Row,
    [Icon.name]: Icon,
    [Form.name]: Form,
    [Field.name]: Field,
    [Cell.name]: Cell,
    [CellGroup.name]: CellGroup,
    [RadioGroup.name]: RadioGroup,
    [Radio.name]: Radio,
    [Checkbox.name]: Checkbox,
    [CheckboxGroup.name]: CheckboxGroup,
    [Notify.Component.name]: Notify,
  },
  created: function () {
    //this.getProduct();
    var staffCode = getQueryString("staffCode");
    this.form.staffCode = staffCode;
    this.apiGetApplyOnline();
  },
  data() {
    return {
      productInfo: [],
      checkbox: false,
      codeTime: 60,
      btnTxt: "发送验证码",
      disabled: false,
      // 表单参数
      form: {},
      lable1: "期望額度",
      userInfo: [],
      staffCode: "",
      avatar: "",
      deptName: "",
      applyInfo: [],
      radio: "1",
    };
  },
  mounted() {
    document
      .querySelector("body")
      .setAttribute("style", "background-color:#f0f0f0");
  },
  beforeDestroy() {
    document.querySelector("body").removeAttribute("style");
  },
  methods: {
    callPhone() {
      window.location.href = "tel://" + userInfo.phonenumber;
    },
    apiGetApplyOnline() {
      var id = getQueryString("id");
      apiGetApplyOnline(id).then((response) => {
        this.applyInfo = response.data;
      });
    },
    getUserInfo() {
      this.staffCode = getQueryString("staffCode");
      getUserInfo(this.staffCode).then((response) => {
        this.userInfo = response.data;
        this.avatar = process.env.VUE_APP_BASE_API + "/" + this.userInfo.avatar;
        this.deptName = this.userInfo.dept.deptName;
      });
    },
    getProduct() {
      this.loading = true;
      var id = getQueryString("id");

      this.form.productId = id;
      apiGetProduct(id).then((response) => {
        this.productInfo = response.data;

        if (
          this.productInfo.type == "1001" ||
          this.productInfo.type == "1003"
        ) {
          this.lable1 = "期望額度";
        }
        if (this.productInfo.type == "1002") {
          this.lable1 = "存款金額";
        }
        this.loading = false;
      });
    },
    onSubmit() {
      apply(this.form)
        .then((res) => {
          if (res.code == 200) {
            Notify({ type: "success", message: res.msg });
          } else {
            Notify({ type: "warning", message: res.msg });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    onSendCode() {
      if (this.form.phone.length != 11) {
        Notify({ type: "warning", message: "请输入正确的手机号码" });
        return;
      }
      this.codeTime = 60;
      this.startTimer();
      sendVerifyCode({ phone: this.form.phone })
        .then((res) => {
          console.log(res.username);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    startTimer() {
      console.log(this.codeTime);
      if (this.codeTime > 0) {
        this.disabled = true;
        this.codeTime--;
        this.btnTxt = this.codeTime + "秒";

        setTimeout(this.startTimer, 1000);
      } else {
        this.codeTime = 0;
        this.btnTxt = "发送验证码";
        this.disabled = false;
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.panel_block {
  background-color: #ffffff;
  border-radius: 8px;
  height: 180px;
  margin-left: 15px;
  margin-right: 15px;
  margin-top: 10px;
  padding: 10px 10px 10px 10px;
}
.panel_block2 {
  background-color: #e4e4e3;
  border-radius: 8px;
  margin-left: 15px;
  margin-right: 15px;
  margin-top: 10px;
  color: dimgray;
  font-size: 13px;
  padding: 10px 10px 10px 10px;
}
.panel_block3 {
  background-color: #ffffff;
  border-radius: 8px;
  margin-left: 15px;
  margin-right: 15px;
  margin-top: 10px;
  color: dimgray;
  font-size: 13px;
  padding: 10px 10px 10px 15px;
}
.logo {
  width: 170px;
}
.Photo {
  width: 130px;
  height: 150px;
  border-radius: 8px;
  float: left;
}
.introduce {
  float: left;
  margin-right: 15px;
  margin-left: 20px;
}
.title {
  font-size: 18px;
  font-weight: bold;
  margin-top: 5px;
}
.title2 {
  font-size: 16px;
  margin-top: 5px;
  height: 22px;
}
.title3 {
  font-size: 14px;
  margin-top: 5px;
  font-weight: bold;
  color: #2f2f2f;
}
.line {
  width: 100%;
  float: left;
  height: 5px;
  border-bottom: 1px solid #dfdfdf;
}
</style>


