package com.hyrcb.hydp.modules.pbc.controller;

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
import com.hyrcb.hydp.modules.pbc.domain.PbcCorporationList;
import com.hyrcb.hydp.modules.pbc.service.IPbcCorporationListService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 企业清单Controller
 *
 * @author shenzulun
 * @date 2020-07-13
 */

@RestController
@RequestMapping("/pbc/corporation" )
public class PbcCorporationListController extends BaseController {

    @Autowired
    private IPbcCorporationListService iPbcCorporationListService;

    /**
     * 查询企业清单列表
     */
    @PreAuthorize("@ss.hasPermi('pbc:corporation:list')")
    @GetMapping("/list")
    public TableDataInfo list(PbcCorporationList pbcCorporationList){
        startPage();
        LambdaQueryWrapper<PbcCorporationList> lqw = new LambdaQueryWrapper<PbcCorporationList>();
        if (StringUtils.isNotBlank(pbcCorporationList.getCorporateName())){
            lqw.like(PbcCorporationList::getCorporateName ,pbcCorporationList.getCorporateName());
        }
        if (StringUtils.isNotBlank(pbcCorporationList.getBusinessLicenseCode())){
            lqw.eq(PbcCorporationList::getBusinessLicenseCode ,pbcCorporationList.getBusinessLicenseCode());
        }
        if (StringUtils.isNotBlank(pbcCorporationList.getIndustry())){
            lqw.eq(PbcCorporationList::getIndustry ,pbcCorporationList.getIndustry());
        }
        if (StringUtils.isNotBlank(pbcCorporationList.getLiaisonMan())){
            lqw.eq(PbcCorporationList::getLiaisonMan ,pbcCorporationList.getLiaisonMan());
        }
        if (StringUtils.isNotBlank(pbcCorporationList.getPhone())){
            lqw.eq(PbcCorporationList::getPhone ,pbcCorporationList.getPhone());
        }
        if (StringUtils.isNotBlank(pbcCorporationList.getLocation())){
            lqw.eq(PbcCorporationList::getLocation ,pbcCorporationList.getLocation());
        }
        if (StringUtils.isNotBlank(pbcCorporationList.getAddress())){
            lqw.eq(PbcCorporationList::getAddress ,pbcCorporationList.getAddress());
        }
        if (StringUtils.isNotBlank(pbcCorporationList.getCharacteristic())){
            lqw.eq(PbcCorporationList::getCharacteristic ,pbcCorporationList.getCharacteristic());
        }
        if (StringUtils.isNotBlank(pbcCorporationList.getScale())){
            lqw.eq(PbcCorporationList::getScale ,pbcCorporationList.getScale());
        }
        if (pbcCorporationList.getState() != null){
            lqw.eq(PbcCorporationList::getState ,pbcCorporationList.getState());
        }
        if (StringUtils.isNotBlank(pbcCorporationList.getRemarks())){
            lqw.eq(PbcCorporationList::getRemarks ,pbcCorporationList.getRemarks());
        }
        List<PbcCorporationList> list = iPbcCorporationListService.list(lqw);
        return getDataTable(list);
    }

    /**
     * 导出企业清单列表
     */
    @PreAuthorize("@ss.hasPermi('pbc:corporation:export')" )
    @Log(title = "企业清单" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public AjaxResult export(PbcCorporationList pbcCorporationList) {
        LambdaQueryWrapper<PbcCorporationList> lqw = new LambdaQueryWrapper<PbcCorporationList>(pbcCorporationList);
        List<PbcCorporationList> list = iPbcCorporationListService.list(lqw);
        ExcelUtil<PbcCorporationList> util = new ExcelUtil<PbcCorporationList>(PbcCorporationList. class);
        return util.exportExcel(list, "corporation" );
    }

    /**
     * 获取企业清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('pbc:corporation:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iPbcCorporationListService.getById(id));
    }

    /**
     * 新增企业清单
     */
    @PreAuthorize("@ss.hasPermi('pbc:corporation:add')" )
    @Log(title = "企业清单" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PbcCorporationList pbcCorporationList) {
        return toAjax(iPbcCorporationListService.save(pbcCorporationList) ? 1 : 0);
    }

    /**
     * 修改企业清单
     */
    @PreAuthorize("@ss.hasPermi('pbc:corporation:edit')" )
    @Log(title = "企业清单" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PbcCorporationList pbcCorporationList) {
        return toAjax(iPbcCorporationListService.updateById(pbcCorporationList) ? 1 : 0);
    }

    /**
     * 删除企业清单
     */
    @PreAuthorize("@ss.hasPermi('pbc:corporation:remove')" )
    @Log(title = "企业清单" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iPbcCorporationListService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}
