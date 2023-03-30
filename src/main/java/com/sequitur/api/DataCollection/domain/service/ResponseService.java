package com.sequitur.api.DataCollection.domain.service;

import com.sequitur.api.DataCollection.domain.model.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ResponseService {

    ResponseEntity<?> deleteResponse(Long responseId, Long intentId);

    Response updateResponse(Long responseId, Long intentId, Response responseRequest);

    Response createResponse(Long intentId, Response response);

    Response getResponseById(Long responseId);

    Page<Response> getAllResponses(Pageable pageable);

    Page<Response> getAllResponsesByIntentId(Long intentId, Pageable pageable);

    Response getResponseByIdAndIntentId(Long intentId, Long responseId);
}
