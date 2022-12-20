package ru.horn.har;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.horn.har.DAO.DatabaseDao;
import ru.horn.har.DAO.JsonParse.JsonParser;
import ru.horn.har.model.HarFile;
import ru.horn.har.controller.UploadController;

import java.io.File;

@SpringBootApplication
@ComponentScan({"ru.horn.har", "ru.horn.har.controller"})
public class HarApplication {

    public static void main(String[] args) {
        new File(UploadController.fileDirectory).mkdir();
        SpringApplication.run(HarApplication.class, args);

    }

}
