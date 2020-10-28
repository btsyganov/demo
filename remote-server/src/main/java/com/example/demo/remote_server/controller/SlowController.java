package com.example.demo.remote_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
public class SlowController {
    @GetMapping("/data")
    public Map<String, Object> getData() throws InterruptedException {
        Random r = new Random();
        int lag = r.nextInt(2000);
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(lag);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
        thread.join();
        return Map.of("data", lag);
    }
}
