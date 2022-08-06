package com.tech.api.tests;

import com.tech.annotations.Testable;
import com.tech.api.config.ApiUrl;
import com.tech.base.ApiBaseTest;
import com.tech.constants.TestType;
import com.tech.service.api.ResponseUtil;
import com.tech.utils.PathUtil;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

@Testable(testName = "Booker Rest API Test")
public class BookerRestApiTest extends ApiBaseTest {

    @Test(groups = {TestType.ACCEPTANCE}, description = "Verify booker GET api without auth.")
    public void testBookerGetApi(){
        String urlPath = PathUtil.concatPaths(ApiUrl.getBooking, "12");
        Response response = api.get(urlPath);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test(groups = {TestType.ACCEPTANCE}, description = "Verify create booking POST api.")
    public void testCreateBookingApi(){
        String urlPath = ApiUrl.createBooking;

        JSONObject payload = new JSONObject();
        payload.put("firstname", "Jim");
        payload.put("lastname", "Brown");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("additionalneeds", "Breakfast");

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");
        payload.put("bookingdates", bookingdates);

        Response response = api.post(urlPath, payload);
        JSONObject jsonResp = ResponseUtil.toJSONObject(response);
        String bookingId = String.valueOf(jsonResp.get("bookingid"));

        Assert.assertEquals(bookingId, "3216");
    }
}
