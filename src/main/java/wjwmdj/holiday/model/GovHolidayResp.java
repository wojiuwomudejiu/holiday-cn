package wjwmdj.holiday.model;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class GovHolidayResp {
    private ResultCode resultCode;
    private Result result;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result{
        private GovHolidayResp.Data data = new Data();

        public GovHolidayResp.Data getData() {
            return data;
        }

        public void setData(GovHolidayResp.Data data) {
            this.data = data;
        }
    }

    public static class Data{
        private Middle middle = new Middle();

        public Middle getMiddle() {
            return middle;
        }

        public void setMiddle(Middle middle) {
            this.middle = middle;
        }
    }

    public static class Middle{
        private List<MiddleItem> list = new ArrayList<>();

        public List<MiddleItem> getList() {
            return list;
        }

        public void setList(List<MiddleItem> list) {
            this.list = list;
        }
    }

    public static class MiddleItem{
        private String time;
        private String url;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Long getLongTime(){
            return DateUtil.parse(this.time, "yyyy-MM-dd HH:mm:ss").getTime();
        }
    }

    public static class ResultCode{
        private String code;
        private String cnMsg;


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCnMsg() {
            return cnMsg;
        }

        public void setCnMsg(String cnMsg) {
            this.cnMsg = cnMsg;
        }

        public boolean isSuccess(){
            return "200".equals(this.code);
        }
    }
}



