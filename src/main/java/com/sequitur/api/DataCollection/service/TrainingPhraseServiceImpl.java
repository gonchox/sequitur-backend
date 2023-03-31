package com.sequitur.api.DataCollection.service;

import com.sequitur.api.DataCollection.domain.model.Intent;
import com.sequitur.api.DataCollection.domain.model.Response;
import com.sequitur.api.DataCollection.domain.model.TrainingPhrase;
import com.sequitur.api.DataCollection.domain.repository.IntentRepository;
import com.sequitur.api.DataCollection.domain.repository.TrainingPhraseRepository;
import com.sequitur.api.DataCollection.domain.service.TrainingPhraseService;
import com.sequitur.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrainingPhraseServiceImpl implements TrainingPhraseService {

    @Autowired
    private TrainingPhraseRepository trainingPhraseRepository;

    @Autowired
    private IntentRepository intentRepository;

    @Override
    public ResponseEntity<?> deleteTrainingPhrase(Long trainingPhraseId, UUID intentId) {
        TrainingPhrase trainingPhrase = trainingPhraseRepository.findById(trainingPhraseId)
                .orElseThrow(() -> new ResourceNotFoundException("TrainingPhrase", "Id", trainingPhraseId));
        trainingPhraseRepository.delete(trainingPhrase);
        return ResponseEntity.ok().build();
    }

    @Override
    public TrainingPhrase updateTrainingPhrase(Long trainingPhraseId, UUID intentId, TrainingPhrase trainingPhraseRequest) {
        TrainingPhrase trainingPhrase = trainingPhraseRepository.findById(trainingPhraseId)
                .orElseThrow(() -> new ResourceNotFoundException("TrainingPhrase", "Id", trainingPhraseId));
        trainingPhrase.setText(trainingPhraseRequest.getText());

        return trainingPhraseRepository.save(trainingPhrase);
    }

    @Override
    public TrainingPhrase createTrainingPhrase(UUID intentId, TrainingPhrase trainingPhrase) {
        Intent intent = intentRepository.findById(intentId).orElseThrow(() -> new ResourceNotFoundException("Intent", "Id", intentId));
        trainingPhrase.setIntent(intent);
        return trainingPhraseRepository.save(trainingPhrase);
    }

    @Override
    public TrainingPhrase getTrainingPhraseById(Long trainingPhraseId) {
        return trainingPhraseRepository.findById(trainingPhraseId)
                .orElseThrow(() -> new ResourceNotFoundException("TrainingPhrase", "Id", trainingPhraseId));
    }

    @Override
    public Page<TrainingPhrase> getAllTrainingPhrases(Pageable pageable) {
        return trainingPhraseRepository.findAll(pageable);
    }

    @Override
    public Page<TrainingPhrase> getAllTrainingPhrasesByIntentId(UUID intentId, Pageable pageable) {
        return trainingPhraseRepository.findByIntentId(intentId, pageable);
    }

    @Override
    public TrainingPhrase getTrainingPhraseByIdAndIntentId(UUID intentId, Long trainingPhraseId) {
        return trainingPhraseRepository.findByIdAndIntentId(trainingPhraseId, intentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "TrainingPhrase not found with Id " + trainingPhraseId +
                                " and IntentId " + intentId));
    }
}
