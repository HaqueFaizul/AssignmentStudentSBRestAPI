package com.example.demo;

import java.util.List;

//import java.util.List;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Service
public class StudentResource
{
	@Autowired
	StudentRepository repo;
	@GetMapping("Student")
	public List<Student> getAllStudent()
	{
		List<Student> std=(List<Student>) repo.findAll();
		
		return std;
	}
	@PostMapping("Student")
	public Student addStudent(@RequestBody Student s)
	{
		Student std=repo.save(s);
		return std;
	}
	@PutMapping("Student/{id}")
	public ResponseEntity<Student> editStudentDetail(@PathVariable(value="id") int id, @RequestBody Student s)
	{
		
		Student std=repo.findById(id).get();
		if(std==null) {
			return ResponseEntity.notFound().build();
		}
		std.setRoll_no(s.getRoll_no());
		std.setName(s.getName());
		std.setCls(s.getCls());
		std.setSec(s.getSec());
		std.setAddrs(s.getAddrs());
		Student std1=repo.save(std);
		return ResponseEntity.ok().body(std1);	
	}
	
	@DeleteMapping("Student/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable(value="id") int id)
	{
		
		Student std=repo.findById(id).get();
		if(std==null) {
			return ResponseEntity.notFound().build();
		}
		repo.delete(std);
		
		return ResponseEntity.ok().build();
		
		
	}
	
}
