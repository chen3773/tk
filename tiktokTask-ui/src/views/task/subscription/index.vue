<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="UID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入UID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品ID" prop="productId">
        <el-input
          v-model="queryParams.productId"
          placeholder="请输入产品ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="认购份数" prop="subscriptionCount">
        <el-input
          v-model="queryParams.subscriptionCount"
          placeholder="请输入认购份数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="购入价格" prop="purchasePrice">
        <el-input
          v-model="queryParams.purchasePrice"
          placeholder="请输入购入价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="分红开始时间" prop="dividendStartTime">
        <el-date-picker clearable
                        v-model="queryParams.dividendStartTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择分红开始时间">
        </el-date-picker>
      </el-form-item> -->
      <el-form-item label="购入时间" prop="purchaseTime">
        <el-date-picker clearable
                        v-model="queryParams.purchaseTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择购入时间">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="累计分红" prop="cumulativeDividend">
        <el-input
          v-model="queryParams.cumulativeDividend"
          placeholder="请输入累计分红"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="过期时间" prop="expirationTime">
        <el-date-picker clearable
                        v-model="queryParams.expirationTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择过期时间">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="分红间隔天数" prop="dividendIntervalDays">
        <el-input
          v-model="queryParams.dividendIntervalDays"
          placeholder="请输入分红间隔天数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分红天数" prop="dividendDays">
        <el-input
          v-model="queryParams.dividendDays"
          placeholder="请输入分红天数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="剩余天数" prop="remainingDays">
        <el-input
          v-model="queryParams.remainingDays"
          placeholder="请输入剩余天数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="订单号" prop="orderNumber">
        <el-input
          v-model="queryParams.orderNumber"
          placeholder="请输入订单号"
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
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['task:subscription:add']"
        >新增</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['task:subscription:edit']"
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
          v-hasPermi="['task:subscription:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['task:subscription:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="subscriptionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="用户ID" align="center" prop="id" /> -->
      <el-table-column label="UID" align="center" prop="userId" />
      <el-table-column label="产品ID" align="center" prop="productId" />
      <el-table-column label="产品标题" align="center" prop="productTitle" />
      <el-table-column label="产品类型" align="center" prop="productType" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.product_type" :value="scope.row.productType"/>
        </template>
      </el-table-column>
      <el-table-column label="订单号" align="center" prop="orderNumber" />
      <el-table-column label="认购份数" align="center" prop="subscriptionCount" />
      <el-table-column label="购入价格" align="center" prop="purchasePrice" />
      <el-table-column label="时间" align="center" prop="purchaseTime" width="180">
        <template slot-scope="scope">
          <div style="text-align: left;">购入时间：{{ scope.row.purchaseTime }}</div>
          <div style="text-align: left;">过期时间：{{ scope.row.expirationTime }}</div>
          <div style="text-align: left;">分红时间：{{ scope.row.dividendStartTime }}</div>
        </template>
      </el-table-column>
      <!-- <el-table-column label="分红开始时间" align="center" prop="dividendStartTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dividendStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="购入时间" align="center" prop="purchaseTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.purchaseTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="分红" align="center" prop="cumulativeDividend" width="130">
        <template slot-scope="scope">
          <div style="text-align: left;">累计分红：{{ scope.row.cumulativeDividend }}</div>
          <div style="text-align: left;">分红周期：{{ scope.row.dividendIntervalDays }}</div>
          <div style="text-align: left;">分红天数：{{ scope.row.dividendDays }}</div>
        </template>
      </el-table-column>
      <!-- <el-table-column label="累计分红" align="center" prop="cumulativeDividend" /> -->
      <!-- <el-table-column label="过期时间" align="center" prop="expirationTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expirationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="分红间隔天数" align="center" prop="dividendIntervalDays" />
      <el-table-column label="分红天数" align="center" prop="dividendDays" /> -->
      <el-table-column label="剩余天数" align="center" prop="remainingDays" />
      <!-- <el-table-column label="状态" align="center" prop="status" /> -->

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['task:subscription:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['task:subscription:remove']"
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

    <!-- 添加或修改认购对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="产品ID" prop="productId">
          <el-input v-model="form.productId" placeholder="请输入产品ID" />
        </el-form-item>
        <el-form-item label="认购份数" prop="subscriptionCount">
          <el-input v-model="form.subscriptionCount" placeholder="请输入认购份数" />
        </el-form-item>
        <el-form-item label="购入价格" prop="purchasePrice">
          <el-input v-model="form.purchasePrice" placeholder="请输入购入价格" />
        </el-form-item>
        <el-form-item label="分红开始时间" prop="dividendStartTime">
          <el-date-picker clearable
                          v-model="form.dividendStartTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择分红开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="购入时间" prop="purchaseTime">
          <el-date-picker clearable
                          v-model="form.purchaseTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择购入时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="累计分红" prop="cumulativeDividend">
          <el-input v-model="form.cumulativeDividend" placeholder="请输入累计分红" />
        </el-form-item>
        <el-form-item label="过期时间" prop="expirationTime">
          <el-date-picker clearable
                          v-model="form.expirationTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择过期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="分红间隔天数" prop="dividendIntervalDays">
          <el-input v-model="form.dividendIntervalDays" placeholder="请输入分红间隔天数" />
        </el-form-item>
        <el-form-item label="分红天数" prop="dividendDays">
          <el-input v-model="form.dividendDays" placeholder="请输入分红天数" />
        </el-form-item>
        <el-form-item label="剩余天数" prop="remainingDays">
          <el-input v-model="form.remainingDays" placeholder="请输入剩余天数" />
        </el-form-item>
        <el-form-item label="订单号" prop="orderNumber">
          <el-input v-model="form.orderNumber" placeholder="请输入订单号" />
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
import { listSubscription, getSubscription, delSubscription, addSubscription, updateSubscription } from "@/api/task/subscription";

export default {
  name: "Subscription",
  dicts: ['product_type'],
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
      // 认购表格数据
      subscriptionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        productId: null,
        subscriptionCount: null,
        purchasePrice: null,
        dividendStartTime: null,
        purchaseTime: null,
        cumulativeDividend: null,
        expirationTime: null,
        dividendIntervalDays: null,
        dividendDays: null,
        remainingDays: null,
        status: null,
        orderNumber: null
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
    /** 查询认购列表 */
    getList() {
      this.loading = true;
      listSubscription(this.queryParams).then(response => {
        this.subscriptionList = response.rows;
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
        userId: null,
        productId: null,
        subscriptionCount: null,
        purchasePrice: null,
        dividendStartTime: null,
        purchaseTime: null,
        cumulativeDividend: null,
        expirationTime: null,
        dividendIntervalDays: null,
        dividendDays: null,
        remainingDays: null,
        status: null,
        orderNumber: null
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
      this.title = "添加认购";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSubscription(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改认购";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSubscription(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSubscription(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除认购编号为"' + ids + '"的数据项？').then(function() {
        return delSubscription(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('task/subscription/export', {
        ...this.queryParams
      }, `subscription_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
