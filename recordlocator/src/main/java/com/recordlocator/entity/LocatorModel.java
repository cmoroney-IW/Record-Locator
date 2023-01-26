package com.recordlocator.entity;

import java.time.LocalDateTime;

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
public class LocatorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rls_id")
    private long rlsId;

    @Column(name = "system_id", nullable = false)
    private int systemId;

    @Column(name = "data_type", nullable = false)
    private int dataType;

    @NonNull
    @Column(name = "patient_id", nullable = false)
    private String patientId;

    @NonNull
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
