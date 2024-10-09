<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="账户名" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入账户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="密码" prop="password">
        <el-input
          v-model="queryParams.password"
          placeholder="请输入密码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="昵称" prop="nickname">
        <el-input
          v-model="queryParams.nickname"
          placeholder="请输入昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="svip等级" prop="svipLevel">
        <el-input
          v-model="queryParams.svipLevel"
          placeholder="请输入svip等级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="总奖励金额" prop="totareward">
        <el-input
          v-model="queryParams.totareward"
          placeholder="请输入总奖励金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="余额" prop="balance">
        <el-input
          v-model="queryParams.balance"
          placeholder="请输入余额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="不可提现余额" prop="nonWithdrawableBalance">
        <el-input
          v-model="queryParams.nonWithdrawableBalance"
          placeholder="请输入不可提现余额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="提现USDT地址" prop="usdtAddress">
        <el-input
          v-model="queryParams.usdtAddress"
          placeholder="请输入提现USDT地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="注册时间">
        <el-date-picker
          v-model="daterangeRegistrationTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="头像" prop="avatar">
        <el-input
          v-model="queryParams.avatar"
          placeholder="请输入头像"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="推荐人ID" prop="referrerId">
        <el-input
          v-model="queryParams.referrerId"
          placeholder="请输入推荐人ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="用户状态" prop="userStatus">
        <el-select v-model="queryParams.userStatus" placeholder="请选择用户状态" clearable>
          <el-option
            v-for="dict in dict.type.user_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item> -->
      <el-form-item>
	    <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['task:userstest:add']"
        >新增</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="userstestList"
      row-key="uid"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="uid" prop="uid" width="180" />
      <el-table-column label="账户名" prop="username" />
      <el-table-column label="密码" align="center" prop="password" />
      <el-table-column label="昵称" align="center" prop="nickname" />
      <el-table-column label="svip等级" align="center" prop="svipLevel" />
      <el-table-column label="总奖励金额" align="center" prop="totareward" />
      <el-table-column label="余额" align="center" prop="balance" />
      <el-table-column label="不可提现余额" align="center" prop="nonWithdrawableBalance" />
      <el-table-column label="提现USDT地址" align="center" prop="usdtAddress" />
      <el-table-column label="注册时间" align="center" prop="registrationTime" width="180">
        <!-- <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registrationTime, '{y}-{m}-{d}') }}</span>
        </template> -->
      </el-table-column>
      <!-- <el-table-column label="头像" align="center" prop="avatar">
        <template slot-scope="scope">
          <image-preview :src="scope.row.avatar" :width="50" :height="50" />
        </template>
      </el-table-column> -->
      <el-table-column label="推荐人ID" align="center" prop="referrerId" />
      <el-table-column label="用户状态" align="center" prop="userStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.user_state" :value="scope.row.userStatus"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="备注" align="center" prop="remark" />
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['task:userstest:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['task:userstest:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['task:userstest:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>

    <!-- 添加或修改用户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="账户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入账户名" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="form.nickname" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="svip等级" prop="svipLevel">
          <el-input v-model="form.svipLevel" placeholder="请输入svip等级" />
        </el-form-item>
        <el-form-item label="总奖励金额" prop="totareward">
          <el-input v-model="form.totareward" placeholder="请输入总奖励金额" />
        </el-form-item>
        <el-form-item label="余额" prop="balance">
          <el-input v-model="form.balance" placeholder="请输入余额" />
        </el-form-item>
        <el-form-item label="不可提现余额" prop="nonWithdrawableBalance">
          <el-input v-model="form.nonWithdrawableBalance" placeholder="请输入不可提现余额" />
        </el-form-item>
        <el-form-item label="提现USDT地址" prop="usdtAddress">
          <el-input v-model="form.usdtAddress" placeholder="请输入提现USDT地址" />
        </el-form-item>
        <el-form-item label="注册时间" prop="registrationTime">
          <el-date-picker clearable
            v-model="form.registrationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择注册时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="头像" prop="avatar">
          <el-input v-model="form.avatar" placeholder="请输入头像" />
        </el-form-item>
        <el-form-item label="推荐人ID" prop="referrerId">
          <treeselect v-model="form.referrerId" :options="userstestOptions" :normalizer="normalizer" placeholder="请选择推荐人ID" />
        </el-form-item>
        <el-form-item label="用户状态" prop="userStatus">
          <el-radio-group v-model="form.userStatus">
            <el-radio
              v-for="dict in dict.type.user_state"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUserstest, getUserstest, delUserstest, addUserstest, updateUserstest } from "@/api/task/userstest";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Userstest",
  dicts: ['user_state'],
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 用户信息表格数据
      userstestList: [],
      // 用户信息树选项
      userstestOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 备注时间范围
      daterangeRegistrationTime: [],
      // 查询参数
      queryParams: {
        username: null,
        password: null,
        nickname: null,
        svipLevel: null,
        totareward: null,
        balance: null,
        nonWithdrawableBalance: null,
        usdtAddress: null,
        registrationTime: null,
        avatar: null,
        referrerId: null,
        userStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        username: [
          { required: true, message: "账户名不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  activated() {
    this.getList();
  },
  methods: {
    /** 查询用户信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeRegistrationTime && '' != this.daterangeRegistrationTime) {
        this.queryParams.params["beginRegistrationTime"] = this.daterangeRegistrationTime[0];
        this.queryParams.params["endRegistrationTime"] = this.daterangeRegistrationTime[1];
      }
      listUserstest(this.queryParams).then(response => {
        this.userstestList = this.handleTree(response.data, "uid", "referrerId");
        this.loading = false;
      });
    },
    /** 转换用户信息数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.uid,
        label: node.username,
        children: node.children
      };
    },
	/** 查询用户信息下拉树结构 */
    getTreeselect() {
      listUserstest().then(response => {
        this.userstestOptions = [];
        const data = { uid: 0, username: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "uid", "referrerId");
        this.userstestOptions.push(data);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        uid: null,
        username: null,
        password: null,
        nickname: null,
        svipLevel: null,
        totareward: null,
        balance: null,
        nonWithdrawableBalance: null,
        usdtAddress: null,
        registrationTime: null,
        avatar: null,
        referrerId: null,
        userStatus: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeRegistrationTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.uid) {
        this.form.referrerId = row.uid;
      } else {
        this.form.referrerId = 0;
      }
      this.open = true;
      this.title = "添加用户信息";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.referrerId = row.referrerId;
      }
      getUserstest(row.uid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.uid != null) {
            updateUserstest(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUserstest(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除用户信息编号为"' + row.uid + '"的数据项？').then(function() {
        return delUserstest(row.uid);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
