import  java.io.*;
import java.util.*;

public class  MancalaGame  {
	MancalaGame(String  player0,  String  player1,Scanner sc)  {
        currentPlayer  =  sc.nextInt()-1;
        cutoff = sc.nextInt();
        board  =  new MancalaBoard(sc);
        
        mancalaplayers  =  new Player[2];
        mancalaplayers[0]  =  new Player(player0,  0);
        mancalaplayers[1]  =  new Player(player1,  1);
        
    }

    public  void play(int playingPits,int currentPlayer,int type, int cutoff)  throws  IOException  {
        
        if(type==1)
        {
        boolean goAgain = true;
        while  (!board.gameFinish() && goAgain)  {
            
            int  pitNum  =  mancalaplayers[currentPlayer].selectAMove(board,playingPits,currentPlayer);  
		    goAgain  =  board.runMove(currentPlayer, pitNum);
                                         
			
			
			
		}
		if(board.gameFinish())
                {
                board.putStonesIntoMancalas();
                }
                displayBoard();                                          
        }
                
                if(type==2)
                {
                if(!board.gameFinish())
                {
                if(cutoff!=0)
                {
                try(PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("traverse_log.txt",false)))) {
                output.println("Node"+","+"Depth"+","+"Value");
                output.print("root"+","+"0"+","+"-Infinity");
                output.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                
                
                if(currentPlayer==0)
                {
                int start = playingPits;
                int end = 1;
                int user = currentPlayer;
                
                int value = -999999999;
                int bestMove=0;
                for(int pitNum=start; pitNum>=end;pitNum--)
                {
                    if  (board.pStones(currentPlayer, pitNum)  !=  0)
                    {    
                    int depth=0;
                    int k=0;
                    PrintWriter output = outOpen();
                    k = mancalaplayers[currentPlayer].selectAMoveMinMax(board,playingPits,currentPlayer,pitNum,cutoff,depth,user,1,output);
                    if(k>value)
                    {
                        value=k;
                        bestMove=pitNum;
                    }
                    if(value==999999999)
                {
                
                    output.println();    
                    output.print("root"+","+depth+","+"Infinity");
                    
                        output.close();
                    
                
                }
                if(value==-999999999)
                {
                
                    output.println();    
                    output.print("root"+","+depth+","+"-Infinity");
                    
                        output.close();
                    
                    
                }
                if(value!=999999999 && value!=-999999999)
                {
                    
                        output.println();
                        output.print("root"+","+depth+","+value);
                    
                        output.close();
                    
                  
                }
                    
                    
                    }
                    
                }
                
                boolean goAgain = true;
                while(goAgain)
                {
                goAgain = board.runMove(currentPlayer,bestMove);
                if(board.gameFinish())
                {
                    board.putStonesIntoMancalas();
                    goAgain=false;
                }
                
                if(goAgain)
                {
                start = playingPits;
                end = 1;
                user = currentPlayer;
                
                value = -999999999;
                bestMove=0;
                for(int pitNum=start; pitNum>=end;pitNum--)
                {
                    if  (board.pStones(currentPlayer, pitNum)  !=  0)
                    {    
                    int depth=0;
                    int k=0;
                    PrintWriter output = outOpen();
                    k = mancalaplayers[currentPlayer].selectAMoveMinMax(board,playingPits,currentPlayer,pitNum,cutoff,depth,user,0,output);
                    if(k>value)
                    {
                        value=k;
                        bestMove=pitNum;
                    }
                    
                    
                    }
                }
                }
                }
                
                }
                
                
                if(currentPlayer==1)
                {
                int start = playingPits+2;
                int end = 2*playingPits+1;
                int user = currentPlayer;
                
                int value = -999999999;
                int bestMove=0;
                for(int pitNum=start; pitNum<=end;pitNum++)
                {
                    if  (board.pStones2(currentPlayer, pitNum)  !=  0)
                    {
                    int depth=0;
                    int k=0;
                    PrintWriter output = outOpen();
                    k = mancalaplayers[currentPlayer].selectAMoveMinMax(board,playingPits,currentPlayer,pitNum,cutoff,depth,user,1,output);
                    if(k>value)
                    {
                        value=k;
                        bestMove=pitNum;
                    }
                    if(value==999999999)
                {
                
                    output.println();    
                    output.print("root"+","+depth+","+"Infinity");
                    
                        output.close();
                    
                
                }
                if(value==-999999999)
                {
                
                    output.println();    
                    output.print("root"+","+depth+","+"-Infinity");
                    
                        output.close();
                    
                    
                }
                if(value!=999999999 && value!=-999999999)
                {
                    
                        output.println();
                        output.print("root"+","+depth+","+value);
                    
                        output.close();
                    
                  
                }
                    
                    
                    }
                }
                
                boolean goAgain = true;
                while(goAgain)
                {
                goAgain = board.runMove(currentPlayer,bestMove);
                if(board.gameFinish())
                {
                    board.putStonesIntoMancalas();
                    goAgain=false;
                }
                if(goAgain)
                {
                start = playingPits+2;
                end = 2*playingPits+1;
                user = currentPlayer;
                
                value = -999999999;
                bestMove=0;
                for(int pitNum=start; pitNum<=end;pitNum++)
                {
                    if  (board.pStones2(currentPlayer, pitNum)  !=  0)
                    {
                    int depth=0;
                    int k=0;
                    PrintWriter output = outOpen();
                    k = mancalaplayers[currentPlayer].selectAMoveMinMax(board,playingPits,currentPlayer,pitNum,cutoff,depth,user,0,output);
                    if(k>value)
                    {
                        value=k;
                        bestMove=pitNum;
                    }
                    
                    }
                } 
                    }
                }
                
                }
                }
                
                }
                if(board.gameFinish())
                {
                    board.putStonesIntoMancalas();
                }
                displayBoard();
                }

