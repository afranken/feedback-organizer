package com.github.afranken.feedback;

import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FeedbackOrganizer {

  private static final Logger logger = LoggerFactory.getLogger(FeedbackOrganizer.class);
  private static final ObjectMapper MAPPER = new ObjectMapper();

  public static void main(String[] args) throws IOException, URISyntaxException, TemplateException {
    Input input =
        MAPPER.readValue(FeedbackOrganizer.class.getResource("/input.json"), Input.class);

    Map<Slot, List<Pair>> slotsToPairs = input.organize();
    logger.info("Meeting output: {}", slotsToPairs);
    Map<String, Object> rootValue = new HashMap<>();
    rootValue.put("slotsToPairs", slotsToPairs);
    rootValue.put("rooms", input.getRooms());

    Renderer renderer = new Renderer();
    renderer.render(rootValue);
  }

}
