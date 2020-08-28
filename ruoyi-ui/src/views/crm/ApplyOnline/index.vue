<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">

      <el-form-item label="身份证" prop="idcardCode">
        <el-input
          v-model="queryParams.idcardCode"
          placeholder="请输入身份证号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约人" prop="liaisonMan">
        <el-input
          v-model="queryParams.liaisonMan"
          placeholder="请输入预约人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="state">
        <el-select v-model="queryParams.state"  clearable size="small">
          <el-option
            v-for="dict in stateOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="归属机构" prop="orgCode">
        <el-input
          v-model="queryParams.orgCode"
          placeholder="请输入归属机构"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户经理" prop="staffCode">
        <el-input
          v-model="queryParams.staffCode"
          placeholder="请输入归属客户经理"
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
          v-hasPermi="['crm:ApplyOnline:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['crm:ApplyOnline:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['crm:ApplyOnline:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['crm:ApplyOnline:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="ApplyOnlineList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="产品id" align="center" prop="productId" />
      <el-table-column label="身份证号码" align="center" prop="idcardCode" />
      <el-table-column label="预约人" align="center" prop="liaisonMan" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="详细地址" align="center" prop="address" />
      <el-table-column label="手机验证码" align="center" prop="verifyCode" />
      <el-table-column label="地区编号1" align="center" prop="areaCode" />
      <el-table-column label="地区编号2" align="center" prop="areaCode2" />
      <el-table-column label="状态" align="center" prop="state" :formatter="stateFormat" />
      <el-table-column label="期望贷款额度" align="center" prop="money" />
      <el-table-column label="归属机构" align="center" prop="orgCode" />
      <el-table-column label="归属客户经理" align="center" prop="staffCode" />
      <el-table-column label="确认人" align="center" prop="confirmStaffCode" />
      <el-table-column label="确认时间" align="center" prop="confirmTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.confirmTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="办理人" align="center" prop="handleStaffCode" />
      <el-table-column label="办理时间" align="center" prop="handleTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.handleTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="关闭人" align="center" prop="closeStaffCode" />
      <el-table-column label="关闭理由" align="center" prop="closeReason" />
      <el-table-column label="关闭时间" align="center" prop="closeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.closeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['crm:ApplyOnline:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['crm:ApplyOnline:remove']"
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

    <!-- 添加或修改在线申请清单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品id" prop="productId">
          <el-input v-model="form.productId" placeholder="请输入产品id" />
        </el-form-item>
        <el-form-item label="身份证号码" prop="idcardCode">
          <el-input v-model="form.idcardCode" placeholder="请输入身份证号码" />
        </el-form-item>
        <el-form-item label="预约人" prop="liaisonMan">
          <el-input v-model="form.liaisonMan" placeholder="请输入预约人" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="手机验证码" prop="verifyCode">
          <el-input v-model="form.verifyCode" placeholder="请输入手机验证码" />
        </el-form-item>
        <el-form-item label="地区编号1" prop="areaCode">
          <el-input v-model="form.areaCode" placeholder="请输入地区编号1" />
        </el-form-item>
        <el-form-item label="地区编号2" prop="areaCode2">
          <el-input v-model="form.areaCode2" placeholder="请输入地区编号2" />
        </el-form-item>
        <el-form-item label="状态，0处理，1已受理，2完成办理,3关闭">
          <el-select v-model="form.state" placeholder="请选择状态，0处理，1已受理，2完成办理,3关闭">
            <el-option
              v-for="dict in stateOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="期望贷款额度" prop="money">
          <el-input v-model="form.money" placeholder="请输入期望贷款额度" />
        </el-form-item>
        <el-form-item label="归属机构" prop="orgCode">
          <el-input v-model="form.orgCode" placeholder="请输入归属机构" />
        </el-form-item>
        <el-form-item label="归属客户经理" prop="staffCode">
          <el-input v-model="form.staffCode" placeholder="请输入归属客户经理" />
        </el-form-item>
        <el-form-item label="确认人" prop="confirmStaffCode">
          <el-input v-model="form.confirmStaffCode" placeholder="请输入确认人" />
        </el-form-item>
        <el-form-item label="确认时间" prop="confirmTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.confirmTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择确认时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="办理人" prop="handleStaffCode">
          <el-input v-model="form.handleStaffCode" placeholder="请输入办理人" />
        </el-form-item>
        <el-form-item label="办理时间" prop="handleTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.handleTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择办理时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="关闭人" prop="closeStaffCode">
          <el-input v-model="form.closeStaffCode" placeholder="请输入关闭人" />
        </el-form-item>
        <el-form-item label="关闭理由" prop="closeReason">
          <el-input v-model="form.closeReason" placeholder="请输入关闭理由" />
        </el-form-item>
        <el-form-item label="关闭时间" prop="closeTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.closeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择关闭时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="删除标志位" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志位" />
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
import { listApplyOnline, getApplyOnline, delApplyOnline, addApplyOnline, updateApplyOnline, exportApplyOnline } from "@/api/crm/ApplyOnline";

export default {
  name: "ApplyOnline",
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
      // 在线申请清单表格数据
      ApplyOnlineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态，0处理，1已受理，2完成办理,3关闭字典
      stateOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productId: undefined,
        idcardCode: undefined,
        liaisonMan: undefined,
        phone: undefined,
        address: undefined,
        verifyCode: undefined,
        areaCode: undefined,
        areaCode2: undefined,
        state: undefined,
        money: undefined,
        orgCode: undefined,
        staffCode: undefined,
        confirmStaffCode: undefined,
        confirmTime: undefined,
        handleStaffCode: undefined,
        handleTime: undefined,
        closeStaffCode: undefined,
        closeReason: undefined,
        closeTime: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        createTime: [
          { required: true, message: "记录创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("apply_online_sate").then(response => {
      this.stateOptions = response.data;
    });
  },
  methods: {
    /** 查询在线申请清单列表 */
    getList() {
      this.loading = true;
      listApplyOnline(this.queryParams).then(response => {
        this.ApplyOnlineList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态，0处理，1已受理，2完成办理,3关闭字典翻译
    stateFormat(row, column) {
      return this.selectDictLabel(this.stateOptions, row.state);
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
        productId: undefined,
        idcardCode: undefined,
        liaisonMan: undefined,
        phone: undefined,
        address: undefined,
        verifyCode: undefined,
        areaCode: undefined,
        areaCode2: undefined,
        state: undefined,
        money: undefined,
        createTime: undefined,
        orgCode: undefined,
        staffCode: undefined,
        confirmStaffCode: undefined,
        confirmTime: undefined,
        handleStaffCode: undefined,
        handleTime: undefined,
        closeStaffCode: undefined,
        closeReason: undefined,
        closeTime: undefined,
        delFlag: undefined
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
      this.title = "添加在线申请清单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApplyOnline(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改在线申请清单";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateApplyOnline(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addApplyOnline(this.form).then(response => {
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
      this.$confirm('是否确认删除在线申请清单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delApplyOnline(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有在线申请清单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportApplyOnline(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>