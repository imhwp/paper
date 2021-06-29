package us.zoom.checkin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import us.zoom.checkin.entity.Paper;
import us.zoom.checkin.entity.Question;
import us.zoom.checkin.mapper.PaperMapper;
import us.zoom.checkin.service.IPaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements IPaperService {

    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private QuestionServiceImpl questionService;

    public void savePaper(Paper paper){
        String paperId = UUID.randomUUID().toString().replace("-", "");
        paper.setPaperId(paperId);
        paper.setCreateDate(new Date());
        for(Question question:paper.getQuestions()){
            question.setPaperId(paperId);
        }
        paperMapper.insert(paper);
        questionService.saveOrUpdateBatch(paper.getQuestions());
    }

    public Paper findPaper(String paperId){
        Paper paper = paperMapper.selectById(paperId);
        if(paper == null) return null;
        List<Question> questions = questionService.list(new QueryWrapper<Question>().eq("paper_id", paperId));
        paper.setQuestions(questions);
        return paper;
    }

}
