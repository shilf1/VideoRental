import java.util.Scanner;

public class VRView {
	private Scanner scanner = new Scanner(System.in) ;
	

	public int showCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");
		
		/// command 변수 생성 불필요:  이렇게 한문장으로 return scanner.nextInt() ;
		return scanner.nextInt() ;
		
	}

	public void listVideos(VRController vr) {
		System.out.println("List of videos");
		
		for ( Video video: vr.videos ) {
			System.out.println("Price code: " + video.getPriceCode() +"\tTitle: " + video.getTitle()) ;
		}
		
		System.out.println("End of list");
	}

	public void listCustomers(VRController vr) {
		System.out.println("List of customers");
		for ( Customer customer: vr.customers ) {
			listOneCustomer(customer);
		}
		System.out.println("End of list");
	}

	public void listOneCustomer(Customer customer) {
		System.out.println("Name: " + customer.getName() +
				"\tRentals: " + customer.getRentals().size()) ;
		for ( Rental rental: customer.getRentals() ) {
			System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
			System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
		}
	}

	public int getPriceCode(VRController vr) {
		System.out.println("Enter price code( 1 for Regular, 2 for New Release ):") ;
		int priceCode = scanner.nextInt();
		return priceCode;
	}

	public int getVideoType(VRController vr) {
		System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):") ;
		int videoType = scanner.nextInt();
		return videoType;
	}

	/*
	public String getVideoTitleRegister() {
		System.out.println("Enter video title to register: ") ;
		String title = scanner.next() ;
		return title;
	}
	
	/// 중복 로직을 메소드로 분리
	public String getVideoTitleReturn() {
		System.out.println("Enter video title to return: ") ;

		return scanner.next() ;
	}
	*/
	
	/// 유사한 getVideoTitleRegister(), getVideoTitleReturn() 파라미터 화 하여 합침
	public String getVideoTitle(String type) {
		System.out.println("Enter video title to " + type + ": ") ;

		return scanner.next() ;
	}
	
	/// 중복 로직을 메소드로 분리
	public String getCustomerName() {
		System.out.println("Enter customer name: ") ;
		
		return scanner.next() ;
	}
	
}
