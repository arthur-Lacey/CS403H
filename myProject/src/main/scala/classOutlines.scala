//package myProject
// things to add: force clubs to be the first lead suit, strategies, GUI

import scala.collection.mutable.Stack
import scala.collection.mutable.Queue
import scala.util.Random

object Hearts{
  private var game:SimControll = null
  println("Welcome to Hearts!")
  println("To begin a new game, type Hearts.NEW_GAME")
  println("To show the commands needed to play the game, type Hearts.help")
  def SHOW_PLAYING_AREA=game.showBoard
  def SHOW_PLAYER_ORDER=game.showQueue
 // def ADVANCE_ORDER=???
  def WINNER_CHECK=game.scoreBoard.loserCheck
  def DO_TRICK=game.completeTrick
  def DO_ROUND=game.completeRound
  def DO_GAME=game.completeGame
  def NEW_GAME{
    println("Game Start")
    help
    game=new SimControll
  }
  def help{
    println("GAME METHODS")
    println("--------------")
    println("NEW_GAME           -> starts a new game")
    println("SHOW_PLAYING_AREA  -> Shows the current layout and staus of each hand")
    println("SHOW_PLAYER_ORDER  -> Displays current player order (index reference)")
    println("WINNER_CHECK       -> Checks to see if the conditions are right for the the game to end")
    println("DO_TRICK           -> completes a trick of the game, or one complete cycle of turns")
    println("DO_ROUND           -> completes a full round meaning each hand will have been played out")
    println("DO_GAME            -> completes a full game and displays the winner")
  }
}


class Card (val rank: Int, val suit: String){
  private val validSuits:Array[String]=Array("Heart","Club","Spade","Diamond")
  private val validRanks:Array[Int]=Array(2,3,4,5,6,7,8,9,10,11,12,13,14)
  def isValid:Boolean=validSuits.contains(suit)&&validRanks.contains(rank)
  def isHeart:Boolean= (suit=="Heart")
  def isClub:Boolean= (suit=="Club")
  def isSpade:Boolean=(suit=="Spade")
  def isDiamond:Boolean=(suit=="Diamond")
  def isHigher(compare:Card):Boolean=(rank>compare.rank)
}




class Deck (val name: String, var cards: Stack[Card]){
  def showContents= cards
  def isEmpty= cards.isEmpty
  def topCard= cards.head
  def deal= cards.pop()
  def length=cards.length
  def shuffle={
    cards=Random.shuffle(cards)
  }
}

class Hand (val seat:Int){
  var cardSet= Set[Card]()
  private val nullCard=new Card(-1,null)
  def showPlayer= ???
  def showCards= println(cardSet)
  def draw(deck: Deck)={
    if (deck.isEmpty) this.cardSet
    else{
      for (index<- 0 to 12) cardSet=cardSet+deck.deal
    }
  }
 def play(dsuit:String):Card= {// add a parameter that checks for a particular suit, and allow an any command that allows any card to be played
     var x=nullCard
     if (dsuit!="Any"){
       val iter = cardSet.filter(_.suit==dsuit)
       if (iter.size==0) play("Any")
       else {
        x= iter.head
        cardSet=cardSet-x
        x
       }
       }
     else {
       x=cardSet.head
       cardSet=cardSet-x
       x
     }
 }
}

//Deleted the discard pile class because it's not needed for implimentation

class PlayArea{
  var lead="Any"//figure out how you want to calculate the lead
  val cards= Array.ofDim[Card](4)
  def max: Int= {
    var ind= -1
    var best=0
    for (x <- 0 to 3){
      if ((cards(x).suit==lead)&&(cards(x).rank>best)) {
        ind=x
        best=cards(x).rank
      }
    }
    ind
  }
  def assignCard(seat:Int,card:Card)={
    cards(seat)=card
  }
  def calculateScore={
    var score=0
    for (x <- 0 to 3){
      if (cards(x).isHeart) score+=1
      if (cards(x).rank==12 && cards(x).suit=="Spade") score+=13
    }  
    score
  }
}


class Scoreboard{
  val scores= Array.ofDim[Int](4)
  var round= Int
  def addScore(seat:Int, score:Int)={
    scores(seat)+=score
  }
  def loserCheck={
    var flag=false
    for (s <- scores){
      if (s>=100) flag=true
    }
    flag
  }
  def winner={
    var best= Int.MaxValue
    var ind= -1
    for (x<-0 to 3){
      if (scores(x)<best){
        best=scores(x)
        ind=x
      }
    }
    ind
  }

}

class TurnQueue extends Queue[Int]{
   def load(first:Int)={
     this.enqueue(first)
     this.enqueue((first+1)%4)
     this.enqueue((first+2)%4)
     this.enqueue((first+3)%4)
     println(this)
   }
   def next= this.head
 }


