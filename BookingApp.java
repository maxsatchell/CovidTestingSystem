import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the class where the initial data is loaded and the main menu class is called
 * @author Max Satchell
 */
public class BookingApp {
    
    /** 
     * This is the main method where the intital data is called and the menu method is called
     */
    public static void main(String[] args) {
        // initial data
        University university = BookingApp.loadUniversityInitialData();
        BookingSystem system = BookingApp.loadSystemInitialData();

        MainMenu.menu(university, system);
    }

    /**
     * This method clears the console
     */
    public static void clearConsole() {
        try
        {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            ex.getStackTrace();
        }
         
    }

    
    /** 
     * This method loads the initial data for the university
     * @return University - The university with the data in it
     */
    public static University loadUniversityInitialData(){
       
        ArrayList<Assistant> assistants = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> roomCodes = new ArrayList<>();
        University university = new University(assistants, rooms, emails, roomCodes);
        Room room1 = new Room("IC726", 3);
        university.addRoom(room1);
        Room room2 = new Room("IC700", 2);
        university.addRoom(room2);
        Room room3 = new Room("MS750", 1);
        university.addRoom(room3);


        Assistant assistant1 = new Assistant("Max Satchell", "maxsatchell@uok.co.uk");
        university.addAssistant(assistant1);
        Assistant assistant2 = new Assistant("Bob Harper", "bobharper@uok.co.uk");
        university.addAssistant(assistant2);
        Assistant assistant3 = new Assistant("Brian Welter", "brianwelter@uok.co.uk");
        university.addAssistant(assistant3);

        return university;
    }

    
    /** 
     * This method loads the initial data into the booking system
     * @return BookingSystem - the system with the data in it
     */
    public static BookingSystem loadSystemInitialData(){
        ArrayList<BookableRoom> bookableRooms = new ArrayList<>();

        bookableRooms.add(new BookableRoom("IC726", 3, BookableRoom.StatusOfRoom.EMPTY, "23/04/2021 07:00", 0));
        bookableRooms.add(new BookableRoom("IC726", 3, BookableRoom.StatusOfRoom.EMPTY, "23/04/2021 08:00", 0));
        bookableRooms.add(new BookableRoom("IC726", 3, BookableRoom.StatusOfRoom.EMPTY, "23/04/2021 09:00", 0));

        bookableRooms.add(new BookableRoom("MS750", 1, BookableRoom.StatusOfRoom.FULL, "25/04/2021 07:00", 1));//assistant assigned
        bookableRooms.add(new BookableRoom("MS750", 1, BookableRoom.StatusOfRoom.FULL, "25/04/2021 08:00", 1));
        bookableRooms.add(new BookableRoom("MS750", 1, BookableRoom.StatusOfRoom.FULL, "25/04/2021 09:00", 1));

        bookableRooms.add(new BookableRoom("IC700", 2, BookableRoom.StatusOfRoom.AVAILABLE, "26/04/2021 07:00", 1));
        bookableRooms.add(new BookableRoom("IC700", 2, BookableRoom.StatusOfRoom.AVAILABLE, "26/04/2021 08:00", 1));
        bookableRooms.add(new BookableRoom("IC700", 2, BookableRoom.StatusOfRoom.AVAILABLE, "26/04/2021 07:00", 1));

        ArrayList<AssistantOnShift> assistantOnShifts = new ArrayList<>();

        assistantOnShifts.add(new AssistantOnShift("Max Satchell", "maxsatchell@uok.co.uk", "25/04/2021 07:00", AssistantOnShift.StatusOfAssistant.BUSY));
        assistantOnShifts.add(new AssistantOnShift("Max Satchell", "maxsatchell@uok.co.uk", "25/04/2021 08:00", AssistantOnShift.StatusOfAssistant.BUSY));
        assistantOnShifts.add(new AssistantOnShift("Max Satchell", "maxsatchell@uok.co.uk", "25/04/2021 09:00", AssistantOnShift.StatusOfAssistant.BUSY));

        assistantOnShifts.add(new AssistantOnShift("Brian Welter", "brianwelter@uok.co.uk", "26/04/2021 07:00", AssistantOnShift.StatusOfAssistant.BUSY));
        assistantOnShifts.add(new AssistantOnShift("Brian Welter", "brianwelter@uok.co.uk", "26/04/2021 08:00", AssistantOnShift.StatusOfAssistant.BUSY));
        assistantOnShifts.add(new AssistantOnShift("Brian Welter", "brianwelter@uok.co.uk", "26/04/2021 09:00", AssistantOnShift.StatusOfAssistant.BUSY));

        assistantOnShifts.add(new AssistantOnShift("Bob Harper", "bobharper@uok.co.uk", "23/04/2021 08:00", AssistantOnShift.StatusOfAssistant.FREE));

        ArrayList<Booking> bookings = new ArrayList<>();

        bookings.add(new Booking("harryhalf@uok.co.uk", "MS750", "maxsatchell@uok.co.uk", Booking.StatusOfBooking.COMPLETED, "25/04/2021 07:00"));
        bookings.add(new Booking("hugofull@uok.co.uk", "MS750", "maxsatchell@uok.co.uk", Booking.StatusOfBooking.SCHEDULED, "25/04/2021 08:00"));

        return new BookingSystem(bookableRooms, assistantOnShifts, bookings);

    }


}
