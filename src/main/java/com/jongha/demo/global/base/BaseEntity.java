package com.jongha.demo.global.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity {

    @Column(insertable = false, updatable = false)
    protected LocalDateTime createdAt;

    @Column(insertable = false, updatable = false)
    protected LocalDateTime updatedAt;

}
