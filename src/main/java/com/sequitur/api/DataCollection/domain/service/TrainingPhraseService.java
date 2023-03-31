package com.sequitur.api.DataCollection.domain.service;

import com.sequitur.api.DataCollection.domain.model.Intent;
import com.sequitur.api.DataCollection.domain.model.TrainingPhrase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface TrainingPhraseService {

    ResponseEntity<?> deleteTrainingPhrase(Long trainingPhraseId, UUID intentId);

    TrainingPhrase updateTrainingPhrase(Long trainingPhraseId,UUID intentId, TrainingPhrase trainingPhraseRequest);

    TrainingPhrase createTrainingPhrase(UUID intentId, TrainingPhrase trainingPhrase);

    TrainingPhrase getTrainingPhraseById(Long trainingPhraseId);

    Page<TrainingPhrase> getAllTrainingPhrases(Pageable pageable);

    Page<TrainingPhrase> getAllTrainingPhrasesByIntentId(UUID intentId, Pageable pageable);

    TrainingPhrase getTrainingPhraseByIdAndIntentId(UUID intentId, Long trainingPhraseId);
}
