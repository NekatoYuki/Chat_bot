import java.util.Random;
import java.util.Scanner;
import java.awt.Desktop;
import java.net.URI;

/* This version:
 * @author Mr. Levin - Reference/starter Code 
 *		   Evan Wu - Modified Code
 *		   Chatbot Project - Due 11/1/17
 * @version October 2017
 */
public class ChatBotWu
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;

	 
	public String getGreeting()
	{
		//info and introduction section of the chatbot
		System.out.println("\n");
		System.out.println("DRUG ABUSE CHAT");
		System.out.println("Enter bye to return back to menu.");
		return "\n" + getRandomGreeting() + " " + "Welcome to the Drug abuse chat! Talk to me or type 'options' for more options!";
	} 
	//input method 
	Scanner input = new Scanner(System.in);
	
	//chatbot responses for certain keywords
	public String getResponse(String statement) throws Exception
	{
		String response = "";
		
		if (statement.length() == 0)
		{
			response = "Please tell me something so I can help you.";
			emotion--;
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Please tell me what you need help with.";
                	emotion--;
		}
		
		else if (findKeyword(statement, "hi") >= 0
				||findKeyword(statement, "hello") >= 0)
		{
			response = getRandomGreeting();
                	emotion++;
		}
		
		else if (findKeyword(statement, "yes") >= 0
				||(findKeyword(statement, "friend") >= 0))
		{
			response = "Tell me more, I'm listening.";
				
		}
		
		else if (findKeyword(statement, "bored") >= 0
				||(findKeyword(statement, "boring") >= 0))
		{
			response = "You're bored? Type keyword 'option' to see what else I have to offer!";
				
		}
		
		else if (findKeyword(statement, "joke") >= 0 
				|| findKeyword(statement, "tell me a joke") >= 0 )
		{
			response = "Here's a joke:" + " " + getRandomJoke();
                	emotion++;
		}
		
		else if (findKeyword(statement, "advice") >= 0 )
		{
			response = "Here's an advice:" + " " + getRandomAdvice();
                	emotion++;
		}

		else if (findKeyword(statement, "fact") >= 0||
		(findKeyword(statement, "facts") >= 0))
		{
			response = "Here's a fact:" + " " + getRandomFact();
	                  emotion++;
	    }
		
		else if (findKeyword(statement, "option") >= 0||
				(findKeyword(statement, "options") >= 0))
				{
					response = "Get advice - keyword 'advice'."+ "\n"+ "Get facts - keyword 'fact'." + "\n"+ "Test your knowledge - keyword 'quiz'." 
								+ "\n"+ "Cheer up with a joke - keyword 'joke'.";
			    }
		
		//Quiz (true/false; everything is true)
		else if (findKeyword(statement, "question") >= 0||
				findKeyword(statement, "test") >= 0 ||
				findKeyword(statement, "knowledge") >= 0 ||
				findKeyword(statement, "quiz") >= 0)
		{
			response = "Test your knowledge in this true/false quiz!" +"\n" + "To start respond with quizYes, to exit, respond with quizNo.";
		}
		//user select yes
		else if (findKeyword(statement, "quizyes") >= 0)
		{
			response = "Are you ready? Respond with ready or notReady.";
		}
		//user ready 
		else if (findKeyword(statement, "ready") >= 0)
		{
			response = "True or false?" + " " + getRandomQuestion();
		}
		//chatbot's true responses
		else if (findKeyword(statement, "true") >= 0)
		{
			response = trueResponses() + " " + "Ready for another question? Or do you want to quit? Respond with ready or exit.";
		}
		//chatbot's false responses
		else if (findKeyword(statement, "false") >= 0)
		{
			response = falseResponses() + " " + "Ready for another question? Or do you want to quit? Respond with ready or exit.";
		}
		//user responds with not ready for quiz
		else if (findKeyword(statement, "notready") >= 0)
		{
			response = "Hurry up! I'm waiting for you.";
                	emotion++;
		}
		//exits quiz
		else if (findKeyword(statement, "quizno") >= 0
				|| findKeyword(statement, "exit") >= 0)
		{
			response = "Ok :(";
                	emotion--;
		}
		
		//searches web for online resources using method searchWeb
		else if (findKeyword(statement, "search") >= 0
				||findKeyword(statement, "resource") >= 0
				||findKeyword(statement, "look up") >= 0
				||findKeyword(statement, "google") >= 0
				||findKeyword(statement, "resources") >= 0)
		{
			response = "Do you want me to link you to search results for drug abuse resources? Respond with searchYes.";
		}
		else if (findKeyword(statement, "searchyes") >= 0)
		{
			response = searchWeb();
		}
		
		//more keyword responses
		else if (findKeyword(statement, "heroin") >=0 
				|| findKeyword(statement, "marijuana") >= 0 
				|| findKeyword(statement, "cocaine") >=0 
				|| findKeyword(statement, "crack") >=0 
				|| findKeyword(statement, "vape") >=0 
				|| findKeyword(statement, "drug abuse") >=0 
				|| findKeyword(statement, "weed") >=0 
				|| findKeyword(statement, "vaping") >=0 
				|| findKeyword(statement, "smoking") >=0)
		{
			response = "Sounds interesting. Tell me some more details.";
		}
		
		//used for if user enters bye which returns them back to menu screen
		else if (findKeyword(statement, "Bye") >= 0)
		{
			System.out.println("Bye!");
			String[] args = new String[0];
		    ChatBotRunner.main(args);
		}


		//Response of transforming "I want to" style statements
		else if (findKeyword(statement, "I need to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}	
		
		else if (findKeyword(statement, "I need help with", 0) >= 0)
		{
			response = transformHelp(statement);
		}
		else if (findKeyword(statement, "Can you help with", 0) >= 0)
		{ 
			response = transformHelp2(statement);
		}
		
		
		//if nothing fits this method generates random responses
		else
		{
			response = getRandomResponse();
		}
		return response;
	}
	
	//methods behind the I want to style statements  
	private String transformHelp(String statement)
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
		int psn = findKeyword (statement, "I need help with", 0);
		String restOfStatement = statement.substring(psn + 16).trim();
		return "Yes! I can definitely help you with" + " " + restOfStatement +"."+ " " + "Tell me more or ask for some of my advice.";
	}
 
	private String transformHelp2(String statement)
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
		int psn = findKeyword (statement, "Can you help with", 0);
		String restOfStatement = statement.substring(psn + 17).trim();
		return "Yes! I can definitely help you with" + " " + restOfStatement +"."+" "+ "Tell me more or ask for some of my advice.";
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
		return "Why do you want to " + restOfStatement + "?";
	}

	private String transformIWantStatement(String statement)
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
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Do you think you would be happy if you had " + restOfStatement + "?";
	}
	
	//Searches web for web results for drug abuse
	//Sourced code from various online resources
	private String searchWeb() throws Exception
	{
		 Desktop desktop=Desktop.getDesktop();
			desktop.browse(new URI("https://www.google.com/search?q=drug+abuse&oq=drug+abuse"));
			return "Loading browser..."+ "\n"+ "Loading site...";	
	}
	
	
	//finds keyword in the user input
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
	
	//Methods used to get a random response 
	//Finds a keyword
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	//Gets a random fact from array
	private String getRandomFact()
	{
		Random r = new Random ();
		{	
			return randomFact [r.nextInt(randomFact.length)];
		}
	}
	//Gets random advice from array
	private String getRandomAdvice()
	{
		Random r = new Random ();
		{	
			return randomAdvice [r.nextInt(randomAdvice.length)];
		}
	}
	//Gets a random question for the quiz
	private String getRandomQuestion()
	{
		Random r = new Random ();
		{	
			return randomQuestion [r.nextInt(randomQuestion.length)];
		}
	}
	//Gets an expression for when the user gets the question wrong
	private String falseResponses()
	{
		Random r = new Random ();
		{	
			return falseResponses [r.nextInt(falseResponses.length)];
		}
	}
	//Gets an expression for when the user gets the question correct
	private String trueResponses()
	{
		Random r = new Random ();
		{	
			return trueResponses [r.nextInt(trueResponses.length)];
		}
	}
	//Gets a joke
	private String getRandomJoke()
	{
		Random r = new Random ();
		{	
			return randomJoke [r.nextInt(randomJoke.length)];
		}
	
	}
	//Gets a random greeting
		private String getRandomGreeting ()
		{
			Random r = new Random ();
			if (emotion == 0)
			{	
				return randomGreeting [r.nextInt(randomGreeting.length)];
			}
			if (emotion < 0)
			{	
				return randomGreeting [r.nextInt(randomGreeting.length)];
			}	
			return randomGreeting [r.nextInt(randomGreeting.length)];
		}
	
	//Gets a random response when nothing fits
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
	
	//Array values/strings for random responses
	private String [] randomNeutralResponses = {"Tell me more...",
			"Hmmm.",
			"Do you really think so?",
			"You don't say.",
			"I'm listening.",
			"Go on...",
			"Interesting...",
			"Uh-huh.",			    
			"Can you repeat that again?",
			"Do you need advice? Respond with 'advice'.",
			"Want a fact? Respond with 'fact'.",
			"Want to test your knowledge? Respond with 'quiz'.",
	};    
	private String [] randomAngryResponses = {"...", "Hmph", ">:(", ":("};
	private String [] randomHappyResponses = {"Great!", "Fantastic!", "Ok!", "Nice!", ":D", ":)"};
	private String [] randomFact = {"A hotline that you can call is 1-800-662-HELP.", 
			"Drugs abuse caused over 307,400 deaths in 2015. You don't want to be one of them!", 
			"Drug abuse can change your brain chemistry :O",
			"Over $190 billion goes towards drug abuse. Imagine what we can do with all that money!",
			"Over 20 million Americans suffer from some form of drug addiction, with a large number of them suffering from drug abuse.",
			"Drug abuse and addiction is a chronic, relapsing, compulsive disorder that often requires formal treatment, and may call for multiple courses of treatment."};
	private String [] randomAdvice = {"Remind yourself of the reasons why you want to change when considering quitting.", 
									"Find a hobby!",
									"Find something that you like to do.",
									"Go to a rehab facility, where you can meet others with similar struggles.",
									"Join a support group and relate to others with similar struggles.",
									"Ask for the support of family or friends in your recovery from drugs.", 
									"Don't hang with friends who use drugs.",
									"Avoid being a statistic, seek help today!"};
	private String [] randomQuestion = {"27 million Americans abuse drugs.", 
			"Drug abuse cause over 300,000 deaths each year in the United States.", 
			"Drug abuse can change your brain chemistry.",
			"Drugs abuse caused over 307,400 deaths in 2015.",
			"Over $190 billion goes towards drug abuse.",
			"Tobacco is the most abused drug.",
			"Drug abuse can have an effect on one's physical health, mental health, and overall well-being."};
	private String [] falseResponses = {"D'oh!", "Stumped?", "Ouch.", "False :("};
	private String [] trueResponses = {"Correct!", "Wow!", "Excellent!"};
	private String [] randomGreeting = {"G'day mate!", "Hello!", "What's up!", "Howdy!", "Sup!"};
	private String [] randomJoke = {"Cocaine is never a solution. Unless you dissolve it in water, of course.",
									"I saw a driver texting and driving. It made me so mad I threw my beer at him.",
									"Thanks to autocorrect, 1 in 5 children will be getting a visit from Satan this Christmas.",
									"Autocorrect has become my worst enema.",
									"Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all."};
	}
