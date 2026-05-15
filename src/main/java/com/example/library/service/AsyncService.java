package com.example.library.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async
    public void sendBookCreatedNotification(String title) {

        try {

            Thread.sleep(5000);

            System.out.println("Notification sent for book: " + title);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void generateBookReport() {

        try {

            Thread.sleep(3000);

            System.out.println("Book report generated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}