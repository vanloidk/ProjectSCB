package scb.com.vn.dbi.dao;

import java.util.ArrayList;

import scb.com.vn.ultility.jdbc.JDBCEngine;

/**
 *
 * @author system
 */
public class DominoDAO extends BaseDAO {

    // LAY TI GIA
    final String GET_EXCHANGE_RATE = " SELECT A.Name,C.OrderNumber,cast(C.AddedDate as time(0)) as NOTE, B.*"
            + " FROM Domino.GoldExchangeCategories A,  Domino.ExchangeRate B,"
            + " (SELECT top 1 ForeignExchangeId,OrderNumber,AddedDate FROM Domino.ForeignExchange order by SortOrder desc) C"
            + " WHERE B.ForeignExchangeId = C.ForeignExchangeId AND A.CatID = B.CatID;";
    // LAY GIA VANG
    final String GET_GOLD_RATE_OLD = " SELECT A.Name, C.OrderNumber, D.NOTE, B.*"
            + " FROM Domino.GoldExchangeCategories A,  Domino.GoldRate B,Domino.GoldPrices D,"
            + " (SELECT distinct top 2 OrderNumber FROM Domino.GoldPrices order by OrderNumber desc) C"
            + " WHERE C.OrderNumber = D.OrderNumber AND D.GoldPriceID = B.GoldPriceID"
            + " AND B.CatID = A.CatID AND B.Buying <> 0"
            + " ORDER BY D.SortOrder desc, CatID";
    final String GET_GOLD_RATE = " SELECT   A.Name,C.OrderNumber,CONVERT(VARCHAR(8), C.AddedDate, 108)  as NOTE,B.*,"
            + " CASE WHEN (E.GoldPriceID IS NULL) THEN '0'"
            + "    WHEN (E.GoldPriceID IS NOT NULL) THEN '1'"
            + " END AS lastest            "
            + "  FROM  (SELECT distinct top 2 OrderNumber,AddedDate,GoldPriceID,SortOrder FROM Domino.GoldPrices order by OrderNumber desc) C"
            + "  JOIN Domino.GoldRate B ON C.GoldPriceID = B.GoldPriceID"
            + "  JOIN Domino.GoldExchangeCategories A ON  B.CatID = A.CatID AND B.Buying <> 0"
            + "   LEFT JOIN  (SELECT distinct top 1 GoldPriceID FROM Domino.GoldPrices order by GoldPriceID desc) E"
            + "    ON C.GoldPriceID = E.GoldPriceID"
            + " ORDER BY C.SortOrder desc, B.CatID";

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getExchangeRate() throws Exception {
        return JDBCEngine.executeQuery(GET_EXCHANGE_RATE, null, connection);
    }

    /**
     *
     * @return @throws Exception
     */
    public ArrayList<?> getGoldRate() throws Exception {
        return JDBCEngine.executeQuery(GET_GOLD_RATE, null, connection);
    }
}
