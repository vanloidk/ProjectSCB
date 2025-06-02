/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dto.CreditCardPay;

import java.math.BigDecimal;

/**
 *
 * @author loinv3
 */
public class PmtInfoV11ResDto implements java.io.Serializable {

    private static final long serialVersionUID = -5674858775824102589L;

    private BigDecimal PrevStmtClsBal;
    private BigDecimal StmtClsBal;
    private BigDecimal PrevCardStmtClsBal;
    private BigDecimal CurClsBal;

    public PmtInfoV11ResDto(){}
    
    public BigDecimal getPrevStmtClsBal() {
        return PrevStmtClsBal;
    }

    public void setPrevStmtClsBal(BigDecimal PrevStmtClsBal) {
        this.PrevStmtClsBal = PrevStmtClsBal;
    }

    public BigDecimal getStmtClsBal() {
        return StmtClsBal;
    }

    public void setStmtClsBal(BigDecimal StmtClsBal) {
        this.StmtClsBal = StmtClsBal;
    }

    public BigDecimal getPrevCardStmtClsBal() {
        return PrevCardStmtClsBal;
    }

    public void setPrevCardStmtClsBal(BigDecimal PrevCardStmtClsBal) {
        this.PrevCardStmtClsBal = PrevCardStmtClsBal;
    }

    public BigDecimal getCurClsBal() {
        return CurClsBal;
    }

    public void setCurClsBal(BigDecimal CurClsBal) {
        this.CurClsBal = CurClsBal;
    }

}
