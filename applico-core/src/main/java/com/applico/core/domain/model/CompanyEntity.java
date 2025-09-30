package com.applico.core.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "company")
public class CompanyEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "founded")
    private Integer founded;

    @Column(name = "industry")
    private String industry;

    @Column(name = "about", length = 5000)
    private String about;

    @Column(name = "website")
    private String website;

    @Column(name = "headquarter")
    private String headquarter;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<JobApplicationEntity> jobApplications = new ArrayList<>();
}
