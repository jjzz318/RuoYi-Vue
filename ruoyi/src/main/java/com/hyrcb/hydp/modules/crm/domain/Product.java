package com.hyrcb.hydp.modules.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import java.util.Date;


/**
 * 产品管理对象 crm_product
 * 
 * @author cc
 * @date 2020-08-17
 */

@TableName("crm_product")
public class Product implements Serializable {

private static final long serialVersionUID=1L;


    /** 主键 */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String type;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String name;

    /** 额度 */
    @Excel(name = "额度")
    private String money;

    /** 利率 */
    @Excel(name = "利率")
    private String interestRate;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney() {
        return money;
    }
    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getInterestRate() {
        return interestRate;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
