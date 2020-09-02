<template>
  <div id="App">
    <div class="panel_block">
      <div class="Photo">
        <img :src="avatar" class="Photo" />
      </div>
      <div class="introduce">
        <div class="title">
          <img src="@/assets/logo.png" class="logo" />
        </div>
        <div class="title2">
          <b>{{userInfo.nickName}}</b>
          <span style="margin-left: 15px;font-weight:bold;">{{deptName}}</span>
        </div>
        <div class="title3">{{userInfo.postName}}</div>
        <div class="title2">
          <div style="float:left;">
            <van-icon name="phone" size="23" color="#1989fa" />
          </div>
          <div style="line-height:25px;margin-left: 35px;" @click="callPhone">{{userInfo.phonenumber}}</div>
        </div>
        <div class="title2">
          <div style="float:left;">
            <van-icon name="enlarge" size="23" color="#1989fa" />
          </div>
          <div style="line-height:25px;margin-left: 35px;" @click="showPopup">点击查看二维码</div>
        </div>
      </div>
    </div>
    <div class="panel_block2">{{userInfo.remark}}</div>
    <van-cell-group class="panel_block3">
      <van-cell :title="productInfo.name" icon="card" />
      <van-cell :value="productInfo.content" />
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
        v-model="form.staffCode"
        name="staffCode"
        label="柜员号"
        placeholder="柜员号"
        :rules="[{ required: true, message: '柜员号' }]"
        v-show="false"
      />
      <van-field
        v-model="form.idcardCode"
        name="idcardCode"
        label="身份证"
        placeholder="身份证"
        :rules="[{ required: true, message: '请填写正确的身份证号码' }]"
      />
      <van-field
        v-model="form.liaisonMan"
        name="liaisonMan"
        label="申请人"
        placeholder="申请人"
        :rules="[{ required: true, message: '请填写申请人姓名' }]"
      />
      <van-field v-model="form.money" name="money" :label="lable1" :placeholder="lable1" />
      <van-field
        v-model="form.address"
        name="address"
        label="详细地址"
        placeholder="详细地址"
        :rules="[{ required: true, message: '请填写详细地址' }]"
      />
      <van-field
        v-model="form.phone"
        name="phone"
        label="手机号"
        placeholder="手机号"
        :rules="[{ required: true, message: '请填写手机号码' }]"
      />
      <van-field
        v-model="form.verifyCode"
        name="verifyCode"
        label="验证码"
        placeholder="验证码"
        :rules="[{ required: true, message: '验证码' }]"
      >
        <van-button
          round
          block
          slot="button"
          size="small"
          type="primary"
          native-type="button"
          :disabled="disabled"
          @click="onSendCode()"
        >{{btnTxt}}</van-button>
      </van-field>

      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit" @click="onSubmit()">提交</van-button>
      </div>
    </van-form>
        <van-popup v-model="show"><img :src="'http://cc1212.natappvip.cc/dev-api/profile/'+staffCode+'.png'"></van-popup>
  </div>
</template>

<script>
import { Button } from "vant";
import { NavBar } from "vant";
import { Col, Row } from "vant";
import { Icon } from "vant";
import { Form } from "vant";
import { Field } from "vant";
import { Cell, CellGroup } from "vant";
import { apply } from "@/api/crm/LoanApplication";
import { Notify } from "vant";
import { sendVerifyCode } from "@/api/crm/shop/msg.js";
import { apiGetProduct } from "@/api/crm/product";
import { getQueryString } from "@/utils/tool.js";
import { getUserInfo } from "@/api/system/user";
import { Popup } from 'vant';

export default {
  name: "LoanApplication",
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
    [Notify.Component.name]: Notify,
    [Popup.name]:Popup
  },
  created: function () {
    this.getProduct();
    var staffCode = getQueryString("staffCode");
    this.form.staffCode = staffCode;
    this.getUserInfo();
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
      show: false
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
    showPopup() {
      this.show = true;
    },
    callPhone(){

      window.location.href = 'tel://'+this.userInfo.phonenumber
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
  width: 150px;
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


