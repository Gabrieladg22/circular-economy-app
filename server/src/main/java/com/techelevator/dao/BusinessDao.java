package com.techelevator.dao;

import com.techelevator.model.Business;

import java.util.List;

public interface BusinessDao {

    /**
     * Get a Business from the datastore that has the given id.
     * If the id is not found, return null.
     * @param businessId The id of the business.
     * @return A filled out Business object, null if the businessId isn't in the database.
     */
    Business getBusinessById(int businessId);

    /**
     * Get all businesses from the datastore, ordered by business_id.
     * @return All businesses as Business objects in a List.
     */
    List<Business> getBusinesses();

    /**
     * Add a new business into the datastore.
     * @param newBusiness the Business object to add.
     * @return The added business object with its new id filled in.
     */
    Business createBusiness(Business newBusiness);

    /**
     * Update a business to the datastore. Only called on businesses that
     * are already in the datastore.
     * @param updatedBusiness The business object to update.
     * @return The updated business object.
     */
    Business updateBusiness(Business updatedBusiness);

    /**
     * Remove a business from the datastore.
     * @param businessId The id of the business to remove. If the id doesn't exist, no error will occur.
     * @return The number of rows deleted.
     */
    int deleteBusinessById(int businessId);
}
