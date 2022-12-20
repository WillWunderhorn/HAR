package ru.horn.har.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Bean;

@Builder
@AllArgsConstructor
@Entity
public class HarFile {

    @Id
    @NotNull
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "sql_gen")
    @SequenceGenerator(name = "id_gen", sequenceName = "hardata_id_seq", allocationSize = 1)
    static Long id;

    @NotNull
    static String version;

    @NotNull
    static String browser;

    @NotNull
    static String content;

    public HarFile(String version, String browser, String content) {
        this.id = id;
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
