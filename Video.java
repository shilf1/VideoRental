import java.util.Date;

/// 추상 클래스로 변경
public abstract class Video {
	private String title ;

	private int priceCode ;
	public static final int REGULAR = 1 ;
	public static final int NEW_RELEASE =2 ;
	
	/// Subclassing 하여 불필요
	/*
	private int videoType ;
	
	public static final int VHS = 1 ;
	public static final int CD = 2 ;
	public static final int DVD = 3 ;
	*/
	
	private Date registeredDate ;
	private boolean rented ;
	
	///public Video(String title, int videoType, int priceCode, Date registeredDate) {
	public Video(String title, int priceCode, Date registeredDate) {
		this.setTitle(title) ;
		///this.setVideoType(videoType) ;
		this.setPriceCode(priceCode) ;
		this.registeredDate = registeredDate ;
	}

	/// 다형성을 위해 Retal.java 에 있는 case 문을 메소드화
	public int getDaysRentedLimit() {
		return 0;		
	}
	
	public int getLateReturnPointPenalty() {
		/// Sub class 에서 재정의
		/*
		int pentalty = 0 ;

		switch ( videoType ) {
			case VHS: pentalty = 1 ; break ;
			case CD: pentalty = 2 ; break ;
			case DVD: pentalty = 3 ; break ;
		}
		
		return pentalty ;
		*/
		return 0;		
	}
	
	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	/// subclassing 에 따라 불필요
	/*
	public int getVideoType() {
		return videoType;
	}

	public void setVideoType(int videoType) {
		this.videoType = videoType;
	}
	*/
}
