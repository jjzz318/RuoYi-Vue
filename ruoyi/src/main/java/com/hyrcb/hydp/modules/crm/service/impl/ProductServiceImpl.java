package com.hyrcb.hydp.modules.crm.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyrcb.hydp.modules.crm.mapper.ProductMapper;
import com.hyrcb.hydp.modules.crm.domain.Product;
import com.hyrcb.hydp.modules.crm.service.IProductService;

/**
 * 产品管理Service业务层处理
 *
 * @author cc
 * @date 2020-08-17
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
