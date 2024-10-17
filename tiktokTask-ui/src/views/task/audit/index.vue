<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">-->
<!--      <el-form-item label="${comment}" prop="lv0">-->
<!--        <el-select v-model="queryParams.lv0" placeholder="请选择${comment}" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.on_off"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="${comment}" prop="lv1">-->
<!--        <el-select v-model="queryParams.lv1" placeholder="请选择${comment}" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.on_off"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="${comment}" prop="lv2">-->
<!--        <el-select v-model="queryParams.lv2" placeholder="请选择${comment}" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.on_off"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="${comment}" prop="lv3">-->
<!--        <el-select v-model="queryParams.lv3" placeholder="请选择${comment}" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.on_off"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="${comment}" prop="lv4">-->
<!--        <el-select v-model="queryParams.lv4" placeholder="请选择${comment}" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.on_off"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="${comment}" prop="lv5">-->
<!--        <el-select v-model="queryParams.lv5" placeholder="请选择${comment}" clearable>-->
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
<!--          v-hasPermi="['task:audit:add']"-->
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
<!--          v-hasPermi="['task:audit:edit']"-->
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
<!--          v-hasPermi="['task:audit:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['task:audit:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
<!--    </el-row>-->

    <el-table v-loading="loading" :data="auditList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="${comment}" align="center" prop="id" />-->
      <el-table-column label="体验任务" align="center" prop="lv0">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.on_off" :value="scope.row.lv0"/>
        </template>
      </el-table-column>
      <el-table-column label="一级任务" align="center" prop="lv1">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.on_off" :value="scope.row.lv1"/>
        </template>
      </el-table-column>
      <el-table-column label="二级任务" align="center" prop="lv2">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.on_off" :value="scope.row.lv2"/>
        </template>
      </el-table-column>
      <el-table-column label="三级任务" align="center" prop="lv3">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.on_off" :value="scope.row.lv3"/>
        </template>
      </el-table-column>
      <el-table-column label="四级任务" align="center" prop="lv4">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.on_off" :value="scope.row.lv4"/>
        </template>
      </el-table-column>
      <el-table-column label="五级任务" align="center" prop="lv5">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.on_off" :value="scope.row.lv5"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['task:audit:edit']"
          >修改</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['task:audit:remove']"-->
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

    <!-- 添加或修改自动审核对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="体验任务" prop="lv0">
          <el-radio-group v-model="form.lv0">
            <el-radio
              v-for="dict in dict.type.on_off"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="一级任务" prop="lv1">
          <el-radio-group v-model="form.lv1">
            <el-radio
              v-for="dict in dict.type.on_off"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="二级任务" prop="lv2">
          <el-radio-group v-model="form.lv2">
            <el-radio
              v-for="dict in dict.type.on_off"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="三级任务" prop="lv3">
          <el-radio-group v-model="form.lv3">
            <el-radio
              v-for="dict in dict.type.on_off"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="四级任务" prop="lv4">
          <el-radio-group v-model="form.lv4">
            <el-radio
              v-for="dict in dict.type.on_off"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="五级任务" prop="lv5">
          <el-radio-group v-model="form.lv5">
            <el-radio
              v-for="dict in dict.type.on_off"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
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
import { listAudit, getAudit, delAudit, addAudit, updateAudit } from "@/api/task/audit";

export default {
  name: "Audit",
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
      // 自动审核表格数据
      auditList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        lv0: null,
        lv1: null,
        lv2: null,
        lv3: null,
        lv4: null,
        lv5: null
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
    /** 查询自动审核列表 */
    getList() {
      this.loading = true;
      listAudit(this.queryParams).then(response => {
        this.auditList = response.rows;
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
        lv0: null,
        lv1: null,
        lv2: null,
        lv3: null,
        lv4: null,
        lv5: null
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
      this.title = "添加自动审核";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAudit(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改自动审核";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAudit(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAudit(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除自动审核编号为"' + ids + '"的数据项？').then(function() {
        return delAudit(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('task/audit/export', {
        ...this.queryParams
      }, `audit_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
