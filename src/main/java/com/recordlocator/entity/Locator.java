package com.recordlocator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "locator")
public class Locator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "systemid", nullable = false)
    private int systemid;

    @Column(name = "datatype", nullable = false)
    private int datatype;

    @NonNull
    @Column(name = "patientid", nullable = false)
    private String patientid;
}
