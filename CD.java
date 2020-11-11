import java.util.Date;

public class CD extends Video {

	public CD(String title, int priceCode, Date registeredDate) {
		super(title, priceCode, registeredDate);
	}

	public int getLateReturnPointPenalty() {
		return 2;
	}
	
	public int getDaysRentedLimit() {
		return 3;		
	}
}
