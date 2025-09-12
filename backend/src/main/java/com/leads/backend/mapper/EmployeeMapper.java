package com.leads.backend.mapper;

import com.leads.backend.dto.EmployeeDto;
import com.leads.backend.model.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeDto toDto(Employee employee);

  Employee toEntity(EmployeeDto dto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateEntityFromDto(EmployeeDto dto, @MappingTarget Employee employee);
}
