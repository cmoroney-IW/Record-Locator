package com.recordlocator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recordlocator.entity.LocatorModel;
import com.recordlocator.repository.LocatorRepository;
import com.recordlocator.utils.NotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LocatorServiceImpl implements LocatorService {

    private LocatorRepository locatorRepository;

    @Override
    public LocatorModel getRecord(Long rlsId) {
        Optional<LocatorModel> locator = locatorRepository.findById(rlsId);
        return unwrapLocator(locator, rlsId);
    }

    @Override
    public List<LocatorModel> getAllRecords() {
        return locatorRepository.findAll();
    }

    @Override
    public LocatorModel createRecord(LocatorModel rls) {
        return locatorRepository.save(rls);
    }

    static LocatorModel unwrapLocator(Optional<LocatorModel> entity, Long rlsId) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new NotFoundException(rlsId);
    }

    @Override
    public void deleteRecord(Long rlsId) {
        locatorRepository.deleteById(rlsId);
    }
}
