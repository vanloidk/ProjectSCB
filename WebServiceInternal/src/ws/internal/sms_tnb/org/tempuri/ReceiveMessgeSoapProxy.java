package ws.internal.sms_tnb.org.tempuri;

public class ReceiveMessgeSoapProxy implements ws.internal.sms_tnb.org.tempuri.ReceiveMessgeSoap {
  private String _endpoint = null;
  private ws.internal.sms_tnb.org.tempuri.ReceiveMessgeSoap receiveMessgeSoap = null;
  
  public ReceiveMessgeSoapProxy() {
    _initReceiveMessgeSoapProxy();
  }
  
  public ReceiveMessgeSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initReceiveMessgeSoapProxy();
  }
  
  private void _initReceiveMessgeSoapProxy() {
    try {
      receiveMessgeSoap = (new ws.internal.sms_tnb.org.tempuri.ReceiveMessgeLocator()).getReceiveMessgeSoap();
      if (receiveMessgeSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)receiveMessgeSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)receiveMessgeSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (receiveMessgeSoap != null)
      ((javax.xml.rpc.Stub)receiveMessgeSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ws.internal.sms_tnb.org.tempuri.ReceiveMessgeSoap getReceiveMessgeSoap() {
    if (receiveMessgeSoap == null)
      _initReceiveMessgeSoapProxy();
    return receiveMessgeSoap;
  }
  
  public java.lang.String messageReceiver(java.lang.String userID, java.lang.String serviceID, java.lang.String message, java.lang.String moID, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (receiveMessgeSoap == null)
      _initReceiveMessgeSoapProxy();
    return receiveMessgeSoap.messageReceiver(userID, serviceID, message, moID, username, password);
  }
  
  
}