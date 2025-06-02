/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visa.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author Visa
 * @modifier Nguyen Dac Binh Minh
 */
public class VisaProperties
{
    private final static Logger logger = Logger.getLogger(VisaAPIClient.class);
    private final static Properties properties = new Properties();
    private static final String name_VM_arg = "wsgateway2";  //wsgateway2 ~ mobile, wsgateway ~ ebank
    private static final String CONFIG_FILE = "/config/visaConfig.properties";

    static
    {
        try
        {
            if (properties.isEmpty())
            {
                String path = System.getProperty(name_VM_arg);
                String propertiesFile = path + CONFIG_FILE;
                File file = new File(propertiesFile);
                if (file.exists())
                {
                    FileReader reader = new FileReader(file);
                    properties.load(reader);
                }
                else
                {
                    logger.warn("file visaConfig.properties does not exist");
                }
            }
        }
        catch (IOException e)
        {
            logger.error("File visa properties does not loaded because: [" + e + "]");
            throw new RuntimeException(e);
        }
    }
    
    public static String getProperty(Property property)
    {
        try
        {
            return properties.get(property.getValue()).toString();
        }
        catch (Exception e)
        {
            return null;
        }
    }
}