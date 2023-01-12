
package backend;

import frontend.LoginFrame;

public class Main {
    
    public static Sistema sys;

    public static void main(String[] args) {
        
        Main.sys = new Sistema();
        
        new LoginFrame().setVisible(true);
        
        
       
    }
}
