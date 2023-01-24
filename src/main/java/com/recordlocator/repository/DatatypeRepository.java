package com.recordlocator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recordlocator.entity.Datatype;

@Repository
public interface DatatypeRepository extends JpaRepository<Datatype, Long> {

}
