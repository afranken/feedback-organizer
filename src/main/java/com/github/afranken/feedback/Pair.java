package com.github.afranken.feedback;

import java.util.Objects;

public class Pair {
  private TeamMember memberOne;
  private TeamMember memberTwo;

  public Pair() {
  }

  public Pair(TeamMember memberOne, TeamMember memberTwo) {
    this.memberOne = memberOne;
    this.memberTwo = memberTwo;
  }

  public TeamMember getMemberOne() {
    return memberOne;
  }

  public TeamMember getMemberTwo() {
    return memberTwo;
  }

  public String getRenderedName() {
    return String.join(" : ", memberOne.getName(), memberTwo.getName());
  }

  /**
   * A pair is unique if none of the members match.
   */
  static boolean hasUniqueMembers(Pair one, Pair two) {
    return one == null ||
        two == null ||
        !Objects.equals(one.getMemberOne(), two.getMemberOne()) && !Objects
        .equals(one.getMemberOne(), two.getMemberTwo()) && !Objects
        .equals(one.getMemberTwo(), two.getMemberOne()) && !Objects
        .equals(one.getMemberTwo(), two.getMemberTwo());

  }

  @Override public int hashCode() {
    return Objects.hash(memberOne, memberTwo) + Objects.hash(memberTwo, memberOne);
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Pair other = (Pair) obj;
    return (Objects.equals(this.memberOne, other.memberOne)
        && Objects.equals(this.memberTwo, other.memberTwo))
        || (Objects.equals(this.memberOne, other.memberTwo)
        && Objects.equals(this.memberTwo, other.memberOne));
  }
}
