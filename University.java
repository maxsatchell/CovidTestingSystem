import java.util.ArrayList;
/**
 * This is the university and it contains the assistants and the rooms
 * @author Max Satchell
 */
public class University {
    /**
     * This is a list of all the assistants at the university
     */
    private ArrayList<Assistant> assistants;
    /**
     * This is a list of all the rooms at the university
     */
    private ArrayList<Room> rooms;
    /**
     * This is a list of all the assistants emails at the university
     */
    private ArrayList<String> emails;
    /**
     * This is a list of all the room codes at the university
     */
    private ArrayList<String> roomCodes;

    /**
     * This constructs the university with assistants, rooms , emails and room codes
     */
    public University(ArrayList<Assistant> assistants, ArrayList<Room> rooms, ArrayList<String> emails,ArrayList<String> roomCodes){

        this.assistants = assistants;
        this.rooms = rooms;
        this.emails = emails;
        this.roomCodes = roomCodes;

    }

    
    /** 
     * This method adds an assistant to the university
     * @param assistant - This is the assistant to be added
     */
    public void addAssistant(Assistant assistant){
        if(assistant.getAssistantName(assistant) == "" || assistant.getAssistantName(assistant) ==null){
            System.out.println("Error!");
            System.out.println("Name is empty cannot add assistant");
            System.out.println("\n");
        }
        else{
            String name = assistant.getAssistantName(assistant).replaceAll("\\s+", "").toLowerCase();
            String email = name + "@uok.ac.uk";
            int count = 1;
            if(emails != null){
                while(emails.contains(assistant.getAssistantEmail(assistant))){//This forces the email to be unique
                    email = name + String.valueOf(count) + "@uok.ac.uk";
                    count++;
        
                }
            }
            emails.add(email);
            assistant.setAssistantEmail(assistant, email);
            assistants.add(assistant);}    

        }
       
    
    /** 
     * This method adds rooms to the university
     * @param room - This is the room to be added
     */
    public void addRoom(Room room){
        if(room.getRoomCode(room)==""||room.getRoomCode(room)==null){
            System.out.println("Error!");
            System.out.println("Code is empty cannot add room");
            System.out.println("\n");
        }
        else{
            String roomCode = room.getRoomCode(room);
            int count = 1;
            if (roomCodes != null){
                while(roomCodes.contains(roomCode)){//This forces the room codes to be unique
                    roomCode = roomCode + String.valueOf(count);
                    count ++;
                }
            }
            roomCodes.add(roomCode);
            room.setRoomCode(roomCode, room);
            rooms.add(room);
        }
    }

    
    /** 
     * This method lists all the rooms at the university to the user
     * @param university - Pass in the university to access the rooms
     */
    public static void listRooms(University university){
        if(university.rooms == null){
            System.out.print("There are no available rooms at this time");
        }
        else{
            for(int i = 0; i < university.rooms.size();i++){
                Room room = university.rooms.get(i);
                String code = room.roomCode;
                String roomCapacty = String.valueOf(room.roomCapacity);
                String seqId = String.valueOf(i+11);
                System.out.println(seqId + ". | "+code+" | capacity: "+roomCapacty +" |");
            }
        }
    }

    
    /** 
     * This method lists all the assistants at the university
     * @param university - Pass in the university to access all the assistants
     */
    public static void listAssistants(University university){
        for(int i = 0; i < university.assistants.size();i++){
            Assistant assistant = university.assistants.get(i);
            String name = assistant.name;
            String email = assistant.email;
            String seqId = String.valueOf(i+11);
            System.out.println(seqId + ". | "+name+" | "+email +" |");
        }
        
    }

    
    /** 
     * This method gets the room from university when the user enters its id
     * @param university - pass in the university to access the rooms
     * @param index - pass in the index to know which room to access
     * @return Room - return the room specified or return null
     */
    public static Room getRoomFromIndex(University university,int index){
        if(index > university.rooms.size()-1||index < 0){
            return null;
        }
        else{
            return university.rooms.get(index);
        }
        
    }
    
    /** 
     * This method gets the assistant from university when the user gives its id
     * @param university - Pass in the university to access the assistants
     * @param index - pass in the index to know which room to access
     * @return Assistant - return the room specified or return null
     */
    public static Assistant getAssistantFromIndex(University university, int index){
        if(index > university.assistants.size()-1||index<0){
            return null;
        }
        else{
        return university.assistants.get(index);
        }
    }


    
    /** 
     * This method prints out an error message to the user
     * @param message - This is the message to be printed out 
     */
    public void errorMessage(String message){
        System.out.println("Error!");
        System.out.println("\n");
        System.out.println(message);
        System.out.println("\n");
    }




    
}
