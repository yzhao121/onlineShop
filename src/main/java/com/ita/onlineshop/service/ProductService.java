package com.ita.onlineshop.service;

import com.ita.onlineshop.dao.ProductDao;
import com.ita.onlineshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProductById(int productId) {
        return productDao.getProductById(productId);
    }

    public void deleteProduct(int productId) {
        productDao.deleteProduct(productId);
    }

    public void addProduct(Product product){
        productDao.addProduct(product);
    }

    public void updateProduct(Product product){
        productDao.updateProduct(product);
    }

}
