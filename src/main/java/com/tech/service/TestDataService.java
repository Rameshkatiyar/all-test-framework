package com.tech.service;

import com.tech.loader.UserDataCsvReader;
import com.tech.loader.models.UserDataCsv;
import com.tech.utils.FileUtil;
import com.tech.utils.PathUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TestDataService {
    private static final String csvTestDataDirName = "testdata/ddtCSVs";
    private static final String csvAbsoluteDirPath = FileUtil.getAbsoluteDirPath(csvTestDataDirName);

    public static List<UserDataCsv> getUserCsvData(){
        String fileName = "testuser.csv";
        String csvFilePath = PathUtil.concatPaths(csvAbsoluteDirPath, fileName);
        System.out.println(csvFilePath);

        UserDataCsvReader userDataCsvReader = new UserDataCsvReader();
        List<UserDataCsv> userDataCsvs = userDataCsvReader.readCSV(csvFilePath);

        return userDataCsvs;
    }
}
