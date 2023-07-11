package com.example.naverwebtoonrenewal;

import android.graphics.Color;

import java.util.Random;

public class RandomColor {

    public int generateRandomColor(){

        Random random = new Random();
        int alpha = 120;
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(100);


        return Color.argb(alpha, red, green, blue);
    }
}
