package com.revature.ECommerce.beans.services;

import com.revature.ECommerce.beans.repositories.SaleRepository;
import com.revature.ECommerce.entities.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    private SaleRepository saleRepository;
    @Autowired
    public SaleService(SaleRepository sRepo){
        this.saleRepository=sRepo;
    }
    public Sale save(Sale sale){return saleRepository.save(sale);}
    public List<Sale> getAllSales(){ return saleRepository.getAll();}

    public Sale getSaleById(Integer id){ return saleRepository.getById(id);}

    public Sale updateSale(Sale sale){ return saleRepository.update(sale);}

    public List<Sale> getAllSalesWithProductId(Integer id) { return saleRepository.getAllSalesWithProductId(id);}

    public Sale getSaleByOrder(Integer id){ return saleRepository.getSaleByOrderId(id); }
    public void delete(Sale sale){
        saleRepository.delete(sale);
    }
}
