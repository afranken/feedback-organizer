package com.github.afranken.feedback;

import com.fasterxml.jackson.annotation.JsonCreator;

public class TeamMember {

  private String name;

  public TeamMember() {
  }

  public TeamMember(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonCreator
  static TeamMember teamMember(String name) {
    return new TeamMember(name);
  }
}
