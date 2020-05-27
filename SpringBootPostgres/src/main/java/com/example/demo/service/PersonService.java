package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;

/*
 *  All business logic will go here
 */

@Service
public class PersonService {
	
	private final PersonDao personDao;
	// just change the qualifier name with postgres, mysql etc. 
	// Keep postgres for getting from db - PersonDataAcessService
	// Keep fakeDao to get from memory
	@Autowired
	public PersonService(@Qualifier("postgres") PersonDao personDao) {
		//super();
		this.personDao = personDao;
	}
	
	public int addPerson (Person person) {
		return personDao.insertPerson(person);
	}

	public List<Person> getAllPeople(){
		return personDao.selectAllPeople();
	}

	public Optional<Person> getPersonById(UUID id){
		return personDao.selectPersonById(id);
	}
	
	public int deletePerson(UUID id) {
		return personDao.deletePersonById(id);
	}
	
	public int updatePerson(UUID id, Person newPerson) {
		return personDao.updatePersonById(id, newPerson);
	}
}
