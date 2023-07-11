package com.abelyaev.turnoverbalance.repository;

import com.abelyaev.turnoverbalance.model.entity.Files;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilesRepository extends CrudRepository<Files, Long> {

    @Query(value = "select * from files where file = ?1", nativeQuery = true)
    List<Files> findFByFileName(String fileName);
}
