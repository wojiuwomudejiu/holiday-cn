package wjwmdj.holiday.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ai")
@Data
public class AiProperties {
    private String platform;
    private String chatModel;
    private String apiKey;
}
