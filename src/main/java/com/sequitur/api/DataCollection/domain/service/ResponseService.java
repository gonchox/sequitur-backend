package com.sequitur.api.DataCollection.domain.service;

import com.sequitur.api.DataCollection.domain.model.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ResponseService {

    ResponseEntity<?> deleteResponse(Long responseId, UUID intentId);

    Response updateResponse(Long responseId, UUID intentId, Response responseRequest);

    Response createResponse(UUID intentId, Response response);

    Response getResponseById(Long responseId);

    Page<Response> getAllResponses(Pageable pageable);

    Page<Response> getAllResponsesByIntentId(UUID intentId, Pageable pageable);

    Response getResponseByIdAndIntentId(UUID intentId, Long responseId);
}
