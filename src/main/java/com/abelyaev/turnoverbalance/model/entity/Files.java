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
@Table(name = "files")
@Schema(description = "Информация о файле и дубликатах")
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fileduplicate_seq")
    @SequenceGenerator(sequenceName = "fileduplicate_seq", name = "fileduplicate_seq", allocationSize = 1)
    private Long id;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "duplicates")
    private String duplicates;

    public Files(String fileName, String duplicates) {
        this.fileName = fileName;
        this.duplicates = duplicates;
    }
}
