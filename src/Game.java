import java.sql.*;
import java.util.*;
public class Game {
    //Carlos Matos, Keyvaun herring, Clark Newell, Devin Gomez
public static HashMap<String,Runnable> commandinputs = new HashMap<>();

public static void  loadgame(){

}
public static void registerCommands(HashMap<String,Runnable> commandinputs){
   commandinputs.put("quit",()-> quitgame());
   commandinputs.put("lost",()-> lost());
}
public static void quitgame(){
    System.out.println("Thank you for playing bye");
    System.exit(0);
}
public static void lost(){
    System.out.println();
}

}
