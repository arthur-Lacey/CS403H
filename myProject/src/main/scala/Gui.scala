package myProject
import scala.swing._
//import Controller

object Gui extends View{
  
  val Frame=new MainFrame
  
  val gui=new BoxPanel(Orientation.Horizontal){
    import BorderPanel.Position._
    
    val menuArea=new BoxPanel(Orientation.Vertical){
      //add actions for buttons
      
      val moveButton=new Button("Do Move") {
        case class moveAction(t:String) extends Action(t){
          def apply()=controller.get.DO_MOVE
        }
        
        val a=new moveAction("move")
        this.action_=(a)
        horizontalAlignment_=(Alignment.Left)
        verticalAlignment_=(Alignment.Top)
      }
      
      
      val turnButton=new Button("Do Turn"){
        case class turnAction(t:String) extends Action(t){
          def apply()=controller.get.DO_TURN
        }

        val a=new turnAction("turn")
        this.action_=(a)
        verticalAlignment_=(Alignment.Top)
      }

      val gameButton=new Button("Do Game"){
        case class gameAction(t:String) extends Action(t){
          def apply()=controller.get.DO_GAME
        }
        
        val a=new gameAction("game")
        this.action_=(a)
        verticalAlignment_=(Alignment.Top)
      }
      
      val advanceButton=new Button("Advance Player Order"){
        case class advanceAction(t:String) extends Action(t){
          def apply()=controller.get.ADVANCE_ORDER
        }
        
        val a=new advanceAction("advance")
        this.action_=(a)
        verticalAlignment_=(Alignment.Top)
      }
      
      val playerOrder=new Label("Player Order Here"){
        verticalAlignment_=(Alignment.Top)
        //this.text_=("1,2,3,4") //resets the text value
        //var order=controller.get.
        //def update(s:String)={text_=(s)}
      }
      
      val stratMenu=new MenuBar(){
        maximumSize_=(new Dimension(100,100))
        minimumSize_=(new Dimension(100,50))
        //verticalAlignment_=(Alignment.Top)
        contents+=new Menu("Set Strategies"){
          var strategy=0
          val m1=new Menu("Strategy 1: Description"){
            strategy=0
            
            //apply this template to all following options
            val p1=new MenuItem("Player 1"){
              //modify action to be a controller set strategy thing
              case class strategyAction(t:String) extends Action(t){def apply()=controller.get.DO_GAME              }
              val a=new strategyAction("Set: Player 1");this.action_=(a)
            }
            val p2=new MenuItem("Player 2")
            val p3=new MenuItem("Player 3")
            val p4=new MenuItem("Player 4")
            contents+=p1;contents+=p2;contents+=p3;contents+=p4;
          }
          val m2=new Menu("Strategy 2: Description"){
            strategy=1
            val p1=new MenuItem("Player 1")
            val p2=new MenuItem("Player 2")
            val p3=new MenuItem("Player 3")
            val p4=new MenuItem("Player 4")
            contents+=p1;contents+=p2;contents+=p3;contents+=p4;
          }
          val m3=new Menu("Strategy 3: Description"){
            strategy=2
            val p1=new MenuItem("Player 1")
            val p2=new MenuItem("Player 2")
            val p3=new MenuItem("Player 3")
            val p4=new MenuItem("Player 4")
            contents+=p1;contents+=p2;contents+=p3;contents+=p4;
          }
          val m4=new Menu("Strategy 4: Description"){
            strategy=3
            val p1=new MenuItem("Player 1")
            val p2=new MenuItem("Player 2")
            val p3=new MenuItem("Player 3")
            val p4=new MenuItem("Player 4")
            contents+=p1;contents+=p2;contents+=p3;contents+=p4;
          }
          contents+=m1
          contents+=m2
          contents+=m3
          contents+=m4
        }
      }
      
      
      contents += stratMenu
      
      contents += moveButton   
      contents += turnButton
      contents += gameButton
      contents += advanceButton
      contents += playerOrder
      
      
      contents += new Label("Player Strategies:")
      contents += new Label("Player 1: ")
      contents += new Label("Player 2: ")
      contents += new Label("Player 3: ")
      contents += new Label("Player 4: ")
      val reset=new Button("RESET"){
        case class resetAction(t:String) extends Action(t){
          def apply()=controller.get.INIT
        }
        
        val a=new resetAction("reset")
        this.action_=(a)        
      }
      
      contents+=reset
      //this.size(Dimension(150,500))//width,height
    }
    
    contents += menuArea
    
    
    
    
    val playArea=new BorderPanel{
      
      case class hand(orientation: Orientation.Value = Orientation.Horizontal) extends BoxPanel(orientation){
        val cards=Array[Label](new Label("Card 1 "),
        new Label("Card 2 "), new Label("Card 3 "),
        new Label("Card 4 "), new Label("Card 5 "),
        new Label("Card 6 "), new Label("Card 7 "),
        new Label("Card 8 "), new Label("Card 9 "),
        new Label("Card 10 "),new Label("Card 11 "),
        new Label("Card 12 "),new Label("Card 13 "))
        
        def getCards():Array[Label]=cards
      }
      
      val hand0= new hand(Orientation.Horizontal){
        for(c<-cards) contents+=c
      } 
      layout+= hand0 -> North
      
      
      val hand1= new hand(Orientation.Vertical){
        for(c<-cards) contents += c
      } 
      layout+= hand1 -> West
      
      val hand2= new hand(Orientation.Horizontal){        
        for(c<-cards) contents += c
      } 
      layout+=hand2 -> South
      
      val hand3=new hand(Orientation.Vertical){
        for(c<-cards) contents += c
      } 
      layout+=hand3 -> East
      
       
      val cardArea=new BorderPanel{
        val p1Card=new Label("p1Card")
        val p2Card=new Label("p2Card")
        val p3Card=new Label("p3Card")
        val p4Card=new Label("p4Card")
        
        layout+=p1Card->North
        layout+=p2Card->West
        layout+=p3Card->South
        layout+=p4Card->East
      }
      
      layout+=cardArea -> Center
    }
    
    contents += playArea
    
  }
 
