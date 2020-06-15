package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;
	private String id;

	@RequestMapping("/create")
	public String create(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int age) {
		Person p = personService.create(firstName, lastName, age);
		return p.toString();
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String customerForm(Model model) {
		model.addAttribute("person", new Person());
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String customerSubmit(@ModelAttribute Person person, Model model) {
		model.addAttribute("person", person);
		personService.create(person.getFirstName(), person.getLastName(), person.getAge());
		return "result";
	}

//	@RequestMapping("/get")
//	public Person getPerson(@RequestParam String firstName) {
//		return personService.getByFirstName(firstName);
//	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("person", personService.getAll());
		return "index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, Model model) {
		model.addAttribute("person", personService.getById(id).get());
		setId(id);
		return "edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam String firstName,@RequestParam String lastName,@RequestParam int age ,@ModelAttribute Person person, Model model) {

		model.addAttribute("person", person);
		personService.update(getId(),firstName,lastName,age);
		return "redirect:/";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id, Model model) {
		personService.delete(id);
		return "redirect:/";
	}

	@RequestMapping("/deleteAll")
	public String deleteAll() {
		personService.deleteAll();
		return "Deleted all records";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
