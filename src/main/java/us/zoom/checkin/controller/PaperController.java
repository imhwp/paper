package us.zoom.checkin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import us.zoom.checkin.Exception.APIException;
import us.zoom.checkin.entity.Answer;
import us.zoom.checkin.entity.Paper;
import us.zoom.checkin.entity.Record;
import us.zoom.checkin.service.impl.AnswerServiceImpl;
import us.zoom.checkin.service.impl.PaperServiceImpl;
import us.zoom.checkin.service.impl.QuestionServiceImpl;
import us.zoom.checkin.service.impl.RecordServiceImpl;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/paper")
@Api(tags = "问卷相关接口")
public class PaperController {
    @Autowired
    PaperServiceImpl paperService;
    @Autowired
    QuestionServiceImpl questionService;
    @Autowired
    AnswerServiceImpl answerService;
    @Autowired
    RecordServiceImpl recordService;

    private static final int ADDITIONAL_PURCHASE = 1;

    @ApiOperation("获取所有问卷信息")
    @PostMapping("/list")
    public List<Paper> getAllPaper(){
        List<Paper> paperList = paperService.list();
        return paperList;
    }

    @ApiOperation("获取指定问卷信息")
    @PostMapping("/get")
    public Paper getOnePaper(String paperId){
        if(Strings.isNullOrEmpty(paperId)){
            log.error("问卷ID为空");
            throw new APIException("请求参数非法");
        }
        Paper paper = paperService.findPaper(paperId);
        return paper;
    }

    @ApiOperation("创建新问卷")
    @PostMapping("/create")
    public String createPaper(@RequestBody @Valid Paper paper, BindingResult result){
        for (ObjectError error:result.getAllErrors()){
            return error.getDefaultMessage();
        }
        if(paper.getEndTime().before(paper.getBeginTime()) || paper.getEndTime().before(new Date(System.currentTimeMillis()))){
            throw new APIException("问卷截止时间非法");
        }
        paperService.savePaper(paper);
        return "success";
    }

    @ApiOperation("提交问卷答题")
    @PostMapping("/submit")
    public String submitPaperAnswer(@RequestBody Map<String,Object> params) throws IOException {
        List<Map> answersMap = (List<Map>) params.get("answers");
        String respondentId = (String) params.get("respondentId");
        String paperId = (String) params.get("paperId");
        if(answersMap == null || answersMap.size() == 0){
            throw new APIException("提交信息不能为空");
        }
        if(recordService.getOne(new QueryWrapper<Record>().eq("paper_id",paperId).eq("respondent_id",respondentId))!=null){
            throw new APIException("您已经登记过该表单，请勿重复提交");
        }
        Record record = new Record();
        record.setAnswerTime(new Date());
        record.setPaperId(paperId);
        record.setRespondentId(respondentId);
        double bill = 0;
        ObjectMapper mapper = new ObjectMapper();
        List<Answer> answers = new ArrayList<>();
        for(Map answerMap:answersMap){
            Answer answer = mapper.readValue(mapper.writeValueAsString(answerMap), Answer.class);
            answer.setRespondentId(respondentId);
            answer.setPaperId(paperId);
            if(answer.getAdditionalPurchase()==1){
                bill+=answer.getAmount()*answer.getValue();
            }
            answers.add(answer);
        }
        record.setBill(bill);
        Random random = new Random();
        int i = random.nextInt(9999);
        record.setCode(i+"");
        recordService.save(record);
        answerService.saveBatch(answers);
        return "success";
    }

    @ApiOperation("问卷统计")
    @PostMapping("/summary")
    public HashMap<String, Map<String, Integer>> submitPaperAnswer(String paperId){
        if(Strings.isNullOrEmpty(paperId)){
            throw new APIException("请求参数有误");
        }
        List<Answer> answers = answerService.list(new QueryWrapper<Answer>().eq("paper_id", paperId));
        HashMap<String,Map<String,Integer>> maps = new HashMap<>();
        for(Answer answer:answers){
            if(maps.containsKey(answer.getTitle())){
                Map<String, Integer> map = maps.get(answer.getTitle());
                Integer sum = map.get("总计");
                map.put("总计",sum+1);
                String answerOption = answer.getAnswerOption();
                if(map.containsKey(answerOption)){
                    map.put(answerOption,map.get(answerOption)+answer.getAmount());
                }else{
                    map.put(answerOption,answer.getAmount());
                }
            }else{
                HashMap<String, Integer> map = new HashMap<>();
                map.put("总计",answer.getAmount());
                map.put(answer.getAnswerOption(),answer.getAmount());
                maps.put(answer.getTitle(),map);
            }
        }
        return maps;
    }

