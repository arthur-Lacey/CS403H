package myProject


class View {
 var controller:Option[Controller]= None
 def init(ctrl:Controller)= {
   var input=0
   controller=Some(ctrl)
   while(input!=9) {
    if (input==0) help
    else if (input==1) controller.get.SHOW_PLAYING_AREA
    else if (input==2) controller.get.SHOW_PLAYER_ORDER
    else if (input==3) controller.get.ADVANCE_ORDER
    else if (input==4) controller.get.INIT
    else if (input==5) controller.get.WINNER_CHECK
    else if (input==6) controller.get.DO_MOVE
    else if (input==7) controller.get.DO_TURN
    else if (input==8) controller.get.DO_GAME
    else println("invalid input")
    input=readInt()
 }
 } 
 
  def MainMenu{
  println("Welcome to Hearts!")
  println("To begin a restart the game, type 4")
  println("To show the commands needed to play the game, type 0")
  println("To exit the game, type 9")
  println("")
 }
 
 def error{
   println("Invalid input, please try again.  If you need help, type 0")
 }
 
 
 def newGame{
   println("New Game Started")
 }
 
 def restartOrExit{
   println("Thanks For Playing!")
   println("Press 4 to restart, or 9 to exit")
 }
 
 
  def help{
    println("GAME METHODS")
    println("--------------")
    println("0-> displays methods for the game")
    println("1-> Shows the playing area")
    println("2-> Shows the player order")
    println("3-> Advances the player order")
    println("4-> Starts a new game")
    println("5-> Checks for a winner")
    println("6-> completes a move for the player currently up")
    println("7-> computes four moves, a complete trick")
    println("8-> completes a full game and displays the winner")
    println("9-> exit the game")
    println("")
  }
  
  def displayThis(input:String){
    println(input)
  }

}

