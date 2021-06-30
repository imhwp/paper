package us.zoom.checkin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "paper")
public class Paper {

    @ApiModelProperty("问卷ID")
    @TableId(value = "id")
    private String paperId;

    @NotEmpty(message = "创建人不能为空")
    @ApiModelProperty("创建人ID")
    @TableField(value = "creator_id")
    private String creatorId;

    @NotEmpty(message = "问卷标题不能为空")
    @ApiModelProperty("问卷标题")
    @TableField(value = "title")
    private String title;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_date")
    private Date createDate;

    @NotNull(message = "问卷状态不能为空")
    @ApiModelProperty("问卷状态")
    @TableField(value = "status")
    private int status;

    @NotNull(message = "问卷开始时间不能为空")
    @ApiModelProperty("问卷开始时间")
    @TableField(value = "begin_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date beginTime;

    @NotNull(message = "问卷结束时间不能为空")
    @ApiModelProperty("问卷结束时间")
    @TableField(value = "end_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

    @NotEmpty(message = "问卷描述信息不能为空")
    @ApiModelProperty("问卷描述信息")
    @TableField(value = "detail")
    private String detail;

    @NotEmpty(message = "问卷问题不能为空")
    @ApiModelProperty("问卷对应的问题列表")
    @TableField(exist = false)
    private List<Question> questions;
}
