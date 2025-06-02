/**
 * AuthenticationServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws.internal.tokenkey.tnb;

public class AuthenticationServiceSoapBindingStub extends org.apache.axis.client.Stub implements ws.internal.tokenkey.tnb.AuthenticationService_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[8];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ping");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "pingCallParms"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", ">pingCallParms"), ws.internal.tokenkey.tnb.com.entrust.NameValue[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationSystemFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationServiceFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAnonymousChallenge");
        oper.setReturnType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericChallenge"));
        oper.setReturnClass(ws.internal.tokenkey.tnb.GenericChallenge.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "getAnonymousChallengeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationSystemFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationServiceFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAnonymousChallengeForGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "getAnonymousChallengeForGroupCallParms"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GetAnonymousChallengeForGroupCallParms"), ws.internal.tokenkey.tnb.GetAnonymousChallengeForGroupCallParms.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericChallenge"));
        oper.setReturnClass(ws.internal.tokenkey.tnb.GenericChallenge.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "getAnonymousChallengeForGroupReturn"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationSystemFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationServiceFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("authenticateAnonymousChallenge");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "authenticateAnonymousChallengeCallParms"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticateAnonymousChallengeCallParms"), ws.internal.tokenkey.tnb.AuthenticateAnonymousChallengeCallParms.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericAuthenticateResponse"));
        oper.setReturnClass(ws.internal.tokenkey.tnb.GenericAuthenticateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "authenticateAnonymousChallengeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationSystemFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationServiceFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllowedAuthenticationTypes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "getAllowedAuthenticationTypesCallParms"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GetAllowedAuthenticationTypesCallParms"), ws.internal.tokenkey.tnb.GetAllowedAuthenticationTypesCallParms.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AllowedAuthenticationTypes"));
        oper.setReturnClass(ws.internal.tokenkey.tnb.AllowedAuthenticationTypes.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "getAllowedAuthenticationTypesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationSystemFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationServiceFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllowedAuthenticationTypesForGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "getAllowedAuthenticationTypesForGroupCallParms"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GetAllowedAuthenticationTypesForGroupCallParms"), ws.internal.tokenkey.tnb.GetAllowedAuthenticationTypesForGroupCallParms.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AllowedAuthenticationTypes"));
        oper.setReturnClass(ws.internal.tokenkey.tnb.AllowedAuthenticationTypes.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "getAllowedAuthenticationTypesForGroupReturn"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationSystemFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationServiceFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getGenericChallenge");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "getGenericChallengeCallParms"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GetGenericChallengeCallParms"), ws.internal.tokenkey.tnb.GetGenericChallengeCallParms.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericChallenge"));
        oper.setReturnClass(ws.internal.tokenkey.tnb.GenericChallenge.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "getGenericChallengeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationSystemFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationServiceFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("authenticateGenericChallenge");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "authenticateGenericChallengeCallParms"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticateGenericChallengeCallParms"), ws.internal.tokenkey.tnb.AuthenticateGenericChallengeCallParms.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericAuthenticateResponse"));
        oper.setReturnClass(ws.internal.tokenkey.tnb.GenericAuthenticateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "authenticateGenericChallengeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationPasswordChangeRequiredFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationPasswordChangeRequiredFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationPasswordChangeRequiredFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationSystemFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"),
                      "wsdl.authenticationV5.ig.com.entrust.AuthenticationServiceFault",
                      new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault"), 
                      true
                     ));
        _operations[7] = oper;

    }

    public AuthenticationServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public AuthenticationServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public AuthenticationServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", ">pingCallParms");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.NameValue[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "NameValue");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AllowedAuthenticationTypes");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.AllowedAuthenticationTypes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticateAnonymousChallengeCallParms");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.AuthenticateAnonymousChallengeCallParms.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticateGenericChallengeCallParms");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.AuthenticateGenericChallengeCallParms.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationFault");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.AuthenticationFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationPasswordChangeRequiredFault");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.AuthenticationPasswordChangeRequiredFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSecretParms");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.AuthenticationSecretParms.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationServiceFault");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.AuthenticationServiceFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationSystemFault");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.AuthenticationSystemFault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "CertificateAuthenticationStatus");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.CertificateAuthenticationStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "ChallengeRequestResult");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "CHAPResponse");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.CHAPResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "DeliveryMechanism");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.DeliveryMechanism.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "ErrorCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "ExternalChallenge");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.ExternalChallenge.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "ExternalRiskScoreParms");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.ExternalRiskScoreParms.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "ExternalRiskScoreResult");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericAuthenticateParms");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.GenericAuthenticateParms.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericAuthenticateResponse");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.GenericAuthenticateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericChallenge");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.GenericChallenge.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GenericChallengeParms");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.GenericChallengeParms.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GetAllowedAuthenticationTypesCallParms");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.GetAllowedAuthenticationTypesCallParms.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GetAllowedAuthenticationTypesForGroupCallParms");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.GetAllowedAuthenticationTypesForGroupCallParms.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GetAnonymousChallengeForGroupCallParms");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.GetAnonymousChallengeForGroupCallParms.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "GetGenericChallengeCallParms");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.GetGenericChallengeCallParms.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "IPAuthenticationStatus");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.IPAuthenticationStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "ListOf_AuthenticationFault");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.AuthenticationFault[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "AuthenticationFault");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "ListOf_DeliveryMechanism");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.DeliveryMechanism[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "DeliveryMechanism");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "ListOf_xsd_byte");
            cachedSerQNames.add(qName);
            cls = byte[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MachineAuthenticationStatus");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.MachineAuthenticationStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MachineSecret");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.MachineSecret.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MachineSecretPolicy");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.MachineSecretPolicy.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MSCHAPv1Response");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.MSCHAPv1Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "MSCHAPv2Response");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.MSCHAPv2Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "OTPChallenge");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.OTPChallenge.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "PAPResponse");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.PAPResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "PasswordChallenge");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.PasswordChallenge.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "PasswordInfo");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.PasswordInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "PVNInfo");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.PVNInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "RadiusResponse");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.RadiusResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "Response");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "RiskScoringResult");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.RiskScoringResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "SecurityLevel");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "SharedSecretParms");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.SharedSecretParms.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:authenticationV5:wsdl", "TransactionReceiptInfo");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.TransactionReceiptInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationInfo");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.AuthenticationInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationType");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "CardData");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.CardData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "CertificateChallenge");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.CertificateChallenge.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "CertificateData");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.CertificateData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "Challenge");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.Challenge.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "ChallengeSet");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.ChallengeSet.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "IPLocation");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.IPLocation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "ListOf_AuthenticationType");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "AuthenticationType");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "ListOf_CertificateData");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.CertificateData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "CertificateData");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "ListOf_Challenge");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.Challenge[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "Challenge");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "ListOf_NameValue");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.NameValue[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "NameValue");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "ListOf_NameValues");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.NameValues[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "NameValues");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "ListOf_SharedSecret");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.SharedSecret[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "SharedSecret");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "ListOf_TokenData");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.TokenData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "TokenData");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "ListOf_xsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "NameValue");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.NameValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "NameValues");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.NameValues.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "PasswordRules");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.PasswordRules.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "SharedSecret");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.SharedSecret.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "TokenChallenge");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.TokenChallenge.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "TokenData");
            cachedSerQNames.add(qName);
            cls = ws.internal.tokenkey.tnb.com.entrust.TokenData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:entrust.com:ig:commonV5", "TransactionSignatureType");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public void ping(ws.internal.tokenkey.tnb.com.entrust.NameValue[] pingCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ping"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pingCallParms});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationSystemFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationSystemFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationServiceFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationServiceFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ws.internal.tokenkey.tnb.GenericChallenge getAnonymousChallenge() throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getAnonymousChallenge"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ws.internal.tokenkey.tnb.GenericChallenge) _resp;
            } catch (java.lang.Exception _exception) {
                return (ws.internal.tokenkey.tnb.GenericChallenge) org.apache.axis.utils.JavaUtils.convert(_resp, ws.internal.tokenkey.tnb.GenericChallenge.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationSystemFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationSystemFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationServiceFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationServiceFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ws.internal.tokenkey.tnb.GenericChallenge getAnonymousChallengeForGroup(ws.internal.tokenkey.tnb.GetAnonymousChallengeForGroupCallParms getAnonymousChallengeForGroupCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getAnonymousChallengeForGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getAnonymousChallengeForGroupCallParms});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ws.internal.tokenkey.tnb.GenericChallenge) _resp;
            } catch (java.lang.Exception _exception) {
                return (ws.internal.tokenkey.tnb.GenericChallenge) org.apache.axis.utils.JavaUtils.convert(_resp, ws.internal.tokenkey.tnb.GenericChallenge.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationSystemFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationSystemFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationServiceFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationServiceFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ws.internal.tokenkey.tnb.GenericAuthenticateResponse authenticateAnonymousChallenge(ws.internal.tokenkey.tnb.AuthenticateAnonymousChallengeCallParms authenticateAnonymousChallengeCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "authenticateAnonymousChallenge"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {authenticateAnonymousChallengeCallParms});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ws.internal.tokenkey.tnb.GenericAuthenticateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (ws.internal.tokenkey.tnb.GenericAuthenticateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, ws.internal.tokenkey.tnb.GenericAuthenticateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationSystemFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationSystemFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationServiceFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationServiceFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ws.internal.tokenkey.tnb.AllowedAuthenticationTypes getAllowedAuthenticationTypes(ws.internal.tokenkey.tnb.GetAllowedAuthenticationTypesCallParms getAllowedAuthenticationTypesCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getAllowedAuthenticationTypes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getAllowedAuthenticationTypesCallParms});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ws.internal.tokenkey.tnb.AllowedAuthenticationTypes) _resp;
            } catch (java.lang.Exception _exception) {
                return (ws.internal.tokenkey.tnb.AllowedAuthenticationTypes) org.apache.axis.utils.JavaUtils.convert(_resp, ws.internal.tokenkey.tnb.AllowedAuthenticationTypes.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationSystemFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationSystemFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationServiceFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationServiceFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ws.internal.tokenkey.tnb.AllowedAuthenticationTypes getAllowedAuthenticationTypesForGroup(ws.internal.tokenkey.tnb.GetAllowedAuthenticationTypesForGroupCallParms getAllowedAuthenticationTypesForGroupCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getAllowedAuthenticationTypesForGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getAllowedAuthenticationTypesForGroupCallParms});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ws.internal.tokenkey.tnb.AllowedAuthenticationTypes) _resp;
            } catch (java.lang.Exception _exception) {
                return (ws.internal.tokenkey.tnb.AllowedAuthenticationTypes) org.apache.axis.utils.JavaUtils.convert(_resp, ws.internal.tokenkey.tnb.AllowedAuthenticationTypes.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationSystemFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationSystemFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationServiceFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationServiceFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ws.internal.tokenkey.tnb.GenericChallenge getGenericChallenge(ws.internal.tokenkey.tnb.GetGenericChallengeCallParms getGenericChallengeCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getGenericChallenge"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getGenericChallengeCallParms});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ws.internal.tokenkey.tnb.GenericChallenge) _resp;
            } catch (java.lang.Exception _exception) {
                return (ws.internal.tokenkey.tnb.GenericChallenge) org.apache.axis.utils.JavaUtils.convert(_resp, ws.internal.tokenkey.tnb.GenericChallenge.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationSystemFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationSystemFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationServiceFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationServiceFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ws.internal.tokenkey.tnb.GenericAuthenticateResponse authenticateGenericChallenge(ws.internal.tokenkey.tnb.AuthenticateGenericChallengeCallParms authenticateGenericChallengeCallParms) throws java.rmi.RemoteException, ws.internal.tokenkey.tnb.AuthenticationPasswordChangeRequiredFault, ws.internal.tokenkey.tnb.AuthenticationSystemFault, ws.internal.tokenkey.tnb.AuthenticationServiceFault {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "authenticateGenericChallenge"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {authenticateGenericChallengeCallParms});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ws.internal.tokenkey.tnb.GenericAuthenticateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (ws.internal.tokenkey.tnb.GenericAuthenticateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, ws.internal.tokenkey.tnb.GenericAuthenticateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationPasswordChangeRequiredFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationPasswordChangeRequiredFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationSystemFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationSystemFault) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ws.internal.tokenkey.tnb.AuthenticationServiceFault) {
              throw (ws.internal.tokenkey.tnb.AuthenticationServiceFault) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
