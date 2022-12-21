package ru.horn.har.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.horn.har.services.HarService;

@Controller
@RequiredArgsConstructor
public class UploadController {
    public final HarService service;

    @RequestMapping(value = "/index")
    public String UploadPage(Model model) {
        return "index";
    }

    @RequestMapping(value = "/uploadingError")
    public String UploadingError(Model model) {
        return "uploadingError";
    }

    @RequestMapping(value = "/upload")
    public String upload(
            Model model,
            @RequestParam("files") MultipartFile[] files,
            @RequestHeader("User-Agent") String userAgent
    ) {
        service.handle(model, files, userAgent);
        return "view";
    }
}
