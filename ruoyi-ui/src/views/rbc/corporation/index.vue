<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="企业名称" prop="corporateName">
        <el-input
          v-model="queryParams.corporateName"
          placeholder="请输入企业名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="统一社会信用代码" prop="businessLicenseCode">
        <el-input
          v-model="queryParams.businessLicenseCode"
          placeholder="请输入统一社会信用代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属行业" prop="industry">
        <el-select v-model="queryParams.industry" placeholder="请选择所属行业" clearable size="small">
          <el-option
            v-for="dict in industryOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="法人姓名" prop="legalPersonName">
        <el-input
          v-model="queryParams.legalPersonName"
          placeholder="请输入法人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="法人身份证" prop="legalPersonIdcard">
        <el-input
          v-model="queryParams.legalPersonIdcard"
          placeholder="请输入法人身份证"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入联系电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="坐标" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入坐标"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入地址"
          clearable
          size="small"
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
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['rbc:corporation:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['rbc:corporation:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['rbc:corporation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['rbc:corporation:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="corporationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />

      <el-table-column label="企业名称" align="center" prop="corporateName" />
      <el-table-column label="统一社会信用代码" align="center" prop="businessLicenseCode" />
      <el-table-column label="所属行业" align="center" prop="industry" :formatter="industryFormat" />
      <el-table-column label="法人姓名" align="center" prop="legalPersonName" />
      <el-table-column label="法人身份证" align="center" prop="legalPersonIdcard" />
      <el-table-column label="联系电话" align="center" prop="phone" />
      <el-table-column label="坐标" align="center" prop="location" />
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['rbc:corporation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['rbc:corporation:remove']"
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

    <!-- 添加或修改企业清单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="企业名称" prop="corporateName">
          <el-input v-model="form.corporateName" placeholder="请输入企业名称" />
        </el-form-item>
        <el-form-item label="统一社会信用代码" prop="businessLicenseCode">
          <el-input v-model="form.businessLicenseCode" placeholder="请输入统一社会信用代码" />
        </el-form-item>
        <el-form-item label="所属行业">
          <el-radio-group v-model="form.industry">
            <el-radio
              v-for="dict in industryOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="法人姓名" prop="legalPersonName">
          <el-input v-model="form.legalPersonName" placeholder="请输入法人姓名" />
        </el-form-item>
        <el-form-item label="法人身份证" prop="legalPersonIdcard">
          <el-input v-model="form.legalPersonIdcard" placeholder="请输入法人身份证" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="坐标" prop="location">
          <el-input v-model="form.location" placeholder="请输入坐标" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
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
import { listCorporation, getCorporation, delCorporation, addCorporation, updateCorporation, exportCorporation } from "@/api/rbc/corporation";

export default {
  name: "Corporation",
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
      // 总条数
      total: 0,
      // 企业清单表格数据
      corporationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 所属行业字典
      industryOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        corporateName: undefined,
        businessLicenseCode: undefined,
        industry: undefined,
        legalPersonName: undefined,
        legalPersonIdcard: undefined,
        phone: undefined,
        location: undefined,
        address: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        corporateName: [
          { required: true, message: "企业名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("industry_type").then(response => {
      this.industryOptions = response.data;
    });
  },
  methods: {
    /** 查询企业清单列表 */
    getList() {
      this.loading = true;
      listCorporation(this.queryParams).then(response => {
        this.corporationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 所属行业字典翻译
    industryFormat(row, column) {
      return this.selectDictLabel(this.industryOptions, row.industry);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        corporateName: undefined,
        businessLicenseCode: undefined,
        industry: "0",
        legalPersonName: undefined,
        legalPersonIdcard: undefined,
        phone: undefined,
        location: undefined,
        address: undefined,
        delFlag: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined
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
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加企业清单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCorporation(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改企业清单";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateCorporation(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addCorporation(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除企业清单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCorporation(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有企业清单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCorporation(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>