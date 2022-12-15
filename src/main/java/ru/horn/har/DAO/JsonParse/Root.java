package ru.horn.har.DAO.JsonParse;

import java.util.List;

public class Root {
    private static List<Datasource> datasources;

    public static List<Datasource> getDatasources() {
        return datasources;
    }

    public void setDatasources(List<Datasource> datasources) {
        this.datasources = datasources;
    }

    @Override
    public String toString() {
        return "Root{" +
                "datasources=" + datasources +
                '}';
    }
}
