package com.sequitur.api.DataCollection.domain.model;

import ch.qos.logback.classic.spi.LoggingEventVO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "intents")
public class Intent {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String displayName;

    @OneToMany(mappedBy = "intent", cascade = CascadeType.ALL)
    private List<TrainingPhrase> trainingPhrases;

    @OneToMany(mappedBy = "intent", cascade = CascadeType.ALL)
    private List<Response> responses;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<TrainingPhrase> getTrainingPhrases() {
        return trainingPhrases;
    }

    public void setTrainingPhrases(List<TrainingPhrase> trainingPhrases) {
        this.trainingPhrases = trainingPhrases;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

}
