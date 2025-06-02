
/**
 * ERPInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.5  Built on : May 06, 2017 (03:45:50 BST)
 */

            
                package vn.com.scb.bian.ws;
            
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


            /**
            *  ERPInfoType bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ERPInfoType
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = ERPInfoType
                Namespace URI = bian.scb.com.vn
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for ErpType
                        */

                        
                                    protected java.lang.String localErpType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpTypeTracker = false ;

                           public boolean isErpTypeSpecified(){
                               return localErpTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpType(){
                               return localErpType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpType
                               */
                               public void setErpType(java.lang.String param){
                            localErpTypeTracker = param != null;
                                   
                                            this.localErpType=param;
                                       

                               }
                            

                        /**
                        * field for ErpCode
                        */

                        
                                    protected java.lang.String localErpCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpCodeTracker = false ;

                           public boolean isErpCodeSpecified(){
                               return localErpCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpCode(){
                               return localErpCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpCode
                               */
                               public void setErpCode(java.lang.String param){
                            localErpCodeTracker = param != null;
                                   
                                            this.localErpCode=param;
                                       

                               }
                            

                        /**
                        * field for ErpSerial
                        */

                        
                                    protected java.lang.String localErpSerial ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpSerialTracker = false ;

                           public boolean isErpSerialSpecified(){
                               return localErpSerialTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpSerial(){
                               return localErpSerial;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpSerial
                               */
                               public void setErpSerial(java.lang.String param){
                            localErpSerialTracker = param != null;
                                   
                                            this.localErpSerial=param;
                                       

                               }
                            

                        /**
                        * field for ErpBranchCode
                        */

                        
                                    protected vn.com.scb.bian.ws.BranchInfoType localErpBranchCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpBranchCodeTracker = false ;

                           public boolean isErpBranchCodeSpecified(){
                               return localErpBranchCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.BranchInfoType
                           */
                           public  vn.com.scb.bian.ws.BranchInfoType getErpBranchCode(){
                               return localErpBranchCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpBranchCode
                               */
                               public void setErpBranchCode(vn.com.scb.bian.ws.BranchInfoType param){
                            localErpBranchCodeTracker = param != null;
                                   
                                            this.localErpBranchCode=param;
                                       

                               }
                            

                        /**
                        * field for ErpStatus
                        */

                        
                                    protected java.lang.String localErpStatus ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpStatusTracker = false ;

                           public boolean isErpStatusSpecified(){
                               return localErpStatusTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpStatus(){
                               return localErpStatus;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpStatus
                               */
                               public void setErpStatus(java.lang.String param){
                            localErpStatusTracker = param != null;
                                   
                                            this.localErpStatus=param;
                                       

                               }
                            

                        /**
                        * field for ErpPrefix
                        */

                        
                                    protected java.lang.String localErpPrefix ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpPrefixTracker = false ;

                           public boolean isErpPrefixSpecified(){
                               return localErpPrefixTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpPrefix(){
                               return localErpPrefix;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpPrefix
                               */
                               public void setErpPrefix(java.lang.String param){
                            localErpPrefixTracker = param != null;
                                   
                                            this.localErpPrefix=param;
                                       

                               }
                            

                        /**
                        * field for ErpAccountingDate
                        */

                        
                                    protected java.lang.String localErpAccountingDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpAccountingDateTracker = false ;

                           public boolean isErpAccountingDateSpecified(){
                               return localErpAccountingDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpAccountingDate(){
                               return localErpAccountingDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpAccountingDate
                               */
                               public void setErpAccountingDate(java.lang.String param){
                            localErpAccountingDateTracker = param != null;
                                   
                                            this.localErpAccountingDate=param;
                                       

                               }
                            

                        /**
                        * field for ErpTransactionDate
                        */

                        
                                    protected java.lang.String localErpTransactionDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpTransactionDateTracker = false ;

                           public boolean isErpTransactionDateSpecified(){
                               return localErpTransactionDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpTransactionDate(){
                               return localErpTransactionDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpTransactionDate
                               */
                               public void setErpTransactionDate(java.lang.String param){
                            localErpTransactionDateTracker = param != null;
                                   
                                            this.localErpTransactionDate=param;
                                       

                               }
                            

                        /**
                        * field for ErpTransactionType
                        */

                        
                                    protected java.lang.String localErpTransactionType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpTransactionTypeTracker = false ;

                           public boolean isErpTransactionTypeSpecified(){
                               return localErpTransactionTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpTransactionType(){
                               return localErpTransactionType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpTransactionType
                               */
                               public void setErpTransactionType(java.lang.String param){
                            localErpTransactionTypeTracker = param != null;
                                   
                                            this.localErpTransactionType=param;
                                       

                               }
                            

                        /**
                        * field for ErpReturnItemID
                        */

                        
                                    protected java.lang.String localErpReturnItemID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpReturnItemIDTracker = false ;

                           public boolean isErpReturnItemIDSpecified(){
                               return localErpReturnItemIDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpReturnItemID(){
                               return localErpReturnItemID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpReturnItemID
                               */
                               public void setErpReturnItemID(java.lang.String param){
                            localErpReturnItemIDTracker = param != null;
                                   
                                            this.localErpReturnItemID=param;
                                       

                               }
                            

                        /**
                        * field for ErpReturnTranSerialID
                        */

                        
                                    protected java.lang.String localErpReturnTranSerialID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpReturnTranSerialIDTracker = false ;

                           public boolean isErpReturnTranSerialIDSpecified(){
                               return localErpReturnTranSerialIDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpReturnTranSerialID(){
                               return localErpReturnTranSerialID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpReturnTranSerialID
                               */
                               public void setErpReturnTranSerialID(java.lang.String param){
                            localErpReturnTranSerialIDTracker = param != null;
                                   
                                            this.localErpReturnTranSerialID=param;
                                       

                               }
                            

                        /**
                        * field for ErpInventoryID
                        */

                        
                                    protected java.lang.String localErpInventoryID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpInventoryIDTracker = false ;

                           public boolean isErpInventoryIDSpecified(){
                               return localErpInventoryIDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpInventoryID(){
                               return localErpInventoryID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpInventoryID
                               */
                               public void setErpInventoryID(java.lang.String param){
                            localErpInventoryIDTracker = param != null;
                                   
                                            this.localErpInventoryID=param;
                                       

                               }
                            

                        /**
                        * field for ErpItemID
                        */

                        
                                    protected java.lang.String localErpItemID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpItemIDTracker = false ;

                           public boolean isErpItemIDSpecified(){
                               return localErpItemIDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpItemID(){
                               return localErpItemID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpItemID
                               */
                               public void setErpItemID(java.lang.String param){
                            localErpItemIDTracker = param != null;
                                   
                                            this.localErpItemID=param;
                                       

                               }
                            

                        /**
                        * field for ErpOrgID
                        */

                        
                                    protected java.lang.String localErpOrgID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpOrgIDTracker = false ;

                           public boolean isErpOrgIDSpecified(){
                               return localErpOrgIDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpOrgID(){
                               return localErpOrgID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpOrgID
                               */
                               public void setErpOrgID(java.lang.String param){
                            localErpOrgIDTracker = param != null;
                                   
                                            this.localErpOrgID=param;
                                       

                               }
                            

                        /**
                        * field for ErpXmlData
                        */

                        
                                    protected java.lang.String localErpXmlData ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpXmlDataTracker = false ;

                           public boolean isErpXmlDataSpecified(){
                               return localErpXmlDataTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpXmlData(){
                               return localErpXmlData;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpXmlData
                               */
                               public void setErpXmlData(java.lang.String param){
                            localErpXmlDataTracker = param != null;
                                   
                                            this.localErpXmlData=param;
                                       

                               }
                            

                        /**
                        * field for ErpOrgFrom
                        */

                        
                                    protected java.lang.String localErpOrgFrom ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpOrgFromTracker = false ;

                           public boolean isErpOrgFromSpecified(){
                               return localErpOrgFromTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpOrgFrom(){
                               return localErpOrgFrom;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpOrgFrom
                               */
                               public void setErpOrgFrom(java.lang.String param){
                            localErpOrgFromTracker = param != null;
                                   
                                            this.localErpOrgFrom=param;
                                       

                               }
                            

                        /**
                        * field for ErpOrgTo
                        */

                        
                                    protected java.lang.String localErpOrgTo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpOrgToTracker = false ;

                           public boolean isErpOrgToSpecified(){
                               return localErpOrgToTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpOrgTo(){
                               return localErpOrgTo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpOrgTo
                               */
                               public void setErpOrgTo(java.lang.String param){
                            localErpOrgToTracker = param != null;
                                   
                                            this.localErpOrgTo=param;
                                       

                               }
                            

                        /**
                        * field for ErpInventoryFrom
                        */

                        
                                    protected java.lang.String localErpInventoryFrom ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpInventoryFromTracker = false ;

                           public boolean isErpInventoryFromSpecified(){
                               return localErpInventoryFromTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpInventoryFrom(){
                               return localErpInventoryFrom;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpInventoryFrom
                               */
                               public void setErpInventoryFrom(java.lang.String param){
                            localErpInventoryFromTracker = param != null;
                                   
                                            this.localErpInventoryFrom=param;
                                       

                               }
                            

                        /**
                        * field for ErpInventoryTo
                        */

                        
                                    protected java.lang.String localErpInventoryTo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpInventoryToTracker = false ;

                           public boolean isErpInventoryToSpecified(){
                               return localErpInventoryToTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpInventoryTo(){
                               return localErpInventoryTo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpInventoryTo
                               */
                               public void setErpInventoryTo(java.lang.String param){
                            localErpInventoryToTracker = param != null;
                                   
                                            this.localErpInventoryTo=param;
                                       

                               }
                            

                        /**
                        * field for ErpPartyFrom
                        */

                        
                                    protected java.lang.String localErpPartyFrom ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpPartyFromTracker = false ;

                           public boolean isErpPartyFromSpecified(){
                               return localErpPartyFromTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpPartyFrom(){
                               return localErpPartyFrom;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpPartyFrom
                               */
                               public void setErpPartyFrom(java.lang.String param){
                            localErpPartyFromTracker = param != null;
                                   
                                            this.localErpPartyFrom=param;
                                       

                               }
                            

                        /**
                        * field for ErpPartyFType
                        */

                        
                                    protected java.lang.String localErpPartyFType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpPartyFTypeTracker = false ;

                           public boolean isErpPartyFTypeSpecified(){
                               return localErpPartyFTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpPartyFType(){
                               return localErpPartyFType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpPartyFType
                               */
                               public void setErpPartyFType(java.lang.String param){
                            localErpPartyFTypeTracker = param != null;
                                   
                                            this.localErpPartyFType=param;
                                       

                               }
                            

                        /**
                        * field for ErpPartyTo
                        */

                        
                                    protected java.lang.String localErpPartyTo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpPartyToTracker = false ;

                           public boolean isErpPartyToSpecified(){
                               return localErpPartyToTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpPartyTo(){
                               return localErpPartyTo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpPartyTo
                               */
                               public void setErpPartyTo(java.lang.String param){
                            localErpPartyToTracker = param != null;
                                   
                                            this.localErpPartyTo=param;
                                       

                               }
                            

                        /**
                        * field for ErpPartyTType
                        */

                        
                                    protected java.lang.String localErpPartyTType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpPartyTTypeTracker = false ;

                           public boolean isErpPartyTTypeSpecified(){
                               return localErpPartyTTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpPartyTType(){
                               return localErpPartyTType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpPartyTType
                               */
                               public void setErpPartyTType(java.lang.String param){
                            localErpPartyTTypeTracker = param != null;
                                   
                                            this.localErpPartyTType=param;
                                       

                               }
                            

                        /**
                        * field for ErpDescription
                        */

                        
                                    protected java.lang.String localErpDescription ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpDescriptionTracker = false ;

                           public boolean isErpDescriptionSpecified(){
                               return localErpDescriptionTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpDescription(){
                               return localErpDescription;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpDescription
                               */
                               public void setErpDescription(java.lang.String param){
                            localErpDescriptionTracker = param != null;
                                   
                                            this.localErpDescription=param;
                                       

                               }
                            

                        /**
                        * field for ErpAccount
                        */

                        
                                    protected java.lang.String localErpAccount ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpAccountTracker = false ;

                           public boolean isErpAccountSpecified(){
                               return localErpAccountTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpAccount(){
                               return localErpAccount;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpAccount
                               */
                               public void setErpAccount(java.lang.String param){
                            localErpAccountTracker = param != null;
                                   
                                            this.localErpAccount=param;
                                       

                               }
                            

                        /**
                        * field for ErpPostGL
                        */

                        
                                    protected java.lang.String localErpPostGL ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpPostGLTracker = false ;

                           public boolean isErpPostGLSpecified(){
                               return localErpPostGLTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpPostGL(){
                               return localErpPostGL;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpPostGL
                               */
                               public void setErpPostGL(java.lang.String param){
                            localErpPostGLTracker = param != null;
                                   
                                            this.localErpPostGL=param;
                                       

                               }
                            

                        /**
                        * field for ErpUser
                        */

                        
                                    protected java.lang.String localErpUser ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localErpUserTracker = false ;

                           public boolean isErpUserSpecified(){
                               return localErpUserTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getErpUser(){
                               return localErpUser;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ErpUser
                               */
                               public void setErpUser(java.lang.String param){
                            localErpUserTracker = param != null;
                                   
                                            this.localErpUser=param;
                                       

                               }
                            

                        /**
                        * field for BarCode
                        */

                        
                                    protected vn.com.scb.bian.ws.ERPBarCodeType localBarCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBarCodeTracker = false ;

                           public boolean isBarCodeSpecified(){
                               return localBarCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.ERPBarCodeType
                           */
                           public  vn.com.scb.bian.ws.ERPBarCodeType getBarCode(){
                               return localBarCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BarCode
                               */
                               public void setBarCode(vn.com.scb.bian.ws.ERPBarCodeType param){
                            localBarCodeTracker = param != null;
                                   
                                            this.localBarCode=param;
                                       

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
                           namespacePrefix+":ERPInfoType",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "ERPInfoType",
                           xmlWriter);
                   }

               
                   }
                if (localErpTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpType", xmlWriter);
                             

                                          if (localErpType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpCodeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpCode", xmlWriter);
                             

                                          if (localErpCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpSerialTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpSerial", xmlWriter);
                             

                                          if (localErpSerial==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpSerial cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpSerial);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpBranchCodeTracker){
                                            if (localErpBranchCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("erpBranchCode cannot be null!!");
                                            }
                                           localErpBranchCode.serialize(new javax.xml.namespace.QName("","erpBranchCode"),
                                               xmlWriter);
                                        } if (localErpStatusTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpStatus", xmlWriter);
                             

                                          if (localErpStatus==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpStatus cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpStatus);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpPrefixTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpPrefix", xmlWriter);
                             

                                          if (localErpPrefix==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpPrefix cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpPrefix);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpAccountingDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpAccountingDate", xmlWriter);
                             

                                          if (localErpAccountingDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpAccountingDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpAccountingDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpTransactionDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpTransactionDate", xmlWriter);
                             

                                          if (localErpTransactionDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpTransactionDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpTransactionDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpTransactionTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpTransactionType", xmlWriter);
                             

                                          if (localErpTransactionType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpTransactionType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpTransactionType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpReturnItemIDTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpReturnItemID", xmlWriter);
                             

                                          if (localErpReturnItemID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpReturnItemID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpReturnItemID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpReturnTranSerialIDTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpReturnTranSerialID", xmlWriter);
                             

                                          if (localErpReturnTranSerialID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpReturnTranSerialID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpReturnTranSerialID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpInventoryIDTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpInventoryID", xmlWriter);
                             

                                          if (localErpInventoryID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpInventoryID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpInventoryID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpItemIDTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpItemID", xmlWriter);
                             

                                          if (localErpItemID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpItemID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpItemID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpOrgIDTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpOrgID", xmlWriter);
                             

                                          if (localErpOrgID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpOrgID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpOrgID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpXmlDataTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpXmlData", xmlWriter);
                             

                                          if (localErpXmlData==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpXmlData cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpXmlData);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpOrgFromTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpOrgFrom", xmlWriter);
                             

                                          if (localErpOrgFrom==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpOrgFrom cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpOrgFrom);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpOrgToTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpOrgTo", xmlWriter);
                             

                                          if (localErpOrgTo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpOrgTo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpOrgTo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpInventoryFromTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpInventoryFrom", xmlWriter);
                             

                                          if (localErpInventoryFrom==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpInventoryFrom cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpInventoryFrom);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpInventoryToTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpInventoryTo", xmlWriter);
                             

                                          if (localErpInventoryTo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpInventoryTo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpInventoryTo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpPartyFromTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpPartyFrom", xmlWriter);
                             

                                          if (localErpPartyFrom==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpPartyFrom cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpPartyFrom);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpPartyFTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpPartyFType", xmlWriter);
                             

                                          if (localErpPartyFType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpPartyFType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpPartyFType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpPartyToTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpPartyTo", xmlWriter);
                             

                                          if (localErpPartyTo==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpPartyTo cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpPartyTo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpPartyTTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpPartyTType", xmlWriter);
                             

                                          if (localErpPartyTType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpPartyTType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpPartyTType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpDescriptionTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpDescription", xmlWriter);
                             

                                          if (localErpDescription==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpDescription cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpDescription);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpAccountTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpAccount", xmlWriter);
                             

                                          if (localErpAccount==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpAccount cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpAccount);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpPostGLTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpPostGL", xmlWriter);
                             

                                          if (localErpPostGL==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpPostGL cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpPostGL);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localErpUserTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "erpUser", xmlWriter);
                             

                                          if (localErpUser==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("erpUser cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localErpUser);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBarCodeTracker){
                                            if (localBarCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("barCode cannot be null!!");
                                            }
                                           localBarCode.serialize(new javax.xml.namespace.QName("","barCode"),
                                               xmlWriter);
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
        public static ERPInfoType parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ERPInfoType object =
                new ERPInfoType();

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
                    
                            if (!"ERPInfoType".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ERPInfoType)vn.com.scb.bian.ws.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpType").equals(reader.getName()) || new javax.xml.namespace.QName("","erpType").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpType" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpCode").equals(reader.getName()) || new javax.xml.namespace.QName("","erpCode").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpCode" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpSerial").equals(reader.getName()) || new javax.xml.namespace.QName("","erpSerial").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpSerial" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpSerial(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpBranchCode").equals(reader.getName()) || new javax.xml.namespace.QName("","erpBranchCode").equals(reader.getName()) ){
                                
                                                object.setErpBranchCode(vn.com.scb.bian.ws.BranchInfoType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpStatus").equals(reader.getName()) || new javax.xml.namespace.QName("","erpStatus").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpStatus" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpStatus(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpPrefix").equals(reader.getName()) || new javax.xml.namespace.QName("","erpPrefix").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpPrefix" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpPrefix(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpAccountingDate").equals(reader.getName()) || new javax.xml.namespace.QName("","erpAccountingDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpAccountingDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpAccountingDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpTransactionDate").equals(reader.getName()) || new javax.xml.namespace.QName("","erpTransactionDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpTransactionDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpTransactionDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpTransactionType").equals(reader.getName()) || new javax.xml.namespace.QName("","erpTransactionType").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpTransactionType" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpTransactionType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpReturnItemID").equals(reader.getName()) || new javax.xml.namespace.QName("","erpReturnItemID").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpReturnItemID" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpReturnItemID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpReturnTranSerialID").equals(reader.getName()) || new javax.xml.namespace.QName("","erpReturnTranSerialID").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpReturnTranSerialID" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpReturnTranSerialID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpInventoryID").equals(reader.getName()) || new javax.xml.namespace.QName("","erpInventoryID").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpInventoryID" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpInventoryID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpItemID").equals(reader.getName()) || new javax.xml.namespace.QName("","erpItemID").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpItemID" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpItemID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpOrgID").equals(reader.getName()) || new javax.xml.namespace.QName("","erpOrgID").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpOrgID" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpOrgID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpXmlData").equals(reader.getName()) || new javax.xml.namespace.QName("","erpXmlData").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpXmlData" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpXmlData(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpOrgFrom").equals(reader.getName()) || new javax.xml.namespace.QName("","erpOrgFrom").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpOrgFrom" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpOrgFrom(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpOrgTo").equals(reader.getName()) || new javax.xml.namespace.QName("","erpOrgTo").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpOrgTo" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpOrgTo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpInventoryFrom").equals(reader.getName()) || new javax.xml.namespace.QName("","erpInventoryFrom").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpInventoryFrom" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpInventoryFrom(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpInventoryTo").equals(reader.getName()) || new javax.xml.namespace.QName("","erpInventoryTo").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpInventoryTo" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpInventoryTo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpPartyFrom").equals(reader.getName()) || new javax.xml.namespace.QName("","erpPartyFrom").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpPartyFrom" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpPartyFrom(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpPartyFType").equals(reader.getName()) || new javax.xml.namespace.QName("","erpPartyFType").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpPartyFType" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpPartyFType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpPartyTo").equals(reader.getName()) || new javax.xml.namespace.QName("","erpPartyTo").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpPartyTo" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpPartyTo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpPartyTType").equals(reader.getName()) || new javax.xml.namespace.QName("","erpPartyTType").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpPartyTType" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpPartyTType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpDescription").equals(reader.getName()) || new javax.xml.namespace.QName("","erpDescription").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpDescription" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpDescription(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpAccount").equals(reader.getName()) || new javax.xml.namespace.QName("","erpAccount").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpAccount" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpAccount(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpPostGL").equals(reader.getName()) || new javax.xml.namespace.QName("","erpPostGL").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpPostGL" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpPostGL(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","erpUser").equals(reader.getName()) || new javax.xml.namespace.QName("","erpUser").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"erpUser" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setErpUser(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","barCode").equals(reader.getName()) || new javax.xml.namespace.QName("","barCode").equals(reader.getName()) ){
                                
                                                object.setBarCode(vn.com.scb.bian.ws.ERPBarCodeType.Factory.parse(reader));
                                              
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
           
    