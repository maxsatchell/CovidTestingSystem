/**
 * This class is the superclass of bookable room and represents a room in the university
 * @author Max Satchell
 */
public class Room {
    /**
     * This is the room code of the room
     */
    protected String roomCode;
    /**
     * This is the amount of bookings a room can hold in one session
     */
    protected int roomCapacity;

    /**
     * This constructs the room with the roomcode and the room capacity
     */
    public Room(String roomCode, int roomCapacity){

        this.roomCode = roomCode;
        this.roomCapacity = roomCapacity;

    }

    
    /** 
     * This method sets the room code of the rooms
     * @param roomCode - This is the code that will be assigned to the room
     * @param room - This is the room the code will be assigned to
     */
    public void setRoomCode(String roomCode,Room room){
        room.roomCode = roomCode;
    }

    
    /** 
     * This method gets the room code
     * @param room - This is the room that you want the code for
     * @return String returns the code
     */
    public String getRoomCode(Room room){
        return room.roomCode;
    }


    
}
