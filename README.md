# holiday-cn

## 项目介绍

**中国法定节假日数据。每日自动监测中国政府官方网站（如国务院）发布的最新公告，通过AI对公告内容进行解析和提取，最终输出为JSON文件**

![](./docs/流程图.png)



## 技术选型

| 技术        | 说明                     | 官网                                   |
| ----------- | ------------------------ | -------------------------------------- |
| SpringBoot  | 简化Spring应用程序的开发 | https://spring.io/projects/spring-boot |
| Langchain4j | 简化LLM集成              | https://docs.langchain4j.dev/intro     |
| 智谱AI      | 智谱AI大模型             | https://open.bigmodel.cn/              |
| Hutool      | Java工具类库             | https://www.hutool.cn/                 |
| Jsoup       | Java解析网页             | https://jsoup.org/                     |

## 项目配置[application.yml]

~~~yml
ai:
  # 模型平台。目前仅支持智谱AI
  platform: zhipu
  # 模型名称,必须配置
  chat-model: glm-4-plus
  # apiKey。你的apiKey,必须配置
  api-key: yourapikey

~~~

