package com.codegym.service.category;

import com.codegym.dao.category.ICategoryDao;
import com.codegym.model.Category;

import java.util.List;

public class CategoryService implements ICategoryService {
    private ICategoryDao categoryDao;

    public CategoryService(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public boolean create(Category category) {
        return false;
    }

    @Override
    public boolean updateById(int id, Category category) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return categoryDao.deleteCategoryUsingProcedure(id);
    }
}
