package com.applico.core.mapper;

import com.applico.core.domain.model.JobApplicationEntity;
import com.applico.core.dto.JobApplicationIn;
import com.applico.core.dto.JobApplicationOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {
    JobApplicationEntity inputToEntity(JobApplicationIn in);

    JobApplicationOut entityToOutput(JobApplicationEntity entity);

    JobApplicationEntity updateEntityFromInput(JobApplicationIn input, @MappingTarget JobApplicationEntity entity);
}
