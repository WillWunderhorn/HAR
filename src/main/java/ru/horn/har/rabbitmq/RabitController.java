package ru.horn.har.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.horn.har.services.HarService;

@RestController
public class RabitController {

    Logger logger = LoggerFactory.getLogger(RabitController.class);

    private final RabbitTemplate template;

    @Autowired
    public RabitController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/emit")
    public ResponseEntity<String> emit(
            String text
    ) {
//        logger.info("Emitted to queue");
        template.setExchange("common-exchange");
//        template.convertAndSend("myQueue", HarService.getContents());
        logger.info("Received from myQueue: {}", HarService.getContents());
        return ResponseEntity.ok("Successfully emitted to queue");

    }
}
