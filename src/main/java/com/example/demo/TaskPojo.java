package com.example.demo;

public class TaskPojo<T> {

  String taskname;

  T parameters;

  int miliseconds;

  public TaskPojo(String taskname, T parameters, int miliseconds) {
    this.taskname = taskname;
    this.parameters = parameters;
    this.miliseconds = miliseconds;
  }
}