                if(type==3)
                {
                if(!board.gameFinish())
                {
                if(cutoff!=0)
                {
                try(PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("traverse_log.txt",false)))) {
                    output.println("Node"+","+"Depth"+","+"Value"+","+"Alpha"+","+"Beta");
                    output.print("root"+","+"0"+","+"-Infinity"+","+"-Infinity"+","+"Infinity");
                    output.close();
                    }catch (IOException e) {
                    e.printStackTrace();
                    }
                    
                
                
                if(currentPlayer==0)
                {
                int start = playingPits;
                int end = 1;
                int user = currentPlayer;
                
                int value = -999999999;
                int alpha = -999999999;
                int beta = 999999999;
                int bestMove=0;
                
                for(int pitNum=start; pitNum>=end && value<beta;pitNum--)
                {
                    if  (board.pStones(currentPlayer, pitNum)  !=  0)
                    {    
                    int depth=0;
                    int k=0;
                    PrintWriter output = outOpen();
                    k = mancalaplayers[currentPlayer].selectAMoveMinMax2(board,playingPits,currentPlayer,pitNum,cutoff,depth,user,1,alpha,beta,output);
                    if(k>value)
                    {
                        value=k;
                        if(value<beta)
                        {
                        alpha=value;
                        }
                        bestMove=pitNum;
                    }
                    
                    if(value==999999999)
                    {
                    if(alpha==-999999999)
                    {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"Infinity"+","+"-Infinity"+","+"Infinity");
                    
                    output.close();
                    
                
                        
                    }
                    if(beta!=999999999)
                    {
                    
                        output.println();
                        output.print("root"+","+depth+","+"Infinity"+","+"-Infinity"+","+beta);
                    
                        output.close();
                    
                        
                    }
                    }
                
                if(alpha!=-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"Infinity"+","+alpha+","+"Infinity");
                    
                        output.close();
                    
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"Infinity"+","+alpha+","+beta);
                    
                        output.close();
                    
                        
                    }
                }
                
                
            }
            
            if(value==-999999999)
            {
                if(alpha==-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"-Infinity"+","+"-Infinity"+","+"Infinity");
                    
                        output.close();
                    
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"-Infinity"+","+"-Infinity"+","+beta);
                    
                        output.close();
                    
                        
                    }
                }
                
                if(alpha!=-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"-Infinity"+","+alpha+","+"Infinity");
                    
                        output.close();
                    
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"-Infinity"+","+alpha+","+beta);
                    
                        output.close();
                    
                        
                    }
                }
                }
            if(value!=999999999 && value!=-999999999)
            {
            if(alpha==-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+value+","+"-Infinity"+","+"Infinity");
                    
                        output.close();
                    
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+value+","+"-Infinity"+","+beta);
                    
                        output.close();
                    
                        
                    }
                }
                
                if(alpha!=-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+value+","+alpha+","+"Infinity");
                    
                        output.close();
                    
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+value+","+alpha+","+beta);
                    
                        output.close();
                    
                        
                    }
                }
                  
            }
                    
                    
                    }
                    
                }
                
                
                boolean goAgain = true;
                while(goAgain)
                {
                goAgain = board.runMove(currentPlayer,bestMove);
                if(board.gameFinish())
                {
                    board.putStonesIntoMancalas();
                    goAgain=false;
                }
                if(goAgain)
                {
                start = playingPits;
                end = 1;
                user = currentPlayer;
                
                value = -999999999;
                alpha = -999999999;
                beta = 999999999;
                bestMove=0;
                for(int pitNum=start; pitNum>=end && value<beta;pitNum--)
                {
                    if  (board.pStones(currentPlayer, pitNum)  !=  0)
                    {    
                    int depth=0;
                    int k=0;
                    PrintWriter output = outOpen();
                    k = mancalaplayers[currentPlayer].selectAMoveMinMax2(board,playingPits,currentPlayer,pitNum,cutoff,depth,user,0,alpha,beta,output);
                    if(k>value)
                    {
                        value=k;
                        if(value<beta)
                        {
                        alpha=value;
                        }
                        bestMove=pitNum;
                    }
                    
                    
                    }
                }
                }
                }
                
                }
                
                
                if(currentPlayer==1)
                {
                int start = playingPits+2;
                int end = 2*playingPits+1;
                int user = currentPlayer;
                
                int value = -999999999;
                int alpha  = -999999999;
                int beta = 999999999;
                int bestMove=0;
                for(int pitNum=start; pitNum<=end && value<beta;pitNum++)
                {
                    if  (board.pStones2(currentPlayer, pitNum)  !=  0)
                    {
                    int depth=0;
                    int k=0;
                    PrintWriter output = outOpen();
                    k = mancalaplayers[currentPlayer].selectAMoveMinMax2(board,playingPits,currentPlayer,pitNum,cutoff,depth,user,1,alpha,beta,output);
                    if(k>value)
                    {
                        value=k;
                        if(value<beta)
                        {
                        alpha=value;
                        }
                        bestMove=pitNum;
                    }
                    
                    if(value==999999999)
                    {
                    if(alpha==-999999999)
                    {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"Infinity"+","+"-Infinity"+","+"Infinity");
                    
                    output.close();
                    
                
                        
                    }
                    if(beta!=999999999)
                    {
                    
                        output.println();
                        output.print("root"+","+depth+","+"Infinity"+","+"-Infinity"+","+beta);
                    
                        output.close();
                    
                        
                    }
                    }
                
                if(alpha!=-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"Infinity"+","+alpha+","+"Infinity");
                    
                        output.close();
                    
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"Infinity"+","+alpha+","+beta);
                    
                        output.close();
                    
                        
                    }
                }
                
                
            }
            
            if(value==-999999999)
            {
                if(alpha==-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"-Infinity"+","+"-Infinity"+","+"Infinity");
                    
                        output.close();
                    
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"-Infinity"+","+"-Infinity"+","+beta);
                    
                        output.close();
                    
                        
                    }
                }
                
                if(alpha!=-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"-Infinity"+","+alpha+","+"Infinity");
                    
                        output.close();
                    
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+"-Infinity"+","+alpha+","+beta);
                    
                        output.close();
                    
                        
                    }
                }
                }
            if(value!=999999999 && value!=-999999999)
            {
            if(alpha==-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+value+","+"-Infinity"+","+"Infinity");
                    
                        output.close();
                    
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+value+","+"-Infinity"+","+beta);
                    
                        output.close();
                    
                        
                    }
                }
                
                if(alpha!=-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+value+","+alpha+","+"Infinity");
                    
                        output.close();
                    
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print("root"+","+depth+","+value+","+alpha+","+beta);
                    
                        output.close();
                    
                        
                    }
                }
                  
            }
                    
                    
                    }
                }
                
                boolean goAgain = true;
                while(goAgain)
                {
                goAgain = board.runMove(currentPlayer,bestMove);
                if(board.gameFinish())
                {
                    board.putStonesIntoMancalas();
                    goAgain=false;
                }
                if(goAgain)
                {
                start = playingPits+2;
                end = 2*playingPits+1;
                user = currentPlayer;
                
                value = -999999999;
                alpha  = -999999999;
                beta = 999999999;
                bestMove=0;
                for(int pitNum=start; pitNum<=end && value<beta;pitNum++)
                {
                    if  (board.pStones2(currentPlayer, pitNum)  !=  0)
                    {
                    int depth=0;
                    int k=0;
                    PrintWriter output = outOpen();
                    k = mancalaplayers[currentPlayer].selectAMoveMinMax2(board,playingPits,currentPlayer,pitNum,cutoff,depth,user,0,alpha,beta,output);
                    if(k>value)
                    {
                        value=k;
                        if(value<beta)
                        {
                        alpha=value;
                        }
                        bestMove=pitNum;
                    }
                    
                    }
                } 
                    }
                }
                
                }
                }
                
                }
                if(board.gameFinish())
                {
                    board.putStonesIntoMancalas();
                    
                }
                displayBoard();
                }

                
                
	}
    
    public PrintWriter outOpen()
    {
        try
        {
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("traverse_log.txt",true)));
            return output;
        }
        catch (IOException e) {
                    e.printStackTrace();
                }
        return null;
    }

    private  void  displayBoard()  {
          
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("next_state.txt", false)))) {
        
            for  (int  i  =  1;  i  <  board.playingPits;  i++)  {
           out.print(board.pStones(1,  i)  +  " "); 
            
           
               
                                             
        }
         out.print(board.pStones(1,  board.playingPits));   
        out.println();
            
        for  (int  i  =  board.playingPits; i  > 1;  i--)
           out.print(board.pStones(0,  i)  + " ");
        out.print(board.pStones(0,  1));
        
        out.println();
        
        out.println(board.mStones(1));
        out.print(board.mStones(0));
        
        out.close();
        }             
        catch (IOException e) {
                    e.printStackTrace();
                }
    }

    

    public  static  void  main(String  []  args)  throws  IOException  {
		Scanner sc = new Scanner(System.in);
                File file = new File("try.txt");
                sc = new Scanner(file);
                
                int type = sc.nextInt();
                
                MancalaGame  game  =  new  MancalaGame("1",  "2",sc);
		game.play(game.board.playingPits,game.currentPlayer,type,game.cutoff);
                
    }

    int  currentPlayer  =  0;
    int cutoff = 0;
    MancalaBoard  board;
    Player  []  mancalaplayers;
}

