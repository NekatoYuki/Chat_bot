import java.util.Random;

public class ChatBotKim
{
	int emotion = 0;
	
	public String getGreeting()
	{
		System.out.println("\n");
		System.out.println("Drug Abuse & Addiction Support");
		System.out.println("To return to the main menu simply enter the word 'bye'");
		return "You have reached Drug Abuse & Addiction Support.";
		return "Are you reaching support for yourself or someone you know?";
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
		else if (findKeyword(statement, "friend")>=0 || findKeyword(statement, "father")>=0)
		{
			response = "I see. May I ask for the person's name please?";
		}
		else if (findKeyword(statement, "mother")>=0 || findKeyword(statement, "cousin")>=0 || findKeyword(statement, "uncle")>=0)
		{
			response = "I see. May I ask for the person's name please?";
		}
		else if (findKeyword(statement, "myself")>=0 || findKeyword(statement, "me")>=0)
		{
			response = "I see. May I ask for your name please?";
		}
		else if (findKeyword(statement, "call")>=0)
		{
			response = "You can call and talk to one of our support members personally at 1-800-555-5555.";
		}
		else if (findKeyword(statement, "Bye") >= 0)
		{
			System.out.println("Enjoy your day and stay safe from drug!!! ^-^");
			@SuppressWarnings("unused")
			ChatBotRunner chatbot1 = new ChatBotRunner();
			String[] args = new String[0] ;
		    ChatBotRunner.main(args);
		}
		else
		{
			response = getRandomResponse();
		}
		return response;
	}
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Do you really believe it's ok to" + restOfStatement + "?" + " Doing" + restOfStatement + "will be bad for your overall health";
	}
	private int findKeyword(String statement, String goal,
			int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}

	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	private String [] randomNeutralResponses =
		{
			"I see... continue.",
			"Ok. What else?",
			"I'm sorry. Can you repeat that again?",
			"What other information do you have?",
		};
	private String [] randomAngryResponses =
		{
			">:c",
			"",
			"",
		};
	private String [] randomHappyResponses =
		{
			"",
			"",
			"",
		};
	private String [] randomSuggestions =
		{
			"How about a short game to lighten the mood? :3",
			"Let's talk about something else besides drugs. Name a sport that you like. ^-^",
			"You like jazz?",
			"You play any video games?"
		};
	
}
