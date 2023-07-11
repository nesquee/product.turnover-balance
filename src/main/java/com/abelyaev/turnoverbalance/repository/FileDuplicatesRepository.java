package com.abelyaev.turnoverbalance.repository;

import com.abelyaev.turnoverbalance.model.entity.FileDuplicates;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FileDuplicatesRepository extends CrudRepository<FileDuplicates, Long> {

    List<FileDuplicates> findFileDuplicatesByFilename(String fileName);

    @Query(value = "select * from fileduplicates where filename = ?1", nativeQuery = true)
    List<FileDuplicates> findFByFileName(String fileName);
}
