package com.globant.internal.oncocureassist.repository.entity;

import com.globant.internal.oncocureassist.domain.dictionary.AuditAction;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_seq_generator")
    @SequenceGenerator(name = "audit_seq_generator", sequenceName = "audit_id_seq")
    private Long id;

    @NotNull
    @Column(name = "entity_id")
    private Long entityId;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private AuditAction action;

    @NotNull
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    private String content;


    public Long getId() {
        return id;
    }


    public Long getEntityId() {
        return entityId;
    }


    public AuditAction getAction() {
        return action;
    }


    public String getUserName() {
        return userName;
    }


    public LocalDateTime getCreatedDate() {
        return createdDate;
    }


    public String getContent() {
        return content;
    }


    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }


    public void setAction(AuditAction action) {
        this.action = action;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }


    public void setContent(String content) {
        this.content = content;
    }
}
