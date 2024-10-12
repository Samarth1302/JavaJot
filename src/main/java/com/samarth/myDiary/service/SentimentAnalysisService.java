package com.samarth.myDiary.service;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class SentimentAnalysisService {

    private final StanfordCoreNLP pipeline;

    public SentimentAnalysisService() {
        // Initialize the properties for the Stanford CoreNLP pipeline
        Properties props = new Properties();
        // Set the annotators to be used
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,sentiment");
        props.setProperty("outputFormat", "text");
        // Create the pipeline
        pipeline = new StanfordCoreNLP(props);
    }

    public String classifyText(String text) {
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);

        // Use a map to count occurrences of sentiments
        Map<String, Integer> sentimentCounts = new HashMap<>();
        for (CoreSentence sentence : document.sentences()) {
            String sentiment = sentence.sentiment();
            sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
        }

        // Find the maximum occurring sentiment
        return sentimentCounts.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Content"); // Default sentiment if none found
    }
}
