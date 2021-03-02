import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class adds all bookable rooms assistants on shit and bookings to the system and manages them
 * @author Max Satchell
 */
public class BookingSystem {

    /**
     * A list of all the bookable rooms created
     */
    private ArrayList<BookableRoom> bookableRooms;
    /**
     * A list of all the assistants on shift created
     */
    private ArrayList<AssistantOnShift> assistantOnShifts;
    /**
     * A list of all the bookings created
     */
    private ArrayList<Booking> bookings;

    /**
     * This constructs the booking system, with bookake rooms, assistants on shift and bookings
     */
    public BookingSystem(ArrayList<BookableRoom> bookableRooms,ArrayList<AssistantOnShift> assistantOnShifts,ArrayList<Booking> bookings){
        this.bookableRooms = bookableRooms;
        this.assistantOnShifts = assistantOnShifts;
        this.bookings = bookings;
    }


    
    /** 
     * This method adds an assistant on shift to the system once it has been created
     * @param assistantOnShift - This is the assistant that is going to be added to the system
     * @return boolean - if the assistant on shift is added return true otherwise false is returned
     */
    public boolean addAssistantOnShift(AssistantOnShift assistantOnShift){ 

        if(assistantOnShift !=null){
            for(int i = 0;i<assistantOnShifts.size();i++){
                AssistantOnShift checker = assistantOnShifts.get(i);//This is so that you cannot add the same assistant on shift twice
                if(assistantOnShift.equals(checker)){
                    BookingApp.clearConsole();
                    System.out.println("Error!");
                    System.out.println("The assistant at date and time "+assistantOnShift.getTimeSlotOfAssistant(assistantOnShift)+ " has already been created please try a different date or assistant");
                    System.out.println("\n");
                    return false;
                }
            }
            assistantOnShifts.add(assistantOnShift);
            return true;
        }
        else if(assistantOnShifts == null)
        {BookingApp.clearConsole(); System.out.println("There are no available assistants at this time");return true;}
        else{assistantOnShifts.add(assistantOnShift);return true;}
    }

    
    /** 
     * This method adds bookable rooms to the systme once they have been created
     * @param bookableRoom - This is the bookable room that is to be added
     * @return boolean - Returns true if the bookable room is added otherwise returns false
     */
    public boolean addBookableRoom(BookableRoom bookableRoom){
        
        if(bookableRooms != null){

            for(int i = 0;i<bookableRooms.size();i++){
                BookableRoom checker = bookableRooms.get(i);//to make sure that the bookable rooms are not duplicated
                if(bookableRoom.equals(checker)){
                    BookingApp.clearConsole();
                    System.out.println("Error!");
                    System.out.println("This room has already been created please try a different time or room");
                    System.out.println("\n");
                    return false;
                }
            }
        
            bookableRooms.add(bookableRoom);
            return true;
        }
        else if(bookableRooms == null)
        {BookingApp.clearConsole(); System.out.println("There are no available rooms at this time");return true;}
        else{bookableRooms.add(bookableRoom);return true;}
    
    }

    
    /** 
     * This method adds the bookings to the system once they have been created
     * @param booking - This is the booking that is to be added
     * @return boolean - returns true if the booking is added else returns false
     */
    public boolean addBooking(Booking booking){
        if(bookings != null){

            for(int i = 0;i<bookings.size();i++){
                Booking checker = bookings.get(i);//to make sure that the bookings are not duplicated
                if(booking.equals(checker)){
                    BookingApp.clearConsole();
                    System.out.println("Error!");
                    System.out.println("This booking has already been created please try a different time or room");
                    System.out.println("\n");
                    return false;
                }
            }
        
            bookings.add(booking);
            return true;
        }
        else if(bookings == null)
        {BookingApp.clearConsole(); System.out.println("There are no available bookings at this time");return true;}
        else{bookings.add(booking);return true;}
    
    }


    
    /** 
     * This method removes bookable rooms from the system
     * @param bookableRoom - This is the bookable room that is being removed
     * @return boolean - returns true if the bookable room is removed otherwise returns false
     */
    public boolean removeBookableRoom(BookableRoom bookableRoom){
        for(int i =0;i<bookableRooms.size();i++){
            if(bookableRooms.get(i).equals(bookableRoom)){
                bookableRooms.remove(bookableRoom);
                return true;
            }
        }
        return false;
    }



    
    /** 
     * This method lists all the empty bookable rooms on the system
     * @return ArrayList<BookableRoom> - All the bookable rooms with status EMPTY list
     */
    public ArrayList<BookableRoom> listOfEmptyRooms(){
        ArrayList<BookableRoom> emptyRooms = new ArrayList();
        int count = 10; 
        for(int i = 0; i<bookableRooms.size();i++){
            String status = bookableRooms.get(i).getStatusOfRoom(bookableRooms.get(i)).toString();
            if((status.equals("EMPTY")))
            {
                count+= 1;
                System.out.println(String.valueOf(count)+". | "+bookableRooms.get(i).getTimeSlotOfRoom(bookableRooms.get(i))+" | "+bookableRooms.get(i).getStatusOfRoom(bookableRooms.get(i))+" | "+bookableRooms.get(i).roomCode+" | occupancy: "+bookableRooms.get(i).getOccupancy(bookableRooms.get(i))+" |");
                emptyRooms.add(bookableRooms.get(i));
            }
        }
        return emptyRooms;
    }

    
    /** 
     * This method removes an assistant on shift from the system
     * @param assistantOnShift - This is the assistant that is going to be removed
     * @return boolean - returns true if the assistant is removed otherwise returns false
     */
    public boolean removeAssistantOnShift(AssistantOnShift assistantOnShift){
        for(int i =0;i<assistantOnShifts.size();i++){
            if(assistantOnShifts.get(i).equals(assistantOnShift)){
                assistantOnShifts.remove(assistantOnShift);
                return true;
            }
        }
        return false;

    }
    
