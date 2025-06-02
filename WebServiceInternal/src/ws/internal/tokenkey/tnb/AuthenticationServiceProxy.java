package ws.internal.tokenkey.tnb;

public class AuthenticationServiceProxy implements ws.internal.tokenkey.tnb.AuthenticationService_PortType {
  private String _endpoint = null;
  private ws.internal.tokenkey.tnb.AuthenticationService_PortType authenticationService_PortType = null;
  
  public AuthenticationServiceProxy() {
    _initAuthenticationServiceProxy();
  }
  
  public AuthenticationServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initAuthenticationServiceProxy();
  }
  
  private void _initAuthenticationServiceProxy() {
    try {
      authenticationService_PortType = (new ws.internal.tokenkey.tnb.AuthenticationService_ServiceLocator()).getAuthenticationService();
      if (authenticationService_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)authenticationService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)authenticationService_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (authenticationService_PortType != null)
      ((javax.xml.rpc.Stub)authenticationService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ws.internal.tokenkey.tnb.AuthenticationService_PortType getAuthenticationService_PortType() {
    if (authenticationService_PortType == null)
      _initAuthenticationServiceProxy();
    return authenticationService_PortType;
  }
  
  public void ping(ws.internal.tokenkey.tnb.com.entrust.NameValue[] pingCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault{
    if (authenticationService_PortType == null)
      _initAuthenticationServiceProxy();
    authenticationService_PortType.ping(pingCallParms);
  }
  
  public ws.internal.tokenkey.tnb.GenericChallenge getAnonymousChallenge() throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault{
    if (authenticationService_PortType == null)
      _initAuthenticationServiceProxy();
    return authenticationService_PortType.getAnonymousChallenge();
  }
  
  public ws.internal.tokenkey.tnb.GenericChallenge getAnonymousChallengeForGroup(ws.internal.tokenkey.tnb.GetAnonymousChallengeForGroupCallParms getAnonymousChallengeForGroupCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault{
    if (authenticationService_PortType == null)
      _initAuthenticationServiceProxy();
    return authenticationService_PortType.getAnonymousChallengeForGroup(getAnonymousChallengeForGroupCallParms);
  }
  
  public ws.internal.tokenkey.tnb.GenericAuthenticateResponse authenticateAnonymousChallenge(ws.internal.tokenkey.tnb.AuthenticateAnonymousChallengeCallParms authenticateAnonymousChallengeCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault{
    if (authenticationService_PortType == null)
      _initAuthenticationServiceProxy();
    return authenticationService_PortType.authenticateAnonymousChallenge(authenticateAnonymousChallengeCallParms);
  }
  
  public ws.internal.tokenkey.tnb.AllowedAuthenticationTypes getAllowedAuthenticationTypes(ws.internal.tokenkey.tnb.GetAllowedAuthenticationTypesCallParms getAllowedAuthenticationTypesCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault{
    if (authenticationService_PortType == null)
      _initAuthenticationServiceProxy();
    return authenticationService_PortType.getAllowedAuthenticationTypes(getAllowedAuthenticationTypesCallParms);
  }
  
  public ws.internal.tokenkey.tnb.AllowedAuthenticationTypes getAllowedAuthenticationTypesForGroup(ws.internal.tokenkey.tnb.GetAllowedAuthenticationTypesForGroupCallParms getAllowedAuthenticationTypesForGroupCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault{
    if (authenticationService_PortType == null)
      _initAuthenticationServiceProxy();
    return authenticationService_PortType.getAllowedAuthenticationTypesForGroup(getAllowedAuthenticationTypesForGroupCallParms);
  }
  
  public ws.internal.tokenkey.tnb.GenericChallenge getGenericChallenge(ws.internal.tokenkey.tnb.GetGenericChallengeCallParms getGenericChallengeCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault{
    if (authenticationService_PortType == null)
      _initAuthenticationServiceProxy();
    return authenticationService_PortType.getGenericChallenge(getGenericChallengeCallParms);
  }
  
  public ws.internal.tokenkey.tnb.GenericAuthenticateResponse authenticateGenericChallenge(ws.internal.tokenkey.tnb.AuthenticateGenericChallengeCallParms authenticateGenericChallengeCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationPasswordChangeRequiredFault, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault{
    if (authenticationService_PortType == null)
      _initAuthenticationServiceProxy();
    return authenticationService_PortType.authenticateGenericChallenge(authenticateGenericChallengeCallParms);
  }
  
  
}