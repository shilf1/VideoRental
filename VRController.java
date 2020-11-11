import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/// VRUI 는 클래스의 용도를 알기 어려운 모호한 이름임
/// WMC 높지만 case 문의 영향이 큼
public class VRController {
	
	List<Customer> customers = new ArrayList<Customer>() ;

	List<Video> videos = new ArrayList<Video>() ;
	
	static VRView ui = new VRView();
	
	
	/// 함수 복잡도가 높지만 case 문이므로 괜찮음
	public static void main(String[] args) {
		VRController vr = new VRController() ;
		
		boolean quit = false ;
		while ( ! quit ) {
			/// command 변수 선언할 필요 없이 switch 문 괄호 안에 그냥 넣어도 됨
			switch ( ui.showCommand() ) {
				case 0: quit = true ; break ;
				case 1: ui.listCustomers(vr) ; break ;
				case 2: ui.listVideos(vr) ; break ;
				case 3: vr.register("customer") ; break ;
				case 4: vr.register("video") ; break ;
				case 5: vr.rentVideo() ; break ;
				case 6: vr.returnVideo() ; break ;
				case 7: vr.getCustomerReport() ; break; 
				case 8: vr.clearRentals() ; break ;
				case -1: vr.init() ; break ;
				default: break ;
			}
		}
		System.out.println("Bye");
	}

	public void clearRentals() {
		/// 동일한 메시지 println 중복 구문  (총 5회)
		String customerName = ui.getCustomerName();
		
		Customer foundCustomer = null ;
		for ( Customer customer: customers ) {
			if ( customer.getName().equals(customerName)) {
				foundCustomer = customer ;
				break ;
			}
		}

		if ( foundCustomer == null ) {
			noCustomerFound();
		} else {
			/// 중복 코드 메소드 분리
			ui.listOneCustomer(foundCustomer);

			List<Rental> rentals = new ArrayList<Rental>() ;
			foundCustomer.setRentals(rentals);
		}
	}

	public void noCustomerFound() {
		System.out.println("No customer found") ;
	}


	public void returnVideo() {
		String customerName = ui.getCustomerName();
		
		Customer foundCustomer = null ;
		for ( Customer customer: customers ) {
			if ( customer.getName().equals(customerName)) {
				foundCustomer = customer ;
				break ;
			}
		}
		if ( foundCustomer == null ) return ;
		
		String videoTitle = ui.getVideoTitle("Return");
			
		List<Rental> customerRentals = foundCustomer.getRentals() ;
		for ( Rental rental: customerRentals ) {
			if ( rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented() ) {
				rental.returnVideo();
				rental.getVideo().setRented(false);
				break ;
			}
		}		
	}

	private void init() {
		Customer james = new Customer("James") ;
		Customer brown = new Customer("Brown") ;
		customers.add(james) ;
		customers.add(brown) ;
		
		/// Video 의 서브클래스로 대체
		Video v1 = new CD("v1", Video.REGULAR, new Date()) ;
		Video v2 = new DVD("v2", Video.NEW_RELEASE, new Date()) ;
		videos.add(v1) ;
		videos.add(v2) ;
		
		/// r1, r2 대신 의미 있는 변수 이름 사용
		Rental r1 = new Rental(v1) ;
		Rental r2 = new Rental(v2) ;
		
		james.addRental(r1) ;
		james.addRental(r2) ;
	}

	/// get으로 시작하는 메소드 이름이지만 리턴하는 값이 없음
	public void getCustomerReport() {
		String customerName = ui.getCustomerName();
		
		Customer foundCustomer = null ;
		for ( Customer customer: customers ) {
			if ( customer.getName().equals(customerName)) {
				foundCustomer = customer ;
				break ;
			}
		}

		if ( foundCustomer == null ) {
			noCustomerFound();
		} else {
			String result = foundCustomer.getReport() ;
			ui.printCustomerReport(result);
		}
	}

	public void rentVideo() {
		String customerName = ui.getCustomerName();
		
		Customer foundCustomer = null ;
		for ( Customer customer: customers ) {
			if ( customer.getName().equals(customerName)) {
				foundCustomer = customer ;
				break ;
			}
		}

		if ( foundCustomer == null ) return ;
		
		/// 중복 로직을 메소드로 분리
		String videoTitle = ui.getVideoTitle("Return");

		Video foundVideo = null ;
		for ( Video video: videos ) {
			if ( video.getTitle().equals(videoTitle) && video.isRented() == false ) {
				foundVideo = video ;
				break ;
			}
		}

		if ( foundVideo == null ) return ;
		
		Rental rental = new Rental(foundVideo) ;
		foundVideo.setRented(true);
		
		List<Rental> customerRentals = foundCustomer.getRentals() ;
		customerRentals.add(rental);
		foundCustomer.setRentals(customerRentals);		
	}

	public void register(String object) {
		/// "customer".equals(object) 로 순서를 바꿔서 object가 null 일때는 대비
		if ( object.equals("customer") ) {
			/// 중복 구문 메소드로 분리
			String name = ui.getCustomerName();		
			Customer customer = new Customer(name) ;
			customers.add(customer) ;
		}
		else {
			String title = ui.getVideoTitle("Register");
			
			int videoType = ui.getVideoType(this);
			
			int priceCode = ui.getPriceCode(this);
		
			Date registeredDate = new Date();
			
			/// 비디오 타입별 클라스 생성하기 위한 팩토리 메소드 추가
			Video video = factoryVideo(title, videoType, priceCode, registeredDate);
			videos.add(video) ;
		}
	}

	/// 서브클래싱 적용에 따라 팩토리 메소드 필요
	private Video factoryVideo(String title, int videoType, int priceCode, Date registeredDate) {
		switch(videoType) {
			case 1: return new VHS(title, priceCode, registeredDate) ;
			case 2: return new CD(title, priceCode, registeredDate) ;
			case 3: return new DVD(title, priceCode, registeredDate) ;
			default: return null;
		}

	}

}
