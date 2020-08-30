package com.hyrcb.hydp.modules.crm.controller;

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
import com.hyrcb.hydp.modules.crm.domain.News;
import com.hyrcb.hydp.modules.crm.service.INewsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 文章列Controller
 *
 * @author cc
 * @date 2020-08-30
 */

@RestController
@RequestMapping("/crm/news" )
public class NewsController extends BaseController {

    @Autowired
    private INewsService iNewsService;

    /**
     * 查询文章列列表
     */
    @PreAuthorize("@ss.hasPermi('crm:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(News news){
        startPage();
        LambdaQueryWrapper<News> lqw = new LambdaQueryWrapper<News>();
        if (news.getDeptId() != null){
            lqw.eq(News::getDeptId ,news.getDeptId());
        }
        if (news.getDictTypeId() != null){
            lqw.eq(News::getDictTypeId ,news.getDictTypeId());
        }
        if (StringUtils.isNotBlank(news.getTitle())){
            lqw.eq(News::getTitle ,news.getTitle());
        }
        if (StringUtils.isNotBlank(news.getContent())){
            lqw.eq(News::getContent ,news.getContent());
        }
        if (news.getState() != null){
            lqw.eq(News::getState ,news.getState());
        }
        List<News> list = iNewsService.list(lqw);
        return getDataTable(list);
    }

    /**
     * 导出文章列列表
     */
    @PreAuthorize("@ss.hasPermi('crm:news:export')" )
    @Log(title = "文章列" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public AjaxResult export(News news) {
        LambdaQueryWrapper<News> lqw = new LambdaQueryWrapper<News>(news);
        List<News> list = iNewsService.list(lqw);
        ExcelUtil<News> util = new ExcelUtil<News>(News. class);
        return util.exportExcel(list, "news" );
    }

    /**
     * 获取文章列详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:news:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iNewsService.getById(id));
    }

    /**
     * 新增文章列
     */
    @PreAuthorize("@ss.hasPermi('crm:news:add')" )
    @Log(title = "文章列" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody News news) {
        return toAjax(iNewsService.save(news) ? 1 : 0);
    }

    /**
     * 修改文章列
     */
    @PreAuthorize("@ss.hasPermi('crm:news:edit')" )
    @Log(title = "文章列" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody News news) {
        return toAjax(iNewsService.updateById(news) ? 1 : 0);
    }

    /**
     * 删除文章列
     */
    @PreAuthorize("@ss.hasPermi('crm:news:remove')" )
    @Log(title = "文章列" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iNewsService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}
