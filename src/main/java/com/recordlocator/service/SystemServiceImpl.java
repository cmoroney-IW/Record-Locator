package com.recordlocator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recordlocator.entity.SystemModel;
import com.recordlocator.repository.SystemRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SystemServiceImpl implements SystemService {

    private SystemRepository systemRepository;

    public SystemModel getSystem(Long systemId) {
        Optional<SystemModel> system = systemRepository.findById(systemId);
        if (system.isPresent()) {
            return system.get();
        } else {
            return null;
        }
    }

    @Override
    public List<SystemModel> getAllSystems() {
        return systemRepository.findAll();
    }

    @Override
    public SystemModel createSystem(SystemModel system) {
        return systemRepository.save(system);
    }
}