    @ApiOperation("问卷统计")
    @PostMapping("/additionalPurchase")
    public HashMap<String, Object> summaryAdditionalPurchase(String paperId){
        if(Strings.isNullOrEmpty(paperId)){
            throw new APIException("请求参数有误");
        }
        List<Answer> answers = answerService.list(new QueryWrapper<Answer>().eq("paper_id", paperId).eq("additional_purchase",ADDITIONAL_PURCHASE));
        HashMap<String,Object> maps = new HashMap<>();
        //加购总共金额，分类统计，按人员统计
        HashMap<String,Object> typeMap = new HashMap<>();
        HashMap<String,Object> userMap = new HashMap<>();
        double money = 0;
        for(Answer answer:answers){
            String respondentId = answer.getRespondentId();
            money+=answer.getAmount()*answer.getValue();
            if(userMap.containsKey(respondentId)){
                Map map = (Map) userMap.get(respondentId);
                double total_value = (double) map.get("合计金额");
                total_value+=answer.getValue()*answer.getAmount();
                map.put("合计金额",total_value);
                Map<String,Object> goods = new HashMap<>();
                List<Map> goodsList = null;
                if(map.containsKey(answer.getTitle())){
                    goodsList = (List<Map>) map.get(answer.getTitle());
                    goods.put("型号",answer.getAnswerOption());
                    goods.put("数量",answer.getAmount());
                    goods.put("总额",answer.getAmount()*answer.getValue());
                    goodsList.add(goods);
                }else{
                    goodsList = new ArrayList<>();
                    goods.put("型号",answer.getAnswerOption());
                    goods.put("数量",answer.getAmount());
                    goods.put("总额",answer.getAmount()*answer.getValue());
                    goodsList.add(goods);
                    map.put(answer.getTitle(),goodsList);
                }
            }else{
                Map<String,Object> map = new HashMap();
                double total_value = answer.getValue()*answer.getAmount();
                map.put("合计金额",total_value);
                Map<String,Object> goods = new HashMap<>();
                List goodsList = new ArrayList<>();
                goods.put("型号",answer.getAnswerOption());
                goods.put("数量",answer.getAmount());
                goods.put("总额",answer.getAmount()*answer.getValue());
                goodsList.add(goods);
                map.put(answer.getTitle(),goodsList);
                userMap.put(answer.getRespondentId(),map);
            }
            Map<String,Object> map = null;
            if(typeMap.containsKey(answer.getTitle())){
                map = (Map) typeMap.get(answer.getTitle());
                if(map.containsKey(answer.getAnswerOption())){
                    int count = (int) map.get(answer.getAnswerOption());
                    map.put(answer.getAnswerOption(),count+answer.getAmount());
                }else{
                    map.put(answer.getAnswerOption(),answer.getAmount());
                }
            }else{
                map = new HashMap<>();
                map.put(answer.getAnswerOption(),answer.getAmount());
                typeMap.put(answer.getTitle(),map);
            }
        }
        maps.put("总计金额",money);
        maps.put("按分类统计",typeMap);
        maps.put("按人员统计",userMap);
        return maps;
    }

