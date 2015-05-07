package myProject

import scala.collection.mutable.Stack
import scala.collection.mutable.Queue
import scala.util.Random

class Controller(view:View,model:Model) {
  def SHOW_PLAYING_AREA{
    model.hand0.showHand
    view.displayThis("-----")
    model.hand1.showHand
    view.displayThis("-----")
    model.hand2.showHand
    view.displayThis("-----")
    model.hand3.showHand
    view.displayThis("-----")
    SHOW_PLAYER_ORDER
    view.displayThis("-----")
    view.displayThis("The Table:")
    view.displayThis("Player 0 has played: "+(model.playArea.cards(0).rank,model.playArea.cards(0).suit).toString)
    view.displayThis("Player 1 has played: "+(model.playArea.cards(1).rank,model.playArea.cards(1).suit).toString)
    view.displayThis("Player 2 has played: "+(model.playArea.cards(2).rank,model.playArea.cards(2).suit).toString)
    view.displayThis("Player 3 has played: "+(model.playArea.cards(3).rank,model.playArea.cards(3).suit).toString)
    view.displayThis("-----")
    view.displayThis("The Scores Are:")
    view.displayThis("Player 0: "+model.scoreBoard.scores(0).toString)
    view.displayThis("Player 1: "+model.scoreBoard.scores(1).toString)
    view.displayThis("Player 2: "+model.scoreBoard.scores(2).toString)
    view.displayThis("Player 3: "+model.scoreBoard.scores(3).toString)
  }
  def SHOW_PLAYER_ORDER{
    var queueString="The player order is: "
    for (y<-model.queue) queueString+=(y+", ")
    view.displayThis(queueString)
    }
  def ADVANCE_ORDER{
    var temp= model.queue.dequeue
    model.queue.enqueue(temp)
    view.displayThis("ORDER CYCLED")
    SHOW_PLAYER_ORDER
  }
  def WINNER_CHECK{
    var winString=""
    if (!model.scoreBoard.loserCheck){
      winString="No Winner Yet"
      view.displayThis(winString)
      } 
    else {
      if (model.scoreBoard.winner==0) winString="player0 wins"
      else if (model.scoreBoard.winner==1) winString="player1 wins"
      else if (model.scoreBoard.winner==2) winString="player2 wins"
      else winString="player3 wins"
      view.displayThis(winString)
      view.restartOrExit
    }
  }
  def DO_MOVE {
    model.doTrick
    SHOW_PLAYING_AREA
  }
  def DO_TURN{
    model.completeTrick
    view.displayThis("turn complete")
  }
  def DO_GAME{
    view.displayThis("FULL GAME")
    SHOW_PLAYING_AREA
    while (!model.flag) model.completeTrick
    SHOW_PLAYING_AREA
    WINNER_CHECK
  }
  def INIT{
    model.init
    model.newHand
    model.loadQueue
    view.displayThis("New Game Initialized")

  }
  
  def SET_STRATEGY(seat:Int, strat:Int){
    model.handArray(seat).strategy=strat  
    view.displayThis("player "+seat.toString+"'s strategy is ")
    view.displayThis(strat.toString)
  }
  
  def VIEW_STRATEGY(seat:Int){
    model.handArray(seat).strategy
    
  }
}