package com.hyrcb.hydp.modules.crm.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyrcb.hydp.modules.crm.mapper.NewsMapper;
import com.hyrcb.hydp.modules.crm.domain.News;
import com.hyrcb.hydp.modules.crm.service.INewsService;

/**
 * 文章列Service业务层处理
 *
 * @author cc
 * @date 2020-08-30
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

}
