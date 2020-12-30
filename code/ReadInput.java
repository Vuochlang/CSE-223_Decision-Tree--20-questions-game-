import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ReadInput{
  Scanner sc;
  String symbol;
  String info;
  Node rootTree;
  String answer="A:";
  String question="Q:";

  public Node read(String file)//this will scan the given file, if success then call scanFile function, if not then return null
  {                            //it will also return the rootTree of the decision tree too
    try{
      sc=new Scanner(new File(file));
    }catch(FileNotFoundException e){
      System.out.println("cannot open "+file);
      return (null);
    }
    scanFile(sc);
    return rootTree;
  }

  public Node readLine(Scanner sc)//this will read the line by line from the file and construct the decision-tree
  {                               //it is using the <Q:> and <A:> as a flag to decide the next step instead of comparing between nodes
    if(sc.hasNextLine())
    {       symbol=sc.nextLine();
      info=sc.nextLine();

      if(symbol.equals(answer))       //if it is <A:> then add the answer to the Node and return
      {       Node root=new Node(info);
        return root;
      }
      if(symbol.equals(question))     //if it is <Q:> then add the data to the node and get nextLine for YesNode and NoNode
      {                               //this is a recursive function
        Node root=new Node(info);
        root.yes=readLine(sc);
        root.no=readLine(sc);
        return root;
      }
    }
    return null;    //return null at the end of the file
  }

  public void scanFile(Scanner sc)//this will start scanning for input from the file and call the readLine to get YesNode and NoNode for the rootTree
  {       if(sc.hasNextLine())
  {     symbol=sc.nextLine();
    info=sc.nextLine();
    rootTree=new Node(info);
    rootTree.yes=readLine(sc);
    rootTree.no=readLine(sc);
    sc.close();
  }
  }

}