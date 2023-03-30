package com.sequitur.api.DataCollection.domain.repository;

import com.sequitur.api.DataCollection.domain.model.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    Page<Response> findByIntentId(Long intentId, Pageable pageable);

    Optional<Response> findByIdAndIntentId(Long id, Long intentId);
}
