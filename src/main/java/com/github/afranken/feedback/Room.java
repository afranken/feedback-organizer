package com.github.afranken.feedback;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data public class Room {
  private final String name;

  @JsonCreator
  static Room room(String name) {
    return new Room(name);
  }
}
