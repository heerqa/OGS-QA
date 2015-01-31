package com.ogs.automation;


import com.thoughtworks.selenium.Selenium;

import static org.junit.Assert.fail;

public class WaitForElement {
    Selenium selenium;

    public void waitforelement(String elementname) throws Throwable {

        for (int second = 0; ; second++) {
            if (second >= 60) {
                fail("timeout");
            }
            try {
                if (selenium.isElementPresent(elementname)) {
                    break;
                }
            }
            catch (Throwable e) {
                throw e;
            }
            Thread.sleep(1000);

        }
    }

}
