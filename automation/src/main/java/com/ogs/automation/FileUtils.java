package com.ogs.automation;


import org.apache.commons.lang3.SystemUtils;

import java.io.*;
import java.net.URL;

/**
 * User: vadim
 * Date: 1/4/14
 * Time: 10:06 AM
 */


public class FileUtils {

    public static InputStream getTestContentInputStream(Class clazz, String fileName) throws FileNotFoundException {
        URL url = getResourceUrl(clazz, fileName);
        return new FileInputStream(url.getFile());
    }


    public static File getResourceFile(Class clazz, String fileName) {
        return new File(getResourceUrl(clazz, fileName).getPath());
    }

    public static File prepateExecutableResourceFile(Class clazz, String fileName) {
        File executableFile = getResourceFile(clazz, fileName);

        //-- CROCK: due to antivirus or something similar we have to add e to the end of file name which ends on .ex
        if (SystemUtils.IS_OS_WINDOWS && executableFile.getPath().endsWith("-win.ex")) {
            File windowsRenamedExecutableFile = new File(executableFile.getPath() + "e");
            try {
                org.apache.commons.io.FileUtils.copyFile(executableFile, windowsRenamedExecutableFile);
                executableFile = windowsRenamedExecutableFile;
            }
            catch (IOException e) {
                //-- nothing we can do here, but log exception to screen
                e.printStackTrace();
            }
        }
        boolean isSetExecutable = executableFile.setExecutable(true);
        assert isSetExecutable : "In order to proceed with tests " + executableFile.getPath() + " must be set to be executable";
        return executableFile;
    }

    public static String getOsVersionStringExtension() {
        if (SystemUtils.IS_OS_MAC_OSX) {
            return "-osx";
        }
        if (SystemUtils.IS_OS_WINDOWS) {
            return "-win.ex";
        }
        if (SystemUtils.IS_OS_UNIX) {
            //return "-linux-x86_64";
        	return "-linux-i686";
        }
        if (SystemUtils.IS_OS_LINUX) {
            return "-linux-i686";
        }
        //-- by default we will execute osx driver
        return "";
    }

    private static URL getResourceUrl(Class clazz, String fileName) {
        //--System.out.println("FileName we want: " + fileName);
        URL resourceURL;
        if (SystemUtils.IS_OS_WINDOWS) {
            resourceURL = clazz.getClassLoader().getResource(fileName);
        }
        else {
            resourceURL = (fileName.startsWith(File.separator)) ?
                    clazz.getResource(fileName) :
                    clazz.getResource(File.separator + fileName);
        }
        //--System.out.println("Full Resource URL: " + resourceURL.getPath());
        return resourceURL;
    }

    public static String getUserDir() {
        //-- We can use the below call to get userDir, inside of which we could install other tools like NodeJS
        return SystemUtils.getUserDir().getPath();
    }
}
