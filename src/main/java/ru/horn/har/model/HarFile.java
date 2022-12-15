package ru.horn.har.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Builder
@AllArgsConstructor
@Entity
public class HarFile {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "sql_gen")
    @SequenceGenerator(name = "id_gen", sequenceName = "seq_id", allocationSize = 1)
    static Long id;

    static String version;
    static String browser;
    static String content;

    public HarFile(String version, String browser, String content) {
        this.version = version;
        this.browser = browser;
        this.content = content;
    }

    public static Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public static String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HarFile{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", browser='" + browser + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
