package com.github.afranken.feedback;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Test;

class FeedbackOrganizerTest {

  /**
   * hack: run FeedbackOrganizer main method to generate HTML page during maven build
   */
  @Test
  void runFeedbackOrganizer() throws IOException, URISyntaxException, TemplateException {
    FeedbackOrganizer.main(new String[]{""});
  }
}