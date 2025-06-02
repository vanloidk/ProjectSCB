/**
 * PaymentExecutionSOAPStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package vn.com.scb.bian;

public class PaymentExecutionSOAPStub extends org.apache.axis.client.Stub implements vn.com.scb.bian.PaymentExecution_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[4];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("evaluatePaymentExecution");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("bian.scb.com.vn", "evaluatePaymentExecution_in"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("bian.scb.com.vn", "evaluatePaymentExecution_in_Type"), vn.com.scb.bian.EvaluatePaymentExecution_in_Type.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("bian.scb.com.vn", "evaluatePaymentExecution_out_Type"));
        oper.setReturnClass(vn.com.scb.bian.EvaluatePaymentExecution_out_Type.class);
        oper.setReturnQName(new javax.xml.namespace.QName("bian.scb.com.vn", "evaluatePaymentExecution_out"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executePaymentTransactionExternal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionExternal_in"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionExternal_in_Type"), vn.com.scb.bian.ExecutePaymentTransactionExternal_in_Type.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionExternal_out_Type"));
        oper.setReturnClass(vn.com.scb.bian.ExecutePaymentTransactionExternal_out_Type.class);
        oper.setReturnQName(new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionExternal_out"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executePaymentTransactionInternal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionInternal_in"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionInternal_in_Type"), vn.com.scb.bian.ExecutePaymentTransactionInternal_in_Type.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionInternal_out_Type"));
        oper.setReturnClass(vn.com.scb.bian.ExecutePaymentTransactionInternal_out_Type.class);
        oper.setReturnQName(new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionInternal_out"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("requestPaymentTransactionCancelation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("bian.scb.com.vn", "requestPaymentTransactionCancelation_in"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("bian.scb.com.vn", "requestPaymentTransactionCancelation_in_Type"), vn.com.scb.bian.RequestPaymentTransactionCancelation_in_Type.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("bian.scb.com.vn", "requestPaymentTransactionCancelation_out_Type"));
        oper.setReturnClass(vn.com.scb.bian.RequestPaymentTransactionCancelation_out_Type.class);
        oper.setReturnQName(new javax.xml.namespace.QName("bian.scb.com.vn", "requestPaymentTransactionCancelation_out"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

    }

    public PaymentExecutionSOAPStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public PaymentExecutionSOAPStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public PaymentExecutionSOAPStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "AccountInfoType");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.AccountInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "BenificialInfoType");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.BenificialInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "CIFDataType");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.CIFDataType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "CoreBankAccountType");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.CoreBankAccountType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "evaluatePaymentExecution_in_Type");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.EvaluatePaymentExecution_in_Type.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "evaluatePaymentExecution_out_Type");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.EvaluatePaymentExecution_out_Type.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionExternal_in_Type");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.ExecutePaymentTransactionExternal_in_Type.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionExternal_out_Type");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.ExecutePaymentTransactionExternal_out_Type.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionInternal_in_Type");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.ExecutePaymentTransactionInternal_in_Type.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "executePaymentTransactionInternal_out_Type");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.ExecutePaymentTransactionInternal_out_Type.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "FundTransferInfoType");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.FundTransferInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "IDInfoType");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.IDInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "PayinType");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.PayinType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "PayoutType");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.PayoutType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "RedemptionType");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.RedemptionType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "requestPaymentTransactionCancelation_in_Type");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.RequestPaymentTransactionCancelation_in_Type.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "requestPaymentTransactionCancelation_out_Type");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.RequestPaymentTransactionCancelation_out_Type.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "SenderInfoType");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.SenderInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("bian.scb.com.vn", "TransactionInfoType");
            cachedSerQNames.add(qName);
            cls = vn.com.scb.bian.TransactionInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

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

    public vn.com.scb.bian.EvaluatePaymentExecution_out_Type evaluatePaymentExecution(vn.com.scb.bian.EvaluatePaymentExecution_in_Type parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("bian.scb.com.vn/evaluatePaymentExecution");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "evaluatePaymentExecution"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (vn.com.scb.bian.EvaluatePaymentExecution_out_Type) _resp;
            } catch (java.lang.Exception _exception) {
                return (vn.com.scb.bian.EvaluatePaymentExecution_out_Type) org.apache.axis.utils.JavaUtils.convert(_resp, vn.com.scb.bian.EvaluatePaymentExecution_out_Type.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public vn.com.scb.bian.ExecutePaymentTransactionExternal_out_Type executePaymentTransactionExternal(vn.com.scb.bian.ExecutePaymentTransactionExternal_in_Type parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("bian.scb.com.vn/executePaymentTransactionExternal");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "executePaymentTransactionExternal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (vn.com.scb.bian.ExecutePaymentTransactionExternal_out_Type) _resp;
            } catch (java.lang.Exception _exception) {
                return (vn.com.scb.bian.ExecutePaymentTransactionExternal_out_Type) org.apache.axis.utils.JavaUtils.convert(_resp, vn.com.scb.bian.ExecutePaymentTransactionExternal_out_Type.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public vn.com.scb.bian.ExecutePaymentTransactionInternal_out_Type executePaymentTransactionInternal(vn.com.scb.bian.ExecutePaymentTransactionInternal_in_Type parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("bian.scb.com.vn/executePaymentTransactionInternal");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "executePaymentTransactionInternal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (vn.com.scb.bian.ExecutePaymentTransactionInternal_out_Type) _resp;
            } catch (java.lang.Exception _exception) {
                return (vn.com.scb.bian.ExecutePaymentTransactionInternal_out_Type) org.apache.axis.utils.JavaUtils.convert(_resp, vn.com.scb.bian.ExecutePaymentTransactionInternal_out_Type.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public vn.com.scb.bian.RequestPaymentTransactionCancelation_out_Type requestPaymentTransactionCancelation(vn.com.scb.bian.RequestPaymentTransactionCancelation_in_Type parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("bian.scb.com.vn/requestPaymentTransactionCancelation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "requestPaymentTransactionCancelation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (vn.com.scb.bian.RequestPaymentTransactionCancelation_out_Type) _resp;
            } catch (java.lang.Exception _exception) {
                return (vn.com.scb.bian.RequestPaymentTransactionCancelation_out_Type) org.apache.axis.utils.JavaUtils.convert(_resp, vn.com.scb.bian.RequestPaymentTransactionCancelation_out_Type.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
