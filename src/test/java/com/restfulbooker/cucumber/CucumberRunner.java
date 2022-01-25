package com.restfulbooker.cucumber;
/*
 * Created By: Hiren Patel
 * Project Name: Restful-Booker-API-Serenity-Week-21
 */

import com.restfulbooker.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature/")
public class CucumberRunner extends TestBase {

}
