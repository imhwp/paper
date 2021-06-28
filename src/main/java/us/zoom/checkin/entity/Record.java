package us.zoom.checkin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

@Data
public class Record {

    @ApiModelProperty("答卷记录ID")
    @Id
    @Column(name = "id")
    private String id;

    @ApiModelProperty("答卷人ID")
    @Column(name = "respondent_id")
    private String respondentId;

    @ApiModelProperty("问卷ID")
    @Column(name = "paper_id")
    private String paperId;

    @ApiModelProperty("验证码")
    @Column(name = "code")
    private String code;

    @ApiModelProperty("答卷时间")
    @Column(name = "answer_time")
    private Date answerTime;

    @ApiModelProperty("领取时间")
    @Column(name = "claim_time")
    private Date claimTime;

}