class  MancalaBoard  {
    static  int  playingPits,totalPits;
    MancalaBoard(Scanner sc)  {
        String a = sc.nextLine();
        String boardState2 = sc.nextLine();
        String[] boardstate2 = boardState2.split(" ");
        playingPits = boardstate2.length;
        totalPits = 2*(playingPits+1);
        
        String boardState1 = sc.nextLine();
        String[] boardstate1 = boardState1.split(" ");
        pits  =  new  Pit[totalPits];
        for  (int  pitNum  =  0;  pitNum  <  totalPits;  pitNum++)
			pits[pitNum]  =  new Pit();
        
        int i=0;
        for  (int  pitNum  =  boardstate1.length;  pitNum  >=  1;  pitNum--)
        {
			pits[pitNum].addStones(Integer.parseInt(boardstate1[i++]));
        }
        
        i=0;
        for(int pitNum = playingPits+2; pitNum < playingPits+2+boardstate2.length; pitNum++)
        {
            pits[pitNum].addStones(Integer.parseInt(boardstate2[i++]));
        }
        
        pits[playingPits+1].addStones(sc.nextInt());
        pits[0].addStones(sc.nextInt());
        
        
        }
    
    MancalaBoard()  {
        
        pits  =  new  Pit[totalPits];
        for  (int  pitNum  =  0;  pitNum  <  totalPits;  
              pitNum++)
			pits[pitNum]  =  new Pit();
    }


    public  int  mStones(int  playerNum)  {
		return  pits[MancalaNumber(playerNum)].obtainStones();
    }

    public  int  pStones(int  playerNum, int pitNum)  {
        return  pits[PitNumber(playerNum, pitNum)].obtainStones();
    }
    
    public  int  pStones2(int  playerNum, int pitNum)  {
        return  pits[pitNum].obtainStones();
    }

    private  int  PitNumber(int  playerNum, int  pitNum) {
        return  playerNum  *  (playingPits+1)  +  pitNum;
    }

    private  int  MancalaNumber(int  playerNum)  {
        return  playerNum  *  (playingPits+1);
    }

    private  boolean  checkPlayerMancala(int  pitNum)  {
        return  pitNum  %  (playingPits+1)  ==  0;
    }

    public  MancalaBoard  makeACopy()  {
      MancalaBoard  newBoard  =  new  MancalaBoard();
        for  (int  pitNum  =  0;  pitNum  <  totalPits;  
              pitNum++)
           newBoard.pits[pitNum].addStones(this. 
                                 pits[pitNum].obtainStones());
        return  newBoard;
    }

    public  boolean  runMove(int  currentPlayerNum,  int  chosenPitNum)  {
        
        int  pitNum  =  chosenPitNum;
        
        int  stones  =  pits[pitNum].removeAllStones();
        while  (stones  !=  0)  {
           pitNum--;
           if  (pitNum  <  0)
                   pitNum  =  totalPits  -  1;
           if  (pitNum  != MancalaNumber(otherPlayerNumber(currentPlayerNum)))  {
               pits[pitNum].addStones(1);
               stones--;
               }
        }
        if  (pitNum  ==  MancalaNumber(currentPlayerNum))
           return  true;
        if  (pitOwner(pitNum)  ==  currentPlayerNum  && pits[pitNum].obtainStones()  ==  1)  {
			stones  =  pits[oppositePitNumber(pitNum)].removeAllStones();
                        stones =  stones+pits[pitNum].removeAllStones();
			pits[MancalaNumber(currentPlayerNum)].addStones(stones);
                        return false;
        }
        
	    return false;
	}

	private  int  pitOwner(int  pitNum)  {
        return  pitNum  /  (playingPits+1);
	}

	private  int  oppositePitNumber(int  pitNum)  {
		return  totalPits  -  pitNum;
	}

	public  int  otherPlayerNumber(int  playerNum)  {
		if  (playerNum  ==  0)
			return  1;
		else
			return  0;
	}

	public  boolean  gameFinish()  {
		for  (int  player  =  0;  player  <  2;  player++)  {
	        int  stones  =  0;
		    for  (int  pitNum  =  1;  pitNum  <=  playingPits; pitNum++)
				stones  +=  pits[PitNumber(player, pitNum)].obtainStones();
			if  (stones  ==  0)
               return  true;
        }
        return  false;
    }

    public  void  putStonesIntoMancalas()  {
        for  (int  player  =  0;  player  <  2;  player++)
           for  (int  pitNum  =  0;  pitNum  <=  playingPits;  pitNum++)  {
               int  stones  =  pits[PitNumber(player,pitNum)].removeAllStones(); 
               pits[MancalaNumber(player)].addStones(stones);
           }
    }

    Pit  []  pits;
    
}

