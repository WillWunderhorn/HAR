package ru.horn.har.services;

import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.horn.har.config.HarConfig;
import ru.horn.har.controller.UploadController;
import ru.horn.har.model.EBrowser;
import ru.horn.har.model.HarFile;
import ru.horn.har.repository.HarFileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HarService extends HttpServlet {
    private final HarConfig config;
    private final HarFileRepository repository;
    private UploadController uploadController;
    static String text;
    static ArrayList<String> contents = new ArrayList<>();

    public static ArrayList<String> getContents() {
        return contents;
    }

    public String handle(
            Model model,
            MultipartFile[] files,
            String userAgent
    ) {
        StringBuilder fileNames = new StringBuilder();
        EBrowser browser = EBrowser.of(userAgent);

        for (MultipartFile file : files) {
            if (!file.isEmpty() && Objects.requireNonNull(file.getOriginalFilename()).endsWith(".HAR")) {
                Class<?> fileClass = file.getClass();
                Package filePack = fileClass.getPackage();
                String version = filePack.getImplementationVersion();
                try {
                    byte[] bytes = file.getBytes();
                    text = new String(bytes);
                    Path fileNamePath = Paths.get(config.getDir(), file.getOriginalFilename());

                    fileNames.append(file.getOriginalFilename())
                            .append(" Version: ")
                            .append(version)
                            .append(" text: ")
                            .append(text)
                            .append(" ");

                    contents.add(text);
                    repository.save(
                            new HarFile(null, version, browser, text)
                    );

                    System.out.println(contents);

                    Files.write(fileNamePath, file.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        model.addAttribute("message", "Successfully uploaded files: ");
        model.addAttribute("filesList", fileNames.toString());
        model.addAttribute("browser", "Browser: " + browser.name());
        return userAgent;
    }

}
