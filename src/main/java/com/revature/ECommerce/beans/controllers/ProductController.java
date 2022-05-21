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


    //Gets all products by category
    @GetMapping("/category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getByCategory(@PathVariable String category){
        return productService.getByCategory(category);
    }

    //Gets all products by status
    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getByStatus(@PathVariable String status){
        return productService.getByStatus(status);
    }


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
    @DeleteMapping("/delete/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@RequestBody Product product){
        productService.deleteProduct(product);
    }

    //Gets all products
    @GetMapping("/sort/price/asc")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> sortPriceAsc(){
        return productService.sortPriceAsc();
    }

    //Gets all products
    @GetMapping("/sort/price/desc")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> sortPriceDesc(){
        return productService.sortPriceDesc();
    }

    //Gets all products
    @GetMapping("/sort/product_name/asc")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> sortProductNameAsc(){
        return productService.sortProductNameAsc();
    }

    //Gets all products
    @GetMapping("/sort/product_name/desc")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> sortProductNameDesc(){
        return productService.sortProductNameDesc();
    }

}
