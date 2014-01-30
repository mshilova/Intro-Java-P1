import java.util.*;	// Scanner for input

public class P1
{
	public static void main(String [] args)
	{
		final int MIN = 0;		// minimum
		final double A_PERCENT = .90;	// 90% letter grade A
    final double B_PERCENT = .80;  // 80% letter grade B
    final double C_PERCENT = .70;   //70% letter grade C
    final double D_PERCENT = .60;   //60% letter grade D
		final int MAX_STUDENTS = 10;   //maximum number of students allowed
    final int MAX_SCORE = 100;   // maximum score
    final int MIN_SCORE= 0;      // minimum score
		boolean errFlag = false;	// error flag input scores range check
    char grade;                 // letter grade
		char choice;		    	// repeat loop
    double bestScore = 0.0;     // find best score
		double tmp;		        	// temporary input for scores
		double []scores;		// array of scores
    int [] scoreCounter = {0, 0, 0, 0, 0}; // array of scores for grade counter
    int numScoresAboveAvg = 0;  // number of scores above average
    int numScoresBelowAvg = 0; // number of scores below average
    int numStudents;         // number of students
		Scanner scan = new Scanner(System.in);// read input from keyboard
		String inputStr = null;		// input string reference;
         
    do
    {
      do
      {
        System.out.print("Enter number of students (");
        System.out.print((MIN+1) + "-" + MAX_STUDENTS + "): ");
        numStudents = scan.nextInt();  // assing to integer

        if(numStudents < 1 || numStudents > 10) // check if numStudents is in range
        {
          System.out.println("ERROR: Enter an integer in the range of " + (MIN + 1) + "-" + MAX_STUDENTS + "!");
        }
      } while(numStudents < 1 || numStudents > 10); // repeat while input is incorrect
        
      scores = new double[numStudents];   // allocate array scores

      System.out.print("\nEnter " + numStudents + " scores (");
      System.out.print(MIN_SCORE + "-" + MAX_SCORE + "): ");

      while(errFlag == false)
      {
        // scan through the input for the number of students entered previously
        for(int i = 0; i < numStudents; i++)
        {
          tmp = scan.nextDouble();
          // check that the score is within the acceptable range
          if( tmp >= MIN_SCORE && tmp <= MAX_SCORE){
            scores[i] = tmp; //assign to the array of scores
          } else {
            //unacceptable value sets the error flag to true
            errFlag = true;
          }
        }
        if(errFlag) {
          errFlag = false;
          System.out.println("ERROR: Enter number(s) in the range of " + MIN_SCORE + "-" + MAX_SCORE + "!");
          System.out.print("ERROR: Enter all " + numStudents + " scores again: ");
        } else {
          // Set the error flag to true in order to exit from the outside while loop.
          errFlag = true;
        }
      }
      errFlag = false; // reset the errFlag
     
      //find the best score in the array
      for(int i = 0; i < numStudents; i++)
      {
        if(scores[i] > bestScore)
        {
          bestScore = scores[i];
        }
      }
      //assign a letter grade for each score in the array
      for(int i = 0; i < numStudents; i++)
      {
        if(scores[i] >= (bestScore * A_PERCENT))
         grade ='A';
        else if(scores[i] >= (bestScore * B_PERCENT))
          grade = 'B';
        else if(scores[i] >= (bestScore * C_PERCENT))
         grade = 'C';
        else if (scores[i] >= (bestScore * D_PERCENT))
          grade = 'D';
        else
         grade = 'F';

        //display the score and the grade of each student
        System.out.printf("\nStudent # %d score is %.2f and grade is %c", (i + 1), scores[i], grade);
        
        //counts and accumulates the occurances of grades
        switch(grade)
        {
          case 'A': scoreCounter[0] += 1; break;
          case 'B': scoreCounter[1] += 1; break;
          case 'C': scoreCounter[2] += 1; break;
          case 'D': scoreCounter[3] += 1; break;
          case 'F': scoreCounter[4] += 1; break;
        }
      }
      //display the results of the counter
      System.out.println("\nNumber of A's: " + scoreCounter[0]);
      System.out.println("Number of B's: " + scoreCounter[1]);
      System.out.println("Number of C's: " + scoreCounter[2]);
      System.out.println("Number of D's: " + scoreCounter[3]);
      System.out.println("Number of F's: " + scoreCounter[4]);

      double avg = avg(scores);
      System.out.printf("Average is: %.2f", avg);

      //loop through the scores to count how many grades are above and below average 
      for(int i = 0; i < scores.length; i++)
      {
        if(scores[i] >= avg)
        {
          numScoresAboveAvg += 1;
        }else{
          numScoresBelowAvg += 1; 
        }
      }

      System.out.println("\nNumber of scores >= average: " + numScoresAboveAvg);
      System.out.println("Number of scores < average: " + numScoresBelowAvg);
      // clear the score counter and scores above/below average
      clearCounter(scoreCounter);
      numScoresAboveAvg = 0;
      numScoresBelowAvg = 0;

      System.out.print("Want to calculate grades (y/n)? ");
      inputStr = scan.next();     // read and assing to string
      choice = inputStr.charAt(0);    // assign to character
     } while( choice != 'n' && choice != 'N');// loop while NOT n or N

     scan.close();    //close scanner
     System.exit(0);  //terminate with successful exit
	}
    public static double avg ( double a[])
    {
      double avg = 0.0;
      double sum = 0.0;
      for(int i = 0; i < a.length; i++)
      {
          sum += a[i];
      }
      avg = sum / a.length;
      return avg;
    }
    public static void clearCounter(int a[])  {
      for(int i=0; i < a.length; i++)
        a[i] = 0;
    }
      
}
