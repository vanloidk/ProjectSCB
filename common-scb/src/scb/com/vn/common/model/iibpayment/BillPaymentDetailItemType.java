package scb.com.vn.common.model.iibpayment;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "billPaymentDetail")
public class BillPaymentDetailItemType {
	
	private List<BillPaymentDetailType> billPaymentDetail = new ArrayList<>();

	@XmlElement(name = "Item")
	public List<BillPaymentDetailType> getBillPaymentDetail() {
		return billPaymentDetail;
	}

	public void setBillPaymentDetail(List<BillPaymentDetailType> billPaymentDetail) {
		this.billPaymentDetail = billPaymentDetail;
	}
}