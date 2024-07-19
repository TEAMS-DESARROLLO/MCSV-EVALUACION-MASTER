package com.bussinesdomain.maestros.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionEntity {
    @Id
    @GeneratedValue(generator = "seqQuestion", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqQuestion", sequenceName = "question_seq", allocationSize = 1)
    @Column(name="id_question")
    private Long idQuestion;


    @ManyToOne(optional = false,fetch= FetchType.EAGER)
    @JoinColumn(name="id_requirement",referencedColumnName="id_requirement")
    private RequirementEntity requirement;


    @ManyToOne(optional = false,fetch= FetchType.EAGER)
    @JoinColumn(name="id_level",referencedColumnName="id_level")
    private LevelEntity level;


    @ManyToOne(optional = false,fetch= FetchType.EAGER)
    @JoinColumn(name="id_topic",referencedColumnName="id_topic")
    private TopicEntity topic;

    @Column(name="statement",nullable = false)
    private String statement;


    @Column(name="image",nullable = false)
    private String image;


    @Column(name="choice_01",nullable = false)
    private String choice01;

    @Column(name="choice_02",nullable = false)
    private String choice02;
    @Column(name="choice_03",nullable = false)
    private String choice03;
    @Column(name="choice_04",nullable = false)
    private String choice04;
    @Column(name="choice_05",nullable = false)
    private String choice05;


    @Column(name="choice_ok",nullable = false)
    private String choiceOK;
}
