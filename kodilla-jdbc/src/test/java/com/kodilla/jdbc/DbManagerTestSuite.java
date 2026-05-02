package com.kodilla.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DbManagerTestSuite {

    @Test
    void testConnect() throws SQLException {
        //Given
        //When
        DbManager dbManager = DbManager.getInstance();
        //Then
        assertNotNull(dbManager.getConnection());
    }

    @Test
    void testSelectUsers() throws SQLException {
        //Given
        DbManager dbManager = DbManager.getInstance();

        //When
        String sqlQuery = "SELECT * FROM USERS";
        Statement statement = dbManager.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);

        //Then
        int counter = 0;
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + ", " +
                    rs.getString("FIRSTNAME") + ", " +
                    rs.getString("LASTNAME"));
            counter++;
        }
        rs.close();
        statement.close();
        assertEquals(5, counter);
    }

    @Test
    void testSelectUsersAndTasks() throws SQLException {
        //Given
        DbManager dbManager = DbManager.getInstance();

        //When
        String sqlQuery = """
                SELECT U.FIRSTNAME, U.LASTNAME, I.SUMMARY
                FROM USERS U
                JOIN ISSUES I ON U.ID = I.USER_ID_ASSIGNEDTO
                """;
        Statement statement = dbManager.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);

        //Then
        int counter = 0;
        while (rs.next()) {
            System.out.println(rs.getString("FIRSTNAME") + ", " +
                    rs.getString("LASTNAME") + ", " +
                    rs.getString("SUMMARY"));
            counter++;
        }
        rs.close();
        statement.close();
        assertEquals(15, counter);
    }

    @Test
    void testSelectUsersAndPosts() throws SQLException {
        //Given
        DbManager dbManager = DbManager.getInstance();

        //When
        String sqlQuery = """
                SELECT U.ID, U.FIRSTNAME, U.LASTNAME, COUNT(*) AS NUMBER_OF_POSTS
                FROM USERS U JOIN POSTS P ON U.ID = P.USER_ID
                GROUP BY U.ID, U.FIRSTNAME, U.LASTNAME
                HAVING COUNT(*) >= 2
                """;
        Statement statement = dbManager.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);

        //Then
        int counter = 0;
        while (rs.next()) {
            System.out.println(rs.getString("FIRSTNAME") + ", " +
                    rs.getString("LASTNAME") + ", " +
                    rs.getInt("NUMBER_OF_POSTS"));
            counter++;
        }
        rs.close();
        statement.close();
        assertEquals(1, counter);
    }

    @Test
    public void testBooksInsertTrigger() throws SQLException {
        DbManager dbManager = DbManager.getInstance();
        Statement stmt = dbManager.getConnection().createStatement();

        // Given
        stmt.executeUpdate("DELETE FROM BOOKS_AUD");

        // When
        stmt.executeUpdate("INSERT INTO BOOKS (TITLE, PUBYEAR, BESTSELLER) VALUES ('TestBook', 2024, 0)");

        // Then
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS CNT FROM BOOKS_AUD WHERE EVENT_TYPE='INSERT'");
        rs.next();
        int count = rs.getInt("CNT");

        assertEquals(1, count);

        rs.close();
        stmt.close();
    }

    @Test
    public void testBooksUpdateTrigger() throws SQLException {
        DbManager dbManager = DbManager.getInstance();
        Statement stmt = dbManager.getConnection().createStatement();

        // Given
        stmt.executeUpdate("DELETE FROM BOOKS_AUD");
        stmt.executeUpdate("INSERT INTO BOOKS (TITLE, PUBYEAR, BESTSELLER) VALUES ('OldTitle', 2000, 0)");

        // When
        stmt.executeUpdate("UPDATE BOOKS SET TITLE='NewTitle' WHERE TITLE='OldTitle'");

        // Then
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS CNT FROM BOOKS_AUD WHERE EVENT_TYPE='UPDATE'");
        rs.next();
        int count = rs.getInt("CNT");

        assertEquals(1, count);

        rs.close();
        stmt.close();
    }

    @Test
    public void testBooksDeleteTrigger() throws SQLException {
        DbManager dbManager = DbManager.getInstance();
        Statement stmt = dbManager.getConnection().createStatement();

        // Given
        stmt.executeUpdate("DELETE FROM BOOKS_AUD");
        stmt.executeUpdate("INSERT INTO BOOKS (TITLE, PUBYEAR, BESTSELLER) VALUES ('ToDelete', 2001, 0)");

        // When
        stmt.executeUpdate("DELETE FROM BOOKS WHERE TITLE='ToDelete'");

        // Then
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS CNT FROM BOOKS_AUD WHERE EVENT_TYPE='DELETE'");
        rs.next();
        int count = rs.getInt("CNT");

        assertEquals(1, count);

        rs.close();
        stmt.close();
    }

    @Test
    public void testReadersInsertTrigger() throws SQLException {
        DbManager dbManager = DbManager.getInstance();
        Statement stmt = dbManager.getConnection().createStatement();

        // Given
        stmt.executeUpdate("DELETE FROM READERS_AUD");

        // When
        stmt.executeUpdate("INSERT INTO READERS (FIRSTNAME, LASTNAME, PESELID, VIP_LEVEL) VALUES ('Jan', 'Kowalski', '12345678901', 'Standard')");

        // Then
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS CNT FROM READERS_AUD WHERE EVENT_TYPE='INSERT'");
        rs.next();
        int count = rs.getInt("CNT");

        assertEquals(1, count);

        rs.close();
        stmt.close();
    }

    @Test
    public void testReadersUpdateTrigger() throws SQLException {
        DbManager dbManager = DbManager.getInstance();
        Statement stmt = dbManager.getConnection().createStatement();

        // Given
        stmt.executeUpdate("DELETE FROM READERS_AUD");
        stmt.executeUpdate("INSERT INTO READERS (FIRSTNAME, LASTNAME, PESELID, VIP_LEVEL) VALUES ('Anna', 'Nowak', '98765432109', 'Standard')");

        // When
        stmt.executeUpdate("UPDATE READERS SET VIP_LEVEL='Gold' WHERE FIRSTNAME='Anna'");

        // Then
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS CNT FROM READERS_AUD WHERE EVENT_TYPE='UPDATE'");
        rs.next();
        int count = rs.getInt("CNT");

        assertEquals(1, count);

        rs.close();
        stmt.close();
    }

    @Test
    public void testReadersDeleteTrigger() throws SQLException {
        DbManager dbManager = DbManager.getInstance();
        Statement stmt = dbManager.getConnection().createStatement();

        // Given
        stmt.executeUpdate("DELETE FROM READERS_AUD");
        stmt.executeUpdate("INSERT INTO READERS (FIRSTNAME, LASTNAME, PESELID, VIP_LEVEL) VALUES ('Piotr', 'Zielinski', '11111111111', 'Standard')");

        // When
        stmt.executeUpdate("DELETE FROM READERS WHERE FIRSTNAME='Piotr'");

        // Then
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS CNT FROM READERS_AUD WHERE EVENT_TYPE='DELETE'");
        rs.next();
        int count = rs.getInt("CNT");

        assertEquals(1, count);

        rs.close();
        stmt.close();
    }
}
