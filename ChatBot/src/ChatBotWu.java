import java.util.Random;
import java.util.Scanner;

/* This version:
 * @author Mr. Levin - Reference Code 
 		   Evan Wu - Modified Code
 * @version October 2017
 */
public class ChatBotWu
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;
	
	
	public String getGreeting()
	{
		System.out.println("DRUG ABUSE CHAT");
		return "Welcome to the Drug abuse chat! You can tell me what you want, or I can listen to you, take a short quiz to test your knowledge, or give you facts/advice.";
	}
	Scanner input = new Scanner(System.in);
	


	@SuppressWarnings("resource")
	public String getResponse(String statement)
	{
		String response = "";
		
		if (statement.length() == 0)
		{
			response = "Please tell me something so I can help you.";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Please tell me what you need help with.";
                	emotion--;
		}
		
		if (findKeyword(statement, "yes") >= 0)
		{
			response = "Tell me more, I'm listening";
				
		}
		

		else if (findKeyword(statement, "friend") >= 0)
		{
			response = "So did your friend introduce you to drugs?";
                	emotion--;
		}
		
		
		//quiz
		else if (findKeyword(statement, "question") >= 0||
				findKeyword(statement, "test") >= 0 ||
				findKeyword(statement, "knowledge") >= 0 ||
				findKeyword(statement, "quiz") >= 0)
		{
			response = "Do you want to test your knowledge? Respond with quizyes or quizno";
                	emotion++;
		}
		else if (findKeyword(statement, "quizyes") >= 0)
		{
			response = "Are you ready? Respond with ready or notready.";
                	emotion++;
		}
		else if (findKeyword(statement, "ready") >= 0)
		{
			response = "True or false?" + " " + getRandomQuestion();
		}
		else if (findKeyword(statement, "true") >= 0)
		{
			response = trueResponses() + " " + "Ready for another question? Or do you want to keep your 100% correct rate? Respond with ready or exit";
		}
		
		else if (findKeyword(statement, "false") >= 0)
		{
			response = falseResponses() + " " + "Ready for another question? Or do you want to quit? Respond with ready or exit";
		}
		
		else if (findKeyword(statement, "notready") >= 0)
		{
			response = "Hurry up! I'm waiting for you.";
                	emotion++;
		}
		
		else if (findKeyword(statement, "quizno") >= 0
				|| findKeyword(statement, "exit") >= 0)
		{
			response = "ok :(";
                	emotion--;
		}
		
		
		
		
		
		else if (findKeyword(statement, "help") >= 0)
		{
			response = "That's what I'm here for! Ask away!";
		}
		
	
		else if (findKeyword(statement, "advice") >= 0 )
		{
			response = getRandomAdvice();
                	emotion++;
		}
		

		else if (findKeyword(statement, "fact") >= 0)
		{
			response = "Heres a fact:" + " " + getRandomFact();
			
	                  emotion++;
	    }
		

		
		else if (findKeyword(statement, "heroin") >=0 
				|| findKeyword(statement, "marijuana") >= 0 
				|| findKeyword(statement, "cocaine") >=0 
				|| findKeyword(statement, "crack") >=0 
				|| findKeyword(statement, "vape") >=0 
				|| findKeyword(statement, "weed") >=0 
				|| findKeyword(statement, "vaping") >=0 
				|| findKeyword(statement, "smoking") >=0)
		{
			response = "Tell me more about it.";
			emotion++;
		}
		
		else if (findKeyword(statement, "Bye") >= 0)
		{
			System.out.println("Bye!");
			ChatBotRunner chatbot1 = new ChatBotRunner();
			String[] args = new String[0] ;
		    chatbot1.main(args);
		}


		// Response transforming I want to statement
		else if (findKeyword(statement, "I need help with", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}	
		else
		{
			response = getRandomResponse();
		}
		
		return response;
	}
	
	
	
	


	public void getQuiz() {
		System.out.println("DRUG ABUSE CHAT");
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
		return "Would you really be happy if you had " + restOfStatement + "?";
	}
	
	
	
	@SuppressWarnings("unused")
	private String transformIYouStatement(String statement)
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
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);
		
		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
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
	
	private String getRandomFact()
	{
		Random r = new Random ();
		{	
			return randomFact [r.nextInt(randomFact.length)];
		}
	}

	private String getRandomAdvice()
	{
		Random r = new Random ();
		{	
			return randomAdvice [r.nextInt(randomAdvice.length)];
		}
	}
	
	private String getRandomQuestion()
	{
		Random r = new Random ();
		{	
			return randomQuestion [r.nextInt(randomQuestion.length)];
		}
	}
	
	private String falseResponses()
	{
		Random r = new Random ();
		{	
			return falseResponses [r.nextInt(falseResponses.length)];
		}
	}

	private String trueResponses()
	{
		Random r = new Random ();
		{	
			return trueResponses [r.nextInt(trueResponses.length)];
		}
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
	
	private String [] randomNeutralResponses = {"Tell me more...",
			"Hmmm.",
			"Do you really think so?",
			"You don't say.",
			"I'm listening.",
			"Go on...",
			"Can you repeat that again?"
	};
	private String [] randomAngryResponses = {"...", "Hmph", ">:(", ":("};
	private String [] randomHappyResponses = {"Great!", "Fantastic!", "ok!", "Nice!", ":D", ":)"};
	private String [] randomFact = {"A hotline that you can call is 1-800-662-HELP.", 
			"Drugs abuse caused over 307,400 deaths in 2015. You don't want to be one of them!", 
			"Drug abuse can change your brain chemistry :O",
			"Over $190 billion goes towards drug abuse. Imagine what we can do with all that money!",
			"Over 20 million Americans suffer from some form of drug addiction, with a large number of them suffering from drug abuse."};
	private String [] randomAdvice = {"Remind yourself of the reasons you want to change.", 
									"Find a hobby!",
									"Find something that you like to do.",
									"Go to a rehab facility, where you can meet others with similar struggles.",
									"Ask for the support of family or friends in your recovery from drugs. ", 
									"Don't hang with friends who use drugs."};
	private String [] randomQuestion = {"27 million Americans abuse drugs.", "Drug abuse cause over 300,000 deaths each year."};
	private String [] falseResponses = {"D'oh!", "Stumped?", "Ouch.", "False :("};
	private String [] trueResponses = {"Correct!", "Wow!", "Excellent!"};
	}
