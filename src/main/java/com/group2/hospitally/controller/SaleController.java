package com.group2.hospitally.controller;

import com.group2.hospitally.model.entity.Sale;
import com.group2.hospitally.model.request.Sale.CreateSaleRequest;
import com.group2.hospitally.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<Sale> getSaleById(@PathVariable int saleId) {
        Sale sale = saleService.getSaleById(saleId);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @GetMapping("/medication/{medicationId}/sales")
    public ResponseEntity<Sale> getSaleByMedicationId(@PathVariable int medicationId) {
        Sale sale = saleService.getSaleByMedicationId(medicationId);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}/sales")
    public ResponseEntity<Sale> getSaleByPatientId(@PathVariable int patientId) {
        Sale sale = saleService.getSaleByPatientId(patientId);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Sale> createSale(@RequestBody CreateSaleRequest request) {
        Sale sale = saleService.createSale(request);
        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    @PutMapping("/update/{saleId}")
    public ResponseEntity<Sale> updateSale(@PathVariable int saleId, @RequestBody CreateSaleRequest request) {
        Sale updatedSale = saleService.updateSale(saleId, request);
        return new ResponseEntity<>(updatedSale, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{saleId}")
    public ResponseEntity<String> deleteSale(@PathVariable int saleId) {
        saleService.deleteSaleById(saleId);
        return new ResponseEntity<>("Sale deleted successfully", HttpStatus.OK);
    }
}