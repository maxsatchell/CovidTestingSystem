import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
/**
 * This class inherits from room and differs with the new fields status timeSlot and occupancy
 */
public class BookableRoom extends Room{

    /**
     * The status is an enum because a bookable room has only three statuses and they cannot be changed
     */
    public enum StatusOfRoom{
        EMPTY, AVAILABLE, FULL;
    }
    /**
     * The status can be EMPTY, AVAILABLE OR FULL
     */
    private StatusOfRoom status;
    /**
     * The timeslot is a String in the format dd/MM/yyyy hh:mm
     */
    private String timeSlot;
    /**
     * The occupancy is the ammount of tests the room is holding at one point and affects the status of the room
     */
    private int occupancy;

    /**
     * Constructs a new Bookable room getting the fields roomCode and roomCapacity from the superclass room
     * Add parameters ?
     */
    public BookableRoom(String roomCode, int roomCapacity, StatusOfRoom status,String timeSlot,int occupancy) {
        super(roomCode, roomCapacity);//The constructor gets these fields from the superclass Room by using the super keyword
        this.status = status;
        this.timeSlot = timeSlot;
        this.occupancy = occupancy;
    }

    
    /** 
     * This method creates the bookable room and then the system adds it
     * @param room - The room which is going to be created in a bookable room
     * @param system -  The booking system which the bookable room will be added to
     * @param timeSlot - The user inputs the time slot for the bookable room they create
     * @return BookableRoom. If the bookable room is created it is return otherwise null is returned
     */
    public static BookableRoom createBookableRoom(Room room, BookingSystem system, String timeSlot)  {
        BookableRoom bookableRoom = new BookableRoom(room.roomCode, room.roomCapacity, StatusOfRoom.EMPTY, timeSlot,0);//When a room is created the status is empty and the occupancy is 0
        if(system.addBookableRoom(bookableRoom)){return bookableRoom;}
        return null;

    }

    
    /** 
     * This method gets the status for the bookable room
     * @param bookableRoom - This bookable rooms status is returned
     * @return StatusOfRoom - The enum status of room is returned either EMPTY,FULL or AVAILABLE
     */
    public StatusOfRoom getStatusOfRoom(BookableRoom bookableRoom){
        return bookableRoom.status;
    }

    
    /** 
     * This method gets the timeslot for a bookable room
     * @param bookableRoom - This bookable rooms timeslot is returned
     * @return String - THe time slot is returned in the format dd/MM/yyy hh:mm
     */
    public String getTimeSlotOfRoom(BookableRoom bookableRoom){
        return bookableRoom.timeSlot;
    }

	
    /** 
     * This method gets the rooms occupancy
     * @param bookableRoom - This bookable rooms occupancy is returned
     * @return int - The number of bookings that are taking place in that bookable room is returned(occupancy)
     */
    public int getOccupancy(BookableRoom bookableRoom) {
		return bookableRoom.occupancy;
	}

    
    /** 
     * This method increases the occupancy of a room by 1
     * @param bookableRoom - The occupany of this bookable room is increased by 1
     */
    public void increaseOccupancy(BookableRoom bookableRoom){
        bookableRoom.occupancy +=1;
    }

    
    /** 
     * This method decreases the occupancy of the bookable room by 1
     * @param bookableRoom - The occupancy of this bookable room is decreased by 1
     */
    public void decreaseOccupancy(BookableRoom bookableRoom){
        bookableRoom.occupancy -=1;
    }

    
    /** 
     * This method changes the status of the room depending on the occupancy
     * @param bookableRoom - The status of this room is changed to EMPTY,FULL or AVAILABLE
     */
    public void changeRoomStatus(BookableRoom bookableRoom){
        if(bookableRoom.occupancy == 0){
            bookableRoom.status = StatusOfRoom.EMPTY;
        }
        else if(bookableRoom.occupancy < bookableRoom.roomCapacity && bookableRoom.occupancy > 0){
            bookableRoom.status = StatusOfRoom.AVAILABLE;
        }
        else if(bookableRoom.occupancy == bookableRoom.roomCapacity){
            bookableRoom.status = StatusOfRoom.FULL;
        }
    }

    
    /** 
     * This method checks that the time slot entered by the user is in the correct foramt
     * @param timeSlot - The time slot that is being checked
     * @return String. The method returns null if the date is valid otherwise it returns the error message
     */
    public static String dateFormatChecker(String timeSlot){
        SimpleDateFormat formatForDateAndTime = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String[] validTimes = {"07:00","08:00","09:00"};
        formatForDateAndTime.setLenient(false);
        try {
            formatForDateAndTime.parse(timeSlot);
            Date roomSlot = formatForDateAndTime.parse(timeSlot);
            DateFormat time = new SimpleDateFormat("hh:mm");
            if(Arrays.asList(validTimes).contains(time.format(roomSlot)))
            {
                return null;
            }
            else{ return "The time you have entered is not valid it needs to be 07:00, 08:00 or 09:00";
        }
            
        } catch (ParseException e) {
            return "The date and time you have entered is in the wrong format please try again in the format dd/mm/yyyy HH:MM";
        }
        
    }

	
    /** 
     * This method prints out the sucessful booking message when a bookable room is added
     * @param bookableRoom - This is the bookable room that has just been added
     */
    public static void successAddingBookableRoom(BookableRoom bookableRoom){
        System.out.println("Bookable Room added succesfully:");
        System.out.println("| "+bookableRoom.getTimeSlotOfRoom(bookableRoom)+" | "+bookableRoom.getStatusOfRoom(bookableRoom)+" | "+bookableRoom.roomCode+" | occupancy: "+bookableRoom.getOccupancy(bookableRoom)+" |");
        System.out.println("\n");
	}

    
    /** 
     * This method prints out the succesful removing message for a bookable room that has just been removed
     * @param bookableRoom - This is the bookable room that has just been removed
     */
    public static void successRemovingBookableRoom(BookableRoom bookableRoom){
        System.out.println("Bookable Room removed succesfully:");
        System.out.println("| "+bookableRoom.getTimeSlotOfRoom(bookableRoom)+" | "+bookableRoom.getStatusOfRoom(bookableRoom)+" | "+bookableRoom.roomCode+" | occupancy: "+bookableRoom.getOccupancy(bookableRoom)+" |");
        System.out.println("\n");
	}

    
    /** 
     * This method overides the equals method so that two bookable rooms can be compared
     * @param obj - This is the object that you will compare to a bookable room
     * @return boolean - If the object equals the bookable room true is returned otherwise false is returned
     */
    @Override
    public boolean equals(final Object obj){

        if ( !(obj instanceof BookableRoom) ) 
            return false;

        BookableRoom otherBookableRoom = (BookableRoom) obj;

        if (otherBookableRoom.status != this.status) return false;
        if (!otherBookableRoom.timeSlot.equals(this.timeSlot)) return false;
        if (otherBookableRoom.occupancy != this.occupancy) return false;
        if (!otherBookableRoom.roomCode.equals(this.roomCode)) return false;
        if (otherBookableRoom.roomCapacity != this.roomCapacity) return false;

        return true;

    }



    
    
}
