package com.codegym.service.product;

import com.codegym.dao.product.IProductDao;
import com.codegym.model.Product;

import java.util.List;

public class ProductService implements IProductService {
    private IProductDao productDao;

    public ProductService(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public boolean create(Product product) {
        return productDao.create(product);
    }

    @Override
    public boolean updateById(int id, Product product) {
        return productDao.updateById(id, product);
    }

    @Override
    public boolean deleteById(int id) {
        return productDao.deleteById(id);
    }

    @Override
    public boolean insertProductUsingProcedure(Product product) {
        return productDao.insertProductUsingProcedure(product);
    }

    @Override
    public List<Product> findAllProductByName(String name) {
        name = "%" + name + "%";
        return productDao.findAllProductByName(name);
    }

    @Override
    public List<Product> findAllProductByCategoryId(int categoryId) {
        return productDao.findAllProductByCategoryId(categoryId);
    }
}
