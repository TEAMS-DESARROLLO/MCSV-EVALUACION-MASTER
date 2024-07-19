package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "level")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LevelEntity {

    @Id
    @GeneratedValue(generator = "seqLevel", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqLevel", sequenceName = "level_seq", allocationSize = 1)
    @Column(name="id_level")
    private Long idLevel;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description",nullable = false)
    private String description;

}
