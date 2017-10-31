import java.util.Random;

public class ChatBotKim
{
	int emotion = 0;
	
	public String getGreeting()
	{
		System.out.println("\n");
		System.out.println("Drug Abuse & Addiction Support");
		System.out.println("To return to the main menu simply enter the word 'bye'");
		return "You have reached Drug Abuse & Addiction Support."
		return "Are you reaching support for yourself or someone you know?"
	}
	Scanner input = new Scanner(System.in);
	
	public String getResponse(String statement)
	{
		String response = "";
		if (statement.length()==0)
		{
			response = "Please indicate who is in need of support.";
		}
		else if (findKeyword(statement, "no")>=0)
		{
			response = "Are you sure you aren't in need of support for someone or yourself?";
			if (findKeyword(statement, "yes")>=0)
			{
				response = "Ok then. Please enter 'bye' to return to the main menu";
			}
			else if (findKeyword(statement, "no")>=0)
			{
				response = "Please indicate who is in need of support.";
			}
		}
		else if (findKeyword(statement, "friend")>=0 || findKeyword(statement, "father")>=0) || findKeyword(statement, "mother")>=0 || findKeyword(statement, "cousin")>=0 || findKeyword(statement, "uncle")>=0)
		{
			response = "I see. May I ask for the person's name please?";
		}
		else if (findKeyword(statement, "myself")>=0 || findKeyword(statement, "me")>=0)
		{
			response = "I see. May I ask for your name please?";
		}
		else if (findKeyword(statement, "Bye") >= 0)
		{
			System.out.println("Enjoy your day and stay safe from drug!!! ^-^");
			@SuppressWarnings("unused")
			ChatBotRunner chatbot1 = new ChatBotRunner();
			String[] args = new String[0] ;
		    ChatBotRunner.main(args);
		}
	}
}
