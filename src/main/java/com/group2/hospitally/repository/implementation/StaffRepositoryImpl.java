package com.group2.hospitally.repository.implementation;

import com.group2.hospitally.repository.Interface.StaffRepository;
import com.group2.hospitally.repository.query.PatientQuery;
import com.group2.hospitally.repository.query.StaffQuery;
import com.group2.hospitally.model.entity.Staff;
import com.group2.hospitally.mapper.StaffRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffRepositoryImpl implements StaffRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StaffRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Staff getStaffById(int staffId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("staffId", staffId);
        return jdbcTemplate.queryForObject(StaffQuery.GET_STAFF_BY_ID, parameterSource, new StaffRowMapper());
    }

    @Override
    public List<Staff> getAllStaffs() {
        return jdbcTemplate.query(StaffQuery.GET_ALL_STAFFS, new StaffRowMapper());
    }
    @Override
    public List<Staff>getStaffByHospitalId(int hospitalId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("hospitalId", hospitalId);
        return jdbcTemplate.query(StaffQuery.GET_STAFF_BY_HOSPITAL_ID, parameterSource, new StaffRowMapper());
    }

    @Override
    public Staff createStaff(Staff staff) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("staffName", staff.getStaffName())
                .addValue("staffRole", staff.getStaffRole())
                .addValue("staffDepartment", staff.getStaffDepartment())
                .addValue("staffContact", staff.getStaffContact());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(StaffQuery.INSERT_STAFF, parameterSource, keyHolder, new String[]{"staff_id"});

        // Get the generated ID and set it in the patient object
        int staffId = keyHolder.getKey().intValue();
        staff.setStaffId(staffId);

        return staff;
    }

    @Override
    public Staff updateStaff(Staff staff) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("staffId", staff.getStaffId())
                .addValue("staffName", staff.getStaffName())
                .addValue("staffRole", staff.getStaffRole())
                .addValue("staffDepartment", staff.getStaffDepartment())
                .addValue("staffContact", staff.getStaffContact());
        jdbcTemplate.update(StaffQuery.UPDATE_STAFF_BY_ID, parameterSource);

        // Retrieve and return the updated staff
        return getStaffById(staff.getStaffId());
    }

    @Override
    public int deleteStaffById(int staffId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("staffId", staffId);
        return jdbcTemplate.update(StaffQuery.DELETE_STAFF_BY_ID, parameterSource);
    }
}
