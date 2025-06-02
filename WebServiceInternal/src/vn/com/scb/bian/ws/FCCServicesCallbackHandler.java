
/**
 * FCCServicesCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.5  Built on : May 06, 2017 (03:45:26 BST)
 */

    package vn.com.scb.bian.ws;

    /**
     *  FCCServicesCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class FCCServicesCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public FCCServicesCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public FCCServicesCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for kycScore method
            * override this method for handling normal response from kycScore operation
            */
           public void receiveResultkycScore(
                    vn.com.scb.bian.ws.KycScore_out result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from kycScore operation
           */
            public void receiveErrorkycScore(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for blockReleaseSerialNumber method
            * override this method for handling normal response from blockReleaseSerialNumber operation
            */
           public void receiveResultblockReleaseSerialNumber(
                    vn.com.scb.bian.ws.BlockReleaseSerialNumber_out result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from blockReleaseSerialNumber operation
           */
            public void receiveErrorblockReleaseSerialNumber(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for publishSerialNumber method
            * override this method for handling normal response from publishSerialNumber operation
            */
           public void receiveResultpublishSerialNumber(
                    vn.com.scb.bian.ws.PublishSerialNumber_out result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from publishSerialNumber operation
           */
            public void receiveErrorpublishSerialNumber(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for selectSerialNumber method
            * override this method for handling normal response from selectSerialNumber operation
            */
           public void receiveResultselectSerialNumber(
                    vn.com.scb.bian.ws.SelectSerialNumber_out result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from selectSerialNumber operation
           */
            public void receiveErrorselectSerialNumber(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for executeCollateralUserReference method
            * override this method for handling normal response from executeCollateralUserReference operation
            */
           public void receiveResultexecuteCollateralUserReference(
                    vn.com.scb.bian.ws.ExecuteCollateralUserReference_out result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from executeCollateralUserReference operation
           */
            public void receiveErrorexecuteCollateralUserReference(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retreiveSerialNumber method
            * override this method for handling normal response from retreiveSerialNumber operation
            */
           public void receiveResultretreiveSerialNumber(
                    vn.com.scb.bian.ws.RetreiveSerialNumber_out result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retreiveSerialNumber operation
           */
            public void receiveErrorretreiveSerialNumber(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCollateralInfo method
            * override this method for handling normal response from retrieveCollateralInfo operation
            */
           public void receiveResultretrieveCollateralInfo(
                    vn.com.scb.bian.ws.RetrieveCollateralInfo_out result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCollateralInfo operation
           */
            public void receiveErrorretrieveCollateralInfo(java.lang.Exception e) {
            }
                


    }
    