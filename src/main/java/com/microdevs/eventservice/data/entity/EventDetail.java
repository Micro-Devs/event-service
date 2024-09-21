package com.microdevs.eventservice.data.entity;

import com.microdevs.baseservice.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "event_detail", schema = "event")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventDetail extends BaseEntity {

    @Id
    @Column(name = "guid", nullable = false)
    private UUID guid;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
