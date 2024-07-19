package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.constants.ResponseMessages;
import com.bussinesdomain.maestros.dto.LevelRequestDTO;
import com.bussinesdomain.maestros.dto.LevelResponseDTO;
import com.bussinesdomain.maestros.mapper.ILevelMapper;
import com.bussinesdomain.maestros.models.LevelEntity;
import com.bussinesdomain.maestros.services.ILevelService;
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
@Tag(name = "Level", description = "Api for Level")
@RestController
@RequiredArgsConstructor
@RequestMapping("/level")
public class LevelController {

	private final ILevelMapper mapper;
	private final IPaginationCommons<LevelResponseDTO> paginationCommons;
	private final ILevelService service;
	
	@PostMapping("/pagination")
	public ResponseEntity<?> paginator(@RequestBody PaginationModel pagination){
		log.info("PAGINATION ..... " + pagination);
		Page<LevelResponseDTO> lst = paginationCommons.pagination(pagination);
		return new ResponseEntity<>(lst,HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<LevelResponseDTO>> findAll(){
		List<LevelEntity> levelsEntities = this.service.getAll();
		List<LevelResponseDTO> levelsDTO = this.mapper.listEntityToDTO(levelsEntities);
		return new ResponseEntity<>(levelsDTO,HttpStatus.OK);
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = ResponseMessages.CODE_404)
	})
	@GetMapping("/{id}")
	public ResponseEntity<LevelResponseDTO> findById(@PathVariable("id")Long id){
		LevelEntity levelEntity = this.service.readById(id);
		LevelResponseDTO levelDTO = this.mapper.toGetDTO(levelEntity);
		return new ResponseEntity<>(levelDTO,HttpStatus.OK);
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = ResponseMessages.CODE_201)
	})
	@PostMapping("/create")
	public ResponseEntity<LevelResponseDTO> save(@Validated @RequestBody LevelRequestDTO dto){
		LevelEntity levelEntity = this.service.create( this.mapper.toEntity(dto) );
		LevelResponseDTO levelDTO = this.mapper.toGetDTO(levelEntity);
		return new ResponseEntity<>(levelDTO,HttpStatus.CREATED);		
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = ResponseMessages.CODE_404),
			@ApiResponse(responseCode = "200", description = ResponseMessages.CODE_200)
	})
	@PutMapping("/{id}")
	public ResponseEntity<LevelResponseDTO> update(@Validated @PathVariable("id") Long id,@RequestBody LevelRequestDTO dto){
		dto.setIdLevel(id);
		LevelEntity levelEntity = this.mapper.toEntity(dto);
		LevelEntity levelEntityUpdated = this.service.update(levelEntity, id);
		return new ResponseEntity<>(this.mapper.toGetDTO(levelEntityUpdated),HttpStatus.OK);	
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
