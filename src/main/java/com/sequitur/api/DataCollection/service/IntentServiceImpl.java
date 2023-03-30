package com.sequitur.api.DataCollection.service;

import com.sequitur.api.DataCollection.domain.model.Intent;
import com.sequitur.api.DataCollection.domain.repository.IntentRepository;
import com.sequitur.api.DataCollection.domain.service.IntentService;
import com.sequitur.api.IdentityAccessManagement.domain.model.University;
import com.sequitur.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IntentServiceImpl implements IntentService {

    @Autowired
    private IntentRepository intentRepository;

    @Override
    public ResponseEntity<?> deleteIntent(Long intentId) {
        Intent intent = intentRepository.findById(intentId).orElseThrow(() -> new ResourceNotFoundException("Intent", "Id", intentId));
        intentRepository.delete(intent);
        return ResponseEntity.ok().build();
    }

    @Override
    public Intent updateIntent(Long intentId, Intent intentRequest) {
        Intent intent = intentRepository.findById(intentId)
                .orElseThrow(() -> new ResourceNotFoundException("Intent", "Id", intentId));
        intent.setDisplayName(intentRequest.getDisplayName());

        return intentRepository.save(intent);
    }

    @Override
    public Intent createIntent(Intent intent) {
        return intentRepository.save(intent);
    }

    @Override
    public Intent getIntentById(Long intentId) {
        Intent intent = intentRepository.findById(intentId).orElseThrow(() -> new ResourceNotFoundException("Intent", "Id", intentId));
        return intent;
    }

    @Override
    public Page<Intent> getAllIntents(Pageable pageable) {
        return intentRepository.findAll(pageable);
    }
}
