package com.example.demo;

public class MutableLiveData<T> {

  T value;
  ImObserver<T> imObserver;

  public void postValue(T value) {
    this.value = value;
    if (imObserver != null) {
      imObserver.update(value);
    }
  }

  public void observer(ImObserver<T> observer) {
    imObserver = observer;
    observer.update(value);
  }
}
