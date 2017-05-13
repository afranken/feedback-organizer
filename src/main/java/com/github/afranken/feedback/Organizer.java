package com.github.afranken.feedback;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Organizer {

  private static final Logger logger = LoggerFactory.getLogger(Organizer.class);

  static Map<Slot, List<Pair>> organize(Input input) {
    return bruteForceOrganizer(input);
  }

  /**
   * brute force organizing the team until all slots match up...
   */
  private static Map<Slot, List<Pair>> bruteForceOrganizer(Input input) {
    int tries = 0;
    boolean allPairsMatchedUp = false;
    Map<Slot, List<Pair>> meetings = null;

    while (!allPairsMatchedUp) {
      meetings = input.organize();

      List<Slot> slots = input.getSlots();
      int fullSlots = 0;
      for (Slot slot : slots) {
        List<Pair> pairs = meetings.get(slot);
        if (pairs.size() == 4) {
          fullSlots++;
        }
      }

      if (fullSlots == slots.size()) {
        allPairsMatchedUp = true;
      } else {
        allPairsMatchedUp = false;
      }
      tries++;
    }
    logger.info("Organizing the team took {} tries.", tries);
    return meetings;
  }
}
