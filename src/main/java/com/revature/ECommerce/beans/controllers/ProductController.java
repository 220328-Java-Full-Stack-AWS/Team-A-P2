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
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //TODO: Test with postman
    //Gets all productts by catagory
    @GetMapping("category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllByCategory(@PathVariable String category){
        return productService.getAllByCategory(category);
    }

    //TODO: Test with postman
    //Gets item by id
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }


    //Posts(adds) new product into the database
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public Product persistNewProduct(@RequestBody Product newProduct){
        return productService.saveProduct(newProduct);
    }


    //Updates product
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    //Deletes product by id
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

}
