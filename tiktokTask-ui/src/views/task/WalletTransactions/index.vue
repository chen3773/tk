<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户ID" prop="userid">
        <el-input
          v-model="queryParams.userid"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交易金额" prop="amount">
        <el-input
          v-model="queryParams.amount"
          placeholder="请输入交易金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交易日期和时间" prop="transactionDate">
        <el-date-picker clearable
          v-model="queryParams.transactionDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择交易日期和时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="交易后余额" prop="fundBalance">
        <el-input
          v-model="queryParams.fundBalance"
          placeholder="请输入交易后余额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交易类别" prop="category">
        <el-input
          v-model="queryParams.category"
          placeholder="请输入交易类别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录最后更新时间" prop="updatedAt">
        <el-date-picker clearable
          v-model="queryParams.updatedAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择记录最后更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['task:WalletTransactions:add']"
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
          v-hasPermi="['task:WalletTransactions:edit']"
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
          v-hasPermi="['task:WalletTransactions:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['task:WalletTransactions:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="WalletTransactionsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="交易ID，自动递增" align="center" prop="id" /> -->
      <el-table-column label="用户ID" align="center" prop="userid" />
      <el-table-column label="交易类型" align="center" prop="transactionType" />
      <el-table-column label="交易金额" align="center" prop="amount" />
      <el-table-column label="交易时间" align="center" prop="transactionDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.transactionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="交易后余额" align="center" prop="fundBalance" />
      <el-table-column label="交易描述/备注" align="center" prop="description" />
      <el-table-column label="交易类别" align="center" prop="category" />
<!--      <el-table-column label="交易状态" align="center" prop="transactionStatus" />-->
      <el-table-column label="记录最后更新时间" align="center" prop="updatedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['task:WalletTransactions:edit']"
          >金额回滚</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['task:WalletTransactions:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户钱包交易记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户ID" prop="userid">
          <el-input v-model="form.userid" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="交易金额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入交易金额" />
        </el-form-item>
        <el-form-item label="交易日期和时间" prop="transactionDate">
          <el-date-picker clearable
            v-model="form.transactionDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择交易日期和时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交易后余额" prop="fundBalance">
          <el-input v-model="form.fundBalance" placeholder="请输入交易后余额" />
        </el-form-item>
        <el-form-item label="交易描述/备注" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="交易类别" prop="category">
          <el-input v-model="form.category" placeholder="请输入交易类别" />
        </el-form-item>
        <el-form-item label="记录最后更新时间" prop="updatedAt">
          <el-date-picker clearable
            v-model="form.updatedAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择记录最后更新时间">
          </el-date-picker>
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
import { listWalletTransactions, getWalletTransactions, delWalletTransactions, addWalletTransactions, updateWalletTransactions, TakeOut } from "@/api/task/WalletTransactions";

export default {
  name: "WalletTransactions",
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
      // 用户钱包交易记录表格数据
      WalletTransactionsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userid: null,
        transactionType: null,
        amount: null,
        transactionDate: null,
        fundBalance: null,
        description: null,
        category: null,
        transactionStatus: null,
        updatedAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userid: [
          { required: true, message: "用户ID", trigger: "blur" }
        ],
        transactionType: [
          { required: true, message: "交易类型不能为空", trigger: "change" }
        ],
        amount: [
          { required: true, message: "交易金额不能为空", trigger: "blur" }
        ],
        transactionDate: [
          { required: true, message: "交易日期和时间不能为空", trigger: "blur" }
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
    /** 查询用户钱包交易记录列表 */
    getList() {
      this.loading = true;
      listWalletTransactions(this.queryParams).then(response => {
        this.WalletTransactionsList = response.rows;
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
        userid: null,
        transactionType: null,
        amount: null,
        transactionDate: null,
        fundBalance: null,
        description: null,
        category: null,
        transactionStatus: null,
        updatedAt: null
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
      this.title = "添加用户钱包交易记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      this.$modal.confirm('是否确认回滚金额？').then(function() {
        return TakeOut({
          id: id
        });
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("回滚成功");
      }).catch(() => {});
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWalletTransactions(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWalletTransactions(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除用户钱包交易记录编号为"' + ids + '"的数据项？').then(function() {
        return delWalletTransactions(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('task/WalletTransactions/export', {
        ...this.queryParams
      }, `WalletTransactions_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
