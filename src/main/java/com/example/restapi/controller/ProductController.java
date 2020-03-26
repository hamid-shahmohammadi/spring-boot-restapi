package com.example.restapi.controller;

import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products/")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "{id}",method = RequestMethod.GET)
    public Product getProduct(@PathVariable(name = "id") String id){
        return productRepository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody Product productToSave){
        return productRepository.save(productToSave);
    }

    @RequestMapping(path = "{id}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product productUpdate,@PathVariable(name = "id") String id){
        Product foundProduct= productRepository.findById(id).orElse(null);
        if(foundProduct!=null){
            foundProduct.setName(productUpdate.getName());
            return productRepository.save(foundProduct);
        }else{
            return productUpdate;
        }

    }

    @RequestMapping(path = "{id}",method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(name = "id") String id){
        Product foundProduct = productRepository.findById(id).orElse(null);
        if (foundProduct!=null){
            productRepository.delete(foundProduct);
        }
    }

}
