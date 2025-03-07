package com.group2.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private int staffId;
    private int hospitalId;
    private String staffName;
    private String staffRole;
    private String staffContact;
    private String staffDepartment;
    private String staffStatus;
    private LocalDateTime staffCreatedAt;
    private LocalDateTime staffUpdatedAt;
}
