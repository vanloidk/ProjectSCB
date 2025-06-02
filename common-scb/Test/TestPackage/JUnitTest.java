/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPackage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import scb.com.vn.ultility.*;


/**
 *
 * @author phucnnd
 */
public class JUnitTest {

    public JUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() throws Exception {
        System.out.println("isMobilePhoneValid1: " + Common.isMobilePhoneValid("01234567890"));//
        System.out.println("isMobilePhoneValid2: " + Common.isMobilePhoneValid("012345678901"));//
        System.out.println("isMobilePhoneValid3: " + Common.isMobilePhoneValid("0123456789012"));//
        System.out.println("isMobilePhoneValid4: " + Common.isMobilePhoneValid("01234567890123"));//
        System.out.println("isMobilePhoneValid5: " + Common.isMobilePhoneValid("012345678901234"));//

        System.out.println("isMobilePhoneValid6: " + Common.isMobilePhoneValid("12345678901"));//
        System.out.println("isMobilePhoneValid7: " + Common.isMobilePhoneValid("097455456"));//
        System.out.println("isMobilePhoneValid8: " + Common.isMobilePhoneValid("0974554565"));//


        System.out.println(AES.decrypt("DBlwwOBb//9rkxbndZ6uSUqRgFYdHpiDV3gMJFzUTgHUoOWSodFBg6KhR/xOXs5gGKJ427x8mt25\n" +
"/JG5w9WqfA==", "phucnguyen"));
    }
}