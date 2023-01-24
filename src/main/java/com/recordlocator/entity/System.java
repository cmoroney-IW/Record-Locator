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
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "third_party_id")
    private String third_party_id;

    @NonNull
    @Column(name = "source_sys", nullable = false)
    private String source_sys;

    @NonNull
    @Column(name = "sys_type", nullable = false)
    private String sys_type;

    @NonNull
    @Column(name = "loc_type", nullable = false)
    private String loc_type;

    @NonNull
    @Column(name = "location", nullable = false)
    private String location;

}
