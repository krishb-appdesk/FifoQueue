package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class Controller {


  @GetMapping("/check")
  public void check() throws InterruptedException {
    QueueConfig.getInstance().addInQueue(new TaskPojo<>("taskA", 1000l, 1000));
    QueueConfig.getInstance().addInQueue(new TaskPojo<>("taskB", 2000, 2000));
    QueueConfig.getInstance().addInQueue(new TaskPojo<>("taskC", "100", 100));

    QueueConfig.getInstance().executeTheMethod();
  }
}
