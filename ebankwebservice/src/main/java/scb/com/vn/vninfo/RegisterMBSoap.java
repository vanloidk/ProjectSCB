/**
 * RegisterMBSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package scb.com.vn.vninfo;

/**
 *
 * @author minhndb
 */
public interface RegisterMBSoap extends java.rmi.Remote {

    /**
     * MD5
     * @param inputmd5
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException
     */
    public java.lang.String md5Test(java.lang.String inputmd5, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Gói sử dụng dịch vụ Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException
     */
    public java.lang.String getCusTypeByMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Đăng ký Mobile Banking trên Internet Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException 
     */
    public java.lang.String registerCustomerMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Kiểm tra tên đăng nhập đã đăng ký Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException
     */
    public java.lang.String checkCustomerRegisterMoblieBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Mở Khóa dịch vụ Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException 
     */
    public java.lang.String unLockMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Khóa dịch vụ Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException 
     */
    public java.lang.String lockMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Cấp lại mật khẩu Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException 
     */
    public java.lang.String resetPasswordMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Lấy thông tin khách hàng đăng ký Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException 
     */
    public java.lang.String getCustomerInfoByMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Tạo mới mật khẩu Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException 
     */
    public java.lang.String encryptPasswordMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Gói sử dụng dịch vụ Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException
     */
    public java.lang.String getPackageByMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Cancel dịch vụ Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException 
     */
    public java.lang.String cancelMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Modify dịch vụ Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException
     */
    public java.lang.String modifyMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Khóa dịch vụ Mobile Banking từ hệ thống thu phí của SCB
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException
     */
    public java.lang.String lockFeeMobileBankingOfSCB(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Mở Khóa dịch vụ Mobile Banking từ hệ thống thu phí của SCB
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException
     */
    public java.lang.String unLockFeeMobileBankingOfSCB(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;

    /**
     * Validate mật khẩu Mobile Banking
     * @param xmlString
     * @param signData
     * @return 
     * @throws java.rmi.RemoteException 
     */
    public java.lang.String validatePasswordMobileBanking(java.lang.String xmlString, java.lang.String signData) throws java.rmi.RemoteException;
}
