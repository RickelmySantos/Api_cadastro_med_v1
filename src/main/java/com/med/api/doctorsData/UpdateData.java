package com.med.api.doctorsData;

import jakarta.validation.constraints.NotNull;

public record UpdateData(
		@NotNull
		Long id,
		String name, 
		String phone, 
		AddressData adress) {

}
