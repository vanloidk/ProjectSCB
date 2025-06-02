/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import javax.mail.Authenticator;
import org.apache.log4j.Logger;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.util.ByteArrayDataSource;
import scb.com.vn.common.model.email.Email;
import scb.com.vn.common.model.email.SendEmailReq;

/**
 *
 * @author minhndb
 */
public class EmailUtils {

    private static final Logger logger = Logger.getLogger(EmailUtils.class);

    private static final String SMTP_HOST_NAME = Configuration.getProperty("email.host"); // mail.scb.com.vn
    private static final String SMTP_PORT = Configuration.getProperty("email.port"); // 587
    private static final String SMTP_PROTOCOL = Configuration.getProperty("email.protocol"); // smtp
    private static final String SMTP_AUTH = Configuration.getProperty("email.auth"); // true
    private static final String SMTP_STARTTLS_ENABLE = Configuration.getProperty("email.starttls.enable"); // true

    private static final String SMTP_AUTH_USER = Configuration.getProperty("email.from");
    private static final String SMTP_AUTH_PWD = Configuration.getProperty("email.from.password");
    
    private static final String LIST_TEMP_EMAIL = Configuration.getProperty("email.test");

    /**
     *
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    private static final Properties props = new Properties();

    static {
        props.put("mail.transport.protocol", SMTP_PROTOCOL);
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", SMTP_AUTH);
        props.put("mail.smtp.starttls.enable", Boolean.valueOf(SMTP_STARTTLS_ENABLE));
    }

    /**
     *
     * @param req
     * @return
     */
    public static boolean sendEmail(SendEmailReq req) {
        boolean result = false;
        try {
                      
            Email emailTo =  req.getEmail();
            List<String> emaillist =  emailTo.getEmailTo().getEmailDetail();
            
            if (LIST_TEMP_EMAIL == null || LIST_TEMP_EMAIL.isEmpty()) {
             return false;
            }
            for (String emailloop : emaillist) 
            {                 
               if(!(LIST_TEMP_EMAIL.contains(emailloop.toLowerCase()))){                                                     
                  logger.warn("EmailUtils Email is not valid: " + emailloop);                                      
                  return false;
              }
            }
                                         
           
            if (req.isValid()) {
                Authenticator auth = new SMTPAuthenticator();
                Session mailSession = Session.getDefaultInstance(props, auth);
                Transport transport = mailSession.getTransport();
                MimeMessage message = new MimeMessage(mailSession);
                message.setSubject(req.getTitle(), "UTF-8");
                // Create the message part
                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setContent(req.getBody(), "text/html; charset=\"UTF-8\"");

                // Create a multipar message
                Multipart multipart = new MimeMultipart();

                // Set text message part
                multipart.addBodyPart(messageBodyPart);
                
                // Part two is attachment
                messageBodyPart = new MimeBodyPart();

                //int index = req.getFileAttach().lastIndexOf("\\");
                String fileName = null;
                if (req.getFileAttach() != null) {
                    int index = req.getFileAttach().lastIndexOf(FILE_SEPARATOR);
                    fileName = req.getFileAttach().substring(index + 1);
                    DataSource source = new FileDataSource(req.getFileAttach());
                    messageBodyPart.setDataHandler(new DataHandler(source));                    
                    messageBodyPart.setFileName(fileName);                    
                }else {                    
                    fileName =  req.getFileName();
                    messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(req.getFileData(), "text/plain")));               
                    messageBodyPart.setFileName(fileName);                                       
                }                                                                 
               
                multipart.addBodyPart(messageBodyPart);
                // Send the complete message parts
                message.setContent(multipart);
                message.setFrom(new InternetAddress(SMTP_AUTH_USER));
                AddAddress(message, Message.RecipientType.TO, req.getEmailTo());
                AddAddress(message, Message.RecipientType.CC, req.getEmailCc());
                AddAddress(message, Message.RecipientType.BCC, req.getEmailBcc());
                transport.connect();
                transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                if (req.isExistEmailCc()) {
                    transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
                }
                if (req.isExistEmailBcc()) {
                    transport.sendMessage(message, message.getRecipients(Message.RecipientType.BCC));
                }
                transport.close();
                result = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    private static void AddAddress(MimeMessage message, Message.RecipientType type, List<String> emails) {
        if (emails != null && !emails.isEmpty()) {
            for (String item : emails) {
                AddAddress(message, type, item);
            }
        }
    }

    private static void AddAddress(MimeMessage message, Message.RecipientType type, String email) {
        try {
            if (isValidEmailAddress(email)) {
                message.addRecipient(type, new InternetAddress(email));
            } else {
                logger.warn("Email is not valid: " + email);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static boolean isValidEmailAddress(String email) {
        boolean result = false;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            result = true;
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    private static class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }

    //Method to replace the values for keys

    /**
     *
     * @param filePath
     * @param input
     * @return
     */
    public static String readEmailFromHtml(String filePath, Map<String, String> input) {
        String msg = readContentFromFile(filePath);
        try {
            Set<Entry<String, String>> entries = input.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return msg;
    }
//Method to read HTML file as a String 

    /**
     *
     * @param fileName
     * @return
     */
    public static String readContentFromFile(String fileName) {
        StringBuffer contents = new StringBuffer();
        try {
            //use buffering, reading one line at a time
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            try {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            } finally {
                reader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return contents.toString();
    }
}
