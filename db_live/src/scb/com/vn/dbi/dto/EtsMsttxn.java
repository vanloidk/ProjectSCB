package scb.com.vn.dbi.dto;

/**
 *
 * @author system
 */
public class EtsMsttxn implements java.io.Serializable {

    private static final long serialVersionUID = 6027676768216594488L;

    private String idtxn;
    private String description;
    private String isenable;

    /**
     * Create a new instance of EtsMsttxn
     */
    public EtsMsttxn() {
    }

    /**
     *
     * @return
     */
    public String getIdtxn() {
        return this.idtxn;
    }

    /**
     *
     * @param idtxn
     */
    public void setIdtxn(String idtxn) {
        this.idtxn = idtxn;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getIsenable() {
        return this.isenable;
    }

    /**
     *
     * @param isenable
     */
    public void setIsenable(String isenable) {
        this.isenable = isenable;
    }

}
