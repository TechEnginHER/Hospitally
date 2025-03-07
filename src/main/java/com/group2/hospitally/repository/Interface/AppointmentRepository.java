package com.group2.hospitally.repository.Interface;

import com.group2.hospitally.model.entity.Appointment;

import java.util.List;

public interface AppointmentRepository {

    Appointment getAppointmentById(int appointmentId);

    List<Appointment> getAllAppointments();

    List<Appointment> getAppointmentsByPatientId(int patientId);

    List<Appointment> getAppointmentsByStaffId(int staffId);

    Appointment createAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointment);

    int deleteAppointmentById(int appointmentId);
}
