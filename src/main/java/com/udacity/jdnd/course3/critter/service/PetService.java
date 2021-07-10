package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.EntityNotFoundException;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PetService {

    private final PetRepository petRepository;
    private final CustomerService customerService;

    public PetService(PetRepository petRepository, CustomerService customerService) {
        this.petRepository = petRepository;
        this.customerService = customerService;
    }

    public Pet findById(Long petId) {
        return petRepository
                .findById(petId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find PET with id: " + petId));
    }

    public Set<Pet> findAllByIdIn(Set<Long> petIds) {
        return petIds == null ? null : petRepository.findAllByIdIn(petIds);
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Set<Pet> findPetsByCustomerId(Long customerId) {
        return petRepository.findAllByCustomerId(customerId);
    }

    public Pet savePet(Pet pet, Long customerId) {
        Customer customer = customerService.findById(customerId);
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        customerService.addPetToCustomer(pet, customer);
        return pet;
    }
}