class SimControll{
  val scoreBoard = new Scoreboard
  val hand1= new Hand(0)
  val hand2= new Hand(1)
  val hand3= new Hand(2)
  val hand4= new Hand(3)
  var handArray=Array.ofDim[Hand](4)
  var playArea=new PlayArea
  val queue= new TurnQueue
  val twoHeart=new Card(2,"Heart")
  val threeHeart=new Card(3,"Heart")
  val fourHeart=new Card(4,"Heart")
  val fiveHeart=new Card(5,"Heart")
  val sixHeart=new Card(6,"Heart")
  val sevenHeart=new Card(7,"Heart")  
  val eightHeart=new Card(8,"Heart")
  val nineHeart=new Card(9,"Heart")
  val tenHeart=new Card(10,"Heart")
  val jackHeart=new Card(11,"Heart") 
  val queenHeart=new Card(12,"Heart")
  val kingHeart=new Card(13,"Heart")
  val aceHeart=new Card(14,"Heart")
  val twoSpade=new Card(2,"Spade")
  val threeSpade=new Card(3,"Spade")
  val fourSpade=new Card(4,"Spade")
  val fiveSpade=new Card(5,"Spade")
  val sixSpade=new Card(6,"Spade")
  val sevenSpade=new Card(7,"Spade")  
  val eightSpade=new Card(8,"Spade")
  val nineSpade=new Card(9,"Spade")
  val tenSpade=new Card(10,"Spade")
  val jackSpade=new Card(11,"Spade") 
  val queenSpade=new Card(12,"Spade")
  val kingSpade=new Card(13,"Spade")
  val aceSpade=new Card(14,"Spade")
  val twoClub=new Card(2,"Club")
  val threeClub=new Card(3,"Club")
  val fourClub=new Card(4,"Club")
  val fiveClub=new Card(5,"Club")
  val sixClub=new Card(6,"Club")
  val sevenClub=new Card(7,"Club")  
  val eightClub=new Card(8,"Club")
  val nineClub=new Card(9,"Club")
  val tenClub=new Card(10,"Club")
  val jackClub=new Card(11,"Club") 
  val queenClub=new Card(12,"Club")
  val kingClub=new Card(13,"Club")
  val aceClub=new Card(14,"Club")
  val twoDiamond=new Card(2,"Diamond")
  val threeDiamond=new Card(3,"Diamond")
  val fourDiamond=new Card(4,"Diamond")
  val fiveDiamond=new Card(5,"Diamond")
  val sixDiamond=new Card(6,"Diamond")
  val sevenDiamond=new Card(7,"Diamond")  
  val eightDiamond=new Card(8,"Diamond")
  val nineDiamond=new Card(9,"Diamond")
  val tenDiamond=new Card(10,"Diamond")
  val jackDiamond=new Card(11,"Diamond") 
  val queenDiamond=new Card(12,"Diamond")
  val kingDiamond=new Card(13,"Diamond")
  val aceDiamond=new Card(14,"Diamond")
  var round=0
  var lastHandWentTo= -1
  NewRound
  def NewRound={
    val standardDeck = new Deck("Standard",Stack[Card](twoHeart,threeHeart,fourHeart,fiveHeart,sixHeart,sevenHeart,
    eightHeart,nineHeart,tenHeart,jackHeart,queenHeart,kingHeart,aceHeart,twoSpade,threeSpade,
    fourSpade,fiveSpade,sixSpade,sevenSpade,eightSpade,nineSpade,tenSpade,jackSpade,queenSpade,
    kingSpade,aceSpade,twoClub,threeClub,fourClub,fiveClub,sixClub,sevenClub,eightClub,nineClub,
    tenClub,jackClub,queenClub,kingClub,aceClub,twoDiamond,threeDiamond,fourDiamond,fiveDiamond,
    sixDiamond,sevenDiamond,eightDiamond,nineDiamond,tenDiamond,jackDiamond,queenDiamond,kingDiamond,aceDiamond))
    standardDeck.shuffle
    hand1.draw(standardDeck)
    hand2.draw(standardDeck)
    hand3.draw(standardDeck)
    hand4.draw(standardDeck)
    /*
    println("Player 1's Hand")
    for (card <-hand1.cardSet) println((card.rank,card.suit))
    println("-----")
    println("Player 2's Hand")
    for (card <-hand2.cardSet) println((card.rank,card.suit))
    println("-----")
    println("Player 3's Hand")
    for (card <-hand3.cardSet) println((card.rank,card.suit))
    println("-----")
    println("Player 4's Hand")
    for (card <-hand4.cardSet) println((card.rank,card.suit))
    println("-----")
    */
    handArray(0)=hand1
    handArray(1)=hand2
    handArray(2)=hand3
    handArray(3)=hand4
    round=1
    }
  def loadQueue={
    if (round<=1){
      if (hand1.cardSet.contains(twoClub)) queue.load(0)
      else if (hand2.cardSet.contains(twoClub)) queue.load(1)
      else if (hand3.cardSet.contains(twoClub)) queue.load(2)
      else queue.load(3)
    }
    else queue.load(lastHandWentTo)
  }
  def doFirstTurn(seat:Int, playArea:PlayArea){
    val playedCard=handArray(seat).play("Any")
    playArea.lead=playedCard.suit
    playArea.assignCard(seat,playedCard)
  }
  def doRegularTurn(seat:Int,playArea:PlayArea){
    val playedCard=handArray(seat).play(playArea.lead)
    playArea.assignCard(seat,playedCard)
  }
  
