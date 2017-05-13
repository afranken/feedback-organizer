package com.github.afranken.feedback;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class TeamMember {

  private final String name;

  @JsonCreator
  static TeamMember teamMember(String name) {
    return new TeamMember(name);
  }
}
