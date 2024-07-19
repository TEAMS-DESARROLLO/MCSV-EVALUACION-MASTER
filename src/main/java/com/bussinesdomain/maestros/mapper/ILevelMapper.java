package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.LevelRequestDTO;
import com.bussinesdomain.maestros.dto.LevelResponseDTO;
import com.bussinesdomain.maestros.models.LevelEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ILevelMapper {

    
    LevelResponseDTO toGetDTO(LevelEntity entity);

    @InheritInverseConfiguration
    LevelEntity toEntity(LevelRequestDTO dto);

    List<LevelResponseDTO> listEntityToDTO(List<LevelEntity> lst);
}
