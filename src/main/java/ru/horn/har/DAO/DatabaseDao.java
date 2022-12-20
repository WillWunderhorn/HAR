package ru.horn.har.DAO;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import ru.horn.har.DAO.JsonParse.JsonParser;
import ru.horn.har.model.HarFile;

import java.sql.*;

@Repository
public class DatabaseDao {
    String table = JsonParser.getJsonDatabase();
    String url = JsonParser.getJsonUrl();
    String username = JsonParser.getJsonUsername();
    String password = JsonParser.getJsonPassword();


    public void addContent(HarFile harFileDAO) {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            PreparedStatement preparedSequence = conn.prepareStatement("CREATE SEQUENCE IF NOT EXISTS hardata_id_seq;");
            preparedSequence.executeUpdate();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + table + " VALUES(nextval('hardata_id_seq'), ?, ?, ?) RETURNING id");
            preparedStatement.setString(1, HarFile.getVersion());
            preparedStatement.setString(2, HarFile.getBrowser());
            preparedStatement.setString(3, HarFile.getContent());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ADDING FAILED" + "\n" + e);
        }
    }

    public void clearTable() {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            int rows = statement.executeUpdate("TRUNCATE TABLE " + table
            );

        } catch (SQLException ex) {
            System.out.println("TRUNCATING FAILED" + "\n" + ex);
        }
    }

}
