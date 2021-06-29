package us.zoom.checkin.service.impl;

import us.zoom.checkin.entity.Record;
import us.zoom.checkin.mapper.RecordMapper;
import us.zoom.checkin.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hank.huang
 * @since 2021-06-28
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

}
