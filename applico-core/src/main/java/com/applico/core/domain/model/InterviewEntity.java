package com.applico.core.domain.model;

import com.applico.core.domain.model.statics.InterviewStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "interview")
public class InterviewEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "hints", length = 1000)
    private String hints;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InterviewStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_application_id")
    private JobApplicationEntity jobApplication;

    @ManyToMany
    @JoinTable(
            name = "interview_interviewers",
            joinColumns = @JoinColumn(name = "interview_id"),
            inverseJoinColumns = @JoinColumn(name = "interviewer_id")
    )
    private List<InterviewerEntity> interviewers;


}
