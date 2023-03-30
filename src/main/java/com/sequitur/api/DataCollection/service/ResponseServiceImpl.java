package com.sequitur.api.DataCollection.service;

import com.sequitur.api.DataCollection.domain.model.Intent;
import com.sequitur.api.DataCollection.domain.model.Response;
import com.sequitur.api.DataCollection.domain.repository.IntentRepository;
import com.sequitur.api.DataCollection.domain.repository.ResponseRepository;
import com.sequitur.api.DataCollection.domain.service.ResponseService;
import com.sequitur.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private IntentRepository intentRepository;

    @Override
    public ResponseEntity<?> deleteResponse(Long responseId,Long intentId) {
        Response response = responseRepository.findById(responseId)
                .orElseThrow(() -> new ResourceNotFoundException("Response", "Id", responseId));
        responseRepository.delete(response);
        return ResponseEntity.ok().build();
    }

    @Override
    public Response updateResponse(Long responseId,Long intentId, Response responseRequest) {
        Response response = responseRepository.findById(responseId)
                .orElseThrow(() -> new ResourceNotFoundException("Response", "Id", responseId));
        response.setMessageText(responseRequest.getMessageText());

        return responseRepository.save(response);
    }

    @Override
    public Response createResponse(Long intentId, Response response) {
        Intent intent = intentRepository.findById(intentId).orElseThrow(() -> new ResourceNotFoundException("Intent", "Id", intentId));
        response.setIntent(intent);
        return responseRepository.save(response);
    }

    @Override
    public Response getResponseById(Long responseId) {
        return responseRepository.findById(responseId)
                .orElseThrow(() -> new ResourceNotFoundException("Response", "Id", responseId));
    }

    @Override
    public Page<Response> getAllResponses(Pageable pageable) {
        return responseRepository.findAll(pageable);
    }

    @Override
    public Page<Response> getAllResponsesByIntentId(Long intentId, Pageable pageable) {
        return responseRepository.findByIntentId(intentId, pageable);
    }

    @Override
    public Response getResponseByIdAndIntentId(Long intentId, Long responseId) {
        return responseRepository.findByIdAndIntentId(responseId, intentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Response not found with Id " + responseId +
                                " and IntentId " + intentId));
    }
}
