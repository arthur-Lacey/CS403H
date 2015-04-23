package myProject

import scala.collection.mutable.Stack
import scala.collection.mutable.Queue
import scala.util.Random

class Controller(view:View,model:Model) {
  val x=model.heartGame
  def SHOW_PLAYING_AREA{}
  def SHOW_PLAYER_ORDER{
    var queueString=""
    for (y<-x.queue) queueString+=(y+", ")
    view.showQueue(queueString)
    }
  def ADVANCE_ORDER{
    var temp= x.queue.dequeue
    x.queue.enqueue(temp)
    SHOW_PLAYER_ORDER
  }
  def WINNER_CHECK{
    var winString=""
    if (x.scoreBoard.loserCheck){
      winString="No Winner Yet"
      view.winner(winString)
      view.restartOrExit
    } 
    else {
      if (x.scoreBoard.winner==0) winString="player1 wins"
      else if (x.scoreBoard.winner==1) winString="player2 wins"
      else if (x.scoreBoard.winner==2) winString="player3 wins"
      else winString="player4 wins"
      view.winner(winString)
    }
  }
  def DO_MOVE {
    x.doTrick
    SHOW_PLAYING_AREA
  }
  def DO_TURN{
    x.completeTrick
    SHOW_PLAYING_AREA
  }
  def DO_GAME{}
  def INIT{
    x.heartGame.init

  }
}