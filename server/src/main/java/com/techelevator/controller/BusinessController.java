package com.techelevator.controller;

import com.techelevator.dao.BusinessDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Business;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.access.prepost.PreAuthorize;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RestController
public class BusinessController {

    private BusinessDao businessDao;

    public BusinessController(BusinessDao businessDao) {
        this.businessDao = businessDao;
    }

//    @GetMapping("/tom")
//    public String wtf() {
//
//        return "Hello";
//    }

    @PreAuthorize("permitAll")
    @GetMapping(path = "/api/businesses")
    public List<Business> businesses() {

        return businessDao.getBusinesses();

    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/api/businesses/{id}")
    public Business getBusiness(@PathVariable int id) {

        Business business = businessDao.getBusinessById(id);

        if (business == null) {
            // 404 Not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Business not found");
        }
        return business;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/api/businesses")
    public Business addBusiness(@Valid @RequestBody Business newBusiness){

        return businessDao.createBusiness(newBusiness);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/api/businesses/{id}")
    public Business updateBusiness(@Valid @RequestBody Business updatedBusiness, @PathVariable int id) {

        updatedBusiness.setBusinessId(id);
        try {
            Business newBusiness = businessDao.updateBusiness(updatedBusiness);
            return newBusiness;
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Business Not Found");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/api/businesses/{id}")
    public void deleteBusiness(@PathVariable int id) {
        businessDao.deleteBusinessById(id);

    }

}
