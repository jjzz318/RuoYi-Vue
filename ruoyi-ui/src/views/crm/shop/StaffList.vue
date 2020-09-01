<template>
  <div id="App">
    <van-cell-group class="panel_block3">
      <van-cell title="客户经理列表" icon="card" />
    </van-cell-group>
    <van-card
      class="panel_block3"
      thumb-link="http://cc1212.natappvip.cc/crm/shop/home?staffCode=9622554"
      desc="柜员号：9622554"
      title="姓名：黄寅卓"
      thumb="http://cc1212.natappvip.cc/dev-api/profile/avatar/2020/08/31/4bd8a42f755c413c8ecbe0843015268a.jpeg"
    >
      <template #tags>
        <van-tag plain type="danger">营业部</van-tag>
        <van-tag plain type="danger">支行副行长</van-tag>
      </template>
    </van-card>
    <van-card
      class="panel_block3"
      thumb-link="http://cc1212.natappvip.cc/crm/shop/home?staffCode=9622031"
      desc="柜员号：9622031"
      title="姓名：王伟胜"
      thumb="http://cc1212.natappvip.cc/dev-api/profile/avatar/2020/08/31/1c67712e0bc1d86b321530300e9141d7.jpeg"
    >
      <template #tags>
        <van-tag plain type="danger">潮济分理处</van-tag>
        <van-tag plain type="danger">客户经理</van-tag>
      </template>
    </van-card>
     <van-card
      class="panel_block3"
      thumb-link="http://cc1212.natappvip.cc/crm/shop/home?staffCode=9622617"
      desc="柜员号：9622617"
      title="姓名：朱欢欢"
      thumb="http://cc1212.natappvip.cc/dev-api/profile/avatar/2020/08/31/96478d967aa555fe7169f83eabf9da49.jpeg"
    >
      <template #tags>
        <van-tag plain type="danger">营业部</van-tag>
        <van-tag plain type="danger">支行副行长</van-tag>
      </template>
    </van-card>
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
import { getUserInfo, getListUser } from "@/api/system/user";
import { apiGetApplyOnline } from "@/api/crm/ApplyOnline";
import { Card } from "vant";
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
    [Card.name]: Card,
  },
  created: function () {
    //this.getProduct();
    //getListUser()
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
    getListUser() {
      getListUser().then((response) => {
        this.userInfo = response.data;
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


