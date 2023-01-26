package com.medtech.service;

import java.util.List;

import com.medtech.entity.Patient;

public interface PatientService {
    Patient getPatient(Long patientId);

    List<Patient> getAllPatients();

    Patient createPatient(Patient patient);

    void deletePatient(Long patientId);
}
