//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Name: Vuochlang Chang                                                                                                            //
// Class: CSE223        Spring 2020                                                                                                 //
// Date: 05/11/2020                                                                                                                 //
// Assignment: PA3 - Decision Tree (20-questions game)                                                                              //
//      This code will be reading a text-file that contains 20-quesions game and its answers. It will read the data and store into  //
//      a tree with Yes(left-node) and No(right-node). The game will start and user will think of something and the computer will   //
//      use the question tree to go down the list and guess that answer. If it doesn't have the answer, it will ask user for a      //
//      question to seperate the wrong answer and users answer and update the text-file for furture reference.                      //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class PA3{
  public static void main(String[] args)
  {   ReadInput readInput=new ReadInput();
      Node tree=readInput.read(args[0]);//readInput will return a decision-tree made from the given textfile, args[0]
      // tree.print(); //in NLR order
      if(tree==null)  return;//if the given file is empty or cannot scan the file
      Game game=new Game();
      game.start(tree,args[0]);//start the game by passing the decision-tree and the given file
  }
}
