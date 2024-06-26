package com.legalsight.speech.repository;

import com.legalsight.speech.entity.SpeechEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeechRepository extends JpaRepository<SpeechEntity, Long>, JpaSpecificationExecutor<SpeechEntity> {
}