package com.abelyaev.balance.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("files")
public class FilesEntity {
    @Id
    private Long id;
    private String filename;
    private String duplicates;
}
