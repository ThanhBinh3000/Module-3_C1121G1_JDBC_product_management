package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    boolean insertProductUsingProcedure(Product product);
    List<Product> findAllProductByName(String name);
}
