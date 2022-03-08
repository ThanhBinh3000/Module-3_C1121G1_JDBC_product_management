package com.codegym.service.product;

import com.codegym.model.Product;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    boolean insertProductUsingProcedure(Product product);
    List<Product> findAllProductByName(String name);
}
