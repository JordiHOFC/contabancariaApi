package com.contabancaria.PersonAPI.mapper;

import com.contabancaria.PersonAPI.dto.request.PersonDTO;
import com.contabancaria.PersonAPI.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel (PersonDTO personDTO);

    PersonDTO toDTO(Person person);

}
