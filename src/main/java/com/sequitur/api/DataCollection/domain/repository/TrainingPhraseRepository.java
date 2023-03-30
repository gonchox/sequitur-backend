package com.sequitur.api.DataCollection.domain.repository;

import com.sequitur.api.DataCollection.domain.model.Response;
import com.sequitur.api.DataCollection.domain.model.TrainingPhrase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingPhraseRepository extends JpaRepository<TrainingPhrase, Long> {

    Page<TrainingPhrase> findByIntentId(Long intentId, Pageable pageable);

    Optional<TrainingPhrase> findByIdAndIntentId(Long id, Long intentId);
}
