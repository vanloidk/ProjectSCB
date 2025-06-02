/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.bo;

import scb.com.vn.dbi.dao.ExPaybillDAO;
import scb.com.vn.dbi.dto.PblEbkScreen;
import scb.com.vn.dbi.utility.HibernateUtil;

/**
 *
 * @author Chuong
 */
public class ExPayBillBO extends BaseSMSSCBBO {

    private ExPaybillDAO expaybilldao;

    /**
     * Create a new instance of ExPayBillBO
     * @throws java.lang.Exception
     */
    public ExPayBillBO() throws Exception {
        expaybilldao = new ExPaybillDAO();
        setSession(HibernateUtil.getSessionFactorySmsScb().getCurrentSession());
        expaybilldao.setSession(getSession());
    }

    /**
     *
     * @param idscreen
     * @return
     * @throws Exception
     */
    public PblEbkScreen getEbkScreenById(int idscreen) throws Exception {
        PblEbkScreen result = null;
        beginTransaction();
        PblEbkScreen r = expaybilldao.getEbkScreenById(idscreen);
        if (r != null) {
            result = HibernateUtil.getMapper().map(r, r.getClass());
        }
        commitTransaction();
        return result;
    }
}
