package com.github.afranken.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Data;

@Data class Input {
  @JsonProperty
  private final List<Room> rooms;

  @JsonProperty
  private final List<TeamMember> people;

  @JsonProperty
  private final List<Slot> slots;

  private final Set<Pair> pairs = new HashSet<>();
  private static final TeamMember BREAK = new TeamMember("Coffee Break");

  /**
   * Shitty implementation for sorting pairs of team members to the available slots.
   * Which is not an easy problem because we want to have the maximum number of unique team
   * meetings per slot, and no pair should meet twice.
   */
  Map<Slot, List<Pair>> organize() {
    List<Pair> pairs = new ArrayList<>(this.getPairs());
    Map<Slot, List<Pair>> meetings = new LinkedHashMap<>();

    for (Slot slot : this.getSlots()) {
      List<Pair> pairsInSlot = new ArrayList<>();
      meetings.put(slot, pairsInSlot);
      //make sure to get a different order for the pairs every time we go through the loop
      Collections.shuffle(pairs);
      Iterator<Pair> iterator = pairs.iterator();
      pairs = new ArrayList<>();

      while (iterator.hasNext()) {
        Pair next = iterator.next();

        //definitely add the first pair.
        if (pairsInSlot.size() == 0) {
          pairsInSlot.add(next);
          continue;
        }
        //never add more teams than there are rooms.
        if (pairsInSlot.size() <= this.getRooms().size()) {
          int uniqueCounter = 0;
          for (Pair pair : pairsInSlot) {
            if (Pair.hasUniqueMembers(next, pair)) {
              uniqueCounter++;
            }
          }
          if (uniqueCounter == pairsInSlot.size()) {
            pairsInSlot.add(next);
            continue;
          }
        }

        //save pair for later if not added to current slot.
        pairs.add(next);
      }
    }
    return meetings;
  }

  /**
   * Pair up all team members
   */
  private Set<Pair> getPairs() {
    if (!pairs.isEmpty()) {
      return pairs;
    }

    //if team size is not even, one member needs to take a break on every slot.
    if (people.size() % 2 != 0) {
      people.add(BREAK);
    }

    for (TeamMember member1 : people) {
      for (TeamMember member2 : people) {
        if (!member1.equals(member2)) {
          pairs.add(Pair.of(member1, member2));
        }
      }
    }
    return pairs;
  }
}
