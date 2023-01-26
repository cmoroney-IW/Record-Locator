package com.medtech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.medtech.entity.Patient;
import com.medtech.repository.PatientRepository;
import com.medtech.utils.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    @Override
    public Patient getPatient(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        return unwrapPatient(patient, patientId);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    static Patient unwrapPatient(Optional<Patient> entity, Long patientId) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new NotFoundException(patientId);
    }
}
