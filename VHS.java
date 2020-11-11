import java.util.Date;

public class VHS extends Video {
	
	public VHS(String title, int priceCode, Date registeredDate) {
		super(title, priceCode, registeredDate);
	}

	public int getLateReturnPointPenalty() {
		return 1;
	}
	
	public int getDaysRentedLimit() {
		return 5;		
	}
	
}
