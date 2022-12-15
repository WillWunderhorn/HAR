package ru.horn.har.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
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
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + table + " VALUES(?, ?, ?, ?)");
            preparedStatement.setLong(1, HarFile.getId());
            preparedStatement.setString(2, HarFile.getVersion());
            preparedStatement.setString(3, HarFile.getBrowser());
            preparedStatement.setString(4, HarFile.getContent());

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
