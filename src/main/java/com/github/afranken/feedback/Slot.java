package com.github.afranken.feedback;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Slot {
  private String name;

  public Slot() {
  }

  public Slot(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonCreator
  static Slot slot(String name) {
    return new Slot(name);
  }
}
