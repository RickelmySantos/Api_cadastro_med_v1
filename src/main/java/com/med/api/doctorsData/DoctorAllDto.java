package com.med.api.doctorsData;

import com.med.api.enums.Specialty;

public record DoctorAllDto(Long id, String name, String email, String phone, String crm, Specialty specialty, Address address) {
	
	public DoctorAllDto(Doctor doctor) {
		this(doctor.getId(), doctor.getName(),doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress());
	}

}
