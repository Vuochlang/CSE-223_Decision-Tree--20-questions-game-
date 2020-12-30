import java.io.PrintWriter;

public class Update{//this will update the text-file with the given info
  Node rootTree,current;
  PrintWriter pw;
  String lastQuestion;
  String userAnswer;
  String userQuestion;

  public void start(Node tree, String file, String q, String a, String b)
  {       try     //check to see if able to create a new printwriter from the file
  {       pw=new PrintWriter(file);
  }catch(Exception e)
  {       System.out.println("something is wrong in Update.java");
    return;
  }
    lastQuestion=q;
    userAnswer=a;
    userQuestion=b;
    rootTree=tree;
    updateFile(tree);//call the recursive function
    pw.close();
  }

  public void updateFile(Node t)//a recursive function to go through the tree and print everything in NLR-order
  {                             //also add the additional information in the correct spot
    Node nextYes=t.yes;
    Node nextNo=t.no;
    if(nextYes!=null && nextNo!=null)//if it's not at the end of any sub-tree
    {       pw.println("Q:");
      pw.println(t.data);
      updateFile(t.yes);
      updateFile(t.no);
    }
    else if(nextYes==null && nextNo==null && lastQuestion.equals(t.data))//if it's at the any sub-tree
    {                                                               //and the its data is the lastQuestion then update/insert with the given info
      pw.println("Q:");
      pw.println(userQuestion);
      pw.println("A:");
      pw.println(userAnswer);
      pw.println("A:");
      pw.println(lastQuestion);
      return;
    }
    else//or it's the end of any subtree and its data does not match the lastQuestion
    {       pw.println("A:");
      pw.println(t.data);
      return;
    }
  }
}
