package com.techelevator.controller;


import com.techelevator.dao.TradingDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Trading;
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
public class TradingController {

    private TradingDao tradingDao;

    public TradingController(TradingDao tradingDao) {
        this.tradingDao = tradingDao;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/api/trades")
    public List<Trading> listOfTrades(@RequestParam(required = false, defaultValue = "") String tradeStatus,
                                      @RequestParam(required = false, defaultValue = "0") Integer offeredByBusinessId,
                                      @RequestParam(required = false, defaultValue = "0") Integer requestedMaterialId) {

        if(!tradeStatus.equals("")) {
            return tradingDao.getAcceptedTrades();
        }
        if (offeredByBusinessId > 0) {

            return tradingDao.getTradeByOfferedByBusinessId(offeredByBusinessId);

        }
        if(requestedMaterialId > 0) {

            return tradingDao.getTradeByRequestedMaterialId(requestedMaterialId);
        }

        if(tradeStatus.equals("") && offeredByBusinessId <= 0 && requestedMaterialId <= 0){

            return tradingDao.getTrades();
        }

        return null;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/api/trades/{tradeId}")
    public Trading getTrade(@PathVariable int tradeId) {

        Trading trading = tradingDao.getTradeById(tradeId);

        if (trading == null) {
            // 404 Not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material not found");
        }
        return trading;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping (path = "/api/trades")
    public Trading addTrade(@Valid @RequestBody Trading newTrading, Principal principal){

        String username = principal.getName();
        newTrading.setCreatedBy(username);
        Trading trade = tradingDao.createTrade(newTrading);
        return trade;
    }

    @PreAuthorize("hasAnyRole('CREATOR', 'ADMIN')")
    @RequestMapping(path = "/api/trades/{tradeId}", method = RequestMethod.PUT)
    public Trading updateTrading(@Valid @RequestBody Trading trade, @PathVariable int tradeId, Principal principal){

        trade.setTradeId(tradeId);
        String username = principal.getName();
        trade.setCreatedBy(username);

        try {
            Trading updatedTrading = tradingDao.updateTrade(trade);
            return updatedTrading;
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material Not Found");
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/api/trades/{id}")
    public void deleteTrade(@PathVariable int id) {

        tradingDao.deleteTradeById(id);

    }
}
