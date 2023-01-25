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

import com.recordlocator.entity.LocatorModel;
import com.recordlocator.service.LocatorServiceImpl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/locator")
public class LocatorController {

    LocatorServiceImpl locatorService;

    @GetMapping("/{id}")
    public ResponseEntity<LocatorModel> getlocator(@PathVariable Long rlsId) {
        return new ResponseEntity<>(locatorService.getRecord(rlsId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LocatorModel>> getAlllocators() {
        return new ResponseEntity<>(locatorService.getAllRecords(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LocatorModel> createlocator(@javax.validation.Valid @RequestBody LocatorModel rls) {
        return new ResponseEntity<>(locatorService.createRecord(rls), HttpStatus.CREATED);
    }
}