package com.techelevator.dao;


import com.techelevator.model.Trading;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdbcTradingDaoTest extends BaseDaoTest{

    private final Trading TRADE_1 = new Trading(1, 1, 5, 1, 5, "pending", "2024-01-31", null);

    private final Trading TRADE_2 = new Trading(2, 5, 3, 4, 1, "pending", "2024-05-20", null);

    private final Trading TRADE_3 = new Trading(4, 2, 3, 1, 2, "accepted", "2024-10-11", null);

    private JdbcTradingDao sut;
    private Trading testTrade;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.sut = new JdbcTradingDao(jdbcTemplate);


    }

    @Test
    public void getTradeById_returns_correct_Trade(){

        Trading trade = sut.getTradeById(1);
        assertTradesMatch(TRADE_1, trade);

        trade = sut.getTradeById(2);
        assertTradesMatch(TRADE_2, trade);

        trade = sut.getTradeById(4);
        assertTradesMatch(TRADE_3, trade);

    }

    @Test
    public void createTrade_creates_new_trade() {

        Trading trading = new Trading();

        trading.setOfferedMaterialId(1);
        trading.setRequestedMaterialId(5);
        trading.setOfferedByBusinessId(1);
        trading.setRequestedToBusinessId(5);
        trading.setTradeStatus("pending");
        trading.setTradeDate("2024-01-31");
        trading.setCreatedBy(null);


        Trading createdTrade = sut.createTrade(trading);

        int newId = createdTrade.getTradeId();
        Assertions.assertTrue(newId > 0);

        Trading resultTrade = sut.getTradeById(newId);
        assertTradesMatch(createdTrade, resultTrade);

    }

    @Test
    public void updateMaterial_returns_correct_values(){
        Trading createdTrade = sut.getTradeById(1);

        createdTrade.setOfferedMaterialId(2);
        createdTrade.setRequestedMaterialId(3);
        createdTrade.setOfferedByBusinessId(1);
        createdTrade.setRequestedToBusinessId(5);
        createdTrade.setTradeStatus("accepted");
        createdTrade.setTradeDate("2024-01-01");

        sut.updateTrade(createdTrade);

        Trading materialResult = sut.getTradeById(1);

        assertTradesMatch(createdTrade, materialResult);
    }

    @Test
    public void deleteTradeById_deletes_the_row() {

        int rowsAffected = sut.deleteTradeById(2);
        assertEquals(1, rowsAffected);
        Trading tradingResult = sut.getTradeById(2);
        Assertions.assertNull(tradingResult);

    }


    private void assertTradesMatch(Trading expected, Trading actual) {

        Assertions.assertEquals(expected.getTradeId(), actual.getTradeId());
        Assertions.assertEquals(expected.getOfferedMaterialId(), actual.getOfferedMaterialId());
        Assertions.assertEquals(expected.getRequestedMaterialId(), actual.getRequestedMaterialId());
        Assertions.assertEquals(expected.getOfferedByBusinessId(), actual.getOfferedByBusinessId());
        Assertions.assertEquals(expected.getRequestedToBusinessId(), actual.getRequestedToBusinessId());
        Assertions.assertEquals(expected.getTradeStatus(), actual.getTradeStatus());
        Assertions.assertEquals(expected.getTradeDate(), actual.getTradeDate());
    }



}