class  Pit  {
    Pit()  {this.stones  =  0;}
    public  int  obtainStones()  {return  stones;}
    public  void  addStones(int  stones) {this.stones += stones;}
    public  boolean  isEmpty() {return stones == 0;}
    public  int  removeAllStones() {
        int  stones  =  this.stones;
        this.stones  =  0;
        return  stones;
    }
    int  stones;
}

class  Player  {
    Player(String  name,  int  playerNum)  {
        this.name  =  name;
        this.playerNum  =  playerNum;
    }


    public  int  selectAMove(MancalaBoard board,int playingPits,int currentPlayer)   throws  IOException  {
    
    int  bestMove  =  -1;        
          
    int  maxEval  =  -9999999; 
      
    int eval = 0;
    if(currentPlayer==0)
    {
        int start = playingPits;
        int end = 1;
    
    for  (int  pitNum  =  start;  pitNum  >= end;  pitNum--)  {
        if  (board.pStones(currentPlayer, pitNum)  !=  0) {
																
			MancalaBoard  testBoard  =  board.makeACopy();		
			boolean  goAgain  = testBoard.runMove(currentPlayer, pitNum);	
                        if(testBoard.gameFinish())
                        {
                            testBoard.putStonesIntoMancalas();
                            goAgain=false;
                        }
			        					
                        				
			eval = testBoard.mStones(currentPlayer)  -  testBoard.mStones(testBoard.otherPlayerNumber(currentPlayer));													
			if  (goAgain)										
				eval = selectAMoveEval(testBoard,playingPits,currentPlayer);
                        
                   if  (eval  >  maxEval) {		 
		   											
                   maxEval = eval;		 
                   bestMove  =  pitNum;				
													
               }
           }
        }
    }
    
    else if(currentPlayer==1)
    {
        int start = playingPits+2;
        int end = 2*playingPits+1;
    
    for  (int  pitNum  =  start;  pitNum  <= end;  pitNum++)  {
        if  (board.pStones2(currentPlayer, pitNum)  !=  0) {
																
			MancalaBoard  testBoard  =  board.makeACopy();		
			boolean  goAgain  = testBoard.runMove(currentPlayer, pitNum);	
							
			if(testBoard.gameFinish())
                        {
                            testBoard.putStonesIntoMancalas();
                            goAgain=false;
                        }
                        
                        eval = testBoard.mStones(currentPlayer)  -  testBoard.mStones(testBoard.otherPlayerNumber(currentPlayer));													
			if  (goAgain)										
				eval = selectAMoveEval(testBoard,playingPits,currentPlayer);
                        
                        if  (eval  >  maxEval) {		
		   											
                   maxEval = eval;		
                   bestMove  =  pitNum;				 
													
               }
           }
        }
    }

        
    return bestMove;    
    }
    
    public  int  selectAMoveEval(MancalaBoard board,int playingPits,int currentPlayer)   throws  IOException  {
        
    
    int  bestMove  =  -1;        
          
    int  maxEval  =  -9999999; 
      
    int eval = 0;
    
    if(currentPlayer==0)
    {
        int start = playingPits;
        int end = 1;
    
    for  (int  pitNum  =  start;  pitNum  >= end;  pitNum--)  {
        if  (board.pStones(currentPlayer, pitNum)  !=  0) {
																
			MancalaBoard  testBoard  =  board.makeACopy();		
			boolean  goAgain  = testBoard.runMove(currentPlayer, pitNum);	
			        									
			if(testBoard.gameFinish())
                        {
                            testBoard.putStonesIntoMancalas();
                            goAgain=false;
                        }
                        
                        eval = testBoard.mStones(currentPlayer)  -  testBoard.mStones(testBoard.otherPlayerNumber(currentPlayer));													
			if  (goAgain)										
				eval = selectAMoveEval(testBoard,playingPits,currentPlayer);
                        
                   if  (eval  >  maxEval) {		 
		   											
                   maxEval = eval;		 
                   bestMove  =  pitNum;				 
													
               }
           }
        }
    }
    
    else if(currentPlayer==1)
    {
        int start = playingPits+2;
        int end = 2*playingPits+1;
    
    for  (int  pitNum  =  start;  pitNum  <= end;  pitNum++)  {
        if  (board.pStones2(currentPlayer, pitNum)  !=  0) {
																
			MancalaBoard  testBoard  =  board.makeACopy();		
			boolean  goAgain  = testBoard.runMove(currentPlayer, pitNum);	
							
			if(testBoard.gameFinish())
                        {
                            testBoard.putStonesIntoMancalas();
                            goAgain=false;
                        }
                        
                        eval = testBoard.mStones(currentPlayer)  -  testBoard.mStones(testBoard.otherPlayerNumber(currentPlayer));													
			if  (goAgain)										
				eval = selectAMoveEval(testBoard,playingPits,currentPlayer);
                        
                        if  (eval  >  maxEval) {		 
		   											
                   maxEval = eval;
                    
                   bestMove  =  pitNum;				 
													
               }
           }
        }
    }

        return maxEval;
    }
    
