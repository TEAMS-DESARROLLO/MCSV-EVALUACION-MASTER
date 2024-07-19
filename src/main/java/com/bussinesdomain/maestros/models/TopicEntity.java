package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "topic")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopicEntity {

    @Id
    @GeneratedValue(generator = "seqTopic", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqTopic", sequenceName = "topic_seq", allocationSize = 1)
    @Column(name="id_topic")
    private Long idTopic;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description",nullable = false)
    private String description;

}
