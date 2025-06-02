package scb.com.vn.common.model.iibpayment;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "payBill_in")
public class PayBill_in {
	private TransactionInfoType transactionInfo = new TransactionInfoType();
	private BillPaymentInfoType billPaymentInfo = new BillPaymentInfoType();
	private BillPaymentDetailItemType billPaymentDetail = new BillPaymentDetailItemType();
	
	@XmlElement(name = "transactionInfo")
	public TransactionInfoType getTransactionInfo() {
		return transactionInfo;
	}
	public void setTransactionInfo(TransactionInfoType transactionInfo) {
		this.transactionInfo = transactionInfo;
	}
	
	@XmlElement(name = "billPaymentInfo")
	public BillPaymentInfoType getBillPaymentInfo() {
		return billPaymentInfo;
	}
	public void setBillPaymentInfo(BillPaymentInfoType billPaymentInfo) {
		this.billPaymentInfo = billPaymentInfo;
	}
	
	@XmlElement(name = "billPaymentDetail")
	public BillPaymentDetailItemType getBillPaymentDetail() {
		return billPaymentDetail;
	}
	public void setBillPaymentDetail(BillPaymentDetailItemType billPaymentDetail) {
		this.billPaymentDetail = billPaymentDetail;
	}
}
