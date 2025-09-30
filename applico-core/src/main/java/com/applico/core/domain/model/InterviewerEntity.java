package com.applico.core.domain.model;

import com.applico.core.domain.model.statics.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "interviewer")
public class InterviewerEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "role")
    private String role;

    @Column(name = "hiring_manager")
    private boolean hiring_manager;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(mappedBy = "interviewers", fetch = FetchType.LAZY)
    private List<InterviewEntity> interviews = new ArrayList<>();
}
