package com.gui.testing
import swing._
import event._
import java.awt.Color
import twitter4j.TwitterFactory
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken


object Main {
  var t=0;
  //var sensorArray = new ArrayList()
  
  var name =""
  var sensorArray = new java.util.ArrayList[SensorDetails]()
  val directionsArea = new TextArea("Result Displayed Here.....")
  var clickedButtonId: String = null
  def main(args: Array[String]) {
    Swing.onEDT(initSensor)
  //  Swing.onEDT(initGUI) // Scala equivalent of Java's SwingUtilities.invokeLater
  }
  /*
   * The functions for various operations
   * in the IDE are implemented.
   */
  def sum( ): Double = {
      var total = 0;
     var i=0;
     var j=0;
     for(i <- 0 to sensorArray.size()-1)
     {
    	 var temp: SensorDetails = sensorArray.get(i); 
    	 if(temp.onOffFlag==1)
     	 total = total + temp.measuredValue
    }
     
            println("Total is " + total);
      return total;

}
  
   def Avg():Double =
     {
      // Finding the largest element
        var total = 0.0;
        var avg = 0.0; 
     total = sum()
      avg = total / sensorArray.size()-1;
     return avg ; 
      
     }
   
      def Max():Double =
     {
      // Finding the largest element
        var max = 0 
     for(i <- 0 to sensorArray.size()-1)
     {
    	 var temp: SensorDetails = sensorArray.get(i); 
    	  if(temp.onOffFlag==1)
    	  {
     	 if( temp.maxValue > max)
     	 {
     	   max = temp.maxValue
     	 }
    	  }
    }
      //avg = total / sensorArray.size();
     return max ; 
      
     }
    
     def Min():Double =
     {
      // Finding the largest element
        var min = 9999 
     for(i <- 0 to sensorArray.size()-1)
     {
    	 var temp: SensorDetails = sensorArray.get(i); 
    	  if(temp.onOffFlag==1)
    	  {
     	 if( temp.minValue < min)
     	 {
     	   min = temp.minValue
     	 }
    	  }
    	  
    }
     return min ; 
      
     }
     
     def Count():Int =
     {
      // Finding the largest element
        var count = 0;
     for(i <- 0 to sensorArray.size()-1)
     {
    	 var temp: SensorDetails = sensorArray.get(i); 
    	  if(temp.onOffFlag==1)
     	 count= count+1;
    }
      //avg = total / sensorArray.size();
     return count ; 
      
     }
     
     def Demo():Int =
     {
      // Finding the largest element
        var a = Count();
      if((a % 2) == 0)
      {
        return 1
      }
      else
        return 0
      
     }
     
 /*
  * This frame displays a panel that prompts
  * the user to enter the number of sensors required
  * based on the system being implemented. This makes the IDE 
  * scalable to any application.
  * */    
           
