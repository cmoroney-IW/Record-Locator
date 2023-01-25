package com.recordlocator.service;

import java.util.List;

import com.recordlocator.entity.Datatype;

public interface DatatypeService {
    List<Datatype> getDatatypes();

    Datatype getDatatypeById(Long id);
}
