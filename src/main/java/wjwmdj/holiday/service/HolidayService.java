package wjwmdj.holiday.service;

import wjwmdj.holiday.model.HolidayInfo;

public interface HolidayService {

    /**
     * 更新节假日数据
     * @param year yyyy
     * @return 是否成功
     */
    boolean modifyHolidayData(Integer year);


}
