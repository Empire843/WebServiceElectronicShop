package com.ptit.webserviceelectronicshop.controller;

import com.ptit.webserviceelectronicshop.model.Product;
import com.ptit.webserviceelectronicshop.model.User;
import com.ptit.webserviceelectronicshop.model.request_body.ProductDTO;
import com.ptit.webserviceelectronicshop.service.implement.ProductServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl service;

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return service.getAllProduct();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addNewProduct(@NotNull @RequestBody @Valid ProductDTO body, BindingResult bindingResult) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        if (bindingResult.hasErrors()) {
            for (FieldError error1 : bindingResult.getFieldErrors()) {
                error.put(error1.getField(), error1.getDefaultMessage());
            }
            return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
        }
        ModelMapper mapper = new ModelMapper();
        Product user = mapper.map(body, Product.class);

        Product product = new Product();
        product.setCreated_date(LocalDateTime.now());
        //code here


        response.put("message", "Product has been added successfully");
        response.put("product", product);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (service.deleteProduct(id)) {
            return new ResponseEntity<>("Product has been deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> editProduct(@PathVariable Long id, @RequestBody ProductDTO body) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();
        Optional<Product> product = service.getProductById(id);

        if (product.isPresent()) {
            Product existingProduct = product.get();
//            code here

            response.put("message", "Product has been updated successfully");
            response.put("product", existingProduct);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            error.put("error", "Product not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}
