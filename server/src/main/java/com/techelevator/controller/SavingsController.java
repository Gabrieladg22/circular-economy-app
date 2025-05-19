package com.techelevator.controller;

import com.techelevator.dao.CarbonSavingsDao;
import com.techelevator.model.Savings;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RestController
public class SavingsController {

    private CarbonSavingsDao carbonSavingsDao;

    public SavingsController(CarbonSavingsDao carbonSavingsDao) {
        this.carbonSavingsDao = carbonSavingsDao;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/api/savings/{savingsId}")
    public Savings getSavings(@PathVariable int savingsId) {

        Savings savings = carbonSavingsDao.getSavingsById(savingsId);

        if (savings == null) {
            // 404 Not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Saving not found");
        }
        return savings;
    }

    @PreAuthorize("permitAll")
    @GetMapping(path = "/api/savings")
    public List<Savings> listOfSavings(@RequestParam(required = false, defaultValue = "") String businessName,
                                      @RequestParam(required = false, defaultValue = "") String materialName){

        if(!businessName.equals("")) {
            return carbonSavingsDao.getSavingsByBusiness(businessName);
        }
        if(!materialName.equals("")){
            return carbonSavingsDao.getSavingsByMaterial(materialName);
        }
        if(businessName.equals("") && materialName.equals("")){
            return carbonSavingsDao.getAllSavings();
        }

        return null;
    }

//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    @GetMapping("/api/savings/total")
//    public Map<String, Double> getTotalSavings(@RequestParam String businessName) {
//        Double total = carbonSavingsDao.getTotalSavingsForBusiness(businessName);
//        Map<String, Double> response = new HashMap<>();
//        response.put("totalSavings", total != null ? total : 0.0);
//        return response;
//    }


}
