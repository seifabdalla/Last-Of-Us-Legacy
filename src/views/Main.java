package views;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.*;
import javafx.geometry.*;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.*;
import model.characters.*;
import model.characters.Character;
import model.collectibles.Supply;
import model.collectibles.Vaccine;

public class Main extends Application {
 static Scene scene1;
 static Stage window;	
 static Rectangle g;
 public static GridPane gg;
 static ImageView ist;
 static Button a,c,u,m;
 static HBox bp;
 static VBox inf;
 static StackPane rr;
 static Rectangle r1,r2,r3,r4,r5,r6,r7,r8;
 public static ArrayList <StackPane> r;
 static VBox heroc;

 @Override
	public void start(Stage primaryStage) throws Exception {
		window=primaryStage;
		window.setTitle("Last Of Us-Legacy");
		Button button=new Button("Start Game");
		StackPane root= new StackPane();
		
		a=new Button("attack");
		a.setVisible(false);
	    a.setStyle("-fx-background-color: green;");
	    a.setTextFill(Color.WHITE);
	    a.setPrefWidth(200);
	    a.setPrefHeight(70);
	    m=new Button("move");
	    m.setVisible(false);
	    m.setStyle("-fx-background-color: green;");
	    m.setTextFill(Color.WHITE);
	    m.setPrefWidth(200);
	    m.setPrefHeight(70);
	    u=new Button("useSpecial");
	    u.setVisible(false);
	    u.setStyle("-fx-background-color: green;");
	    u.setTextFill(Color.WHITE);
	    u.setPrefWidth(200);
	    u.setPrefHeight(70);
	    c=new Button("cure");
	    c.setVisible(false);
	    c.setStyle("-fx-background-color: green;");
	    c.setTextFill(Color.WHITE);
	    c.setPrefWidth(200);
	    c.setPrefHeight(70);
	    Button e=new Button("end turn");
	    e.setStyle("-fx-background-color: green;");
	    e.setTextFill(Color.WHITE);
	    e.setPrefWidth(200);
	    e.setPrefHeight(70);
		Button exit=new Button("Exit");
		exit.setOnAction( ee-> window.close());
		
		ArrayList<Button> gc =new ArrayList<Button>();
		gc.add(a);
		gc.add(m);
		gc.add(u);
		gc.add(c);
		gc.add(e);
		for(int i=0;i<gc.size();i++) {
			Button bt=gc.get(i);
			bt.setOnMouseEntered(egc -> bt.setStyle("-fx-background-color: #0DC92D;"));
		    bt.setOnMouseExited(egc1 -> bt.setStyle("-fx-background-color: green;"));
		}
		
		
	   /* BorderPane options=new BorderPane();
	    options.setPadding(new Insets(20, 0, 0,30));
	    
	    
		GridPane optionsGrid = new GridPane();
		optionsGrid.setAlignment(Pos.CENTER);
		options.setCenter(optionsGrid);
		

		// create brightness label and slider
		Label brightnessLabel = new Label("Brightness:");
		Slider brightnessSlider = new Slider(0, 1, 0.5);
		optionsGrid.add(brightnessLabel, 0, 0);
		optionsGrid.add(brightnessSlider, 1, 0);

		// create audio label and slider
		Label audioLabel = new Label("Audio Volume:");
		Slider audioSlider = new Slider(0, 1, 0.5);
		optionsGrid.add(audioLabel, 0, 1);
		optionsGrid.add(audioSlider, 1, 1);*/
        
        
		button.setPrefSize(250,80);
		exit.setPrefSize(250,80);
		button.setVisible(true);
		root.getChildren().add(button);
		root.getChildren().add(exit);
		root.setAlignment(button, Pos.CENTER_RIGHT);
		root.setMargin(button, new Insets(0,400,200,0));
		root.setAlignment(exit, Pos.CENTER_RIGHT);
		root.setMargin(exit, new Insets(200,400,0,0));
		button.toFront();
		button.setStyle("-fx-background-color: #ace4ca;");
		button.setTextFill(Color.BLACK);
		exit.setTextFill(Color.BLACK);
		exit.setStyle("-fx-background-color: #ace4ca;");
		button.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
		exit.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
	        
		BackgroundImage backgroundImage = new BackgroundImage(
				new Image(Main.class.getResourceAsStream("menu.jpg")),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
		bp=new HBox();
		bp.setBackground(new Background(new BackgroundFill(Color.web("#F1F7ED"), null, null)));
		gg =new GridPane();
		rr=new StackPane();
		rr.getChildren().add(gg);
	    bp.getChildren().add(rr);
		
		Image cell=new Image(Main.class.getResourceAsStream("Celll.jpeg"));;
		ImagePattern imagePattern = new ImagePattern(cell);
	
		for (int i = 0; i < 15; i++) {
		    for (int j = 0; j < 15; j++) {
		    	Rectangle rectangle = new Rectangle(70, 65);
		        rectangle.setFill(imagePattern);
		        gg.add(rectangle, j, 14 - i);
		    }
		}
		

        
                BackgroundImage backgroundImage2 = new BackgroundImage(
                new Image(Main.class.getResourceAsStream("load1.jpg")),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        
        bp.setPadding(new Insets(0,0,50,30));
        heroc =new VBox(40);
        inf =new VBox(40);
        VBox inf2=new VBox();
        
        StackPane st1=new StackPane();
        StackPane st2=new StackPane();
        StackPane st3=new StackPane();
        StackPane st4=new StackPane();
        StackPane st5=new StackPane();
        StackPane st6=new StackPane();
        StackPane st7=new StackPane();
        StackPane st8=new StackPane();
      
        
        r1=new Rectangle(250,240);
        r2=new Rectangle(250,240);
        r3=new Rectangle(250,240);
        r4=new Rectangle(250,240);
        r5=new Rectangle(250,240);
        r6=new Rectangle(250,240);
        r7=new Rectangle(250,240);
        r8=new Rectangle(250,240);
        	
        r=new ArrayList<StackPane>();
        
        r.add(st1);
        r.add(st2);
        r.add(st3);
        r.add(st4);
        r.add(st5);
        r.add(st6);
        r.add(st7);
        r.add(st8);
        
        Rectangle[] ra= {r1,r2,r3,r4,r5,r6,r7,r8};
        for(int i=0;i<ra.length;i++){
        	ra[i].setFill(Color.web("#EEECE5"));
            ra[i].setStroke(Color.BLACK);
            ra[i].setVisible(false);
        }
        
        st1.getChildren().add(r1);
        st2.getChildren().add(r2);
        st3.getChildren().add(r3);
        st4.getChildren().add(r4);
        st5.getChildren().add(r5);
        st6.getChildren().add(r6);
        st7.getChildren().add(r7);
        st8.getChildren().add(r8);
    
		bp.getChildren().add(heroc);
		bp.getChildren().add(inf);
		bp.getChildren().add(inf2);
		bp.setMargin(rr, new Insets(40,0,0,20));
		bp.setMargin(heroc,new Insets(45,0,0,30));
		bp.setMargin(inf, new Insets(45,0,0,0));
		bp.setMargin(inf2,new Insets(45,40,0,0));
		heroc.getChildren().add(st1);
		inf.getChildren().add(st2);
        inf2.getChildren().add(st3);
        inf2.getChildren().add(st4);
	    inf2.getChildren().add(st5);
        inf2.getChildren().add(st6);
		heroc.getChildren().add(a);
	    heroc.getChildren().add(e);
	    inf.getChildren().add(c);
	    inf.getChildren().add(u);
	    inf.getChildren().add(st7);
	    heroc.getChildren().add(st8);
	    
	    heroc.setMargin(st1,new Insets(0,0,40,0));
	    inf.setMargin(st2,new Insets(0,0,40,0));
	    heroc.setMargin(a,new Insets(40,0,0,20));
	    heroc.setMargin(e,new Insets(40,0,0,20));
	    inf2.setMargin(c,new Insets(40,0,0,20));
		inf2.setMargin(u,new Insets(40,0,0,20));
		inf.setMargin(st7,new Insets(102,0,0,0));
		heroc.setMargin(st8,new Insets(102,0,0,0));
		
	    //e.setAlignment(Pos.CENTER);


        root.setBackground(new Background(backgroundImage2));
	    
        
        scene1=new Scene(root);
		
        StackPane transitionLayout = new StackPane();
	    transitionLayout.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        Rectangle transitionRectangle = new Rectangle(400, 400);
        transitionRectangle.setFill(Color.BLACK);
        transitionLayout.getChildren().add(transitionRectangle);

        
        VBox vbox = new VBox();
        
        ProgressBar progressBar = new ProgressBar(20);
        transitionLayout.getChildren().add(progressBar);
        button.setOnAction(event -> {
            // switch to transition layout
            scene1.setRoot(transitionLayout);

            // animate loading bar
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                    new KeyFrame(Duration.seconds(3), new KeyValue(progressBar.progressProperty(), 1))
            );
            timeline.play();

            // create pause transition
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(el -> {
                // switch to final layout after 5 seconds
                scene1.setRoot(vbox);
            });
            pause.play();
        });
        

        vbox.setAlignment(Pos.CENTER);
		vbox.setBackground(new Background(backgroundImage));
		vbox.setSpacing(40);
		Text text0 = new Text("Pick your Hero");
		text0.setFont(Font.font("Pick your Hero", FontWeight.BOLD, 40));
		text0.setFill(Color.WHITE);
		
		
		vbox.getChildren().add(text0);
        int k=0;
		for (int i = 0; i < 2; i++) {
		    HBox hbox = new HBox();
		    hbox.setSpacing(80);
		    hbox.setAlignment(Pos.CENTER);
		    for (int j = 0; j < 4; j++) {
		        Rectangle rectangle = new Rectangle(275, 375);
		        rectangle.setFill(Color.web("#EEECE5"));
		        rectangle.setArcWidth(20);
		        rectangle.setArcHeight(20);
		        hbox.getChildren().add(rectangle);
		        Hero h= Game.availableHeroes.get(k);
		        String s= h.getName();
		        String s1= gettype(h); 
		        int s2 =h.getMaxHp();
		        int s3 = h.getMaxActions();
		        int s4 =h.getAttackDmg();
		        Text text = new Text(s);
		        Text text1= new Text("Type = "+s1);
		        Text text2 = new Text("MaxHp = "+s2);
		        Text text3= new Text("Max Actions = "+s3);
		        Text text4 = new Text("Attack Damage =  "+s4);
		        text.setFont(Font.font(null, FontWeight.BOLD, 20));
		        text1.setFont(Font.font(null, FontWeight.BOLD, 20));
		        text2.setFont(Font.font(null, FontWeight.BOLD, 20));
		        text3.setFont(Font.font(null, FontWeight.BOLD, 20));
		        text4.setFont(Font.font(null, FontWeight.BOLD, 20));
		        StackPane stackPane = new StackPane();
		        VBox textVbox = new VBox();
		        ImageView ist = chooseHeroImage(h);
		        ist.setFitWidth(200);
		        ist.setFitHeight(150);
		        textVbox.setTranslateY(-20);
		        textVbox.setAlignment(Pos.BOTTOM_CENTER);
		        textVbox.setSpacing(10);
		        textVbox.getChildren().addAll(ist,text,text1,text2,text3,text4);
		        stackPane.getChildren().addAll(rectangle,textVbox);
		        //stackPane.setAlignment(textVbox,);
		        stackPane.setOnMouseEntered(ev1 -> rectangle.setFill(Color.web("#D0CFCB")));
		        stackPane.setOnMouseExited(ev2 -> rectangle.setFill(Color.web("#EEECE5")));
		        stackPane.setOnMouseClicked(ev3 -> {Game.startGame(h); startgameGui(h); scene1.setRoot(bp); });
		        k++;
		        

		        hbox.getChildren().add(stackPane);
		    }
		    vbox.getChildren().add(hbox);
		}
		
        
        
        window.setScene(scene1);
		window.setFullScreen(true);
		window.addEventHandler (KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>  () {
			   @Override
			  public void handle (KeyEvent t) {
			  if (t.getCode ()==KeyCode.ESCAPE)  {
			  primaryStage.setIconified (true); //minimize the stage
			  }
			  }
			  });
		
		primaryStage.iconifiedProperty().addListener((observable, oldValue, newValue) -> {
		    if (!newValue) {
		        Platform.runLater(() -> {
		            primaryStage.setMaximized(true);
		        });
		    }
		});
		
		/*Image z=new Image("file:///C:/Game/Zomf.png");
		ImageView zz=new ImageView(z);
		zz.setFitWidth(40);
		zz.setFitHeight(40);
		gg.setHalignment(zz, HPos.CENTER);
		gg.setValignment(zz, VPos.CENTER);
		
		Zombie zzz=new Zombie();
	    zzz.setLocation(new Point(1,12));
	    Point ui=gploc(new Point(1,12));
	    int nx=ui.x;
	    int ny=ui.y;
		
		gg.add(zz,nx,ny);*/
		
	   
	   
	 
		window.iconifiedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                primaryStage.setFullScreen(false);
            }
        });
		
		
		
		bp.requestFocus();
		e.setOnAction(er -> Main.endturngui());
				
 
		window.show();
		
		
	}
 
 
 
    public static void controls(ImageView i) {
    	Hero b=(Hero)Actions.findCharacter(i);
    	if(b instanceof Hero) {

        i.setOnMouseEntered(e1 -> changerectcolorgreen(b));
        i.setOnMouseExited(e2 -> changerectcolorg(b));
        i.setOnMouseClicked( evento -> {
			viewButtons();
			Actions.movegui(i);
			a.setOnMouseClicked(ao -> Actions.attackgui(i));
			m.setOnMouseClicked(em -> Actions.movegui(i) );
			c.setOnMouseClicked(ec -> Actions.curegui(i));
			if(b instanceof Medic){
			controlsm(b.getiv());}
			else 
			u.setOnMouseClicked(eu -> Actions.usespecialgui(i));
		
			});
    	
    	}
    }
    
      
        public static void  controlsm(ImageView g){
        	a.setOnMouseClicked(ao -> Actions.attackgui(g));
			m.setOnMouseClicked(em -> Actions.movegui(g) );
			c.setOnMouseClicked(ec -> Actions.curegui(g));
			u.setOnMouseClicked(eu -> {Actions.usespecialgui(g); controlsm(g);});
		
        }
        
        public static void endturngui(){
        			
        try{
            Game.endTurn();
			updatevisgui();
			updaterect();
		}
		
		catch(NotEnoughActionsException e1){
		}
		
		catch(InvalidTargetException e2){
			
		}
        
        }
    
    
    	
    	public static void changerectcolorgreen(Hero b){
    		for(int i=0;i<Game.heroes.size();i++){
        	    Hero h=Game.heroes.get(i);
        	    if(b.equals(h)){
        	    	StackPane a=r.get(i);
        	    	checkrecinfo(a);
        	    }
    		}
        	    
    	}
    	
    	public static void changerectcolorg(Hero b){
    		for(int i=0;i<Game.heroes.size();i++){
        	    Hero h=Game.heroes.get(i);
        	    if(b.equals(h)){
        	    	StackPane a=r.get(i);
        	    	checkrecinforg(a);
        	    }
    		}
        	    
    	}
    	
    	
    	
    	
    		
    	public static void checkrecinfo(StackPane a) {
         	 for (int i=0;i<a.getChildren().size();i++) {
         if (a.getChildren().get(i) instanceof Rectangle) {
       	  ((Rectangle)a.getChildren().get(i)).setFill(Color.web("#8ee0ba"));
         	        break;
         	     }
         	 }
          }
    	
    	public static void checkrecinfor(StackPane a) {
        	 for (int i=0;i<a.getChildren().size();i++) {
        if (a.getChildren().get(i) instanceof Rectangle) {
      	  ((Rectangle)a.getChildren().get(i)).setFill(Color.RED);
        	        break;
        	     }
        	 }
         }
    	
    	public static void checkrecinforg(StackPane a) {
        	 for (int i=0;i<a.getChildren().size();i++) {
        if (a.getChildren().get(i) instanceof Rectangle) {
      	  ((Rectangle)a.getChildren().get(i)).setFill(Color.web("#EEECE5"));
        	        break;
        	     }
        	 }
         }
    	
    	
    	
    	
    	
    	
    
 


	public static void removeImageViewFromCell(GridPane gridPane, int colIndex, int rowIndex) {
        for (Node child : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(child) == colIndex && GridPane.getRowIndex(child) == rowIndex && child instanceof ImageView) {
                gridPane.getChildren().remove(child);
                break;
            }
        }
    }
    public static GridPane getgg(){
    	return gg;
    }
    
    public static void updaterect(){
    	for(int i=0;i<Game.heroes.size();i++){
    	    
    		Hero h=Game.heroes.get(i);
    		VBox v=new VBox(8);
    		v.setAlignment(Pos.CENTER);
    		setrecvis(r.get(i).getChildren());
    		remrect(r.get(i).getChildren(),r.get(i));
    		r.get(i).getChildren().add(v);
    		Text t=new Text(h.getName());
    		Text t1=new Text("Type = "+gettype(h));
    		Text t2 = new Text("Current Hp = "+h.getCurrentHp());
	        Text t3= new Text("Max Actions = "+h.getMaxActions());
	        Text t4= new Text("Available Actions = "+h.getActionsAvailable());
	        Text t5 = new Text("Attack Damage =  "+h.getAttackDmg());
	        Text t6 = new Text("Supplies Amount =  "+h.getSupplyInventory().size());
	        Text t7 = new Text("Vaccines Amount =  "+h.getVaccineInventory().size());
	        t.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
	        t1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
	        t2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
	        t3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
	        t4.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
	        t5.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
	        t6.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
	        t7.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
	        v.getChildren().addAll(t,t1,t2,t3,t4,t5,t6,t7);
    		
    		
    		
    	}
    	
    }
    
  
    	public static void setrecvis(List<Node> nodes) {
       	 for (Node node : nodes) {
       if (node instanceof Rectangle && ((Rectangle)node).isVisible()==false) {
       	         node.setVisible(true);;
       	         break;
       	     }
       	 }
        }
    
    public static void remrect(List<Node> nodes,StackPane s){
    	for (Node node : nodes) {
    	    if (node instanceof VBox ) {
    	    	         s.getChildren().remove(node);
    	    	         break;
    	    	     }
    	    	 }
    	     }
 
 
 
     public void startgameGui(Hero h) {
	    ImageView st=chooseHeroImage(h);
        gg.add(st, 0, 14);
		controls(h.getiv());
		
		for(int i=0;i<Game.map.length;i++){
	       for(int j=0;j<Game.map[i].length;j++){
	    	   Point p=gploc(new Point(i,j));
	    	   int x=p.x;
	    	   int y=p.y;
	    	   ImageView v=null;
	    	   Image l;
	    	   if((Game.map[i][j]) instanceof CollectibleCell){
	    	      CollectibleCell co=(CollectibleCell)Game.map[i][j];
	    		   if(co.getCollectible() instanceof Vaccine){
	    			   l=new Image(Main.class.getResourceAsStream("Vac.png"));
	    			   v= new ImageView(l);
	    			   ((Vaccine)co.getCollectible()).setiv(v);
	    			   gg.add(v,x,y);}
	    		   else 
	    			   if(co.getCollectible() instanceof Supply){
	    		      l =new Image(Main.class.getResourceAsStream("battery.png"));
    			      v= new ImageView(l);
    			   ((Supply)co.getCollectible()).setiv(v);
    			   gg.add(v,x,y);}}
	    			
	    	   if(v!=null){
	    	   v.setFitWidth(50);
	   		   v.setFitHeight(45);
	   		   gg.setHalignment(v, HPos.CENTER);
	   		   gg.setValignment(v, VPos.CENTER);}
	    	   

	    		   }
	       
	       }
		updatevisgui(); 
		updaterect();
	     
     }
     
     // should be used in cure 
   
     
     
     public static ImageView chooseHeroImage(Hero h) {
    	 Image st;
    	 String s=h.getName();
    	 if(s.equals("Joel Miller")) 
    		 st=new Image(Main.class.getResourceAsStream("figr.png"));
    	 else if(s.equals("David")) 
    		 st=new Image(Main.class.getResourceAsStream("fig2.png"));
    	 else if(s.equals("Ellie Williams")) 
    		 st=new Image(Main.class.getResourceAsStream("Ellie.png"));
    	 else if(s.equals("Tess") ) 
    		 st=new Image(Main.class.getResourceAsStream("Expww.png"));
    	 else if(s.equals("Riley Abel") ) 
    		 st=new Image(Main.class.getResourceAsStream("Expp.png"));
    	 else if(s.equals("Tommy Miller")) 
    		 st=new Image(Main.class.getResourceAsStream("Expf.png"));
    	 else if(s.equals("Bill"))
    		 st=new Image(Main.class.getResourceAsStream("Docr.png"));
    	 else 
    		 st=new Image(Main.class.getResourceAsStream("Docvv.png"));
    	 
    	 
    /*	String s=gettype(h);
     if (s.equals("Explorer")) 
	 st= new Image(Main.class.getResourceAsStream("Expf.png"));
	 else if(s.equals("Fighter")) 
		 st=new Image(Main.class.getResourceAsStream("figr.png"));
	 else {
		st=new Image(Main.class.getResourceAsStream("Docr.png"));
	    
	}*/
	 ist=new ImageView(st);
      h.setiv(ist);
	 ist.setFitWidth(55);
	 ist.setFitHeight(50);
	Main.gg.setHalignment(ist, HPos.CENTER);
	Main.gg.setValignment(ist, VPos.CENTER);
     return ist;
     }
     
     
     public static Point gploc(Point p) {
    	 int nx=p.y;
    	 int ny=14-p.x;
    	 return new Point(nx,ny);
     }
     
     
     //incase hero is dead or moved to another Cell
    
     
    public static void hide(int x ,int y){
    	for(int i=0;i<Game.heroes.size();i++){
    		if(Game.heroes.get(i) instanceof Explorer){
    			Explorer e=(Explorer)Game.heroes.get(i);
    			if(e.isSpecialAction()==true)
    				return;
    		}
    			
    	}
    	Main.checkrec(Main.gg.getChildren(), x, y);
    	g=new Rectangle(70,65);
        g.setFill(Color.BLACK);
        gg.add(g, x, y);
        g.toFront();
        g.setArcWidth(20);
        g.setArcHeight(20);
        
    }
    
    //used in end turn
    public static void updatevisgui(){
    	for(int i=0;i<Game.map.length;i++){
    		for(int j=0;j<Game.map[i].length;j++){
    		   Point p=gploc(new Point(i,j));
 	    	   int x=p.x;
 	    	   int y=p.y;
    			if(Game.map[i][j].isVisible()==true)
    				checkrec(gg.getChildren(),x,y);
    			else
    				hide(x,y);
    		}
    	}
    }
    
   
     
     
     public static void setimgtoback(List<Node> nodes ,int x,int y) {
    	 for (Node node : nodes) {
    if (node instanceof ImageView && gg.getColumnIndex(node)==x && gg.getRowIndex(node)==y) {
    	         g=new Rectangle(70,65);
    	         g.setFill(Color.BLACK);
    	         gg.add(g, x, y);
    	         g.setArcWidth(20);
 		         g.setArcHeight(20);
    	         node.toBack();
    	         break;
    	     }
    	 }
     }
     
     public static void checkrec(List<Node> nodes ,int x,int y) {
    	 for (Node node : nodes) {
    if (node instanceof Rectangle && gg.getColumnIndex(node)==x && gg.getRowIndex(node)==y && ((Rectangle) node).getFill()==Color.BLACK) {
    	         gg.getChildren().remove(node);
    	         break;
    	     }
    	 }
     }
 
     
    /* public static void checkimg(ImageView v) {
    	 if()
     }*/
 
 
     public static String gettype(Hero h) {
		if (h instanceof Fighter)
			return "Fighter";
		if (h instanceof Medic)
		   return "Medic";
		return "Explorer";
	}
     
     
     public static void viewButtons() {
    	 a.setVisible(true);
    	 c.setVisible(true);
    	 u.setVisible(true);
    	 m.setVisible(true);
     }
   
 
 


	public static void main(String[]args) throws Exception {
		Game.loadHeroes("Heroes.csv");
		launch(args);
	
		
	}

}
