package PacMan;

/**
 * @author Nathan Boehning
 * Date: 11/16/2017
 * Purpose: Create the main menu for pac man game. Have options of joining
 *          or hosting a game.
 */

// Javafx imports
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// Game client and server imports
import java.util.ArrayList;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Shape;




public class GameClient extends Application {
    
    private static LinkedBlockingQueue<String> sendMessages = new LinkedBlockingQueue<String>();
    private static LinkedBlockingQueue<String> receiveMessages = new LinkedBlockingQueue<String>();
    private static int myPlayerNumber = -1;
    private static ArrayList<NetworkPlayer> players = new ArrayList<NetworkPlayer>();
    private static VBox playerHolder;
    private static Text[] lobbyTexts = new Text[5];
    private static ArrayList<Text> scoreTexts = new ArrayList<Text>();
    private static ArrayList<Rectangle> walls = new ArrayList<Rectangle>();
    private static ArrayList<Circle> dots = new ArrayList<Circle>();
    private static ArrayList<Circle> magicDots = new ArrayList<Circle>();
    private static ArrayList<Circle> playerCircles = new ArrayList<Circle>();
    Pane gameBoard = new Pane();
    
    HBox playerScoreHolder;
    VBox gameHolder;
    
    static int numRows = 11;
    static int numCols = 10;
    private static int[][] board = new int[][] {
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 4, 2, 2, 1, 1, 2, 2, 4, 1 },
        { 1, 2, 1, 2, 2, 3, 2, 1, 2, 1 },
        { 1, 2, 1, 2, 1, 2, 1, 1, 2, 1 },
        { 1, 2, 1, 2, 1, 2, 1, 1, 2, 1 },
        { 1, 2, 3, 2, 2, 2, 3, 2, 2, 1 },
        { 1, 2, 1, 2, 1, 1, 2, 1, 2, 1 },
        { 1, 2, 2, 2, 1, 1, 2, 1, 2, 1 },
        { 1, 2, 1, 2, 1, 1, 2, 1, 2, 1 },
        { 1, 4, 2, 2, 2, 3, 2, 2, 4, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }        
    };
    
    // Load fonts
    private Font btnFont = Font.loadFont(getClass().getResourceAsStream("/fonts/slkscr.ttf"), 30);        
    Font titleFont = Font.loadFont(getClass().getResourceAsStream("/fonts/slkscr.ttf"), 60);
    
    
    double width = 1000;
    double height = 1000;
    
    // Define base pane
    BorderPane root = new BorderPane();        
    
    @Override
    public void start(Stage primaryStage) {  
        // <editor-fold defaultstate="collapsed" desc="Basic Inits">

        // Set styles for hovering over buttons as well as idle
        String idleButtonStyle = "-fx-border-color: blue; -fx-background-color: black; -fx-text-fill: white;";
        String hoverButtonStyle = "-fx-border-color: white; -fx-background-color: black; -fx-text-fill: yellow";
        String textStyle = "-fx-background-color: black; -fx-text-fill: white;";

        
        root.setStyle("-fx-background-color: black");

        // Create pane to hold various menus
        StackPane menu = new StackPane();

        // Styling rect
        Rectangle borderRect = new Rectangle();
        borderRect.setHeight(500);
        borderRect.setWidth(650);
        borderRect.setStroke(Color.BLUE);

        // </editor-fold>                

        //<editor-fold defaultstate="collapsed" desc="Main Menu">

        // Pane for the main menu
        VBox mainMenuHolder = new VBox();
        mainMenuHolder.setAlignment(Pos.CENTER);
        mainMenuHolder.setSpacing(50);

        // Text fields and buttons for main menu
        Text titleText = new Text("Pac-Man Versus");
        titleText.setFont(titleFont);
        titleText.setStroke(Color.WHITE);
        titleText.setFill(Color.WHITE);

        Button btnJoinGameMenu = new Button("Join Game");
        btnJoinGameMenu.setFont(btnFont);
        btnJoinGameMenu.setStyle(idleButtonStyle);
        btnJoinGameMenu.setOnMouseEntered(e -> btnJoinGameMenu.setStyle(hoverButtonStyle));
        btnJoinGameMenu.setOnMouseExited(e -> btnJoinGameMenu.setStyle(idleButtonStyle));

        Button btnHostGame = new Button("Host Game");
        btnHostGame.setFont(btnFont);
        btnHostGame.setStyle(idleButtonStyle);
        btnHostGame.setOnMouseEntered(e -> btnHostGame.setStyle(hoverButtonStyle));
        btnHostGame.setOnMouseExited(e -> btnHostGame.setStyle(idleButtonStyle));

        // Initial population of the menu
        mainMenuHolder.getChildren().addAll(titleText, btnHostGame, btnJoinGameMenu);
        menu.getChildren().addAll(borderRect, mainMenuHolder);
        root.setCenter(menu);
        //</editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Join Menu">
        // Pane for the join menu        
        VBox joinMenuHolder = new VBox();
        joinMenuHolder.setAlignment(Pos.CENTER);
        joinMenuHolder.setSpacing(70);

        // Buttons, fields, labels for join menu
        Text joinMenuTitleText = new Text("Join Game");
        joinMenuTitleText.setFont(titleFont);
        joinMenuTitleText.setStroke(Color.WHITE);
        joinMenuTitleText.setFill(Color.WHITE);
        
        Label ipAddress = new Label(" Host Address:");
        ipAddress.setFont(btnFont);
        ipAddress.setStyle(textStyle);
        
        TextField ipAddressField = new TextField("");
        ipAddressField.setFont(btnFont);
        ipAddressField.setStyle(idleButtonStyle);
        ipAddressField.setMaxWidth(325);
        
        HBox ipBox = new HBox();
        ipBox.getChildren().addAll(ipAddress, ipAddressField);
        ipBox.setSpacing(10);
        ipBox.setAlignment(Pos.CENTER);        
        
        Button btnReturnToMain = new Button("Main Menu");
        btnReturnToMain.setFont(btnFont);
        btnReturnToMain.setStyle(idleButtonStyle);        
        btnReturnToMain.setOnMouseEntered(e -> btnReturnToMain.setStyle(hoverButtonStyle));        
        btnReturnToMain.setOnMouseExited(e -> btnReturnToMain.setStyle(idleButtonStyle));
        
        Button btnJoinGame = new Button("Join");
        btnJoinGame.setMinWidth(215);
        btnJoinGame.setFont(btnFont);
        btnJoinGame.setStyle(idleButtonStyle);        
        btnJoinGame.setOnMouseEntered(e -> btnJoinGame.setStyle(hoverButtonStyle));        
        btnJoinGame.setOnMouseExited(e -> btnJoinGame.setStyle(idleButtonStyle));
        
        HBox joinBtnBox = new HBox();
        joinBtnBox.getChildren().addAll(btnReturnToMain, btnJoinGame);
        joinBtnBox.setSpacing(150);
        joinBtnBox.setAlignment(Pos.CENTER);        
        
        joinMenuHolder.getChildren().addAll(joinMenuTitleText, ipBox, joinBtnBox);

// </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Lobby">
        
        VBox lobbyMenuHolder = new VBox();
        lobbyMenuHolder.setAlignment(Pos.CENTER);
        lobbyMenuHolder.setSpacing(70);

        // Buttons, fields, labels for join menu
        Text lobbyMenuTitleText = new Text("Lobby");
        lobbyMenuTitleText.setFont(titleFont);
        lobbyMenuTitleText.setStroke(Color.WHITE);
        lobbyMenuTitleText.setFill(Color.WHITE);
        
        playerHolder = new VBox();
        playerHolder.setAlignment(Pos.CENTER);
        playerHolder.setSpacing(10);               
               
        Button btnReady = new Button("Toggle Ready");
        btnReady.setMinWidth(215);
        btnReady.setFont(btnFont);
        btnReady.setStyle(idleButtonStyle);        
        btnReady.setOnMouseEntered(e -> btnReady.setStyle(hoverButtonStyle));        
        btnReady.setOnMouseExited(e -> btnReady.setStyle(idleButtonStyle));
        
        HBox lobbyBtnBox = new HBox();
        lobbyBtnBox.getChildren().addAll(btnReady);
        lobbyBtnBox.setSpacing(150);
        lobbyBtnBox.setAlignment(Pos.CENTER);        
        
        lobbyMenuHolder.getChildren().addAll(lobbyMenuTitleText, playerHolder, lobbyBtnBox);

        // </editor-fold>                
        
        
        // Thread to constantly check received messages and act on them
        Thread readServerMessages = new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        String newMessage = receiveMessages.take();
                        // split the message up to determine exactly what to do
                        String[] msg = newMessage.split("\\s+");
                        switch(msg[0]) {
                            case "NEWPLAYER":
                                System.out.println("Player " + myPlayerNumber + " adding new player to players");
                                if(myPlayerNumber != -1) {
                                    // You've already joined the game, simply add the new player
                                    System.out.println("Player " + myPlayerNumber + " adding new player to players");
                                    players.add(new NetworkPlayer(Integer.parseInt(msg[1])));
                                } else {
                                    // Get your number
                                    myPlayerNumber = Integer.parseInt(msg[1]);
                                    System.out.println("Player " + myPlayerNumber + " adding new player to players");
                                    // Loop through and add all player who joined before you
                                    for(int i=1; i<=myPlayerNumber; i++) {
                                        players.add(new NetworkPlayer(i));
                                    }
                                }
                                updateLobbyPlayerList();
                                System.out.println("Number of players: " + players.size());
                                break;
                            case "MOVE":
                                players.get(Integer.parseInt(msg[1]) - 1).setDirection(msg[2]);
                                break;
                            case "READY":
                                players.get(Integer.parseInt(msg[1])-1).toggleIsReady();
                                updateLobbyPlayerList();
                                break;
                        }
                    } 
                    catch (InterruptedException ex) {}
                }
            }
        };
        
        readServerMessages.start();
                        
        // <editor-fold defaultstate="collapsed" desc="Buttons">
        // Button functions
        btnJoinGameMenu.setOnAction(e -> {
            menu.getChildren().clear();
            menu.getChildren().addAll(borderRect, joinMenuHolder);
            root.setCenter(menu);
        });
        
        btnReturnToMain.setOnAction(e -> {
            menu.getChildren().clear();
            menu.getChildren().addAll(borderRect, mainMenuHolder);
            root.setCenter(menu);
        });
        
        btnReady.setOnAction(e-> {
            sendMessages.add("READY "+myPlayerNumber);
        });
        
        btnHostGame.setOnAction(e -> {
            try {     
                PacMan.GameServer.startServer();
                connectToServer("localhost");
                
            } catch (IOException | ClassNotFoundException ex) {}
            
            menu.getChildren().clear();
            menu.getChildren().addAll(borderRect, lobbyMenuHolder);
        });
        
        btnJoinGame.setOnAction(e -> {
            try {   
                connectToServer(ipAddressField.getText());
            } catch (IOException | ClassNotFoundException ex) {}
            
            menu.getChildren().clear();
            menu.getChildren().addAll(borderRect, lobbyMenuHolder);     
        });
        // </editor-fold>
                       
                
        // Display the menu
        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("Pac-Man: Versus");
        primaryStage.setScene(scene);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }
    
    void updateLobbyPlayerList() {
        Platform.runLater(() -> {
            playerHolder.getChildren().clear(); // clear so it doesn't double add players
            for(int i=0; i<5; i++) {
                if(i == 0) {
                    Text newText = new Text("You are player " + myPlayerNumber);                    
                    newText.setFont(btnFont);
                    lobbyTexts[i] = newText;
                    
                    lobbyTexts[i].setStroke(players.get(myPlayerNumber-1).getColor());
                    lobbyTexts[i].setFill(players.get(myPlayerNumber-1).getColor());
                }
                else {
                    Text newText = new Text("Player " + i);
                    newText.setFont(btnFont);
                    lobbyTexts[i] = newText;
                    try {
                        if(players.get(i-1).getIsReady()) {
                            lobbyTexts[i].setStroke(Color.GREEN);
                            lobbyTexts[i].setFill(Color.GREEN);
                            checkStartGame();
                        } 
                        else {        
                            lobbyTexts[i].setStroke(players.get(i-1).getColor());
                            lobbyTexts[i].setFill(players.get(i-1).getColor());
                        }

                    } catch(NullPointerException | IndexOutOfBoundsException ex) {
                        lobbyTexts[i].setStroke(Color.WHITE);
                        lobbyTexts[i].setStroke(Color.WHITE);
                    }
                }                
            }                
            playerHolder.getChildren().addAll(lobbyTexts);
        });
    }
    
    void startGame() {
        Platform.runLater(() -> {        
            playerScoreHolder = new HBox();
            for(int i=0; i<players.size(); i++) {
                scoreTexts.add(new Text("Player " + (i+1) + ": 000"));
                scoreTexts.get(i).setFont(btnFont);
                scoreTexts.get(i).setFill(players.get(i).getColor());
                scoreTexts.get(i).setStroke(players.get(i).getColor());
            }
            playerScoreHolder.getChildren().addAll(scoreTexts);
            playerScoreHolder.setSpacing(15);
            
            double cellWidth = width / numCols;
            double cellHeight = (height-50) / numRows;
            ArrayList<Shape> boardPieces = new ArrayList<Shape>();
            for(int x=0; x<numRows; x++) {
                for(int y=0; y<numCols; y++) {
                    switch(board[x][y]) {
                        case 1: // wall
                            Rectangle newWall = new Rectangle();
                            newWall.setHeight(cellHeight);
                            newWall.setWidth(cellWidth);
                            newWall.setFill(Color.BLACK);
                            newWall.setStroke(Color.BLUE);
                            newWall.setX(y * newWall.getWidth());
                            newWall.setY(x * newWall.getHeight());
                            walls.add(newWall);
                            boardPieces.add(newWall);
                            break;
                        case 2: //regular dot
                            Circle newDot = new Circle();
                            newDot.setRadius(5);
                            newDot.setCenterX((y * cellWidth) + (cellWidth / 2));
                            newDot.setCenterY((x * cellHeight) + (cellHeight / 2));     
                            newDot.setFill(Color.WHITE);
                            newDot.setStroke(Color.WHITE);
                            dots.add(newDot);
                            boardPieces.add(newDot);
                            break;
                        case 3:
                            Circle newMagicDot = new Circle();
                            newMagicDot.setRadius(10);
                            newMagicDot.setCenterX((y * cellWidth) + (cellWidth / 2));
                            newMagicDot.setCenterY((x * cellHeight) + (cellHeight / 2));   
                            newMagicDot.setFill(Color.WHITE);
                            newMagicDot.setStroke(Color.WHITE);
                            magicDots.add(newMagicDot);
                            boardPieces.add(newMagicDot);
                            break;
                        case 4:
                            if(players.size() > playerCircles.size()) {
                                Circle newPlayerCircle = new Circle();
                                newPlayerCircle.setRadius(15);
                                newPlayerCircle.setCenterX((y * cellWidth) + (cellWidth / 2));
                                newPlayerCircle.setCenterY((x * cellHeight) + (cellHeight / 2));    
                                newPlayerCircle.setFill(players.get(playerCircles.size()).getColor());
                                newPlayerCircle.setStroke(players.get(playerCircles.size()).getColor());
                                playerCircles.add(newPlayerCircle);
                                boardPieces.add(newPlayerCircle);
                            }
                            break;
                    }
                }
            }
            
            gameBoard.getChildren().addAll(boardPieces);
            gameBoard.setPrefSize(975, 1000);
            
            playerCircles.get(myPlayerNumber-1).setOnKeyPressed((KeyEvent e) -> {
                if(null != e.getCode()) switch(e.getCode()) {
                    case UP:
                        sendMessages.add("MOVE " + myPlayerNumber + " UP");
                        break;
                    case DOWN:
                        sendMessages.add("MOVE " + myPlayerNumber + " DOWN");
                        break;
                    case LEFT:
                        sendMessages.add("MOVE " + myPlayerNumber + " LEFT");
                        break;
                    case RIGHT:
                        sendMessages.add("MOVE " + myPlayerNumber + " RIGHT");
                        break;
                }
            });
            playerCircles.get(myPlayerNumber-1).setOnKeyReleased((KeyEvent e) -> {
                sendMessages.add("MOVE " + myPlayerNumber + " STOP");
            });
            
            System.out.println("Player circle size: " + playerCircles.size());
            System.out.println("Player list size: " + players.size());
            
            Thread gameLoop = new Thread() {
                @Override
                public void run() {
                    while(true) {
                        for(int i=0; i<players.size(); i++) {                            
                            switch(players.get(i).getDirection()) {
                                case "UP":
                                    playerCircles.get(i).setCenterY(playerCircles.get(i).getCenterY() - 5);                                    
                                    break;
                                case "DOWN":
                                    playerCircles.get(i).setCenterY(playerCircles.get(i).getCenterY() + 5);
                                    break;
                                case "LEFT":
                                    playerCircles.get(i).setCenterX(playerCircles.get(i).getCenterX() - 5);
                                    break;
                                case "RIGHT":
                                    playerCircles.get(i).setCenterX(playerCircles.get(i).getCenterX() + 5);
                                    break;                                
                            }
                            players.get(i).setDirection("STOP");
                        }
                    }
                }
            };
            
            gameLoop.start();
            
            root.getChildren().clear();
            root.setTop(playerScoreHolder);
            root.setCenter(gameBoard);
            playerCircles.get(myPlayerNumber-1).requestFocus();
        });
    }
    
    void checkCollision(int index) {
        
    }
    
    void checkStartGame() {
       // if(players.size() < 2) // Can't start game with only one player
        //    return;
        
        for(int i=0; i<players.size(); i++) // loop and return if there's a player who isn't ready
            if(!players.get(i).getIsReady())
                return;
        
        startGame(); // start the game if all players are ready and there's more than one player
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private void connectToServer(String hostAddress) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(hostAddress, 8000);
        
        ReceiveServerMessageTask receiveTask = new ReceiveServerMessageTask(socket);
        Thread thread = new Thread(receiveTask);
        thread.start();

        SendServerMessageTask sendTask = new SendServerMessageTask(socket);
        thread = new Thread(sendTask);
        thread.start();
    }
    
    static class ReceiveServerMessageTask implements Runnable {
        private Socket socket;

        ReceiveServerMessageTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
                // Read in initial message from server containing their player number
                                
                while(true) {
                    try {
                        String message = (String)fromServer.readObject();
                        receiveMessages.add(message);
                    }
                    catch(ClassNotFoundException e2) {}
                }
            } 
            catch(IOException e1) {} 
        }
    }

    static class SendServerMessageTask implements Runnable {
        private Socket socket;

        SendServerMessageTask (Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
                while(true) {
                    String message = sendMessages.take();
                    toServer.writeObject(message);
                }
            } 
            catch(IOException | InterruptedException  e1) {}
        }
    }
    
    static class NetworkPlayer {
        Color playerCol;
        int playerNum;
        int score = 0;
        boolean isAlive;
        boolean isReady;
        String direction = "STOP";
        
        NetworkPlayer(int num) {
            this.playerNum = num;
            switch(num) {
                case 1: playerCol = Color.YELLOW; break;
                case 2: playerCol = Color.RED; break;
                case 3: playerCol = Color.CYAN; break;
                case 4: playerCol = Color.PINK; break;
            }
        }
        
        public void addScore(int points) {
            score += points;
        }
        
        public void killMe() {
            isAlive = false;
        }
        
        public int getPlayerNum() {
            return playerNum;
        }
        
        public int getScore() {
            return score;
        }
        
        public Color getColor() {
            return playerCol;
        }
        
        public boolean isAlive() {
            return isAlive;
        }
        
        public void toggleIsReady() {
            isReady = !isReady;
        }
        
        public boolean getIsReady() {
            return isReady;
        }
        
        public void setDirection(String newDir) {
            direction = newDir;
        }
        
        public String getDirection() {
            return direction;
        }
    }
} // End of class GameClient
