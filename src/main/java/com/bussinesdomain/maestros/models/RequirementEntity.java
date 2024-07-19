package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "requirement")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequirementEntity {

    @Id
    @GeneratedValue(generator = "seqRequirement", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqRequirement", sequenceName = "requirement_seq", allocationSize = 1)
    @Column(name="id_requirement")
    private Long idRequirement;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description",nullable = false)
    private String description;

}
