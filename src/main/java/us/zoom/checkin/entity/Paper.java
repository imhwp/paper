package us.zoom.checkin.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Paper {
    private String paperId;
    private String creatorId;
    private String title;
    private Date createDate;
    private int status;
    private Date beginTime;
    private Date endTTime;
    private String detail;
}
