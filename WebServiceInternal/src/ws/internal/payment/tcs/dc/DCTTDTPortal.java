

/**
 * DCTTDTPortal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.5  Built on : May 06, 2017 (03:45:26 BST)
 */

    package ws.internal.payment.tcs.dc;

    /*
     *  DCTTDTPortal java interface
     */

    public interface DCTTDTPortal {
          

        /**
          * Auto generated method signature
          * 
                    * @param reconcileProcess0
                
         */

         
                     public ws.internal.payment.tcs.dc.ReconcileProcessResponse reconcileProcess(

                        ws.internal.payment.tcs.dc.ReconcileProcess reconcileProcess0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param reconcileProcess0
            
          */
        public void startreconcileProcess(

            ws.internal.payment.tcs.dc.ReconcileProcess reconcileProcess0,

            final ws.internal.payment.tcs.dc.DCTTDTPortalCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    