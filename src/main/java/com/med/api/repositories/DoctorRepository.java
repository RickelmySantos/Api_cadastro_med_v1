package com.med.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.med.api.doctorsData.Doctor;

public interface  DoctorRepository extends JpaRepository<Doctor, Long> {

	
}
