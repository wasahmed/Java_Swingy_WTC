package com.wasahmed.swingy;

import com.wasahmed.swingy.views.Console;
import com.wasahmed.swingy.views.FirstScreen;

public class Swingy {
    public static void main(String[] args) {

        Console console = new Console();
        if (args.length == 0){
            System.out.println("Please choose GUI or console");
        }
        else if (args[0].equalsIgnoreCase("GUI")){
            new FirstScreen(100,150);
        }
        else if (args[0].equalsIgnoreCase("console")){
//            System.out.println("console game");
            console.runGameInConsole();
        }
        else {
            System.out.println("Invalid option. Please choose GUI or console");
        }

    }
}
