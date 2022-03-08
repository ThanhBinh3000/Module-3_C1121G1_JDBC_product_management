package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.product.ProductDao;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.product.IProductService;
import com.codegym.service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/categories")
public class CategoryServlet extends HttpServlet {
    private ICategoryService categoryService;
    private IProductService productService;

    public CategoryServlet() {
        this.categoryService = new CategoryService(new CategoryDao());
        this.productService = new ProductService(new ProductDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete": {
                showDeleteForm(request, response);
                break;
            }
            case "view": {
                showListProductByCategoryId(request, response);
                break;
            }
            default: {
                showListCategory(request, response);
                break;
            }
        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        request.setAttribute("category", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void showListProductByCategoryId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        List<Product> products = productService.findAllProductByCategoryId(categoryId);
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/view.jsp");
        dispatcher.forward(request, response);
    }

    private void showListCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete": {
                deleteCategory(request, response);
                break;
            }
        }
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryService.deleteById(id);
        response.sendRedirect("/categories");
    }
}
