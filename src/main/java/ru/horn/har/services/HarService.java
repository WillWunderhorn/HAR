package ru.horn.har.services;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.horn.har.config.HarConfig;
import ru.horn.har.model.EBrowser;
import ru.horn.har.model.HarFile;
import ru.horn.har.rabbitmq.RabitController;
import ru.horn.har.repository.HarFileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HarService extends HttpServlet {
    private final HarConfig config;
    private final HarFileRepository repository;
    public final RabitController rabitController;
    public HttpServletRequest request;
    static String text;

    public static String getContents() {
        return text;
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

                    rabitController.emit(text);
                    repository.save(
                            new HarFile(null, version, browser, text)
                    );

                    Files.write(fileNamePath, file.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        model.addAttribute("message", "Successfully uploaded files: ");
        model.addAttribute("filesList", fileNames.toString());
        model.addAttribute("Browser: " + browser.name());
        return userAgent;
    }

}
