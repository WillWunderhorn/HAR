package ru.horn.har.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.horn.har.config.HarConfig;
import ru.horn.har.model.EBrowser;
import ru.horn.har.model.HarFile;
import ru.horn.har.repository.HarFileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HarService {
    private final HarConfig config;
    private final HarFileRepository repository;

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
                    String text = new String(bytes);
                    Path fileNamePath = Paths.get(config.getDir(), file.getOriginalFilename());

                    fileNames.append(file.getOriginalFilename())
                            .append(" Version: ")
                            .append(version)
                            .append(" text: ")
                            .append(text)
                            .append(" ");

                    repository.save(
                            new HarFile(null, version, browser, text)
                    );

                    Files.write(fileNamePath, file.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                return "uploadingError"; // логика возврата страницы с ошибкой из файла "UploadController" (36 - 40 строки) должна быть тут, я пока не знаю как ее перенести
            }
        }
        model.addAttribute("message", "Successfully uploaded files: ");
        model.addAttribute("filesList", fileNames.toString());
        model.addAttribute("browser", "Browser: " + browser.name());
        return userAgent;
    }
}
