import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is the main menu is guides the output to the user and calls the right methods depending on the input from the user
 * @author Max Satchell
 */
public class MainMenu {
    
    /** 
     * The menu method contols the input and output to the user
     * @param university - Pass in the university so there is some data
     * @param system - Pass in the system so that assistants on shift, bookable rooms and bookings can be created
     */
    public static void menu(University university, BookingSystem system)  {
        Scanner read = new Scanner(System.in);
        System.out.println("University of Knowledge - COVID test");
        System.out.println("\n");
        System.out.println("Manage Bookings");
        System.out.println("\n");
        System.out.println("Please, enter the number to select your option:");
        System.out.println("\n");
        System.out.println("To manage Bookable Rooms:");
        System.out.println("    1. List");
        System.out.println("    2. Add");
        System.out.println("    3. Remove");
        System.out.println("To manage Assistants on Shift:");
        System.out.println("    4. List");
        System.out.println("    5. Add");
        System.out.println("    6. Remove");
        System.out.println("To manage Bookings:");
        System.out.println("    7. List");
        System.out.println("    8. Add");
        System.out.println("    9. Remove");
        System.out.println("    10. Conclude");
        System.out.println("After selecting one the options above, you will be presented other screens.");
        System.out.println("If you press 0, you will be able to return to this main menu.");
        System.out.println("Press -1 (or ctrl+c) to quit this application.");
        String selection = read.nextLine();


        //A switch case statement is an easy way to stucture the initial input from the user
        switch(selection){
            case "1":
                BookingApp.clearConsole();
                System.out.println("University of Knowledge - COVID test");
                System.out.println("\n");
                system.listOfBookableRooms();
                System.out.println("0. Back to main menu.");
                System.out.println("-1. Quit application");
                selection = read.nextLine();
   
                if(selection.equals("0")){
                    BookingApp.clearConsole();
                    menu(university,system);        
                }
                else if(selection.equals("-1")){
                    System.exit(0);
                }
                else if(selection.equals("")){
                    university.errorMessage("Invalid input detected you entered an empty string try again");
                }
                else{
                    System.out.println("invalid input detected heading back to main menu");
                    System.out.println("\n");
                    menu(university,system);
                }
                break;
            case "2":
                BookingApp.clearConsole();
                System.out.println("University of Knowledge - COVID test");
                System.out.println("\n");
                System.out.println("Adding Bookable room");
                System.out.println("\n");
                while(selection != "0"|| selection != "-1"){
                University.listRooms(university);
                    System.out.println("Please enter one of the following");
                    System.out.println("\n");            
                    System.out.println("The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM),separated by a white space.");
                    System.out.println("0. Back to main menu.");
                    System.out.println("-1. Quit application");
                    System.out.println("\n");

                    selection = read.nextLine();
    
                    if(selection.equals("0"))
                    {
                        BookingApp.clearConsole();
                        menu(university,system);
                    }
                    else if(selection.equals("-1")){
                        System.exit(0);
                    }
                    else if(selection.equals("")){
                        university.errorMessage("Invalid input detected you entered an empty string try again");
                    }
                    else{
                        try {
                            String[] selectionSplit = selection.split("\\s+");
                            int index = Integer.parseInt(selectionSplit[0]);
                            index = index -11;
                            Room room = University.getRoomFromIndex(university, index);
                            if(selectionSplit.length< 3){
                                BookingApp.clearConsole();
                                String message = "Incorrect entry of data you did not input id then date then time please try again";
                                university.errorMessage(message);
                            }
                            else{
                                String timeSlot = selectionSplit[1]+" "+selectionSplit[2];
                                if(room == null){
                                    BookingApp.clearConsole();
                                    String message = "The sequential id that you inputted is invalid please try again";
                                    university.errorMessage(message);
                                    if(BookableRoom.dateFormatChecker(timeSlot) != null){
                                        university.errorMessage(BookableRoom.dateFormatChecker(timeSlot));}
                                }
                                else if(BookableRoom.dateFormatChecker(timeSlot) != null){
                                    BookingApp.clearConsole();
                                    university.errorMessage(BookableRoom.dateFormatChecker(timeSlot));
                                }
        
                                else{             
                                    BookableRoom validRoom = BookableRoom.createBookableRoom(room, system, timeSlot);
                                    if(validRoom != null){
                                        BookingApp.clearConsole();
                                        BookableRoom.successAddingBookableRoom(validRoom);
                                    }
                                                                
                                }
                                    
                                
                            }
                            
                        } catch (NumberFormatException e) {
                            BookingApp.clearConsole();
                            university.errorMessage("The id you entered is not a number please try again");
                        }
                        
                        
                    }

                }               
                break;
            case "3":
            BookingApp.clearConsole();
            System.out.println("University of Knowledge - COVID test");
            System.out.println("\n");
            while(selection != "0"|| selection != "-1"){
            ArrayList<BookableRoom> emptyRooms = system.listOfEmptyRooms();
            if(emptyRooms.size() ==0){
                System.out.println("There are no rooms that can be removed at this time");
            }
  
            
                System.out.println("Please enter one of the following");
                System.out.println("\n");            
                System.out.println("The sequential ID to select the bookable room to be removed");
                System.out.println("0. Back to main menu.");
                System.out.println("-1. Quit application");
                System.out.println("\n");
                selection = read.nextLine();

                if(selection.equals("0")){
                    BookingApp.clearConsole();
                    menu(university,system);
                }
                else if(selection.equals("-1")){
                    System.exit(0);
                }
                else if(selection.equals("")){
                    BookingApp.clearConsole();
                    university.errorMessage("Invalid input detected you entered an empty string try again");
                }
                else{
                    try {
                        int index = Integer.parseInt(selection) - 11;
                        if(index > emptyRooms.size()-1 || index<0){
                            BookingApp.clearConsole();
                            university.errorMessage("The id you selected was incorrect please try again");
                        }
                        else{
                            if(system.removeBookableRoom(emptyRooms.get(index))){
                                BookingApp.clearConsole();
                                BookableRoom.successRemovingBookableRoom(emptyRooms.get(index));
                            }
                            else{
                                BookingApp.clearConsole();
                                university.errorMessage(" The ID you entered is incorrect please try again");}
                        }
                    } catch (NumberFormatException e) {
                        BookingApp.clearConsole();
                        university.errorMessage("The id you entered is not a number please try again");
                    }
                    
                }
            }
                break;
            case "4":
                BookingApp.clearConsole();
                System.out.println("University of Knowledge - COVID test");
                System.out.println("\n");
                system.listOfAssistantOnShifts();
                System.out.println("0. Back to main menu.");
                System.out.println("-1. Quit application");
                selection = read.nextLine();


                if(selection.equals("0")){
                    BookingApp.clearConsole();
                    menu(university,system);
                }
                else if(selection.equals("-1")){
                    System.exit(0);
                }
                else if(selection.equals("")){
                    BookingApp.clearConsole();
                    university.errorMessage("Invalid input detected you entered an empty string try again");
                }
                else{
                    BookingApp.clearConsole();
                    System.out.println("invalid input detected heading back to main menu");
                    System.out.println("\n");
                    menu(university,system);
                }
                break;
                
            case "5":
                BookingApp.clearConsole();
                System.out.println("University of Knowledge - COVID test");
                System.out.println("\n");
                System.out.println("Adding Assistant on Shift");
                System.out.println("\n");
                while(selection != "0"|| selection != "-1"){
                University.listAssistants(university);
                
                    System.out.println("Please enter one of the following");
                    System.out.println("\n");
                    System.out.println("The sequential ID listed to a assistant, a date (dd/mm/yyyy), separated by a white space.");
                    System.out.println("0. Back to main menu.");
                    System.out.println("-1. Quit application");
                    System.out.println("\n");
                    selection = read.nextLine();

                    if(selection.equals("0")){
                        BookingApp.clearConsole();
                        menu(university,system);
                    }
                    else if(selection.equals("-1")){
                        System.exit(0);
                    }
                    else if(selection.equals("")){
                        BookingApp.clearConsole();
                        university.errorMessage("Invalid input detected you entered an empty string try again");
                    }
                    else{
                        String[] selectionSplit = selection.split("\\s+");
                        if(selectionSplit.length < 2){
                            BookingApp.clearConsole();
                            university.errorMessage("You need to input an id and a date in that order");
                        }
                        else{
                            try {
                                int index = Integer.parseInt(selectionSplit[0]);
                                index = index -11;
                                Assistant assistant = University.getAssistantFromIndex(university, index);
                                String date = selectionSplit[1];
                                if(assistant == null){
                                    BookingApp.clearConsole();
                                    university.errorMessage("The sequential id you have entered is invalid please try again");
                                    if(AssistantOnShift.dateChecker(date)!= null){
                                        university.errorMessage(AssistantOnShift.dateChecker(date));}
                                }
                                else if(AssistantOnShift.dateChecker(date)!= null){
                                    BookingApp.clearConsole();
                                    university.errorMessage(AssistantOnShift.dateChecker(date));
                                }
                                else{
                                    ArrayList<AssistantOnShift> createdAssistants = AssistantOnShift.createAssistantOnShift(assistant, date, system);
                                    BookingApp.clearConsole();
                                    if(createdAssistants.size() != 0){
                                        AssistantOnShift.successAddingAssistantOnShift(createdAssistants);
                                    }
                                }
                        
                            } catch (NumberFormatException e) {
                                BookingApp.clearConsole();
                                university.errorMessage("The id you entered is not a number please try again");
                            }
                            
                        }
                        
                }   }
                break;
            case "6":
                BookingApp.clearConsole();
                System.out.println("University of Knowledge - COVID test");
                System.out.println("\n");
                while(selection != "0" || selection != "-1"){
                AssistantOnShift[] freeAssistantOnShifts = system.getAvailableAssistantOnShifts(system.getAssistantsOnShift());
                int count =10;
                for(int i = 0;i<freeAssistantOnShifts.length;i++){
                    count+=1;
                    System.out.println(String.valueOf(count)+". | "+freeAssistantOnShifts[i].getTimeSlotOfAssistant(freeAssistantOnShifts[i])+" | "+freeAssistantOnShifts[i].getStatusOfAssistant(freeAssistantOnShifts[i])+" | "+freeAssistantOnShifts[i].email+" |");
                }
    
                
                    System.out.println("Please enter one of the following");
                    System.out.println("\n");            
                    System.out.println("The sequential ID to select the assistant on shift to be removed");
                    System.out.println("0. Back to main menu.");
                    System.out.println("-1. Quit application");
                    System.out.println("\n");
                    selection = read.nextLine();

                    if(selection.equals("0")){
                        BookingApp.clearConsole();
                        menu(university,system);
                    }
                    else if(selection.equals("-1")){
                        System.exit(0);
                    }
                    else if(selection.equals("")){
                        BookingApp.clearConsole();
                        university.errorMessage("Invalid input detected you entered an empty string try again");
                    }
                    else{
                        try {
                            int index = Integer.parseInt(selection) - 11;
                            if(index > freeAssistantOnShifts.length-1 || index<0 ){
                                university.errorMessage("The id you selected was incorrect please try again");
                            }
                            else{
                                if(system.removeAssistantOnShift(freeAssistantOnShifts[index])){
                                    BookingApp.clearConsole();
                                    AssistantOnShift.successRemovingAssistantOnShift(freeAssistantOnShifts[index]);
                                }
                                else{BookingApp.clearConsole(); university.errorMessage(" The ID you entered is incorrect please try again");}
                            }
                        } catch (NumberFormatException e) {
                            BookingApp.clearConsole();
                            university.errorMessage("The id you entered is not a number please try again");
                        }
                    }
                }
                break;
            case "7":
                BookingApp.clearConsole();
                System.out.println("University of Knowledge - COVID test");
                System.out.println("\n");
                System.out.println("Select which booking to list:");
                System.out.println("1. All");
                System.out.println("2. Only bookings status:SCHEDULED");
                System.out.println("3. Only bookings status:COMPLETED");
                while(selection != "0"|| selection != "-1"){
                    System.out.println("0. Back to main menu.");
                    System.out.println("-1. Quit application");
                    selection = read.nextLine();
                    if(selection.equals("0")){
                        BookingApp.clearConsole();
                        menu(university,system);
                        
                    }
                    else if(selection.equals("-1")){
                        System.exit(0);
                    }
                    else if(selection.equals("2")){
                        system.listBookings("SCHEDULED");
                    }
                    else if(selection.equals("3")){
                        system.listBookings("COMPLETED");
                    }
                    else{
                        system.listBookings("");
                    }
                }
                break;
            case "8":
                BookingApp.clearConsole();
                System.out.println("University of Knowledge - COVID test");
                System.out.println("\n");
                System.out.println("Adding Booking (appointment to covid test) to the system");
                System.out.println("\n");
                System.out.println("List of available time-slots");
                while(selection != "0"|| selection != "-1"){
                system.listOfAvailableTimeSlots();
                    System.out.println("\n");
                    System.out.println("Please enter the following");
                    System.out.println("\n");
                    System.out.println("The sequential ID of an available time-slot and a student email, separated by a white space.");
                    System.out.println("0. Back to main menu.");
                    System.out.println("-1. Quit application");
                    System.out.println("\n");
                    selection = read.nextLine();

                    if(selection.equals("0")){
                        BookingApp.clearConsole();
                        menu(university,system);
                    }
                    else if(selection.equals("-1")){
                        System.exit(0);
                    }
                    else if(selection.equals("")){
                        BookingApp.clearConsole();
                        university.errorMessage("Invalid input detected you entered an empty string try again");
                    }
                    else{
                        String[] selectionSplit = selection.split("\\s+");
                        if(selectionSplit.length < 2){
                            BookingApp.clearConsole();
                            university.errorMessage("invalid input you need to input the id then the student email");
                        }
                        else{
                            try {
                                int index = Integer.parseInt(selectionSplit[0]);
                                index = index -11;
                                String studentEmail = selectionSplit[1];
                                ArrayList<String> availableTimeSlots = Booking.createAvailableTimeSlots(system.getBookableRooms(), system.getAssistantsOnShift());
                                if(studentEmail.length()< 10){
                                    BookingApp.clearConsole();
                                    university.errorMessage("The student email you have entered does not meet the requirements please try again with an email ending @uok.co.uk");
                                }
                                else{
                                    if(index > availableTimeSlots.size() || index  < 0){
                                        BookingApp.clearConsole();
                                        university.errorMessage("The sequential id you have inputted is invalid");
                                        if(!studentEmail.substring(studentEmail.length()-10).equals("@uok.co.uk")){
                                            university.errorMessage("The student email you have entered does not end in @uok.co.uk please try again");
                                        }
                                    }
                                    else if(!studentEmail.substring(studentEmail.length()-10).equals("@uok.co.uk")){
                                        BookingApp.clearConsole();
                                        university.errorMessage("The student email you have entered does not end in @uok.co.uk please try again");
                                    }
                                    else{
                                        String timeSlot = availableTimeSlots.get(index);
                                        Booking booking = Booking.createBooking(system.getBookableRooms(), system.getAssistantsOnShift(), timeSlot, studentEmail, system);
                                        if(booking != null){
                                            BookingApp.clearConsole();
                                            Booking.successAddingBooking(booking);
                                        }
                                    }
                                }

                                
                            } catch (NumberFormatException e) {
                                BookingApp.clearConsole();
                                university.errorMessage("The id you entered is not a number please try again");
                            }
                            
                        }

                    }    }
                break;
            case "9":
                BookingApp.clearConsole();
                System.out.println("University of Knowledge - COVID test");
                System.out.println("\n");
                while(selection != "0"|| selection != "-1"){
                ArrayList<Booking> scheduledBookings = system.getScheduledBookings();
                System.out.println("Removing booking from the system");
                    System.out.println("Please enter one of the following");
                    System.out.println("\n");            
                    System.out.println("The sequential ID to select the booking to be removed");
                    System.out.println("0. Back to main menu.");
                    System.out.println("-1. Quit application");
                    System.out.println("\n");
                    selection = read.nextLine();

                    if(selection.equals("0")){
                        BookingApp.clearConsole();
                        menu(university,system);
                    }
                    else if(selection.equals("-1")){
                        System.exit(0);
                    }
                    else if(selection.equals("")){
                        BookingApp.clearConsole();
                        university.errorMessage("Invalid input detected you entered an empty string try again");
                    }
                    else{
                        try {
                            
                            int index = Integer.parseInt(selection) - 11;
                            if(index > scheduledBookings.size()-1 || index<0){
                                university.errorMessage("The id you selected was incorrect please try again");
                            }
                            else{
                                if(system.removeBooking(scheduledBookings.get(index))){
                                    BookingApp.clearConsole();
                                    Booking.successRemovingBooking(scheduledBookings.get(index));
                                }
                                else{BookingApp.clearConsole(); university.errorMessage(" The ID you entered is incorrect please try again");}
                            }
                            
                        } catch (NumberFormatException e) {
                            BookingApp.clearConsole();
                            university.errorMessage("The id you entered is not a number please try again");
                        }
                    }
                }
                break;
            case "10":
                BookingApp.clearConsole();
                System.out.println("University of Knowledge - COVID test");
                System.out.println("\n");
                while(selection != "0"|| selection != "-1"){
                    ArrayList<Booking> scheduledBookings = system.getScheduledBookings();
                    System.out.println("Conclude Booking");
                    System.out.println("Please enter one of the following");
                    System.out.println("\n");            
                    System.out.println("The sequential ID to select the booking to be completed");
                    System.out.println("0. Back to main menu.");
                    System.out.println("-1. Quit application");
                    System.out.println("\n");
                    selection = read.nextLine();

                    if(selection.equals("0")){
                        BookingApp.clearConsole();
                        menu(university,system);
                    }
                    else if(selection.equals("-1")){
                        System.exit(0);
                    }
                    else if(selection.equals("")){
                        BookingApp.clearConsole();
                        university.errorMessage("Invalid input detected you entered an empty string try again");
                    }
                    else{
                        try {
                            int index = Integer.parseInt(selection) - 11;
                            if(index > scheduledBookings.size()-1 || index<0){
                                BookingApp.clearConsole();
                                university.errorMessage("The id you selected was incorrect please try again");
                            }
                            else
                            {
                                Booking completeBooking = system.concludeBooking(scheduledBookings.get(index));
                                if(completeBooking != null){
                                    BookingApp.clearConsole();
                                    Booking.successConcludingBooking(completeBooking);
                                }
                                else{BookingApp.clearConsole(); university.errorMessage(" The ID you entered is incorrect please try again");}
                            }
                                                   
                        } catch (NumberFormatException e) {
                            BookingApp.clearConsole();
                            university.errorMessage("The id you entered is not a number please try again");
                        }
                        
                    }
                }
                break;
            case "-1":
                System.exit(0);
                break;
            default:
                BookingApp.clearConsole();
                System.out.println("The input is invalid please try again");
                menu(university,system);

        }



    }

    
}
