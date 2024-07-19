package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.constants.ResponseMessages;
import com.bussinesdomain.maestros.dto.TopicRequestDTO;
import com.bussinesdomain.maestros.dto.TopicResponseDTO;
import com.bussinesdomain.maestros.mapper.ITopicMapper;
import com.bussinesdomain.maestros.models.TopicEntity;
import com.bussinesdomain.maestros.services.ITopicService;
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
@Tag(name = "Topic", description = "Api for Topic")
@RestController
@RequiredArgsConstructor
@RequestMapping("/topic")
public class TopicController {

	private final ITopicMapper mapper;
	private final IPaginationCommons<TopicResponseDTO> paginationCommons;
	private final ITopicService service;
	
	@PostMapping("/pagination")
	public ResponseEntity<?> paginator(@RequestBody PaginationModel pagination){
		log.info("PAGINATION ..... " + pagination);
		Page<TopicResponseDTO> lst = paginationCommons.pagination(pagination);
		return new ResponseEntity<>(lst,HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<TopicResponseDTO>> findAll(){
		List<TopicEntity> topicsEntities = this.service.getAll();
		List<TopicResponseDTO> levelsDTO = this.mapper.listEntityToDTO(topicsEntities);
		return new ResponseEntity<>(levelsDTO,HttpStatus.OK);
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = ResponseMessages.CODE_404)
	})
	@GetMapping("/{id}")
	public ResponseEntity<TopicResponseDTO> findById(@PathVariable("id")Long id){
		TopicEntity topicEntity = this.service.readById(id);
		TopicResponseDTO levelDTO = this.mapper.toGetDTO(topicEntity);
		return new ResponseEntity<>(levelDTO,HttpStatus.OK);
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = ResponseMessages.CODE_201)
	})
	@PostMapping("/create")
	public ResponseEntity<TopicResponseDTO> save(@Validated @RequestBody TopicRequestDTO dto){
		TopicEntity topicEntity = this.service.create( this.mapper.toEntity(dto) );
		TopicResponseDTO levelDTO = this.mapper.toGetDTO(topicEntity);
		return new ResponseEntity<>(levelDTO,HttpStatus.CREATED);		
	}
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = ResponseMessages.CODE_404),
			@ApiResponse(responseCode = "200", description = ResponseMessages.CODE_200)
	})
	@PutMapping("/{id}")
	public ResponseEntity<TopicResponseDTO> update(@Validated @PathVariable("id") Long id,@RequestBody TopicRequestDTO dto){
		dto.setIdTopic(id);
		TopicEntity levelEntity = this.mapper.toEntity(dto);
		TopicEntity levelEntityUpdated = this.service.update(levelEntity, id);
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
