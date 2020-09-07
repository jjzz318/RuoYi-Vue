package com.hyrcb.hydp.modules.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;
import java.util.Arrays;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableSupport;
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
import com.hyrcb.hydp.modules.crm.domain.Product;
import com.hyrcb.hydp.modules.crm.service.IProductService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 产品管理Controller
 *
 * @author cc
 * @date 2020-08-17
 */

@RestController
@RequestMapping("/crm/product" )
public class ProductController extends BaseController {

    @Autowired
    private IProductService iProductService;
    @GetMapping("/api/getList")
    public TableDataInfo apiGetList(Product product){
        startPage();
        PageDomain pageDomain = TableSupport.buildPageRequest();
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<Product>();
        if (StringUtils.isNotBlank(product.getType())){
            lqw.eq(Product::getType ,product.getType());
        }
        List<Product> list = iProductService.list(lqw);
        return getDataTable(list);
    }

    /**
     * 获取产品管理详细信息
     */
    @GetMapping(value = "/api/{id}" )
    public AjaxResult apiGetInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iProductService.getById(id));
    }

    /**
     * 查询产品管理列表
     */
    @PreAuthorize("@ss.hasPermi('crm:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(Product product){
        startPage();
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<Product>();
        if (StringUtils.isNotBlank(product.getType())){
            lqw.eq(Product::getType ,product.getType());
        }
        if (StringUtils.isNotBlank(product.getName())){
            lqw.like(Product::getName ,product.getName());
        }
        if (StringUtils.isNotBlank(product.getMoney())){
            lqw.eq(Product::getMoney ,product.getMoney());
        }
        if (StringUtils.isNotBlank(product.getInterestRate())){
            lqw.eq(Product::getInterestRate ,product.getInterestRate());
        }
        if (StringUtils.isNotBlank(product.getContent())){
            lqw.eq(Product::getContent ,product.getContent());
        }
        List<Product> list = iProductService.list(lqw);
        return getDataTable(list);
    }

    /**
     * 导出产品管理列表
     */
    @PreAuthorize("@ss.hasPermi('crm:product:export')" )
    @Log(title = "产品管理" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public AjaxResult export(Product product) {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<Product>(product);
        List<Product> list = iProductService.list(lqw);
        ExcelUtil<Product> util = new ExcelUtil<Product>(Product. class);
        return util.exportExcel(list, "product" );
    }

    /**
     * 获取产品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:product:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iProductService.getById(id));
    }

    /**
     * 新增产品管理
     */
    @PreAuthorize("@ss.hasPermi('crm:product:add')" )
    @Log(title = "产品管理" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Product product) {
        return toAjax(iProductService.save(product) ? 1 : 0);
    }

    /**
     * 修改产品管理
     */
    @PreAuthorize("@ss.hasPermi('crm:product:edit')" )
    @Log(title = "产品管理" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Product product) {
        return toAjax(iProductService.updateById(product) ? 1 : 0);
    }

    /**
     * 删除产品管理
     */
    @PreAuthorize("@ss.hasPermi('crm:product:remove')" )
    @Log(title = "产品管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iProductService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}
