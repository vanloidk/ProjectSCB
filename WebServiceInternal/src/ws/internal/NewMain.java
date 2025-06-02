/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.internal;

/**
 *
 * @author loinv3
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String data = "TRAN THI DU";
        String[] datas = data.split("@");
        int aa = datas.length;

        if (aa >= 5 && !datas[4].isEmpty()) {
            System.out.println(datas.length);
        } else {
            int dd = datas.length;
        }
        
        int bb = datas.length;
        int cc = datas.length;
    }

}
