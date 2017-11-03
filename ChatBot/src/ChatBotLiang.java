 import java.awt.Desktop;
import java.net.URI;
import java.util.Random;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Mr. Levin
 * Stanley Liang - Modified Code
 * @version October 2017
 */
public class ChatBotLiang
{ 
	int emotion = 0;
	public String getGreeting()
	{
		return "Welcome! Do you want to know some drug awareness facts? Type mm to return to the main menu.";
	}
	public String getResponse(String statement)
	{
		String response = "";
		
		if (statement.length() == 0)
		{
			response = "Say something, please.";
		}
		
		else if (findKeyword (statement, "mm",0) >= 0)
		{
			System.out.println("Returning to main menu...");
			String[] args = new String[0];
		    ChatBotRunner.main(args);
		}
		
		else if (findKeyword(statement, "Hi") >= 0)
		{
			response = getRandomGreetingResponses();
		}
		
		else if (findKeyword(statement, "Hello") >= 0)
		{
			response = getRandomGreetingResponses();
		}

		else if (findKeyword(statement, "No") >= 0)
		{
			response = "Why not?";
                	emotion--;
		}
		
		else if (findKeyword(statement, "Yes") >= 0)
		{
			response = "Please type afact for a drug awareness fact.";
			emotion++;
		}
		
		//Will give a random fact from the facts list.
		else if (findKeyword(statement, "afact") >= 0)
		{
			response = getRandomFactResponses();
		}
		
		//Will ask the user if they want to answer a random question based on the facts provided.
		else if (findKeyword(statement, "question") >= 0)
		{
			response = "Ready for a quiz? Respond with aquestion or no. (Enter aquestion everytime you want a question.)";
			emotion++;
		}
		
		//Will start the quiz with random questions.
		else if (findKeyword(statement, "aquestion") >= 0)
		{
			response = getRandomQuestion();
			emotion++;
			if (findKeyword(statement, "1/5") >= 0)
			{
				response = getCorrectAnswer();
			}

			else if (findKeyword(statement, "3") >= 0)
			{
				response = getCorrectAnswer();
			}
			
			else if (findKeyword(statement, "over-the-counter") >= 0)
			{
				response = getCorrectAnswer();
			}
			
			else if (findKeyword(statement, "false") >= 0)
			{
				response = getCorrectAnswer();
			}
			
			else if (findKeyword(statement, "false") >= 0)
			{
				response = getCorrectAnswer();
			}
			
			else if (findKeyword(statement, "true") >= 0)
			{
				response = getWrongAnswer();
			}
			
			else if (findKeyword(statement, "14") >= 0)
			{
				response = getCorrectAnswer();
			}
			
			else if (findKeyword(statement, "35") >= 0)
			{
				response = getCorrectAnswer();
			}
			
			else if (findKeyword(statement, "16,500") >= 0)
			{
				response = getCorrectAnswer();
			}
		}

		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}	
		
		else if (findKeyword(statement, "I think",0) >= 0)
		{
			response = transformIThinkThatStatement(statement);
		}
		
		else if (findKeyword (statement, "you",0) >= 0)
		{
			response = transformIYouStatement(statement);
		}
		
		else if (findKeyword (statement, "I will",0) >= 0)
		{
			response = transformIWillStatement(statement);
		}
		
		else if (findKeyword (statement, "I will not",0) >= 0)
		{
			response = transformIWillNotStatement(statement);
		}
		
		else if (findKeyword (statement, "I do not",0) >= 0)
		{
			response = transformIDoNotStatement(statement);
		}
		
		else if (findKeyword (statement, "I do",0) >= 0)
		{
			response = transformIDoStatement(statement);
		}
		
		else if (findKeyword (statement, "source",0) >= 0 || findKeyword (statement, "works cited",0) >= 0)
		{
			response = workCited();
		}
		
		else
		{
			response = getRandomResponse();
		}
		
