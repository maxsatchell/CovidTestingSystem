import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 * This represents the booking created by the user 
 * @author Max Satchell
 */
public class Booking {
    /**
     * This is the student email inputed by the user which must end in uok.co.uk
     */
    private String studentEmail;
    /**
     * This is the bookable room code for the bookable room associated with the booking
     */
    private String bookableRoomCode;
    /**
     * This is the assistant on shifts email that is associated with the booking
     */
    private String assistantOnShiftEmail;
    /**
     * This is the enum for the status of the booking as there can only be two statuses
     */
    public enum StatusOfBooking {
        SCHEDULED, COMPLETED;
    }
    /**
     * This is the staus of the booking which can be SCHEDULED or COMPLETED
     */
    private StatusOfBooking status;
    /**
     * This is the timeslot of the booking in the format dd/MM/yyy hh:mm
     */
    private String timeSlot;
    /**
     * This constructs the booking
     */
    public Booking(String studentEmail, String bookableRoomCode, String assistantOnShiftEmail, StatusOfBooking status,
            String timeSlot) {

        this.studentEmail = studentEmail;
        this.bookableRoomCode = bookableRoomCode;
        this.assistantOnShiftEmail = assistantOnShiftEmail;
        this.status = status;
        this.timeSlot = timeSlot;
    }

    
    /** 
     * This method creates the available time slots that can be booked and then orders them in chronological order
     * @param bookableRooms - This is the list of all bookable rooms to be sorted 
     * @param assistantOnShifts - This is the list of all the assistants on shift to be sorted
     * @return ArrayList<String> - An array list of times is returned 
     */
    public static ArrayList<String> createAvailableTimeSlots(ArrayList<BookableRoom> bookableRooms,ArrayList<AssistantOnShift> assistantOnShifts) {

        AssistantOnShift[] assistantsOnShiftsArray = assistantOnShifts.toArray(new AssistantOnShift[assistantOnShifts.size()]);
        ArrayList<String> assistantsAvailableAtThisTime = new ArrayList<>();
        ArrayList<String> timeSlotsAvailable = new ArrayList<>();

        for (int i = 0; i < assistantsOnShiftsArray.length; i++) {// check for errors in 0 or not getting to the max                                                             
                if (assistantsOnShiftsArray[i].getStatusOfAssistant(assistantsOnShiftsArray[i]).toString().equals("FREE")) {
                assistantsAvailableAtThisTime.add(assistantsOnShiftsArray[i].getTimeSlotOfAssistant(assistantsOnShiftsArray[i]));
            }

        }
        BookableRoom[] roomAvailableArray = BookingSystem.getAvailableRooms(bookableRooms);
        for (int i = 0; i < roomAvailableArray.length; i++) {
            for (int c = 0; c < roomAvailableArray[i].roomCapacity - roomAvailableArray[i].getOccupancy(roomAvailableArray[i]); c++) {
                if (assistantsAvailableAtThisTime.contains(roomAvailableArray[i].getTimeSlotOfRoom(roomAvailableArray[i]))) {
                    timeSlotsAvailable.add(roomAvailableArray[i].getTimeSlotOfRoom(roomAvailableArray[i]));
                    assistantsAvailableAtThisTime.remove(roomAvailableArray[i].getTimeSlotOfRoom(roomAvailableArray[i]));
                }
            }

        }
        ArrayList<String> availableTimeSlots = timeSlotsAvailable;
        ArrayList<String> orderedTimes = new ArrayList();
        ArrayList<Date> timesList = new ArrayList();
        SimpleDateFormat formatForDateAndTime = new SimpleDateFormat("dd/MM/yyyy hh:mm");
       
        for (int i = 0; i < availableTimeSlots.size(); i++) {
            try {
                timesList.add(formatForDateAndTime.parse(availableTimeSlots.get(i)));
            } catch (ParseException e) {          
                e.printStackTrace();
            }
        }
        Date[] dates = timesList.toArray(new Date[timesList.size()]);
        Arrays.sort(dates);

        LinkedHashSet<Date> linkedHashSet = new LinkedHashSet<>(Arrays.asList(dates));//conversion to linked hash set to remove the duplicate times
        Date[] datesNonDuplicate = linkedHashSet.toArray(new Date[linkedHashSet.size()]);
        
        for(int i= 0;i<datesNonDuplicate.length;i++){
            for(int c =0;c<availableTimeSlots.size();c++){
                try {
                    Date checker = formatForDateAndTime.parse(availableTimeSlots.get(c));
                    if (datesNonDuplicate[i].equals(checker)) {
                        orderedTimes.add(availableTimeSlots.get(c));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return orderedTimes;
    }

    
    /** 
     * This method creates the bookings and adds the to the booking system
     * @param bookableRooms - The list of all the bookable rooms
     * @param assistantOnShifts - The list of all the assistants on shift
     * @param timeSlot - The time slot the user has selected for the booking
     * @param studentEmail - The student email the user has inputted
     * @param system - The system the booking is going to be added to
     * @return Booking - If the booking is created it is returned otherwise null is returned
     */
    public static Booking createBooking(ArrayList<BookableRoom> bookableRooms,ArrayList<AssistantOnShift> assistantOnShifts, String timeSlot, String studentEmail, BookingSystem system) {
        BookableRoom[] roomAvailableArray = BookingSystem.getAvailableRooms(bookableRooms);
        AssistantOnShift[] assistantOnShiftsAvailableArray = BookingSystem.getAvailableAssistantOnShifts(assistantOnShifts);
        ArrayList<AssistantOnShift> assistantsAvailableAtTimeSlot = new ArrayList<>();
        ArrayList<BookableRoom> roomsAvailableAtTimeSlot = new ArrayList<>();
        for (int i = 0; i < roomAvailableArray.length; i++) {
            if (roomAvailableArray[i].getTimeSlotOfRoom(roomAvailableArray[i]).equals(timeSlot)) {
                roomsAvailableAtTimeSlot.add(roomAvailableArray[i]);
            }

        }
        for (int i = 0; i < assistantOnShiftsAvailableArray.length; i++) { 
            if (assistantOnShiftsAvailableArray[i].getTimeSlotOfAssistant(assistantOnShiftsAvailableArray[i]).equals(timeSlot)) {
                assistantsAvailableAtTimeSlot.add(assistantOnShiftsAvailableArray[i]);
            }

        }
        BookableRoom room = roomsAvailableAtTimeSlot.get(0);
        room.increaseOccupancy(room);
        room.changeRoomStatus(room);

        AssistantOnShift assistant = assistantsAvailableAtTimeSlot.get(0);
        assistant.changeAssistantStatusToBusy(assistant);
        Booking booking = new Booking(studentEmail, room.roomCode, assistant.email, StatusOfBooking.SCHEDULED,timeSlot);
        if (system.addBooking(booking)) {
            return booking;
        }
        return null;

    }

    
    /** 
     * This method gets the status of the booking
     * @param booking - This is the booking which status is returned
     * @return StatusOfBooking - This will either return COMPLETED or SCHEDULED
     */
    public StatusOfBooking getStatusOfBooking(Booking booking) {
        return booking.status;
    }

    
    /** 
     * This method gets the timeslot of the booking
     * @param booking - This is the booking whose timeslot will be returned
     * @return String - The time slot will be returned in the format dd/MM/yyy hh:mm
     */
    public String getTimeSlotBooking(Booking booking) {
        return booking.timeSlot;
    }

    
    /** 
     * This method gets the assistant on shifts email of the booking
     * @param booking - This is the booking whose assistant on shift email will be returned
     * @return String - The email will be returned ending uok.co.uk
     */
    public String getAssistantOnShiftEmailFromBooking(Booking booking) {
        return booking.assistantOnShiftEmail;
    }

    
    /** 
     * This method gets the bookable room code for the booking
     * @param booking - This is the booking whose room code will be returned
     * @return String - This will be the room code
     */
    public String getBookableRoomCodeBooking(Booking booking) {
        return booking.bookableRoomCode;
    }

    
    /** 
     * This method gets the student email of the booking
     * @param booking - This is the booking whose student email will be returned
     * @return String - The email will be ending in uok.co.uk
     */
    public String getStudentEmailBooking(Booking booking) {
        return booking.studentEmail;
    }

    
    /**
     * This method prints out the message for a succesful adding of a booking 
     * @param booking - This is the booking that has just been added
     */
    public static void successAddingBooking(Booking booking) {
        System.out.println("Booking added succesfully:");
        System.out.println("| " + booking.getTimeSlotBooking(booking) + " | " + booking.getStatusOfBooking(booking) + " | " + booking.getAssistantOnShiftEmailFromBooking(booking) + " | "+ booking.getBookableRoomCodeBooking(booking) + " | " + booking.getStudentEmailBooking(booking) + " |");
        System.out.println("\n");
    }

    
    /** 
    * This method prints out the message for a succesful concluding of a booking 
    * @param booking - This is the booking that has just been concluded
    */
    public static void successConcludingBooking(Booking booking) {
        System.out.println("Booking completed succesfully:");
        System.out.println("| " + booking.getTimeSlotBooking(booking) + " | " + booking.getStatusOfBooking(booking) + " | " + booking.getAssistantOnShiftEmailFromBooking(booking) + " | " + booking.getBookableRoomCodeBooking(booking) + " | " + booking.getStudentEmailBooking(booking) + " |");
        System.out.println("\n");
    }

    
    /** 
     * This method overrides the equals method so that two bookings can be compared
     * @param obj - this is the object that will be compared with a booking
     * @return boolean - if the object equals the booking it will return true otherwise false
     */
    @Override
    public boolean equals(final Object obj) {

        if (!(obj instanceof Booking))
            return false;

        Booking otherBooking = (Booking) obj;

        if (otherBooking.status != this.status)
            return false;
        if (!otherBooking.timeSlot.equals(this.timeSlot))
            return false;
        if (!otherBooking.studentEmail.equals(this.studentEmail))
            return false;
        if (!otherBooking.bookableRoomCode.equals(this.bookableRoomCode))
            return false;
        if (!otherBooking.assistantOnShiftEmail.equals(this.assistantOnShiftEmail))
            return false;
        return true;

    }

    
    /** 
    * This method prints out the message for a succesful removing of a booking 
    * @param booking - This is the booking that has just been removed
    */
    public static void successRemovingBooking(Booking booking) {
        System.out.println("Booking removed succesfully:");
        System.out.println("| " + booking.getTimeSlotBooking(booking) + " | " + booking.getStatusOfBooking(booking) + " | " + booking.getAssistantOnShiftEmailFromBooking(booking) + " | "+ booking.getBookableRoomCodeBooking(booking) + " | " + booking.getStudentEmailBooking(booking) + " |");
        System.out.println("\n");
    }

    
    /** 
     * This method changes the status of the booking to completed
     * @param booking - This bookings status is changed to completed
     */
    public void changeStatusCompleted(Booking booking) {
        booking.status = StatusOfBooking.COMPLETED;
    }

    
    /** 
     * This method organises all the bookings in chronlogical order
     * @param bookings - This is all the bookings that need to be sorted
     * @return ArrayList<Booking> - returns all the bookings in chronological order as an arraylist
     */
    public static ArrayList<Booking> chronologicalOrderBookings(ArrayList<Booking> bookings) {
        ArrayList<Date> datesList = new ArrayList();
        ArrayList<Booking> orderedBookings = new ArrayList();
        SimpleDateFormat formatForDateAndTime = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        for (int i = 0; i < bookings.size(); i++) {
            try {
                datesList.add(formatForDateAndTime.parse(bookings.get(i).timeSlot));
            } catch (ParseException e) {          
                e.printStackTrace();
            }
        }
        Date[] dates = datesList.toArray(new Date[datesList.size()]);
        Arrays.sort(dates);

        LinkedHashSet<Date> linkedHashSet = new LinkedHashSet<>(Arrays.asList(dates));
        Date[] datesNonDuplicate = linkedHashSet.toArray(new Date[linkedHashSet.size()]);
        
        for(int i= 0;i<datesNonDuplicate.length;i++){
            for(int c =0;c<bookings.size();c++){
                try {
                    Date checker = formatForDateAndTime.parse(bookings.get(c).timeSlot);
                    if (datesNonDuplicate[i].equals(checker)) {
                        orderedBookings.add(bookings.get(c));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return orderedBookings;


       

    }

    


    
}
