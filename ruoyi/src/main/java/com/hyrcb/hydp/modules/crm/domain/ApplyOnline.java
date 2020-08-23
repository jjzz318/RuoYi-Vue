package com.hyrcb.hydp.modules.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import java.util.Date;


/**
 * 在线申请清单对象 crm_apply_online
 * 
 * @author shenzulun
 * @date 2020-08-23
 */

@TableName("crm_apply_online")
public class ApplyOnline implements Serializable {

private static final long serialVersionUID=1L;


    /** 主键 */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 产品id */
    @Excel(name = "产品id")
    private Long productId;

    /** 身份证号码 */
    @Excel(name = "身份证号码")
    private String idcardCode;

    /** 预约人 */
    @Excel(name = "预约人")
    private String liaisonMan;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 手机验证码 */
    @Excel(name = "手机验证码")
    private String verifyCode;

    /** 地区编号1 */
    @Excel(name = "地区编号1")
    private String areaCode;

    /** 地区编号2 */
    @Excel(name = "地区编号2")
    private String areaCode2;

    /** 状态，0处理，1已受理，2完成办理,3关闭 */
    @Excel(name = "状态，0处理，1已受理，2完成办理,3关闭")
    private String state;

    /** 期望贷款额度 */
    @Excel(name = "期望贷款额度")
    private String money;

    /** 记录创建时间 */
    private Date createTime;

    /** 归属机构 */
    @Excel(name = "归属机构")
    private String orgCode;

    /** 归属客户经理 */
    @Excel(name = "归属客户经理")
    private String staffCode;

    /** 确认人 */
    @Excel(name = "确认人")
    private String confirmStaffCode;

    /** 确认时间 */
    @Excel(name = "确认时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date confirmTime;

    /** 办理人 */
    @Excel(name = "办理人")
    private String handleStaffCode;

    /** 办理时间 */
    @Excel(name = "办理时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date handleTime;

    /** 关闭人 */
    @Excel(name = "关闭人")
    private String closeStaffCode;

    /** 关闭理由 */
    @Excel(name = "关闭理由")
    private String closeReason;

    /** 关闭时间 */
    @Excel(name = "关闭时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeTime;

    /** 删除标志位 */
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setIdcardCode(String idcardCode) {
        this.idcardCode = idcardCode;
    }

    public String getIdcardCode() {
        return idcardCode;
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
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyCode() {
        return verifyCode;
    }
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }
    public void setAreaCode2(String areaCode2) {
        this.areaCode2 = areaCode2;
    }

    public String getAreaCode2() {
        return areaCode2;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney() {
        return money;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgCode() {
        return orgCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffCode() {
        return staffCode;
    }
    public void setConfirmStaffCode(String confirmStaffCode) {
        this.confirmStaffCode = confirmStaffCode;
    }

    public String getConfirmStaffCode() {
        return confirmStaffCode;
    }
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }
    public void setHandleStaffCode(String handleStaffCode) {
        this.handleStaffCode = handleStaffCode;
    }

    public String getHandleStaffCode() {
        return handleStaffCode;
    }
    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }
    public void setCloseStaffCode(String closeStaffCode) {
        this.closeStaffCode = closeStaffCode;
    }

    public String getCloseStaffCode() {
        return closeStaffCode;
    }
    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }

    public String getCloseReason() {
        return closeReason;
    }
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }
}
