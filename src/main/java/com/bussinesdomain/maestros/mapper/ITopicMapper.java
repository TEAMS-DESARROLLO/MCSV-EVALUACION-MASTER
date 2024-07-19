package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.TopicRequestDTO;
import com.bussinesdomain.maestros.dto.TopicResponseDTO;
import com.bussinesdomain.maestros.models.TopicEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ITopicMapper {

    
    TopicResponseDTO toGetDTO(TopicEntity entity);

    @InheritInverseConfiguration
    TopicEntity toEntity(TopicRequestDTO dto);

    List<TopicResponseDTO> listEntityToDTO(List<TopicEntity> lst);
}
