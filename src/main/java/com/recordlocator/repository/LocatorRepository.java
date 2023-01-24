package com.recordlocator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recordlocator.entity.Locator;

@Repository
public interface LocatorRepository extends JpaRepository<Locator, Long> {

}
