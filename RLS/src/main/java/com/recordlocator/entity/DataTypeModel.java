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
@Table(name = "dataType")
public class DataTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private long typeId;

    @NonNull
    @Column(name = "type_desc", nullable = false)
    private String typeDesc;

    @Column(name = "rank", nullable = false)
    private int rank;
}