    /** 
     * This method gets the assistant on shift given the timeslot and the email
     * @param timeSlot - the timeslot for the assistant in the format of dd/MM/yyy hh:mm
     * @param email - the email of the assistant ending uok.co.uk
     * @return AssistantOnShift - return assistant on shift if there is one otherwise returns null
     */
    public AssistantOnShift getAssistantOnShiftFromTimeAndEmail(String timeSlot, String email){
        for(int i = 0;i<assistantOnShifts.size();i++){
            if(assistantOnShifts.get(i).getAssistantEmail(assistantOnShifts.get(i)).equals(email) && assistantOnShifts.get(i).getTimeSlotOfAssistant(assistantOnShifts.get(i)).equals(timeSlot)){
                return assistantOnShifts.get(i);
            }
        }
        return null;
    }

    
    /** 
     * This method gets the bookable room from the timeslot and code 
     * @param timeSlot - the timeslot for the bookable room in the format of dd/MM/yyy hh:mm
     * @param roomCode - this is the bookable room code
     * @return BookableRoom - returns the bookable room if there is one otherwise returns null
     */
    public BookableRoom getBookableRoomFromTimeAndCode(String timeSlot, String roomCode){
        for(int i = 0;i<bookableRooms.size();i++){
            if(bookableRooms.get(i).getRoomCode(bookableRooms.get(i)).equals(roomCode) && bookableRooms.get(i).getTimeSlotOfRoom(bookableRooms.get(i)).equals(timeSlot)){
                return bookableRooms.get(i);
            }
        }
        return null;
    }

    
    /** 
     * This method removes the booking and frees up the assistant on shift and the bookable room so they can be used again
     * @param booking - This is the booking that is going to be removed
     * @return boolean - returns true if the booking is removed otherwise returns false
     */
    public boolean removeBooking(Booking booking){
        bookings = Booking.chronologicalOrderBookings(bookings);
        for(int i =0;i<bookings.size();i++){
            if(bookings.get(i).equals(booking)){
                String timeSlot = bookings.get(i).getTimeSlotBooking(booking);
                String assistantOnShiftEmail = bookings.get(i).getAssistantOnShiftEmailFromBooking(booking);
                String bookableRoomCode = bookings.get(i).getBookableRoomCodeBooking(booking);
                AssistantOnShift assistantOnShift = getAssistantOnShiftFromTimeAndEmail(timeSlot, assistantOnShiftEmail);
                BookableRoom bookableRoom = getBookableRoomFromTimeAndCode(timeSlot, bookableRoomCode);
                bookableRoom.decreaseOccupancy(bookableRoom);
                bookableRoom.changeRoomStatus(bookableRoom);
                assistantOnShift.changeAssistantStatusToFree(assistantOnShift);
                bookings.remove(booking);
                return true;
            }
        }
        return false;
    }

    
    /** 
     * This method concludes the booking and changes the status to completed
     * @param booking - This is the booking which will be completed
     * @return Booking - returns booking if it is completed otherwise returns null
     */
    public Booking concludeBooking(Booking booking){
        for(int i =0;i<bookings.size();i++){
            if(bookings.get(i).equals(booking)){
                booking.changeStatusCompleted(booking);
                return booking;
            }
        }
        return null;
    }



