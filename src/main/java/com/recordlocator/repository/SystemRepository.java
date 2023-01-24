package com.recordlocator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recordlocator.entity.System;

@Repository
public interface SystemRepository extends JpaRepository<System, Long> {

}
