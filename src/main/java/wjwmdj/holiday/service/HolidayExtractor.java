package wjwmdj.holiday.service;

import wjwmdj.holiday.model.HolidayInfo;

/**
 * 节假日提取器
 */
public interface HolidayExtractor {

    /**
     * 从文本中提取出节假日信息
     * @param text 文本
     * @return 节假日信息
     */
    HolidayInfo extract(String text);
}
