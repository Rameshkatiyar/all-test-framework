package com.tech.app;

import com.tech.annotations.Testable;
import com.tech.base.AppBaseTest;
import com.tech.constants.TestType;
import org.testng.annotations.Test;

@Testable(testName = "New App Test")
public class NewAppTest extends AppBaseTest {

    @Test(groups = {TestType.ACCEPTANCE}, description = "Verify app first login page.")
    public void appLoginTest(){
        //Todo
    }
}
