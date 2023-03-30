package com.sequitur.api.DataCollection.domain.repository;

import com.sequitur.api.DataCollection.domain.model.Intent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntentRepository extends JpaRepository<Intent,Long> {
}
