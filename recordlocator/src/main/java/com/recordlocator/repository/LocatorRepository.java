package com.recordlocator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recordlocator.entity.LocatorModel;

@Repository
public interface LocatorRepository extends JpaRepository<LocatorModel, Long> {

}
