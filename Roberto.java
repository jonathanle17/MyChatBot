import java.util.Random;
/**
 * Write a description of class Roberto here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Roberto
{
    // instance variables - replace the example below with your own
   public String getGreeting()
   {
       return "Hello, my name is Roberto Clemente. Nice to meet you.";
   }
   
   /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (statement.length() == 0)
        {
            response = "Talk to me, I talk back.";
        }

        else if (findKeyword(statement, "no") >= 0)
        {
            response = "Let me talk to you, what's your name?";
        }
        
        else if (findKeyword(statement, "My name is", 0) >= 0)
        {
            response = transformIWantToStatement(statement);
        } 
        else if (findKeyword(statement, "hello") >= 0
                || findKeyword(statement, "hey") >= 0
                || findKeyword(statement, "hi") >= 0)
        {
            response = "Hello, what's your name?";
        }
        else if (findKeyword(statement, "home runs") >= 0)
                
        {
            response = "I have 240 career home runs.";
        }
        
        else if (findKeyword(statement, "team") >= 0)
        {
            response = "I played for the Pittsburgh Pirates for 18 years.";
        }
        
        else if (findKeyword(statement, "position") >= 0)
        {
            response = "I caught flyballs in the outfield, primarily from right.";
        }
        
        else if (findKeyword(statement, "planes") >= 0)
                
        {
            response = "I would ";
        }
        
        else if (findKeyword(statement, "famous") >= 0)
        {
            response = "I am the first Latin American MLB player to reach 3,000 hits.";
        }

        // Responses which require transformations
    
        //  Part of student solution
        else if (findKeyword(statement, "I hit", 0) >= 0)
        {
            response = transformIWantStatement(statement);
        }

        else
        {

            // Look for a two word (you <something> me)
            // pattern
            int psn = findKeyword(statement, "you", 0);

            if (psn >= 0
                    && findKeyword(statement, "me", psn) >= 0)
            {
                response = transformYouMeStatement(statement);
            }
            else
            {
                //  Part of student solution
                // Look for a two word (I <something> you)
                // pattern
                psn = findKeyword(statement, "i", 0);

                if (psn >= 0
                        && findKeyword(statement, "you", psn) >= 0)
                {
                    response = transformIYouStatement(statement);
                }
                else
                {
                    response = getRandomResponse();
                }
            }
        }
        return response;
    }
    
    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
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
        int psn = findKeyword (statement, "My name is", 0);
        String restOfStatement = statement.substring(psn + 10).trim();
        return "Tell me more about yourself " + restOfStatement + ".";
    }

    
    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want"
     * @return the transformed statement
     */
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
        int psn = findKeyword (statement, "I hit", 0);
        String restOfStatement = statement.substring(psn + 5).trim();
        return "Is your " + restOfStatement + " better than my .317 though?";
    }
    
    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    private String transformYouMeStatement(String statement)
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
        
        int psnOfYou = findKeyword (statement, "you", 0);
        int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
        
        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        return "What makes you think that I " + restOfStatement + " you?";
    }
    
    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     * @param statement the user statement, assumed to contain "I" followed by "you"
     * @return the transformed statement
     */
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
    

    
    
    /**
     * Search for one word in phrase.  The search is not case sensitive.
     * This method will check that the given goal is not a substring of a longer string
     * (so, for example, "I know" does not contain "no").  
     * @param statement the string to search
     * @param goal the string to search for
     * @param startPos the character of the string to begin the search at
     * @return the index of the first occurrence of goal in statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal, int startPos)
    {
        String phrase = statement.trim();
        //  The only change to incorporate the startPos is in the line below
        int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
        
        //  Refinement--make sure the goal isn't part of a word 
        while (psn >= 0) 
        {
            //  Find the string of length 1 before and after the word
            String before = " ", after = " "; 
            if (psn > 0)
            {
                before = phrase.substring (psn - 1, psn).toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
            }
            
            //  If before and after aren't letters, we've found the word
            if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
                    && ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
            {
                return psn;
            }
            
            //  The last position didn't work, so let's find the next, if there is one.
            psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
            
        }
        
        return -1;
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
    


    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse ()
    {
        Random r = new Random ();
        return randomResponses [r.nextInt(randomResponses.length)];
    }
    
    private String [] randomResponses = {"Interesting, tell me more",
            "Hmmm.",
            "Do you really think so?",
            "You don't say."
    };
    
}
   
