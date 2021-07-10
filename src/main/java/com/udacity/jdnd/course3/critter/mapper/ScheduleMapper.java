package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleMapper {

    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(source = "employees", target = "employeeIds")
    @Mapping(source = "pets", target = "petIds")
    ScheduleDTO entityToDTO(Schedule schedule);

    Schedule dtoToEntity(ScheduleDTO scheduleDTO);

    default Long map(Employee employee) {
        return employee.getId();
    }

    default Long map(Pet pet) {
        return pet.getId();
    }

}
