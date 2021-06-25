package us.zoom.checkin.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Record {
    private String respondentId;
    private String paperId;
    private String code;
    private Date answerTime;
    private Date claimTime;

}
