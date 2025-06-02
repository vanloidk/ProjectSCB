package ws.internal.payment.vnpay.navigation;

public class WSBeanProxy implements ws.internal.payment.vnpay.navigation.WSBean {
  private String _endpoint = null;
  private ws.internal.payment.vnpay.navigation.WSBean wSBean = null;
  
  public WSBeanProxy() {
    _initWSBeanProxy();
  }
  
  public WSBeanProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSBeanProxy();
  }
  
  private void _initWSBeanProxy() {
    try {
      wSBean = (new ws.internal.payment.vnpay.navigation.WSBeanServiceLocator()).getWSBean();
      if (wSBean != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSBean)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSBean)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSBean != null)
      ((javax.xml.rpc.Stub)wSBean)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ws.internal.payment.vnpay.navigation.WSBean getWSBean() {
    if (wSBean == null)
      _initWSBeanProxy();
    return wSBean;
  }
  
  public java.lang.String getString(java.lang.String str) throws java.rmi.RemoteException{
    if (wSBean == null)
      _initWSBeanProxy();
    return wSBean.getString(str);
  }
  
  public java.lang.String requestTransaction(java.lang.String requestTrans) throws java.rmi.RemoteException{
    if (wSBean == null)
      _initWSBeanProxy();
    return wSBean.requestTransaction(requestTrans);
  }
  
  public java.lang.String requestUploadFileToVNPAY(java.lang.String filename) throws java.rmi.RemoteException{
    if (wSBean == null)
      _initWSBeanProxy();
    return wSBean.requestUploadFileToVNPAY(filename);
  }
  
  public java.lang.String requestDownloadFileToVNPAY(java.lang.String filename) throws java.rmi.RemoteException{
    if (wSBean == null)
      _initWSBeanProxy();
    return wSBean.requestDownloadFileToVNPAY(filename);
  }
  
  
}