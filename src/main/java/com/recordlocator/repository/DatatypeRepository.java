package com.recordlocator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recordlocator.entity.DataTypeModel;

@Repository
public interface DataTypeRepository extends JpaRepository<DataTypeModel, Long> {

}
