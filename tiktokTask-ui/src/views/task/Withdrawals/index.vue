<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户 ID" prop="uid">
        <el-input
          v-model="queryParams.uid"
          placeholder="请输入用户 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区块链地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入区块链地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提现金额" prop="amount">
        <el-input
          v-model="queryParams.amount"
          placeholder="请输入提现金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提现时间">
        <el-date-picker
          v-model="daterangeWithdrawalTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="提现状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择提现状态" clearable>
          <el-option
            v-for="dict in dict.type.tk_withdrawals"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
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
          v-hasPermi="['task:Withdrawals:add']"
        >新增</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['task:Withdrawals:edit']"
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
          v-hasPermi="['task:Withdrawals:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['task:Withdrawals:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="WithdrawalsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="自增主键" align="center" prop="id" /> -->
      <el-table-column label="用户ID" align="center" prop="uid" />
      <el-table-column label="用户名" align="center" prop="username" />
      <el-table-column label="区块链地址" align="center" prop="address" />
      <el-table-column label="链名称" align="center" prop="blockchainName" />
      <el-table-column label="提现金额" align="center" prop="amount" />
      <el-table-column label="提现时间" align="center" prop="withdrawalTime" width="180">
      </el-table-column>
      <el-table-column label="提现状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.tk_withdrawals" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row, true)"
            v-hasPermi="['task:acceptances:edit']"
            v-if="scope.row.status == 0"
          >通过</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row, false)"
            v-hasPermi="['task:acceptances:edit']"
            v-if="scope.row.status == 0"
          >驳回</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate2(scope.row, true)"
            v-hasPermi="['task:acceptances:edit']"
            v-if="scope.row.tips == 0"
          >关闭提示音</el-button>
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['task:Withdrawals:edit']"
          >修改</el-button> -->
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['task:acceptances:remove']"
          >删除</el-button> -->
        </template>
        <!-- <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['task:Withdrawals:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['task:Withdrawals:remove']"
          >删除</el-button>
        </template> -->
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改提现记录对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户 ID" prop="uid">
          <el-input v-model="form.uid" placeholder="请输入用户 ID" />
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="区块链地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入区块链地址" />
        </el-form-item>
        <el-form-item label="区块链地址" prop="address">
          <el-input v-model="form.blockchainName" placeholder="请输入区块链名称" />
        </el-form-item>
        <el-form-item label="提现金额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入提现金额" />
        </el-form-item>
        <el-form-item label="提现时间" prop="withdrawalTime">
          <el-date-picker clearable
            v-model="form.withdrawalTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择提现时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="提现状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择提现状态">
            <el-option
              v-for="dict in dict.type.tk_withdrawals"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import { listWithdrawals, getWithdrawals, delWithdrawals, addWithdrawals, updateWithdrawals } from "@/api/task/Withdrawals";

export default {
  name: "Withdrawals",
  dicts: ['tk_withdrawals'],
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
      // 提现记录表格数据
      WithdrawalsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 提现状态时间范围
      daterangeWithdrawalTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        uid: null,
        username: null,
        address: null,
        amount: null,
        withdrawalTime: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        uid: [
          { required: true, message: "用户 ID不能为空", trigger: "blur" }
        ],
        username: [
          { required: true, message: "用户名不能为空", trigger: "blur" }
        ],
        amount: [
          { required: true, message: "提现金额不能为空", trigger: "blur" }
        ],
        withdrawalTime: [
          { required: true, message: "提现时间不能为空", trigger: "blur" }
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
    /** 查询提现记录列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeWithdrawalTime && '' != this.daterangeWithdrawalTime) {
        this.queryParams.params["beginWithdrawalTime"] = this.daterangeWithdrawalTime[0];
        this.queryParams.params["endWithdrawalTime"] = this.daterangeWithdrawalTime[1];
      }
      listWithdrawals(this.queryParams).then(response => {
        this.WithdrawalsList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        id: null,
        uid: null,
        username: null,
        address: null,
        blockchainName:null,
        amount: null,
        withdrawalTime: null,
        status: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeWithdrawalTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加提现记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row, bol) {
      this.reset();
      updateWithdrawals({
        id: row.id,
        uid: row.uid,
        username: row.username,
        address: row.address,
        blockchainName: row.blockchainName,
        amount: row.amount,
        withdrawalTime: row.withdrawalTime,
        status: bol ? 1 : 2
      }).then(response => {
        this.getList();
        this.$modal.msgSuccess("审核成功");
      });
      // getWithdrawals(id).then(response => {
      //   this.form = response.data;
      //   this.open = true;
      //   this.title = "修改提现记录";
      // });
    },
    handleUpdate2(row, bol) {
      const id = row.id
      updateWithdrawals({
        id,
        tips: bol ? '1' : '0'
      }).then(response => {
        this.getList();
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWithdrawals(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWithdrawals(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除提现记录编号为"' + ids + '"的数据项？').then(function() {
        return delWithdrawals(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('task/Withdrawals/export', {
        ...this.queryParams
      }, `Withdrawals_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
