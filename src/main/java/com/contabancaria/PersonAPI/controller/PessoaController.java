package com.contabancaria.PersonAPI.controller;

import com.contabancaria.PersonAPI.dto.request.PersonDTO;
import com.contabancaria.PersonAPI.dto.response.MessageDTO;
import com.contabancaria.PersonAPI.exception.PersonNotFoundException;
import com.contabancaria.PersonAPI.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PessoaController {

    @Autowired
    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.addPerson(personDTO);
    }
    @GetMapping
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }
    @PutMapping("/{id}")
    public MessageDTO updateById(@PathVariable Long id,@RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id,personDTO);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void delete(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }



}
