package com.ollamates;
import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.LibraryModel;
import io.github.ollama4j.models.response.LibraryModelDetail;
import io.github.ollama4j.models.response.LibraryModelTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * mAKE SURE TO INCLUDE SLF4J Simple Logging Facade for Java (abbreviated SLF4J) acts as a facade for different logging frameworks
 * (e.g., java.util.logging, logback, Log4j). It offers a generic API, making the logging independent of the actual implementation.
 */


public class Main {

    private static final String HOST = "http://localhost:11434/";

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application started");
        ListModelsFromLibrary();
        try {
        GetLibraryModelTags();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            FindSpecificModel();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        }

    /**
     * This API Fetches the tags associated with a specific model from Ollama library.
     * @throws OllamaBaseException
     * @throws IOException
     * @throws URISyntaxException
     * @throws InterruptedException
     */
        public static void GetLibraryModelTags() throws OllamaBaseException, IOException, URISyntaxException, InterruptedException {


            OllamaAPI ollamaAPI = new OllamaAPI(HOST);

                List<LibraryModel> libraryModels = ollamaAPI.listModelsFromLibrary();

                LibraryModelDetail libraryModelDetail = ollamaAPI.getLibraryModelDetails(libraryModels.get(0));

                System.out.println(libraryModelDetail);

        }

    /**
     * This API fetches available models from the Ollama library page, including details such as the model's name,
     * pull count, popular tags, tag count, and the last update time.
     *
     */

    public static void ListModelsFromLibrary() {

        logger.debug("Debugging information");
        try {

            OllamaAPI ollamaAPI = new OllamaAPI(HOST);

            List<LibraryModel> libraryModels = ollamaAPI.listModelsFromLibrary();

            System.out.println(libraryModels);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("An error occurred", new RuntimeException("Test error"));
        }
    }

    public static void FindSpecificModel() throws OllamaBaseException, IOException, URISyntaxException, InterruptedException {

        OllamaAPI ollamaAPI = new OllamaAPI(HOST);

        LibraryModelTag libraryModelTag = ollamaAPI.findModelTagFromLibrary("qwen2.5", "7b");

        System.out.println(libraryModelTag);
    }
}