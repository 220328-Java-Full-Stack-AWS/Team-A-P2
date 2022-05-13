package com.revature.ECommerce.beans.controllers;

import com.revature.ECommerce.beans.services.SaleService;
import com.revature.ECommerce.entities.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    public final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public Sale persistNewSale(@RequestBody Sale newSale){
        return saleService.save(newSale);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Sale updateSale(@RequestBody Sale sale){
        return saleService.updateSale(sale);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSale(@RequestBody Sale sale){
        saleService.deleteSale(sale);
    }

}
