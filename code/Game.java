import java.util.Scanner;
import java.io.PrintWriter;

public  class Game{     //this will start the game to guess the answer and update the text-file if the answer from the user does not exist
  Node rootTree,currentQuestion;
  Scanner userAnswer;
  PrintWriter pw;
  String fileName,answer,newAnswer,newQuestion;
  Update updateFile=new Update();
  boolean start=true,flag=true;
  boolean lastQuestion=false;//flag to indicate if it'sthe last answer of the question in the tree to ask user

  public void start(Node tree,String file)
  {       if(tree==null)
  {       System.out.println("no questions available...");
    return;
  }
    boolean inGame=true;//this is a flag to use the while-loop to keep asking question
    System.out.println("\n----------------------------------------------------------------------------");
    System.out.println("************WELCOME TO 20-QUESTIONS GMAE************");
    System.out.println("Let's start....\nThink of an object and I'll try to guess what is it.\nREADY? Go!\n");
    rootTree=tree;
    while(inGame)
    {       String question=getQuestion();//get the question from the function
      if(lastQuestion==false) System.out.print(question+"?  ");//print the question to user
      if(lastQuestion==true)  System.out.print("Okay, I got it. Is it "+question+"?  ");
      userAnswer=new Scanner(System.in);//get answer from user
      answer=userAnswer.nextLine();

      if(lastQuestion)//if the question is the last one in the file
      {       if(answer.equals("yes")==false&&answer.equals("no")==false)//a condition if user didn't type <yes> or <no>
      {       boolean error=true;
        while(error)//a loop to go through again and again if user did not type in correctly
        {       System.out.println("Please answer <yes> or <no>");
          System.out.print("is it "+question+"?  ");
          userAnswer=new Scanner (System.in);
          answer=userAnswer.next();
          if(answer.equals("yes") || answer.equals("no")) error=false;
        }
      }
        if(answer.equals("yes"))//this means the computer had guess the answer
        {       System.out.println("************YES!! I got it! Thanks for playing! Goodbye.************");
          System.out.println("----------------------------------------------------------------------------\n");
          return;
        }
        //the following codes will run if the user answer <no> at the last question
        //which means the computer needs to ask what is the answer and a question which <yes>-for the user-answer
        //and <no>-for what the computer had guess
        System.out.println("Okay,I lost.\nWhat were you thinking of?");
        userAnswer=new Scanner(System.in);
        newAnswer=userAnswer.nextLine();
        System.out.println("Okay...can you tell me a yes/no question to help me next time?");
        System.out.print("Please type a question which a <yes> answer means "+newAnswer.toUpperCase());
        System.out.println(" and a <no> answer means "+question.toUpperCase());
        userAnswer=new Scanner(System.in);
        newQuestion=userAnswer.nextLine();
        //   System.out.println("You have enter <"+newQuestion+">");
        updateFile.start(tree,file,question,newAnswer,newQuestion);//this will call to update the file with the given information
        System.out.println("************Thank you! I will update my questions!************");
        System.out.println("----------------------------------------------------------------------------\n");
        return;
      }
      if(answer.equals("yes")==false&&answer.equals("no")==false)//a condition if user didn't type <yes> or <no>
      {       boolean error=true;
        while(error)//a loop to go through again and again if user did not type in correctly
        {       System.out.println("Please answer <yes> or <no>");
          System.out.print(question+"?  ");
          userAnswer=new Scanner(System.in);
          answer=userAnswer.next();
          if(answer.equals("yes") || answer.equals("no")) error=false;
        }
      }
    }
  }

  public String getQuestion()//this will pass a question at a time
  {                          //it is using the currentQuestion node to keep track where it was left earlier
    Node nextYes, nextNo,temp;
    if(start)//only run when it's the beginning of the game
    {       currentQuestion=rootTree;
      start=false;
      return(currentQuestion.data);
    }
    if(answer.equals("yes"))//if user type in <yes>, it will return another question from the yesNode of the currentQuestion
    {       nextYes=currentQuestion.yes.yes;
      nextNo=currentQuestion.yes.no;
      if(nextYes==null && nextNo==null)//set flag to indicate that it's at the end of the tree
      {       lastQuestion=true;
      }
      temp=currentQuestion.yes;
      currentQuestion=temp;
      return temp.data;
    }
    //the follwoing will run when user type in <no>, it will return another question from noNode of the current Question
    nextYes=currentQuestion.no.yes;
    nextNo=currentQuestion.no.no;
    if(nextYes==null && nextNo==null)//set flag to indicate that it's at the end of the tree
    {       lastQuestion=true;
    }
    temp=currentQuestion.no;
    currentQuestion=temp;
    return temp.data;
  }
}
