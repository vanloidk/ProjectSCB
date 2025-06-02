
/**
 * CustomerInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.5  Built on : May 06, 2017 (03:45:50 BST)
 */

            
                package vn.com.scb.bian.ws;
            
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


            /**
            *  CustomerInfoType bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class CustomerInfoType
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = CustomerInfoType
                Namespace URI = bian.scb.com.vn
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for Fullname
                        */

                        
                                    protected java.lang.String localFullname ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFullnameTracker = false ;

                           public boolean isFullnameSpecified(){
                               return localFullnameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFullname(){
                               return localFullname;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Fullname
                               */
                               public void setFullname(java.lang.String param){
                            localFullnameTracker = param != null;
                                   
                                            this.localFullname=param;
                                       

                               }
                            

                        /**
                        * field for Fullname_vn
                        */

                        
                                    protected java.lang.String localFullname_vn ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFullname_vnTracker = false ;

                           public boolean isFullname_vnSpecified(){
                               return localFullname_vnTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFullname_vn(){
                               return localFullname_vn;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Fullname_vn
                               */
                               public void setFullname_vn(java.lang.String param){
                            localFullname_vnTracker = param != null;
                                   
                                            this.localFullname_vn=param;
                                       

                               }
                            

                        /**
                        * field for BirthDay
                        */

                        
                                    protected java.lang.String localBirthDay ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBirthDayTracker = false ;

                           public boolean isBirthDaySpecified(){
                               return localBirthDayTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBirthDay(){
                               return localBirthDay;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BirthDay
                               */
                               public void setBirthDay(java.lang.String param){
                            localBirthDayTracker = param != null;
                                   
                                            this.localBirthDay=param;
                                       

                               }
                            

                        /**
                        * field for Gender
                        */

                        
                                    protected java.lang.String localGender ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localGenderTracker = false ;

                           public boolean isGenderSpecified(){
                               return localGenderTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getGender(){
                               return localGender;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Gender
                               */
                               public void setGender(java.lang.String param){
                            localGenderTracker = param != null;
                                   
                                            this.localGender=param;
                                       

                               }
                            

                        /**
                        * field for CustomerVIPType
                        */

                        
                                    protected java.lang.String localCustomerVIPType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCustomerVIPTypeTracker = false ;

                           public boolean isCustomerVIPTypeSpecified(){
                               return localCustomerVIPTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCustomerVIPType(){
                               return localCustomerVIPType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CustomerVIPType
                               */
                               public void setCustomerVIPType(java.lang.String param){
                            localCustomerVIPTypeTracker = param != null;
                                   
                                            this.localCustomerVIPType=param;
                                       

                               }
                            

                        /**
                        * field for ManageStaffID
                        */

                        
                                    protected java.lang.String localManageStaffID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localManageStaffIDTracker = false ;

                           public boolean isManageStaffIDSpecified(){
                               return localManageStaffIDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getManageStaffID(){
                               return localManageStaffID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ManageStaffID
                               */
                               public void setManageStaffID(java.lang.String param){
                            localManageStaffIDTracker = param != null;
                                   
                                            this.localManageStaffID=param;
                                       

                               }
                            

                        /**
                        * field for RowOrder
                        */

                        
                                    protected java.lang.String localRowOrder ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRowOrderTracker = false ;

                           public boolean isRowOrderSpecified(){
                               return localRowOrderTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getRowOrder(){
                               return localRowOrder;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RowOrder
                               */
                               public void setRowOrder(java.lang.String param){
                            localRowOrderTracker = param != null;
                                   
                                            this.localRowOrder=param;
                                       

                               }
                            

                        /**
                        * field for JobInfo
                        */

                        
                                    protected vn.com.scb.bian.ws.JobInfoType localJobInfo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localJobInfoTracker = false ;

                           public boolean isJobInfoSpecified(){
                               return localJobInfoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.JobInfoType
                           */
                           public  vn.com.scb.bian.ws.JobInfoType getJobInfo(){
                               return localJobInfo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param JobInfo
                               */
                               public void setJobInfo(vn.com.scb.bian.ws.JobInfoType param){
                            localJobInfoTracker = param != null;
                                   
                                            this.localJobInfo=param;
                                       

                               }
                            

                        /**
                        * field for IDInfo
                        */

                        
                                    protected vn.com.scb.bian.ws.IDInfoType localIDInfo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIDInfoTracker = false ;

                           public boolean isIDInfoSpecified(){
                               return localIDInfoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.IDInfoType
                           */
                           public  vn.com.scb.bian.ws.IDInfoType getIDInfo(){
                               return localIDInfo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IDInfo
                               */
                               public void setIDInfo(vn.com.scb.bian.ws.IDInfoType param){
                            localIDInfoTracker = param != null;
                                   
                                            this.localIDInfo=param;
                                       

                               }
                            

                        /**
                        * field for Address
                        */

                        
                                    protected vn.com.scb.bian.ws.AddressType localAddress ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAddressTracker = false ;

                           public boolean isAddressSpecified(){
                               return localAddressTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.AddressType
                           */
                           public  vn.com.scb.bian.ws.AddressType getAddress(){
                               return localAddress;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Address
                               */
                               public void setAddress(vn.com.scb.bian.ws.AddressType param){
                            localAddressTracker = param != null;
                                   
                                            this.localAddress=param;
                                       

                               }
                            

                        /**
                        * field for FullNameNoneAccented
                        */

                        
                                    protected java.lang.String localFullNameNoneAccented ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFullNameNoneAccentedTracker = false ;

                           public boolean isFullNameNoneAccentedSpecified(){
                               return localFullNameNoneAccentedTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFullNameNoneAccented(){
                               return localFullNameNoneAccented;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FullNameNoneAccented
                               */
                               public void setFullNameNoneAccented(java.lang.String param){
                            localFullNameNoneAccentedTracker = param != null;
                                   
                                            this.localFullNameNoneAccented=param;
                                       

                               }
                            

                        /**
                        * field for CustomerType
                        */

                        
                                    protected vn.com.scb.bian.ws.CustomerTypeInfoType localCustomerType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCustomerTypeTracker = false ;

                           public boolean isCustomerTypeSpecified(){
                               return localCustomerTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.CustomerTypeInfoType
                           */
                           public  vn.com.scb.bian.ws.CustomerTypeInfoType getCustomerType(){
                               return localCustomerType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CustomerType
                               */
                               public void setCustomerType(vn.com.scb.bian.ws.CustomerTypeInfoType param){
                            localCustomerTypeTracker = param != null;
                                   
                                            this.localCustomerType=param;
                                       

                               }
                            

                        /**
                        * field for ShortName
                        */

                        
                                    protected java.lang.String localShortName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localShortNameTracker = false ;

                           public boolean isShortNameSpecified(){
                               return localShortNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getShortName(){
                               return localShortName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ShortName
                               */
                               public void setShortName(java.lang.String param){
                            localShortNameTracker = param != null;
                                   
                                            this.localShortName=param;
                                       

                               }
                            

                        /**
                        * field for ResidentStatus
                        */

                        
                                    protected java.lang.String localResidentStatus ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localResidentStatusTracker = false ;

                           public boolean isResidentStatusSpecified(){
                               return localResidentStatusTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getResidentStatus(){
                               return localResidentStatus;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ResidentStatus
                               */
                               public void setResidentStatus(java.lang.String param){
                            localResidentStatusTracker = param != null;
                                   
                                            this.localResidentStatus=param;
                                       

                               }
                            

                        /**
                        * field for LegalGuardian
                        */

                        
                                    protected java.lang.String localLegalGuardian ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLegalGuardianTracker = false ;

                           public boolean isLegalGuardianSpecified(){
                               return localLegalGuardianTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLegalGuardian(){
                               return localLegalGuardian;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param LegalGuardian
                               */
                               public void setLegalGuardian(java.lang.String param){
                            localLegalGuardianTracker = param != null;
                                   
                                            this.localLegalGuardian=param;
                                       

                               }
                            

                        /**
                        * field for NationlityCode
                        */

                        
                                    protected java.lang.String localNationlityCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNationlityCodeTracker = false ;

                           public boolean isNationlityCodeSpecified(){
                               return localNationlityCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNationlityCode(){
                               return localNationlityCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param NationlityCode
                               */
                               public void setNationlityCode(java.lang.String param){
                            localNationlityCodeTracker = param != null;
                                   
                                            this.localNationlityCode=param;
                                       

                               }
                            

                        /**
                        * field for Nationlity
                        */

                        
                                    protected java.lang.String localNationlity ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNationlityTracker = false ;

                           public boolean isNationlitySpecified(){
                               return localNationlityTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNationlity(){
                               return localNationlity;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Nationlity
                               */
                               public void setNationlity(java.lang.String param){
                            localNationlityTracker = param != null;
                                   
                                            this.localNationlity=param;
                                       

                               }
                            

                        /**
                        * field for Language
                        */

                        
                                    protected java.lang.String localLanguage ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLanguageTracker = false ;

                           public boolean isLanguageSpecified(){
                               return localLanguageTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLanguage(){
                               return localLanguage;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Language
                               */
                               public void setLanguage(java.lang.String param){
                            localLanguageTracker = param != null;
                                   
                                            this.localLanguage=param;
                                       

                               }
                            

                        /**
                        * field for LocalCode
                        */

                        
                                    protected java.lang.String localLocalCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLocalCodeTracker = false ;

                           public boolean isLocalCodeSpecified(){
                               return localLocalCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLocalCode(){
                               return localLocalCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param LocalCode
                               */
                               public void setLocalCode(java.lang.String param){
                            localLocalCodeTracker = param != null;
                                   
                                            this.localLocalCode=param;
                                       

                               }
                            

                        /**
                        * field for CustomerCatory
                        */

                        
                                    protected vn.com.scb.bian.ws.CustomerCatoryInfoType localCustomerCatory ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCustomerCatoryTracker = false ;

                           public boolean isCustomerCatorySpecified(){
                               return localCustomerCatoryTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.CustomerCatoryInfoType
                           */
                           public  vn.com.scb.bian.ws.CustomerCatoryInfoType getCustomerCatory(){
                               return localCustomerCatory;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CustomerCatory
                               */
                               public void setCustomerCatory(vn.com.scb.bian.ws.CustomerCatoryInfoType param){
                            localCustomerCatoryTracker = param != null;
                                   
                                            this.localCustomerCatory=param;
                                       

                               }
                            

                        /**
                        * field for RecordStatus
                        */

                        
                                    protected java.lang.String localRecordStatus ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRecordStatusTracker = false ;

                           public boolean isRecordStatusSpecified(){
                               return localRecordStatusTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getRecordStatus(){
                               return localRecordStatus;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RecordStatus
                               */
                               public void setRecordStatus(java.lang.String param){
                            localRecordStatusTracker = param != null;
                                   
                                            this.localRecordStatus=param;
                                       

                               }
                            

                        /**
                        * field for AuthStatus
                        */

                        
                                    protected java.lang.String localAuthStatus ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAuthStatusTracker = false ;

                           public boolean isAuthStatusSpecified(){
                               return localAuthStatusTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAuthStatus(){
                               return localAuthStatus;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AuthStatus
                               */
                               public void setAuthStatus(java.lang.String param){
                            localAuthStatusTracker = param != null;
                                   
                                            this.localAuthStatus=param;
                                       

                               }
                            

                        /**
                        * field for UsIndicia
                        */

                        
                                    protected java.lang.String localUsIndicia ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localUsIndiciaTracker = false ;

                           public boolean isUsIndiciaSpecified(){
                               return localUsIndiciaTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getUsIndicia(){
                               return localUsIndicia;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param UsIndicia
                               */
                               public void setUsIndicia(java.lang.String param){
                            localUsIndiciaTracker = param != null;
                                   
                                            this.localUsIndicia=param;
                                       

                               }
                            

                        /**
                        * field for DirectorName
                        */

                        
                                    protected java.lang.String localDirectorName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDirectorNameTracker = false ;

                           public boolean isDirectorNameSpecified(){
                               return localDirectorNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDirectorName(){
                               return localDirectorName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DirectorName
                               */
                               public void setDirectorName(java.lang.String param){
                            localDirectorNameTracker = param != null;
                                   
                                            this.localDirectorName=param;
                                       

                               }
                            

                        /**
                        * field for LegalRepresentativePrsnName
                        */

                        
                                    protected java.lang.String localLegalRepresentativePrsnName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLegalRepresentativePrsnNameTracker = false ;

                           public boolean isLegalRepresentativePrsnNameSpecified(){
                               return localLegalRepresentativePrsnNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLegalRepresentativePrsnName(){
                               return localLegalRepresentativePrsnName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param LegalRepresentativePrsnName
                               */
                               public void setLegalRepresentativePrsnName(java.lang.String param){
                            localLegalRepresentativePrsnNameTracker = param != null;
                                   
                                            this.localLegalRepresentativePrsnName=param;
                                       

                               }
                            

                        /**
                        * field for LegalRepresentativePrsnID
                        */

                        
                                    protected java.lang.String localLegalRepresentativePrsnID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLegalRepresentativePrsnIDTracker = false ;

                           public boolean isLegalRepresentativePrsnIDSpecified(){
                               return localLegalRepresentativePrsnIDTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLegalRepresentativePrsnID(){
                               return localLegalRepresentativePrsnID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param LegalRepresentativePrsnID
                               */
                               public void setLegalRepresentativePrsnID(java.lang.String param){
                            localLegalRepresentativePrsnIDTracker = param != null;
                                   
                                            this.localLegalRepresentativePrsnID=param;
                                       

                               }
                            

                        /**
                        * field for BizContactPerson
                        */

                        
                                    protected java.lang.String localBizContactPerson ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBizContactPersonTracker = false ;

                           public boolean isBizContactPersonSpecified(){
                               return localBizContactPersonTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBizContactPerson(){
                               return localBizContactPerson;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BizContactPerson
                               */
                               public void setBizContactPerson(java.lang.String param){
                            localBizContactPersonTracker = param != null;
                                   
                                            this.localBizContactPerson=param;
                                       

                               }
                            

                        /**
                        * field for BizContactPersonPhoneNum
                        */

                        
                                    protected java.lang.String localBizContactPersonPhoneNum ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBizContactPersonPhoneNumTracker = false ;

                           public boolean isBizContactPersonPhoneNumSpecified(){
                               return localBizContactPersonPhoneNumTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBizContactPersonPhoneNum(){
                               return localBizContactPersonPhoneNum;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BizContactPersonPhoneNum
                               */
                               public void setBizContactPersonPhoneNum(java.lang.String param){
                            localBizContactPersonPhoneNumTracker = param != null;
                                   
                                            this.localBizContactPersonPhoneNum=param;
                                       

                               }
                            

                        /**
                        * field for BizLine
                        */

                        
                                    protected java.lang.String localBizLine ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBizLineTracker = false ;

                           public boolean isBizLineSpecified(){
                               return localBizLineTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBizLine(){
                               return localBizLine;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BizLine
                               */
                               public void setBizLine(java.lang.String param){
                            localBizLineTracker = param != null;
                                   
                                            this.localBizLine=param;
                                       

                               }
                            

                        /**
                        * field for BizLicenseNum
                        */

                        
                                    protected java.lang.String localBizLicenseNum ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBizLicenseNumTracker = false ;

                           public boolean isBizLicenseNumSpecified(){
                               return localBizLicenseNumTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBizLicenseNum(){
                               return localBizLicenseNum;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BizLicenseNum
                               */
                               public void setBizLicenseNum(java.lang.String param){
                            localBizLicenseNumTracker = param != null;
                                   
                                            this.localBizLicenseNum=param;
                                       

                               }
                            

                        /**
                        * field for BizLicenseIssueDate
                        */

                        
                                    protected java.lang.String localBizLicenseIssueDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBizLicenseIssueDateTracker = false ;

                           public boolean isBizLicenseIssueDateSpecified(){
                               return localBizLicenseIssueDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBizLicenseIssueDate(){
                               return localBizLicenseIssueDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BizLicenseIssueDate
                               */
                               public void setBizLicenseIssueDate(java.lang.String param){
                            localBizLicenseIssueDateTracker = param != null;
                                   
                                            this.localBizLicenseIssueDate=param;
                                       

                               }
                            

                        /**
                        * field for EmailList
                        */

                        
                                    protected java.lang.String localEmailList ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEmailListTracker = false ;

                           public boolean isEmailListSpecified(){
                               return localEmailListTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEmailList(){
                               return localEmailList;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EmailList
                               */
                               public void setEmailList(java.lang.String param){
                            localEmailListTracker = param != null;
                                   
                                            this.localEmailList=param;
                                       

                               }
                            

                        /**
                        * field for FeeDebt
                        */

                        
                                    protected java.lang.String localFeeDebt ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFeeDebtTracker = false ;

                           public boolean isFeeDebtSpecified(){
                               return localFeeDebtTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getFeeDebt(){
                               return localFeeDebt;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param FeeDebt
                               */
                               public void setFeeDebt(java.lang.String param){
                            localFeeDebtTracker = param != null;
                                   
                                            this.localFeeDebt=param;
                                       

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
                           namespacePrefix+":CustomerInfoType",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "CustomerInfoType",
                           xmlWriter);
                   }

               
                   }
                if (localFullnameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "fullname", xmlWriter);
                             

                                          if (localFullname==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("fullname cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFullname);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFullname_vnTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "fullname_vn", xmlWriter);
                             

                                          if (localFullname_vn==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("fullname_vn cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFullname_vn);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBirthDayTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "birthDay", xmlWriter);
                             

                                          if (localBirthDay==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("birthDay cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBirthDay);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localGenderTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "gender", xmlWriter);
                             

                                          if (localGender==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("gender cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localGender);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCustomerVIPTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "customerVIPType", xmlWriter);
                             

                                          if (localCustomerVIPType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("customerVIPType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCustomerVIPType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localManageStaffIDTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "manageStaffID", xmlWriter);
                             

                                          if (localManageStaffID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("manageStaffID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localManageStaffID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localRowOrderTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "rowOrder", xmlWriter);
                             

                                          if (localRowOrder==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("rowOrder cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localRowOrder);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localJobInfoTracker){
                                            if (localJobInfo==null){
                                                 throw new org.apache.axis2.databinding.ADBException("jobInfo cannot be null!!");
                                            }
                                           localJobInfo.serialize(new javax.xml.namespace.QName("","jobInfo"),
                                               xmlWriter);
                                        } if (localIDInfoTracker){
                                            if (localIDInfo==null){
                                                 throw new org.apache.axis2.databinding.ADBException("IDInfo cannot be null!!");
                                            }
                                           localIDInfo.serialize(new javax.xml.namespace.QName("","IDInfo"),
                                               xmlWriter);
                                        } if (localAddressTracker){
                                            if (localAddress==null){
                                                 throw new org.apache.axis2.databinding.ADBException("address cannot be null!!");
                                            }
                                           localAddress.serialize(new javax.xml.namespace.QName("","address"),
                                               xmlWriter);
                                        } if (localFullNameNoneAccentedTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "fullNameNoneAccented", xmlWriter);
                             

                                          if (localFullNameNoneAccented==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("fullNameNoneAccented cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFullNameNoneAccented);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCustomerTypeTracker){
                                            if (localCustomerType==null){
                                                 throw new org.apache.axis2.databinding.ADBException("customerType cannot be null!!");
                                            }
                                           localCustomerType.serialize(new javax.xml.namespace.QName("","customerType"),
                                               xmlWriter);
                                        } if (localShortNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "shortName", xmlWriter);
                             

                                          if (localShortName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("shortName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localShortName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localResidentStatusTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "residentStatus", xmlWriter);
                             

                                          if (localResidentStatus==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("residentStatus cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localResidentStatus);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLegalGuardianTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "legalGuardian", xmlWriter);
                             

                                          if (localLegalGuardian==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("legalGuardian cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLegalGuardian);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNationlityCodeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "nationlityCode", xmlWriter);
                             

                                          if (localNationlityCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("nationlityCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNationlityCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNationlityTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "nationlity", xmlWriter);
                             

                                          if (localNationlity==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("nationlity cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNationlity);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLanguageTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "language", xmlWriter);
                             

                                          if (localLanguage==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("language cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLanguage);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLocalCodeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "localCode", xmlWriter);
                             

                                          if (localLocalCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("localCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLocalCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCustomerCatoryTracker){
                                            if (localCustomerCatory==null){
                                                 throw new org.apache.axis2.databinding.ADBException("customerCatory cannot be null!!");
                                            }
                                           localCustomerCatory.serialize(new javax.xml.namespace.QName("","customerCatory"),
                                               xmlWriter);
                                        } if (localRecordStatusTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "recordStatus", xmlWriter);
                             

                                          if (localRecordStatus==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("recordStatus cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localRecordStatus);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAuthStatusTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "authStatus", xmlWriter);
                             

                                          if (localAuthStatus==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("authStatus cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAuthStatus);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localUsIndiciaTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "usIndicia", xmlWriter);
                             

                                          if (localUsIndicia==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("usIndicia cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localUsIndicia);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDirectorNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "directorName", xmlWriter);
                             

                                          if (localDirectorName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("directorName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDirectorName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLegalRepresentativePrsnNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "legalRepresentativePrsnName", xmlWriter);
                             

                                          if (localLegalRepresentativePrsnName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("legalRepresentativePrsnName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLegalRepresentativePrsnName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLegalRepresentativePrsnIDTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "legalRepresentativePrsnID", xmlWriter);
                             

                                          if (localLegalRepresentativePrsnID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("legalRepresentativePrsnID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLegalRepresentativePrsnID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBizContactPersonTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "bizContactPerson", xmlWriter);
                             

                                          if (localBizContactPerson==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("bizContactPerson cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBizContactPerson);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBizContactPersonPhoneNumTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "bizContactPersonPhoneNum", xmlWriter);
                             

                                          if (localBizContactPersonPhoneNum==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("bizContactPersonPhoneNum cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBizContactPersonPhoneNum);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBizLineTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "bizLine", xmlWriter);
                             

                                          if (localBizLine==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("bizLine cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBizLine);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBizLicenseNumTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "bizLicenseNum", xmlWriter);
                             

                                          if (localBizLicenseNum==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("bizLicenseNum cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBizLicenseNum);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBizLicenseIssueDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "bizLicenseIssueDate", xmlWriter);
                             

                                          if (localBizLicenseIssueDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("bizLicenseIssueDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBizLicenseIssueDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localEmailListTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "emailList", xmlWriter);
                             

                                          if (localEmailList==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("emailList cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEmailList);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localFeeDebtTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "feeDebt", xmlWriter);
                             

                                          if (localFeeDebt==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("feeDebt cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localFeeDebt);
                                            
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
        public static CustomerInfoType parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            CustomerInfoType object =
                new CustomerInfoType();

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
                    
                            if (!"CustomerInfoType".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (CustomerInfoType)vn.com.scb.bian.ws.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","fullname").equals(reader.getName()) || new javax.xml.namespace.QName("","fullname").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"fullname" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFullname(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","fullname_vn").equals(reader.getName()) || new javax.xml.namespace.QName("","fullname_vn").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"fullname_vn" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFullname_vn(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","birthDay").equals(reader.getName()) || new javax.xml.namespace.QName("","birthDay").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"birthDay" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBirthDay(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","gender").equals(reader.getName()) || new javax.xml.namespace.QName("","gender").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"gender" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setGender(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","customerVIPType").equals(reader.getName()) || new javax.xml.namespace.QName("","customerVIPType").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"customerVIPType" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCustomerVIPType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","manageStaffID").equals(reader.getName()) || new javax.xml.namespace.QName("","manageStaffID").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"manageStaffID" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setManageStaffID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","rowOrder").equals(reader.getName()) || new javax.xml.namespace.QName("","rowOrder").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"rowOrder" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setRowOrder(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","jobInfo").equals(reader.getName()) || new javax.xml.namespace.QName("","jobInfo").equals(reader.getName()) ){
                                
                                                object.setJobInfo(vn.com.scb.bian.ws.JobInfoType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","IDInfo").equals(reader.getName()) || new javax.xml.namespace.QName("","IDInfo").equals(reader.getName()) ){
                                
                                                object.setIDInfo(vn.com.scb.bian.ws.IDInfoType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","address").equals(reader.getName()) || new javax.xml.namespace.QName("","address").equals(reader.getName()) ){
                                
                                                object.setAddress(vn.com.scb.bian.ws.AddressType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","fullNameNoneAccented").equals(reader.getName()) || new javax.xml.namespace.QName("","fullNameNoneAccented").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"fullNameNoneAccented" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFullNameNoneAccented(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","customerType").equals(reader.getName()) || new javax.xml.namespace.QName("","customerType").equals(reader.getName()) ){
                                
                                                object.setCustomerType(vn.com.scb.bian.ws.CustomerTypeInfoType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","shortName").equals(reader.getName()) || new javax.xml.namespace.QName("","shortName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"shortName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setShortName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","residentStatus").equals(reader.getName()) || new javax.xml.namespace.QName("","residentStatus").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"residentStatus" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setResidentStatus(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","legalGuardian").equals(reader.getName()) || new javax.xml.namespace.QName("","legalGuardian").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"legalGuardian" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLegalGuardian(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","nationlityCode").equals(reader.getName()) || new javax.xml.namespace.QName("","nationlityCode").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"nationlityCode" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNationlityCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","nationlity").equals(reader.getName()) || new javax.xml.namespace.QName("","nationlity").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"nationlity" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNationlity(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","language").equals(reader.getName()) || new javax.xml.namespace.QName("","language").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"language" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLanguage(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","localCode").equals(reader.getName()) || new javax.xml.namespace.QName("","localCode").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"localCode" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLocalCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","customerCatory").equals(reader.getName()) || new javax.xml.namespace.QName("","customerCatory").equals(reader.getName()) ){
                                
                                                object.setCustomerCatory(vn.com.scb.bian.ws.CustomerCatoryInfoType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","recordStatus").equals(reader.getName()) || new javax.xml.namespace.QName("","recordStatus").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"recordStatus" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setRecordStatus(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","authStatus").equals(reader.getName()) || new javax.xml.namespace.QName("","authStatus").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"authStatus" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAuthStatus(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","usIndicia").equals(reader.getName()) || new javax.xml.namespace.QName("","usIndicia").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"usIndicia" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setUsIndicia(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","directorName").equals(reader.getName()) || new javax.xml.namespace.QName("","directorName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"directorName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDirectorName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","legalRepresentativePrsnName").equals(reader.getName()) || new javax.xml.namespace.QName("","legalRepresentativePrsnName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"legalRepresentativePrsnName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLegalRepresentativePrsnName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","legalRepresentativePrsnID").equals(reader.getName()) || new javax.xml.namespace.QName("","legalRepresentativePrsnID").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"legalRepresentativePrsnID" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLegalRepresentativePrsnID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","bizContactPerson").equals(reader.getName()) || new javax.xml.namespace.QName("","bizContactPerson").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"bizContactPerson" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBizContactPerson(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","bizContactPersonPhoneNum").equals(reader.getName()) || new javax.xml.namespace.QName("","bizContactPersonPhoneNum").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"bizContactPersonPhoneNum" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBizContactPersonPhoneNum(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","bizLine").equals(reader.getName()) || new javax.xml.namespace.QName("","bizLine").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"bizLine" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBizLine(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","bizLicenseNum").equals(reader.getName()) || new javax.xml.namespace.QName("","bizLicenseNum").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"bizLicenseNum" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBizLicenseNum(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","bizLicenseIssueDate").equals(reader.getName()) || new javax.xml.namespace.QName("","bizLicenseIssueDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"bizLicenseIssueDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBizLicenseIssueDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","emailList").equals(reader.getName()) || new javax.xml.namespace.QName("","emailList").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"emailList" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEmailList(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","feeDebt").equals(reader.getName()) || new javax.xml.namespace.QName("","feeDebt").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"feeDebt" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setFeeDebt(
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
           
    