		return response;
	}
	
	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
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
		return "Why do you want to " + restOfStatement + "?";
	}

	
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Do you really want " + restOfStatement + "?";
	}
	
	
	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);
		
		String restOfStatement = statement.substring(psnOfYou + 1, statement.length()).trim();
		return "Why do you " + restOfStatement + " me?";
	}
	
	/**
	 * This will take a statement with "I think <something>" and transforms it into
	 * "Why do you think <something>?"
	 * @param statement the user statement, assumed to contain "I think" followed by something
	 * @return the transformed statement
	 */
	private String transformIThinkThatStatement(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfThink = findKeyword (statement, "think", psnOfI);
		int psnOfThat = findKeyword (statement, "that", psnOfThink);
		
		String restOfStatement = statement.substring(psnOfThat + 1, statement.length()).trim();
		return "Why do you think that " + restOfStatement + "?";
	}
	
	/**
	 * This will take a statement with "I will not <something>" and transform it into a
	 * "Why will you not <something>?"
	 * @param statement the user statement, assumed to contain "I will not" followed by something
	 * @return the transformed statement
	 */
	private String transformIWillNotStatement(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfWill = findKeyword (statement, "will", psnOfI);
		int psnOfNot = findKeyword (statement, "not", psnOfWill);
		
		String restOfStatement = statement.substring(psnOfNot + 4, statement.length()).trim();
		return "Why will you not " + restOfStatement + "?";
	}
	
	
	/**
	 * This will take a statement with "I will <something>" and transform it into a
	 * "Why will you <something>?"
	 * @param statement the user statement, assumed to contain "I will" followed by something
	 * @return the transformed statement
	 */
	private String transformIWillStatement(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfWill = findKeyword (statement, "will", psnOfI);
		
		String restOfStatement = statement.substring(psnOfWill + 4, statement.length()).trim();
		return "Why will you " + restOfStatement + "?";
	}
	
	/**
	 * This will take a statement with "I do not <something>" and transform it into a
	 * "Why do you not <something>?"
	 * @param statement the user statement, assumed to contain "I do not" followed by something
	 * @return the transformed statement
	 */
	private String transformIDoNotStatement(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfDo = findKeyword (statement, "do", psnOfI);
		int psnOfNot = findKeyword (statement, "not", psnOfDo);
		
		String restOfStatement = statement.substring(psnOfNot + 3, statement.length()).trim();
		return "Why do you not " + restOfStatement + "?";
	}
	
	/**
	 * This will take a statement with "I do <something>" and transform it into a
	 * "Why do you <something>?"
	 * @param statement the user statement, assumed to contain "I do" followed by something
	 * @return the transformed statement
	 */
	private String transformIDoStatement(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfDo = findKeyword (statement, "do", psnOfI);
		
		String restOfStatement = statement.substring(psnOfDo + 1, statement.length()).trim();
		return "Why do you " + restOfStatement + "?";
	}

	
	
	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
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
	
	//Takes you to the source of all the information for this chat bot
	private String workCited(String[]) throws Exception
	{
			//Creates a desktop object
		 Desktop d = Desktop.getDesktop();
		 	//Opens the URL
			d.browse(new URI("http://www.preventionlane.org/national-drug-facts-awareness-week"));
			return "Opening Source Page";
			
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
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
	
	private String getCorrectAnswer()
	{
		Random r = new Random ();
		{	
			return correctAnswer [r.nextInt(correctAnswer.length)];
		}
	}
	
	private String getWrongAnswer()
	{
		Random r = new Random ();
		{	
			return wrongAnswer [r.nextInt(wrongAnswer.length)];
		}
	}
	
	private String getRandomGreetingResponses()
	{
		Random r = new Random ();
		{	
			return randomGreetingResponses [r.nextInt(randomGreetingResponses.length)];
		}
	}
	
	private String getRandomFactResponses()
	{
		Random r = new Random ();
		{	
			return randomFactResponses [r.nextInt(randomFactResponses.length)];
		}
	}
	
	private String getRandomQuestion()
	{
		Random r = new Random ();
		{	
			return randomQuestion [r.nextInt(randomQuestion.length)];
		}
	}
	
	//Random responses from the chat bot depending on what the user says
	private String [] randomNeutralResponses = {"Interesting, tell me more",
			"Hmmm, is there more?",
			"Do you really think so?"
	};
	private String [] randomAngryResponses = {"Hmph", "Grrrr", "..."};
	private String [] randomHappyResponses = {"Yay", "Great", "Amazing", "Fantastic"};
	private String [] randomFactResponses = {"Death rates from drug overdose has triples since 1990",
			"1/5 of teens abuse prescription drugs",
			"After marijuana, prescription and over-the-counter medications are most commonly abused amongst high school seniors",
			"Teenagers are more likely to use E-Cigarettes than regular cigarettes.",
			"Alcohol and cigarette use has dropped significantly amongst teens in the past 2 decades.",
			"According to a study in 2014 on 41,511 students grades 8 to 12, only 14% of high school seniors view E-Cigarettes as harmful.",
			"According to a study in 2014 on 41,511 students grades 8 to 12, 35% of high school seniors have used marijuana.",
			"Over 16,500 people died from painkiller overdoses in 2010."
	};
	private String [] randomGreetingResponses = {"Hi!", "Hello!"};
	private String [] randomQuestion = {"How many times has drug overdose cases gone up since the 1990's?",
			"How many teens abuse prescription drugs?",
			"What are the second most commonly abused drugs amongst high school students?",
			"True or false? Teenagers are more likely to use cigarettes than E-Cigarettes.",
			"True or false? Alcohol and cigarette use amongst teenagers have been on the rise for the past 2 decades.",
			"What percentage of high school seniors view E-Cigarettes as harmful?",
			"What percentage of high school seniors have used marijuana?",
			"Over how many people died from painkiller overdoses in 2010?"
	};
	private String [] correctAnswer = {"Correct!", "Yes!"};
	private String [] wrongAnswer = {"Nope!","Incorrect!", "Try again!"};
}
