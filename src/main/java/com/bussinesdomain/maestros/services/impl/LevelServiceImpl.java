package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.LevelEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ILevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl extends CRUDImpl<LevelEntity,Long> implements ILevelService {
    private final IGenericRepository<LevelEntity,Long> repository;

    @Override
    protected IGenericRepository<LevelEntity, Long> getRepo() {
        return repository;
    }
    @Override
    public LevelEntity update(LevelEntity entity,Long id){
        LevelEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idLevel"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }
}
