package com.recordlocator.service;

import java.util.List;

import com.recordlocator.entity.DataTypeModel;

public interface DataTypeService {

    DataTypeModel getDataType(Long dataTypeId);

    List<DataTypeModel> getAllDataTypes();

    DataTypeModel createDataType(DataTypeModel dataType);

    void deleteDataType(Long dataTypeId);
}
