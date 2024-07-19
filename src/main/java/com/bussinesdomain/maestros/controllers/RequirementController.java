package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.constants.ResponseMessages;
import com.bussinesdomain.maestros.dto.RequirementRequestDTO;
import com.bussinesdomain.maestros.dto.RequirementResponseDTO;
import com.bussinesdomain.maestros.mapper.IRequirementMapper;
import com.bussinesdomain.maestros.models.RequirementEntity;
import com.bussinesdomain.maestros.services.IRequirementService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "Requirement", description = "Api for Requirement")
@RestController
@RequiredArgsConstructor
@RequestMapping("/requirement")
public class RequirementController {

	private final IRequirementMapper mapper;
	private final IPaginationCommons<RequirementResponseDTO> paginationCommons;
	private final IRequirementService service;
	
	@PostMapping("/pagination")
	public ResponseEntity<?> paginator(@RequestBody PaginationModel pagination){
		log.info("PAGINATION ..... " + pagination);
		Page<RequirementResponseDTO> lst = paginationCommons.pagination(pagination);
		return new ResponseEntity<>(lst,HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<RequirementResponseDTO>> findAll(){
		List<RequirementEntity> requirementsEntities = this.service.getAll();
		List<RequirementResponseDTO> requirementsDTO = this.mapper.listEntityToDTO(requirementsEntities);
		return new ResponseEntity<>(requirementsDTO,HttpStatus.OK);
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = ResponseMessages.CODE_404)
	})
	@GetMapping("/{id}")
	public ResponseEntity<RequirementResponseDTO> findById(@PathVariable("id")Long id){
		RequirementEntity requirementEntity = this.service.readById(id);
		RequirementResponseDTO requirementDTO = this.mapper.toGetDTO(requirementEntity);
		return new ResponseEntity<>(requirementDTO,HttpStatus.OK);
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = ResponseMessages.CODE_201)
	})
	@PostMapping("/create")
	public ResponseEntity<RequirementResponseDTO> save(@Validated @RequestBody RequirementRequestDTO dto){
		RequirementEntity requirementEntity = this.service.create( this.mapper.toEntity(dto) );
		RequirementResponseDTO requirementDTO = this.mapper.toGetDTO(requirementEntity);
		return new ResponseEntity<>(requirementDTO,HttpStatus.CREATED);		
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = ResponseMessages.CODE_404),
			@ApiResponse(responseCode = "200", description = ResponseMessages.CODE_200)
	})
	@PutMapping("/{id}")
	public ResponseEntity<RequirementResponseDTO> update(@Validated @PathVariable("id") Long id,@RequestBody RequirementRequestDTO dto){
		dto.setIdRequirement(id);
		RequirementEntity requirementEntity = this.mapper.toEntity(dto);
		RequirementEntity requirementEntityUpdated = this.service.update(requirementEntity, id);
		return new ResponseEntity<>(this.mapper.toGetDTO(requirementEntityUpdated),HttpStatus.OK);	
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = ResponseMessages.CODE_204),
			@ApiResponse(responseCode = "404", description = ResponseMessages.CODE_404)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id ){
		this.service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
