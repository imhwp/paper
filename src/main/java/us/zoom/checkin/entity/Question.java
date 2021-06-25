package us.zoom.checkin.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Question {
    private String questionId;
    private String paperId;
    private String title;
    private Date createDate;
    private int type;
    private String option;
}
