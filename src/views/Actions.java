package views;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.characters.Character;
import javafx.stage.Modality;

import java.awt.Point;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import javax.annotation.processing.Messager;

import engine.Game;
import exceptions.*;

public class Actions extends Application {
	
	@Override
	public void start(Stage arg0) throws Exception {
		
		
	}
	

	
	
	
			public static void showmessage(String message,GridPane gg,StackPane container) {
			    Label messageLabel = new Label(message);
			    messageLabel.setFont(new Font("Times New Roman", 50));
			    messageLabel.setStyle("-fx-text-fill: red; ");
			    messageLabel.setOpacity(0);

			    container.getChildren().add(messageLabel);
			    StackPane.setAlignment(messageLabel, Pos.CENTER);
			    StackPane.setMargin(messageLabel, new Insets(10, 0, 0, 0));

			    Timeline timeline = new Timeline(
			        new KeyFrame(Duration.seconds(5), new KeyValue(messageLabel.opacityProperty(), 0))
			    );
			    timeline.setOnFinished(event -> {container.getChildren().remove(messageLabel); gg.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);});
			    
			    messageLabel.setOpacity(1);
			    timeline.playFromStart();
	    }
		

		public static void alertex(String a) {
			Label label = new Label(a);
			label.setStyle("-fx-background-color: red; -fx-padding: 10;");
			label.setTextFill(Color.WHITE);
			label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));

			Button closeButton = new Button("Close");

			VBox layout = new VBox(10);
			layout.getChildren().addAll(label, closeButton);
			layout.setStyle("-fx-background-color: red; -fx-padding: 10;");

			Popup popup = new Popup();
			popup.getContent().add(layout);
			popup.setAutoHide(false);
            Main.rr.getChildren().add(layout);
            layout.setAlignment(Pos.CENTER);
            Main.rr.setMargin(layout, new Insets(400,150,400,150));
			closeButton.setOnAction(event -> Main.rr.getChildren().remove(layout));
            Main.bp.setOnKeyPressed(event->{
            		if(event.getCode() == KeyCode.ENTER)
            		Main.rr.getChildren().remove(layout);});
			popup.show(Main.window);
		}
		
		public static void alertgame(String a , String c) {
			Label label = new Label(a);
			label.setStyle("-fx-background-color: "+c+"; -fx-padding: 10;");
			label.setTextFill(Color.WHITE);
			label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));

			Button closeButton = new Button("Close");

			VBox layout = new VBox(10);
			layout.getChildren().addAll(label, closeButton);
			layout.setStyle("-fx-background-color: "+c+"; -fx-padding: 10;");

			Popup popup = new Popup();
			popup.getContent().add(layout);
			popup.setAutoHide(false);
			Main.rr.getChildren().add(layout);
            layout.setAlignment(Pos.CENTER);
           // Main.rr.setMargin(layout, new Insets(400,150,400,150));
            
			Main.inf.setDisable(true);
			Main.heroc.setDisable(true);
			Main.gg.setDisable(true);
			for(int i=0;i<Game.heroes.size();i++){
				Game.heroes.get(i).setiv(null);
			}
			closeButton.setOnAction(event -> Main.window.close());

			popup.show(Main.window);
		}
		

		
		/*public static void alertgame(String s){
			VBox popup = new VBox(10);
			popup.setAlignment(Pos.CENTER);
			popup.setVisible(false);

			Label label = new Label("This is some text in the popup window.");
			Button closeButton = new Button("Close");
			closeButton.setOnAction(e -> popup.setVisible(false));
			popup.getChildren().addAll(label, closeButton);

			button.setOnAction(e -> popup.setVisible(true));
		}
		*/
		
		public static void movegui(ImageView i) {
			Hero h=(Hero)findCharacter(i);
			showmessage("Select your Direction using arrow keys", Main.gg, Main.rr);
			Main.scene1.setOnKeyPressed(event -> {
			    Direction direction = null;
			    switch (event.getCode()) {
			        case UP:
			            direction = Direction.UP;
			            break;
			        case DOWN:
			            direction = Direction.DOWN;
			            break;
			        case LEFT:
			            direction = Direction.LEFT;
			            break;
			        case RIGHT:
			            direction = Direction.RIGHT;
			            break;
			    };
			    if (direction != null) {
			    	int bhp=h.getCurrentHp();
			    	//System.out.println(bhp);
					Point p=h.getLocation();
					Point np=gploc(p);
					int x=np.x;
					int y=np.y;
					int nx=np.x;
					int ny=np.y;
					if(direction==Direction.DOWN)
						ny++;
					if(direction==Direction.UP)
						ny--;
					if(direction==Direction.LEFT)
						nx--;
					if(direction==Direction.RIGHT)
						nx++;
				try {
				
				h.move(direction);
				int ahp=h.getCurrentHp();
				System.out.println(ahp);
				if (ahp!=bhp)
					showmessage("Hero entered a TrapCell",Main.gg,Main.rr);
				
				else if(cellHasImageView(Main.gg,nx,ny)) {
					Main.removeImageViewFromCell(Main.gg,nx,ny);
					Main.checkrec(Main.gg.getChildren(), x, y);}
				
				Main.removeImageViewFromCell(Main.gg, x, y);
				
				if(ahp!=0) 
					Main.gg.add(i, nx, ny);
				
				Main.updaterect();
				if(Game.checkWin())
					alertgame("Congratulations, You Won","green");
				else if(Game.checkGameOver())
					alertgame("Game Over, Try Again","red");
				}
			
				catch(NotEnoughActionsException er) {
			       alertex(er.getMessage());
				}
				catch(MovementException mo) {
					alertex(mo.getMessage());
				}};
				
		});
			} 
			    
			
		
		
		public static boolean cellHasImageView(GridPane gridPane, int colIndex, int rowIndex) {
		    for (Node child : gridPane.getChildren()) {
		        if (GridPane.getColumnIndex(child) == colIndex && GridPane.getRowIndex(child) == rowIndex && child instanceof ImageView) {
		            return true;
		        }
		    }
		    return false;
		}

		
		public static Point gploc(Point p) {
	    	 int nx=p.y;
	    	 int ny=14-p.x;
	    	 return new Point(nx,ny);
	     }
				
		
		public static void attackgui(ImageView a) {
			Hero h=(Hero)findCharacter(a);
			showmessage("Select the Zombie you want to attack", Main.gg, Main.rr);
			for(int i=0;i<Game.zombies.size();i++){
        		Zombie z =Game.zombies.get(i);
        		z.getiv().setOnMouseClicked(e->{ h.setTarget(z);
        	 
			try{
				h.attack();
				Point p=h.getTarget().getLocation();
				Point pg=Main.gploc(p);
				int x=pg.x;
				int y=pg.y;
				flashGridCell(Main.gg, y, x,z.getiv());
				if(h.getTarget().getCurrentHp()==0){
				Main.removeImageViewFromCell(Main.gg,x,y);
				Point np=Game.zombies.get(Game.zombies.size()-1).getLocation();
				Point npg=Main.gploc(np);
				int nx=npg.x;
				int ny=npg.y;
				Game.adjustVisibility(h);}
				Main.updaterect();
				if(Game.checkWin())
					alertgame("Congratulations, You Won","green");
				else if(Game.checkGameOver())
					alertgame("Game Over, Try Again","red");
			}
			
			   catch(InvalidTargetException e1){
	        	   alertex(e1.getMessage());
	           }
	           catch(NotEnoughActionsException e2){
	        	   alertex(e2.getMessage());
	           }
			
		});}
        		}
	
		
		
		public static void flashGridCell(GridPane gridPane, int row, int column,ImageView a) {
		    // Create a new Rectangle node to use as the background of the cell
		    Rectangle rect = new Rectangle(65, 60, Color.RED);
		    rect.setStroke(Color.BLACK);
		    rect.setStrokeWidth(1);
		    rect.setArcWidth(20);
	        rect.setArcHeight(20);

		    // Add the Rectangle to the cell
		    gridPane.setHalignment(rect, HPos.CENTER);
		    gridPane.setValignment(rect, VPos.CENTER);
		    gridPane.getChildren().remove(a);
		    gridPane.add(rect, column, row);
		    gridPane.add(a, column, row);
		    a.toFront();

		    // Create a Timeline animation to change the fill color of the Rectangle
		    Timeline timeline = new Timeline(
		            new KeyFrame(Duration.seconds(0.1), e -> {
		                rect.setFill(Color.RED);
		            }),
		            new KeyFrame(Duration.seconds(0.5), e -> {
		                rect.setFill(Color.TRANSPARENT);
		            })
		    );
		    timeline.setCycleCount(1);
		    timeline.play();
		}
		
		
		public static void usespecialgui(ImageView a) {
			Hero h=(Hero)findCharacter(a);
			if(h instanceof Medic){
				showmessage("Select the hero you want to heal using u",Main.gg,Main.rr);
				for(int i=0;i<Game.heroes.size();i++){
		        Hero hh =Game.heroes.get(i);
		        hh.getiv().setOnMouseEntered(ep->{ 
		        	Main.bp.setOnKeyPressed(el->{ 
		        	if(el.getCode()==KeyCode.U){
		        	h.setTarget(hh);
			
			try{
			h.useSpecial();
			h.setSpecialAction(false);
			Main.updaterect();
			
			}
			
			catch(NoAvailableResourcesException e){
				alertex(e.getMessage());
			}
			catch(InvalidTargetException e1){
				alertex(e1.getMessage());
			};
			
		        	}});});}}
	        		
	      else{
	    	  try{
	    		  h.useSpecial();
	    	  
	      
			if( h instanceof Explorer)
				visibilitytrue();
			Main.updaterect();
			
			
			if(Game.checkWin())
				alertgame("Congratulations, You Won","green");
			
			else if(Game.checkGameOver())
				alertgame("Game Over, Try Again","red");}
			
		
			catch(NoAvailableResourcesException e){
				alertex(e.getMessage());
			}
			catch(InvalidTargetException e1){
				alertex(e1.getMessage());
			
			}}
			
			
		}
		
		public static void visibilitytrue(){
			for(int i = 0; i < Game.map.length; i++) {
				for(int j = 0; j < Game.map[i].length; j++) {
			    	Point p=gploc(new Point(i,j));
			 	    int x=p.x;
			 	    int y=p.y;
			    	if(Game.map[i][j].isVisible()==true)
			    		Main.checkrec(Main.gg.getChildren(),x,y);
				}
		}
			}
		
		
		public static void playmedia(String s){
			Media audio=new Media(s);
			MediaPlayer m=new MediaPlayer(audio);
			m.play();
		}
		
		
		
        public static void curegui(ImageView a) {
        	Hero h=(Hero)findCharacter(a);
        	showmessage("Select the Zombie you want to cure using c", Main.gg, Main.rr);
        	for(int i=0;i<Game.zombies.size();i++){
        		Zombie z =Game.zombies.get(i);
        		z.getiv().setOnMouseEntered(ep->{ 
		        	Main.bp.setOnKeyPressed(el->{ 
		        	if(el.getCode()==KeyCode.C){
		        		h.setTarget(z);
        	
           try{
        	Point p=h.getTarget().getLocation();
        	h.cure();
        	Point pg=Main.gploc(p);
			int x=pg.x;
			int y=pg.y;
			Main.removeImageViewFromCell(Main.gg,x,y);
			Hero c=getcuredh(p);
			ImageView v=Main.chooseHeroImage(c);
			c.setiv(v);
			Main.gg.add(v,x,y);
			Main.updaterect();
			v.setOnMouseEntered(e1 -> Main.changerectcolorgreen(c));
	        v.setOnMouseExited(e2 -> Main.changerectcolorg(c));
			
			if(Game.checkWin())
				alertgame("Congratulations, You Won","green");
			else if(Game.checkGameOver())
				alertgame("Game Over, Try Again","red");
           }
        		
           catch(NoAvailableResourcesException e1){
        	   alertex(e1.getMessage());
           }
           catch(InvalidTargetException e2){
        	   alertex(e2.getMessage());
           }
           catch(NotEnoughActionsException e3){
        	   alertex(e3.getMessage());
          
        }
        }});});}}
		
		public static Character findCharacter(ImageView a) {
			for(int i=0;i<Game.heroes.size();i++) {
				Hero h= Game.heroes.get(i);
				if(a==h.getiv())
					return h;
					
			}
			
			for(int i=0;i<Game.zombies.size();i++) {
				Zombie z = Game.zombies.get(i) ;
				if(a==z.getiv())
					return z;
		}
			return null;
		}
		
		
		
		public static Hero getcuredh(Point p){
			for(int i=0;i<Game.heroes.size();i++){
				if(Game.heroes.get(i).getLocation().equals(p))
					return Game.heroes.get(i);
			}
			return null;
			
		}
		
		 
					
	
	

			   
	public static void main(String[]args) throws IOException {
		launch(args);
		    }
	
	

}

