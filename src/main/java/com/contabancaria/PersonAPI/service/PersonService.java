package com.contabancaria.PersonAPI.service;

import com.contabancaria.PersonAPI.dto.request.PersonDTO;
import com.contabancaria.PersonAPI.dto.response.MessageDTO;
import com.contabancaria.PersonAPI.exception.PersonNotFoundException;
import com.contabancaria.PersonAPI.mapper.PersonMapper;
import com.contabancaria.PersonAPI.entities.Person;
import com.contabancaria.PersonAPI.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService{
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    public MessageDTO addPerson(PersonDTO personDTO) {
        Person personToSave =personMapper.toModel(personDTO);
        Person personSaved = personRepository.save(personToSave);
        return MessageDTO.builder().message("Person with ID: "+personSaved.getId()+" is registered.").build();

    }

    public Person verifyExist(Long id) throws PersonNotFoundException {
        Person peopleExist =personRepository.findById(id).orElseThrow(()-> new PersonNotFoundException(id));
        return peopleExist;
    }

    public List<PersonDTO> listAll(){
        List<Person> allPeople= personRepository.findAll();
        return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        return personMapper.toDTO(verifyExist(id));
    }

    public MessageDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        Person peopleExist =verifyExist(id);
        Person peopleToUpdate=personMapper.toModel(personDTO);
        peopleToUpdate.setId(peopleExist.getId());
        personRepository.save(peopleToUpdate);
        return MessageDTO.builder().message("Person with ID: "+peopleToUpdate.getId()+" is update.").build();
    }
    public void delete(Long id) throws PersonNotFoundException {
        verifyExist(id);
        personRepository.deleteById(id);

    }

}
