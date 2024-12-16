package wjwmdj.holiday.service.impl;

import dev.langchain4j.model.zhipu.ZhipuAiChatModel;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wjwmdj.holiday.util.HolidayDataCrawler;
import wjwmdj.holiday.service.HolidayExtractor;
import wjwmdj.holiday.service.HolidayStore;
import wjwmdj.holiday.model.HolidayInfo;
import wjwmdj.holiday.service.HolidayService;

@Service
@Slf4j
public class HolidayServiceImpl implements HolidayService {



    @Autowired
    private HolidayExtractor holidayExtractor;
    @Autowired
    private HolidayStore holidayStore;

    @Override
    public boolean modifyHolidayData(Integer year) {
        // 数据爬取 key:发布时间 value:通知公告
        Pair<Long,String> holidayNoticePair = HolidayDataCrawler.crawlLatestNotice(year);
        if (holidayNoticePair == null){
            log.error("modifyHolidayData crawl data error! year:{}", year);
            return false;
        }
        // 文件是否重复写入
        HolidayInfo holidayInfo = holidayStore.get(year);
        if (holidayInfo == null || holidayNoticePair.getKey() > holidayInfo.getVersion()){
            // 节假日信息提取
            HolidayInfo aiExtractRes = holidayExtractor.extract(holidayNoticePair.getValue());
            aiExtractRes.setYear(year);
            aiExtractRes.setVersion(holidayNoticePair.getKey());
            // 数据存储
            return holidayStore.store(aiExtractRes);
        }
        return true;
    }

}
