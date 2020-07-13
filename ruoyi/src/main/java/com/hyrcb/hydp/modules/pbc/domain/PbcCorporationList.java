package com.hyrcb.hydp.modules.pbc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import java.util.Date;


/**
 * 企业清单对象 pbc_corporation_list
 * 
 * @author shenzulun
 * @date 2020-07-13
 */

@TableName("pbc_corporation_list")
public class PbcCorporationList implements Serializable {

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

    /** 联系人法人姓名 */
    @Excel(name = "联系人法人姓名")
    private String liaisonMan;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 坐标 */
    @Excel(name = "坐标")
    private String location;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 标识 */
    @Excel(name = "标识")
    private String characteristic;

    /** 企业规模 */
    @Excel(name = "企业规模")
    private String scale;

    /** 0未解决，1已解决，2需要协助解决，3不予解决 */
    @Excel(name = "0未解决，1已解决，2需要协助解决，3不予解决")
    private Long state;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

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
    public void setLiaisonMan(String liaisonMan) {
        this.liaisonMan = liaisonMan;
    }

    public String getLiaisonMan() {
        return liaisonMan;
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
    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getCharacteristic() {
        return characteristic;
    }
    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getScale() {
        return scale;
    }
    public void setState(Long state) {
        this.state = state;
    }

    public Long getState() {
        return state;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
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
