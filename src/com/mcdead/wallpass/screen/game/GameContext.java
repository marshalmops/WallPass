package com.mcdead.wallpass.screen.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class GameContext {
    public final static int C_DEFAULT_FRAME_TIME = 11;
    public final static String C_DEFAULT_PLAYER_IMAGE_FILENAME = "player.png";
    public final static String C_DEFAULT_ROCK_IMAGE_FILENAME   = "rock.png";
    public final static String C_DEFAULT_BOX_IMAGE_FILENAME = "box.png";

    private static Map<Class<? extends GameObject>, BufferedImage> s_objectImageMap;

    public static boolean initialize() throws IOException {
        s_objectImageMap = new HashMap<>();

        BufferedImage playerImg       = new BufferedImage(Player.C_DEFAULT_WIDTH, Player.C_DEFAULT_HEIGHT, BufferedImage.TYPE_INT_RGB);
        InputStream   playerImgStream = GameContext.class.getResourceAsStream(C_DEFAULT_PLAYER_IMAGE_FILENAME);

        if (playerImgStream == null) return false;

        BufferedImage playerImgBuffer = ImageIO.read(playerImgStream);

        playerImg.createGraphics().drawImage(playerImgBuffer, 0, 0, Player.C_DEFAULT_WIDTH, Player.C_DEFAULT_HEIGHT, null);

        BufferedImage rockImg       = new BufferedImage(Rock.C_DEFAULT_WIDTH, Rock.C_DEFAULT_HEIGHT, BufferedImage.TYPE_INT_RGB);
        InputStream   rockImgStream = GameContext.class.getResourceAsStream(C_DEFAULT_ROCK_IMAGE_FILENAME);

        if (rockImgStream == null) return false;

        BufferedImage rockImgBuffer = ImageIO.read(rockImgStream);

        rockImg.createGraphics().drawImage(rockImgBuffer, 0, 0, Rock.C_DEFAULT_WIDTH, Rock.C_DEFAULT_HEIGHT, null);

        BufferedImage boxImg       = new BufferedImage(Box.C_DEFAULT_WIDTH, Box.C_DEFAULT_HEIGHT, BufferedImage.TYPE_INT_RGB);
        InputStream   boxImgStream = GameContext.class.getResourceAsStream(C_DEFAULT_BOX_IMAGE_FILENAME);

        if (boxImgStream == null) return false;

        BufferedImage boxImgBuffer = ImageIO.read(boxImgStream);

        boxImg.createGraphics().drawImage(boxImgBuffer, 0, 0, Rock.C_DEFAULT_WIDTH, Rock.C_DEFAULT_HEIGHT, null);


        s_objectImageMap.put(Player.class, playerImg);
        s_objectImageMap.put(Rock.class,   rockImg);
        s_objectImageMap.put(Box.class,    boxImg);

        return true;
    }

    public static boolean setImageForObject(GameObject object) {
        BufferedImage foundImg = s_objectImageMap.get(object.getClass());

        if (foundImg == null) return false;

        return object.setImage(foundImg);
    }

}
