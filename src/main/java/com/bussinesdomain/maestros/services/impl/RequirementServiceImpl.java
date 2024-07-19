package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.RequirementEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IRequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequirementServiceImpl extends CRUDImpl<RequirementEntity,Long> implements IRequirementService {
    private final IGenericRepository<RequirementEntity,Long> repository;

    @Override
    protected IGenericRepository<RequirementEntity, Long> getRepo() {
        return repository;
    }
    @Override
    public RequirementEntity update(RequirementEntity entity,Long id){
        RequirementEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idRequirement"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }
}
