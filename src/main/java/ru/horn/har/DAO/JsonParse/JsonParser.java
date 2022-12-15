package ru.horn.har.DAO.JsonParse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonParser {

    static String jsonDriver = null;
    static String jsonUrl = null;
    static String jsonDatabase = null;
    static String jsonUsername = null;
    static String jsonPassword = null;

    public static String getJsonDriver() {
        return jsonDriver;
    }

    public static String getJsonUrl() {
        return jsonUrl;
    }

    public static String getJsonDatabase() {
        return jsonDatabase;
    }

    public static String getJsonUsername() {
        return jsonUsername;
    }

    public static String getJsonPassword() {
        return jsonPassword;
    }

    public Root parce() {

        Root root = new Root();
        JSONParser parser = new JSONParser();

        try (FileReader fileReader = new FileReader("src/main/resources/connection.json")) {

            JSONObject rootObject = (JSONObject) parser.parse(fileReader);
            JSONArray datasourceArray = (JSONArray) rootObject.get("datasource");

            List<Datasource> datasourceList = new ArrayList<>();

            for(Object obj: datasourceArray){
                JSONObject datasourceObject = (JSONObject) obj;

                jsonDriver = (String) datasourceObject.get("driver");
                jsonUrl = (String) datasourceObject.get("url");
                jsonDatabase = (String) datasourceObject.get("database");
                jsonUsername = (String) datasourceObject.get("username");
                jsonPassword = (String) datasourceObject.get("password");

                Datasource datasource = new Datasource(jsonDriver, jsonUrl, jsonDatabase, jsonUsername, jsonPassword);
                datasourceList.add(datasource);
            }

            return root;

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    static JsonParser parser = new JsonParser();
    static Root root = parser.parce();

}
