package com.sequitur.api.DataCollection.service;

import com.google.cloud.dialogflow.v2.CreateIntentRequest;
import com.google.cloud.dialogflow.v2.IntentsClient;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.TextInput;
import com.sequitur.api.DataCollection.domain.model.Intent;
import com.sequitur.api.DataCollection.domain.repository.IntentRepository;
import com.sequitur.api.DataCollection.domain.service.IntentService;
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
        try (IntentsClient intentsClient = IntentsClient.create()) {
            // Create the QueryInput object.

            // Convert the provided intent object to a Dialogflow intent object.
            com.google.cloud.dialogflow.v2.Intent dialogflowIntent = com.google.cloud.dialogflow.v2.Intent.newBuilder()
                    .setDisplayName(intent.getDisplayName())
                    .build();

            // Create the CreateIntentRequest object.
            CreateIntentRequest createIntentRequest = CreateIntentRequest.newBuilder()
                    .setParent("projects/sequitur-yqvh/agent")
                    .setIntent(dialogflowIntent)
                    .setLanguageCode("es")
                    .build();

            // Send the CreateIntentRequest to Dialogflow and return the created intent.
            com.google.cloud.dialogflow.v2.Intent createdIntent = intentsClient.createIntent(createIntentRequest);

            // Convert the created intent object back to a local intent object.
            com.sequitur.api.DataCollection.domain.model.Intent localIntent = new com.sequitur.api.DataCollection.domain.model.Intent();

            localIntent.setDisplayName(createdIntent.getDisplayName());


            // Save the created intent in your local database.
            intentRepository.save(localIntent);

            // Return the created intent.
            return localIntent;
        } catch (Exception e) {
            System.err.println("Error creating intent: " + e.getMessage());
            return null;
        }
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
