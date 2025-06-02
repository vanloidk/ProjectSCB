/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scb.com.vn.dbi.dao;

import scb.com.vn.dbi.dto.PblEbkScreen;

/**
 *
 * @author Chuong
 */
public class ExPaybillDAO extends BaseDAO {

    /**
     *
     * @param idscreen
     * @return
     * @throws Exception
     */
    public PblEbkScreen getEbkScreenById(int idscreen) throws Exception {
        return (PblEbkScreen) getSession().get(PblEbkScreen.class, idscreen);
    }
}
