package ru.horn.har.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.horn.har.services.HarService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UploadControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Model model;
    private MultipartFile[] files;
    private String userAgent;
    private UploadController uploadController;
    private HarService service;


    @Test
    void Controller() throws Exception {
        assertThat(uploadController).isNotNull();
    }

    @Test
    void uploadPage() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("/index")));
    }

    @Test
    void uploadingError() throws Exception {
        this.mockMvc.perform(get("/uploadingError"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("/uploadingError")));
    }

    @Test
    void filesIsNull() throws Exception {
        assertThat(files).isNull();
    }

    @Test
    void userAgentIsNull() throws Exception {
        assertThat(userAgent).isNull();
    }

}