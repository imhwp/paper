package us.zoom.checkin.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Data
@ApiModel("答案实体")
@TableName("answer")
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @ApiModelProperty("答案ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Long answerId;

    @NotEmpty(message = "问卷描述信息不能为空")
    @ApiModelProperty("问题标题")
    @TableField(value = "title")
    private String title;


    @ApiModelProperty("答案类型")
    @NotNull(message = "答案类型不能为空")
    @TableField(value = "type")
    private int type;

    @ApiModelProperty("答题结果")
    @NotEmpty(message = "答案结果不能为空")
    @TableField(value = "answer_option")
    private String answerOption;

    @ApiModelProperty("答题人ID")
    @NotEmpty(message = "答题人不能为空")
    @TableField(value = "respondent_id")
    private String respondentId;

    @NotNull(message = "价值不能为空")
    @ApiModelProperty("价值")
    @TableField(value = "value")
    private double value;

    @ApiModelProperty("产品数量")
    @NotNull(message = "产品数量不能为空")
    @TableField(value = "amount")
    private int amount;

    @NotNull(message = "产品数量不能为空")
    @ApiModelProperty("问卷ID")
    @TableField(value = "paper_id")
    private String paperId;

    @NotNull(message = "产品数量不能为空")
    @ApiModelProperty("问题ID")
    @TableField(value = "question_id")
    private String questionId;

    @ApiModelProperty("是否是加购产品")
    @TableField(value = "additional_purchase")
    private int additionalPurchase;


}
