package wjwmdj.holiday.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import javafx.util.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import wjwmdj.holiday.model.GovHolidayResp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 节假日数据爬取工具
 * 从国务院官网爬取节假日公告
 */
public class HolidayDataCrawler {

    private static final String HOLIDAY_GET_URL = "https://sousuoht.www.gov.cn/athena/forward/2B22E8E39E850E17F95A016A74FCB6B673336FA8B6FEC0E2955907EF9AEE06BE";
    private static final String SEARCH_WORD_TEMPLATE = "国务院办公厅关于%d年部分节假日安排的通知";
    private static final String GOV_HOLIDAY_NOTICE_ID = "UCAP-CONTENT";



    /**
     * 爬取最新的公告
     * @param year 年份
     * @return key:公告版本 value:公告内容
     */
    public static Pair<Long, String> crawlLatestNotice(Integer year){
        Map<String, Object> holidayReqParam = createHolidayReqParam(year);
        Map<String, String> holidayReqHeader = createHolidayReqHeader();
        String holidayJsonStr = HttpUtil.createPost(HOLIDAY_GET_URL).headerMap(holidayReqHeader, true)
                .body(JSONUtil.toJsonStr(holidayReqParam)).execute().body();
        GovHolidayResp govHolidayResp = JSONUtil.toBean(holidayJsonStr, GovHolidayResp.class);
        if (!govHolidayResp.getResultCode().isSuccess()){
            System.out.println(govHolidayResp.getResultCode().getCnMsg());
            return null;
        }
        List<GovHolidayResp.MiddleItem> holidayUrlList = govHolidayResp.getResult().getData().getMiddle().getList();
        if (CollUtil.isEmpty(holidayUrlList)){
            return null;
        }
        GovHolidayResp.MiddleItem firstHolidayUrl = CollUtil.getFirst(holidayUrlList);
        Document noticeDocument = Jsoup.parse(HttpUtil.get(StrUtil.replace(firstHolidayUrl.getUrl(), "http://", "https://")));
        Element holidayNoticeHtml = noticeDocument.getElementById(GOV_HOLIDAY_NOTICE_ID);
        return new Pair<>(firstHolidayUrl.getLongTime(), HtmlUtil.cleanHtmlTag(holidayNoticeHtml.html()));
    }


    private static Map<String, String> createHolidayReqHeader() {
        return new HashMap<>(){{
            put("athenaappkey", "c3qeypTcnWN3Z2eB3ne67dI5YLJL7htphRVCQ6DLuCq4HymywfK2iVxnK7x%2FLZ4RKrzexx4IIMXMzFz09thbWzDXN4IYUZukT3y0dH%2FFgGZ0N6RYDyxMeRe5U%2BRz7OVeLjTqJE5YvdZqEzVRzb%2Fx6hEgTOw6yahRT%2FAdgaW4u7c%3D");
            put("athenaappname", "%E5%9B%BD%E7%BD%91%E6%90%9C%E7%B4%A2");
        }};
    }

    private static Map<String, Object> createHolidayReqParam(Integer year){
        String searchWord = String.format(SEARCH_WORD_TEMPLATE, year);
        String paramJson = "{\"code\":\"17da70961a7\",\"historySearchWords\":[],\"dataTypeId\":\"107\"," +
                "\"orderBy\":\"time\",\"searchBy\":\"title\",\"appendixType\":\"\",\"granularity\":\"ALL\"," +
                "\"trackTotalHits\":true,\"beginDateTime\":\"\",\"endDateTime\":\"\",\"isSearchForced\":0," +
                "\"filters\":[],\"pageNo\":1,\"pageSize\":10,\"customFilter\":{\"operator\":\"and\",\"properties\":[]}}";
        JSONObject paramMap = JSONUtil.parseObj(paramJson);
        paramMap.put("searchWord", searchWord);
        return paramMap;
    }
}
