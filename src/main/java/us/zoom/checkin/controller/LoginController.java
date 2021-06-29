package us.zoom.checkin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import us.zoom.checkin.entity.Paper;
import us.zoom.checkin.entity.Question;
import us.zoom.checkin.mapper.PaperMapper;
import us.zoom.checkin.service.impl.PaperServiceImpl;
import us.zoom.checkin.service.impl.QuestionServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private PaperServiceImpl paperService;
    @Autowired
    private QuestionServiceImpl questionService;

    @RequestMapping("/test")
    public Paper login(int paperId){
        Paper paper = paperService.getById(paperId);
        List<Question> questions = questionService.list(new QueryWrapper<Question>().eq("paper_id",paperId));
        paper.setQuestions(questions);
        return paper;
    }
}
