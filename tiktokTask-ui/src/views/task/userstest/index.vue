<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="uid" prop="uid">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入uid"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button icon="el-icon-refresh" size="mini" v-if="historyList.length > 0" @click="back">返回上一步</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8" v-if="false">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['task:users:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['task:users:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['task:users:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['task:users:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="usersList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="UID" align="center" prop="uid" width="140" >
        <template slot-scope="scope">
          <div><el-button type="text" style="padding: 0;" @click="toPage(scope.row.tkUsers.uid, scope.row.tkUsers)">UID：{{ scope.row.tkUsers.uid }}</el-button></div>
          <div :class="`tag-color${scope.row.tkUsers.svipLevel}`">等级：VIP{{ scope.row.tkUsers.svipLevel }}</div>
          <div>团队人数：{{ scope.row.tkUsers.teamsize }}</div>
          <div>邀请码：{{ scope.row.tkUsers.invitationCode }}</div>
        </template>
      </el-table-column>
      <el-table-column label="推荐人" align="center" prop="tkInvitation.inviterId" width="150" >
        <template slot-scope="scope">
          <div style="text-align: left;">推荐人ID：{{ scope.row.tkInvitation.inviterId }}</div>
          <div style="text-align: left;">归属代理：{{ scope.row.sysUser.nickName }}</div>
        </template>
      </el-table-column>
      <!-- <el-table-column label="归属代理" align="center" prop="sysUser.nickName" >
      </el-table-column> -->
      <el-table-column label="账户" align="center" prop="username" width="170" >
        <template slot-scope="scope">
          <div>账户名：{{ scope.row.tkUsers.username }}</div>
          <div>密码：{{ scope.row.tkUsers.password }}</div>
          <div>昵称：{{ scope.row.tkUsers.nickname }}</div>
        </template>
      </el-table-column>
      <!-- <el-table-column label="团队人数" align="center" prop="tkUsers.teamsize" /> -->
      <!-- <el-table-column label="密码" align="center" prop="password" /> -->
      <!-- <el-table-column label="昵称" align="center" prop="tkUsers.nickname" /> -->
      <!-- <el-table-column label="svip等级" align="center" prop="svipLevel" /> -->
      <el-table-column label="余额" align="center" prop="tkUsers.balance" width="150" >
        <template slot-scope="scope">
          <div style="color: #1890ff;">余额：{{ scope.row.tkUsers.balance }}</div>
          <div style="color: #ff9292;">冻结金额：{{ scope.row.tkUsers.nonWithdrawableBalance }}</div>
        </template>
      </el-table-column>
      <!-- <el-table-column label="不可提现余额" align="center" prop="nonWithdrawableBalance" /> -->
      <el-table-column label="钱包地址" align="center" prop="tkUsers.usdtAddress" />
      <el-table-column label="时间" align="center" prop="registrationTime" width="230">
        <template slot-scope="scope">
          <div style="text-align: left;">注册时间：{{ scope.row.tkUsers.registrationTime }}</div>
          <div style="text-align: left;">登录时间：{{ scope.row.tkUsers.logindate }}</div>
        </template>
      </el-table-column>
      <!-- <el-table-column label="邀请码" align="center" prop="tkUsers.invitationCode" /> -->
      <el-table-column label="状态" align="center" prop="tkUsers.userStatus" width="70">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.user_state" :value="scope.row.tkUsers.userStatus"/>
        </template>
      </el-table-column>

      <el-table-column label="提现" align="center" prop="withdraw" width="70">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.user_state" :value="scope.row.tkUsers.withdraw"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="tkUsers.remark" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { ViewSubordinates } from "@/api/task/userstest";
import { listTasks } from "@/api/task/tasks";

export default {
  name: "Users",
  dicts: ['user_state'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户信息表格数据
      usersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeRegistrationTime: [],
      // 查询参数
      queryParams: {
        id: null,
        pageNum: 1,
        pageSize: 10
      },
      historyList: []
    };
  },
  created() {
    if (this.$route.query.uid) {
      this.queryParams.id = this.$route.query.uid;
    }
    this.getList();
  },
  activated() {
    if (this.$route.query.uid) {
      this.queryParams.id = this.$route.query.uid;
    }
    this.getList();
  },
  methods: {
    /** 查询用户信息列表 */
    getList() {
      this.loading = true;
      ViewSubordinates(this.queryParams).then(response => {
        this.usersList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeRegistrationTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.uid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('task/users/export', {
        ...this.queryParams
      }, `users_${new Date().getTime()}.xlsx`)
    },
    toPage(id, val) {
      if (val.teamsize > 0) {
        this.historyList.push(this.queryParams.id)
        this.queryParams.id = id;
        this.getList();
      }
    },
    back() {
      this.queryParams.id = this.historyList[this.historyList.length - 1];
      this.historyList.pop();
      this.getList();
    }
  }
};
</script>
