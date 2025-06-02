package scb.com.vn.ultility;

import org.apache.log4j.Logger;

public class Log {
	static Logger logger = null;

	public static void writeLog(String classname, String msg) {
		try {
			logger = Logger.getLogger(classname);
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
