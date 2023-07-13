package com.abelyaev.turnoverbalance.repository;

import com.abelyaev.turnoverbalance.model.entity.FilesEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilesRepository extends JpaRepository<FilesEntity, Integer> {

    @Query(value = "select * from files where file = ?1", nativeQuery = true)
    List<FilesEntity> findFByFileName(String fileName);
}
