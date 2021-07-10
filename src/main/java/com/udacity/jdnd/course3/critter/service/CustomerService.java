package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.EntityNotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findById(Long customerId) {
        return customerRepository
                .findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find CUSTOMER with id: " + customerId));
    }

    public Customer findCustomerByPet(Long petId) {
        return customerRepository
                .findByPetId(petId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find CUSTOMER with pet id: " + petId));
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void addPetToCustomer(Pet pet, Customer customer) {
        Set<Pet> pets = customer.getPets();
        if (pets == null) {
            pets = new HashSet<>();
        }
        pets.add(pet);
        customer.setPets(pets);
        customerRepository.save(customer);
    }
}
