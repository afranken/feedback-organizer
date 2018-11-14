package com.github.afranken.feedback;

import static com.google.common.base.Preconditions.checkArgument;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Input {
  @JsonProperty
  private List<Room> rooms;

  @JsonProperty
  private List<TeamMember> people;

  @JsonProperty
  private List<Slot> slots;

  private final Set<Pair> pairs = new HashSet<>();
  private static final TeamMember BREAK = new TeamMember("Coffee Break");

  public Input() {
  }

  Input(List<Room> rooms, List<TeamMember> people,
      List<Slot> slots) {
    this.rooms = rooms;
    this.people = people;
    this.slots = slots;
  }

  public List<Room> getRooms() {
    return rooms;
  }

  public List<TeamMember> getPeople() {
    return people;
  }

  public List<Slot> getSlots() {
    return slots;
  }

  /**
   * Uses the https://en.wikipedia.org/wiki/Round-robin_tournament algorithm as implemented
   * by Thorben (https://github.com/tguelck)
   */
  Map<Slot, List<Pair>> organize() {
    validate();
    Map<Slot, List<Pair>> meetings = new LinkedHashMap<>();

    final List<List<TeamMember>> partitions = Lists.partition(people, people.size() / 2);
    final List<TeamMember> upperRow = Lists.newArrayList(partitions.get(0));
    final List<TeamMember> lowerRow = Lists.newArrayList(partitions.get(1));

    for (Slot slot : this.getSlots()) {
        List<Pair> pairsInSlot = new ArrayList<>();
        for (int i = 0; i < upperRow.size(); i++) {
          pairsInSlot.add(new Pair(upperRow.get(i), lowerRow.get(i)));
        }
      meetings.put(slot, pairsInSlot);
      lowerRow.add(upperRow.remove(upperRow.size() - 1));
      upperRow.add(1, lowerRow.remove(0));
    }
    return meetings;
  }

  private void validate() {
    checkArgument(people.size() >= 4, "Minimum number of people is 4!");
    //if team size is not even, one member needs to take a break on every slot.
    if (people.size() % 2 != 0) {
      people.add(BREAK);
    }
    checkArgument(rooms.size() == people.size() / 2, "Wrong number of rooms! Must be half the number of people.");
    checkArgument(slots.size() == people.size() - 1, "Wrong number of slots! Must be one less than the number of people (including 'coffee' pseudo-person).");
  }
}
