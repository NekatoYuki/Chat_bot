import java.util.Random;
import java.util.Scanner;
import java.awt.Desktop;
import java.net.URI;

public class ChatBotKim
{
	int emotion = 0; //current emotion state
	
	public String getGreeting()
	{
		//beginning of chat_bot
		System.out.println("\n");
		System.out.println("Drug Abuse & Addiction Support");
		System.out.println("To return to the main menu simply enter the word 'bye'");
		return "You have reached Drug Abuse & Addiction Support. Are you reaching support for yourself or someone you know?";
	}
	//input method
	Scanner input = new Scanner(System.in);
	
	//all possible responses for specific detected keywords
	public String getResponse(String statement) throws Exception
	{
		String response = "";
		if (statement.length()==0)
		{
			response = "Please indicate who is in need of support.";
		}
		else if (findKeyword(statement, "no one")>=0)
		{
			response = "Are you sure you aren't in need of support for someone or yourself?";
			emotion--;
			if (findKeyword(statement, "yes im sure")>=0)
			{
				response = "Ok then. Please enter 'bye' to return to the main menu";
			}
			else if (findKeyword(statement, "no im not sure")>=0)
			{
				response = "Please indicate who is in need of support.";
			}
		}
		else if (findKeyword(statement, "someone i know")>=0) 
		{
			response = "Ok. Who is this person?";
		}
		else if (findKeyword(statement, "friend")>=0 || findKeyword(statement, "father")>=0 ||
				findKeyword(statement, "mother")>=0 || findKeyword(statement, "cousin")>=0 ||
				findKeyword(statement, "uncle")>=0 || findKeyword(statement, "aunt")>=0 ||
				findKeyword(statement, "brother")>=0 || findKeyword(statement, "sister")>=0)//possible answers if not oneself
		{
			response = "I see. May I ask for the person's name please?";
			if (findKeyword(statement, "no")>=0)
			{
				response = "If you really want to help this person, you should give up their name so we can help.";
			}
		}
		else if (findKeyword(statement, "myself")>=0)
		{
			response = "I see. May I ask for your name please?";
			if (findKeyword(statement, "no")>=0)
			{
				response = "Ok. It's ok to keep it hidden if you are shy. Why do you want to talk to me?";
			}
		}
		else if (findKeyword(statement, "call")>=0 || findKeyword(statement, "number")>=0)
		{
			response = "You can call and talk to one of our support members personally at 1-800-555-5555.";
		}
		else if (findKeyword(statement, "I like you")>=0)
		{
			response = "I like you too!!! :D";
			emotion++;
		}
		else if (findKeyword(statement, "I love you")>=0)
		{
			response = "R-Really...? Q///^///Q T-thank you... I love you too.. -blushes- ";
			emotion+=365;
		}
		else if (findKeyword(statement, "game")>=0) //game
		{
			response = "Do you want to play a game? Enter YG to accept or NG to decline.";
			emotion++;
		}
		else if (findKeyword(statement, "YG")>=0)
		{
			response = "I can play rock, paper, scissor. Which one do you want to play? Enter playRPS or noGame.";
		}
		else if (findKeyword(statement, "playRPS")>=0) //rock paper scissors
		{
			response = "We go on the count of 3, ok? 1... 2... 3...!!!";
			emotion++;
		}
		else if ((findKeyword(statement, "rock")>=0) || findKeyword(statement, "paper")>=0 || findKeyword(statement, "scissor")>=0)
		{
			response = getRandomGameOutcome() + " Yay! That was fun, want to play again? Enter playRPS to play again or quitGame to quit.";
		}
		else if (findKeyword(statement, "noGame")>=0)
		{
			response = "Is there anything else you want to talk about?";
		}
		else if (findKeyword(statement, "music")>=0)
		{
			response = "What kind of music do you like? o.o I like Drum & Bass, Electro, and House. X3";
			emotion++;
		}
		else if (findKeyword(statement, "quitGame")>=0)
		{
			response = "Thanks for playing!!!";
		}
		else if (findKeyword(statement, "bored")>=0)
		{
			response = getRandomSuggestions();
			if (findKeyword(statement, "no")>=0 || findKeyword(statement, "nah")>=0);
			{
				response = getRandomSuggestions();
			}
		}
		else if (findKeyword(statement, "drug")>=0)
		{
			response = "That is a problem isn't it. However, we do have some numbers and site you can use for help.";
		}
		else if (findKeyword(statement, "web")>=0 || findKeyword(statement, "site")>=0)
		{
			response = website1();
		}
		else if (findKeyword(statement, "I want to", 0)>=0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "Can you", 0)>=0)
		{
			response = transformCanYouStatement(statement);
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
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Do you really believe it's ok to" + restOfStatement + "?";
	}
	private String transformCanYouStatement(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length()-1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length()-1);
		}
		int psn = findKeyword (statement, "Can you", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Of course I can" + restOfStatement + "." + "Anything specific you need to tell me?";
	}
	private int findKeyword(String statement, String goal,
			int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();
		int psn = phrase.indexOf(goal, startPos);
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
				after = phrase.substring(psn + goal.length(),psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0) && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))))
			{
				return psn;
			}
			psn = phrase.indexOf(goal, psn + 1);
		}
		return -1;
	}
	private String website1() throws Exception
	{
		Desktop desktop=Desktop.getDesktop();
		desktop.browse(new URI("https://www.centeronaddiction.org/"));
		return "Opening requested website...";
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
			return randomLoveResponses [r.nextInt(randomLoveResponses.length)];
		}
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	private String getRandomGameOutcome ()
	{
		Random r = new Random ();
		{
		return getRandomGameOutcome [r.nextInt(getRandomGameOutcome.length)];
		}
	}
	private String getRandomSuggestions ()
	{
		Random r = new Random ();
		{
		return randomSuggestions [r.nextInt(randomSuggestions.length)];
		}
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
	private String [] getRandomGameOutcome =
		{
			"paper!!!",
			"rock!!!",
			"scissor!!!",
		};
}