 def initSensor() {
  val SensorCount  = new Frame  { title = "Sensor Count" 
								  //size = new Dimension(800,600)
								  }
  
  val sensorCountPanel = new BoxPanel(Orientation.Vertical) {
  background= Color.pink
  val Input = new Label("Enter the number of sensors you want to Register in Smart Connectors") 
  val Intro = new Label("Welcome to Smart Connectors IDE !"){font = new Font("Arial Black", 0,14) } 
  val Output = new TextField("")
  contents += Intro
  contents += Input
  
  
  contents += Output
  val accept = new Button(Action("OK") {
  var sensorNumber = Output.text
 

if(sensorNumber.toInt >= 50)
  {
    var twitter = new TwitterFactory().getInstance();
  twitter.setOAuthConsumer("NJVF2uVbmu90jKuZ8HOyrRv0W", "cyLKKUGpWWOfKQm0tHo1sLHGLQ8LaYoMJBw7XHpLk0ldqthCpO");
  twitter.setOAuthAccessToken(new AccessToken("2173722175-akIkXpzbSzFQ2iGuR4r5N4NgahNCMqvIRuARt31","5jTtUDNYM7us6DHmDFHSNnDFV2GCuks1HwbalHjMGBU5v"));

  twitter.updateStatus("Alert!!!: Sensors exceeded the threshold value 50; Entered value is  "+sensorNumber);
 
	  initGUI(sensorNumber.toInt)
      SensorCount.dispose()
  }
  else
  {
   
	  initGUI(sensorNumber.toInt)
      SensorCount.dispose() 
  }      
        
        
 
  
  
  
  
  
  
  
  })//END OF aCTION
contents += accept

  }//END OF PANEL
 SensorCount.contents = sensorCountPanel
  //SensorCount.size = new Dimension(200,200)
   SensorCount.visible  = true

  }//END OF DEF
 
                            
  /*
   * This frame displays the Sensor registration panel
   * where the user can set the threshold values and the 
   * measured value for a particular sensor. The sensor can be 
   * made enabled or disabled based on the requirement.
   */
 def initFrame() {
   
   
  val dataReg  = new Frame  { title = "Sensor Registeration" 
                            }

 object maxValue extends TextField { columns = 5 }
 object minValue extends TextField { columns = 5 }
 object Name extends TextField { columns = 5 }
 object measuredValue extends TextField { columns = 5 }

 val button = new Button {text = "SAVE" }
 val okButton = new Button {text = "EXIT"}
// val onOffRadio = (new RadioButton("Close Sensor"){name="OFF"},new RadioButton("Connect Sensor"){name="ON"})
// new ButtonGroup(onOffRadio: _*);
 val dirFileSelector = List(
    new RadioButton() {
      name = "Connect_sensor"
      text = "CONNECT"
         background = Color.pink

    },
    new RadioButton() {
      name = "Disconnect_sensor"
      text = "CLOSE"
         background = Color.pink
    }
  )

  new ButtonGroup(dirFileSelector: _*
      )

 

 val confirmationlabel = new Label {text = ""}
var existingflagTemp = 0 
var onOff = 0
//var onoffbtntext:String="OFF"
  for(i <- 0 to sensorArray.size()-1){
	 var temp: SensorDetails = sensorArray.get(i); 
	 if(clickedButtonId == temp.id){
	   existingflagTemp=1
	   maxValue.text=temp.maxValue.toString
	   minValue.text=temp.minValue.toString
	   measuredValue.text=temp.measuredValue.toString
	   Name.text=temp.name.toString()
	   /*if(temp.onOffFlag==1){
	     onoffbtntext=="ON"
	   }
	   else{
		   onoffbtntext=="OFF"
	   }*/
	   
	 }
}
//val onOffBtn = new Button {text = onoffbtntext}
val sensorRegistrationPanel = new BoxPanel(Orientation.Vertical) {
  background = Color.pink
 

 contents += new Label(" Sensor Name :")

 contents += Name

 contents += new Label(" Max Value :")

 contents += maxValue

 contents += new Label(" Min Value :")

 contents += minValue

 contents += new Label(" Measured Value :")

 contents += measuredValue

 //contents += onOffBtn
 dirFileSelector.foreach(listenTo(_))
    reactions += {
      case ButtonClicked(button) => {
        button.name match {
          case "Connect_sensor" => onOff =1 
          case "Disconnect_sensor" =>  onOff =0
        }
      }
    }
 contents ++= dirFileSelector

 contents += button
 
 contents += okButton

 contents += confirmationlabel

 border = Swing.EmptyBorder(75, 50, 75, 50)

  }
  
  val onOffRadioReactor = new Reactor{}	
  onOffRadioReactor.reactions += {
    case SelectionChanged(onOffRadio) => {
      onOffRadio.name match {
        case "OFF" => onOff=0
        case "ON" => onOff=1
      }
    }
  }
  val okBtnreactor = new Reactor{}
  okBtnreactor.listenTo(okButton)
  okBtnreactor.reactions  += {
    case ButtonClicked(okButton)=>
      dataReg.close
  }
 
 val r2 = new Reactor{}

 r2.listenTo(button)

 var max = 0

 var min = 0

 var measured = 0

 var sensorId = 0

 r2.reactions += {

 case ButtonClicked(button) => 

 

if(Name.text.equals("") && maxValue.text.equals("") && minValue.text.equals("") && measuredValue.text.equals("")){
 confirmationlabel.text = "Enter all the values"
 }

 else{

 //val tempSensorDataobj

 //for(int i=0;i<sensorArray.size();i++){

 // sensorArray.foreach{=>}

 //}

 max = maxValue.text.toInt

 min = minValue.text.toInt

 measured = measuredValue.text.toInt
  
 name = Name.text

 if(max < min || (measured > max) || (measured < min)){

 confirmationlabel.text = "Invalid entry. Values should be: Min  value < Measured value < Max value"

 }

 else{

 if(onOff==0){

 confirmationlabel.text = "Sensor Closed"

 }

 if(onOff==1){

 confirmationlabel.text = "Sensor Connected"

 }
 
var existingSensorFlag = 0 
for(i <- 0 to sensorArray.size()-1){
 var temp: SensorDetails = sensorArray.get(i); 
 if(clickedButtonId == temp.id){
   existingSensorFlag=1
   temp.maxValue = max
   temp.minValue  = min
   temp.measuredValue  = measured
   temp.onOffFlag = onOff
   temp.name = name
   directionsArea.text = name
 }

}
 if(existingSensorFlag == 0){
	 val sensorDataObject = new SensorDetails()
	 sensorDataObject.name = name
	 sensorDataObject.maxValue = max
	
	 sensorDataObject.minValue = min
	
	 sensorDataObject.measuredValue = measured
	
	 sensorDataObject.onOffFlag = onOff
	
	 sensorDataObject.id = clickedButtonId
	 sensorArray.add(sensorDataObject)
	 //directionsArea.text = name
 }
  
    
 
   
 

 }

 }

 }
 dataReg.contents = sensorRegistrationPanel
   dataReg.visible  = true
     
  }
 
