package POJODeSerialize;

import java.util.List;

public class CreateOrderResponse {
          
	List<orders> orders;
	List<productOrderId> productOrderId;
	String message;
	public List<orders> getOrders() {
		return orders;
	}
	public void setOrders(List<orders> order) {
		this.orders = order;
	}
	public List<productOrderId> getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(List<productOrderId> productOrder) {
		this.productOrderId = productOrder;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
}
