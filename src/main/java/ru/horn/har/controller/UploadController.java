package ru.horn.har.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.horn.har.services.HarService;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UploadController {
    public final HarService service;

    @RequestMapping(value = "/index")
    public String UploadPage() {
        return "index";
    }
    @RequestMapping(value = "/uploadingError")
    public String UploadingError() {
        return "uploadingError";
    }

    @RequestMapping(value = "/upload")
    public String upload(
            Model model,
            @RequestParam("files") MultipartFile[] files,
            @RequestHeader("User-Agent") String userAgent
    ) {
        for (MultipartFile file : files) {
            if (file.isEmpty() || !Objects.requireNonNull(file.getOriginalFilename()).endsWith(".HAR")) {
                return "/uploadingError";
            }
        }
        service.handle(model, files, userAgent);
        return "view";
    }
}
