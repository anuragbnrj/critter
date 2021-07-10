package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final PetMapper petMapper;

    public PetController(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petMapper.dtoToEntity(petDTO);
        return petMapper
                .entityToDTO(
                        petService.savePet(pet, petDTO.getOwnerId())
                );
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable Long petId) {
        return petMapper.entityToDTO(petService.findById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService
                .findAll()
                .stream()
                .map(petMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable Long ownerId) {
        return petService
                .findPetsByCustomerId(ownerId)
                .stream()
                .map(petMapper::entityToDTO)
                .collect(Collectors.toList());
    }
}
