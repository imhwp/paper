package us.zoom.checkin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.zoom.checkin.entity.Answer;
import us.zoom.checkin.entity.Paper;
import us.zoom.checkin.mapper.AnswerMapper;
import us.zoom.checkin.mapper.PaperMapper;

import java.util.Date;
import java.util.List;

@SpringBootTest
class CheckinApplicationTests {

    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private PaperMapper paperMapper;
    @Test
    void contextLoads() {
//        Paper paper = new Paper();
//        paper.setPaperId("1");
//        paper.setBeginTime(new Date(System.currentTimeMillis()));
//        paper.setCreateDate(new Date(System.currentTimeMillis()));
//        paper.setDetail("这是一个测试的问卷");
//        paper.setCreatorId("hank.huang");
//        paper.setEndTime(new Date(System.currentTimeMillis()));
//        paper.setStatus(1);
//        paper.setTitle("这是问卷标题");
//        paperMapper.insert(paper);
        Date date = new Date();
        System.out.println(date);
    }

}
