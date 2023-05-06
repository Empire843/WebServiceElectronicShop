package com.ptit.webserviceelectronicshop.service.implement;

import com.ptit.webserviceelectronicshop.model.Category;
import com.ptit.webserviceelectronicshop.model.Product;
import com.ptit.webserviceelectronicshop.repository.ProductRepository;
import com.ptit.webserviceelectronicshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;
    @Override
    public void addProduct(Product product) {
        repo.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return repo.findAll();
    }

    @Override
    public boolean deleteProduct(Long id) {
        Optional<Product> product = repo.findById(id);
        if(product.isPresent()){
            repo.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Product> getProductByCategory(Category category) {
        return repo.findByCategory(category);
    }

    @Override
    public ArrayList<Product> getProductsContainKeys(String key) {
        ArrayList<Product> listProducts = new ArrayList<Product>();
        listProducts = this.repo.findByKey(key);
        return listProducts;
    }

    @Override
    public ArrayList<Product> getProductsByCategory(Long id) {
        ArrayList<Product> products = new ArrayList<>();
        products = this.repo.getProductsByCategory(id);
        return  products;
    }

}
