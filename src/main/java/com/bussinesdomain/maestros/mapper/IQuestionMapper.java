package com.bussinesdomain.maestros.mapper;


import com.bussinesdomain.maestros.dto.QuestionRequestDTO;
import com.bussinesdomain.maestros.dto.QuestionResponseDTO;
import com.bussinesdomain.maestros.models.QuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IQuestionMapper {
    @Mapping(target  = "idRequirement", ignore = true)
    @Mapping(target  = "requirementTitle", ignore = true)
    @Mapping(target  = "idLevel", ignore = true)
    @Mapping(target  = "levelTitle", ignore = true)
    @Mapping(target  = "idTopic", ignore = true)
    @Mapping(target  = "topicTitle", ignore = true)
    QuestionResponseDTO toGetResponseDTO(QuestionEntity entity);

    @Mapping(target = "requirement", ignore = true)
    @Mapping(target = "level",ignore = true)
    @Mapping(target = "topic",ignore = true)
    QuestionEntity toEntity(QuestionRequestDTO dto);
}
