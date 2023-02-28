package com.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.med.api.doctorsData.Doctor;
import com.med.api.doctorsData.MedicalDoctors;
import com.med.api.doctorsData.MedicalRecords;
import com.med.api.doctorsData.UpdateData;
import com.med.api.repositories.DoctorRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/doctors")
public class DoctorsController {

	@Autowired
	private DoctorRepository repository;

	@PostMapping
	@Transactional
	public void register(@RequestBody @Valid MedicalRecords dados) {
		repository.save(new Doctor(dados));
	}

	@GetMapping
	public Page<MedicalDoctors> list(Pageable pagination) {
		return repository.findAll(pagination).map(MedicalDoctors::new);
	}

	@PutMapping
	@Transactional
	public void update(@RequestBody @Valid UpdateData dados) {
		var doctor = repository.getReferenceById(dados.id());
		doctor.updateData(dados);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
