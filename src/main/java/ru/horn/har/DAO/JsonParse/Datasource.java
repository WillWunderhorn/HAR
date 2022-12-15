package ru.horn.har.DAO.JsonParse;

public class Datasource {
    private String driver;
    private String url;
    private String database;
    private String username;
    private String password;

    public Datasource(String driver, String url, String database, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Datasource{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", database='" + database + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
