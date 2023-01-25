package com.recordlocator.service;

import java.util.List;

import com.recordlocator.entity.LocatorModel;

public interface LocatorService {

    LocatorModel getRecord(Long rlsId);

    List<LocatorModel> getAllRecords();

    LocatorModel createRecord(LocatorModel locator);

    void deleteRecord(Long rlsId);
}
