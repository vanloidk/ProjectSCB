
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.5  Built on : May 06, 2017 (03:45:50 BST)
 */

        
            package ws.internal.payment.evnhcm;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://tempuri.org/".equals(namespaceURI) &&
                  "BankRequestAddressResult_type0".equals(typeName)){
                   
                            return  ws.internal.payment.evnhcm.BankRequestAddressResult_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://tempuri.org/".equals(namespaceURI) &&
                  "BankRequestMaDLResult_type0".equals(typeName)){
                   
                            return  ws.internal.payment.evnhcm.BankRequestMaDLResult_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://tempuri.org/".equals(namespaceURI) &&
                  "BankRequestResult_type0".equals(typeName)){
                   
                            return  ws.internal.payment.evnhcm.BankRequestResult_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://tempuri.org/".equals(namespaceURI) &&
                  "BankCheckConfirmResult_type0".equals(typeName)){
                   
                            return  ws.internal.payment.evnhcm.BankCheckConfirmResult_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://tempuri.org/".equals(namespaceURI) &&
                  "BankRequestDurationResult_type0".equals(typeName)){
                   
                            return  ws.internal.payment.evnhcm.BankRequestDurationResult_type0.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    