import greenfoot.*;

public class FinalScreen extends World{
    private boolean returnEnabled = false;
    
    public FinalScreen(boolean victory, int score){
        super(1600, 800, 1);

        if (victory){
            showText("VITÓRIA!", getWidth() / 2, 300);
        } else {
            showText("GAME OVER", getWidth() / 2, 300);
        }

        showText("Pontuação: " + score, getWidth() / 2, 400);

        showText("Pressione ENTER para voltar ao menu", getWidth() / 2, 500);
    }

    @Override
    public void act() {
        if (!Greenfoot.isKeyDown("enter")) {
            returnEnabled = true;
    
            return;
        }
    
        if (returnEnabled) {
            Greenfoot.setWorld(new Menu());
        }
    }
}