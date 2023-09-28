package model.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
	
	private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Integer roomNumber;
	LocalDate checkin, checkout;
	
	public Reservation() {
		
	}

	public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) {
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public LocalDate getChekout() {
		return checkout;
	}
	
	public long duration() {
		return Duration.between(checkin.atTime(0,0), checkout.atTime(0, 0)).toDays();
	}
	
	public String updateDates(LocalDate checkin, LocalDate checkout) {
		if (checkout.isBefore(LocalDate.now())||checkin.isBefore(LocalDate.now())) {
			return "Error in reservation: Reservation dates for update must be future dates";
		}
		if (!checkout.isAfter(checkin)) {
			return "Error in reservation: Check-out date must be after check-in date";
		}
		this.checkin = checkin;
		this.checkout = checkout;
		return null;
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Reservation: " + roomNumber);
		sb.append(", check-in: " + fmt.format(checkin));
		sb.append(", check-out: " + fmt.format(checkout) + ", ");
		sb.append(duration() + " nights");
		
		return sb.toString();
	}
	
}
