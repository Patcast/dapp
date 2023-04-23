package staff;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Set;

import hotel.Booking;
import hotel.BookingDetail;

public class BookingClient extends AbstractScriptedSimpleTest {

	private Booking bm = null;

	public static void main(String[] args) throws Exception {
		BookingClient client = new BookingClient();
		client.run();
	}

	public BookingClient() {
		try {
			//Look up the registered remote instance
			bm = (Booking) Naming.lookup("//localhost/BookingServer");

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws RemoteException {

		return  bm.isRoomAvailable(roomNumber,date);
	}

	@Override
	public void addBooking(BookingDetail bookingDetail) throws Exception {
		bm.addBooking(bookingDetail);
	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException {

		return bm.getAvailableRooms(date);
	}

	@Override
	public Set<Integer> getAllRooms() throws RemoteException {
		return bm.getAllRooms();
	}
}
