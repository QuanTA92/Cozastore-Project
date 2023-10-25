package com.cybersoft.cozastore.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/push")
public class PushDataToQueueController {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @PostMapping("/queue")
    public ResponseEntity<?> pushToQueue(@RequestParam String data){

        rabbitTemplate.convertAndSend("exstorage02", "/routeStorage02", data);

        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}
