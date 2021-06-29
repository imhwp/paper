package us.zoom.checkin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;


@Data
@ApiModel("答卷记录实体")
@TableName("record")
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    @ApiModelProperty("答卷记录ID")
    @TableId(value = "Id",type = IdType.AUTO)
    private String id;

    @ApiModelProperty("答卷人ID")
    private String respondentId;

    @ApiModelProperty("问卷ID")
    private String paperId;

    @ApiModelProperty("验证码")
    private String code;

    @ApiModelProperty("答卷时间")
    private Date answerTime;

    @ApiModelProperty("领取时间")
    private Date claimTime;

    @ApiModelProperty("加购账单")
    private double bill;


}
