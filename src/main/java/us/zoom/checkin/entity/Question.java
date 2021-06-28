package us.zoom.checkin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

@Data
public class Question {
    @ApiModelProperty("问题ID")
    @Id
    @Column(name = "id")
    private String questionId;

    @ApiModelProperty("问卷ID")
    @Column(name = "paper_id")
    private String paperId;

    @ApiModelProperty("问题标题")
    @Column(name = "title")
    private String title;

    @ApiModelProperty("问题创建时间")
    @Column(name = "create_date")
    private Date createDate;

    @ApiModelProperty("问题类型")
    @Column(name = "type")
    private int type;

    @ApiModelProperty("问题选项")
    @Column(name = "option")
    private String option;
}
