package com.applico.core.domain.model;

import com.applico.core.domain.model.statics.ApplicationStatus;
import com.applico.core.domain.model.statics.ExperienceLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "job_application")
public class JobApplicationEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "about", nullable = false, length = 5000)
    private String about;

    @Enumerated(EnumType.STRING)
    @Column(name = "experience_level")
    private ExperienceLevel experienceLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status;

    @Column(name = "applied_at", nullable = false)
    private LocalDate appliedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;

    @OneToMany(mappedBy = "jobApplication", fetch = FetchType.LAZY)
    private List<InterviewEntity> interviews;

}
