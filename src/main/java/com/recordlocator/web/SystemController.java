package com.recordlocator.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recordlocator.entity.SystemModel;
import com.recordlocator.service.SystemServiceImpl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/system")
public class SystemController {

    SystemServiceImpl systemService;

    @GetMapping("/{id}")
    public ResponseEntity<SystemModel> getSystem(@PathVariable Long systemId) {
        return new ResponseEntity<>(systemService.getSystem(systemId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SystemModel>> getAllSystems() {
        return new ResponseEntity<>(systemService.getAllSystems(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SystemModel> createSystem(@javax.validation.Valid @RequestBody SystemModel system) {
        return new ResponseEntity<>(systemService.createSystem(system), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSystem(@PathVariable Long systemId) {
        systemService.deleteSystem(systemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}