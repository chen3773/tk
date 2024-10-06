<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="体验任务数" prop="experienceTaskCount">
        <el-input
          v-model="queryParams.experienceTaskCount"
          placeholder="请输入体验任务数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="普通任务数" prop="regularTaskCount">
        <el-input
          v-model="queryParams.regularTaskCount"
          placeholder="请输入普通任务数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="隐藏任务数" prop="hiddenTaskCount">
        <el-input
          v-model="queryParams.hiddenTaskCount"
          placeholder="请输入隐藏任务数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最低提现金额" prop="minimumWithdrawalAmount">
        <el-input
          v-model="queryParams.minimumWithdrawalAmount"
          placeholder="请输入最低提现金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区块链名称" prop="blockchain">
        <el-input
          v-model="queryParams.blockchain"
          placeholder="请输入区块链名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['task:userDefault:add']"
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
          v-hasPermi="['task:userDefault:edit']"
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
          v-hasPermi="['task:userDefault:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['task:userDefault:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userDefaultList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="体验任务数" align="center" prop="experienceTaskCount" />
      <el-table-column label="普通任务数" align="center" prop="regularTaskCount" />
      <el-table-column label="隐藏任务数" align="center" prop="hiddenTaskCount" />
      <el-table-column label="最低提现金额" align="center" prop="minimumWithdrawalAmount" />
      <el-table-column label="区块链名称" align="center" prop="blockchain" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['task:userDefault:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['task:userDefault:remove']"
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

    <!-- 添加或修改系统默认配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="体验任务数" prop="experienceTaskCount">
          <el-input v-model="form.experienceTaskCount" placeholder="请输入体验任务数" />
        </el-form-item>
        <el-form-item label="普通任务数" prop="regularTaskCount">
          <el-input v-model="form.regularTaskCount" placeholder="请输入普通任务数" />
        </el-form-item>
        <el-form-item label="隐藏任务数" prop="hiddenTaskCount">
          <el-input v-model="form.hiddenTaskCount" placeholder="请输入隐藏任务数" />
        </el-form-item>
        <el-form-item label="最低提现金额" prop="minimumWithdrawalAmount">
          <el-input v-model="form.minimumWithdrawalAmount" placeholder="请输入最低提现金额" />
        </el-form-item>
        <el-form-item label="区块链名称" prop="blockchain">
          <el-input v-model="form.blockchain" placeholder="请输入区块链名称" />
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
import { listUserDefault, getUserDefault, delUserDefault, addUserDefault, updateUserDefault } from "@/api/task/userDefault";

export default {
  name: "UserDefault",
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
      // 系统默认配置表格数据
      userDefaultList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        experienceTaskCount: null,
        regularTaskCount: null,
        hiddenTaskCount: null,
        minimumWithdrawalAmount: null,
        blockchain: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询系统默认配置列表 */
    getList() {
      this.loading = true;
      listUserDefault(this.queryParams).then(response => {
        this.userDefaultList = response.rows;
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
        experienceTaskCount: null,
        regularTaskCount: null,
        hiddenTaskCount: null,
        minimumWithdrawalAmount: null,
        blockchain: null
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
      this.title = "添加系统默认配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUserDefault(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改系统默认配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUserDefault(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUserDefault(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除系统默认配置编号为"' + ids + '"的数据项？').then(function() {
        return delUserDefault(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('task/userDefault/export', {
        ...this.queryParams
      }, `userDefault_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
