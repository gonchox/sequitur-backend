package com.sequitur.api.DataCollection.domain.service;

import com.sequitur.api.DataCollection.domain.model.Intent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IntentService {

    ResponseEntity<?> deleteIntent(Long intentId);

    Intent updateIntent(Long intentId, Intent intentRequest);

    Intent createIntent(Intent intent);

    Intent getIntentById(Long intentId);

    Page<Intent> getAllIntents(Pageable pageable);

}
