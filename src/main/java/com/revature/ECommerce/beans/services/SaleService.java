package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.SaleRepository;
import com.revature.ECommerce.entities.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
    private SaleRepository sRepo;
    @Autowired
    public SaleService(SaleRepository sRepo){
        this.sRepo=sRepo;
    }
    public void delete(Sale sale){
        sRepo.delete(sale);
    }
}
