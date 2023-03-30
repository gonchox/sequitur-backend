package com.sequitur.api.DataCollection.domain.service;

import com.sequitur.api.DataCollection.domain.model.Intent;
import com.sequitur.api.DataCollection.domain.model.TrainingPhrase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TrainingPhraseService {

    ResponseEntity<?> deleteTrainingPhrase(Long trainingPhraseId, Long intentId);

    TrainingPhrase updateTrainingPhrase(Long trainingPhraseId,Long intentId, TrainingPhrase trainingPhraseRequest);

    TrainingPhrase createTrainingPhrase(Long intentId, TrainingPhrase trainingPhrase);

    TrainingPhrase getTrainingPhraseById(Long trainingPhraseId);

    Page<TrainingPhrase> getAllTrainingPhrases(Pageable pageable);

    Page<TrainingPhrase> getAllTrainingPhrasesByIntentId(Long intentId, Pageable pageable);

    TrainingPhrase getTrainingPhraseByIdAndIntentId(Long intentId, Long trainingPhraseId);
}
