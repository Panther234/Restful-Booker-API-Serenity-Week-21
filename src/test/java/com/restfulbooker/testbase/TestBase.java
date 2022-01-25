/*
 * Created By: Hiren Patel
 * Project Name: Restful-Booker-API-Serenity-Week-21
 */

package com.restfulbooker.testbase;

import com.restfulbooker.constants.Path;
import com.restfulbooker.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {

    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.basePath = Path.BOOKING;
    }
}