    public  int  selectAMoveMinMax(MancalaBoard board,int playingPits,int currentPlayer,int pitNum,int cutoff,int depth,int user,int print,PrintWriter output)   throws  IOException  {
        
    
    depth = depth+1;
    int value=0;
    
    if(depth%2==0)
        value=-999999999;
    
    if(depth%2!=0)
        value=999999999;
    
    if(depth<cutoff)
    {
    
    // Computer player - need to determine best move
    int  bestMove  =  -1;        // No best move initially
          // Or go again.
    int  maxEval  =  -9999999;
    
     
    int eval = 0;//      mancala.
    if(currentPlayer==0)
    {
        
        
        MancalaBoard  testBoard  =  board.makeACopy();		
	boolean  goAgain  = testBoard.runMove(currentPlayer, pitNum);	
        
        if(testBoard.gameFinish())
        {
            if(depth%2!=0)
            {
                if(goAgain)
                {
                    value=-999999999;
                }
                if(!goAgain)
                {
                value=999999999;
                }
            }
            if(depth%2==0)
            {
                if(goAgain)
                {
                    value=999999999;
                }
                if(!goAgain)
                {
                value=-999999999;
                }
            }
            printMinMax("B",(playingPits-pitNum+2),depth,value,print,output);
            testBoard.putStonesIntoMancalas();
            value = testBoard.mStones(user)  -  testBoard.mStones(testBoard.otherPlayerNumber(user));
            printMinMax("B",(playingPits-pitNum+2),depth,value,print,output);
            return value;
        }
        
        if(goAgain)
        {
            if(depth%2!=0)
            {
                value=-999999999;
            }
            if(depth%2==0)
            {
                value=999999999;
            }
            
            printMinMax("B",(playingPits-pitNum+2),depth,value,print,output);
           
            int start = playingPits;
            int end = 1;
            int j=0;
            for(int i=start; i>=end;i--)
            {
                if  (testBoard.pStones(currentPlayer, i)  !=  0)
                {
                int k = selectAMoveMinMax(testBoard,playingPits,currentPlayer,i,cutoff,depth-1,user,print,output);
                j++;
                if(j==1)
                {
                    value=k;
                }
                
                if(depth%2==0)
                {
                    
                if(k<value)
                {
                    value=k;
                }
                }
                
                if(depth%2!=0)
                {
                if(k>value)
                {
                    value=k;
                }  
                }
                
                printMinMax("B",(playingPits-pitNum+2),depth,value,print,output);
           
                
                }
            }
        }
        else
        {
            //currentPlayer = 1;
            printMinMax("B",(playingPits-pitNum+2),depth,value,print,output);
           
            int start = playingPits+2;
            int end = 2*playingPits+1;
            //System.out.println(pitNum+","+depth+","+value);
            for(int i=start;i<=end;i++)
            {
                if  (testBoard.pStones(currentPlayer, i)  !=  0)
                {
                int k = selectAMoveMinMax(testBoard,playingPits,testBoard.otherPlayerNumber(currentPlayer),i,cutoff,depth,user,print,output);
                if(depth%2==0)
                {
                if(k>value)
                {
                    value=k;
                }
                }
                if(depth%2!=0)
                {
                if(k<value) //take minimum
                {
                    value=k;
                }  
                }
                printMinMax("B",(playingPits-pitNum+2),depth,value,print,output);
           
                
                }
            }
        }
    
    }
    
    
    if(currentPlayer==1)
    {
        
        
        MancalaBoard  testBoard  =  board.makeACopy();		
	boolean  goAgain  = testBoard.runMove(currentPlayer, pitNum);	
        
        if(testBoard.gameFinish())
        {
            if(depth%2!=0)
            {
                if(goAgain)
                {
                    value=-999999999;
                }
                if(!goAgain)
                {
                value=999999999;
                }
            }
            if(depth%2==0)
            {
                if(goAgain)
                {
                    value=999999999;
                }
                if(!goAgain)
                {
                value=-999999999;
                }
            }
            printMinMax("A",(pitNum-playingPits),depth,value,print,output);
            testBoard.putStonesIntoMancalas();
            value = testBoard.mStones(user)  -  testBoard.mStones(testBoard.otherPlayerNumber(user));
            printMinMax("A",(pitNum-playingPits),depth,value,print,output);
            return value;
        }
        if(goAgain)
        {
            if(depth%2!=0)
            {
                value=-999999999;
            }
            if(depth%2==0)
            {
                value=999999999;
            }
            
            printMinMax("A",(pitNum-playingPits),depth,value,print,output);
           
            
            int start = playingPits+2;
            int end = 2*playingPits+1;
            
            for(int i=start; i<=end;i++)
            {
                if  (testBoard.pStones2(currentPlayer, i)  !=  0)
                {
                int k = selectAMoveMinMax(testBoard,playingPits,currentPlayer,i,cutoff,depth-1,user,print,output);
                
                if(i==start)
                {
                    value=k;
                }
                
                if(depth%2==0)
                {
                if(k<value)
                {
                    value=k;
                }
                }
                if(depth%2!=0)
                {
                if(k>value)
                {
                    value=k;
                }  
                }
                
                printMinMax("A",(pitNum-playingPits),depth,value,print,output);
                }
            }
        }
        else
        {
            //currentPlayer = 0;
            printMinMax("A",(pitNum-playingPits),depth,value,print,output);
            
            int start = playingPits;
            int end = 1;
            //System.out.println(pitNum+","+depth+","+value);
            for(int i=start;i>=end;i--)
            {
                if  (testBoard.pStones2(currentPlayer, i)  !=  0)
                {
                int k = selectAMoveMinMax(testBoard,playingPits,testBoard.otherPlayerNumber(currentPlayer),i,cutoff,depth,user,print,output);
                if(depth%2==0)
                {
                if(k>value)
                {
                    value=k;
                }
                }
                if(depth%2!=0)
                {
                if(k<value)
                {
                    value=k;
                }  
                }
                printMinMax("A",(pitNum-playingPits),depth,value,print,output);
                }
            }
        }
    
    }
    
    
    
            
    }
    
    if(depth==cutoff)
    {
    
    
    int  bestMove  =  -1;        
          
    int  maxEval  =  -9999999;
    
     
    int eval = 0;
    if(currentPlayer==0)
    {
        MancalaBoard  testBoard  =  board.makeACopy();		
	boolean  goAgain  = testBoard.runMove(currentPlayer, pitNum);	
        
        if(testBoard.gameFinish())
        {
            if(depth%2!=0)
            {
                if(goAgain)
                {
                    value=-999999999;
                }
                if(!goAgain)
                {
                value=999999999;
                }
            }
            if(depth%2==0)
            {
                if(goAgain)
                {
                    value=999999999;
                }
                if(!goAgain)
                {
                value=-999999999;
                }
            }
            //printMinMax("B",(playingPits-pitNum+2),depth,value,print);
            testBoard.putStonesIntoMancalas();
            value = testBoard.mStones(user)  -  testBoard.mStones(testBoard.otherPlayerNumber(user));
            printMinMax("B",(playingPits-pitNum+2),depth,value,print,output);
            return value;
        }
        
        if(goAgain)
        {
            if(depth%2!=0)
            {
                value=-999999999;
            }
            if(depth%2==0)
            {
                value=999999999;
            }
            
            int start = playingPits;
            int end = 1;
            
            printMinMax("B",(playingPits-pitNum+2),depth,value,print,output);
            
            for(int i=start; i>=end;i--)
            {
                if  (testBoard.pStones(currentPlayer, i)  !=  0)
                {
                int k = selectAMoveMinMax(testBoard,playingPits,currentPlayer,i,cutoff,depth-1,user,print,output);
                
                if(i==start)
                {
                    value=k;
                }
                
                if(depth%2==0)
                {
                if(k<value)
                {
                    value=k;
                }
                }
                if(depth%2!=0)
                {
                if(k>value)
                {
                    value=k;
                }  
                }
                printMinMax("B",(playingPits-pitNum+2),depth,value,print,output);
            
                }
            }
        }
        else
        {
            
            value = testBoard.mStones(user)  -  testBoard.mStones(testBoard.otherPlayerNumber(user));
            printMinMax("B",(playingPits-pitNum+2),depth,value,print,output);
            
            return value;
        }
    
    
    }
    
    if(currentPlayer==1)
    {
        MancalaBoard  testBoard  =  board.makeACopy();		
	boolean  goAgain  = testBoard.runMove(currentPlayer, pitNum);	
        
        if(testBoard.gameFinish())
        {
            if(depth%2!=0)
            {
                if(goAgain)
                {
                    value=-999999999;
                }
                if(!goAgain)
                {
                value=999999999;
                }
            }
            if(depth%2==0)
            {
                if(goAgain)
                {
                    value=999999999;
                }
                if(!goAgain)
                {
                value=-999999999;
                }
            }
            //printMinMax("A",(pitNum-playingPits),depth,value,print);
            testBoard.putStonesIntoMancalas();
            value = testBoard.mStones(user)  -  testBoard.mStones(testBoard.otherPlayerNumber(user));
            printMinMax("A",(pitNum-playingPits),depth,value,print,output);
            return value;
        }
        if(goAgain)
        {
            if(depth%2!=0)
            {
                value=-999999999;
            }
            if(depth%2==0)
            {
                value=999999999;
            }
            
            int start = playingPits+2;
            int end = 2*playingPits+1;
            printMinMax("A",(pitNum-playingPits),depth,value,print,output);
            
            
            for(int i=start; i<=end;i++)
            {
                if  (testBoard.pStones2(currentPlayer, i)  !=  0)
                {
                
                int k = selectAMoveMinMax(testBoard,playingPits,currentPlayer,i,cutoff,depth-1,user,print,output);
                
                if(i==start)
                {
                    value=k;
                }
                
                if(depth%2==0)
                {
                if(k<value)
                {
                    value=k;
                }
                }
                if(depth%2!=0)
                {
                if(k>value)
                {
                    value=k;
                }  
                }
                printMinMax("A",(pitNum-playingPits),depth,value,print,output);
                }
            }
        }
        else
        {
            value = testBoard.mStones(user)  -  testBoard.mStones(testBoard.otherPlayerNumber(user));
                printMinMax("A",(pitNum-playingPits),depth,value,print,output);
                
                return value;
        }
    
    
    }

        
    return value;    
    }
    
    return value;
}

