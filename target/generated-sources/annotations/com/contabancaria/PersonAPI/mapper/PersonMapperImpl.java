package com.contabancaria.PersonAPI.mapper;

import com.contabancaria.PersonAPI.dto.request.PersonDTO;
import com.contabancaria.PersonAPI.entities.Person;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-02T17:00:54-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.9.1 (Oracle Corporation)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person toModel(PersonDTO personDTO) {
        if ( personDTO == null ) {
            return null;
        }

        Person person = new Person();

        if ( personDTO.getBirthDate() != null ) {
            person.setBirthDate( LocalDate.parse( personDTO.getBirthDate(), DateTimeFormatter.ofPattern( "dd-MM-yyyy" ) ) );
        }
        person.setId( personDTO.getId() );
        person.setName( personDTO.getName() );
        person.setEmail( personDTO.getEmail() );
        person.setCpf( personDTO.getCpf() );

        return person;
    }

    @Override
    public PersonDTO toDTO(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setId( person.getId() );
        personDTO.setName( person.getName() );
        personDTO.setEmail( person.getEmail() );
        personDTO.setCpf( person.getCpf() );
        if ( person.getBirthDate() != null ) {
            personDTO.setBirthDate( DateTimeFormatter.ISO_LOCAL_DATE.format( person.getBirthDate() ) );
        }

        return personDTO;
    }
}
