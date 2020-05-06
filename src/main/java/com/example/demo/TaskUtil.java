
package com.example.demo;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TaskUtil {

  private static TaskUtil taskUtil;
  private MutableLiveData<String> mutableLiveData;
  private ExecutorService executor;

  public MutableLiveData<String> getMutableLiveData() {
    return mutableLiveData;
  }

  public TaskUtil() {
    mutableLiveData = new MutableLiveData<>();
  }

  public static TaskUtil getInstance() {
    if (taskUtil == null) {
      taskUtil = new TaskUtil();
    }
    return taskUtil;
  }


  public boolean taskA(TaskPojo<Long> taskPojo) throws InterruptedException {
    final boolean[] result = {false};
    executor = Executors.newFixedThreadPool(1);
    executor.execute(new Runnable() {
      @Override
      public void run() {
        try {
          mutableLiveData.postValue("The " + taskPojo.taskname + " has started");
          Thread.sleep(taskPojo.parameters);
          result[0] = true;
          executor.shutdownNow();
        } catch (InterruptedException e) {
          System.out.println("exception ");
          result[0] = false;
        }
      }
    });
    if (executor.awaitTermination(1001, TimeUnit.MILLISECONDS) && result[0]) {
      mutableLiveData.postValue("The " + taskPojo.taskname + " is completed.");


    } else {

      taskPojo.parameters = taskPojo.parameters - 1000L;
      mutableLiveData.postValue(
          "Oops! the " + taskPojo.taskname + " was not completed. It requires more time of about "
              + taskPojo.parameters);
      executor.shutdownNow();
    }
    return result[0];
  }

  public boolean taskB(TaskPojo<Integer> taskPojo) throws InterruptedException {
    final boolean[] result = {false};
    executor = Executors.newFixedThreadPool(1);
    executor.execute(new Runnable() {
      @Override
      public void run() {
        try {
          mutableLiveData.postValue("The " + taskPojo.taskname + " has started");
          Thread.sleep(taskPojo.parameters);
          result[0] = true;
          executor.shutdownNow();
        } catch (InterruptedException e) {
          System.out.println("exception ");
          result[0] = false;
        }
      }
    });
    if (executor.awaitTermination(1001, TimeUnit.MILLISECONDS) && result[0]) {
      mutableLiveData.postValue("The " + taskPojo.taskname + " is completed.");
    } else {

      taskPojo.parameters = Math.toIntExact(taskPojo.parameters - 1000L);
      mutableLiveData.postValue(
          "Oops! the " + taskPojo.taskname + " was not completed. It requires more time of about "
              + taskPojo.parameters);
      executor.shutdownNow();
    }
    return result[0];
  }

  public boolean taskC(TaskPojo<String> taskPojo) throws InterruptedException {
    final boolean[] result = {false};
    executor = Executors.newFixedThreadPool(1);
    executor.execute(new Runnable() {
      @Override
      public void run() {
        try {
          mutableLiveData.postValue("The " + taskPojo.taskname + " has started");
          Thread.sleep(Long.parseLong(taskPojo.parameters));
          result[0] = true;
          executor.shutdownNow();
        } catch (InterruptedException e) {
          System.out.println("exception ");
          result[0] = false;
        }
      }
    });
    if (executor.awaitTermination(1001, TimeUnit.MILLISECONDS) && result[0]) {
      mutableLiveData.postValue("The " + taskPojo.taskname + " is completed.");
    } else {

      taskPojo.parameters = String.valueOf(Long.parseLong(taskPojo.parameters) - 1000L);
      mutableLiveData.postValue(
          "Oops! the " + taskPojo.taskname + " was not completed. It requires more time of about "
              + taskPojo.parameters);
      executor.shutdownNow();
    }
    return result[0];
  }
}
