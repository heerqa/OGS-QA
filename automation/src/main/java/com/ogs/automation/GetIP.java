package com.ogs.automation;

import java.net.*;
import java.util.Enumeration;
import java.io.*;

public class GetIP {
	  /*
	   * Following code is not required now, may be useful in future so keeping it here.
	   */
	  /*
	    System.out.println("Your Host addr: " + InetAddress.getLocalHost().getHostAddress());  // often returns "127.0.0.1"
	    Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
	    for (; n.hasMoreElements();)
	    {
	        NetworkInterface e = n.nextElement();

	        Enumeration<InetAddress> a = e.getInetAddresses();
	        for (; a.hasMoreElements();)
	        {
	            InetAddress addr = a.nextElement();
	            System.out.println("  " + addr.getHostAddress());
	        }
	    }
	    */
   
  public String getIpAddress() throws MalformedURLException, IOException {
      URL myIP = new URL("http://checkip.amazonaws.com");
      BufferedReader in = new BufferedReader(
                           new InputStreamReader(myIP.openStream())
                          );
      return in.readLine();
    }
  
  public String getIpLocation() throws MalformedURLException, IOException {
	  GetIP ip=new GetIP();
	  
      URL myIP = new URL("http://freegeoip.net/csv/"+ip.getIpAddress());
      BufferedReader in = new BufferedReader(
                           new InputStreamReader(myIP.openStream())
                          );
      return in.readLine();
    }

}