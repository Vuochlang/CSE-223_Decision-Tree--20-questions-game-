public class Node{      //this is a node class for the decision-tree
  String data;
  Node yes;
  Node no;

  public Node()
  {yes=no=null;
  }

  public Node(String a)
  {       data=a;
    yes=no=null;
  }

  //this print is not using the decision-tree game
  //it's just a refernce to print out the tree to check if everything is in the correct spot
  public void print()//this will print the tree in NLP traversal
  {       System.out.println(data);
    if(yes!=null)   yes.print();
    if(no!=null)    no.print();
  }
}