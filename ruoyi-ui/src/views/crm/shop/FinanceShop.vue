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
          <span style="margin-left: 15px;font-weight:bold;">{{userInfo.dept.deptName}}</span>
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
          <div style="line-height:25px;margin-left: 35px;">点击查看二维码</div>
        </div>
      </div>
    </div>
        <div class="panel_block2">{{userInfo.remark}}</div>
    <van-cell-group class="panel_block3">
      <van-cell title="贷款产品" icon="card" is-link />
      <van-cell    v-for="(item, index) in loanProductList" :title="item.name" size="large" :key="index" :label="item.money" :url="'/crm/shop/LoanApplication?staffCode='+staffCode+'&id='+item.id" />
    </van-cell-group>

    <van-cell-group class="panel_block3">
      <van-cell title="存款产品" icon="card" is-link />
      <van-cell    v-for="(item, index) in depProductList" :title="item.name" size="large" :key="index" :label="item.money" :url="'/crm/shop/LoanApplication?staffCode='+staffCode+'&id='+item.id" />
    </van-cell-group>

    <van-cell-group class="panel_block3">
      <van-cell title="信用卡" icon="card" is-link />
      <van-cell title="五折信用卡"  size="large" label="1.满足消费条件，即享1元洗车、10元看电影、10元洗发、5折面包券；2.刷卡享受椒黄路仙150余家商户5折美食" url="https://cc.zj96596.com/bankcoas/mb/?from=singlemessage&isappinstalled=0"/>
      <van-cell title="腾讯联名卡" size="large" label="1.喜欢的权益自己选，加享150元3项自选权益；2.满足消费条件，即享1元洗车、10元看电影、10元洗发、5折面包券；3.刷卡享受椒黄路仙150余家商户5折美食" url="https://wepluscard.qq.com/web/huidu.html?platform=wx&offlineId=2020080421003901303621&channelID=46076&state=&code=0916FtFa1oy2xz0lLrFa1DLuqd26FtFm#/apply/main?platform=wx&channelID=46076"/>
    </van-cell-group>
  </div>
</template>

<script>
import { Button } from "vant";
import { NavBar } from "vant";
import { Col, Row } from "vant";
import { Icon } from "vant";
import { Cell, CellGroup } from "vant";
import { apiListProduct } from "@/api/crm/product";
import { getUserInfo } from "@/api/system/user";
import { getQueryString } from "@/utils/tool.js";
export default {
  name: "FinanceShop",
  components: {
    [Button.name]: Button,
    [NavBar.name]: NavBar,
    [Col.name]: Col,
    [Row.name]: Row,
    [Icon.name]: Icon,
    [Cell.name]: Cell,
    [CellGroup.name]: CellGroup,
  },
  data() {
    return {
      loanProductList: [],
      depProductList: [],
      otherProductList: [],
      userInfo:[],
      staffCode:'',
      avatar:''
    }
  },
  created: function () {
    this.getList();
    this.getUserInfo();
  },
  methods: {
    callPhone(){
      window.location.href = 'tel://'+userInfo.phonenumber
    },
    getUserInfo(){
        this.staffCode=getQueryString("staffCode");
        getUserInfo(this.staffCode).then((response) => {
            this.userInfo=response.data;
           this.avatar = process.env.VUE_APP_BASE_API + "/" + this.userInfo.avatar;
           this.deptName = this.userInfo.dept.deptName;
        });
    },
    /** 查询产品管理列表 */
    getList() {
      this.loading = true;
      apiListProduct({type: "1001"}).then((response) => {
        this.loanProductList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      apiListProduct({type: "1002"}).then((response) => {
        this.depProductList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      apiListProduct({type: "1003"}).then((response) => {
        this.otherProductList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
  },
  mounted() {
    document
      .querySelector("body")
      .setAttribute("style", "background-color:#f0f0f0");
  },
  beforeDestroy() {
    document.querySelector("body").removeAttribute("style");
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.panel_block {
  background-color: #ffffff;
  border-radius: 8px;
  height: 170px;
  margin-left: 15px;
  margin-right: 15px;
  margin-top: 10px;
  padding: 10px 10px 10px 10px;
}
.panel_block2 {
  background-color: #e4e4e3;
  border-radius: 8px;
  height: 50px;
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
