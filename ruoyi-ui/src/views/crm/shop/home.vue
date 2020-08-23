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
    <van-cell-group class="panel_block3" style="background-color: #c7d9ff;">
      <van-cell title="进入我的微店" icon="card" is-link  style="background-color: #c7d9ff;"/>
    </van-cell-group>
    <div class="panel_block4">
        <span style="font-size: 30px;font-weight: bold;">23</span> <div>2020年8月</div>    
    </div>
    <van-cell-group class="panel_block3">
      <van-cell title="头条" icon="card" is-link />
      <van-cell value="昨日，在央视新闻相对论节目中，董明珠回应“如果再跟雷军打赌，赌什么？”：格力在空调领域拥有核心技术，现在是走在领先地位。格力要在保持主业不变的情况下，让中国的装备在世界上叫得响！请雷军提出自己行业的目标。" />
      <van-cell value="点评：2013年雷军被选为中国经济年度人物，编导在后台启发雷军和董明珠打个赌，就赌1元。可一上台董明珠说要赌就赌10个亿，于是赌约一下子成为社会话题。第一季赌约时，格力代表中国传统制造业，有30年的历史，小米代表新经济，是一家成立才三年多的小公司，放到一起就特别有话题。
近期，小米召开了“十周年主题演讲会”介绍公司目前已经投资了超过70家半导体和智能制造的公司，希望用互联网赋能制造业，深度参与制造业，甚至已经自研全智能设备。赌约第六年小米营收超越了格力，毕竟成功者不是战胜了别人，而是战胜了自己。
新一季赌约开启 ，在你看来小米和格力谁更能代表中国制造业的未来？" style="background-color: #e8effe;"/>
    </van-cell-group>
    
    <van-cell-group class="panel_block3">
      <van-cell title="银保监会支持小微企业、服务实体经济有哪些着力点？专家这样解读" icon="card" is-link />
      <van-cell value="国家金融与发展实验室副主任曾刚称：上半年银保监会紧扣“六保”的目标，特别是保市场主体这方面，出台一系列政策措施，效果还是比较明显的。目前普惠金融的投放量增长较快。" />
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
  name: "home",
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
    }
  },
  created: function () {
    this.getList();
  },
  methods: {
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
.panel_block4 {
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
