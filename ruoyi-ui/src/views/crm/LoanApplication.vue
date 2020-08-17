<template>
  <div id="App">
    <div class="panel_block">
      <div class="Photo">
        <img src="./../../assets/171579081443_.pic_hd.jpg" class="Photo" />
      </div>
      <div class="introduce">
        <div class="title">
          <img src="./../../assets/logo.png" class="logo" />
        </div>
        <div class="title2">
          <b>农商小陈</b>
          <span style="margin-left: 15px;font-weight:bold;">客户经理</span>
        </div>
        <div class="title3">宁溪支行</div>
        <div class="title2">
          <div style="float:left;">
            <van-icon name="phone" size="23" color="#1989fa" />
          </div>
          <div style="line-height:25px;margin-left: 35px;">15867009090</div>
        </div>
        <div class="title2">
          <div style="float:left;">
            <van-icon name="enlarge" size="23" color="#1989fa" />
          </div>
          <div style="line-height:25px;margin-left: 35px;">点击查看二维码</div>
        </div>
      </div>
    </div>
    <div class="panel_block2">有多年金融行业经验，具有丰富的理财业务知识，熟知各项理财产品，能根据市场变化，和客户个人情况，为其提供最合适的金融产品。</div>
    <van-form @submit="onSubmit" class="panel_block3">
      <van-field
        v-model="username"
        name="username"
        label="身份证"
        placeholder="用户名"
        :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
        v-model="username"
        name="username"
        label="申请人"
        placeholder="申请人"
        :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
        v-model="username"
        name="username"
        label="期望额度"
        placeholder="用户名"
        :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
        v-model="username"
        name="username"
        label="详细地址"
        placeholder="用户名"
        :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
        v-model="username"
        name="username"
        label="手机号"
        placeholder="用户名"
        :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit">提交</van-button>
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
import { apply } from "@/api/crm/LoanApplication";
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
  },
  created: function () {},
  data() {
    return {

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
    onSubmit(values) {
        apply(values)
          .then((res) => {
            console.log(res.username);
          })
          .catch((error) => {
            console.log(error);
          });
        console.log("submit", values);
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


