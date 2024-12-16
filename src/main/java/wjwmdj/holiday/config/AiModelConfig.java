package wjwmdj.holiday.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wjwmdj.holiday.service.HolidayExtractor;
import wjwmdj.holiday.service.impl.AiHolidayExtractor;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(AiProperties.class)
public class AiModelConfig {

    @Bean
    @ConditionalOnProperty(name = "ai.platform", havingValue = "zhipu", matchIfMissing = true)
    public ChatLanguageModel zhipuAiModel(AiProperties aiProperties){
        return ZhipuAiChatModel.builder()
                .callTimeout(Duration.ofMinutes(10))
                .connectTimeout(Duration.ofMinutes(10))
                .writeTimeout(Duration.ofMinutes(10))
                .readTimeout(Duration.ofMinutes(10))
                .logRequests(true)
                .logResponses(true)
                .apiKey(aiProperties.getApiKey())
                .model(aiProperties.getChatModel())
                .build();
    }




    @Bean
    public HolidayExtractor holidayExtractor(ChatLanguageModel chatLanguageModel){
        return new AiHolidayExtractor(chatLanguageModel);
    }


}
