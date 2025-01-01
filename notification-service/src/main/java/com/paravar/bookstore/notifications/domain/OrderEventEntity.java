package com.paravar.bookstore.notifications.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_events")
/*
We are violating the Hexagonal Architecture here, by marking 'public' the entity.
 - but is is ok to use the Entity as a POJO, bcz we are not transferring this to end users ...in this service
*/
public class OrderEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_event_id_generator")
    @SequenceGenerator(name = "order_event_id_generator", sequenceName = "order_event_id_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    private String eventId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public OrderEventEntity() {}

    public OrderEventEntity(String eventId) {
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
