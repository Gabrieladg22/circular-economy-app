package com.techelevator.controller;

import com.techelevator.dao.MaterialDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Material;
import com.techelevator.model.dto.MaterialResponse;
import com.techelevator.service.MaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.security.Principal;
import java.util.List;


@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RestController
public class MaterialController {

    private MaterialDao materialDao;

    private MaterialService materialService;

    public MaterialController(MaterialDao materialDao, MaterialService materialService) {
        this.materialDao = materialDao;
        this.materialService = materialService;
    }

    @PreAuthorize("permitAll")
    @GetMapping(path = "/api/materials")
    public List<Material> listOfMaterials(@RequestParam(required = false) boolean isAvailableForTrading) {

        if (isAvailableForTrading) {
            return materialDao.getMaterialsAvailableForTrading();
        } else {
            return materialDao.getMaterials();
        }

    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/api/materials/{materialId}")
    public Material getMaterial(@PathVariable int materialId) {

        Material material = materialDao.getMaterialById(materialId);

        if (material == null) {
            // 404 Not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material not found");
        }
        return material;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping (path = "/api/materials")

    public MaterialResponse addMaterial(@Valid @RequestBody Material newMaterial, Principal principal){

        String username = principal.getName();
        newMaterial.setCreatedBy(username);
        Material createdMaterial = materialDao.createMaterial(newMaterial);

        //Get business Id to use it in AddMaterialAndCalculateSavings()
        int businessId = createdMaterial.getBusinessId();

        double carbonSavings = materialService.addMaterialAndCalculateSavings(businessId, newMaterial.getMaterialName(),
                newMaterial.getQuantityKg());

        System.out.println("By facilitating " + newMaterial.getMaterialName() + " to be reused, you have saved " + carbonSavings +
                "kg carbon emissions that would be generated if a new one was produced");

        return new MaterialResponse(createdMaterial, carbonSavings);

    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'CREATOR')")
    @RequestMapping(path = "/api/materials/{materialId}", method = RequestMethod.PUT)
    public Material updateMaterial(@Valid @RequestBody Material material, @PathVariable int materialId, Principal principal){
        material.setMaterialId(materialId);
        String username = principal.getName();
        material.setCreatedBy(username);
        try {
            Material updatedMaterial = materialDao.updateMaterial(material);
            return updatedMaterial;
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material Not Found");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/api/materials/{id}")
    public void deleteMaterial(@PathVariable int id) {

        materialDao.deleteMaterialById(id);

    }

}
