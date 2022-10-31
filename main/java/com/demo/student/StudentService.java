package com.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
	
	@Autowired
	private final StudentDAO studentDAO;
	
	public StudentService(StudentDAO studentDAO) {
		super();
		this.studentDAO = studentDAO;
	}



	public List<Student> getStudents() {
		
		return studentDAO.findAll();
	}



	public void addNewStudent(Student student) {
		
		Optional<Student> studentByEmail = studentDAO.findStudentByEmail(student.getEmail());
		
		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		
		studentDAO.save(student);
		System.out.println(student);
		
	}



	public void deleteStudent(Long studentId) {
		
		boolean existsById = studentDAO.existsById(studentId);
		
		if(!existsById) {
			throw new IllegalStateException("Student with id "+ studentId + "not exists");
		}
		
		studentDAO.deleteById(studentId);
		
		
		
	}


	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		
		Student student = studentDAO.findById(studentId).orElseThrow(() -> new IllegalStateException(
				"Student with id "+ studentId + " does not exists"));
		
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			
			student.setName(name);
		}
		
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			
			Optional<Student> studentByEmail = studentDAO.findStudentByEmail(email);
			if(studentByEmail.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			
			student.setEmail(email);
		}
		
	}

}
