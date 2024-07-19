package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.QuestionEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends CRUDImpl<QuestionEntity,Long> implements IQuestionService {
    private final IGenericRepository<QuestionEntity,Long> repository;


    @Override
    protected IGenericRepository<QuestionEntity, Long> getRepo() {
        return repository;
    }

    @Override
    public QuestionEntity update(QuestionEntity entity, Long id){
        QuestionEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idQuestion"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }
}
