package com.github.afranken.feedback;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class Slot {
  private final String name;

  @JsonCreator
  static Slot slot(String name) {
    return new Slot(name);
  }
}
