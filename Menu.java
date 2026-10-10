import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Menu extends World{
    private enum MenuState{
        OPENING,
        PLAYER_SELECTION,
        CHARACTER_SELECTION
    }
    
    private MenuState state = MenuState.OPENING;
    private int selectedPlayers = 1;
    
    private WizardType selectedWizard = WizardType.FIRE;
    
    public Menu(){    
        super(1600, 800, 1); 
        
        render();
    }
    
    @Override
    public void act(){
        String key = Greenfoot.getKey();
        
        if(key == null){
            return;
        }
        
        switch (state){
            case OPENING:
                if("space".equals(key)){
                state = MenuState.PLAYER_SELECTION;
                
                render();
                }
                break;
            
            case PLAYER_SELECTION:
                if ("up".equals(key)) {
                    selectedPlayers = 1;

                    render();
                } else if ("down".equals(key)) {
                    selectedPlayers = 2;

                    render();
                } else if ("enter".equals(key)) {
                    if (selectedPlayers == 1) {
                        state = MenuState.CHARACTER_SELECTION;

                        render();
                    } else {
                        Greenfoot.setWorld(new Arena(2));
                    }
                }
                break;
                
            case CHARACTER_SELECTION:
                if ("left".equals(key)) {
                    selectedWizard = WizardType.FIRE;

                    render();
                } else if ("right".equals(key)) {
                    selectedWizard = WizardType.ICE;

                    render();
                } else if ("enter".equals(key)) {
                    Greenfoot.setWorld(
                        new Arena(1, selectedWizard)
                    );
                } else if ("escape".equals(key)) {
                    state = MenuState.PLAYER_SELECTION;

                    render();
                }
                break;
        }
    }
    
    private void render() {
        GreenfootImage background = new GreenfootImage(
            getWidth(),
            getHeight()
        );

        background.setColor(new Color(25, 25, 35));
        background.fill();

        setBackground(background);

        showText(null, 800, 180);
        showText(null, 800, 400);
        showText(null, 800, 500);
        showText(null, 800, 650);
        showText(null, 550, 500);
        showText(null, 1050, 500);

        switch (state) {
            case OPENING:
                showText("Frost & Flame", 800, 180);

                showText(
                    "Pressione SPACE para continuar",
                    800,
                    400
                );
                break;

            case PLAYER_SELECTION:
                showText("Escolha o modo de jogo", 800, 180);

                showText(
                    (selectedPlayers == 1 ? "> " : "")
                        + "1 player",
                    800,
                    400
                );

                showText(
                    (selectedPlayers == 2 ? "> " : "")
                        + "2 players",
                    800,
                    500
                );

                showText(
                    "Cima/baixo: escolher | ENTER: confirmar",
                    800,
                    650
                );
                break;

            case CHARACTER_SELECTION:
                showText("Escolha seu mago", 800, 180);

                drawCharacter(WizardType.FIRE, 550, 350);
                drawCharacter(WizardType.ICE, 1050, 350);

                showText(
                    "Esquerda/direita: escolher"
                        + " | ENTER: jogar | ESC: voltar",
                    800,
                    650
                );
                break;
        }
    }

    private void drawCharacter(
        WizardType type,
        int x,
        int y
    ) {
        GreenfootImage image = new GreenfootImage(
            type.getImageName()
        );

        image.scale(200, 200);

        getBackground().drawImage(
            image,
            x - 100,
            y - 100
        );

        if (type == selectedWizard) {
            getBackground().setColor(
                new Color(255, 210, 80)
            );

            getBackground().drawRect(
                x - 120,
                y - 120,
                240,
                240
            );
        }

        showText(
            (type == selectedWizard ? "> " : "")
                + type.getDisplayName(),
            x,
            500
        );
    }
}