    @ApiOperation("用户通过验证码领取")
    @PostMapping("/claim")
    public Map<String, Object> claimByCode(String paperId, String code){
        if(Strings.isNullOrEmpty(code) || Strings.isNullOrEmpty(paperId)){
            throw new APIException("请求参数有误");
        }
        Record record = recordService.getOne(new QueryWrapper<Record>().eq("paper_id", paperId).eq("code", code));
        if(record == null){
            throw new APIException("验证码无效");
        }
        if(record.getClaimTime()!=null && record.getClaimTime().before(new Date())){
            throw new APIException("该福利已经被领取，领取时间："+record.getClaimTime().toString());
        }
        record.setClaimTime(new Date());
        recordService.saveOrUpdate(record);
        List<Answer> answers = answerService.list(new QueryWrapper<Answer>().eq("respondent_id", record.getRespondentId()).eq("paper_id", paperId));
        Map<String,Object> maps = new HashMap<>();
        Map<String,Map<String, Integer>> goods = new HashMap<>();
        String userName = null;
        for(Answer answer:answers){
            String title = answer.getTitle();
            if(userName == null){
                userName = answer.getRespondentId();
            }
            if(goods.containsKey(title)){
                //如果包含
               Map<String,Integer> map = goods.get(title);
               if(map.containsKey(answer.getAnswerOption())){
                   int amount = map.get(answer.getAnswerOption());
                   map.put(answer.getAnswerOption(),amount+answer.getAmount());
               }else{
                   map.put(answer.getAnswerOption(),answer.getAmount());
               }

            }else{
                Map<String,Integer> map = new HashMap<>();
                map.put(answer.getAnswerOption(),answer.getAmount());
                goods.put(answer.getTitle(),map);
            }
        }
        maps.put("用户",userName);
        maps.put("领取物品",goods);
        if(record.getBill()>0)
        maps.put("加购账单",record.getBill());
        return maps;
    }


    @ApiOperation("问卷进度概览")
    @PostMapping("/progress")
    public HashMap<String, Set<String>> getPaperProgress(String paperId){
        List<Record> records = recordService.list(new QueryWrapper<Record>().eq("paper_id", paperId));
        HashMap<String,Set<String>> maps = new HashMap<>();
        HashSet<String> unfilledSet = new HashSet<>();
        HashSet<String> unclaimedSet = new HashSet<>();
        HashSet<String> claimedSet = new HashSet<>();
        unfilledSet.add("Xavier.li");
        for(Record record:records){
            if(record.getClaimTime()!=null){
                claimedSet.add(record.getRespondentId());
            }else{
                unclaimedSet.add(record.getRespondentId());
            }
        }
        maps.put("未登记",unfilledSet);
        maps.put("已登记未领取",unclaimedSet);
        maps.put("已领取",claimedSet);
        return maps;
    }

    @ApiOperation("查询单个用户问卷回答情况")
    @PostMapping("/query")
    public Map<String, Object> queryById(String paperId, String userId){
        if(Strings.isNullOrEmpty(paperId) || Strings.isNullOrEmpty(userId)){
            throw new APIException("请求参数有误");
        }

        Record record = recordService.getOne(new QueryWrapper<Record>().eq("paper_id", paperId).eq("respondent_id", userId));
//        if(record.getClaimTime()!=null && record.getClaimTime().before(new Date())){
//            throw new APIException("该福利已经被领取，领取时间："+record.getClaimTime().toString());
//        }
        Map<String,Object> maps = new HashMap<>();
        maps.put("用户",userId);
        if (record == null){
            maps.put("表单状态","该用户尚未登记表单");
            return maps;
        }
        List<Answer> answers = answerService.list(new QueryWrapper<Answer>().eq("respondent_id", userId).eq("paper_id", paperId));

        Map<String,Map<String, Integer>> goods = new HashMap<>();
        for(Answer answer:answers){
            String title = answer.getTitle();
            if(goods.containsKey(title)){
                //如果包含
                Map<String,Integer> map = goods.get(title);
                if(map.containsKey(answer.getAnswerOption())){
                    int amount = map.get(answer.getAnswerOption());
                    map.put(answer.getAnswerOption(),amount+answer.getAmount());
                }else{
                    map.put(answer.getAnswerOption(),answer.getAmount());
                }

            }else{
                Map<String,Integer> map = new HashMap<>();
                map.put(answer.getAnswerOption(),answer.getAmount());
                goods.put(answer.getTitle(),map);
            }
        }
        String status = record.getClaimTime()==null?"登记未领取":"已领取";
        maps.put("表单状态",status);
        maps.put("物品列表",goods);
        maps.put("验证码",record.getCode());
        if(record.getBill()>0)
            maps.put("加购账单",record.getBill());
        if(record.getClaimTime()==null){
            maps.put("领取时间","暂未领取");
        }else{
            maps.put("领取时间",record.getClaimTime().toString());
        }
        return maps;
    }

}
