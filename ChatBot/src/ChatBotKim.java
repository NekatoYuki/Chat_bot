import java.util.Random;
import java.util.Scanner;

public class ChatBotKim
{
	int emotion = 0; //current emotion state
	
	public String getGreeting()
	{
		//beginning of chatbot
		System.out.println("\n");
		System.out.println("Drug Abuse & Addiction Support");
		System.out.println("To return to the main menu simply enter the word 'bye'");
		return "You have reached Drug Abuse & Addiction Support.";
		return "Are you reaching support for yourself or someone you know?";
	}
	//input method
	Scanner input = new Scanner(System.in);
	
	//all possible responses for specific detected keywords
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
			emotion--;
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
		else if (findKeyword(statement, "I like you")>=0)
		{
			response = "I like you too!!! :D";
			emotion+2;
		}
		else if (findKeyword(statement, "I love you")>=0)
		{
			response = "R-Really...? Q///^///Q T-thank you... I love you too.. -blushes- ";
			emotion+365;
		}
		else if (findKeyword(statement, "game")>=0 //game
		{
			response = "Do you want to play a game? Enter gameYes to accept or gameNo to decline.";
			emotion++;
		}
		else if (findKeyword(statement, "gameYes")>=0)
		{
			response = "The only game I know how to play is rock, paper, scissor. Will that be ok? Enter playGame to accept or noGame to decline.;"
		}
		else if (findKeyword(statement, "playGame")>=0)
		{
			response = "We go on the count of 3, ok? 1... 2... 3...!!!";
			emotion++;
		}
		else if (findKeyword(statement, "rock")>=0) || findKeyword(statement, "paper")>=0 || findKeyword(statement, "scissor")>=0)
		{
			response = getRandomGameOutcome();
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
		if (emotion >=365)
		{
			return randomLoveResponse [r.nextInt(randomLoveResponses.length)];
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
			":c",
			"Y-youre kinda pissing me off...",
			"...",
			"Wouldn't you rather talk to a different chatBot than me?"
		};
	private String [] randomHappyResponses =
		{
			"I really like you. <3",
			"Friend <3",
			"^-^",
		};
	private String [] randomSuggestions =
		{
			"How about a short game to lighten the mood? :3",
			"Let's talk about something else besides drugs. Name a sport that you like. ^-^",
			"You like to listen to music?",
			"You play any video games?"
		};
	private String [] randomLoveResponses =
		{
			"-happily listening-",
			" <3 <3 <3",
			"I- I love you a lot! >///^///<",
		};
	
}
