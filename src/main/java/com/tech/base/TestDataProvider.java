package com.tech.base;

import com.tech.constants.CsvDataProvider;
import com.tech.loader.models.UserDataCsv;
import com.tech.service.TestDataService;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;

import java.util.List;

//Todo: Make this class generic using interface. So that it can work for all types of Csv Models.
@Slf4j
public class TestDataProvider {

    @DataProvider(name = CsvDataProvider.userDataProvider)
    public Object[][] userDataProviderMethod() {
        List<UserDataCsv> userCsvData = TestDataService.getUserCsvData();
        return mapDataTo2DArray(userCsvData);
    }

    private UserDataCsv[][] mapDataTo2DArray(List<UserDataCsv> userCsvData){
        UserDataCsv[][] twoDArray = new UserDataCsv[userCsvData.size()][1];
        for (int i=0; i<userCsvData.size(); i++){
            twoDArray[i][0] = userCsvData.get(i);
        }
        return twoDArray;
    }

}
