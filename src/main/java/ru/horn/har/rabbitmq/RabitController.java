//package ru.horn.har.rabbitmq;
//
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.logging.Logger;
//
//@RestController
//public class RabitController {
//
//    Logger logger = (Logger) LoggerFactory.getLogger(RabitController.class);
//    private final AmqpTemplate template;
//
//    @Autowired
//    public RabitController(AmqpTemplate template) {
//        this.template = template;
//    }
//
//    @PostMapping("/emit")
//    public ResponseEntity<String> emit(@RequestBody String message){
//        logger.info("Emitted to queue");
//        template.convertAndSend("myQueue", message);
//        return ResponseEntity.ok("Successfully emitted to queue");
//    }
//}
