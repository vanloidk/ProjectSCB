
/**
 * AccountInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.5  Built on : May 06, 2017 (03:45:50 BST)
 */

            
                package vn.com.scb.bian.ws;
            
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


            /**
            *  AccountInfoType bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class AccountInfoType
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = AccountInfoType
                Namespace URI = bian.scb.com.vn
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for CIFInfo
                        */

                        
                                    protected vn.com.scb.bian.ws.CIFDataType localCIFInfo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCIFInfoTracker = false ;

                           public boolean isCIFInfoSpecified(){
                               return localCIFInfoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.CIFDataType
                           */
                           public  vn.com.scb.bian.ws.CIFDataType getCIFInfo(){
                               return localCIFInfo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CIFInfo
                               */
                               public void setCIFInfo(vn.com.scb.bian.ws.CIFDataType param){
                            localCIFInfoTracker = param != null;
                                   
                                            this.localCIFInfo=param;
                                       

                               }
                            

                        /**
                        * field for CustomerInfo
                        */

                        
                                    protected vn.com.scb.bian.ws.CustomerInfoType localCustomerInfo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCustomerInfoTracker = false ;

                           public boolean isCustomerInfoSpecified(){
                               return localCustomerInfoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.CustomerInfoType
                           */
                           public  vn.com.scb.bian.ws.CustomerInfoType getCustomerInfo(){
                               return localCustomerInfo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CustomerInfo
                               */
                               public void setCustomerInfo(vn.com.scb.bian.ws.CustomerInfoType param){
                            localCustomerInfoTracker = param != null;
                                   
                                            this.localCustomerInfo=param;
                                       

                               }
                            

                        /**
                        * field for AccountNum
                        */

                        
                                    protected java.lang.String localAccountNum ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountNumTracker = false ;

                           public boolean isAccountNumSpecified(){
                               return localAccountNumTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountNum(){
                               return localAccountNum;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountNum
                               */
                               public void setAccountNum(java.lang.String param){
                            localAccountNumTracker = param != null;
                                   
                                            this.localAccountNum=param;
                                       

                               }
                            

                        /**
                        * field for AccountComment
                        */

                        
                                    protected java.lang.String localAccountComment ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountCommentTracker = false ;

                           public boolean isAccountCommentSpecified(){
                               return localAccountCommentTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountComment(){
                               return localAccountComment;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountComment
                               */
                               public void setAccountComment(java.lang.String param){
                            localAccountCommentTracker = param != null;
                                   
                                            this.localAccountComment=param;
                                       

                               }
                            

                        /**
                        * field for AccountName
                        */

                        
                                    protected java.lang.String localAccountName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountNameTracker = false ;

                           public boolean isAccountNameSpecified(){
                               return localAccountNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountName(){
                               return localAccountName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountName
                               */
                               public void setAccountName(java.lang.String param){
                            localAccountNameTracker = param != null;
                                   
                                            this.localAccountName=param;
                                       

                               }
                            

                        /**
                        * field for AccountType
                        */

                        
                                    protected java.lang.String localAccountType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountTypeTracker = false ;

                           public boolean isAccountTypeSpecified(){
                               return localAccountTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountType(){
                               return localAccountType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountType
                               */
                               public void setAccountType(java.lang.String param){
                            localAccountTypeTracker = param != null;
                                   
                                            this.localAccountType=param;
                                       

                               }
                            

                        /**
                        * field for AccountTypeName
                        */

                        
                                    protected java.lang.String localAccountTypeName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountTypeNameTracker = false ;

                           public boolean isAccountTypeNameSpecified(){
                               return localAccountTypeNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountTypeName(){
                               return localAccountTypeName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountTypeName
                               */
                               public void setAccountTypeName(java.lang.String param){
                            localAccountTypeNameTracker = param != null;
                                   
                                            this.localAccountTypeName=param;
                                       

                               }
                            

                        /**
                        * field for AccountCurrency
                        */

                        
                                    protected java.lang.String localAccountCurrency ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountCurrencyTracker = false ;

                           public boolean isAccountCurrencySpecified(){
                               return localAccountCurrencyTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountCurrency(){
                               return localAccountCurrency;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountCurrency
                               */
                               public void setAccountCurrency(java.lang.String param){
                            localAccountCurrencyTracker = param != null;
                                   
                                            this.localAccountCurrency=param;
                                       

                               }
                            

                        /**
                        * field for AccountOpeningAmount
                        */

                        
                                    protected float localAccountOpeningAmount ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountOpeningAmountTracker = false ;

                           public boolean isAccountOpeningAmountSpecified(){
                               return localAccountOpeningAmountTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return float
                           */
                           public  float getAccountOpeningAmount(){
                               return localAccountOpeningAmount;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountOpeningAmount
                               */
                               public void setAccountOpeningAmount(float param){
                            
                                       // setting primitive attribute tracker to true
                                       localAccountOpeningAmountTracker =
                                       !java.lang.Float.isNaN(param);
                                   
                                            this.localAccountOpeningAmount=param;
                                       

                               }
                            

                        /**
                        * field for AccountBalance
                        */

                        
                                    protected float localAccountBalance ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountBalanceTracker = false ;

                           public boolean isAccountBalanceSpecified(){
                               return localAccountBalanceTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return float
                           */
                           public  float getAccountBalance(){
                               return localAccountBalance;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountBalance
                               */
                               public void setAccountBalance(float param){
                            
                                       // setting primitive attribute tracker to true
                                       localAccountBalanceTracker =
                                       !java.lang.Float.isNaN(param);
                                   
                                            this.localAccountBalance=param;
                                       

                               }
                            

                        /**
                        * field for AccountBalanceAvailable
                        */

                        
                                    protected float localAccountBalanceAvailable ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountBalanceAvailableTracker = false ;

                           public boolean isAccountBalanceAvailableSpecified(){
                               return localAccountBalanceAvailableTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return float
                           */
                           public  float getAccountBalanceAvailable(){
                               return localAccountBalanceAvailable;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountBalanceAvailable
                               */
                               public void setAccountBalanceAvailable(float param){
                            
                                       // setting primitive attribute tracker to true
                                       localAccountBalanceAvailableTracker =
                                       !java.lang.Float.isNaN(param);
                                   
                                            this.localAccountBalanceAvailable=param;
                                       

                               }
                            

                        /**
                        * field for AccountExchangeBalanceEQV
                        */

                        
                                    protected float localAccountExchangeBalanceEQV ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountExchangeBalanceEQVTracker = false ;

                           public boolean isAccountExchangeBalanceEQVSpecified(){
                               return localAccountExchangeBalanceEQVTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return float
                           */
                           public  float getAccountExchangeBalanceEQV(){
                               return localAccountExchangeBalanceEQV;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountExchangeBalanceEQV
                               */
                               public void setAccountExchangeBalanceEQV(float param){
                            
                                       // setting primitive attribute tracker to true
                                       localAccountExchangeBalanceEQVTracker =
                                       !java.lang.Float.isNaN(param);
                                   
                                            this.localAccountExchangeBalanceEQV=param;
                                       

                               }
                            

                        /**
                        * field for AccountOpenDate
                        */

                        
                                    protected java.lang.String localAccountOpenDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountOpenDateTracker = false ;

                           public boolean isAccountOpenDateSpecified(){
                               return localAccountOpenDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountOpenDate(){
                               return localAccountOpenDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountOpenDate
                               */
                               public void setAccountOpenDate(java.lang.String param){
                            localAccountOpenDateTracker = param != null;
                                   
                                            this.localAccountOpenDate=param;
                                       

                               }
                            

                        /**
                        * field for AccountMaturityDate
                        */

                        
                                    protected java.lang.String localAccountMaturityDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountMaturityDateTracker = false ;

                           public boolean isAccountMaturityDateSpecified(){
                               return localAccountMaturityDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountMaturityDate(){
                               return localAccountMaturityDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountMaturityDate
                               */
                               public void setAccountMaturityDate(java.lang.String param){
                            localAccountMaturityDateTracker = param != null;
                                   
                                            this.localAccountMaturityDate=param;
                                       

                               }
                            

                        /**
                        * field for AccountStatus
                        */

                        
                                    protected java.lang.String localAccountStatus ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountStatusTracker = false ;

                           public boolean isAccountStatusSpecified(){
                               return localAccountStatusTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountStatus(){
                               return localAccountStatus;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountStatus
                               */
                               public void setAccountStatus(java.lang.String param){
                            localAccountStatusTracker = param != null;
                                   
                                            this.localAccountStatus=param;
                                       

                               }
                            

                        /**
                        * field for AccountClassMinBalance
                        */

                        
                                    protected float localAccountClassMinBalance ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountClassMinBalanceTracker = false ;

                           public boolean isAccountClassMinBalanceSpecified(){
                               return localAccountClassMinBalanceTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return float
                           */
                           public  float getAccountClassMinBalance(){
                               return localAccountClassMinBalance;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountClassMinBalance
                               */
                               public void setAccountClassMinBalance(float param){
                            
                                       // setting primitive attribute tracker to true
                                       localAccountClassMinBalanceTracker =
                                       !java.lang.Float.isNaN(param);
                                   
                                            this.localAccountClassMinBalance=param;
                                       

                               }
                            

                        /**
                        * field for AccountClassName
                        */

                        
                                    protected java.lang.String localAccountClassName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountClassNameTracker = false ;

                           public boolean isAccountClassNameSpecified(){
                               return localAccountClassNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountClassName(){
                               return localAccountClassName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountClassName
                               */
                               public void setAccountClassName(java.lang.String param){
                            localAccountClassNameTracker = param != null;
                                   
                                            this.localAccountClassName=param;
                                       

                               }
                            

                        /**
                        * field for AccountClassCode
                        */

                        
                                    protected java.lang.String localAccountClassCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountClassCodeTracker = false ;

                           public boolean isAccountClassCodeSpecified(){
                               return localAccountClassCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountClassCode(){
                               return localAccountClassCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountClassCode
                               */
                               public void setAccountClassCode(java.lang.String param){
                            localAccountClassCodeTracker = param != null;
                                   
                                            this.localAccountClassCode=param;
                                       

                               }
                            

                        /**
                        * field for AccountInterestRate
                        */

                        
                                    protected float localAccountInterestRate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountInterestRateTracker = false ;

                           public boolean isAccountInterestRateSpecified(){
                               return localAccountInterestRateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return float
                           */
                           public  float getAccountInterestRate(){
                               return localAccountInterestRate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountInterestRate
                               */
                               public void setAccountInterestRate(float param){
                            
                                       // setting primitive attribute tracker to true
                                       localAccountInterestRateTracker =
                                       !java.lang.Float.isNaN(param);
                                   
                                            this.localAccountInterestRate=param;
                                       

                               }
                            

                        /**
                        * field for AccountPromotionRate
                        */

                        
                                    protected java.lang.String localAccountPromotionRate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountPromotionRateTracker = false ;

                           public boolean isAccountPromotionRateSpecified(){
                               return localAccountPromotionRateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountPromotionRate(){
                               return localAccountPromotionRate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountPromotionRate
                               */
                               public void setAccountPromotionRate(java.lang.String param){
                            localAccountPromotionRateTracker = param != null;
                                   
                                            this.localAccountPromotionRate=param;
                                       

                               }
                            

                        /**
                        * field for AccountOpenBrandCode
                        */

                        
                                    protected java.lang.String localAccountOpenBrandCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountOpenBrandCodeTracker = false ;

                           public boolean isAccountOpenBrandCodeSpecified(){
                               return localAccountOpenBrandCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountOpenBrandCode(){
                               return localAccountOpenBrandCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountOpenBrandCode
                               */
                               public void setAccountOpenBrandCode(java.lang.String param){
                            localAccountOpenBrandCodeTracker = param != null;
                                   
                                            this.localAccountOpenBrandCode=param;
                                       

                               }
                            

                        /**
                        * field for AccountOpenBrandName
                        */

                        
                                    protected java.lang.String localAccountOpenBrandName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountOpenBrandNameTracker = false ;

                           public boolean isAccountOpenBrandNameSpecified(){
                               return localAccountOpenBrandNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountOpenBrandName(){
                               return localAccountOpenBrandName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountOpenBrandName
                               */
                               public void setAccountOpenBrandName(java.lang.String param){
                            localAccountOpenBrandNameTracker = param != null;
                                   
                                            this.localAccountOpenBrandName=param;
                                       

                               }
                            

                        /**
                        * field for AccountLatestTransDate
                        */

                        
                                    protected java.lang.String localAccountLatestTransDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountLatestTransDateTracker = false ;

                           public boolean isAccountLatestTransDateSpecified(){
                               return localAccountLatestTransDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountLatestTransDate(){
                               return localAccountLatestTransDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountLatestTransDate
                               */
                               public void setAccountLatestTransDate(java.lang.String param){
                            localAccountLatestTransDateTracker = param != null;
                                   
                                            this.localAccountLatestTransDate=param;
                                       

                               }
                            

                        /**
                        * field for AccountOverdraftDate
                        */

                        
                                    protected java.lang.String localAccountOverdraftDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountOverdraftDateTracker = false ;

                           public boolean isAccountOverdraftDateSpecified(){
                               return localAccountOverdraftDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountOverdraftDate(){
                               return localAccountOverdraftDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountOverdraftDate
                               */
                               public void setAccountOverdraftDate(java.lang.String param){
                            localAccountOverdraftDateTracker = param != null;
                                   
                                            this.localAccountOverdraftDate=param;
                                       

                               }
                            

                        /**
                        * field for AccountOverdraftExpiredDate
                        */

                        
                                    protected java.lang.String localAccountOverdraftExpiredDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountOverdraftExpiredDateTracker = false ;

                           public boolean isAccountOverdraftExpiredDateSpecified(){
                               return localAccountOverdraftExpiredDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountOverdraftExpiredDate(){
                               return localAccountOverdraftExpiredDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountOverdraftExpiredDate
                               */
                               public void setAccountOverdraftExpiredDate(java.lang.String param){
                            localAccountOverdraftExpiredDateTracker = param != null;
                                   
                                            this.localAccountOverdraftExpiredDate=param;
                                       

                               }
                            

                        /**
                        * field for AccountOverdraftLimit
                        */

                        
                                    protected float localAccountOverdraftLimit ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountOverdraftLimitTracker = false ;

                           public boolean isAccountOverdraftLimitSpecified(){
                               return localAccountOverdraftLimitTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return float
                           */
                           public  float getAccountOverdraftLimit(){
                               return localAccountOverdraftLimit;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountOverdraftLimit
                               */
                               public void setAccountOverdraftLimit(float param){
                            
                                       // setting primitive attribute tracker to true
                                       localAccountOverdraftLimitTracker =
                                       !java.lang.Float.isNaN(param);
                                   
                                            this.localAccountOverdraftLimit=param;
                                       

                               }
                            

                        /**
                        * field for AccountAuthorizedStatus
                        */

                        
                                    protected java.lang.String localAccountAuthorizedStatus ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountAuthorizedStatusTracker = false ;

                           public boolean isAccountAuthorizedStatusSpecified(){
                               return localAccountAuthorizedStatusTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountAuthorizedStatus(){
                               return localAccountAuthorizedStatus;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountAuthorizedStatus
                               */
                               public void setAccountAuthorizedStatus(java.lang.String param){
                            localAccountAuthorizedStatusTracker = param != null;
                                   
                                            this.localAccountAuthorizedStatus=param;
                                       

                               }
                            

                        /**
                        * field for AccountDelegatedPerson
                        */

                        
                                    protected java.lang.String localAccountDelegatedPerson ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountDelegatedPersonTracker = false ;

                           public boolean isAccountDelegatedPersonSpecified(){
                               return localAccountDelegatedPersonTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountDelegatedPerson(){
                               return localAccountDelegatedPerson;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountDelegatedPerson
                               */
                               public void setAccountDelegatedPerson(java.lang.String param){
                            localAccountDelegatedPersonTracker = param != null;
                                   
                                            this.localAccountDelegatedPerson=param;
                                       

                               }
                            

                        /**
                        * field for AccountCoownerName
                        */

                        
                                    protected java.lang.String localAccountCoownerName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountCoownerNameTracker = false ;

                           public boolean isAccountCoownerNameSpecified(){
                               return localAccountCoownerNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountCoownerName(){
                               return localAccountCoownerName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountCoownerName
                               */
                               public void setAccountCoownerName(java.lang.String param){
                            localAccountCoownerNameTracker = param != null;
                                   
                                            this.localAccountCoownerName=param;
                                       

                               }
                            

                        /**
                        * field for AccountTerm
                        */

                        
                                    protected java.lang.String localAccountTerm ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountTermTracker = false ;

                           public boolean isAccountTermSpecified(){
                               return localAccountTermTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountTerm(){
                               return localAccountTerm;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountTerm
                               */
                               public void setAccountTerm(java.lang.String param){
                            localAccountTermTracker = param != null;
                                   
                                            this.localAccountTerm=param;
                                       

                               }
                            

                        /**
                        * field for AccountAccrued
                        */

                        
                                    protected float localAccountAccrued ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountAccruedTracker = false ;

                           public boolean isAccountAccruedSpecified(){
                               return localAccountAccruedTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return float
                           */
                           public  float getAccountAccrued(){
                               return localAccountAccrued;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountAccrued
                               */
                               public void setAccountAccrued(float param){
                            
                                       // setting primitive attribute tracker to true
                                       localAccountAccruedTracker =
                                       !java.lang.Float.isNaN(param);
                                   
                                            this.localAccountAccrued=param;
                                       

                               }
                            

                        /**
                        * field for SavingApplicationInterest
                        */

                        
                                    protected java.lang.String localSavingApplicationInterest ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSavingApplicationInterestTracker = false ;

                           public boolean isSavingApplicationInterestSpecified(){
                               return localSavingApplicationInterestTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSavingApplicationInterest(){
                               return localSavingApplicationInterest;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SavingApplicationInterest
                               */
                               public void setSavingApplicationInterest(java.lang.String param){
                            localSavingApplicationInterestTracker = param != null;
                                   
                                            this.localSavingApplicationInterest=param;
                                       

                               }
                            

                        /**
                        * field for AccountSavingSerials
                        */

                        
                                    protected java.lang.String localAccountSavingSerials ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountSavingSerialsTracker = false ;

                           public boolean isAccountSavingSerialsSpecified(){
                               return localAccountSavingSerialsTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountSavingSerials(){
                               return localAccountSavingSerials;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountSavingSerials
                               */
                               public void setAccountSavingSerials(java.lang.String param){
                            localAccountSavingSerialsTracker = param != null;
                                   
                                            this.localAccountSavingSerials=param;
                                       

                               }
                            

                        /**
                        * field for AccountRolloverAmount
                        */

                        
                                    protected float localAccountRolloverAmount ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountRolloverAmountTracker = false ;

                           public boolean isAccountRolloverAmountSpecified(){
                               return localAccountRolloverAmountTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return float
                           */
                           public  float getAccountRolloverAmount(){
                               return localAccountRolloverAmount;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountRolloverAmount
                               */
                               public void setAccountRolloverAmount(float param){
                            
                                       // setting primitive attribute tracker to true
                                       localAccountRolloverAmountTracker =
                                       !java.lang.Float.isNaN(param);
                                   
                                            this.localAccountRolloverAmount=param;
                                       

                               }
                            

                        /**
                        * field for AccountStaffCode
                        */

                        
                                    protected java.lang.String localAccountStaffCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountStaffCodeTracker = false ;

                           public boolean isAccountStaffCodeSpecified(){
                               return localAccountStaffCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountStaffCode(){
                               return localAccountStaffCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountStaffCode
                               */
                               public void setAccountStaffCode(java.lang.String param){
                            localAccountStaffCodeTracker = param != null;
                                   
                                            this.localAccountStaffCode=param;
                                       

                               }
                            

                        /**
                        * field for SrcAccountNum
                        */

                        
                                    protected java.lang.String localSrcAccountNum ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSrcAccountNumTracker = false ;

                           public boolean isSrcAccountNumSpecified(){
                               return localSrcAccountNumTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getSrcAccountNum(){
                               return localSrcAccountNum;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SrcAccountNum
                               */
                               public void setSrcAccountNum(java.lang.String param){
                            localSrcAccountNumTracker = param != null;
                                   
                                            this.localSrcAccountNum=param;
                                       

                               }
                            

                        /**
                        * field for AccountNarrative
                        */

                        
                                    protected java.lang.String localAccountNarrative ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountNarrativeTracker = false ;

                           public boolean isAccountNarrativeSpecified(){
                               return localAccountNarrativeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountNarrative(){
                               return localAccountNarrative;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountNarrative
                               */
                               public void setAccountNarrative(java.lang.String param){
                            localAccountNarrativeTracker = param != null;
                                   
                                            this.localAccountNarrative=param;
                                       

                               }
                            

                        /**
                        * field for Payout
                        * This was an Array!
                        */

                        
                                    protected vn.com.scb.bian.ws.PayoutType[] localPayout ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPayoutTracker = false ;

                           public boolean isPayoutSpecified(){
                               return localPayoutTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.PayoutType[]
                           */
                           public  vn.com.scb.bian.ws.PayoutType[] getPayout(){
                               return localPayout;
                           }

                           
                        


                               
                              /**
                               * validate the array for Payout
                               */
                              protected void validatePayout(vn.com.scb.bian.ws.PayoutType[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Payout
                              */
                              public void setPayout(vn.com.scb.bian.ws.PayoutType[] param){
                              
                                   validatePayout(param);

                               localPayoutTracker = param != null;
                                      
                                      this.localPayout=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param vn.com.scb.bian.PayoutType
                             */
                             public void addPayout(vn.com.scb.bian.ws.PayoutType param){
                                   if (localPayout == null){
                                   localPayout = new vn.com.scb.bian.ws.PayoutType[]{};
                                   }

                            
                                 //update the setting tracker
                                localPayoutTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localPayout);
                               list.add(param);
                               this.localPayout =
                             (vn.com.scb.bian.ws.PayoutType[])list.toArray(new vn.com.scb.bian.ws.PayoutType[list.size()]);

                             }
                             

                        /**
                        * field for Redemption
                        */

                        
                                    protected vn.com.scb.bian.ws.RedemptionType localRedemption ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localRedemptionTracker = false ;

                           public boolean isRedemptionSpecified(){
                               return localRedemptionTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.RedemptionType
                           */
                           public  vn.com.scb.bian.ws.RedemptionType getRedemption(){
                               return localRedemption;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Redemption
                               */
                               public void setRedemption(vn.com.scb.bian.ws.RedemptionType param){
                            localRedemptionTracker = param != null;
                                   
                                            this.localRedemption=param;
                                       

                               }
                            

                        /**
                        * field for TerminationType
                        */

                        
                                    protected java.lang.String localTerminationType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTerminationTypeTracker = false ;

                           public boolean isTerminationTypeSpecified(){
                               return localTerminationTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTerminationType(){
                               return localTerminationType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TerminationType
                               */
                               public void setTerminationType(java.lang.String param){
                            localTerminationTypeTracker = param != null;
                                   
                                            this.localTerminationType=param;
                                       

                               }
                            

                        /**
                        * field for AccountExpectedAmount
                        */

                        
                                    protected float localAccountExpectedAmount ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountExpectedAmountTracker = false ;

                           public boolean isAccountExpectedAmountSpecified(){
                               return localAccountExpectedAmountTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return float
                           */
                           public  float getAccountExpectedAmount(){
                               return localAccountExpectedAmount;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountExpectedAmount
                               */
                               public void setAccountExpectedAmount(float param){
                            
                                       // setting primitive attribute tracker to true
                                       localAccountExpectedAmountTracker =
                                       !java.lang.Float.isNaN(param);
                                   
                                            this.localAccountExpectedAmount=param;
                                       

                               }
                            

                        /**
                        * field for Payin
                        * This was an Array!
                        */

                        
                                    protected vn.com.scb.bian.ws.PayinType[] localPayin ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPayinTracker = false ;

                           public boolean isPayinSpecified(){
                               return localPayinTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.PayinType[]
                           */
                           public  vn.com.scb.bian.ws.PayinType[] getPayin(){
                               return localPayin;
                           }

                           
                        


                               
                              /**
                               * validate the array for Payin
                               */
                              protected void validatePayin(vn.com.scb.bian.ws.PayinType[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Payin
                              */
                              public void setPayin(vn.com.scb.bian.ws.PayinType[] param){
                              
                                   validatePayin(param);

                               localPayinTracker = param != null;
                                      
                                      this.localPayin=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param vn.com.scb.bian.PayinType
                             */
                             public void addPayin(vn.com.scb.bian.ws.PayinType param){
                                   if (localPayin == null){
                                   localPayin = new vn.com.scb.bian.ws.PayinType[]{};
                                   }

                            
                                 //update the setting tracker
                                localPayinTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localPayin);
                               list.add(param);
                               this.localPayin =
                             (vn.com.scb.bian.ws.PayinType[])list.toArray(new vn.com.scb.bian.ws.PayinType[list.size()]);

                             }
                             

                        /**
                        * field for AccountOfficialOpenDate
                        */

                        
                                    protected java.lang.String localAccountOfficialOpenDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountOfficialOpenDateTracker = false ;

                           public boolean isAccountOfficialOpenDateSpecified(){
                               return localAccountOfficialOpenDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountOfficialOpenDate(){
                               return localAccountOfficialOpenDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountOfficialOpenDate
                               */
                               public void setAccountOfficialOpenDate(java.lang.String param){
                            localAccountOfficialOpenDateTracker = param != null;
                                   
                                            this.localAccountOfficialOpenDate=param;
                                       

                               }
                            

                        /**
                        * field for AccountInterestReceivableType
                        */

                        
                                    protected java.lang.String localAccountInterestReceivableType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountInterestReceivableTypeTracker = false ;

                           public boolean isAccountInterestReceivableTypeSpecified(){
                               return localAccountInterestReceivableTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountInterestReceivableType(){
                               return localAccountInterestReceivableType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountInterestReceivableType
                               */
                               public void setAccountInterestReceivableType(java.lang.String param){
                            localAccountInterestReceivableTypeTracker = param != null;
                                   
                                            this.localAccountInterestReceivableType=param;
                                       

                               }
                            

                        /**
                        * field for AccountLatestReceivableDate
                        */

                        
                                    protected java.lang.String localAccountLatestReceivableDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountLatestReceivableDateTracker = false ;

                           public boolean isAccountLatestReceivableDateSpecified(){
                               return localAccountLatestReceivableDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountLatestReceivableDate(){
                               return localAccountLatestReceivableDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountLatestReceivableDate
                               */
                               public void setAccountLatestReceivableDate(java.lang.String param){
                            localAccountLatestReceivableDateTracker = param != null;
                                   
                                            this.localAccountLatestReceivableDate=param;
                                       

                               }
                            

                        /**
                        * field for AccountLockReason
                        */

                        
                                    protected java.lang.String localAccountLockReason ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountLockReasonTracker = false ;

                           public boolean isAccountLockReasonSpecified(){
                               return localAccountLockReasonTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountLockReason(){
                               return localAccountLockReason;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountLockReason
                               */
                               public void setAccountLockReason(java.lang.String param){
                            localAccountLockReasonTracker = param != null;
                                   
                                            this.localAccountLockReason=param;
                                       

                               }
                            

                        /**
                        * field for AccountLockDate
                        */

                        
                                    protected java.lang.String localAccountLockDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountLockDateTracker = false ;

                           public boolean isAccountLockDateSpecified(){
                               return localAccountLockDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountLockDate(){
                               return localAccountLockDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountLockDate
                               */
                               public void setAccountLockDate(java.lang.String param){
                            localAccountLockDateTracker = param != null;
                                   
                                            this.localAccountLockDate=param;
                                       

                               }
                            

                        /**
                        * field for AccountLockExpireDate
                        */

                        
                                    protected java.lang.String localAccountLockExpireDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountLockExpireDateTracker = false ;

                           public boolean isAccountLockExpireDateSpecified(){
                               return localAccountLockExpireDateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountLockExpireDate(){
                               return localAccountLockExpireDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountLockExpireDate
                               */
                               public void setAccountLockExpireDate(java.lang.String param){
                            localAccountLockExpireDateTracker = param != null;
                                   
                                            this.localAccountLockExpireDate=param;
                                       

                               }
                            

                        /**
                        * field for AccountLockStatus
                        */

                        
                                    protected java.lang.String localAccountLockStatus ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountLockStatusTracker = false ;

                           public boolean isAccountLockStatusSpecified(){
                               return localAccountLockStatusTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountLockStatus(){
                               return localAccountLockStatus;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountLockStatus
                               */
                               public void setAccountLockStatus(java.lang.String param){
                            localAccountLockStatusTracker = param != null;
                                   
                                            this.localAccountLockStatus=param;
                                       

                               }
                            

                        /**
                        * field for AccountLockStatName
                        */

                        
                                    protected java.lang.String localAccountLockStatName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountLockStatNameTracker = false ;

                           public boolean isAccountLockStatNameSpecified(){
                               return localAccountLockStatNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountLockStatName(){
                               return localAccountLockStatName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountLockStatName
                               */
                               public void setAccountLockStatName(java.lang.String param){
                            localAccountLockStatNameTracker = param != null;
                                   
                                            this.localAccountLockStatName=param;
                                       

                               }
                            

                        /**
                        * field for AccountMorgateType
                        */

                        
                                    protected java.lang.String localAccountMorgateType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountMorgateTypeTracker = false ;

                           public boolean isAccountMorgateTypeSpecified(){
                               return localAccountMorgateTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountMorgateType(){
                               return localAccountMorgateType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountMorgateType
                               */
                               public void setAccountMorgateType(java.lang.String param){
                            localAccountMorgateTypeTracker = param != null;
                                   
                                            this.localAccountMorgateType=param;
                                       

                               }
                            

                        /**
                        * field for AccountMorgateLimit
                        */

                        
                                    protected java.lang.String localAccountMorgateLimit ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountMorgateLimitTracker = false ;

                           public boolean isAccountMorgateLimitSpecified(){
                               return localAccountMorgateLimitTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountMorgateLimit(){
                               return localAccountMorgateLimit;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountMorgateLimit
                               */
                               public void setAccountMorgateLimit(java.lang.String param){
                            localAccountMorgateLimitTracker = param != null;
                                   
                                            this.localAccountMorgateLimit=param;
                                       

                               }
                            

                        /**
                        * field for AccountMorgateLimitType
                        */

                        
                                    protected java.lang.String localAccountMorgateLimitType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountMorgateLimitTypeTracker = false ;

                           public boolean isAccountMorgateLimitTypeSpecified(){
                               return localAccountMorgateLimitTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountMorgateLimitType(){
                               return localAccountMorgateLimitType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountMorgateLimitType
                               */
                               public void setAccountMorgateLimitType(java.lang.String param){
                            localAccountMorgateLimitTypeTracker = param != null;
                                   
                                            this.localAccountMorgateLimitType=param;
                                       

                               }
                            

                        /**
                        * field for AccountSheetBalance
                        */

                        
                                    protected java.lang.String localAccountSheetBalance ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountSheetBalanceTracker = false ;

                           public boolean isAccountSheetBalanceSpecified(){
                               return localAccountSheetBalanceTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountSheetBalance(){
                               return localAccountSheetBalance;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountSheetBalance
                               */
                               public void setAccountSheetBalance(java.lang.String param){
                            localAccountSheetBalanceTracker = param != null;
                                   
                                            this.localAccountSheetBalance=param;
                                       

                               }
                            

                        /**
                        * field for AccountDebtGroup
                        */

                        
                                    protected java.lang.String localAccountDebtGroup ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountDebtGroupTracker = false ;

                           public boolean isAccountDebtGroupSpecified(){
                               return localAccountDebtGroupTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountDebtGroup(){
                               return localAccountDebtGroup;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountDebtGroup
                               */
                               public void setAccountDebtGroup(java.lang.String param){
                            localAccountDebtGroupTracker = param != null;
                                   
                                            this.localAccountDebtGroup=param;
                                       

                               }
                            

                        /**
                        * field for TdBookAccount
                        */

                        
                                    protected java.lang.String localTdBookAccount ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTdBookAccountTracker = false ;

                           public boolean isTdBookAccountSpecified(){
                               return localTdBookAccountTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTdBookAccount(){
                               return localTdBookAccount;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TdBookAccount
                               */
                               public void setTdBookAccount(java.lang.String param){
                            localTdBookAccountTracker = param != null;
                                   
                                            this.localTdBookAccount=param;
                                       

                               }
                            

                        /**
                        * field for BookAccBRN
                        */

                        
                                    protected java.lang.String localBookAccBRN ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBookAccBRNTracker = false ;

                           public boolean isBookAccBRNSpecified(){
                               return localBookAccBRNTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getBookAccBRN(){
                               return localBookAccBRN;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BookAccBRN
                               */
                               public void setBookAccBRN(java.lang.String param){
                            localBookAccBRNTracker = param != null;
                                   
                                            this.localBookAccBRN=param;
                                       

                               }
                            

                        /**
                        * field for AccountAutoRollType
                        */

                        
                                    protected java.lang.String localAccountAutoRollType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountAutoRollTypeTracker = false ;

                           public boolean isAccountAutoRollTypeSpecified(){
                               return localAccountAutoRollTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountAutoRollType(){
                               return localAccountAutoRollType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountAutoRollType
                               */
                               public void setAccountAutoRollType(java.lang.String param){
                            localAccountAutoRollTypeTracker = param != null;
                                   
                                            this.localAccountAutoRollType=param;
                                       

                               }
                            

                        /**
                        * field for AccountRollType
                        */

                        
                                    protected java.lang.String localAccountRollType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountRollTypeTracker = false ;

                           public boolean isAccountRollTypeSpecified(){
                               return localAccountRollTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountRollType(){
                               return localAccountRollType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountRollType
                               */
                               public void setAccountRollType(java.lang.String param){
                            localAccountRollTypeTracker = param != null;
                                   
                                            this.localAccountRollType=param;
                                       

                               }
                            

                        /**
                        * field for AccountOpenBrandInfo
                        */

                        
                                    protected vn.com.scb.bian.ws.BranchInfoType localAccountOpenBrandInfo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountOpenBrandInfoTracker = false ;

                           public boolean isAccountOpenBrandInfoSpecified(){
                               return localAccountOpenBrandInfoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.BranchInfoType
                           */
                           public  vn.com.scb.bian.ws.BranchInfoType getAccountOpenBrandInfo(){
                               return localAccountOpenBrandInfo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountOpenBrandInfo
                               */
                               public void setAccountOpenBrandInfo(vn.com.scb.bian.ws.BranchInfoType param){
                            localAccountOpenBrandInfoTracker = param != null;
                                   
                                            this.localAccountOpenBrandInfo=param;
                                       

                               }
                            

                        /**
                        * field for CustomerInfoNotes
                        */

                        
                                    protected vn.com.scb.bian.ws.CustomerInfoNotesType localCustomerInfoNotes ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCustomerInfoNotesTracker = false ;

                           public boolean isCustomerInfoNotesSpecified(){
                               return localCustomerInfoNotesTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return vn.com.scb.bian.CustomerInfoNotesType
                           */
                           public  vn.com.scb.bian.ws.CustomerInfoNotesType getCustomerInfoNotes(){
                               return localCustomerInfoNotes;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CustomerInfoNotes
                               */
                               public void setCustomerInfoNotes(vn.com.scb.bian.ws.CustomerInfoNotesType param){
                            localCustomerInfoNotesTracker = param != null;
                                   
                                            this.localCustomerInfoNotes=param;
                                       

                               }
                            

                        /**
                        * field for AccountAutoCredit
                        */

                        
                                    protected java.lang.String localAccountAutoCredit ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountAutoCreditTracker = false ;

                           public boolean isAccountAutoCreditSpecified(){
                               return localAccountAutoCreditTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountAutoCredit(){
                               return localAccountAutoCredit;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountAutoCredit
                               */
                               public void setAccountAutoCredit(java.lang.String param){
                            localAccountAutoCreditTracker = param != null;
                                   
                                            this.localAccountAutoCredit=param;
                                       

                               }
                            

                        /**
                        * field for AccountDays
                        */

                        
                                    protected java.lang.String localAccountDays ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountDaysTracker = false ;

                           public boolean isAccountDaysSpecified(){
                               return localAccountDaysTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountDays(){
                               return localAccountDays;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountDays
                               */
                               public void setAccountDays(java.lang.String param){
                            localAccountDaysTracker = param != null;
                                   
                                            this.localAccountDays=param;
                                       

                               }
                            

                        /**
                        * field for AccountMonths
                        */

                        
                                    protected java.lang.String localAccountMonths ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountMonthsTracker = false ;

                           public boolean isAccountMonthsSpecified(){
                               return localAccountMonthsTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountMonths(){
                               return localAccountMonths;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountMonths
                               */
                               public void setAccountMonths(java.lang.String param){
                            localAccountMonthsTracker = param != null;
                                   
                                            this.localAccountMonths=param;
                                       

                               }
                            

                        /**
                        * field for AccountYears
                        */

                        
                                    protected java.lang.String localAccountYears ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountYearsTracker = false ;

                           public boolean isAccountYearsSpecified(){
                               return localAccountYearsTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountYears(){
                               return localAccountYears;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountYears
                               */
                               public void setAccountYears(java.lang.String param){
                            localAccountYearsTracker = param != null;
                                   
                                            this.localAccountYears=param;
                                       

                               }
                            

                        /**
                        * field for AccountFeeForSpecial
                        */

                        
                                    protected java.lang.String localAccountFeeForSpecial ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountFeeForSpecialTracker = false ;

                           public boolean isAccountFeeForSpecialSpecified(){
                               return localAccountFeeForSpecialTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountFeeForSpecial(){
                               return localAccountFeeForSpecial;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountFeeForSpecial
                               */
                               public void setAccountFeeForSpecial(java.lang.String param){
                            localAccountFeeForSpecialTracker = param != null;
                                   
                                            this.localAccountFeeForSpecial=param;
                                       

                               }
                            

                        /**
                        * field for AccountRegistProfileCode
                        */

                        
                                    protected java.lang.String localAccountRegistProfileCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountRegistProfileCodeTracker = false ;

                           public boolean isAccountRegistProfileCodeSpecified(){
                               return localAccountRegistProfileCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountRegistProfileCode(){
                               return localAccountRegistProfileCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountRegistProfileCode
                               */
                               public void setAccountRegistProfileCode(java.lang.String param){
                            localAccountRegistProfileCodeTracker = param != null;
                                   
                                            this.localAccountRegistProfileCode=param;
                                       

                               }
                            

                        /**
                        * field for AccountBeautiNum
                        */

                        
                                    protected java.lang.String localAccountBeautiNum ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountBeautiNumTracker = false ;

                           public boolean isAccountBeautiNumSpecified(){
                               return localAccountBeautiNumTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountBeautiNum(){
                               return localAccountBeautiNum;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountBeautiNum
                               */
                               public void setAccountBeautiNum(java.lang.String param){
                            localAccountBeautiNumTracker = param != null;
                                   
                                            this.localAccountBeautiNum=param;
                                       

                               }
                            

                        /**
                        * field for AccountDesignate
                        */

                        
                                    protected java.lang.String localAccountDesignate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountDesignateTracker = false ;

                           public boolean isAccountDesignateSpecified(){
                               return localAccountDesignateTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAccountDesignate(){
                               return localAccountDesignate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountDesignate
                               */
                               public void setAccountDesignate(java.lang.String param){
                            localAccountDesignateTracker = param != null;
                                   
                                            this.localAccountDesignate=param;
                                       

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
                           namespacePrefix+":AccountInfoType",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "AccountInfoType",
                           xmlWriter);
                   }

               
                   }
                if (localCIFInfoTracker){
                                            if (localCIFInfo==null){
                                                 throw new org.apache.axis2.databinding.ADBException("CIFInfo cannot be null!!");
                                            }
                                           localCIFInfo.serialize(new javax.xml.namespace.QName("","CIFInfo"),
                                               xmlWriter);
                                        } if (localCustomerInfoTracker){
                                            if (localCustomerInfo==null){
                                                 throw new org.apache.axis2.databinding.ADBException("customerInfo cannot be null!!");
                                            }
                                           localCustomerInfo.serialize(new javax.xml.namespace.QName("","customerInfo"),
                                               xmlWriter);
                                        } if (localAccountNumTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountNum", xmlWriter);
                             

                                          if (localAccountNum==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountNum cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountNum);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountCommentTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountComment", xmlWriter);
                             

                                          if (localAccountComment==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountComment cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountComment);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountName", xmlWriter);
                             

                                          if (localAccountName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountType", xmlWriter);
                             

                                          if (localAccountType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountTypeNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountTypeName", xmlWriter);
                             

                                          if (localAccountTypeName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountTypeName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountTypeName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountCurrencyTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountCurrency", xmlWriter);
                             

                                          if (localAccountCurrency==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountCurrency cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountCurrency);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountOpeningAmountTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountOpeningAmount", xmlWriter);
                             
                                               if (java.lang.Float.isNaN(localAccountOpeningAmount)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("accountOpeningAmount cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountOpeningAmount));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountBalanceTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountBalance", xmlWriter);
                             
                                               if (java.lang.Float.isNaN(localAccountBalance)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("accountBalance cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountBalance));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountBalanceAvailableTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountBalanceAvailable", xmlWriter);
                             
                                               if (java.lang.Float.isNaN(localAccountBalanceAvailable)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("accountBalanceAvailable cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountBalanceAvailable));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountExchangeBalanceEQVTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountExchangeBalanceEQV", xmlWriter);
                             
                                               if (java.lang.Float.isNaN(localAccountExchangeBalanceEQV)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("accountExchangeBalanceEQV cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountExchangeBalanceEQV));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountOpenDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountOpenDate", xmlWriter);
                             

                                          if (localAccountOpenDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountOpenDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountOpenDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountMaturityDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountMaturityDate", xmlWriter);
                             

                                          if (localAccountMaturityDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountMaturityDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountMaturityDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountStatusTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountStatus", xmlWriter);
                             

                                          if (localAccountStatus==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountStatus cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountStatus);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountClassMinBalanceTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountClassMinBalance", xmlWriter);
                             
                                               if (java.lang.Float.isNaN(localAccountClassMinBalance)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("accountClassMinBalance cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountClassMinBalance));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountClassNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountClassName", xmlWriter);
                             

                                          if (localAccountClassName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountClassName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountClassName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountClassCodeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountClassCode", xmlWriter);
                             

                                          if (localAccountClassCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountClassCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountClassCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountInterestRateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountInterestRate", xmlWriter);
                             
                                               if (java.lang.Float.isNaN(localAccountInterestRate)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("accountInterestRate cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountInterestRate));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountPromotionRateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountPromotionRate", xmlWriter);
                             

                                          if (localAccountPromotionRate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountPromotionRate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountPromotionRate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountOpenBrandCodeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountOpenBrandCode", xmlWriter);
                             

                                          if (localAccountOpenBrandCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountOpenBrandCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountOpenBrandCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountOpenBrandNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountOpenBrandName", xmlWriter);
                             

                                          if (localAccountOpenBrandName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountOpenBrandName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountOpenBrandName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountLatestTransDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountLatestTransDate", xmlWriter);
                             

                                          if (localAccountLatestTransDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountLatestTransDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountLatestTransDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountOverdraftDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountOverdraftDate", xmlWriter);
                             

                                          if (localAccountOverdraftDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountOverdraftDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountOverdraftDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountOverdraftExpiredDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountOverdraftExpiredDate", xmlWriter);
                             

                                          if (localAccountOverdraftExpiredDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountOverdraftExpiredDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountOverdraftExpiredDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountOverdraftLimitTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountOverdraftLimit", xmlWriter);
                             
                                               if (java.lang.Float.isNaN(localAccountOverdraftLimit)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("accountOverdraftLimit cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountOverdraftLimit));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountAuthorizedStatusTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountAuthorizedStatus", xmlWriter);
                             

                                          if (localAccountAuthorizedStatus==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountAuthorizedStatus cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountAuthorizedStatus);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountDelegatedPersonTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountDelegatedPerson", xmlWriter);
                             

                                          if (localAccountDelegatedPerson==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountDelegatedPerson cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountDelegatedPerson);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountCoownerNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountCoownerName", xmlWriter);
                             

                                          if (localAccountCoownerName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountCoownerName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountCoownerName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountTermTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountTerm", xmlWriter);
                             

                                          if (localAccountTerm==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountTerm cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountTerm);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountAccruedTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountAccrued", xmlWriter);
                             
                                               if (java.lang.Float.isNaN(localAccountAccrued)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("accountAccrued cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountAccrued));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSavingApplicationInterestTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "savingApplicationInterest", xmlWriter);
                             

                                          if (localSavingApplicationInterest==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("savingApplicationInterest cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSavingApplicationInterest);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountSavingSerialsTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountSavingSerials", xmlWriter);
                             

                                          if (localAccountSavingSerials==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountSavingSerials cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountSavingSerials);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountRolloverAmountTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountRolloverAmount", xmlWriter);
                             
                                               if (java.lang.Float.isNaN(localAccountRolloverAmount)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("accountRolloverAmount cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountRolloverAmount));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountStaffCodeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountStaffCode", xmlWriter);
                             

                                          if (localAccountStaffCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountStaffCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountStaffCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSrcAccountNumTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "srcAccountNum", xmlWriter);
                             

                                          if (localSrcAccountNum==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("srcAccountNum cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localSrcAccountNum);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountNarrativeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountNarrative", xmlWriter);
                             

                                          if (localAccountNarrative==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountNarrative cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountNarrative);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPayoutTracker){
                                       if (localPayout!=null){
                                            for (int i = 0;i < localPayout.length;i++){
                                                if (localPayout[i] != null){
                                                 localPayout[i].serialize(new javax.xml.namespace.QName("","payout"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("payout cannot be null!!");
                                        
                                    }
                                 } if (localRedemptionTracker){
                                            if (localRedemption==null){
                                                 throw new org.apache.axis2.databinding.ADBException("redemption cannot be null!!");
                                            }
                                           localRedemption.serialize(new javax.xml.namespace.QName("","redemption"),
                                               xmlWriter);
                                        } if (localTerminationTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "terminationType", xmlWriter);
                             

                                          if (localTerminationType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("terminationType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTerminationType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountExpectedAmountTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountExpectedAmount", xmlWriter);
                             
                                               if (java.lang.Float.isNaN(localAccountExpectedAmount)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("accountExpectedAmount cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountExpectedAmount));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPayinTracker){
                                       if (localPayin!=null){
                                            for (int i = 0;i < localPayin.length;i++){
                                                if (localPayin[i] != null){
                                                 localPayin[i].serialize(new javax.xml.namespace.QName("","payin"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("payin cannot be null!!");
                                        
                                    }
                                 } if (localAccountOfficialOpenDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountOfficialOpenDate", xmlWriter);
                             

                                          if (localAccountOfficialOpenDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountOfficialOpenDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountOfficialOpenDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountInterestReceivableTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountInterestReceivableType", xmlWriter);
                             

                                          if (localAccountInterestReceivableType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountInterestReceivableType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountInterestReceivableType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountLatestReceivableDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountLatestReceivableDate", xmlWriter);
                             

                                          if (localAccountLatestReceivableDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountLatestReceivableDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountLatestReceivableDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountLockReasonTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountLockReason", xmlWriter);
                             

                                          if (localAccountLockReason==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountLockReason cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountLockReason);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountLockDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountLockDate", xmlWriter);
                             

                                          if (localAccountLockDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountLockDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountLockDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountLockExpireDateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountLockExpireDate", xmlWriter);
                             

                                          if (localAccountLockExpireDate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountLockExpireDate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountLockExpireDate);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountLockStatusTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountLockStatus", xmlWriter);
                             

                                          if (localAccountLockStatus==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountLockStatus cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountLockStatus);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountLockStatNameTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountLockStatName", xmlWriter);
                             

                                          if (localAccountLockStatName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountLockStatName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountLockStatName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountMorgateTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountMorgateType", xmlWriter);
                             

                                          if (localAccountMorgateType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountMorgateType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountMorgateType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountMorgateLimitTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountMorgateLimit", xmlWriter);
                             

                                          if (localAccountMorgateLimit==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountMorgateLimit cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountMorgateLimit);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountMorgateLimitTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountMorgateLimitType", xmlWriter);
                             

                                          if (localAccountMorgateLimitType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountMorgateLimitType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountMorgateLimitType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountSheetBalanceTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountSheetBalance", xmlWriter);
                             

                                          if (localAccountSheetBalance==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountSheetBalance cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountSheetBalance);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountDebtGroupTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountDebtGroup", xmlWriter);
                             

                                          if (localAccountDebtGroup==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountDebtGroup cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountDebtGroup);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTdBookAccountTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "tdBookAccount", xmlWriter);
                             

                                          if (localTdBookAccount==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("tdBookAccount cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTdBookAccount);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localBookAccBRNTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "bookAccBRN", xmlWriter);
                             

                                          if (localBookAccBRN==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("bookAccBRN cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localBookAccBRN);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountAutoRollTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountAutoRollType", xmlWriter);
                             

                                          if (localAccountAutoRollType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountAutoRollType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountAutoRollType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountRollTypeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountRollType", xmlWriter);
                             

                                          if (localAccountRollType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountRollType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountRollType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountOpenBrandInfoTracker){
                                            if (localAccountOpenBrandInfo==null){
                                                 throw new org.apache.axis2.databinding.ADBException("accountOpenBrandInfo cannot be null!!");
                                            }
                                           localAccountOpenBrandInfo.serialize(new javax.xml.namespace.QName("","accountOpenBrandInfo"),
                                               xmlWriter);
                                        } if (localCustomerInfoNotesTracker){
                                            if (localCustomerInfoNotes==null){
                                                 throw new org.apache.axis2.databinding.ADBException("customerInfoNotes cannot be null!!");
                                            }
                                           localCustomerInfoNotes.serialize(new javax.xml.namespace.QName("","customerInfoNotes"),
                                               xmlWriter);
                                        } if (localAccountAutoCreditTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountAutoCredit", xmlWriter);
                             

                                          if (localAccountAutoCredit==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountAutoCredit cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountAutoCredit);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountDaysTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountDays", xmlWriter);
                             

                                          if (localAccountDays==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountDays cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountDays);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountMonthsTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountMonths", xmlWriter);
                             

                                          if (localAccountMonths==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountMonths cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountMonths);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountYearsTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountYears", xmlWriter);
                             

                                          if (localAccountYears==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountYears cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountYears);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountFeeForSpecialTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountFeeForSpecial", xmlWriter);
                             

                                          if (localAccountFeeForSpecial==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountFeeForSpecial cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountFeeForSpecial);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountRegistProfileCodeTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountRegistProfileCode", xmlWriter);
                             

                                          if (localAccountRegistProfileCode==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountRegistProfileCode cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountRegistProfileCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountBeautiNumTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountBeautiNum", xmlWriter);
                             

                                          if (localAccountBeautiNum==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountBeautiNum cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountBeautiNum);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountDesignateTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "accountDesignate", xmlWriter);
                             

                                          if (localAccountDesignate==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("accountDesignate cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAccountDesignate);
                                            
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
        public static AccountInfoType parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            AccountInfoType object =
                new AccountInfoType();

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
                    
                            if (!"AccountInfoType".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (AccountInfoType)vn.com.scb.bian.ws.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list38 = new java.util.ArrayList();
                    
                        java.util.ArrayList list42 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CIFInfo").equals(reader.getName()) || new javax.xml.namespace.QName("","CIFInfo").equals(reader.getName()) ){
                                
                                                object.setCIFInfo(vn.com.scb.bian.ws.CIFDataType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","customerInfo").equals(reader.getName()) || new javax.xml.namespace.QName("","customerInfo").equals(reader.getName()) ){
                                
                                                object.setCustomerInfo(vn.com.scb.bian.ws.CustomerInfoType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountNum").equals(reader.getName()) || new javax.xml.namespace.QName("","accountNum").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountNum" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountNum(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountComment").equals(reader.getName()) || new javax.xml.namespace.QName("","accountComment").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountComment" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountComment(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountName").equals(reader.getName()) || new javax.xml.namespace.QName("","accountName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountType").equals(reader.getName()) || new javax.xml.namespace.QName("","accountType").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountType" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountTypeName").equals(reader.getName()) || new javax.xml.namespace.QName("","accountTypeName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountTypeName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountTypeName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountCurrency").equals(reader.getName()) || new javax.xml.namespace.QName("","accountCurrency").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountCurrency" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountCurrency(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountOpeningAmount").equals(reader.getName()) || new javax.xml.namespace.QName("","accountOpeningAmount").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountOpeningAmount" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountOpeningAmount(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToFloat(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setAccountOpeningAmount(java.lang.Float.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountBalance").equals(reader.getName()) || new javax.xml.namespace.QName("","accountBalance").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountBalance" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountBalance(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToFloat(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setAccountBalance(java.lang.Float.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountBalanceAvailable").equals(reader.getName()) || new javax.xml.namespace.QName("","accountBalanceAvailable").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountBalanceAvailable" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountBalanceAvailable(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToFloat(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setAccountBalanceAvailable(java.lang.Float.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountExchangeBalanceEQV").equals(reader.getName()) || new javax.xml.namespace.QName("","accountExchangeBalanceEQV").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountExchangeBalanceEQV" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountExchangeBalanceEQV(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToFloat(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setAccountExchangeBalanceEQV(java.lang.Float.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountOpenDate").equals(reader.getName()) || new javax.xml.namespace.QName("","accountOpenDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountOpenDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountOpenDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountMaturityDate").equals(reader.getName()) || new javax.xml.namespace.QName("","accountMaturityDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountMaturityDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountMaturityDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountStatus").equals(reader.getName()) || new javax.xml.namespace.QName("","accountStatus").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountStatus" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountStatus(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountClassMinBalance").equals(reader.getName()) || new javax.xml.namespace.QName("","accountClassMinBalance").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountClassMinBalance" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountClassMinBalance(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToFloat(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setAccountClassMinBalance(java.lang.Float.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountClassName").equals(reader.getName()) || new javax.xml.namespace.QName("","accountClassName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountClassName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountClassName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountClassCode").equals(reader.getName()) || new javax.xml.namespace.QName("","accountClassCode").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountClassCode" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountClassCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountInterestRate").equals(reader.getName()) || new javax.xml.namespace.QName("","accountInterestRate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountInterestRate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountInterestRate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToFloat(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setAccountInterestRate(java.lang.Float.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountPromotionRate").equals(reader.getName()) || new javax.xml.namespace.QName("","accountPromotionRate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountPromotionRate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountPromotionRate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountOpenBrandCode").equals(reader.getName()) || new javax.xml.namespace.QName("","accountOpenBrandCode").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountOpenBrandCode" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountOpenBrandCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountOpenBrandName").equals(reader.getName()) || new javax.xml.namespace.QName("","accountOpenBrandName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountOpenBrandName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountOpenBrandName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountLatestTransDate").equals(reader.getName()) || new javax.xml.namespace.QName("","accountLatestTransDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountLatestTransDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountLatestTransDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountOverdraftDate").equals(reader.getName()) || new javax.xml.namespace.QName("","accountOverdraftDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountOverdraftDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountOverdraftDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountOverdraftExpiredDate").equals(reader.getName()) || new javax.xml.namespace.QName("","accountOverdraftExpiredDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountOverdraftExpiredDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountOverdraftExpiredDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountOverdraftLimit").equals(reader.getName()) || new javax.xml.namespace.QName("","accountOverdraftLimit").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountOverdraftLimit" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountOverdraftLimit(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToFloat(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setAccountOverdraftLimit(java.lang.Float.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountAuthorizedStatus").equals(reader.getName()) || new javax.xml.namespace.QName("","accountAuthorizedStatus").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountAuthorizedStatus" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountAuthorizedStatus(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountDelegatedPerson").equals(reader.getName()) || new javax.xml.namespace.QName("","accountDelegatedPerson").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountDelegatedPerson" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountDelegatedPerson(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountCoownerName").equals(reader.getName()) || new javax.xml.namespace.QName("","accountCoownerName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountCoownerName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountCoownerName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountTerm").equals(reader.getName()) || new javax.xml.namespace.QName("","accountTerm").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountTerm" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountTerm(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountAccrued").equals(reader.getName()) || new javax.xml.namespace.QName("","accountAccrued").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountAccrued" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountAccrued(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToFloat(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setAccountAccrued(java.lang.Float.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","savingApplicationInterest").equals(reader.getName()) || new javax.xml.namespace.QName("","savingApplicationInterest").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"savingApplicationInterest" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSavingApplicationInterest(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountSavingSerials").equals(reader.getName()) || new javax.xml.namespace.QName("","accountSavingSerials").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountSavingSerials" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountSavingSerials(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountRolloverAmount").equals(reader.getName()) || new javax.xml.namespace.QName("","accountRolloverAmount").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountRolloverAmount" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountRolloverAmount(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToFloat(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setAccountRolloverAmount(java.lang.Float.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountStaffCode").equals(reader.getName()) || new javax.xml.namespace.QName("","accountStaffCode").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountStaffCode" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountStaffCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","srcAccountNum").equals(reader.getName()) || new javax.xml.namespace.QName("","srcAccountNum").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"srcAccountNum" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSrcAccountNum(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountNarrative").equals(reader.getName()) || new javax.xml.namespace.QName("","accountNarrative").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountNarrative" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountNarrative(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","payout").equals(reader.getName()) || new javax.xml.namespace.QName("","payout").equals(reader.getName()) ){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                    list38.add(vn.com.scb.bian.ws.PayoutType.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone38 = false;
                                                        while(!loopDone38){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone38 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","payout").equals(reader.getName())){
                                                                    list38.add(vn.com.scb.bian.ws.PayoutType.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone38 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setPayout((vn.com.scb.bian.ws.PayoutType[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(vn.com.scb.bian.ws.PayoutType.class,
                                                                list38));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","redemption").equals(reader.getName()) || new javax.xml.namespace.QName("","redemption").equals(reader.getName()) ){
                                
                                                object.setRedemption(vn.com.scb.bian.ws.RedemptionType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","terminationType").equals(reader.getName()) || new javax.xml.namespace.QName("","terminationType").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"terminationType" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTerminationType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountExpectedAmount").equals(reader.getName()) || new javax.xml.namespace.QName("","accountExpectedAmount").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountExpectedAmount" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountExpectedAmount(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToFloat(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setAccountExpectedAmount(java.lang.Float.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","payin").equals(reader.getName()) || new javax.xml.namespace.QName("","payin").equals(reader.getName()) ){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                    list42.add(vn.com.scb.bian.ws.PayinType.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone42 = false;
                                                        while(!loopDone42){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone42 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","payin").equals(reader.getName())){
                                                                    list42.add(vn.com.scb.bian.ws.PayinType.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone42 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setPayin((vn.com.scb.bian.ws.PayinType[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(vn.com.scb.bian.ws.PayinType.class,
                                                                list42));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountOfficialOpenDate").equals(reader.getName()) || new javax.xml.namespace.QName("","accountOfficialOpenDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountOfficialOpenDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountOfficialOpenDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountInterestReceivableType").equals(reader.getName()) || new javax.xml.namespace.QName("","accountInterestReceivableType").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountInterestReceivableType" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountInterestReceivableType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountLatestReceivableDate").equals(reader.getName()) || new javax.xml.namespace.QName("","accountLatestReceivableDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountLatestReceivableDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountLatestReceivableDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountLockReason").equals(reader.getName()) || new javax.xml.namespace.QName("","accountLockReason").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountLockReason" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountLockReason(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountLockDate").equals(reader.getName()) || new javax.xml.namespace.QName("","accountLockDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountLockDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountLockDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountLockExpireDate").equals(reader.getName()) || new javax.xml.namespace.QName("","accountLockExpireDate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountLockExpireDate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountLockExpireDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountLockStatus").equals(reader.getName()) || new javax.xml.namespace.QName("","accountLockStatus").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountLockStatus" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountLockStatus(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountLockStatName").equals(reader.getName()) || new javax.xml.namespace.QName("","accountLockStatName").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountLockStatName" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountLockStatName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountMorgateType").equals(reader.getName()) || new javax.xml.namespace.QName("","accountMorgateType").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountMorgateType" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountMorgateType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountMorgateLimit").equals(reader.getName()) || new javax.xml.namespace.QName("","accountMorgateLimit").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountMorgateLimit" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountMorgateLimit(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountMorgateLimitType").equals(reader.getName()) || new javax.xml.namespace.QName("","accountMorgateLimitType").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountMorgateLimitType" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountMorgateLimitType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountSheetBalance").equals(reader.getName()) || new javax.xml.namespace.QName("","accountSheetBalance").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountSheetBalance" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountSheetBalance(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountDebtGroup").equals(reader.getName()) || new javax.xml.namespace.QName("","accountDebtGroup").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountDebtGroup" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountDebtGroup(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","tdBookAccount").equals(reader.getName()) || new javax.xml.namespace.QName("","tdBookAccount").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"tdBookAccount" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTdBookAccount(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","bookAccBRN").equals(reader.getName()) || new javax.xml.namespace.QName("","bookAccBRN").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"bookAccBRN" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBookAccBRN(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountAutoRollType").equals(reader.getName()) || new javax.xml.namespace.QName("","accountAutoRollType").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountAutoRollType" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountAutoRollType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountRollType").equals(reader.getName()) || new javax.xml.namespace.QName("","accountRollType").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountRollType" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountRollType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountOpenBrandInfo").equals(reader.getName()) || new javax.xml.namespace.QName("","accountOpenBrandInfo").equals(reader.getName()) ){
                                
                                                object.setAccountOpenBrandInfo(vn.com.scb.bian.ws.BranchInfoType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","customerInfoNotes").equals(reader.getName()) || new javax.xml.namespace.QName("","customerInfoNotes").equals(reader.getName()) ){
                                
                                                object.setCustomerInfoNotes(vn.com.scb.bian.ws.CustomerInfoNotesType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountAutoCredit").equals(reader.getName()) || new javax.xml.namespace.QName("","accountAutoCredit").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountAutoCredit" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountAutoCredit(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountDays").equals(reader.getName()) || new javax.xml.namespace.QName("","accountDays").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountDays" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountDays(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountMonths").equals(reader.getName()) || new javax.xml.namespace.QName("","accountMonths").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountMonths" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountMonths(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountYears").equals(reader.getName()) || new javax.xml.namespace.QName("","accountYears").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountYears" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountYears(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountFeeForSpecial").equals(reader.getName()) || new javax.xml.namespace.QName("","accountFeeForSpecial").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountFeeForSpecial" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountFeeForSpecial(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountRegistProfileCode").equals(reader.getName()) || new javax.xml.namespace.QName("","accountRegistProfileCode").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountRegistProfileCode" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountRegistProfileCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountBeautiNum").equals(reader.getName()) || new javax.xml.namespace.QName("","accountBeautiNum").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountBeautiNum" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountBeautiNum(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","accountDesignate").equals(reader.getName()) || new javax.xml.namespace.QName("","accountDesignate").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"accountDesignate" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountDesignate(
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
           
    