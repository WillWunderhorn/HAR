package ru.horn.har.config;


import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Getter
@Setter
@Configuration
@ConfigurationProperties("app.har")
public class HarConfig {
	private String dir;

	@PostConstruct
	public void pc() {
		new File(dir).mkdir();
	}
}
