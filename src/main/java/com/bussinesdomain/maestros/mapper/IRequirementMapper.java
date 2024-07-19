package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.RequirementRequestDTO;
import com.bussinesdomain.maestros.dto.RequirementResponseDTO;
import com.bussinesdomain.maestros.models.RequirementEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IRequirementMapper {

    
    RequirementResponseDTO toGetDTO(RequirementEntity entity);

    @InheritInverseConfiguration
    RequirementEntity toEntity(RequirementRequestDTO dto);

    List<RequirementResponseDTO> listEntityToDTO(List<RequirementEntity> lst);
}
