import java.util.Date;

public class Rental {
	public static final int MILLISECOND_PER_DAY = 1000 * 60 * 60 * 24;

	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
		rentDate = new Date() ;
	}
	
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == 1 ) {
			this.status = 1;
			returnDate = new Date() ;
		}
	}
	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}
	
	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}



	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented ;

		daysRented = getDaysRented();


		if ( daysRented <= 2) return limit ;
		
		/*
		switch ( video.getVideoType() ) {
			case Video.VHS: limit = 5 ; break ;
			case Video.CD: limit = 3 ; break ;
			case Video.DVD: limit = 2 ; break ;
		}
		return limit ;	
		*/
		
		return 	video.getDaysRentedLimit();
	}

	public int getDaysRented() {

		int daysRented;
		long diff = 0;
		if (getStatus() == 1) {
			diff = returnDate.getTime();
		} else { // not yet returned
			diff = new Date().getTime();
		}
		diff -= rentDate.getTime();
		daysRented = (int) (diff / MILLISECOND_PER_DAY) + 1;
		return daysRented;
	}
}
