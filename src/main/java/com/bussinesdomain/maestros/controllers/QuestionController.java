package com.bussinesdomain.maestros.controllers;


import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.constants.ResponseMessages;
import com.bussinesdomain.maestros.dto.*;
import com.bussinesdomain.maestros.dto.QuestionResponseDTO;
import com.bussinesdomain.maestros.mapper.IQuestionMapper;
import com.bussinesdomain.maestros.models.*;
import com.bussinesdomain.maestros.services.ILevelService;
import com.bussinesdomain.maestros.services.IQuestionService;
import com.bussinesdomain.maestros.services.IRequirementService;
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

@Slf4j
@Tag(name = "Question", description = "Api for Question")
@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {


    private final IQuestionService collaboratorService;
    private final IQuestionMapper collaboratorMapper;


    private final ILevelService levelService;
    private final IRequirementService requirementService;
    private final ITopicService topicService;

    private final IPaginationCommons<QuestionResponseDTO> iPaginationCommons;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador(@RequestBody PaginationModel pagination ){
        Page<QuestionResponseDTO> lst = iPaginationCommons.pagination(pagination);
        return new ResponseEntity<>(lst, HttpStatus.OK) ;
    }
    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = ResponseMessages.CODE_404)
    })
    @GetMapping("/{idQuestion}")
    public ResponseEntity<QuestionResponseDTO> findById(@PathVariable("idQuestion") Long idQuestion){

        QuestionEntity obj = this.collaboratorService.readById(idQuestion);
        QuestionResponseDTO dto = this.collaboratorMapper.toGetResponseDTO(obj);


        dto.setIdLevel(obj.getLevel().getIdLevel());
        dto.setLevelTitle(obj.getLevel().getTitle());

        dto.setIdTopic(obj.getTopic().getIdTopic());
        dto.setTopicTitle(obj.getTopic().getTitle());

        dto.setIdRequirement(obj.getRequirement().getIdRequirement());
        dto.setRequirementTitle(obj.getRequirement().getTitle());


        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ResponseMessages.CODE_201)
    })
    @PostMapping("/create")
    public ResponseEntity<QuestionResponseDTO> save(@Validated @RequestBody QuestionRequestDTO requestDTO) {
        LevelEntity levelEntity =  this.levelService.readById(requestDTO.getIdLevel());

        TopicEntity topicEntity = this.topicService.readById(requestDTO.getIdTopic());

        RequirementEntity requirementEntity = this.requirementService.readById(requestDTO.getIdRequirement());

        QuestionEntity entidad = this.collaboratorMapper.toEntity(requestDTO);

        entidad.setLevel(levelEntity);
        entidad.setTopic(topicEntity);
        entidad.setRequirement(requirementEntity);

        QuestionEntity entidadSave = this.collaboratorService.create( entidad);
        QuestionResponseDTO responseviaDTO = this.collaboratorMapper.toGetResponseDTO(entidadSave);


        responseviaDTO.setIdLevel(entidadSave.getLevel().getIdLevel());
        responseviaDTO.setLevelTitle(entidadSave.getLevel().getTitle());

        responseviaDTO.setIdTopic(entidadSave.getTopic().getIdTopic());
        responseviaDTO.setTopicTitle(entidadSave.getTopic().getTitle());

        responseviaDTO.setIdRequirement(entidadSave.getRequirement().getIdRequirement());
        responseviaDTO.setRequirementTitle(entidadSave.getRequirement().getTitle());

        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = ResponseMessages.CODE_404),
            @ApiResponse(responseCode = "200", description = ResponseMessages.CODE_200)
    })
    @PutMapping("/{idQuestion}")
    public ResponseEntity<QuestionResponseDTO> update(@Validated @PathVariable("idQuestion") Long idQuestion,
                                                          @RequestBody QuestionRequestDTO requestDTO){
        LevelEntity levelEntity =  this.levelService.readById(requestDTO.getIdLevel());

        TopicEntity topicEntity = this.topicService.readById(requestDTO.getIdTopic());

        RequirementEntity requirementEntity = this.requirementService.readById(requestDTO.getIdRequirement());


        QuestionEntity objEntitySource = this.collaboratorMapper.toEntity(requestDTO);

        objEntitySource.setLevel(levelEntity);
        objEntitySource.setTopic(topicEntity);
        objEntitySource.setRequirement(requirementEntity);

        QuestionEntity obj =  collaboratorService.update(objEntitySource, idQuestion);
        QuestionResponseDTO responseviaDTO = this.collaboratorMapper.toGetResponseDTO(obj);

        responseviaDTO.setIdLevel(obj.getLevel().getIdLevel());
        responseviaDTO.setLevelTitle(obj.getLevel().getTitle());

        responseviaDTO.setIdTopic(obj.getTopic().getIdTopic());
        responseviaDTO.setTopicTitle(obj.getTopic().getTitle());

        responseviaDTO.setIdRequirement(obj.getRequirement().getIdRequirement());
        responseviaDTO.setRequirementTitle(obj.getRequirement().getTitle());

        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = ResponseMessages.CODE_204),
            @ApiResponse(responseCode = "404", description = ResponseMessages.CODE_404)
    })
    @DeleteMapping("/{idQuestion}")
    public ResponseEntity<QuestionResponseDTO> delete(@PathVariable("idQuestion") Long idQuestion){
        collaboratorService.deleteById(idQuestion);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    
}
