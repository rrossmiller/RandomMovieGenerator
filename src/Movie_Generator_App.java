import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Movie_Generator_App
{
	private static Scanner in;
	private static Random rng;
	
	// initialize String arrays to hold movies by categories
	private static String[] action;
	private static String[] drama;
	private static String[] think;
	private static String[] light ;
	private static String[] comedy;
	private static String[] all;
			
	public static void main(String[] args)
	{
		// initialize scanner for user input
		in = new Scanner(System.in);
		String prompt;
		String userChoice = "1";
		String[] answers;
		
		// fill the arrays with movies
		createMovieLists("Movie_list.txt");

		
		System.out.println("Welcome to the Movie Generator in Java!\n");
		
		while(!userChoice.equals("0"))
		{
			prompt = "Generate (R)andom Movie, (S)how movies, or (0) to quit: ";
			answers = new String[]{"R", "S", "0"};
			userChoice = askUser(prompt, answers);
			
			if (userChoice.equals("R"))
			{
				// generate random movie
				prompt = "Categories to choose from: \n\t(A)ction \n\t(D)rama \n\t(T)hink \n\t(L)ight \n\t(C)omedy \n\t(U)All \n--> ";
				answers = new String[]{"A", "D", "C", "L", "T", "U", "0"};
				userChoice = askUser(prompt, answers);
				
				switch(userChoice)
				{
				case "A":
					System.out.println(randMovie(action));
					break;
					
				case "D":
					System.out.println(randMovie(drama));
					break;
					
				case "C":
					System.out.println(randMovie(comedy));
					break;
					
				case "L":
					System.out.println(randMovie(light));
					break;
					
				case "T":
					System.out.println(randMovie(think));
					break;
					
				case "U":
					System.out.println(randMovie(all));
					break;
					
				}
				
			}
			
			else if (userChoice.equals("S"))
			{
				prompt = "Categories to choose from: \n\t(A)ction \n\t(D)rama \n\t(T)hink \n\t(L)ight \n\t(C)omedy \n\t(U)All \n--> ";
				answers = new String[]{"A", "D", "C", "L", "T", "U", "0"};
				userChoice = askUser(prompt, answers);
				
				switch(userChoice)
				{
				case "A":
					showMovies("Action", action);
					System.out.println("\n");
					break;
					
				case "D":
					showMovies("Drama", drama);
					System.out.println("\n");
					break;
					
				case "C":
					showMovies("Comedy", comedy);
					System.out.println("\n");
					break;
					
				case "L":
					showMovies("Light", light);
					System.out.println("\n");
					break;
					
				case "T":
					showMovies("Think", think);
					System.out.println("\n");
					break;
					
				case "U":
					showMovies("Action", action);
					System.out.println();
					showMovies("Drama", drama);
					System.out.println();
					showMovies("Comedy", comedy);
					System.out.println();
					showMovies("Light", light);
					System.out.println();
					showMovies("Think", think);
					System.out.println("\n");
					break;
				}
			}
			
			else if (userChoice.equals("0"))
				continue;
			else
			{
				// didn't recognize method, ask again
				System.out.println("Didn't recognize: " + userChoice + ".");
				System.out.println();
			}
		}
			
		System.out.println("Goodbye.");
	}
	
	/**
	 * The createMovieLists method parses the text file containing movies organized
	 * by category and puts them in arrays
	 * @param fileName, the text file containing the movies
	 */
	public static void createMovieLists(String fileName)
	{
		String line;
		String[] lineArray;
		String category;
		
		// try to read the file
		try
			{
			// initialize the scanner to scan the file
			in = new Scanner(new File(fileName));
		
			// while there are still lines to be read
			while (in.hasNextLine())
			{
				line = in.nextLine();
				if(line.length() > 0)
				{
					lineArray = line.split(":");
					category = lineArray[0].trim().toLowerCase();
					
					if(category.equals("action"))
					{
						action = lineArray[1].split(",");
						for (String s : action)
							s.trim();
					}
					else if(category.equals("drama"))
					{
						drama = lineArray[1].split(",");
						for (String s : action)
							s.trim();
					}
					else if(category.equals("think"))
					{
						think = lineArray[1].split(",");
						for (String s : action)
							s.trim();
					}
					else if(category.equals("comedy"))
					{
						comedy = lineArray[1].split(",");
						for (String s : action)
							s.trim();
					}
					else if(category.equals("light"))
					{
						light = lineArray[1].split(",");
						for (String s : action)
							s.trim();
					}
				}
			}

			// count the total number of movies
			int numMovies = action.length + drama.length + think.length + comedy.length + light.length;
			all = new String[numMovies];
			
			// add the movies from other arrays to all
			int p = 0;
			for(int i = 0 ; i < action.length ; i++)
			{
				all[p] = action[i];
				p++;
			}
			for(int i = 0 ; i < drama.length ; i++)
			{
				all[p] = drama[i];
				p++;
			}
			for(int i = 0 ; i < think.length ; i++)
			{
				all[p] = think[i];
				p++;
			}
			for(int i = 0 ; i < comedy.length ; i++)
			{
				all[p] = comedy[i];
				p++;
			}
			for(int i = 0 ; i < light.length ; i++)
			{
				all[p] = light[i];
				p++;
			}

			in.close();
		
		} catch (FileNotFoundException e) {
			System.out.println("Unable to read from file: " + fileName);
		}
	}

	/**
	 * the askUser method is used to get user input
	 * @param prompt: the question to ask the user
	 * @param answers: the acceptable answers
	 * @return: the String the user inputs (if it is acceptable)
	 */
	public static String askUser(String prompt, String[] answers)
	{
		// create a scanner to get user input
		in = new Scanner(System.in);
		
		// initialize boolean to control while and String to store user's choice
		boolean ans = true;
		String userChoice = "";
		
		// while the user hasn't input a valid choice...
		while(ans)
		{
			// show the prompt and get the user's input
			System.out.print(prompt);
			userChoice = in.nextLine();
			
			// check the user's input
			for(String s : answers)
			{ 	// if the input if valid, start exiting the loop
				if(s.equals(userChoice.toUpperCase()))
				{
					ans = false;
				}
			}
			// if the answer was invalid, say so
			if (!ans)
				continue;
			else
				System.out.println(userChoice + " --> not recognized.\n");
		}
		return userChoice.toUpperCase();
	}

	public static String randMovie(String[] category)
	{
		rng = new Random();
		int index = rng.nextInt(category.length);
		return category[index].trim();
	}
	
	public static void showMovies(String type, String[] category)
	{
		System.out.println(type+": ");
		
		int x = 0;
		for(int i = 0 ; i < category.length ; i++)
		{
			if(x == 9)
			{
				x = 0;
				System.out.println();
			}
			
			System.out.print(category[i] + ", ");
			x++;
		}
	}
	
}
