package com.example.konsi.mobil_computing_app;

/**
 * A Task represents a message for a patient including all information he needs to solve the task and experience progress in operation
 */
public class Task {

    private String taskID;
    private String patientID;
    private String doctorID;
    private String devicetype;
    private String times;
    private String from;
    private String to;
    private String start;
    private TaskStates state;

    public Task(String taskID, String patientID, String doctorID, String devicetype, String times, String from, String to, String start) {
        setTaskID(taskID);
        setPatientID(patientID);
        setDoctorID(doctorID);
        setDevicetype(devicetype);
        setTimes(times);
        setFrom(from);
        setTo(to);
        setStart(start);
        state = TaskStates.TASK_IN_PROGRESS;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public TaskStates getState() {
        return state;
    }

    public void setState(TaskStates state) {
        this.state = state;
    }
}
