package com.med.api.doctorsData;

import com.med.api.enums.Specialty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicalRecords(
		@NotBlank
		String name,
		@NotBlank
		@Email
		String email,
		@NotBlank
		String phone,
		@NotBlank
		String crm,
		@NotNull
		Specialty specialty,
		@NotNull @Valid
		AddressData address) 
{

	
}
