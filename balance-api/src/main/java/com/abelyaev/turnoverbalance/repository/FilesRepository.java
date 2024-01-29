package com.abelyaev.turnoverbalance.repository;

import com.abelyaev.turnoverbalance.model.entity.FilesEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface FilesRepository extends ReactiveCrudRepository<FilesEntity, Long> {

    Mono<FilesEntity> findByFilename(String filename);

}
