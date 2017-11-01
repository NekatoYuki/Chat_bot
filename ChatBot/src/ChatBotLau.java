import java.util.Random;

public class ChatBotLau {

	//A program to carry out a conversation with its user
	
	//Emotion level will alter the way this bot responds. Emotion level can get more positive or negative over time

		int emotion = 0;

		//Default emotion level starts out neutral
		
		public String getGreeting()

		//Initiating contact with user 
			
		{

			return "Hi, what is up?";

		}


		public String getResponse(String statement)

		//Return response based on the rules given 
			
		{

			String response = "";

			if (statement.length() == 0)

			{

				response = "Talk to me, please.";

			}
				
			//In case the user does not respond

			else if (findKeyword(statement, "no") >= 0)

			{

				response = "Why so negative?";

		        emotion--;

			}
			
			else if (findKeyword(statement, "nah") >= 0)
				
			{
				
				response = "Why so negative?";
				
				emotion--;
				
			}

			//Minus one emotion level if the user says "no" or "nah"
				

			else if (findKeyword(statement, "I won't") >= 0)

			{

				response = "You better not";

				emotion++;

			}

			//Plus one emotion level if the user says "I won't"
			
			
			else if (findKeyword(statement, "friend") >= 0)
				
			{
				
				response = "Did this friend of yours introduce you to drugs?";
				
				emotion--;
				
			}
			
			//Minus one emotion level if the user mentions "friend"
			
			
			else if (findKeyword(statement, "information") >= 0)
				
			{
				
				response = "Sure! Just name the drug and I'll give you some information about it!";
				
				emotion++;
				
			}
			
			//Plus one emotion level if the user wants "information"
			

			else if (findKeyword(statement, "I want", 0) >= 0)
					
			{
				
				response = "Are you sure that's a good idea?";

			}

			//Make the user question his/her reasoning
				
				
			else if (findKeyword(statement, "Heroin",0) >= 0)

			{

			response = "Heroin is a destructive opioid and can lead to seizures, psychosis, and hallucinations when it is abused";

			}	

			//Gives information about Heroin
				
				
			else if (findKeyword(statement, "Cocaine",0) >= 0)
					
			{
				
				response = "Cocaine is a very dangerous stimulant even when taken in small amounts.  It induces euphoria, increases blood pressure, and accelerates the heart rate.";
					
			}
				
			//Gives information about Cocaine
				
				
			else if (findKeyword(statement, "Crack",0) >= 0)
				
			{
					
				response = "This is a potent form of cocaine and will suddenly create an intense euphoric sensation for a short while.";
					
			}
				
			//Gives information about Crack
				
			
			else if (findKeyword(statement, "Hallucinogens",0) >= 0)
					
			{
					
				response = "Be careful. Hallucinogens make users feel, see, and hear things that are not real";
					
			}
				
			//Gives information about Hallucinogens
				
				
			else if (findKeyword(statement, "Amphetamines",0) >= 0)
					
			{
					
				response = "Amphetamines accelerates the user's bodily and mental functions and can cause manic periods of distress in abusers.";
							
			}
				
			//Gives information about Amphetamines
				
				
			else if (findKeyword(statement, "Marijuana",0) >= 0)
					
			{
				
				response = "Many people begin using it as a recreational drug in social situations but also carries a significant cancer risk";
					
			}
				
			//Gives information about Marijuana
				
				
			else if (findKeyword(statement, "Alcohol",0) >= 0)
					
			{
					
				response = "Abusing alcohol can cause psychological, physical, and social breakdowns. More importantly, it can lead to the destruction of relationships, friendships, and marriages";
					
			}
				
			//Gives information about Alcohol
				
				
			else if (findKeyword(statement, "Inhalants",0) >= 0)
					
			{
					
				response = "Overdose can result in extreme confusion, blackout, coma and convulsions. It can also cause an erratic and accelerated heart beat, which can lead to heart failure and death";
					
			}
				
			//Gives information about Inhalants
				
				
			else if (findKeyword(statement, "Prescription Drugs",0) >= 0)
					
			{
					
				response = "Prescription drug abuse includes everything from taking a friend's prescription painkiller for your backache to snorting or injecting ground-up pills to get high. This abuse will get you highly addicted, despite of the negative consequences";
					
			}
				
			//Gives information about Prescription Drugs
			
			else if (findKeyword(statement, "Bye",0) >= 0)
				
			{
				
				response = "Okay! Have a nice day!";
				
			}
				
			else if(findKeyword(statement, "I want to", 0) >= 0)
			{
				
				response = transform_into_want_to_statement(statement);	
				
			}
			
			
			else if (findKeyword(statement, "I want", 0) >= 0)
			{
				
				response = transform_into_would_you_statement(statement);
				
			}
			
			else

			{

				response = getRandomResponse();

			}

			//Room for other responses

			return response;
			
		}
		
		
		private String transform_into_want_to_statement(String statement)
		{
			
			//Takes "I want to <something>" into "why do you want to <something>?"
			
			statement = statement.trim();
			String lastChar = statement.substring(statement.length() -1);
			
			if(lastChar.equalsIgnoreCase(".")) 
			{
				
				statement = statement.substring(0,statement.length() -1);		
				
			}
			
			//Removes period
			
			int keyword = findKeyword(statement, "I want to", 0);
			String rest_of_statement = statement.substring(keyword +9).trim();
			
			return "Why do you want to " + rest_of_statement + "?";
			
		}
		
		
		private String transform_into_would_you_statement(String statement)
		{
			
			//Takes "I want <something> into "Would you really be happy if you had <something>?"
			
			statement = statement.trim();
			String lastChar = statement.substring(0, statement.length() -1);
			if(lastChar.equalsIgnoreCase("."))
				
				{
				
					statement = statement.substring(0, statement.length() -1);
					
				}
			
			//Removes period 
			
			int keyword = findKeyword(statement, "I want", 0);
			String rest_of_statement = statement.substring(keyword + 6).trim();
			
			return "Would you really be happy if you had " + rest_of_statement + "?";
			
		}
		
		
		private String transform_into_why_do_you(String statement)
		{
			
			//Takes "I <something> you" statement and transforms it into "why do you <something> me?"
			
			statement = statement.trim();
			String lastChar = statement.substring(statement.length() -1);
			
			if(lastChar.equalsIgnoreCase("."))
			{
				
				statement = statement.substring(0, statement.length() -1);
				
			}
			
			//Removes period
			
			int keywordI = findKeyword (statement, "I", 0);
			int keywordYou = findKeyword (statement, "you", keywordI);
			
			String rest_of_statement = statement.substring(keywordI + 1, keywordYou).trim();
			
			return "Why do you " + rest_of_statement + "me?";
		}
		
	
		private int findKeyword(String statement, String goal, int startPos)
		{
			
			String phrase = statement.trim().toLowerCase();
			goal = goal.toLowerCase();
			
			int phrase_index = phrase.indexOf(goal, startPos);
			
			while(phrase_index >= 0)
			{
				
				String before = " ", after = " ";
				if(phrase_index > 0)
				{				
					
					before = phrase.substring(phrase_index - 1, phrase_index);
					
				}
				
				if(phrase_index + goal.length() < phrase.length())
				{
					
					after = phrase.substring(phrase_index + goal.length(), phrase_index + goal.length() + 1);
					
				}
				
				if(((before.compareTo("a") < 0 || (before.compareTo("z") > 0)) && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))))
				{
					
					return phrase_index;
				}
				
				phrase_index = phrase.indexOf(goal, phrase_index + 1);
			}
			
			return - 1;
			
		}
		
		//Find string of len 1 before and after the word
		//before and after != letters, word has been found
		
		
		private int findKeyword(String statement, String goal)
		{
			
			return findKeyword(statement, goal, 0);
			
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
		
		
		private String [] randomNeutralResponses = {"Interesting, tell me more.",
				"Hmmm.",
				"Is that so?",
				"What other things would you like to know?",
				"Oh wow!",
				"So, would you like to go for a walk?",
				"Keep going. I'm listening."
		};
		
		
		private String [] randomAngryResponses = {"Drugs can cause permanent damage.",
				"It's impossible to maintain healthy relationships while doing drugs",
				"Grrr...",
				"Hmph!"		
		};
		
		
		private String [] randomHappyResponses = {"Fantastic!", 
				"Amazing job!",
				"Neat!",
				"I believe in you!",
				"You can do it!"
		};
		
	}



		

		
