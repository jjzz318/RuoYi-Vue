package com.hyrcb.hydp.modules.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

import com.hyrcb.hydp.common.utils.RandomUtil;
import com.hyrcb.hydp.common.utils.SMSUtil;
import com.hyrcb.hydp.common.utils.WeChat.WorkWxUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.hyrcb.hydp.modules.crm.domain.ApplyOnline;
import com.hyrcb.hydp.modules.crm.service.IApplyOnlineService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 在线申请清单Controller
 *
 * @author shenzulun
 * @date 2020-08-23
 */

@RestController
@RequestMapping("/crm/ApplyOnline")
public class ApplyOnlineController extends BaseController {

    @Autowired
    private IApplyOnlineService iApplyOnlineService;

    @GetMapping("/sendVerifyCode")
    public AjaxResult sendVerifyCode(String phone) {
        String code = RandomUtil.randomNumber(6);
        SMSUtil.sendSMS(phone, code);
        Record record = new Record();
        record.set("type", "贷款申请");
        record.set("code", code);
        record.set("send_time", new Date());
        record.set("phone", phone);
        record.set("state", 0);
        boolean ret = Db.save("sys_verify_code", record);
        return AjaxResult.success();
    }

    @PostMapping("/applyLoan")
    public AjaxResult applyLoan(@RequestBody ApplyOnline applyOnline) {

        AjaxResult ajaxResult = iApplyOnlineService.verifyCodeExist(applyOnline.getVerifyCode());
        String code = ajaxResult.get("code").toString();
        if ("200".equals(code)) {
            if (iApplyOnlineService.ApplyOnlineExist(applyOnline.getIdcardCode(), applyOnline.getProductId())) {
                ajaxResult = AjaxResult.error("您已经提交过该产品的申请，客户经理会及时与您联系。");
                return ajaxResult;
            }

            if (iApplyOnlineService.save(applyOnline)) {
                WorkWxUtil wxUtil = new WorkWxUtil();
                wxUtil.sendMsg("ChenChong|JinLin", applyOnline.getLiaisonMan(), applyOnline.getId().toString());
                //wxUtil.sendMsg("ChenChong", applyOnline.getLiaisonMan(), applyOnline.getId().toString());
                Record record2=Db.use("oldDb").findFirst("SELECT * from hyrcb_staff where staff_code =?",applyOnline.getStaffCode());

                Record record=new Record();
                record.set("type","个人");
                record.set("idcard_code",applyOnline.getIdcardCode());
                record.set("liaison_man",applyOnline.getLiaisonMan());
                record.set("phone",applyOnline.getPhone());
                record.set("address",applyOnline.getAddress());
                record.set("loan_quota",applyOnline.getMoney());
                record.set("staff_code",applyOnline.getStaffCode());
                record.set("org_code",record2.getStr("org_code"));
                record.set("state","0");
                record.set("loan_purpose","");
                record.set("create_time",new Date());
                record.set("del_flag","I");
                Db.use("oldDb").save("hyrcb_loan_reservation",record);
            } else {
                ajaxResult = AjaxResult.error("保存失败");
            }

        }
        return ajaxResult;
    }

