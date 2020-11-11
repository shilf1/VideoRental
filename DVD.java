import java.util.Date;

public class DVD extends Video {

	public DVD(String title, int priceCode, Date registeredDate) {
		super(title, priceCode, registeredDate);
	}

	public int getLateReturnPointPenalty() {
		return 3;
	}
	
	public int getDaysRentedLimit() {
		return 2;		
	}
}
