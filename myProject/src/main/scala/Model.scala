package myProject

import scala.collection.mutable.Stack
import scala.collection.mutable.Queue
import scala.util.Random

class Model {
  
  
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

class Deck{
  private val validSuits:Array[String]=Array("Heart","Club","Spade","Diamond")
  private val validRanks:Array[Int]=Array(2,3,4,5,6,7,8,9,10,11,12,13,14)
  var cards= new Stack[Card]
  def init{
    for (suit<-validSuits){
      for(rank<-validRanks){
        var newCard=new Card(rank,suit)
        cards.push(newCard)
      }
    }
  }
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
  def init{
    cardSet=Set[Card]()
  }
  private val nullCard=new Card(-1,null)
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
  def init = {
    for (x<-0 to 3) scores(x)=0
  }
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

class heartGame {
  
  val deck=new Deck
  val scoreBoard = new Scoreboard
  val hand0= new Hand(0)
  val hand1= new Hand(1)
  val hand2= new Hand(2)
  val hand3= new Hand(3)
  var handArray=Array.ofDim[Hand](4)
  var playArea=new PlayArea
  val queue= new TurnQueue
  var round=0
  var trick=0
  var lastHandWentTo= -1
  
  def init{
    scoreBoard.init
    hand0.init
    hand1.init
    hand2.init
    hand3.init
    round=0
    trick=0
    lastHandWentTo= -1
  }
  
  def newRound{
    deck.init
    deck.shuffle
    hand0.draw(deck)
    hand1.draw(deck)
    hand2.draw(deck)
    hand3.draw(deck)
    handArray(0)=hand0
    handArray(1)=hand1
    handArray(2)=hand2
    handArray(3)=hand3
    trick=0
    round+=1 
    println("Player 1's Hand")
    for (card <-hand0.cardSet) println((card.rank,card.suit))
    println("-----")
    println("Player 2's Hand")
    for (card <-hand1.cardSet) println((card.rank,card.suit))
    println("-----")
    println("Player 3's Hand")
    for (card <-hand2.cardSet) println((card.rank,card.suit))
    println("-----")
    println("Player 4's Hand")
    for (card <-hand3.cardSet) println((card.rank,card.suit))
    println("-----")
  }
  
  
  
  def loadQueue={
    val twoClub= new Card(2,"Club")
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
    trick+=1
  }
  
  def doRegularTurn(seat:Int,playArea:PlayArea){
    val playedCard=handArray(seat).play(playArea.lead)
    playArea.assignCard(seat,playedCard)
    trick+=1
  } 
  
  def doTrick{// need to figure out how best to determine if a lead suit needs to be picked
    if (trick==0){
      loadQueue
      playArea=new PlayArea
      doFirstTurn(queue.dequeue,playArea)
    }
    else doRegularTurn(queue.dequeue,playArea)
    if (trick==3) {
      val score=playArea.calculateScore
      scoreBoard.addScore(playArea.max,score)
      lastHandWentTo=playArea.max
      trick=0
    }
  }
  def completeTrick{
    doTrick
    doTrick
    doTrick
    doTrick
  }
  
  }
}