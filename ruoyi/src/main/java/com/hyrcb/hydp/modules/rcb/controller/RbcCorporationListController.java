package com.hyrcb.hydp.modules.rcb.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;
import java.util.Arrays;
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
import com.hyrcb.hydp.modules.rcb.domain.RbcCorporationList;
import com.hyrcb.hydp.modules.rcb.service.IRbcCorporationListService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 企业清单Controller
 *
 * @author shenzulun
 * @date 2020-06-24
 */

@RestController
@RequestMapping("/rbc/corporation" )
public class RbcCorporationListController extends BaseController {

    @Autowired
    private IRbcCorporationListService iRbcCorporationListService;

    /**
     * 查询企业清单列表
     */
    @PreAuthorize("@ss.hasPermi('rbc:corporation:list')")
    @GetMapping("/list")
    public TableDataInfo list(RbcCorporationList rbcCorporationList){
        System.out.println("进入查询222211");
        startPage();
        LambdaQueryWrapper<RbcCorporationList> lqw = new LambdaQueryWrapper<RbcCorporationList>();
        if (StringUtils.isNotBlank(rbcCorporationList.getCorporateName())){
            lqw.like(RbcCorporationList::getCorporateName ,rbcCorporationList.getCorporateName());
        }
        if (StringUtils.isNotBlank(rbcCorporationList.getBusinessLicenseCode())){
            lqw.eq(RbcCorporationList::getBusinessLicenseCode ,rbcCorporationList.getBusinessLicenseCode());
        }
        if (StringUtils.isNotBlank(rbcCorporationList.getIndustry())){
            lqw.eq(RbcCorporationList::getIndustry ,rbcCorporationList.getIndustry());
        }
        if (StringUtils.isNotBlank(rbcCorporationList.getLegalPersonName())){
            lqw.like(RbcCorporationList::getLegalPersonName ,rbcCorporationList.getLegalPersonName());
        }
        if (StringUtils.isNotBlank(rbcCorporationList.getLegalPersonIdcard())){
            lqw.eq(RbcCorporationList::getLegalPersonIdcard ,rbcCorporationList.getLegalPersonIdcard());
        }
        if (StringUtils.isNotBlank(rbcCorporationList.getPhone())){
            lqw.eq(RbcCorporationList::getPhone ,rbcCorporationList.getPhone());
        }
        if (StringUtils.isNotBlank(rbcCorporationList.getLocation())){
            lqw.eq(RbcCorporationList::getLocation ,rbcCorporationList.getLocation());
        }
        if (StringUtils.isNotBlank(rbcCorporationList.getAddress())){
            lqw.eq(RbcCorporationList::getAddress ,rbcCorporationList.getAddress());
        }
        List<RbcCorporationList> list = iRbcCorporationListService.list(lqw);
        return getDataTable(list);
    }

    /**
     * 导出企业清单列表
     */
    @PreAuthorize("@ss.hasPermi('rbc:corporation:export')" )
    @Log(title = "企业清单" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public AjaxResult export(RbcCorporationList rbcCorporationList) {
        LambdaQueryWrapper<RbcCorporationList> lqw = new LambdaQueryWrapper<RbcCorporationList>(rbcCorporationList);
        List<RbcCorporationList> list = iRbcCorporationListService.list(lqw);
        ExcelUtil<RbcCorporationList> util = new ExcelUtil<RbcCorporationList>(RbcCorporationList. class);
        return util.exportExcel(list, "corporation" );
    }

    /**
     * 获取企业清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('rbc:corporation:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iRbcCorporationListService.getById(id));
    }

    /**
     * 新增企业清单
     */
    @PreAuthorize("@ss.hasPermi('rbc:corporation:add')" )
    @Log(title = "企业清单" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RbcCorporationList rbcCorporationList) {
        return toAjax(iRbcCorporationListService.save(rbcCorporationList) ? 1 : 0);
    }

    /**
     * 修改企业清单
     */
    @PreAuthorize("@ss.hasPermi('rbc:corporation:edit')" )
    @Log(title = "企业清单" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RbcCorporationList rbcCorporationList) {
        return toAjax(iRbcCorporationListService.updateById(rbcCorporationList) ? 1 : 0);
    }

    /**
     * 删除企业清单
     */
    @PreAuthorize("@ss.hasPermi('rbc:corporation:remove')" )
    @Log(title = "企业清单" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iRbcCorporationListService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}