    /**
     * 获取在线申请清单详细信息
     */
    @GetMapping(value = "api/{id}")
    public AjaxResult apiGetInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(iApplyOnlineService.getById(id));
    }

    /**
     * 查询在线申请清单列表
     */
    @PreAuthorize("@ss.hasPermi('crm:ApplyOnline:list')")
    @GetMapping("/list")
    public TableDataInfo list(ApplyOnline applyOnline) {
        startPage();
        LambdaQueryWrapper<ApplyOnline> lqw = new LambdaQueryWrapper<ApplyOnline>();
        if (applyOnline.getProductId() != null) {
            lqw.eq(ApplyOnline::getProductId, applyOnline.getProductId());
        }
        if (StringUtils.isNotBlank(applyOnline.getIdcardCode())) {
            lqw.eq(ApplyOnline::getIdcardCode, applyOnline.getIdcardCode());
        }
        if (StringUtils.isNotBlank(applyOnline.getLiaisonMan())) {
            lqw.eq(ApplyOnline::getLiaisonMan, applyOnline.getLiaisonMan());
        }
        if (StringUtils.isNotBlank(applyOnline.getPhone())) {
            lqw.eq(ApplyOnline::getPhone, applyOnline.getPhone());
        }
        if (StringUtils.isNotBlank(applyOnline.getAddress())) {
            lqw.eq(ApplyOnline::getAddress, applyOnline.getAddress());
        }
        if (StringUtils.isNotBlank(applyOnline.getVerifyCode())) {
            lqw.eq(ApplyOnline::getVerifyCode, applyOnline.getVerifyCode());
        }
        if (StringUtils.isNotBlank(applyOnline.getAreaCode())) {
            lqw.eq(ApplyOnline::getAreaCode, applyOnline.getAreaCode());
        }
        if (StringUtils.isNotBlank(applyOnline.getAreaCode2())) {
            lqw.eq(ApplyOnline::getAreaCode2, applyOnline.getAreaCode2());
        }
        if (StringUtils.isNotBlank(applyOnline.getState())) {
            lqw.eq(ApplyOnline::getState, applyOnline.getState());
        }
        if (StringUtils.isNotBlank(applyOnline.getMoney())) {
            lqw.eq(ApplyOnline::getMoney, applyOnline.getMoney());
        }
        if (StringUtils.isNotBlank(applyOnline.getOrgCode())) {
            lqw.eq(ApplyOnline::getOrgCode, applyOnline.getOrgCode());
        }
        if (StringUtils.isNotBlank(applyOnline.getStaffCode())) {
            lqw.eq(ApplyOnline::getStaffCode, applyOnline.getStaffCode());
        }
        if (StringUtils.isNotBlank(applyOnline.getConfirmStaffCode())) {
            lqw.eq(ApplyOnline::getConfirmStaffCode, applyOnline.getConfirmStaffCode());
        }
        if (applyOnline.getConfirmTime() != null) {
            lqw.eq(ApplyOnline::getConfirmTime, applyOnline.getConfirmTime());
        }
        if (StringUtils.isNotBlank(applyOnline.getHandleStaffCode())) {
            lqw.eq(ApplyOnline::getHandleStaffCode, applyOnline.getHandleStaffCode());
        }
        if (applyOnline.getHandleTime() != null) {
            lqw.eq(ApplyOnline::getHandleTime, applyOnline.getHandleTime());
        }
        if (StringUtils.isNotBlank(applyOnline.getCloseStaffCode())) {
            lqw.eq(ApplyOnline::getCloseStaffCode, applyOnline.getCloseStaffCode());
        }
        if (StringUtils.isNotBlank(applyOnline.getCloseReason())) {
            lqw.eq(ApplyOnline::getCloseReason, applyOnline.getCloseReason());
        }
        if (applyOnline.getCloseTime() != null) {
            lqw.eq(ApplyOnline::getCloseTime, applyOnline.getCloseTime());
        }
        List<ApplyOnline> list = iApplyOnlineService.list(lqw);
        return getDataTable(list);
    }

    /**
     * 导出在线申请清单列表
     */
    @PreAuthorize("@ss.hasPermi('crm:ApplyOnline:export')")
    @Log(title = "在线申请清单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ApplyOnline applyOnline) {
        LambdaQueryWrapper<ApplyOnline> lqw = new LambdaQueryWrapper<ApplyOnline>(applyOnline);
        List<ApplyOnline> list = iApplyOnlineService.list(lqw);
        ExcelUtil<ApplyOnline> util = new ExcelUtil<ApplyOnline>(ApplyOnline.class);
        return util.exportExcel(list, "ApplyOnline");
    }

    /**
     * 获取在线申请清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:ApplyOnline:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(iApplyOnlineService.getById(id));
    }

    /**
     * 新增在线申请清单
     */
    @PreAuthorize("@ss.hasPermi('crm:ApplyOnline:add')")
    @Log(title = "在线申请清单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ApplyOnline applyOnline) {
        return toAjax(iApplyOnlineService.save(applyOnline) ? 1 : 0);
    }

    /**
     * 修改在线申请清单
     */
    @PreAuthorize("@ss.hasPermi('crm:ApplyOnline:edit')")
    @Log(title = "在线申请清单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ApplyOnline applyOnline) {
        return toAjax(iApplyOnlineService.updateById(applyOnline) ? 1 : 0);
    }

    /**
     * 删除在线申请清单
     */
    @PreAuthorize("@ss.hasPermi('crm:ApplyOnline:remove')")
    @Log(title = "在线申请清单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iApplyOnlineService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}