 /*
  * This frame presents the GUI where the number of sensors 
  * needed by the user are displayed along with the operations 
  * that can be performed. the result of operation is shown in the 
  * Text Area.
  * 
  * */

def print()
{
  

 for(j <- 0 to sensorArray.size()-1){
    var temp1: SensorDetails = sensorArray.get(j); 
   
    
 }

}
  def initGUI(sn :Int) {
 val recipeList = new ListView(List(""))
val ingredientList = new ListView(List(""))
val directionsArea1 = new TextArea("Result Displayed Here.....")

val ingredientNameField = new TextField("Pop Tart ") 
val amountField = new TextField("1 Packet")

val ingerdientsListPanel = new BorderPanel{

  layout+=(new GridPanel(2,2){
   
      val Addition = new Button(Action("Add") {
        var a = sum();
        
         directionsArea.text = "Computed value from the Sensors "+ a.toString()
    

    })
      
    contents+=Addition
   val Average = new Button(Action("Avg") {
        var a = Avg();
         directionsArea.text ="Computed value from the Sensors "+ a.toString()
   print();
   })
      
    contents+=Average
   val MaxValue = new Button(Action("Max") {
        var a = Max();
         directionsArea.text = "Computed value from the Sensors "+ a.toString()
         print();
    })
      
    contents+=MaxValue
   val MinValue = new Button(Action("Min") {
        var a = Min();
         directionsArea.text = "Computed value from the Sensors "+ a.toString()
         print();
    })
      
    contents+=MinValue  

   val Count1 = new Button(Action("Count") {
        var a = Count();
         directionsArea.text = "Computed value from the Sensors "+a.toString()
         print();
    })
      
    contents += Count1
    
     val Exampledemo = new Button(Action("Demo Example") {
        var a = Demo();
        if(a==1)
         directionsArea.text = "Bulb Switched ON!!!"
            if(a==0)
         directionsArea.text = "Bulb Switched OFF!!!"
    })  
      contents += Exampledemo
      val twitter = new Button(Action("POST TO TWITTER"){
        
        var twitter = new TwitterFactory().getInstance();
  twitter.setOAuthConsumer("NJVF2uVbmu90jKuZ8HOyrRv0W", "cyLKKUGpWWOfKQm0tHo1sLHGLQ8LaYoMJBw7XHpLk0ldqthCpO");
  twitter.setOAuthAccessToken(new AccessToken("2173722175-akIkXpzbSzFQ2iGuR4r5N4NgahNCMqvIRuARt31","5jTtUDNYM7us6DHmDFHSNnDFV2GCuks1HwbalHjMGBU5v"));

  twitter.updateStatus("Smart Connector: Updates:  "+directionsArea.text  );
        
      })
      contents += twitter
    
  } -> BorderPanel.Position.North)
  
  layout +=(new ScrollPane(ingredientList)
  ->BorderPanel.Position.Center)
  
}

val ingerdientsDataPanel = new BorderPanel{
  layout+=(new GridPanel(2,1){
    contents+=new Label("Name")
    
    contents+=new Label("Amount")
    
  } -> BorderPanel.Position.West)
  
  layout +=(new GridPanel(2,1){
    contents+=ingredientNameField
    
    contents+=amountField
    
  }  ->BorderPanel.Position.Center)
  
}

val recipeListPanel = new BorderPanel{
 
   layout += (new GridPanel(0,1){
 
     for(i <- 1 to sn)
     {	
    	 val button = new Button { text  = "Sensor "+i }//temSensorDetails.name
    	  
    	
    //println(Name.text);
    contents+=button 
      
     val r = new Reactor {}
     r.listenTo(button)
      r.reactions += {
      case event.ButtonClicked(_) =>  
      	clickedButtonId="button"+i

        initFrame() // println("Clicked")
    }
    
    }
  } -> BorderPanel.Position.West)
  
  layout +=(new ScrollPane(recipeList)
  ->BorderPanel.Position.Center)
  
}

 val frame = new MainFrame{
   title= "Scala Connectors"
     contents = new BorderPanel{
     layout +=(new ScrollPane(recipeListPanel)  ->BorderPanel.Position.West)
  
     layout+= (new SplitPane(Orientation.Horizontal,
      new BorderPanel {
   
    layout+=(ingerdientsListPanel
        ->BorderPanel.Position.Center)
//        layout+=(ingerdientsDataPanel
//        ->BorderPanel.Position.Center)
  },
  new ScrollPane(directionsArea))
   ->BorderPanel.Position.Center)
   }
   size = new Dimension(800,600)
   
   }
 frame.visible=true;

  }
}
