/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.model;

public class NotificationFirbaseResp {
    
    private String multicast_id;
    private String success;
    private String failure;
    private String canonical_ids;
    private Object results;
    
    public Object getResults() {
        return results;
    }
    
    public void setResults(Object results) {
        this.results = results;
    }

    public String getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(String multicast_id) {
        this.multicast_id = multicast_id;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }

    public String getCanonical_ids() {
        return canonical_ids;
    }

    public void setCanonical_ids(String canonical_ids) {
        this.canonical_ids = canonical_ids;
    }

    public static class result
    {
        private String  error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        
    }
}
