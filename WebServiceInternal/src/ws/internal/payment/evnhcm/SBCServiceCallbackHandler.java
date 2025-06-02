
/**
 * SBCServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.5  Built on : May 06, 2017 (03:45:26 BST)
 */

    package ws.internal.payment.evnhcm;

    /**
     *  SBCServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class SBCServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public SBCServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public SBCServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for bankCheckConfirm method
            * override this method for handling normal response from bankCheckConfirm operation
            */
           public void receiveResultbankCheckConfirm(
                    ws.internal.payment.evnhcm.BankCheckConfirmResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bankCheckConfirm operation
           */
            public void receiveErrorbankCheckConfirm(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bankRequestDuration method
            * override this method for handling normal response from bankRequestDuration operation
            */
           public void receiveResultbankRequestDuration(
                    ws.internal.payment.evnhcm.BankRequestDurationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bankRequestDuration operation
           */
            public void receiveErrorbankRequestDuration(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bankRequestMaDL method
            * override this method for handling normal response from bankRequestMaDL operation
            */
           public void receiveResultbankRequestMaDL(
                    ws.internal.payment.evnhcm.BankRequestMaDLResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bankRequestMaDL operation
           */
            public void receiveErrorbankRequestMaDL(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bankCancelHDDT method
            * override this method for handling normal response from bankCancelHDDT operation
            */
           public void receiveResultbankCancelHDDT(
                    ws.internal.payment.evnhcm.BankCancelHDDTResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bankCancelHDDT operation
           */
            public void receiveErrorbankCancelHDDT(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bankRequest method
            * override this method for handling normal response from bankRequest operation
            */
           public void receiveResultbankRequest(
                    ws.internal.payment.evnhcm.BankRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bankRequest operation
           */
            public void receiveErrorbankRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bankRequestAddress method
            * override this method for handling normal response from bankRequestAddress operation
            */
           public void receiveResultbankRequestAddress(
                    ws.internal.payment.evnhcm.BankRequestAddressResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bankRequestAddress operation
           */
            public void receiveErrorbankRequestAddress(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bankChangeSerial method
            * override this method for handling normal response from bankChangeSerial operation
            */
           public void receiveResultbankChangeSerial(
                    ws.internal.payment.evnhcm.BankChangeSerialResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bankChangeSerial operation
           */
            public void receiveErrorbankChangeSerial(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bankConfirmHDDT method
            * override this method for handling normal response from bankConfirmHDDT operation
            */
           public void receiveResultbankConfirmHDDT(
                    ws.internal.payment.evnhcm.BankConfirmHDDTResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bankConfirmHDDT operation
           */
            public void receiveErrorbankConfirmHDDT(java.lang.Exception e) {
            }
                


    }
    