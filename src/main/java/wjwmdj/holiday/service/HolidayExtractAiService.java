package wjwmdj.holiday.service;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import wjwmdj.holiday.model.HolidayInfo;

public interface HolidayExtractAiService {

    @UserMessage("从{{text}}中提取出该年的节假日信息")
    HolidayInfo extract(@V("text") String text);
}
