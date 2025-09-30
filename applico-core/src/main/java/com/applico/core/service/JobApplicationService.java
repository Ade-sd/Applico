package com.applico.core.service;

import com.applico.core.domain.model.JobApplicationEntity;
import com.applico.core.dto.JobApplicationIn;
import com.applico.core.dto.JobApplicationOut;
import com.applico.core.mapper.JobApplicationMapper;
import com.applico.core.service.repository.JobApplicationRepository;
import com.applico.core.utils.exception.SystemException;
import com.applico.core.utils.exception.statics.SystemError;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * Exceptions range start from 1051 to 1100
 */

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;
    private final JobApplicationMapper mapper;

    public JobApplicationService(JobApplicationRepository repository, JobApplicationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<JobApplicationOut> getAll() {
        List<JobApplicationEntity> entities = repository.findAll();
        return entities.stream().map(mapper::entityToOutput).toList();
    }

    public JobApplicationOut getById(Integer id) {
        JobApplicationEntity entity = getEntityById(id);
        return mapper.entityToOutput(entity);
    }

    public JobApplicationOut create(JobApplicationIn in) {
        JobApplicationEntity entity = mapper.inputToEntity(in);
        entity = repository.save(entity);
        return mapper.entityToOutput(entity);
    }

    public JobApplicationOut update(Integer id, JobApplicationIn in) {
        JobApplicationEntity entity = getEntityById(id);
        entity = mapper.updateEntityFromInput(in, entity);
        return mapper.entityToOutput(entity);
    }

    public void deleteById(Integer id) {
        JobApplicationEntity entity = getEntityById(id);
        repository.delete(entity);
    }

    private JobApplicationEntity getEntityById(Integer id) {
        Optional<JobApplicationEntity> entityMaybe = repository.findById(id);
        if (entityMaybe.isEmpty()) {
            throw new SystemException(SystemError.DATA_NOT_FOUND, "job application not exists with following id: " + id, 1051);
        }
        return entityMaybe.get();
    }

}
