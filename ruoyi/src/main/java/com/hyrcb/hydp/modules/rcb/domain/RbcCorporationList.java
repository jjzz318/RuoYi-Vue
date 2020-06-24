package com.hyrcb.hydp.modules.rcb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import java.util.Date;


/**
 * 企业清单对象 rbc_corporation_list
 * 
 * @author shenzulun
 * @date 2020-06-24
 */

@TableName("rbc_corporation_list")
public class RbcCorporationList implements Serializable {

private static final long serialVersionUID=1L;


    /** 企业id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String corporateName;

    /** 统一社会信用代码 */
    @Excel(name = "统一社会信用代码")
    private String businessLicenseCode;

    /** 所属行业 */
    @Excel(name = "所属行业")
    private String industry;

    /** 法人姓名 */
    @Excel(name = "法人姓名")
    private String legalPersonName;

    /** 法人身份证 */
    @Excel(name = "法人身份证")
    private String legalPersonIdcard;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 坐标 */
    @Excel(name = "坐标")
    private String location;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getCorporateName() {
        return corporateName;
    }
    public void setBusinessLicenseCode(String businessLicenseCode) {
        this.businessLicenseCode = businessLicenseCode;
    }

    public String getBusinessLicenseCode() {
        return businessLicenseCode;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }
    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }
    public void setLegalPersonIdcard(String legalPersonIdcard) {
        this.legalPersonIdcard = legalPersonIdcard;
    }

    public String getLegalPersonIdcard() {
        return legalPersonIdcard;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
}
