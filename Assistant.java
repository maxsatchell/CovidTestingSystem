/**
 * Represents an Assistant that is helping the university with its testing programme
 * @author Max Satchell
 */
public class Assistant {

    /**
     * The first and last name of this student it is protected so that Assistant on shift the subclass can access it.
     */
    protected String name;

    /**
     * The unique email that the student holds it is protected so that Assistant on shift the subclass can access it.
     */
    protected String email;


    /**
     * Constructs an Assistant with a name and email
     * @param name - this is the name of an assistant
     * @param email - this is the email of the assistant
     */
    public Assistant(String name, String email){
        this.name = name;//this cannot be null
        this.email = email;
    }

    
    /** 
     * Sets the assistants email when passed a specific email and assistant
     * @param assistant - this is the assistant the email will be assigned to.
     * @param email - this is the email the assistant will be assigned it is unique.
     */
    public void setAssistantEmail(Assistant assistant, String email){
        assistant.email = email;
    }
    /**
    * Gets the assistant name when passed the assistant
    * @param assistant - this is the the assistants name, it should include a first and a last name
    * @return the name of the assistant first and last as a String.
    */
    public String getAssistantName(Assistant assistant){
        return assistant.name;
    }

    
    /** 
     * Gets the assistant email when passes the assistant.
     * @param assistant - this is the assistant the email will be acessed from.
     * @return the email of the assistant passed in as a String.
     */
    public String getAssistantEmail(Assistant assistant){
        return assistant.email;
    }

}
