package wjwmdj.holiday.service;

import wjwmdj.holiday.model.HolidayInfo;

public interface HolidayStore {

    boolean store(HolidayInfo holidayInfo);

    HolidayInfo get(Integer year);
}
