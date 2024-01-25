package _base.design_pattern.template;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/1/25
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Main {
    public static void main(String[] args) {

        Game game = new Cricket();
        game.play();


        game = new Football();
        game.play();
    }
}
