package wjwmdj.holiday.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;
import wjwmdj.holiday.model.HolidayInfo;
import wjwmdj.holiday.service.HolidayStore;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 磁盘文件存储,默认输出文件为 target/data/${year}.json
 */
@Service
public class HolidayDiskStore implements HolidayStore {

    private static final String BASE_PATH = "data/";
    private static final String FILE_EXT = ".json";
    private static final String PACKAGE_SPLIT = "/";

    private String createFilePath(Integer year) {
        return BASE_PATH + PACKAGE_SPLIT + year + FILE_EXT;
    }


    @Override
    public boolean store(HolidayInfo holidayInfo) {
        File diskFile = FileUtil.file(createFilePath(holidayInfo.getYear()));
        FileUtil.writeString(JSONUtil.toJsonStr(holidayInfo), diskFile, "utf-8");
        return true;
    }

    @Override
    public HolidayInfo get(Integer year) {
        File diskFile = FileUtil.file(createFilePath(year));
        if (!diskFile.exists()){
            return null;
        }
        String jsonStr = FileUtil.readString(diskFile, "utf-8");
        return JSONUtil.toBean(jsonStr, HolidayInfo.class);

    }

}
