package com.ogs.automation;

import com.sun.mail.imap.IMAPFolder;
import  org.junit.Assert;

import javax.mail.Flags.Flag;
import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

public class Email {

    public String emailNotification(String emailid, String emailpassword, String emailtype) throws MessagingException, IOException {
        IMAPFolder folder = null;
        Store store = null;
        String subject = null;
        String notificationFrom = null;
        String updateStatus;
        Flag flag = null;
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);

            store = session.getStore("imaps");
            store.connect("imap.googlemail.com", emailid, emailpassword);

            //folder = (IMAPFolder) store.getFolder("[Gmail]/Important");
            folder = (IMAPFolder) store.getFolder("inbox");


            if (!folder.isOpen()) {
                folder.open(Folder.READ_WRITE);
            }
            //Message[] messages = folder.search(arg0)
            Message[] messages = folder.getMessages();


            for (int i =messages.length ; i >0; i--) {

                Message msg = messages[i - 1];

                subject = msg.getSubject();
                String expectedsubject = "Your iQabinet Messages";

                if (subject.equals(expectedsubject)) {
                    System.out.println("*****************************************************************************");
                    System.out.println("MESSAGE " + (i + 1) + ":");
                    System.out.println(msg.getReceivedDate());
                    String emailsubject=msg.getSubject().toString();
                    String emailbody = msg.getContent().toString();
                    System.out.println(emailbody);

                    if (emailtype.toLowerCase() == "failure") {
                        boolean containsDriver = emailbody.contains("Document Update Failure");
                        Assert.assertTrue("Test Email for Document Update Failure", containsDriver);

                    }
                    else if (emailtype.toLowerCase() == "successful") {
                        boolean containsDriver = emailbody.contains("New Documents");
                        Assert.assertTrue("Test Email for New Document Alert", containsDriver);
                    }

                    else if (emailtype.toLowerCase() == "nonewdocumentalrt") {
                        boolean containsDriver = emailbody.contains("No New Documents");
                        Assert.assertTrue("Test email for No New Documents update", containsDriver);
                    }
                    
                    else if (emailtype.toLowerCase() == "sharingrequest") {
                        boolean containsDriver = emailsubject.contains("Card Sharing Notification from iQabinet");
                        Assert.assertTrue("Test email for sharing request comes in", containsDriver);
                    }


                    notificationFrom = msg.getSubject().toString();
                    
                    

                    break;

                }
            }

        }
        finally {
            if (folder != null && folder.isOpen()) {
                folder.close(true);
            }
            if (store != null) {
                store.close();
            }
        }
        
      
        return notificationFrom;
    }

    public String emailNotificationforsahedcard(String emailid, String emailpassword, String emailtype) throws MessagingException, IOException {
        IMAPFolder folder = null;
        Store store = null;
        String subject = null;
        String msgbody=null;
        String notificationFrom = null;
        String updateStatus;
        Flag flag = null;
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);

            store = session.getStore("imaps");
            store.connect("imap.googlemail.com", emailid, emailpassword);

            //folder = (IMAPFolder) store.getFolder("[Gmail]/Important");
            folder = (IMAPFolder) store.getFolder("inbox");


            if (!folder.isOpen()) {
                folder.open(Folder.READ_WRITE);
            }
            //Message[] messages = folder.search(arg0)
            Message[] messages = folder.getMessages();


            for (int i =messages.length ; i >0; i--) {

                Message msg = messages[i - 1];

                subject = msg.getSubject();
                
                
                String expectedsubject = "Card Sharing Notification from iQabinet";

                if (subject.equals(expectedsubject)) {
                    System.out.println("*****************************************************************************");
                    System.out.println("MESSAGE " + (i + 1) + ":");
                    System.out.println(msg.getReceivedDate());
                    String emailsubject=msg.getSubject().toString();
                    String emailbody = msg.getContent().toString();
                    System.out.println(emailbody);

                    if (emailtype.toLowerCase() == "failure") {
                        boolean containsDriver = emailbody.contains("Document Update Failure");
                        Assert.assertTrue("Test Email for Document Update Failure", containsDriver);

                    }
                    else if (emailtype.toLowerCase() == "successful") {
                        boolean containsDriver = emailbody.contains("New Documents");
                        Assert.assertTrue("Test Email for New Document Alert", containsDriver);
                    }

                    else if (emailtype.toLowerCase() == "nonewdocumentalrt") {
                        boolean containsDriver = emailbody.contains("No New Documents");
                        Assert.assertTrue("Test email for No New Documents update", containsDriver);
                    }
                    
                    else if (emailtype.toLowerCase() == "sharingrequest") {
                        boolean containsDriver = emailsubject.contains("Card Sharing Notification from iQabinet");
                        Assert.assertTrue("Test email for sharing request comes in", containsDriver);
                    }


                    notificationFrom = msg.getSubject().toString();
                    System.out.println("Mila kya"+notificationFrom);
                    

                    break;

                }
            }

        }
        finally {
            if (folder != null && folder.isOpen()) {
                folder.close(true);
            }
            if (store != null) {
                store.close();
            }
        }
        
        System.out.println(notificationFrom);
        return notificationFrom;
    }

    public String getEmailMessageBody(String emailid, String emailpassword, String expectedsubject) throws MessagingException, IOException {
        IMAPFolder folder = null;
        Store store = null;
        String subject = null;
        String msgbody=null;
        String notificationFrom = "NotFound";
        String updateStatus;
        Flag flag = null;
        int j=1;
        
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            Session session = Session.getDefaultInstance(props, null);

            store = session.getStore("imaps");
            store.connect("imap.googlemail.com", emailid, emailpassword);

            //folder = (IMAPFolder) store.getFolder("[Gmail]/Important");
            folder = (IMAPFolder) store.getFolder("inbox");


            if (!folder.isOpen()) {
                folder.open(Folder.READ_WRITE);
            }
            //Message[] messages = folder.search(arg0)
            Message[] messages = folder.getMessages();


            for (int i =messages.length ; i >0; i--) {

                Message msg = messages[i - 1];

                subject = msg.getSubject();
                msgbody=(String) msg.getContent();
                
               // String expectedsubject = "Password Reset Request";

                if (subject.equals(expectedsubject)|| messages.length>=10 ) {
                    //System.out.println("*****************************************************************************");
                    //System.out.println("MESSAGE " + (i + 1) + ":");
                    //System.out.println(msg.getReceivedDate());
                    String emailsubject=msg.getSubject().toString();
                    String emailbody = msg.getContent().toString();
                    //System.out.println(emailbody);

                    
						//System.out.println("no of emails checked :"+j);
                    
                       // System.out.println("Message Body: "+msgbody.replaceAll("\\<[^>]*>","").trim());
                    
                        //j++;


                    notificationFrom = msgbody.replaceAll("\\<[^>]*>","").trim();
                   // System.out.println("Mila kya"+notificationFrom);
                    

                    break;

                }
            }

        }
        finally {
            if (folder != null && folder.isOpen()) {
                folder.close(true);
            }
            if (store != null) {
                store.close();
            }
        }
        
        //System.out.println(notificationFrom);
        return notificationFrom;
    }


}