    public void printMinMax(String player,int pitnum,int depth,int value,int print,PrintWriter output)
    {
        if(print==1)
        {
            if(value==999999999)
                {
                
                    output.println();    
                    output.print(player+pitnum+","+depth+","+"Infinity");
                
                }
                if(value==-999999999)
                {
                
                    output.println();
                    output.print(player+pitnum+","+depth+","+"-Infinity");    
                }
                if(value!=999999999 && value!=-999999999)
                {
                    
                        output.println();
                        output.print(player+pitnum+","+depth+","+value);  
                }
        }
    }
    
    public void printAlphaBeta(String player,int pitnum,int depth,int value,int alpha,int beta,int print,PrintWriter output)
{
    if(print==1)
    {
            if(value==999999999)
                    {
                    if(alpha==-999999999)
                    {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print(player+pitnum+","+depth+","+"Infinity"+","+"-Infinity"+","+"Infinity");

                    }
                    if(beta!=999999999)
                    {
                    
                        output.println();
                        output.print(player+pitnum+","+depth+","+"Infinity"+","+"-Infinity"+","+beta);

                    }
                    }
                
                if(alpha!=-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print(player+pitnum+","+depth+","+"Infinity"+","+alpha+","+"Infinity");
                   
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print(player+pitnum+","+depth+","+"Infinity"+","+alpha+","+beta);
                    
                    }
                }
                
                
            }
            
            if(value==-999999999)
            {
                if(alpha==-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print(player+pitnum+","+depth+","+"-Infinity"+","+"-Infinity"+","+"Infinity");
           
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print(player+pitnum+","+depth+","+"-Infinity"+","+"-Infinity"+","+beta);
      
                    }
                }
                
                if(alpha!=-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print(player+pitnum+","+depth+","+"-Infinity"+","+alpha+","+"Infinity");
                    
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print(player+pitnum+","+depth+","+"-Infinity"+","+alpha+","+beta);
   
                        
                    }
                }
                }
            if(value!=999999999 && value!=-999999999)
            {
            if(alpha==-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print(player+pitnum+","+depth+","+value+","+"-Infinity"+","+"Infinity");
  
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print(player+pitnum+","+depth+","+value+","+"-Infinity"+","+beta);
        
                    }
                }
                
                if(alpha!=-999999999)
                {
                    if(beta==999999999)
                    {
                        
                            output.println();
                            output.print(player+pitnum+","+depth+","+value+","+alpha+","+"Infinity");
                    
                    
                    
                        
                    }
                    if(beta!=999999999)
                    {
                        
                            output.println();
                            output.print(player+pitnum+","+depth+","+value+","+alpha+","+beta);
                    
                    }
                }
                  
            }
    }
}

