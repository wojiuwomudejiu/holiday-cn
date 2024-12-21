package wjwmdj.holiday.model;

import dev.langchain4j.model.output.structured.Description;

import java.util.List;

public class HolidayInfo {

    @Description("不要给该参数赋值")
    private Integer year;
    @Description("不要给该参数赋值")
    private Long version;


    @Description("节假日列表")
    List<AiExtractHoliday> holidayList;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<AiExtractHoliday> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<AiExtractHoliday> holidayList) {
        this.holidayList = holidayList;
    }

    public static class AiExtractHoliday{

        @Description("节假日名称")
        String holidayName;
        @Description("节假日日期,格式为yyyy-MM-dd")
        List<String> holidayDate;

        @Description("假期需要进行调休上班的日期,格式为yyyy-MM-dd")
        List<String> holidayRest;

        public String getHolidayName() {
            return holidayName;
        }

        public void setHolidayName(String holidayName) {
            this.holidayName = holidayName;
        }

        public List<String> getHolidayDate() {
            return holidayDate;
        }

        public void setHolidayDate(List<String> holidayDate) {
            this.holidayDate = holidayDate;
        }

        public List<String> getHolidayRest() {
            return holidayRest;
        }

        public void setHolidayRest(List<String> holidayRest) {
            this.holidayRest = holidayRest;
        }
    }
}
