package hearts

object Main {
  
  def main(args: Array[String]): Unit = {
      
    val model = new Model
    val view  = new View   
    val controller = new Controller(view, model)
    
    view.init(controller)    
  }
  
}

class Model{}

class View{
  def init(ctl: Controller)="tada"
  def displayPlayerOrder: String
}

class Controller(view: View, model: Model)
  def showPlayerOrder: String
  def advancePlayer: String
  
  
class PlayerOrder extends scala.mutable.Queue=
  
