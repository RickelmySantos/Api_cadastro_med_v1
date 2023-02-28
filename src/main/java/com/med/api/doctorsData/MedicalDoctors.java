package com.med.api.doctorsData;

import com.med.api.enums.Specialty;

public record MedicalDoctors(Long id, String name, String email, String crm, Specialty specialty) {

	public MedicalDoctors(Doctor doctor) {
		this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
	}
}
