package com.microdevs.eventservice.data.entity;

import com.microdevs.baseservice.service.AuditModelBase;
import com.microdevs.eventservice.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "event", schema = "event")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event extends AuditModelBase implements Serializable {

    @Id
    @GeneratedValue(generator = "common_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "common_sequence", sequenceName = "common_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "location", nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EventStatus status;

    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

}
