package com.bussinesdomain.maestros.dto;

import com.bussinesdomain.maestros.constants.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class QuestionRequestDTO {

    @EqualsAndHashCode.Include
    private Long idQuestion;

    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idRequirement;
    private String requirementTitle;

    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idLevel;
    private String levelTitle;


    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
    private Long idTopic;
    private String topicTitle;

    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String statement;


    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String image;


    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String choice01;

    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String choice02;

    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String choice03;

    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String choice04;

    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String choice05;

    @NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
    private String choiceOK;
}
