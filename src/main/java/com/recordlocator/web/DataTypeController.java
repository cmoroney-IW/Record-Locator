package com.recordlocator.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recordlocator.entity.DataTypeModel;
import com.recordlocator.service.DataTypeServiceImpl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/datatype")
public class DataTypeController {

    DataTypeServiceImpl dataTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<DataTypeModel> getDataType(@PathVariable Long dataTypeId) {
        return new ResponseEntity<>(dataTypeService.getDataType(dataTypeId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DataTypeModel>> getAllDataTypes() {
        return new ResponseEntity<>(dataTypeService.getAllDataTypes(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DataTypeModel> createDataType(@javax.validation.Valid @RequestBody DataTypeModel dataType) {
        return new ResponseEntity<>(dataTypeService.createDataType(dataType), HttpStatus.CREATED);
    }
}