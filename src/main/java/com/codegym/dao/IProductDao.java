package com.codegym.dao;

import com.codegym.model.Product;

import java.util.List;

public interface IProductDao extends IGeneralDAO<Product> {
    boolean insertProductUsingProcedure(Product product);

    List<Product> findAllProductByName(String name);
}
