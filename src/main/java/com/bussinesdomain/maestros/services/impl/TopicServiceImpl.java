package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.TopicEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ITopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl extends CRUDImpl<TopicEntity,Long> implements ITopicService {
    private final IGenericRepository<TopicEntity,Long> repository;

    @Override
    protected IGenericRepository<TopicEntity, Long> getRepo() {
        return repository;
    }
    @Override
    public TopicEntity update(TopicEntity entity,Long id){
        TopicEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idTopic"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }
}
