package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	// Create operation
	public Person create(String firstName, String lastName, int age) {
		return personRepository.save(new Person(firstName, lastName, age));
	}

	// Retrieve operation
	public List<Person> getAll() {
		return personRepository.findAll();
	}

	public Optional<Person> getById(String id) {
		return personRepository.findById(id);
	}

//	public Person getByFirstName(String firstName) {
//		return personRepository.findByFirstName(firstName);
//	}

	// Update operation
	public void update(String id, String firstName, String lastName, int age) {
		Optional<Person> p = personRepository.findById(id);
		p.get().setFirstName(firstName);
		p.get().setLastName(lastName);
		p.get().setAge(age);
		personRepository.save(p.get());
	}
	
	public void update(Person p) {
		personRepository.save(p);
	}

	// Delete operation
	public void deleteAll() {
		personRepository.deleteAll();
	}

	public void delete(String id) {
		Optional<Person> p = personRepository.findById(id);
		personRepository.delete(p.get());
	}
}
