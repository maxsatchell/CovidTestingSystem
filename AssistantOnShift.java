import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * This class inherits from Assistant and differs because it has a timeslot and status fields
 * @author Max Satchell
 */
public class AssistantOnShift extends Assistant {

    /**
     * This is the timeslot to which the Assistant is assigned when they are put on shift in the format dd/MM/yyyy hh:mm
     */
    private String timeSlot;


    /**
     * The status is an enum because the status cannot change a assistant on shift can be busy or free.
     */
    public enum StatusOfAssistant{
        FREE, BUSY;
    }

    /**
     * The status of the assistant is busy or free
     */
    private StatusOfAssistant status;

    /**
     * Constructs an assistant on shift using the superclass to get the other fields
     * @param name - this is got from the superclass and is the first and last name of the Assistant
     * @param email - this is got from the superclass and is the unique email of the assistant
     * @param timeSlot - this is the timeSlot of the assistant on shift
     * @param status - this the status of the assistant and it is free or Busy
     */
    public AssistantOnShift(String name, String email, String timeSlot,StatusOfAssistant status) {
        super(name, email);//The constructor gets these fields from the superclass assistant by using the super keyword
        this.timeSlot = timeSlot;
        this.status = status;
    }

    
    /** 
     * This Method creates the Assisant on shift and then adds them to the booking system
     * @param assistant - The assistant that will be turned into an assistant on shift
     * @param date - The date the assistant will work. The times will be added to this date at 07:00, 08:00 and 09:00
     * @param system - The booking system where assistants are assigned and added
     * @return ArrayList<AssistantOnShift> This returns the assistants that have just been created in an ArrayList
     */
    public static ArrayList<AssistantOnShift> createAssistantOnShift(Assistant assistant, String date,BookingSystem system){
        ArrayList<AssistantOnShift> createdAssistants = new ArrayList<>();
        AssistantOnShift earlyShift = new AssistantOnShift(assistant.name, assistant.email, date + " 07:00",StatusOfAssistant.FREE);//All assistants on shift are free until they are asigned to a booking
        AssistantOnShift middleShift = new AssistantOnShift(assistant.name, assistant.email, date + " 08:00",StatusOfAssistant.FREE);
        AssistantOnShift finalShift = new AssistantOnShift(assistant.name, assistant.email, date + " 09:00",StatusOfAssistant.FREE);  
        if(system.addAssistantOnShift(earlyShift)){createdAssistants.add(earlyShift);}
        if(system.addAssistantOnShift(middleShift)){createdAssistants.add(middleShift);}
        if(system.addAssistantOnShift(finalShift)){createdAssistants.add(finalShift);}
        return createdAssistants;

    }
    
    /** 
     * Gets the status of the assistant on shift
     * @param assistantOnShift - this is the assistant on shifts status that you want to get
     * @return StatusOfAssistant - this is an enum that will return either FREE or BUSY
     */
    public StatusOfAssistant getStatusOfAssistant(AssistantOnShift assistantOnShift){
        return assistantOnShift.status;
    }

    
    /** 
     * Gets the timeslot alocated to the assistant on shift
     * @param assistantOnShift - pass in the assistant on shift whose timeslot you want
     * @return returns the string time slot in the format dd/MM/yyyy hh:mm
     */
    public String getTimeSlotOfAssistant(AssistantOnShift assistantOnShift){
        return assistantOnShift.timeSlot;
    }

    
    /** 
     * Changes the status of the assistant on shift to busy
     * @param assistantOnShift - This is the assistant on shift whose status will change to Busy
     */
    public void changeAssistantStatusToBusy(AssistantOnShift assistantOnShift){
        assistantOnShift.status = StatusOfAssistant.BUSY;
    }

    
    /** 
     * Changes the status of the assistant on shift to Free
     * @param assistantOnShift - This is the assistant on shift whose status will change to Free
     */
    public void changeAssistantStatusToFree(AssistantOnShift assistantOnShift){
        assistantOnShift.status = StatusOfAssistant.FREE;
    }

    
    /** 
     * This Method checks that the date inputed is in the foramat dd/MM/yyyy if it is not the error is caught and an error message is printed
     * @param date - This is the date the user inputs for the assistant on shift
     * @return This method returns null if all is good if not it returns an error message
     */
    public static String dateChecker(String date){
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd/MM/yyyy");
        formatForDate.setLenient(false);
        try {
            formatForDate.parse(date);
            return null;
            
        } catch (ParseException e) {
            return "The date you have entered is in the wrong format please try again in the format dd/mm/yyyy";
        }

    }
    
    /** 
     * This method prints out the success message after a Assistant on shift has been added
     * @param assistantOnShifts - The new assistant on shifts that have been added
     */
    public static void successAddingAssistantOnShift(ArrayList<AssistantOnShift> assistantOnShifts){
        System.out.println("Assistant on Shift added succesfully:");
        for(int i =0;i<assistantOnShifts.size();i++){
            System.out.println("| "+assistantOnShifts.get(i).getTimeSlotOfAssistant(assistantOnShifts.get(i))+" | "+assistantOnShifts.get(i).getStatusOfAssistant(assistantOnShifts.get(i))+" | "+assistantOnShifts.get(i).email+" |");
        }
        System.out.println("\n");
	}

    
    /** 
     * This method prints out the succes message after a assistant on shift has been removed
     * @param assistantOnShift - The assistant on shift that has just been removed
     */
    public static void successRemovingAssistantOnShift(AssistantOnShift assistantOnShift){
        System.out.println("Bookable Room removed succesfully:");
        System.out.println("| "+assistantOnShift.getTimeSlotOfAssistant(assistantOnShift)+" | "+assistantOnShift.getStatusOfAssistant(assistantOnShift)+" | "+assistantOnShift.email+" |");
        System.out.println("\n");
	}




    
    /** 
     * This Method overides the equals function so that you can compare two assistant on shifts
     * @param obj - This is the object that you compare with you assistant on shift to see if they are the same
     * @return boolean this is true if they are the same otherwise it returns false
     */
    @Override
    public boolean equals(final Object obj){

        if ( !(obj instanceof AssistantOnShift) ) 
            return false;

        AssistantOnShift otherAssistantOnShift = (AssistantOnShift) obj;

        if (otherAssistantOnShift.status != this.status) return false;
        if (!otherAssistantOnShift.timeSlot.equals(this.timeSlot)) return false;
        if (!otherAssistantOnShift.name.equals(this.name)) return false;
        if (!otherAssistantOnShift.email.equals(this.email)) return false;
        return true;

    }



    
}
