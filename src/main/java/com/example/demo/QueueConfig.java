package com.example.demo;

import java.util.LinkedList;
import java.util.Queue;

public class QueueConfig {

  private static QueueConfig queueConfig;

  public QueueConfig() {
    this.queue = new LinkedList<>();

    TaskUtil.getInstance().getMutableLiveData().observer(new ImObserver<String>() {
      @Override
      public void update(String s) {
        if (s != null) {
          System.out.println(s);
        }
      }
    });
  }

  private Queue<TaskPojo> queue;

  public static QueueConfig getInstance() {
    if (queueConfig == null) {
      queueConfig = new QueueConfig();
    }
    return queueConfig;
  }

  public boolean addInQueue(TaskPojo<?> taskPojo) {

    return queue.add(taskPojo);
  }

  public TaskPojo<?> removeFromQueue() {
    return queue.remove();
  }


  public void executeTheMethod() throws InterruptedException {
    while (!queue.isEmpty()) {
      TaskPojo<?> taskPojo = removeFromQueue();
      System.out.println("The task name is " + taskPojo.taskname + " and the time required is "
          + taskPojo.parameters);
      if (taskPojo.taskname.equals("taskA")) {
        if (!TaskUtil.getInstance().taskA((TaskPojo<Long>) taskPojo)) {
          queue.add(taskPojo);
        }
      }
      if (taskPojo.taskname.equals("taskB")) {
        if (!TaskUtil.getInstance().taskB((TaskPojo<Integer>) taskPojo)) {
          queue.add(taskPojo);
        }
      }
      if (taskPojo.taskname.equals("taskC")) {
        if (!TaskUtil.getInstance().taskC((TaskPojo<String>) taskPojo)) {
          queue.add(taskPojo);
        }
      }
      System.out.println("Current size is " + queue.size());
    }
    System.out.println("All Jobs are completed");
  }
}
