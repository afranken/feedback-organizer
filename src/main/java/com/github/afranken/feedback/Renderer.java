package com.github.afranken.feedback;

import static freemarker.template.Configuration.VERSION_2_3_26;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.Map;

class Renderer {

  private Configuration configuration;

  Renderer() {
    this.configuration = initializeFreemarkerConfiguration();
  }

  void render(Map<String, Object> rootModel)
      throws IOException, URISyntaxException, TemplateException {
    Template rootTemplate = this.getRootTemplate();

    File file = new File(Renderer.class.getResource("/output/").getFile() + "index.html");
    file.createNewFile();
    Writer writer = getWriter(file);
    rootTemplate.process(rootModel, writer);
  }

  private Template getRootTemplate() throws URISyntaxException, IOException {
    String rootTemplateName = "output.ftl";
    Template template = null;
    File templateDirectory =
        new File(FeedbackOrganizer.class.getResource("/templates/").getFile());

    if (templateDirectory.isDirectory()) {
      this.configuration.setDirectoryForTemplateLoading(templateDirectory);
      if (new File(templateDirectory, rootTemplateName).exists()) {
        template = this.configuration.getTemplate(rootTemplateName);
      }
    }
    return template;
  }

  private static Writer getWriter(File file) throws IOException {
    return new FileWriter(file);
  }

  /**
   * Initialize Freemarker Configuration
   */
  private static Configuration initializeFreemarkerConfiguration() {

    Configuration configuration = new Configuration(VERSION_2_3_26);
    configuration.setDefaultEncoding("UTF-8");
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

    //make maps work in Freemarker when map key is not a String
    BeansWrapperBuilder beansWrapperBuilder = new BeansWrapperBuilder(VERSION_2_3_26);
    beansWrapperBuilder.setSimpleMapWrapper(true);
    BeansWrapper beansWrapper = beansWrapperBuilder.build();
    configuration.setObjectWrapper(beansWrapper);

    configuration.setAutoFlush(true);

    return configuration;
  }
}
