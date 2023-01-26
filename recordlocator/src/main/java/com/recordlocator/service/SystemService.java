package com.recordlocator.service;

import java.util.List;

import com.recordlocator.entity.SystemModel;

public interface SystemService {

    SystemModel getSystem(Long systemId);

    List<SystemModel> getAllSystems();

    SystemModel createSystem(SystemModel system);

    void deleteSystem(Long systemId);
}
