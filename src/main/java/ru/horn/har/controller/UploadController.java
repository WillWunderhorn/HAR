package ru.horn.har.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.horn.har.DAO.DatabaseDao;
import ru.horn.har.model.HarFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
    public static String fileDirectory = System.getProperty("user.dir") + "/uploads";

    @RequestMapping(value = "/index")
    public String UploadPage(Model model) {
        return "index";
    }

    @RequestMapping(value = "/uploadingError")
    public String UploadingError(Model model) {
        return "uploadingError";
    }

    @RequestMapping(value = "/upload")
    public String upload(Model model, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        String userAgent = request.getHeader("User-Agent");
        String browserName = "---";
        DatabaseDao databaseDao = new DatabaseDao();
        if (userAgent.contains("YaBrowser") || userAgent.contains("Yowser")) {
            browserName = "Yandex";
        } else if (userAgent.contains("Chrome")) {
            browserName = "Chrome";
        } else if (userAgent.contains("Mozilla")) {
            browserName = "Mozilla";
        } else {
            browserName = "Unknown browser";
        }
        for (MultipartFile file : files) {
            if (!file.isEmpty() && file.getOriginalFilename().endsWith(".HAR")) {
                Class<?> fileClass = file.getClass();
                Package filePack = fileClass.getPackage();
                String version = filePack.getImplementationVersion();
                byte[] bytes = file.getBytes();
                String text = new String(bytes);
                Path fileNamePath = Paths.get(fileDirectory, file.getOriginalFilename());
                fileNames.append(file.getOriginalFilename() + " Version: " + version + " text: " + text + " ");
                databaseDao.addContent(new HarFile(version, browserName, text));
                try {
                    Files.write(fileNamePath, file.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                return "uploadingError";
            }
        }
        model.addAttribute("message", "Successfully uploaded files: ");
        model.addAttribute("filesList", fileNames.toString());
        model.addAttribute("browser", "Browser: " + browserName);
        return "view";

    }
}
