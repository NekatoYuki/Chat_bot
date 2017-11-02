import java.util.Scanner;
import java.util.Random;

/**
 * A simple class to run our chatbot teams.
 * @author Mr. Levin - Starter Code
 *    	Kenneth Kim, Jeffery Lau, Stanley Liang, Evan Wu - Modified Code
 * @version September 2017; Updated October 2017
 */
@SuppressWarnings("unused")
public class ChatBotRunner
{
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception
	{
		 
		//Value storage for chatbot names?
		ChatBotKim chatbot1 = new ChatBotKim();
		ChatBotLau chatbot2 = new ChatBotLau();
		ChatBotLiang chatbot3 = new ChatBotLiang();
		ChatBotWu chatbot4 = new ChatBotWu();
		
		//Menu info text screen
		System.out.println("\n");
		System.out.println("Drug awareness chat");
		System.out.println("\n");
		System.out.println("Welcome to the drug awareness chat. Please select a topic/chatbot.");
		System.out.println("1.Drug abuse/addiction support");
		System.out.println("2.Drug information");
		System.out.println("3.Drug awareness");
		System.out.println("4.Drug abuse");
		System.out.println("***NOTE: when selecting a choice please enter the choice number ONLY.***");
		Scanner input = new Scanner(System.in);
		String choose = input.nextLine();
	
	//User input must be a number or else the code will return bye and break
	//When user inputs a choice number the choice selected chatbot will load
	if (choose.equals("1")) {
	System.out.println (chatbot1.getGreeting());
	Scanner in = new Scanner (System.in);
	String statement = in.nextLine();

	while (!statement.equals("Bye"))
	{
		System.out.println (chatbot1.getResponse(statement));
		statement = in.nextLine();
		}
	}

	
	else if (choose.equals("2")) {
	System.out.println (chatbot2.getGreeting());
	Scanner in = new Scanner (System.in);
	String statement = in.nextLine();

	while (!statement.equals("Bye"))
	{
		System.out.println (chatbot2.getResponse(statement));
		statement = in.nextLine();
		}
	}

	
	else if (choose.equals("3")) {
	System.out.println (chatbot3.getGreeting());
	Scanner in = new Scanner (System.in);
	String statement = in.nextLine();

	while (!statement.equals("Bye"))
	{
		System.out.println (chatbot3.getResponse(statement));
		statement = in.nextLine();
		}
	}

	else if (choose.equals("4")) {
	System.out.println (chatbot4.getGreeting());
	Scanner in = new Scanner (System.in);
	String statement = in.nextLine();

	while (!statement.equals("Bye"))
	{
		System.out.println (chatbot4.getResponse(statement));
		statement = in.nextLine();
		}
	}
	
	//Returns bye if user selects a choice that is not a choce number
	else {
		System.out.println("Bye!");
	}
	}
}
