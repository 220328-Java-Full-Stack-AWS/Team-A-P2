package com.revature.ECommerce.beans.controllers;

import com.revature.ECommerce.beans.services.ProductService;
import com.revature.ECommerce.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    //Gets all products
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //Gets by id or product name
    @GetMapping("/{productNameOrId}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable String productNameOrId, @RequestHeader("mode") String mode) throws Exception {
        switch(mode){
            case "productName":
                return productService.getProductByName(productNameOrId);
            case "id":
                return productService.getProductById(Integer.parseInt(productNameOrId));
            default:
                throw new Exception("Invalid product");
        }
    }

    //Posts(adds) new product into the database
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public Product persistNewProduct(@RequestBody Product newProduct){
        return productService.saveProduct(newProduct);
    }

}
