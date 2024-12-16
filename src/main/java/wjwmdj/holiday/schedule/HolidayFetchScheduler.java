package wjwmdj.holiday.schedule;


import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wjwmdj.holiday.service.HolidayService;

import java.util.Date;

@Component
@Slf4j
public class HolidayFetchScheduler {

    @Autowired
    private HolidayService holidayService;

    /**
     * 每天00:00:00执行,更新次年节假日数据
     */
    @Scheduled(cron = "0 */1 * * * ? ")
//    @Scheduled(cron = "0 0 0 * * ?")
    public void fetchHoliday(){
        try {
//            Integer year = DateUtil.year(new Date()) + 1;
            holidayService.modifyHolidayData(2009);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("fetchHoliday error! errorMsg:{}", e.getMessage());
        }
    }


}
