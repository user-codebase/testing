package com.kodilla.jdbc;

import org.junit.jupiter.api.Test;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;


public class StoredProcTestSuite {
    @Test
    public void testUpdateVipLevels() throws SQLException {
        // Given
        DbManager dbManager = DbManager.getInstance();
        String sqlUpdate = "UPDATE READERS SET VIP_LEVEL=\"Not set\"";
        Statement statement = dbManager.getConnection().createStatement();
        statement.executeUpdate(sqlUpdate);
        String sqlCheckTable = "SELECT COUNT(*) AS HOW_MANY FROM READERS WHERE VIP_LEVEL=\"Not set\"";

        // When
        Statement statement2 = dbManager.getConnection().createStatement();
        String sqlProcedureCall = "CALL UpdateVipLevels()";
        statement2.execute(sqlProcedureCall);
        ResultSet rs = statement.executeQuery(sqlCheckTable);

        // Then
        int howMany = -1;
        if (rs.next()) {
            howMany = rs.getInt("HOW_MANY");
        }
        assertEquals(0, howMany);
        rs.close();
        statement.close();
        statement2.close();
    }


    @Test
    public void testUpdateBestsellers() throws SQLException {
        // Given
        DbManager dbManager = DbManager.getInstance();

        String sqlReset = "UPDATE BOOKS SET BESTSELLER = 0";
        Statement statement = dbManager.getConnection().createStatement();
        statement.executeUpdate(sqlReset);

        String sqlCheckBefore = "SELECT COUNT(*) AS HOW_MANY FROM BOOKS WHERE BESTSELLER = 1";
        ResultSet rsBefore = statement.executeQuery(sqlCheckBefore);

        int before = -1;
        if (rsBefore.next()) {
            before = rsBefore.getInt("HOW_MANY");
        }

        // When
        Statement statement2 = dbManager.getConnection().createStatement();
        String sqlProcedureCall = "CALL UpdateBestsellers()";
        statement2.execute(sqlProcedureCall);

        String sqlCheckAfter = "SELECT COUNT(*) AS HOW_MANY FROM BOOKS WHERE BESTSELLER = 1";
        ResultSet rsAfter = statement.executeQuery(sqlCheckAfter);

        int after = -1;
        if (rsAfter.next()) {
            after = rsAfter.getInt("HOW_MANY");
        }

        // Then
        assertEquals(0, before);
        assertFalse(after > 0);

        // cleanup
        rsBefore.close();
        rsAfter.close();
        statement.close();
        statement2.close();
    }

}