public  int  selectAMoveMinMax2(MancalaBoard board,int playingPits,int currentPlayer,int pitNum,int cutoff,int depth,int user,int print,int alpha,int beta,PrintWriter output)   throws  IOException  {
        
    
    depth = depth+1;
    int value=0;
    int flag=0;
    
    
    if(depth%2==0)
        value=-999999999;
    
    if(depth%2!=0)
        value=999999999;
    
    if(depth<cutoff)
    {
    
    
    int  bestMove  =  -1;        
          
    int  maxEval  =  -9999999;
    
    
     
    int eval = 0;
    if(currentPlayer==0)
    {
        
        
        MancalaBoard  testBoard  =  board.makeACopy();		
	boolean  goAgain  = testBoard.runMove(currentPlayer, pitNum);	
        
        if(testBoard.gameFinish())
        {
            if(depth%2!=0)
            {
                if(goAgain)
                {
                    value=-999999999;
                }
                if(!goAgain)
                {
                value=999999999;
                }
            }
            if(depth%2==0)
            {
                if(goAgain)
                {
                    value=999999999;
                }
                if(!goAgain)
                {
                value=-999999999;
                }
            }
            printAlphaBeta("B",(playingPits-pitNum+2),depth,value,alpha,beta,print,output);
            testBoard.putStonesIntoMancalas();
            value = testBoard.mStones(user)  -  testBoard.mStones(testBoard.otherPlayerNumber(user));
            printAlphaBeta("B",(playingPits-pitNum+2),depth,value,alpha,beta,print,output);
            return value;
        }
        
        if(goAgain)
        {
            if(depth%2!=0)
            {
                value=-999999999;
            }
            if(depth%2==0)
            {
                value=999999999;
            }
            
            if(depth%2!=0 && value>=beta)
            {
            flag=1;
               
            }
            
            if(depth%2==0 && value<=alpha)
            {
            flag=1;
            }
            
            printAlphaBeta("B",(playingPits-pitNum+2),depth,value,alpha,beta,print,output);
           
            int start = playingPits;
            int end = 1;
            int j=0;
            for(int i=start; i>=end && flag!=1;i--)
            {
                if  (testBoard.pStones(currentPlayer, i)  !=  0)
                {
                int k = selectAMoveMinMax2(testBoard,playingPits,currentPlayer,i,cutoff,depth-1,user,print,alpha,beta,output);
                j++;
                if(j==1)
                {
                    value=k;
                }
                
                if(depth%2==0)
                {
                    
                if(k<value)
                {
                    
                    value=k;
                }
                
            
                if(value<=alpha)
                {
                flag=1;
                }
                
                if(value<beta && flag!=1)
                {
                    beta=value;
                }
                }
                
                if(depth%2!=0)
                {
                if(k>value)
                {
                    
                    value = k;
                    
                }
                if(value>=beta)
                {
                flag=1;
               
                }
            
                
                if(value>alpha && flag!=1)
                {
                    alpha=value;
                }
                }
                
                if(depth%2!=0 && value>=beta)
                {
                flag=1;
               
                }
            
                if(depth%2==0 && value<=alpha)
                {
                flag=1;
                }
                
                printAlphaBeta("B",(playingPits-pitNum+2),depth,value,alpha,beta,print,output);
                }
            }
        }
        else
        {
            
            if(depth%2!=0)
            {
                value=999999999;
            }
            if(depth%2==0)
            {
                value=-999999999;
            }
            if(depth%2==0 && value>=beta)
            {
            flag=1;
               
            }
            
            if(depth%2!=0 && value<=alpha)
            {
            flag=1;
            }
            
            printAlphaBeta("B",(playingPits-pitNum+2),depth,value,alpha,beta,print,output);
            
            int start = playingPits+2;
            int end = 2*playingPits+1;
            
            for(int i=start;i<=end && flag!=1;i++)
            {
                if  (testBoard.pStones(currentPlayer, i)  !=  0)
                {
                int k = selectAMoveMinMax2(testBoard,playingPits,testBoard.otherPlayerNumber(currentPlayer),i,cutoff,depth,user,print,alpha,beta,output);
                if(depth%2==0)
                {
                if(k>value)
                {
                    
                    value = k;
                }
                
                if(value>=beta)
                {
                flag=1;
               
                }
            
                
                if(value>alpha && flag!=1)
                {
                    alpha=value;
                }
                }
                if(depth%2!=0)
                {
                if(k<value) 
                {
                    
                    value=k;
                }  
                
            
                if(value<=alpha)
                {
                flag=1;
                }
                if(value<beta && flag!=1)
                {
                    beta=value;
                }
                }
                if(depth%2!=0 && value<=alpha)
                {
                flag=1;
               
                }
            
                if(depth%2==0 && value>=beta)
                {
                flag=1;
                }
                
                printAlphaBeta("B",(playingPits-pitNum+2),depth,value,alpha,beta,print,output);
                
                }
            }
        }
    
    }
    
    
    if(currentPlayer==1)
    {
        
        
        MancalaBoard  testBoard  =  board.makeACopy();		
	boolean  goAgain  = testBoard.runMove(currentPlayer, pitNum);	
        if(testBoard.gameFinish())
        {
            if(depth%2!=0)
            {
                if(goAgain)
                {
                    value=-999999999;
                }
                if(!goAgain)
                {
                value=999999999;
                }
            }
            if(depth%2==0)
            {
                if(goAgain)
                {
                    value=999999999;
                }
                if(!goAgain)
                {
                value=-999999999;
                }
            }
            printAlphaBeta("A",(pitNum-playingPits),depth,value,alpha,beta,print,output);
            testBoard.putStonesIntoMancalas();
            value = testBoard.mStones(user)  -  testBoard.mStones(testBoard.otherPlayerNumber(user));
            printAlphaBeta("A",(pitNum-playingPits),depth,value,alpha,beta,print,output);
            return value;
        }
        if(goAgain)
        {
            if(depth%2!=0)
            {
                value=-999999999;
            }
            if(depth%2==0)
            {
                value=999999999;
            }
            
            if(depth%2!=0 && value>=beta)
            {
            flag=1;
               
            }
            
        if(depth%2==0 && value<=alpha)
        {
        flag=1;
        }
            
            printAlphaBeta("A",(pitNum-playingPits),depth,value,alpha,beta,print,output);
            
            int start = playingPits+2;
            int end = 2*playingPits+1;
            
            for(int i=start; i<=end && flag!=1;i++)
            {
                if  (testBoard.pStones2(currentPlayer, i)  !=  0)
                {
                int k = selectAMoveMinMax2(testBoard,playingPits,currentPlayer,i,cutoff,depth-1,user,print,alpha,beta,output);
                
                if(i==start)
                {
                    value=k;
                }
                
                if(depth%2==0)
                {
                if(k<value)
                {
                    
                    value=k;
                }
                
            
                if(value<=alpha)
                {
                flag=1;
                }
                if(value<beta && flag!=1)
                {
                    beta=value;
                }
                }
                if(depth%2!=0)
                {
                if(k>value)
                {
                    
                    value=k;
                } 
                if(value>=beta)
                {
                flag=1;
               
                }
            
                
                if(value>alpha && flag!=1)
                {
                    alpha=value;
                }
                }
                
                if(depth%2!=0 && value>=beta)
                {
                flag=1;
               
                }
            
                if(depth%2==0 && value<=alpha)
                {
                flag=1;
                }
                
                printAlphaBeta("A",(pitNum-playingPits),depth,value,alpha,beta,print,output);
                }
            }
        }
        else
        {
            if(depth%2!=0)
            {
                value=999999999;
            }
            if(depth%2==0)
            {
                value=-999999999;
            }
            
            if(depth%2==0 && value>=beta)
            {
            flag=1;
               
            }
            
            if(depth%2!=0 && value<=alpha)
            {
            flag=1;
            }
            
            printAlphaBeta("A",(pitNum-playingPits),depth,value,alpha,beta,print,output);
            
            int start = playingPits;
            int end = 1;
            
            for(int i=start;i>=end && flag!=1;i--)
            {
                if  (testBoard.pStones2(currentPlayer, i)  !=  0)
                {
                int k = selectAMoveMinMax2(testBoard,playingPits,testBoard.otherPlayerNumber(currentPlayer),i,cutoff,depth,user,print,alpha,beta,output);
                if(depth%2==0)
                {
                if(k>value)
                {
                    
                    value=k;
                }
                if(value>=beta)
                {
                flag=1;
               
                }
            
                
                if(value>alpha && flag!=1)
                {
                    alpha=value;
                }
                }
                if(depth%2!=0)
                {
                if(k<value)
                {
                    
                    value=k;
                }
                
            
                if(value<=alpha)
                {
                flag=1;
                }
                if(value<beta && flag!=1)
                {
                    beta=value;
                }
                }
                
                if(depth%2!=0 && value<=alpha)
                {
                flag=1;
               
                }
            
                if(depth%2==0 && value>=beta)
                {
                flag=1;
                }
                printAlphaBeta("A",(pitNum-playingPits),depth,value,alpha,beta,print,output);
                
                }
            }
        }
    
    }
    
    
    
          
    }
    
    if(depth==cutoff)
    {
    
    
    int  bestMove  =  -1;        
          
    int  maxEval  =  -9999999;
    
     
    int eval = 0;
    if(currentPlayer==0)
    {
        MancalaBoard  testBoard  =  board.makeACopy();		
	boolean  goAgain  = testBoard.runMove(currentPlayer, pitNum);	
        
        if(testBoard.gameFinish())
        {
            if(depth%2!=0)
            {
                if(goAgain)
                {
                    value=-999999999;
                }
                if(!goAgain)
                {
                value=999999999;
                }
            }
            if(depth%2==0)
            {
                if(goAgain)
                {
                    value=999999999;
                }
                if(!goAgain)
                {
                value=-999999999;
                }
            }
            //printAlphaBeta("B",(playingPits-pitNum+2),depth,value,alpha,beta,print);
            testBoard.putStonesIntoMancalas();
            value = testBoard.mStones(user)  -  testBoard.mStones(testBoard.otherPlayerNumber(user));
            printAlphaBeta("B",(playingPits-pitNum+2),depth,value,alpha,beta,print,output);
            return value;
        }
        
        
        
        if(goAgain)
        {
            if(depth%2!=0)
            {
                value=-999999999;
            }
            if(depth%2==0)
            {
                value=999999999;
            }
            
            if(depth%2!=0 && value>=beta)
            {
            flag=1;
               
            }
            
            if(depth%2==0 && value<=alpha)
            {
            flag=1;
            }
            int start = playingPits;
            int end = 1;
            
            printAlphaBeta("B",(playingPits-pitNum+2),depth,value,alpha,beta,print,output);
            
            for(int i=start; i>=end && flag!=1;i--)
            {
                if  (testBoard.pStones(currentPlayer, i)  !=  0)
                {
                int k = selectAMoveMinMax2(testBoard,playingPits,currentPlayer,i,cutoff,depth-1,user,print,alpha,beta,output);
                
                if(i==start)
                {
                    value=k;
                }
                
                if(depth%2==0)
                {
                if(k<value)
                {
                    
                    value=k;
                }
                
            
                if(value<=alpha)
                {
                flag=1;
                }
                if(value<beta && flag!=1)
                {
                    beta=value;
                }
                }
                
                if(depth%2!=0)
                {
                if(k>value)
                {
                    
                    value=k;
                }
                
                if(value>=beta)
                {
                flag=1;
               
                }
            
                
                if(value>alpha && flag!=1)
                {
                    alpha=value;
                }
                }
                
                if(depth%2!=0 && value>=beta)
                {
                flag=1;
               
                }
            
                if(depth%2==0 && value<=alpha)
                {
                flag=1;
                }
                
                printAlphaBeta("B",(playingPits-pitNum+2),depth,value,alpha,beta,print,output);
                }
            }
        }
        else
        {
            
            value = testBoard.mStones(user)  -  testBoard.mStones(testBoard.otherPlayerNumber(user));
            
            printAlphaBeta("B",(playingPits-pitNum+2),depth,value,alpha,beta,print,output);
            return value;
        }
    
    
    }
    
    if(currentPlayer==1)
    {
        MancalaBoard  testBoard  =  board.makeACopy();		
	boolean  goAgain  = testBoard.runMove(currentPlayer, pitNum);	
        
        
        if(testBoard.gameFinish())
        {
            if(depth%2!=0)
            {
                if(goAgain)
                {
                    value=-999999999;
                }
                if(!goAgain)
                {
                value=999999999;
                }
            }
            if(depth%2==0)
            {
                if(goAgain)
                {
                    value=999999999;
                }
                if(!goAgain)
                {
                value=-999999999;
                }
            }
            //printAlphaBeta("A",(pitNum-playingPits),depth,value,alpha,beta,print);
            testBoard.putStonesIntoMancalas();
            value = testBoard.mStones(user)  -  testBoard.mStones(testBoard.otherPlayerNumber(user));
            printAlphaBeta("A",(pitNum-playingPits),depth,value,alpha,beta,print,output);
            return value;
        }
        
        
        if(goAgain)
        {
            if(depth%2!=0)
            {
                value=-999999999;
            }
            if(depth%2==0)
            {
                value=999999999;
            }
            if(depth%2!=0 && value>=beta)
            {
            flag=1;
               
            }
            
            if(depth%2==0 && value<=alpha)
            {
            flag=1;
            }
            
            int start = playingPits+2;
            int end = 2*playingPits+1;
            
            printAlphaBeta("A",(pitNum-playingPits),depth,value,alpha,beta,print,output);
            
            for(int i=start; i<=end && flag!=1;i++)
            {
                if  (testBoard.pStones2(currentPlayer, i)  !=  0)
                {
                
                int k = selectAMoveMinMax2(testBoard,playingPits,currentPlayer,i,cutoff,depth-1,user,print,alpha,beta,output);
                
                if(i==start)
                {
                    value=k;
                }
                
                if(depth%2==0)
                {
                if(k<value)
                {
                    
                    value=k;
                }
                
            
                if(value<=alpha)
                {
                flag=1;
                }
                
                if(value<beta && flag!=1)
                {
                    beta=value;
                }
                }
                
                if(depth%2!=0)
                {
                if(k>value)
                {
                    
                    value=k;
                }  
                if(value>=beta)
                {
                flag=1;
               
                }
            
                
                if(value>alpha && flag!=1)
                {
                    alpha=value;
                }
                }
                
                if(depth%2!=0 && value>=beta)
                {
                flag=1;
               
                }
            
                if(depth%2==0 && value<=alpha)
                {
                flag=1;
                }
                
                
                
                printAlphaBeta("A",(pitNum-playingPits),depth,value,alpha,beta,print,output);
            
                }
            }
        }
        else
        {
            value = testBoard.mStones(user)  -  testBoard.mStones(testBoard.otherPlayerNumber(user));
            
            
            printAlphaBeta("A",(pitNum-playingPits),depth,value,alpha,beta,print,output);
            
                
                return value;
        }
    
    
    }

        
    return value;    
    }
    
    return value;
}    
    
    String  name;
    int  playerNum;
}
