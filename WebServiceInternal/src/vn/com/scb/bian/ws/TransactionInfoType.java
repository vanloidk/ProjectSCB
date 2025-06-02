
/**
 * TransactionInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.5  Built on : May 06, 2017 (03:45:50 BST)
 */

            
                package vn.com.scb.bian.ws;
            
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


            /**
            *  TransactionInfoType bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class TransactionInfoType
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = TransactionInfoType
                Namespace URI = bian.scb.com.vn
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for CoreRefNum
                        */

                        
                                    protected java.lang.String localCoreRefNum ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCoreRefNumTracker = false ;

                           public boolean isCoreRefNumSpecified(){
                               return localCoreRefNumTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCoreRefNum(){
                               return localCoreRefNum;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CoreRefNum
                               */
                               public void setCoreRefNum(java.lang.String param){
                            localCoreRefNumTracker = param != null;
                                   
                                            this.localCoreRefNum=param;
                                       

                               }
                            

                        /**
                        * field for CRefNum
                        */

                        
                                    protected java.lang.String localCRefNum ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCRefNumTracker = false ;

                           public boolean isCRefNumSpecified(){
                               return localCRefNumTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCRefNum(){
                               return localCRefNum;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CRefNum
                               */
                               public void setCRefNum(java.lang.String param){
                            localCRefNumTracker = param != null;
                                   
                                            this.localCRefNum=param;
                                       

                               }
                            

                        /**
                        * field for PRefNum
                        */

                        
                                    protected java.lang.String localPRefNum ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPRefNumTracker = false ;

                           public boolean isPRefNumSpecified(){
                               return localPRefNumTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPRefNum(){
                               return localPRefNum;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PRefNum
                               */
                               public void setPRefNum(java.lang.String param){
                            localPRefNumTracker = param != null;
                                   
                                            this.localPRefNum=param;
                                       

                               }
                            

                        /**
                        * field for TransactionStartTime
                        */

                        
                                    protected java.lang.String localTransactionStartTime ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionStartTimeTracker = false ;

                           public boolean isTransactionStartTimeSpecified(){
                               return localTransactionStartTimeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionStartTime(){
                               return localTransactionStartTime;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionStartTime
                               */
                               public void setTransactionStartTime(java.lang.String param){
                            localTransactionStartTimeTracker = param != null;
                                   
                                            this.localTransactionStartTime=param;
                                       

                               }
                            

                        /**
                        * field for TransactionCompletedTime
                        */

                        
                                    protected java.lang.String localTransactionCompletedTime ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionCompletedTimeTracker = false ;

                           public boolean isTransactionCompletedTimeSpecified(){
                               return localTransactionCompletedTimeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionCompletedTime(){
                               return localTransactionCompletedTime;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionCompletedTime
                               */
                               public void setTransactionCompletedTime(java.lang.String param){
                            localTransactionCompletedTimeTracker = param != null;
                                   
                                            this.localTransactionCompletedTime=param;
                                       

                               }
                            

                        /**
                        * field for TransactionProcessTime
                        */

                        
                                    protected java.lang.String localTransactionProcessTime ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionProcessTimeTracker = false ;

                           public boolean isTransactionProcessTimeSpecified(){
                               return localTransactionProcessTimeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionProcessTime(){
                               return localTransactionProcessTime;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionProcessTime
                               */
                               public void setTransactionProcessTime(java.lang.String param){
                            localTransactionProcessTimeTracker = param != null;
                                   
                                            this.localTransactionProcessTime=param;
                                       

                               }
                            

                        /**
                        * field for MaxTimeout
                        */

                        
                                    protected java.lang.String localMaxTimeout ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMaxTimeoutTracker = false ;

                           public boolean isMaxTimeoutSpecified(){
                               return localMaxTimeoutTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMaxTimeout(){
                               return localMaxTimeout;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MaxTimeout
                               */
                               public void setMaxTimeout(java.lang.String param){
                            localMaxTimeoutTracker = param != null;
                                   
                                            this.localMaxTimeout=param;
                                       

                               }
                            

                        /**
                        * field for TransactionErrorCode
                        */

                        
                                    protected java.lang.String localTransactionErrorCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionErrorCodeTracker = false ;

                           public boolean isTransactionErrorCodeSpecified(){
                               return localTransactionErrorCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionErrorCode(){
                               return localTransactionErrorCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionErrorCode
                               */
                               public void setTransactionErrorCode(java.lang.String param){
                            localTransactionErrorCodeTracker = param != null;
                                   
                                            this.localTransactionErrorCode=param;
                                       

                               }
                            

                        /**
                        * field for TransactionErrorMsg
                        */

                        
                                    protected java.lang.String localTransactionErrorMsg ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionErrorMsgTracker = false ;

                           public boolean isTransactionErrorMsgSpecified(){
                               return localTransactionErrorMsgTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionErrorMsg(){
                               return localTransactionErrorMsg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionErrorMsg
                               */
                               public void setTransactionErrorMsg(java.lang.String param){
                            localTransactionErrorMsgTracker = param != null;
                                   
                                            this.localTransactionErrorMsg=param;
                                       

                               }
                            

                        /**
                        * field for TransactionReturn
                        */

                        
                                    protected int localTransactionReturn ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionReturnTracker = false ;

                           public boolean isTransactionReturnSpecified(){
                               return localTransactionReturnTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getTransactionReturn(){
                               return localTransactionReturn;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionReturn
                               */
                               public void setTransactionReturn(int param){
                            
                                       // setting primitive attribute tracker to true
                                       localTransactionReturnTracker =
                                       param != java.lang.Integer.MIN_VALUE;
                                   
                                            this.localTransactionReturn=param;
                                       

                               }
                            

                        /**
                        * field for TransactionReturnMsg
                        */

                        
                                    protected java.lang.String localTransactionReturnMsg ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionReturnMsgTracker = false ;

                           public boolean isTransactionReturnMsgSpecified(){
                               return localTransactionReturnMsgTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionReturnMsg(){
                               return localTransactionReturnMsg;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionReturnMsg
                               */
                               public void setTransactionReturnMsg(java.lang.String param){
                            localTransactionReturnMsgTracker = param != null;
                                   
                                            this.localTransactionReturnMsg=param;
                                       

                               }
                            

                        /**
                        * field for ClientCode
                        */

                        
                                    protected java.lang.String localClientCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localClientCodeTracker = false ;

                           public boolean isClientCodeSpecified(){
                               return localClientCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getClientCode(){
                               return localClientCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ClientCode
                               */
                               public void setClientCode(java.lang.String param){
                            localClientCodeTracker = param != null;
                                   
                                            this.localClientCode=param;
                                       

                               }
                            

                        /**
                        * field for IsInternalUse
                        */

                        
                                    protected int localIsInternalUse ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIsInternalUseTracker = false ;

                           public boolean isIsInternalUseSpecified(){
                               return localIsInternalUseTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getIsInternalUse(){
                               return localIsInternalUse;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IsInternalUse
                               */
                               public void setIsInternalUse(int param){
                            
                                       // setting primitive attribute tracker to true
                                       localIsInternalUseTracker =
                                       param != java.lang.Integer.MIN_VALUE;
                                   
                                            this.localIsInternalUse=param;
                                       

                               }
                            

                        /**
                        * field for ParentOperator
                        */

                        
                                    protected java.lang.String localParentOperator ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localParentOperatorTracker = false ;

                           public boolean isParentOperatorSpecified(){
                               return localParentOperatorTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getParentOperator(){
                               return localParentOperator;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ParentOperator
                               */
                               public void setParentOperator(java.lang.String param){
                            localParentOperatorTracker = param != null;
                                   
                                            this.localParentOperator=param;
                                       

                               }
                            

                        /**
                        * field for TransactionBatchID
                        */

                        
                                    protected java.lang.String localTransactionBatchID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionBatchIDTracker = false ;

                           public boolean isTransactionBatchIDSpecified(){
                               return localTransactionBatchIDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionBatchID(){
                               return localTransactionBatchID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionBatchID
                               */
                               public void setTransactionBatchID(java.lang.String param){
                            localTransactionBatchIDTracker = param != null;
                                   
                                            this.localTransactionBatchID=param;
                                       

                               }
                            

                        /**
                        * field for BranchInfo
                        */

                        
                                    protected vn.com.scb.bian.ws.BranchInfoType localBranchInfo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBranchInfoTracker = false ;

                           public boolean isBranchInfoSpecified(){
                               return localBranchInfoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.BranchInfoType
                           */
                           public  vn.com.scb.bian.ws.BranchInfoType getBranchInfo(){
                               return localBranchInfo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BranchInfo
                               */
                               public void setBranchInfo(vn.com.scb.bian.ws.BranchInfoType param){
                            localBranchInfoTracker = param != null;
                                   
                                            this.localBranchInfo=param;
                                       

                               }
                            

                        /**
                        * field for TransactionValDate
                        */

                        
                                    protected java.lang.String localTransactionValDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionValDateTracker = false ;

                           public boolean isTransactionValDateSpecified(){
                               return localTransactionValDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionValDate(){
                               return localTransactionValDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionValDate
                               */
                               public void setTransactionValDate(java.lang.String param){
                            localTransactionValDateTracker = param != null;
                                   
                                            this.localTransactionValDate=param;
                                       

                               }
                            

                        /**
                        * field for TransactionCurrency
                        */

                        
                                    protected java.lang.String localTransactionCurrency ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionCurrencyTracker = false ;

                           public boolean isTransactionCurrencySpecified(){
                               return localTransactionCurrencyTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionCurrency(){
                               return localTransactionCurrency;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionCurrency
                               */
                               public void setTransactionCurrency(java.lang.String param){
                            localTransactionCurrencyTracker = param != null;
                                   
                                            this.localTransactionCurrency=param;
                                       

                               }
                            

                        /**
                        * field for TransactionValue
                        */

                        
                                    protected java.lang.String localTransactionValue ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionValueTracker = false ;

                           public boolean isTransactionValueSpecified(){
                               return localTransactionValueTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionValue(){
                               return localTransactionValue;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionValue
                               */
                               public void setTransactionValue(java.lang.String param){
                            localTransactionValueTracker = param != null;
                                   
                                            this.localTransactionValue=param;
                                       

                               }
                            

                        /**
                        * field for TransactionValDate_ToDate
                        */

                        
                                    protected java.lang.String localTransactionValDate_ToDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionValDate_ToDateTracker = false ;

                           public boolean isTransactionValDate_ToDateSpecified(){
                               return localTransactionValDate_ToDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionValDate_ToDate(){
                               return localTransactionValDate_ToDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionValDate_ToDate
                               */
                               public void setTransactionValDate_ToDate(java.lang.String param){
                            localTransactionValDate_ToDateTracker = param != null;
                                   
                                            this.localTransactionValDate_ToDate=param;
                                       

                               }
                            

                        /**
                        * field for TransactionDate
                        */

                        
                                    protected java.lang.String localTransactionDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionDateTracker = false ;

                           public boolean isTransactionDateSpecified(){
                               return localTransactionDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionDate(){
                               return localTransactionDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionDate
                               */
                               public void setTransactionDate(java.lang.String param){
                            localTransactionDateTracker = param != null;
                                   
                                            this.localTransactionDate=param;
                                       

                               }
                            

                        /**
                        * field for TransactionBackDate
                        */

                        
                                    protected java.lang.String localTransactionBackDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTransactionBackDateTracker = false ;

                           public boolean isTransactionBackDateSpecified(){
                               return localTransactionBackDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTransactionBackDate(){
                               return localTransactionBackDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionBackDate
                               */
                               public void setTransactionBackDate(java.lang.String param){
                            localTransactionBackDateTracker = param != null;
                                   
                                            this.localTransactionBackDate=param;
                                       

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               return factory.createOMElement(new org.apache.axis2.databinding.ADBDataSource(this,parentQName));
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"bian.scb.com.vn");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":TransactionInfoType",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "TransactionInfoType",
                           xmlWriter);
                   }

               
                   }
                if (localCoreRefNumTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "coreRefNum", xmlWriter);
                             

                                          if (localCoreRefNum==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("coreRefNum cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCoreRefNum);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCRefNumTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "cRefNum", xmlWriter);
                             

                                          if (localCRefNum==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("cRefNum cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCRefNum);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPRefNumTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "pRefNum", xmlWriter);
                             

                                          if (localPRefNum==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("pRefNum cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPRefNum);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionStartTimeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionStartTime", xmlWriter);
                             

                                          if (localTransactionStartTime==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionStartTime cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionStartTime);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionCompletedTimeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionCompletedTime", xmlWriter);
                             

                                          if (localTransactionCompletedTime==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionCompletedTime cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionCompletedTime);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionProcessTimeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionProcessTime", xmlWriter);
                             

                                          if (localTransactionProcessTime==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionProcessTime cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionProcessTime);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localMaxTimeoutTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "maxTimeout", xmlWriter);
                             

                                          if (localMaxTimeout==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("maxTimeout cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMaxTimeout);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionErrorCodeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionErrorCode", xmlWriter);
                             

                                          if (localTransactionErrorCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionErrorCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionErrorCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionErrorMsgTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionErrorMsg", xmlWriter);
                             

                                          if (localTransactionErrorMsg==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionErrorMsg cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionErrorMsg);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionReturnTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionReturn", xmlWriter);
                             
                                               if (localTransactionReturn==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("transactionReturn cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTransactionReturn));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionReturnMsgTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionReturnMsg", xmlWriter);
                             

                                          if (localTransactionReturnMsg==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionReturnMsg cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionReturnMsg);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localClientCodeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "clientCode", xmlWriter);
                             

                                          if (localClientCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("clientCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localClientCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localIsInternalUseTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "isInternalUse", xmlWriter);
                             
                                               if (localIsInternalUse==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("isInternalUse cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsInternalUse));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localParentOperatorTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "parentOperator", xmlWriter);
                             

                                          if (localParentOperator==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("parentOperator cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localParentOperator);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionBatchIDTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionBatchID", xmlWriter);
                             

                                          if (localTransactionBatchID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionBatchID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionBatchID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBranchInfoTracker){
                                            if (localBranchInfo==null){
                                                 throw new org.apache.axis2.databinding.ADBException("branchInfo cannot be null!!");
                                            }
                                           localBranchInfo.serialize(new javax.xml.namespace.QName("","branchInfo"),
                                               xmlWriter);
                                        } if (localTransactionValDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionValDate", xmlWriter);
                             

                                          if (localTransactionValDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionValDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionValDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionCurrencyTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionCurrency", xmlWriter);
                             

                                          if (localTransactionCurrency==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionCurrency cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionCurrency);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionValueTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionValue", xmlWriter);
                             

                                          if (localTransactionValue==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionValue cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionValue);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionValDate_ToDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionValDate_ToDate", xmlWriter);
                             

                                          if (localTransactionValDate_ToDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionValDate_ToDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionValDate_ToDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionDate", xmlWriter);
                             

                                          if (localTransactionDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTransactionBackDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "transactionBackDate", xmlWriter);
                             

                                          if (localTransactionBackDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("transactionBackDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTransactionBackDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("bian.scb.com.vn")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeAttribute(writerPrefix, namespace,attName,attValue);
            } else {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
                xmlWriter.writeAttribute(prefix, namespace,attName,attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                xmlWriter.writeAttribute(registerPrefix(xmlWriter, namespace), namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }

    @Override
    public XMLStreamReader getPullParser(QName qname) throws XMLStreamException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{
        private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Factory.class);

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static TransactionInfoType parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            TransactionInfoType object =
                new TransactionInfoType();

            int event;
            javax.xml.namespace.QName currentQName = null;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                currentQName = reader.getName();
                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"TransactionInfoType".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (TransactionInfoType)vn.com.scb.bian.ws.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","coreRefNum").equals(reader.getName()) || new javax.xml.namespace.QName("","coreRefNum").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"coreRefNum" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCoreRefNum(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","cRefNum").equals(reader.getName()) || new javax.xml.namespace.QName("","cRefNum").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"cRefNum" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCRefNum(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","pRefNum").equals(reader.getName()) || new javax.xml.namespace.QName("","pRefNum").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"pRefNum" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPRefNum(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionStartTime").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionStartTime").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionStartTime" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionStartTime(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionCompletedTime").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionCompletedTime").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionCompletedTime" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionCompletedTime(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionProcessTime").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionProcessTime").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionProcessTime" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionProcessTime(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","maxTimeout").equals(reader.getName()) || new javax.xml.namespace.QName("","maxTimeout").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"maxTimeout" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMaxTimeout(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionErrorCode").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionErrorCode").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionErrorCode" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionErrorCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionErrorMsg").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionErrorMsg").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionErrorMsg" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionErrorMsg(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionReturn").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionReturn").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionReturn" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionReturn(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setTransactionReturn(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionReturnMsg").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionReturnMsg").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionReturnMsg" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionReturnMsg(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","clientCode").equals(reader.getName()) || new javax.xml.namespace.QName("","clientCode").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"clientCode" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setClientCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","isInternalUse").equals(reader.getName()) || new javax.xml.namespace.QName("","isInternalUse").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"isInternalUse" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setIsInternalUse(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setIsInternalUse(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","parentOperator").equals(reader.getName()) || new javax.xml.namespace.QName("","parentOperator").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"parentOperator" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setParentOperator(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionBatchID").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionBatchID").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionBatchID" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionBatchID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","branchInfo").equals(reader.getName()) || new javax.xml.namespace.QName("","branchInfo").equals(reader.getName()) ){
                                
                                                object.setBranchInfo(vn.com.scb.bian.ws.BranchInfoType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionValDate").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionValDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionValDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionValDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionCurrency").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionCurrency").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionCurrency" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionCurrency(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionValue").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionValue").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionValue" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionValue(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionValDate_ToDate").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionValDate_ToDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionValDate_ToDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionValDate_ToDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionDate").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionBackDate").equals(reader.getName()) || new javax.xml.namespace.QName("","transactionBackDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"transactionBackDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTransactionBackDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // 2 - A start element we are not expecting indicates a trailing invalid property
                                
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    