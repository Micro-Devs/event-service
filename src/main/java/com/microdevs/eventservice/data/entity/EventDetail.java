package com.microdevs.eventservice.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "event_detail", schema = "event")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventDetail {

    @Id
    @Column(name = "guid", nullable = false)
    private UUID guid = UUID.randomUUID();

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
