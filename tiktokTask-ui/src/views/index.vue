<template>
  <div class="app-container home">
    <panel-group :info="userIndexData" />
    <el-table v-loading="loading" :data="userIndexData.AgencyPerformance" v-if="userIndexData.AgencyPerformance.length > 0">
      <el-table-column label="代理ID" align="center" prop="agent_id" >
      </el-table-column>
      <el-table-column label="代理昵称" align="center" prop="nick_name" >
      </el-table-column>
      <el-table-column label="总客户数" align="center" prop="total_customer_count" />
      <el-table-column label="今日注册客户数" align="center" prop="daily_customer_count" />
      <el-table-column label="本月注册客户数" align="center" prop="monthly_customer_count" />
      <el-table-column label="充值" align="center" prop="total_top_up_amount" width="220" >
        <template slot-scope="scope">
          <div style="text-align: left;">总充值：{{ scope.row.total_top_up_amount }}</div>
          <div style="text-align: left;">今日充值：{{ scope.row.daily_top_up_amount }}</div>
          <div style="text-align: left;">本月充值：{{ scope.row.monthly_top_up_amount }}</div>
        </template>
      </el-table-column>
      <el-table-column label="提现" align="center" prop="total_withdraw_amount" width="220" >
        <template slot-scope="scope">
          <div style="text-align: left;">总提现：{{ scope.row.total_withdraw_amount }}</div>
          <div style="text-align: left;">今日提现：{{ scope.row.daily_withdraw_amount }}</div>
          <div style="text-align: left;">本月提现：{{ scope.row.monthly_withdraw_amount }}</div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getHomePage } from "@/api/index";
import PanelGroup from "./dashboard/PanelGroup";
export default {
  name: "Index",
  components: {
    PanelGroup
  },
  data() {
    return {
      // 版本号
      version: "3.8.8",
      userIndexData: {},
      loading: true,
      dataList: []
    };
  },
  created() {
    this.getData();
  },
  activated() {
    this.getData();
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
    getData() {
      this.loading = true;
      getHomePage().then((response) => {
        this.userIndexData = response.data;
        this.loading = false;
      });
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

