<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="接取任务的用户ID" prop="uid">
        <el-input
          v-model="queryParams.uid"
          placeholder="请输入接取任务的用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联的任务ID" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入关联的任务ID"
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
          v-hasPermi="['task:acceptances:add']"
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
          v-hasPermi="['task:acceptances:edit']"
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
          v-hasPermi="['task:acceptances:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['task:acceptances:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="acceptancesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="接取任务的唯一标识符" align="center" prop="id" />
      <el-table-column label="接取任务的用户ID" align="center" prop="uid" />
      <el-table-column label="关联的任务ID" align="center" prop="taskId" />
      <el-table-column label="接取任务的状态" align="center" prop="status" />
      <el-table-column label="提交的图片链接" align="center" prop="submittedImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.submittedImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="提交时间" align="center" prop="submissionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.submissionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核通过时间" align="center" prop="approvalTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.approvalTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['task:acceptances:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['task:acceptances:remove']"
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

    <!-- 添加或修改用户接取任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="接取任务的用户ID" prop="uid">
          <el-input v-model="form.uid" placeholder="请输入接取任务的用户ID" />
        </el-form-item>
        <el-form-item label="关联的任务ID" prop="taskId">
          <el-input v-model="form.taskId" placeholder="请输入关联的任务ID" />
        </el-form-item>
        <el-form-item label="提交的图片链接" prop="submittedImage">
          <image-upload v-model="form.submittedImage"/>
        </el-form-item>
        <el-form-item label="提交时间" prop="submissionTime">
          <el-date-picker clearable
            v-model="form.submissionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择提交时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核通过时间" prop="approvalTime">
          <el-date-picker clearable
            v-model="form.approvalTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择审核通过时间">
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
import { listAcceptances, getAcceptances, delAcceptances, addAcceptances, updateAcceptances } from "@/api/task/acceptances";

export default {
  name: "Acceptances",
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
      // 用户接取任务表格数据
      acceptancesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        uid: null,
        taskId: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        uid: [
          { required: true, message: "接取任务的用户ID不能为空", trigger: "blur" }
        ],
        taskId: [
          { required: true, message: "关联的任务ID不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "接取任务的状态不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户接取任务列表 */
    getList() {
      this.loading = true;
      listAcceptances(this.queryParams).then(response => {
        this.acceptancesList = response.rows;
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
        taskId: null,
        status: null,
        submittedImage: null,
        submissionTime: null,
        approvalTime: null
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
      this.title = "添加用户接取任务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAcceptances(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户接取任务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAcceptances(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAcceptances(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除用户接取任务编号为"' + ids + '"的数据项？').then(function() {
        return delAcceptances(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('task/acceptances/export', {
        ...this.queryParams
      }, `acceptances_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
