package com.ogs.automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;


/**
 * User: vadim
 * Date: 2/12/14
 * Time: 5:03 PM
 */


public final class BrowserUtils {

    private static BrowserUtils instance = new BrowserUtils();
    private boolean shouldLogin = true;
    private boolean shouldBrowserBeClosed = true;
    private boolean isProductionEnv = false;
    private WebDriver driver;


    /**
     * Singelton access.
     *
     * @return singeton instance
     */
    public static BrowserUtils getInstance() {
        return instance;
    }

    private BrowserUtils() {
    }


    /**
     * Get the method name for a depth in call stack. <br />
     * Utility function
     *
     * @param depth depth in the call stack (0 means current method, 1 means call method, ...)
     * @link http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-executing-method
     */
    public String getMethodName(int depth) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return (ste.length >= depth) ? (ste[ste.length - 1 - depth].getMethodName()) : "NO_NAME_METHOD";
    }


    public String getMethodName() {
        return getMethodName(29);
    }

    public String getBaseURL() {
        return isProd() ? Browser.baseProdUrl : Browser.baseDevUrl;
    }

    public boolean isShouldBrowserBeClosed() {
        return shouldBrowserBeClosed;
    }

    public void setShouldBrowserBeClosed(boolean shouldBrowserBeClosed) {
        this.shouldBrowserBeClosed = shouldBrowserBeClosed;
    }

    public boolean isShouldLogin() {
        return shouldLogin;
    }

    public void setShouldLogin(boolean shouldLogin) {
        this.shouldLogin = shouldLogin;
    }

    public boolean isProd() {
        return isProductionEnv;
    }

    public void setProd(boolean isProductionEnv) {
        this.isProductionEnv = isProductionEnv;
    }

    public WebDriver getDriver() {
    	
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;

    }
}
