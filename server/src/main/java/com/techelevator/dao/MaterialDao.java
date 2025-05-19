package com.techelevator.dao;

import com.techelevator.model.Material;

import java.util.List;

public interface MaterialDao {

    /**
     * Get a material from the datastore that has the given id.
     * If the id is not found, return null.
     * @param materialId The id of the product.
     * @return A filled out Material object, or null if the id is invalid.
     */
    Material getMaterialById(int materialId);

    /**
     * Get all materials from the datastore, ordered by material_id.
     * @return All materials as Material objects in a List.
     */
    List<Material> getMaterials();

    /**
     * Get a list of material that are available for trading, ordered by product_id.
     * @return All available materials as Product objects in a List.
     */
    List<Material> getMaterialsAvailableForTrading();

    /**
     * Add a new material into the datastore.
     * @param newMaterial The Product object to add.
     * @return The added Product object with its new id filled in.
     */
    Material createMaterial(Material newMaterial);

    /**
     * Update a material in the datastore. Only called on products that
     * are already in the datastore.
     * @param updatedMaterial The Material object to update.
     * @return The updated Material object with its new id filled in.
     */
    Material updateMaterial(Material updatedMaterial);

    /**
     * Remove a material from the datastore.
     * @param materialId The id of the material to remove. If the id doesn't exist, no error will occur.
     * @return The number of rows deleted.
     */
    int deleteMaterialById(int materialId);

    /**
     * Get Electricity according to material weight
     */

}
