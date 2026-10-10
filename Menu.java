import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Menu extends World{
    private enum MenuState{
        OPENING,
        PLAYER_SELECTION
    }
    
    private MenuState state = MenuState.OPENING;
    private int selectedPlayers = 1;
    
    public Menu(){    
        super(1600, 800, 1); 
        
        showOpening();
    }
    
    @Override
    public void act(){
        String key = Greenfoot.getKey();
        
        if(key == null){
            return;
        }
        
        if(state == MenuState.OPENING){
            if("space".equals(key)){
                state = MenuState.PLAYER_SELECTION;
                
                showPlayerSelection();
            }
            
            return;
        }
        
        if("up".equals(key)){
            selectedPlayers = 1;
            
            showPlayerSelection();
        } else if ("down".equals(key)){
            selectedPlayers = 2;
            
            showPlayerSelection();
        } else if("enter".equals(key)){
            Greenfoot.setWorld(new Arena(selectedPlayers));
        }
    }
    
    private void showOpening(){
        showText("Frost & Flame", getWidth() / 2, 250);
        
        showText("pressione SPACE para jogar", getWidth() / 2, 400);
    }
    
    private void showPlayerSelection(){
        String onePLayer = selectedPlayers == 1
            ? "> 1 player"
            : " 1 player";
            
        String TwoPlayer = selectedPlayers == 2
            ? "> 2 players"
            : " 2 players";
        
        showText(onePLayer, getWidth() / 2, 400);
        showText(TwoPlayer, getWidth() / 2, 500);
        
        showText("Use as setas para escolher e ENTER para jogar", getWidth() / 2, 600);
    }
}
