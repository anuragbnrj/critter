package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final PetService petService;
    private final EmployeeService employeeService;
    private final CustomerService customerService;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           PetService petService,
                           EmployeeService employeeService,
                           CustomerService customerService) {
        this.scheduleRepository = scheduleRepository;
        this.petService = petService;
        this.employeeService = employeeService;
        this.customerService = customerService;
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findAllSchedulesForPet(Long petId) {
        Pet pet = petService.findById(petId);
        return scheduleRepository.findAllByPetsContains(pet);
    }

    public List<Schedule> findAllSchedulesForEmployee(Long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        return scheduleRepository.findAllByEmployeesContains(employee);
    }

    public List<Schedule> findAllSchedulesForCustomer(Long customerId) {
        Customer customer = customerService.findById(customerId);
        return scheduleRepository
                .findAllByPetsIn(
                        new ArrayList<>(customer.getPets())
                );
    }

    public Schedule saveSchedule(Schedule schedule, Set<Long> employeeIds, Set<Long> petIds) {
        Set<Employee> employees = employeeService.findAllByIdIn(new ArrayList<>(employeeIds));
        Set<Pet> pets = petService.findAllByIdIn(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepository.save(schedule);
    }
}
