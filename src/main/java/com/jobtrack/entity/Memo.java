package com.jobtrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String selfPr;
    private String motivation;
    private String memo;

    public Memo() {
    }

    public Long getId() {
        return id;
    }

    public String getSelfPr() {
        return selfPr;
    }

    public void setSelfPr(String selfPr) {
        this.selfPr = selfPr;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}