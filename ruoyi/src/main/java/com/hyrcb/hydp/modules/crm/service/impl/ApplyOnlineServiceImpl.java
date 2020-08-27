package com.hyrcb.hydp.modules.crm.service.impl;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyrcb.hydp.modules.crm.mapper.ApplyOnlineMapper;
import com.hyrcb.hydp.modules.crm.domain.ApplyOnline;
import com.hyrcb.hydp.modules.crm.service.IApplyOnlineService;

import java.util.Date;

/**
 * 在线申请清单Service业务层处理
 *
 * @author shenzulun
 * @date 2020-08-23
 */
@Service
public class ApplyOnlineServiceImpl extends ServiceImpl<ApplyOnlineMapper, ApplyOnline> implements IApplyOnlineService {
    /**
     * 判断验证码是否存在，并进行销号
     *
     * @param code
     * @return
     */
    public AjaxResult verifyCodeExist(String code) {
        String sql = "SELECT * from sys_verify_code WHERE code =? ";
        Record record = Db.findFirst(sql, code);
        if (record == null) {
            return AjaxResult.error("验证码不存在");
        }
        if (record.getInt("state") == 1) {
            return AjaxResult.error("验证码已经使用");
        }
        record.set("state", 1);
        record.set("use_time", new Date());
        Db.update("sys_verify_code", record);
        return AjaxResult.success();
    }

    /**
     * 判断对同一个身份证是否对同一个产品有重复申请
     * @param idcardCode
     * @param productId
     * @return
     */
    public boolean ApplyOnlineExist(String idcardCode, long productId) {
        String sql = "SELECT * from crm_apply_online WHERE idcard_code=? and product_id =? ";
        Record record = Db.findFirst(sql, idcardCode, productId);
        if(record!=null){
            return true;
        }else{
            return false;
        }
    }
}
