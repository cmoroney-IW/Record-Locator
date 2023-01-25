package com.recordlocator.service;

import java.util.List;

import com.recordlocator.entity.SystemModel;

public interface SystemService {

    public SystemModel getSystem(Long systemId);

    List<SystemModel> getAllSystems();

    SystemModel createSystem(SystemModel system);

}
