package com.recordlocator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recordlocator.entity.DataTypeModel;
import com.recordlocator.repository.DataTypeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DataTypeServiceImpl implements DataTypeService {

    private DataTypeRepository dataTypeRepository;

    public DataTypeModel getDataType(Long dataTypeId) {
        Optional<DataTypeModel> dataType = dataTypeRepository.findById(dataTypeId);
        if (dataType.isPresent()) {
            return dataType.get();
        } else {
            return null;
        }
    }

    @Override
    public List<DataTypeModel> getAllDataTypes() {
        return dataTypeRepository.findAll();
    }

    @Override
    public DataTypeModel createDataType(DataTypeModel dataType) {
        return dataTypeRepository.save(dataType);
    }

    @Override
    public void deleteDataType(Long dataTypeId) {
        dataTypeRepository.deleteById(dataTypeId);
    }

}
