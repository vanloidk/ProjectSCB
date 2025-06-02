package scb.com.vn.common.model.iibpayment;


import java.util.ArrayList;
import java.util.List;

public class GetBillInfo_outType {
	private TransactionInfoType transactionInfo = new TransactionInfoType();
	private BillPaymentInfoType billPaymentInfo = new BillPaymentInfoType();
	private List<BillPaymentDetailType> billPaymentDetail = new ArrayList<>();
	
	public TransactionInfoType getTransactionInfo() {
		return transactionInfo;
	}
	public void setTransactionInfo(TransactionInfoType transactionInfo) {
		this.transactionInfo = transactionInfo;
	}
	public BillPaymentInfoType getBillPaymentInfo() {
		return billPaymentInfo;
	}
	public void setBillPaymentInfo(BillPaymentInfoType billPaymentInfo) {
		this.billPaymentInfo = billPaymentInfo;
	}
	public List<BillPaymentDetailType> getBillPaymentDetail() {
		return billPaymentDetail;
	}
	public void setBillPaymentDetail(List<BillPaymentDetailType> billPaymentDetail) {
		this.billPaymentDetail = billPaymentDetail;
	}
}