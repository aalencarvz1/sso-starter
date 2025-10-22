package com.oiis.sso_starter.database.entities.sso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oiis.sso_starter.database.entities.BaseEntityModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * base entity of sso
 *
 * @author aalencarvz1
 * @version 1.0.0
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseSsoEntityModel extends BaseEntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status_reg_id", nullable = false)
    private Long statusRegId = RecordStatus.ACTIVE;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_reg_id", insertable = false, updatable = false)
    private RecordStatus recordStatus;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

}