    /**
     * This method prints out all the availabe time slots to the user
     */
    public void listOfAvailableTimeSlots(){
        ArrayList<String> availableTimeSlots = Booking.createAvailableTimeSlots(bookableRooms, assistantOnShifts);
        if(availableTimeSlots.size() == 0){
            System.out.println("There are no available bookings to be created at this moment try and add some bookable rooms and assistants then come back");
            System.out.println("\n");
        }
        for(int i = 0; i < availableTimeSlots.size();i++){
            String index = String.valueOf(i+11);
            System.out.println(index+". "+ availableTimeSlots.get(i));
        }
    }
    /**
     * This method prints out all the assistants on shift to the user
     */
    public void listOfAssistantOnShifts(){
        if(assistantOnShifts.size() == 0){
            System.out.println("There are no assistants on shift go the main menu and create them");
            System.out.println("\n");
        }
        for(int i = 0;i<assistantOnShifts.size();i++){
            System.out.println("| "+assistantOnShifts.get(i).getTimeSlotOfAssistant(assistantOnShifts.get(i))+" | "+assistantOnShifts.get(i).getStatusOfAssistant(assistantOnShifts.get(i))+" | "+assistantOnShifts.get(i).email+" |");
        }

    }
    /**
     * This method prints out all the bookable rooms to the user
     */
    public void listOfBookableRooms(){
        if(bookableRooms.size() == 0){
            System.out.println("There are no bookable rooms please create one in the menu!");
            System.out.println("\n");
        }
        for(int i = 0;i < bookableRooms.size();i++){
            System.out.println("| "+bookableRooms.get(i).getTimeSlotOfRoom(bookableRooms.get(i))+" | "+bookableRooms.get(i).getStatusOfRoom(bookableRooms.get(i))+" | "+bookableRooms.get(i).roomCode+" | occupancy: "+bookableRooms.get(i).getOccupancy(bookableRooms.get(i))+" |");
        }
    }

    
    /** 
     * This method gets all the bookable rooms with status EMPTY or AVAILABLE
     * @param bookableRooms - The list of all bookable rooms is passed in
     * @return BookableRoom[] - An array of rooms with status empty or AVAILABLE is returned
     */
    public static BookableRoom[] getAvailableRooms(ArrayList<BookableRoom> bookableRooms){
        BookableRoom[] bookableRoomsArray = bookableRooms.toArray(new BookableRoom[bookableRooms.size()]);
        ArrayList<BookableRoom> roomsAvailable = new ArrayList<>();
        String[] validRoomStatus = {"EMPTY", "AVAILABLE"};
        for(int i = 0; i< bookableRoomsArray.length; i++){//check for errors in 0 or not getting to the max size
            if(Arrays.asList(validRoomStatus).contains(bookableRoomsArray[i].getStatusOfRoom(bookableRoomsArray[i]).toString())){
                roomsAvailable.add(bookableRoomsArray[i]);
            }   
        }
        return roomsAvailable.toArray(new BookableRoom[roomsAvailable.size()]);
    }

    
    /** 
     * This method gets all the assistants on shift with status FREE
     * @param assistantOnShifts - The list of all assistants on shift
     * @return AssistantOnShift[] - An array of assistants on shift with status FREE
     */
    public static AssistantOnShift[] getAvailableAssistantOnShifts(ArrayList<AssistantOnShift> assistantOnShifts){
        AssistantOnShift[] assistantsOnShiftsArray = assistantOnShifts.toArray(new AssistantOnShift[assistantOnShifts.size()]);
        ArrayList<AssistantOnShift> assistantsAvailable = new ArrayList<>();

        for(int i = 0; i< assistantsOnShiftsArray.length; i++){//check for errors in 0 or not getting to the max size
            if(assistantsOnShiftsArray[i].getStatusOfAssistant(assistantsOnShiftsArray[i]).toString().equals("FREE")){
                assistantsAvailable.add(assistantsOnShiftsArray[i]);
            }
       
        }
        return assistantsAvailable.toArray(new AssistantOnShift[assistantsAvailable.size()]);


    }
    
