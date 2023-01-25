package com.recordlocator.service;

import java.util.List;

import com.recordlocator.entity.DataTypeModel;

public interface DataTypeService {

    public DataTypeModel getDataType(Long dataTypeId);

    List<DataTypeModel> getAllDataTypes();

    DataTypeModel createDataType(DataTypeModel dataType);
}
