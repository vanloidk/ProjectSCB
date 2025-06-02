package scb.com.vn.ultility;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {

    public static Properties loadProperty(String pathfile) throws Exception {
        Properties configProp = new Properties();
        File file = new File(pathfile);

        if (file.exists() == false) {
            throw new Exception("Khong load duoc properties theo duong dan: " + pathfile);
        }

        FileInputStream in = new FileInputStream(file);
        configProp.load(in);
        in.close();

        return configProp;
    }

    public static boolean isNumeric(String str) throws Exception {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * *
     *
     * @param value
     * @param pattern Vi du: ###,###,###,###,###.###
     * @return
     */
    public static String getFormatCurrency(double value, String pattern) throws Exception {
        return new DecimalFormat(pattern).format(value);
    }

    /**
     * *
     *
     * @param format dd/MM/yy HH:mm:ss
     * @return
     */
    public static String getDate(String pattern) throws Exception {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static Date toDate(String strdate, String pattern) throws Exception {
        if (isEmpty(strdate)) {
            return null;
        }
        return new SimpleDateFormat(pattern).parse(strdate);
    }

    public static String toStringByDate(Date dDate, String pattern) throws Exception {
        if (dDate == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(dDate);
    }

    public static java.sql.Date toSqlDate(String strdate, String pattern) throws Exception {
        Date d = toDate(strdate, pattern);
        if (d != null) {
            return new java.sql.Date(d.getTime());
        } else {
            return null;
        }
    }

    public static boolean isEmailValid(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean isMobilePhoneValid(String mobilephone) {
//        String PHONE_PATTERN = "0(\\d(9))|0\\d(10)|0\\d(11)|0\\d(12)";
        String PHONE_PATTERN = "^(0)(\\d{9}|\\d{10}|\\d{11}|\\d{12})$";
//        String PHONE_PATTERN = "(0)\\d{9}";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(mobilephone);
        return matcher.matches();
    }
    

    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

    public static String convertMoneyToWordVNE(String strSo) throws Exception {
        String[] chu = {"không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
        String[] donvi = {"", "mươi", "trăm", "nghìn", "mươi", "trăm", "triệu", "mươi", "trăm", "tỷ", "mươi", "trăm",
            "nghìn tỷ", "mươi", "trăm", "triệu tỷ", "mươi", "trăm", "tỷ tỷ", "mươi", "trăm", ""};
        String so;
        Long d = Long.parseLong(strSo);
        so = String.valueOf(d);

        int len_so = so.trim().length();
        String kyso, kychu, previous = "", fullchuso = "", strDatach3 = "";
        for (int i = len_so; i > 0; i--) {
            strDatach3 = tach3(chu, so.substring(len_so - i, len_so));

            if (strDatach3 != null) {
                fullchuso = (strDatach3.equals("")) ? (fullchuso + strDatach3) : (fullchuso + strDatach3 + " "
                        + donvi[i - 3] + " ");
                i = i - 2; // sau khi continue, vong lap for giam -1
                continue;
            }

            // 1.234.567.890: Quet tu 1,2,3,4,5,... // phai qua trai
            kyso = String.valueOf(so.charAt(len_so - i));
            if (kyso.equals("1") && (donvi[i - 1].equals("mươi")) && (i > 1)) {
                previous = kychu = "mười";
            } else {
                if (kyso.equals("1")) {
                    if (!donvi[i].equals("mươi")) {
                        kychu = "một";
                    } // do muoi` nhay continue nen phai tao khoang trang
                    else if (previous.equals("mười")) {
                        kychu = " một";
                    } else if (i == len_so) {
                        kychu = "một";
                    } else {
                        kychu = "mốt";
                    }
                    previous = " ";
                } else if (kyso.equals("5")) {
                    if (!donvi[i].equals("mươi")) {
                        kychu = "năm";
                    } else {
                        fullchuso = fullchuso.trim();
                        if (fullchuso.length() == 0) {
                            kychu = "năm";
                        } else {
                            kychu = " lăm";
                        }
                    }
                    previous = " ";
                } else {
                    if (previous.equals("mười")) {
                        if (kyso.equals("0")) {
                            kychu = "";
                        } else if (kyso.equals("5")) {
                            kychu = " lăm";
                        } else {
                            kychu = " " + chu[Integer.parseInt(kyso)];
                        }
                    } else {
                        if (kyso.equals("0")) {
                            kychu = "";
                        } else {
                            kychu = chu[Integer.parseInt(kyso)];
                        }
                    }
                    previous = " ";
                }
            }

            fullchuso = fullchuso + kychu;
            if (previous.equals("mười")) {
                continue;
            }

            // System.out.print(donvi[i - 1] + " ");
            fullchuso = fullchuso.trim() + " " + donvi[i - 1] + " ";
        }

        // System.out.println(fullchuso);
        return fullchuso;
    }

    private static String tach3(String[] chu, String so) {
        String strReturn = null;
        if ((int) (so.length() % 3) == 0) // kiem tra phan tach 3 so la dung
        {
            String strTach3 = so.substring(0, 3); // Trai sang phai
            char t = strTach3.charAt(0), c = strTach3.charAt(1), d = strTach3.charAt(2);

            if ((t == '0') && (c == '0') && (d == '0')) {
                strReturn = "";
            } else if ((t == '0') && (c == '0') && (d != '0')) {
                strReturn = "lẻ " + chu[toInt(d)];
            } else if ((t == '0') && (c != '0') && (d == '0')) {
                strReturn = "không trăm ";
                if (c == '1') {
                    strReturn = (strReturn + "mười");
                } else {
                    strReturn = (strReturn + chu[toInt(c)] + " mươi");
                }
            } else if ((t == '0') && (c != '0') && (d != '0')) {
                strReturn = "không trăm";

                String sNam = chu[toInt(d)];
                if (sNam.equals("năm")) {
                    sNam = "lăm";
                }
                if (c == '1') {
                    strReturn = (strReturn + " mười " + sNam);
                } else {
                    strReturn = (d == '1') ? (strReturn + " " + chu[toInt(c)] + " mươi mốt") : (strReturn + " "
                            + chu[toInt(c)] + " mươi " + sNam);
                }
            } else if ((t != '0') && (c == '0') && (d == '0')) {
                strReturn = chu[toInt(t)] + " trăm";
            } else if ((t != '0') && (c == '0') && (d != '0')) {
                strReturn = chu[toInt(t)] + " trăm lẻ " + chu[toInt(d)];
            } else if ((t != '0') && (c != '0') && (d == '0')) {
                strReturn = chu[toInt(t)] + " trăm";
                if (c == '1') {
                    strReturn = (strReturn + " mười");
                } else {
                    strReturn = (strReturn + " " + chu[toInt(c)] + " mươi");
                }
            }
        }
        return strReturn;
    }

    private static int toInt(char c) {
        return Integer.parseInt(String.valueOf(c));
    }

    public static String getNameOfGetPrefix(Method method) throws Exception {
        if (method.getName().length() > 3) {
            String prefix = method.getName().substring(0, 2);
            if (!prefix.equalsIgnoreCase("get")) {
                return null;
            }
            return method.getName().substring(3);
        }
        return null;
    }
     public static String EncriptMD5(String Input)
    {
       try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(Input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            //String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            String hashtext = String.format("%032x", number);

            return hashtext;
        }
       catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
     
     public static String getJulianFromDate()
    {
        Date date = new Date();
        return getJulianFromDate(date);
    }
    
    public static String getJulianFromDate(Date date)
    {
        StringBuilder sb = new StringBuilder();
        Calendar cal  = Calendar.getInstance();
        cal.setTime(date);

        String year = String.valueOf(cal.get(Calendar.YEAR));
        String day = String.format("%03d", cal.get(Calendar.DAY_OF_YEAR));
        String hour = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY));

        return  sb.append(year.substring(year.length()-1)).append(day).append(hour).toString();
    }
    
    public static boolean validateStringValue(String data)
    {
        return (data != null && !data.isEmpty());
    }
    
    public static boolean validateStringValue(String data, int maxSize)
    {
        return (data != null && !data.isEmpty() && data.length() <= maxSize);
    }
    
    public static String addExtraChar(String str, int maxlength, String chartoadd) {
        String newstr = "";
        for (int j = 0; j < (maxlength - str.length()); j++) {
            newstr += chartoadd;
        }
        return newstr + str;
    }
}
