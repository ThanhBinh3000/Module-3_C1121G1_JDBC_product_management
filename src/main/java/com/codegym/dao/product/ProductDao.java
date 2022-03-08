package com.codegym.dao.product;

import com.codegym.dao.DBConnection;
import com.codegym.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    public static final String SQL_DELETE_PRODUCT = "DELETE from product where  id = ?";
    public static final String SQL_UPDATE_PRODUCT = "UPDATE product SET name = ?, price = ?, description = ?, category_id = ? where  id = ?";
    public static final String SQL_INSERT_PRODUCT = "INSERT INTO product (name, price, description, category_id) values (?, ?, ?, ?)";
    public static final String SQL_SELECT_ONE_PRODUCT = "SELECT * FROM product where id = ?";
    public static final String SQL_SELECT_ALL_PRODUCT = "SELECT * FROM product";
    public static final String SQL_CALL_PROCEDURE_INSERT = "call insert_product(?, ?, ?)";
    public static final String SQL_SELECT_PRODUCT_BY_NAME = "SELECT * from product where name like ?";
    public static final String SQL_SELECT_ALL_PRODUCT_BY_CATEGORY = "SELECT * FROM product where category_id = ?";
    private Connection connection = DBConnection.getConnection();

    public ProductDao() {
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_PRODUCT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");
                Product product = new Product(id, name, price, description);
                product.setCategoryId(categoryId);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        Product product = new Product();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ONE_PRODUCT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");
                product = new Product(id, name, price, description);
                product.setCategoryId(categoryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean create(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getCategoryId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getCategoryId());
            preparedStatement.setInt(5, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insertProductUsingProcedure(Product product) {
        try {
            CallableStatement callableStatement = connection.prepareCall(SQL_CALL_PROCEDURE_INSERT);
            callableStatement.setString(1, product.getName());
            callableStatement.setDouble(2, product.getPrice());
            callableStatement.setString(3, product.getDescription());
            return callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> findAllProductByName(String name) {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_PRODUCT_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameFromSQL = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                int categoryId = rs.getInt("category_id");
                Product product = new Product(id, nameFromSQL, price, description);
                product.setCategoryId(categoryId);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findAllProductByCategoryId(int categoryId) {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_PRODUCT_BY_CATEGORY);
            preparedStatement.setInt(1, categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameFromSQL = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                Product product = new Product(id, nameFromSQL, price, description);
                product.setCategoryId(categoryId);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
