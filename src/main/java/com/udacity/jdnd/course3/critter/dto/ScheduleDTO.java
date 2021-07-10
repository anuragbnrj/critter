package com.udacity.jdnd.course3.critter.dto;

import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@Data
public class ScheduleDTO {

    private Long id;
    private Set<Long> employeeIds;
    private Set<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;

}
