package com.example.konsi.mobil_computing_app;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Message {

    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo
    private String messageContent;
    @ForeignKey(entity = Doctor.class, parentColumns = "id", childColumns = "doctorId")
    private String doctorId;
    @ForeignKey(entity = Patient.class, parentColumns = "id", childColumns = "patientId")
    private String patientId;

    public Message(String id, String messageContent, String doctorId, String patientId){
        setId(id);
        setMessageContent(messageContent);
        setDoctorId(doctorId);
        setPatientId(patientId);
    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageContent() { return messageContent; }

    public void setMessageContent(String messageContent) { this.messageContent = messageContent; }

    public String getDoctorId() { return doctorId; }

    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public String getPatientId() { return patientId; }

    public void setPatientId(String patientId) { this.patientId = patientId; }


}
