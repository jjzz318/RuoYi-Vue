<template>
  <div id="App">
    <van-cell-group class="panel_block3">
      <van-cell title="客户经理列表" icon="card" />
    </van-cell-group>
    <van-card
      v-for="(item, userCount) in userlist"
      class="panel_block3"
      :thumb-link="url2+'9622554'"
      :desc="'柜员号：'+item.columns.user_name"
      :title="'姓名：'+item.columns.nick_name"
      :thumb="url+item.columns.avatar"
    >
      <template #tags>
        <van-tag plain type="danger">{{item.columns.dept_name}}:</van-tag>
        <van-tag plain type="danger">{{item.columns.post_name}}</van-tag>
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
    let href = window.location.href;
    this.url="http://"+href.split('/')[2]+process.env.VUE_APP_BASE_API+"";
    this.url2="http://"+href.split('/')[2]+'/crm/shop/FinanceShop?staffCode=';
    console.log(this.url2)
    this.getListUser();
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
      userlist: [],
      userCount: 0,
      staffCode: "",
      avatar: "",
      deptName: "",
      applyInfo: [],
      radio: "1",
      url:'',
      url2:'',
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
    getListUser() {
      getListUser().then((response) => {
        this.userlist = response.data;
        this.userCount = this.userlist.length;
      });
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


