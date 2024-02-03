package application;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {
   
	public static void main(String[] args) throws ParseException {
			
		Scanner scan = new Scanner(System.in);
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("(Enter rental data)");
		System.out.print("Car model: ");
		String carModel = scan.nextLine();
		System.out.print("Start of rental (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(scan.nextLine(), formatter);
		System.out.print("End of rental (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(scan.nextLine(), formatter);			
				
		CarRental carRental = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Enter the price per hour: ");
		Double pricePerHour = scan.nextDouble();
		System.out.print("Enter the price per day: ");
		Double pricePerDay = scan.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(carRental);
		
		System.out.println("\n(INVOICE)");
		System.out.println("Basic payment: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", carRental.getInvoice().getTax()));
		System.out.println("Total payment: " + String.format("%.2f", carRental.getInvoice().getTotalPayment()));
		
		scan.close();
	}
}
