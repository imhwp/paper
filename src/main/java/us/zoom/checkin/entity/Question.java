package us.zoom.checkin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.sql.Date;

@Data
@ApiModel("问题实体")
@TableName("question")
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @ApiModelProperty("问题ID")
    @TableId(value = "Id",type = IdType.AUTO)
    private Long questionId;

    @ApiModelProperty("问卷ID")
    @TableField(value = "paper_id")
    private String paperId;

    @ApiModelProperty("问题标题")
    @TableField(value = "title")
    private String title;


    @ApiModelProperty("问题类型")
    @TableField(value = "type")
    private int type;

    @ApiModelProperty("问题选项")
    @TableField(value = "question_option")
    private String questionOption;

    @ApiModelProperty("价值")
    @TableField(value = "value")
    private double value;

    @ApiModelProperty("是否是加购产品")
    @TableField(value = "additional_purchase")
    private int additionalPurchase;

    @ApiModelProperty("产品数量")
    @TableField(value = "amount")
    private int amount;

}
