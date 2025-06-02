package ws.internal.payment.vnpay;

public class VnpaySrvSoapProxy implements VnpaySrvSoap {
  private String _endpoint = null;
  private VnpaySrvSoap vnpaySrvSoap = null;
  
  public VnpaySrvSoapProxy() {
    _initVnpaySrvSoapProxy();
  }
  
  public VnpaySrvSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initVnpaySrvSoapProxy();
  }
  
  private void _initVnpaySrvSoapProxy() {
    try {
      vnpaySrvSoap = (new VnpaySrvLocator()).getVnpaySrvSoap();
      if (vnpaySrvSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)vnpaySrvSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)vnpaySrvSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (vnpaySrvSoap != null)
      ((javax.xml.rpc.Stub)vnpaySrvSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public VnpaySrvSoap getVnpaySrvSoap() {
    if (vnpaySrvSoap == null)
      _initVnpaySrvSoapProxy();
    return vnpaySrvSoap;
  }
  
  public java.lang.String requestTransaction(java.lang.String sRequestTransaction) throws java.rmi.RemoteException{
    if (vnpaySrvSoap == null)
      _initVnpaySrvSoapProxy();
    return vnpaySrvSoap.requestTransaction(sRequestTransaction);
  }
  
  
}