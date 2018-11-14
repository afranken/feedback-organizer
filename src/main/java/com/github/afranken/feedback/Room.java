package com.github.afranken.feedback;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Room {
  private String name;

  public Room() {
  }

  public Room(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonCreator
  static Room room(String name) {
    return new Room(name);
  }
}
