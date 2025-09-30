package com.applico.core.dto;

import com.applico.core.domain.model.statics.ApplicationStatus;
import com.applico.core.domain.model.statics.ExperienceLevel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationIn {
    @NotNull
    private String title;
    @NotNull
    @Size(max = 5000, message = "about cannot exceed 5000 characters")
    private String about;
    private ExperienceLevel experienceLevel;
    private ApplicationStatus status;
    @NotNull
    private LocalDate appliedAt;
    @NotNull
    private Integer companyId;
}
