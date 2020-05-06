package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class Controller {


  @GetMapping("/check")
  public void check() throws InterruptedException {
    QueueConfig.getInstance().addInQueue(new TaskPojo<>("task1", 1000l, 1000));
    QueueConfig.getInstance().addInQueue(new TaskPojo<Long>("task2", 2000l, 2000));
    QueueConfig.getInstance().addInQueue(new TaskPojo<Long>("task3", 3000l, 3000));
    QueueConfig.getInstance().addInQueue(new TaskPojo<Long>("task4", 1500l, 1500));
    QueueConfig.getInstance().addInQueue(new TaskPojo<Long>("task5", 100l, 100));
    QueueConfig.getInstance().addInQueue(new TaskPojo<Long>("task6", 900l, 1000));

    QueueConfig.getInstance().executeTheMethod();
  }
}
