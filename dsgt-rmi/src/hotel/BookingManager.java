package hotel;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class BookingManager extends UnicastRemoteObject implements Booking{

	private final List<Room> rooms = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BookingManager manager = new BookingManager();
//		System.out.println("\nRooms initialized \n");
//		manager.getAllRooms().forEach(n->System.out.println("RoomID: " + n));
//		manager.addBooking(new BookingDetail("Patricio",101, LocalDate.of(2017, 1, 13)));
//		System.out.println("\nRooms available for 13/1/2017 \n");
//		manager.getAvailableRooms( LocalDate.of(2017, 1, 13)).forEach(n->System.out.println("RoomID: " + n));
		Booking booking = new BookingManager();
		Naming.rebind("//localhost/BookingServer", booking);
	}

	public BookingManager() throws RemoteException {
		super();
		initializeRooms();
	}

	public Set<Integer> getAllRooms() throws RemoteException  {
		return rooms.stream().map(Room::getRoomNumber).collect(Collectors.toSet());
	}

	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws RemoteException {
		Optional<Room> room = rooms.
				stream().
				filter(r-> Objects.equals(r.getRoomNumber(), roomNumber)).
				filter(r-> r.getBookings().stream().noneMatch(b->(b.getDate().equals(date)))).
				findFirst();
		return room.isPresent();
	}

	public void addBooking(BookingDetail bookingDetail)throws RemoteException  {
		if(isRoomAvailable(bookingDetail.getRoomNumber(),bookingDetail.getDate())){
			for ( Room r : rooms) {
				if(Objects.equals(r.getRoomNumber(), bookingDetail.getRoomNumber())) {
					r.addSingleBooking(bookingDetail);
					System.out.println("hotel.Booking completed\n");
				}
			}
		}
	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException {
		return rooms.
				stream().
				filter(r-> r.getBookings().stream().noneMatch(b->(b.getDate().equals(date)))).
				map(Room::getRoomNumber).collect(Collectors.toSet());
	}

	private  void initializeRooms() {
		rooms.add( new Room(101));
		rooms.add( new Room(102));
		rooms.add( new Room(201));
		rooms.add( new Room(203));
	}

}
