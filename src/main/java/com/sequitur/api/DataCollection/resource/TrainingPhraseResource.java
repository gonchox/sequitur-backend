package com.sequitur.api.DataCollection.resource;

import lombok.Data;

@Data
public class TrainingPhraseResource {
    private Long id;
    private Long intentId;
    private String text;
}
