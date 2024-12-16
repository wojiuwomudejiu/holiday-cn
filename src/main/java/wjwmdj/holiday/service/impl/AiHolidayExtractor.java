package wjwmdj.holiday.service.impl;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import wjwmdj.holiday.model.HolidayInfo;
import wjwmdj.holiday.service.HolidayExtractAiService;
import wjwmdj.holiday.service.HolidayExtractor;

/**
 * 基于AI的节假日提取器
 */
public class AiHolidayExtractor implements HolidayExtractor {

    private ChatLanguageModel chatLanguageModel;

    public AiHolidayExtractor(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    @Override
    public HolidayInfo extract(String text){
        HolidayExtractAiService holidayExtractAiService = AiServices.create(HolidayExtractAiService.class, chatLanguageModel);
        return holidayExtractAiService.extract(text);
    }

}
