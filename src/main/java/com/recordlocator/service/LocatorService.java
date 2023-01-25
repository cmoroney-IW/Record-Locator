package com.recordlocator.service;

import java.util.List;

import com.recordlocator.entity.LocatorModel;

public interface LocatorService {

    public LocatorModel getRecord(Long rlsId);

    List<LocatorModel> getAllRecords();

    LocatorModel createRecord(LocatorModel locator);
}
