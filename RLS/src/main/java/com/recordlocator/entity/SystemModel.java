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
@Table(name = "system")
public class SystemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "system_id")
    private long systemId;

    @Column(name = "third_party_id")
    private String thirdPartyId;

    @NonNull
    @Column(name = "source_sys", nullable = false)
    private String sourceSys;

    @NonNull
    @Column(name = "sys_type", nullable = false)
    private String sysType;

    @NonNull
    @Column(name = "loc_type", nullable = false)
    private String locType;

    @NonNull
    @Column(name = "location", nullable = false)
    private String location;

}
