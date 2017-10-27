import java.util.Scanner;
import java.util.Random;

/**
 * A simple class to run our chatbot teams.
 * @author Mr. Levin
 * @version September 2017
 */
public class ChatBotRunner
{

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 * @return 
	 */
	public static void main(String[] args)
	{
		System.out.println("DRUG ABUSE CHAT");
		System.out.println("\n");
		System.out.println("NOTE: THIS CHATBOT IS IN DEVELOPMENT SO EXPECT SOME BUGS.");
		
		public String getAnswer()
		{
			return "Hi, what is up?";
		}
		
		ChatBotKim chatbot1 = new ChatBotKim();
		ChatBotLiang chatbot2 = new ChatBotLiang();
		ChatBotLau chatbot3 = new ChatBotLau();
		ChatBotWu chatbot4 = new ChatBotWu();
		
	
		
		System.out.println (chatbot1.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
		


		while (!statement.equals("Bye"))
		{
			System.out.println (chatbot1.getResponse(statement));
			statement = in.nextLine();
		}
	}

}
