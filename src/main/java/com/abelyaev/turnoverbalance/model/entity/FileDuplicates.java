package com.abelyaev.turnoverbalance.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "fileduplicates")
@Schema(description = "Информация о файле и дубликатах")
public class FileDuplicates {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fileduplicate_seq")
    @SequenceGenerator(sequenceName = "fileduplicate_seq", name = "fileduplicate_seq", allocationSize = 1)
    private Long id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "duplicates")
    private String duplicates;

    public FileDuplicates(String filename, String duplicates) {
        this.filename = filename;
        this.duplicates = duplicates;
    }
}
