package com.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.med.api.doctorsData.Doctor;
import com.med.api.doctorsData.DoctorAllDto;
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
	public ResponseEntity register(@RequestBody @Valid MedicalRecords dados, UriComponentsBuilder uriBuilder) {
		
		var doctor = new Doctor(dados);
		repository.save(doctor);
		
		var uri =uriBuilder.path("/doctos/{id}").buildAndExpand(doctor.getId()).toUri();
		return ResponseEntity.created(uri).body(new DoctorAllDto(doctor));
	}

	@GetMapping
	public ResponseEntity<Page<MedicalDoctors>> list(Pageable pagination) {
		var page = repository.findAllByAtivoTrue(pagination).map(MedicalDoctors::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity update(@RequestBody @Valid UpdateData dados) {
		var doctor = repository.getReferenceById(dados.id());
		doctor.updateData(dados);
		return ResponseEntity.ok(new DoctorAllDto(doctor));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable Long id) {
		var doctor = repository.getReferenceById(id);
		doctor.excluir();
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detailing(@PathVariable Long id) {
		
		var doctor = repository.getReferenceById(id);
		return ResponseEntity.ok(new DoctorAllDto(doctor));
	}
}
