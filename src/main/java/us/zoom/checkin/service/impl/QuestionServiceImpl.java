package us.zoom.checkin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import us.zoom.checkin.entity.Paper;
import us.zoom.checkin.entity.Question;
import us.zoom.checkin.mapper.PaperMapper;
import us.zoom.checkin.mapper.QuestionMapper;
import us.zoom.checkin.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hank.huang
 * @since 2021-06-28
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {



}
