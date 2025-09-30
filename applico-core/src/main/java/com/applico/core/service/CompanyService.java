package com.applico.core.service;

import com.applico.core.domain.model.CompanyEntity;
import com.applico.core.dto.CompanyIn;
import com.applico.core.dto.CompanyOut;
import com.applico.core.mapper.CompanyMapper;
import com.applico.core.service.repository.CompanyRepository;
import com.applico.core.utils.exception.SystemException;
import com.applico.core.utils.exception.statics.SystemError;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * Exceptions range start from 1000 to 1050
 */

@Service
public class CompanyService {

    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    public CompanyService(CompanyRepository repository, CompanyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CompanyOut> getAll() {
        List<CompanyEntity> entities = repository.findAll();
        return entities.stream().map(mapper::entityToOutput).toList();
    }

    public CompanyOut getById(Integer id) {
        CompanyEntity entity = getEntityById(id);
        return mapper.entityToOutput(entity);
    }

    public CompanyOut create(CompanyIn in) {
        CompanyEntity entity = mapper.inputToEntity(in);
        entity = repository.save(entity);
        return mapper.entityToOutput(entity);
    }

    public CompanyOut update(Integer id, CompanyIn in) {
        CompanyEntity entity = getEntityById(id);
        entity = mapper.updateEntityFromInput(in, entity);
        return mapper.entityToOutput(entity);
    }

    public void deleteById(Integer id) {
        CompanyEntity entity = getEntityById(id);
        repository.delete(entity);
    }

    private CompanyEntity getEntityById(Integer id) {
        Optional<CompanyEntity> entityMaybe = repository.findById(id);
        if (entityMaybe.isEmpty()) {
            throw new SystemException(SystemError.DATA_NOT_FOUND, "company not exists with following id: " + id, 1000);
        }
        return entityMaybe.get();
    }

}
