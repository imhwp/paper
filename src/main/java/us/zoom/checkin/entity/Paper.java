package us.zoom.checkin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

@Data
public class Paper {

    @ApiModelProperty("问卷ID")
    @Id
    @Column(name = "id")
    private String paperId;

    @ApiModelProperty("创建人ID")
    @Column(name = "creator_id")
    private String creatorId;

    @ApiModelProperty("问卷标题")
    @Column(name = "title")
    private String title;

    @ApiModelProperty("创建时间")
    @Column(name = "create_date")
    private Date createDate;

    @ApiModelProperty("问卷状态")
    @Column(name = "status")
    private int status;

    @ApiModelProperty("问卷开始时间")
    @Column(name = "begin_time")
    private Date beginTime;

    @ApiModelProperty("问卷结束时间")
    @Column(name = "end_time")
    private Date endTTime;

    @ApiModelProperty("问卷描述信息")
    @Column(name = "detail")
    private String detail;
}
