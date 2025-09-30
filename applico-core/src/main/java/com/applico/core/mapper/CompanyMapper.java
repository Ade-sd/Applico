package com.applico.core.mapper;

import com.applico.core.domain.model.CompanyEntity;
import com.applico.core.dto.CompanyIn;
import com.applico.core.dto.CompanyOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyEntity inputToEntity(CompanyIn in);

    CompanyOut entityToOutput(CompanyEntity entity);

    CompanyEntity updateEntityFromInput(CompanyIn input, @MappingTarget CompanyEntity entity);

}
