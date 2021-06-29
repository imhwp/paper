package us.zoom.checkin.mapper;

import org.springframework.stereotype.Component;
import us.zoom.checkin.entity.Answer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hank.huang
 * @since 2021-06-28
 */
@Component(value = "AnswerMapper")
public interface AnswerMapper extends BaseMapper<Answer> {

}
