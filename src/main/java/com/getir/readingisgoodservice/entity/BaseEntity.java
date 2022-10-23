package com.getir.readingisgoodservice.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdDate;

    @Column(name = "updated_date", insertable = false)
    @UpdateTimestamp
    private OffsetDateTime updatedDate;
}
