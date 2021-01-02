package com.contabancaria.PersonAPI.repository;

import com.contabancaria.PersonAPI.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
