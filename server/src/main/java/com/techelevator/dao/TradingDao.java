package com.techelevator.dao;

import com.techelevator.model.Trading;

import java.util.List;

public interface TradingDao {
    /**
     Get a Trade from the datastore that has the given id, if the id is not found, return null.
     @param tradeId The id of the trade.
     @return A trade object, null if the id is not found in the database.
     */
    Trading getTradeById(int tradeId);

    /**
     * Get all trades from the datastore that have been accepted by both businesses and the materials are available,
     * ordered by trade_id.
     * @return All rejected trades as Trade objects in a List.
     */
    List<Trading> getAcceptedTrades();

    /**
     * Get all Trades from the datastore offered by a given Business, ordered by trade_id.
     * @param offeredByBusinessId The id of the business.
     * @return All trades as trade objects in a List.
     */
    List<Trading> getTradeByOfferedByBusinessId(int offeredByBusinessId);

    /**
     * Get all trades from the datastore for a requested material, ordered by trade_id.
     * @param requestedMaterialId The id of the material.
     * @return All trades as trade objects in a List.
     */
    List<Trading> getTradeByRequestedMaterialId(int requestedMaterialId);

    /**
     * Add a new trade into the datastore.
     * @param newTrade The Trade object to add.
     * @return The added Trade object with its new id filled in.
     */
    Trading createTrade(Trading newTrade);

    /**
     * Update a trade to the datastore. Only called on trades that
     * are already in the datastore.
     * @param updatedTrade The Trade object to update.
     * @return The updated Trade object.
     */
    Trading updateTrade(Trading updatedTrade);

    /**
     * Remove a Trade from the datastore.
     * @param tradeId The id of the trade to remove. If the id doesn't exist, no error will occur.
     * @return The number of trades deleted.
     */
    int deleteTradeById(int tradeId);

    List<Trading> getTrades();

}