  //def run(): Unit = {
    Frame.contents=gui
    Frame.size=new Dimension(700,400)
    Frame.visible=true
  //}
  
  def card0Update(s:String)={
    gui.cardArea.p1Card.text_=(s)
  }
  
  def card1update(s:String)={
    gui.cardArea.p2Card.text_=(s)
  }
  
  
  def card2Update(s:String)={
    gui.cardArea.p3Card.text_=(s)
  }
  
  def card3Update(s:String)={
    gui.cardArea.p4Card.text_=(s)
  }
    
    
    
    
  override def showOrder(s:String){
    gui.menuArea.playerOrder.text_=(s)
  }
    
    
    
  override def showHand0(s:String)={  //shows player 1's hand
    println(s)
    for(i<-0 until gui.playArea.hand0.getCards.length){
      gui.playArea.hand0.getCards()(i).text_=(s.substring(i*4,i*4+4))
    }
  }
  
  override def showHand1(s:String)={   //shows player 2's hand
    println(s)
    for(i<-0 until gui.playArea.hand1.getCards.length){
      gui.playArea.hand1.getCards()(i).text_=(s.substring(i*4,i*4+4))
    }
  }
  
  override def showHand2(s:String)={   //shows player 3's hand
    println(s)
    for(i<-0 until gui.playArea.hand2.getCards.length){
      gui.playArea.hand2.getCards()(i).text_=(s.substring(i*4,i*4+4))
    }
  }
  
  override def showHand3(s:String)={   //shows player 4's hand
    println(s)
    for(i<-0 until gui.playArea.hand3.getCards.length){
      gui.playArea.hand3.getCards()(i).text_=(s.substring(i*4,i*4+4))
    }
  }
  
  override def refresh()={
    controller.get.SHOW_PLAYER_ORDER
    controller.get.SHOW_PLAYING_AREA
  }
}
/*
def main()={
  HeartsGui.run
}*/