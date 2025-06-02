package scb.com.vn.ultility.rmi;

import java.rmi.Naming;
import java.rmi.Remote;

public class RemoteStub<E> {

	/***
	 * 
	 * @param url
	 *            : rmi://host:port/service
	 * @return
	 * @return
	 */
	public static final Remote get(String url) {
		try {
			return Naming.lookup(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