  def showQueue{
    println("the queue is: ")
    for (x<- queue) println(x)
  }
  
  def completeTrick{
    println("Full Turn")
    loadQueue
    //showQueue
    playArea=new PlayArea
    var currTurn=queue.dequeue
    doFirstTurn(currTurn, playArea)
    //println("the first card played at index "+currTurn.toString+" is: "+playArea.cards(currTurn).rank+" "+playArea.cards(currTurn).suit)
    
    currTurn=queue.dequeue
    doRegularTurn(currTurn,playArea)
    //println("the second card played at index "+currTurn.toString+" is: "+playArea.cards(currTurn).rank+" "+playArea.cards(currTurn).suit)
    
    currTurn=queue.dequeue
    doRegularTurn(currTurn,playArea)
    //println("the third card played at index "+currTurn.toString+" is: "+playArea.cards(currTurn).rank+" "+playArea.cards(currTurn).suit)
    
    currTurn=queue.dequeue
    doRegularTurn(currTurn,playArea)
    //println("the last card played at index "+currTurn.toString+" is: "+playArea.cards(currTurn).rank+" "+playArea.cards(currTurn).suit)

    val score=playArea.calculateScore
    println("the score of the pile is: "+score.toString)
    
    scoreBoard.addScore(playArea.max,score)
    
    println("the player at index "+playArea.max.toString+" would take the pile")
    lastHandWentTo=playArea.max
    round=round+1
    println("")
  }
  def completeRound{
    println("Full Round")
    while (handArray(0).cardSet.size+handArray(1).cardSet.size+handArray(2).cardSet.size+handArray(2).cardSet.size>0){
        completeTrick
        }
    if (scoreBoard.loserCheck==false){
      NewRound
      println("------------------------------------------")
      println("Round Complete the score is:")
      println("Player 1: "+scoreBoard.scores(0).toString)
      println("Player 2: "+scoreBoard.scores(1).toString)
      println("Player 3: "+scoreBoard.scores(2).toString)
      println("Player 4: "+scoreBoard.scores(3).toString)
      println("")
    }
    else {
    println("")
    println("GAME OVER")
    if (scoreBoard.winner==0) println("player1 wins")
    else if (scoreBoard.winner==1) println("player2 wins")
    else if (scoreBoard.winner==2) println("player3 wins")
    else println("player4 wins")
    }
  }
  def completeGame{
    println("Full Game")
    while (scoreBoard.loserCheck==false){
      completeRound
    }
    
}
  def showBoard{
    println("CURRENT STATUS")
    println("------------------------")
    println("Player 1's Hand")
    for (card <-hand1.cardSet) println((card.rank,card.suit))
    println("-----")
    println("Player 2's Hand")
    for (card <-hand2.cardSet) println((card.rank,card.suit))
    println("-----")
    println("Player 3's Hand")
    for (card <-hand3.cardSet) println((card.rank,card.suit))
    println("-----")
    println("Player 4's Hand")
    for (card <-hand4.cardSet) println((card.rank,card.suit))
    println("------------------------------------------")
    println("The current score is:")
    println("Player 1: "+scoreBoard.scores(0).toString)
    println("Player 2: "+scoreBoard.scores(1).toString)
    println("Player 3: "+scoreBoard.scores(2).toString)
    println("Player 4: "+scoreBoard.scores(3).toString)
    println("")
    println("currently on the table:")
    println("Player 1 has played: "+(playArea.cards(0).rank,playArea.cards(0).suit).toString)
    println("Player 2 has played: "+(playArea.cards(1).rank,playArea.cards(1).suit).toString)
    println("Player 3 has played: "+(playArea.cards(2).rank,playArea.cards(2).suit).toString)
    println("Player 4 has played: "+(playArea.cards(3).rank,playArea.cards(3).suit).toString)
  }
}

/*
class View{
  def init(ctl: Controller)="tada"
  def displayPlayerOrder: String
}

class Controller(view: View, model: Model){
  def showPlayerOrder: String
  def advancePlayer: String
}
*/


//winner check method not needed because a method can be instituted in the scoreboard


