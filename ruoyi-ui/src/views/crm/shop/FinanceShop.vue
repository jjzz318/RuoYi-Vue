<template>
  <div id="App">
    <div class="panel_block">
      <div class="Photo">
        <img src="@/assets/171579081443_.pic_hd.jpg" class="Photo" />
      </div>
      <div class="introduce">
        <div class="title">
          <img src="@/assets/logo.png" class="logo" />
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
    <van-cell-group class="panel_block3">
      <van-cell title="贷款产品" icon="card" is-link />
      <van-cell  v-for="p in productList" :title="p.name" size="large"  :label="p.money" url="/LoanApplication" />

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
      productList: [],
    };
  },
  created: function () {
    this.getList();
  },
  methods: {
    /** 查询产品管理列表 */
    getList() {
      var queryParams = {
        pageNum: 1,
        pageSize: 10,
        type: "1001",
        name: undefined,
        money: undefined,
        interestRate: undefined,
        content: undefined,
      };
      this.loading = true;
      apiListProduct(this.queryParams).then((response) => {
        this.productList = response.rows;
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
  data() {
    return {
      msg: "Welcome to Your Vue.js App",
    };
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
