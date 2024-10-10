<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">-->
<!--      <el-form-item label="" prop="id">-->
<!--        <el-input-->
<!--          v-model="queryParams.id"-->
<!--          placeholder="请输入"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="等级" prop="vipLevel">-->
<!--        <el-input-->
<!--          v-model="queryParams.vipLevel"-->
<!--          placeholder="请输入等级"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="每日任务数量" prop="dailyTaskCount">-->
<!--        <el-input-->
<!--          v-model="queryParams.dailyTaskCount"-->
<!--          placeholder="请输入每日任务数量"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="升级所需金额" prop="upgradeAmount">-->
<!--        <el-input-->
<!--          v-model="queryParams.upgradeAmount"-->
<!--          placeholder="请输入升级所需金额"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="是否开启升级" prop="status">-->
<!--        <el-select v-model="queryParams.status" placeholder="请选择是否开启升级" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.on_off"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

<!--    <el-row :gutter="10" class="mb8">-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['task:svipSetting:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['task:svipSetting:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['task:svipSetting:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['task:svipSetting:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
<!--    </el-row>-->

    <el-table v-loading="loading" :data="svipSettingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="" align="center" prop="id" />
      <el-table-column label="等级" align="center" prop="vipLevel" />
      <el-table-column label="每日任务数量" align="center" prop="dailyTaskCount" />
      <el-table-column label="升级所需金额" align="center" prop="upgradeAmount" />
      <el-table-column label="是否开启升级" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.on_off" :value="scope.row.status"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="升级客服地址" align="center" prop="customerServiceAddress" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['task:svipSetting:edit']"
          >修改</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['task:svipSetting:remove']"-->
<!--          >删除</el-button>-->
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

    <!-- 添加或修改svip默认配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="等级" prop="vipLevel">
          <el-input v-model="form.vipLevel" placeholder="请输入等级" />
        </el-form-item>
        <el-form-item label="每日任务数量" prop="dailyTaskCount">
          <el-input v-model="form.dailyTaskCount" placeholder="请输入每日任务数量" />
        </el-form-item>
        <el-form-item label="升级所需金额" prop="upgradeAmount">
          <el-input v-model="form.upgradeAmount" placeholder="请输入升级所需金额" />
        </el-form-item>
        <el-form-item label="是否开启升级" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.on_off"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
<!--        <el-form-item label="升级客服地址" prop="customerServiceAddress">-->
<!--          <el-input v-model="form.customerServiceAddress" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSvipSetting, getSvipSetting, delSvipSetting, addSvipSetting, updateSvipSetting } from "@/api/task/svipSetting";

export default {
  name: "SvipSetting",
  dicts: ['on_off'],
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
      // svip默认配置表格数据
      svipSettingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        vipLevel: null,
        dailyTaskCount: null,
        upgradeAmount: null,
        status: null,
        customerServiceAddress: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        vipLevel: [
          { required: true, message: "等级不能为空", trigger: "blur" }
        ],
        dailyTaskCount: [
          { required: true, message: "每日任务数量不能为空", trigger: "blur" }
        ],
        upgradeAmount: [
          { required: true, message: "升级所需金额不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询svip默认配置列表 */
    getList() {
      this.loading = true;
      listSvipSetting(this.queryParams).then(response => {
        this.svipSettingList = response.rows;
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
        vipLevel: null,
        dailyTaskCount: null,
        upgradeAmount: null,
        status: null,
        customerServiceAddress: null
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
      this.title = "添加svip默认配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSvipSetting(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改svip默认配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSvipSetting(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSvipSetting(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除svip默认配置编号为"' + ids + '"的数据项？').then(function() {
        return delSvipSetting(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('task/svipSetting/export', {
        ...this.queryParams
      }, `svipSetting_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
