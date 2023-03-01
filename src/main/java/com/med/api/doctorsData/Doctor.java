package com.med.api.doctorsData;

import com.med.api.enums.Specialty;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity(name = "doctor")
@Table(name = "Doctors")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String crm;
	@Enumerated(EnumType.STRING)
	private Specialty specialty;
	@Embedded
	private Address address;
	private Boolean ativo;
	
	public Doctor() {

	}

	public Doctor(Long id, String name, String email, String phone, String crm, Specialty specialty, Address address, Boolean ativo) {
		this.ativo = true;
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.crm = crm;
		this.specialty = specialty;
		this.address = address;
	}

	
	public Doctor(MedicalRecords dados) {
		this.ativo = true;
		this.name = dados.name();
		this.email = dados.email();
		this.phone = dados.phone();
		this.crm = dados.crm();
		this.specialty = dados.specialty();
		this.address = new Address(dados.address());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setActive(Boolean active) {
		this.ativo = active;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + "]";
	}

	public void updateData(@Valid UpdateData dados) {
		if(dados.name() != null) {
			this.name = dados.name();
		}
		if(dados.phone() != null) {
			this.phone = dados.phone();
		}
		if(dados.adress() != null) {
			this.address.updatephone(dados.adress());
		}
	}

	public void excluir() {
		this.ativo = false;
		
	}

}