    /** 
     * This method lists all the bookings depending on the type 
     * @param type - The type can be COMPLETED SCHEDULED or all of them
     */
    public void listBookings(String type){
        bookings = Booking.chronologicalOrderBookings(bookings);
        if(type.equals("SCHEDULED")){
            for(int i = 0; i< bookings.size();i++){
                if(bookings.get(i).getStatusOfBooking(bookings.get(i)).toString().equals("SCHEDULED")){
                    System.out.println("| "+bookings.get(i).getTimeSlotBooking(bookings.get(i))+" | "+bookings.get(i).getStatusOfBooking(bookings.get(i))+" | "+bookings.get(i).getAssistantOnShiftEmailFromBooking(bookings.get(i))+" | "+bookings.get(i).getBookableRoomCodeBooking(bookings.get(i))+" | "+bookings.get(i).getStudentEmailBooking(bookings.get(i))+" |");
                }
            }
        }
        else if(type.equals("COMPLETED")){
            for(int i = 0; i< bookings.size();i++){
                if(bookings.get(i).getStatusOfBooking(bookings.get(i)).toString().equals("COMPLETED")){
                    System.out.println("| "+bookings.get(i).getTimeSlotBooking(bookings.get(i))+" | "+bookings.get(i).getStatusOfBooking(bookings.get(i))+" | "+bookings.get(i).getAssistantOnShiftEmailFromBooking(bookings.get(i))+" | "+bookings.get(i).getBookableRoomCodeBooking(bookings.get(i))+" | "+bookings.get(i).getStudentEmailBooking(bookings.get(i))+" |");
                }
            }

        }
        else{
            if(bookings.size() == 0){
                System.out.println("There are no bookings at the moment");
            }
            for(int i = 0; i< bookings.size();i++){
                System.out.println("| "+bookings.get(i).getTimeSlotBooking(bookings.get(i))+" | "+bookings.get(i).getStatusOfBooking(bookings.get(i))+" | "+bookings.get(i).getAssistantOnShiftEmailFromBooking(bookings.get(i))+" | "+bookings.get(i).getBookableRoomCodeBooking(bookings.get(i))+" | "+bookings.get(i).getStudentEmailBooking(bookings.get(i))+" |");
            }
        }
    }

    
    /** 
     * This method gets all the bookable rooms
     * @return ArrayList<BookableRoom> returns the list of bookable rooms
     */
    public ArrayList<BookableRoom> getBookableRooms(){
        return bookableRooms;
    }
    
    /** 
     * This method gets the list of all assistants on shift
     * @return ArrayList<AssistantOnShift> returns the list of assistants on shift
     */
    public ArrayList<AssistantOnShift> getAssistantsOnShift(){
        return assistantOnShifts;
    }

    
    /** 
     * This method gets all the bookings that are SCHEDULED
     * @return ArrayList<Booking> returns a list of SCHEDULED bookings
     */
    public ArrayList<Booking> getScheduledBookings(){
        bookings = Booking.chronologicalOrderBookings(bookings);
        String type = "SCHEDULED";
        ArrayList<Booking> scheduledBookings = new ArrayList();
        if(type.equals("SCHEDULED")){
            int count = 10;
            for(int i = 0; i< bookings.size();i++){
                if(bookings.get(i).getStatusOfBooking(bookings.get(i)).toString().equals("SCHEDULED")){
                    count+=1;
                    scheduledBookings.add(bookings.get(i));
                    System.out.println(String.valueOf(count)+". | "+bookings.get(i).getTimeSlotBooking(bookings.get(i))+" | "+bookings.get(i).getStatusOfBooking(bookings.get(i))+" | "+bookings.get(i).getAssistantOnShiftEmailFromBooking(bookings.get(i))+" | "+bookings.get(i).getBookableRoomCodeBooking(bookings.get(i))+" | "+bookings.get(i).getStudentEmailBooking(bookings.get(i))+" |");
                }
            }
        }
        if(scheduledBookings.size() == 0){
            System.out.println("There are no scheduled bookings at this time please create one");
            return null;
        }
        else{
            return scheduledBookings;
        }
        
    }




    
}
