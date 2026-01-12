package com.router.audit;



import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Auditable {

    @Column(name = "created_date", updatable = false)
    protected LocalDateTime createdDate;

    @Column(name = "created_by", updatable = false)
    protected Long createdBy;

    @Column(name = "updated_date")
    protected LocalDateTime updatedDate;

    @Column(name = "updated_by")
    protected Long updatedBy;

    @Column(name = "deleted_date")
    protected LocalDateTime deletedDate;

    @Column(name = "deleted_by")
    protected Long deletedBy;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
    
//    @PreUpdate
//    protected void onDelete() {
//        this.deletedDate = LocalDateTime.now();
//    }
    
    
}
