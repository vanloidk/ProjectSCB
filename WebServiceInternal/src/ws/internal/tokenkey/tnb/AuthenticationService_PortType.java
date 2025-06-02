/**
 * AuthenticationService_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public interface AuthenticationService_PortType extends java.rmi.Remote {
    public void ping(ws.internal.tokenkey.tnb.com.entrust.NameValue[] pingCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault;
    public ws.internal.tokenkey.tnb.GenericChallenge getAnonymousChallenge() throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault;
    public ws.internal.tokenkey.tnb.GenericChallenge getAnonymousChallengeForGroup(ws.internal.tokenkey.tnb.GetAnonymousChallengeForGroupCallParms getAnonymousChallengeForGroupCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault;
    public ws.internal.tokenkey.tnb.GenericAuthenticateResponse authenticateAnonymousChallenge(ws.internal.tokenkey.tnb.AuthenticateAnonymousChallengeCallParms authenticateAnonymousChallengeCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault;
    public ws.internal.tokenkey.tnb.AllowedAuthenticationTypes getAllowedAuthenticationTypes(ws.internal.tokenkey.tnb.GetAllowedAuthenticationTypesCallParms getAllowedAuthenticationTypesCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault;
    public ws.internal.tokenkey.tnb.AllowedAuthenticationTypes getAllowedAuthenticationTypesForGroup(ws.internal.tokenkey.tnb.GetAllowedAuthenticationTypesForGroupCallParms getAllowedAuthenticationTypesForGroupCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault;
    public ws.internal.tokenkey.tnb.GenericChallenge getGenericChallenge(ws.internal.tokenkey.tnb.GetGenericChallengeCallParms getGenericChallengeCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault;
    public ws.internal.tokenkey.tnb.GenericAuthenticateResponse authenticateGenericChallenge(ws.internal.tokenkey.tnb.AuthenticateGenericChallengeCallParms authenticateGenericChallengeCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationPasswordChangeRequiredFault, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault;
}
