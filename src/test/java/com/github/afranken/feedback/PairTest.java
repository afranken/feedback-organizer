package com.github.afranken.feedback;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PairTest {

  private TeamMember one = new TeamMember("ONE");
  private TeamMember two = new TeamMember("TWO");
  private TeamMember three = new TeamMember("THREE");
  private TeamMember four = new TeamMember("FOUR");

  @Test
  void testEquals() {

    Pair pair_ONE = Pair.of(one, two);
    Pair pair_TWO = Pair.of(one, two);

    assertEquals(pair_ONE, pair_TWO);
  }

  @Test
  void testHashcode() {

    Pair pair_ONE = Pair.of(one, two);
    Pair pair_TWO = Pair.of(one, two);

    assertEquals(pair_ONE.hashCode(), pair_TWO.hashCode());
  }

  @Test
  void testEquals_backwards() {

    Pair pair_ONE = Pair.of(one, two);
    Pair pair_TWO = Pair.of(two, one);

    assertEquals(pair_ONE, pair_TWO);
  }

  @Test
  void testHashcode_backwards() {

    Pair pair_ONE = Pair.of(one, two);
    Pair pair_TWO = Pair.of(two, one);

    assertEquals(pair_ONE.hashCode(), pair_TWO.hashCode());
  }

  @Test
  void testIsUnique() {

    Pair pair_ONE = Pair.of(one, two);
    Pair pair_TWO = Pair.of(three, four);

    assertTrue(Pair.hasUniqueMembers(pair_ONE, pair_TWO));
  }

  @Test
  void testIsUnique_false() {

    Pair pair_ONE = Pair.of(one, two);
    Pair pair_TWO = Pair.of(two, four);

    assertFalse(Pair.hasUniqueMembers(pair_ONE, pair_TWO));
  }

}