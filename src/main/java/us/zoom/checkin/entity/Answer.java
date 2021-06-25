package us.zoom.checkin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Table(name = "answer")
@ApiModel("答案实体")
public class Answer {

    @ApiModelProperty("答案ID")
    @Id
    @Column(name = "id")
    private String id;

    @ApiModelProperty("问卷ID")
    @Column(name = "paper_id")
    private String paperId;

    @ApiModelProperty("答案类型")
    @Column(name = "type")
    private int type;

    @ApiModelProperty("回答时间")
    @Column(name = "answer_date")
    private Date answerDate;

    @ApiModelProperty("答题结果")
    @Column(name = "option")
    private String option;

    @ApiModelProperty("所属问卷ID")
    @Column(name = "question_id")
    private String questionId;

    @ApiModelProperty("答题人ID")
    @Column(name = "respondent_id")
    private String respondentId;
}
