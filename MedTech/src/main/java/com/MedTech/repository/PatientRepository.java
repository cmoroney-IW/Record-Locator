package com.medtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medtech.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
