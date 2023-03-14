package com.lift;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author benwq
 * @create 2023/3/2 13:50
 * @desc 电梯图
 **/
public class MyLift extends PApplet {

    PImage pg;
    List<Lift> liftList;

    @Override
    public void settings() {
        size(1024, 910, P3D);
        String path = "D:/workspace/idea/github/java-example/processing/src/main/java/";
        pg = loadImage(path + "com/lift/space.png");
    }

    @Override
    public void draw() {
        background(pg);
        for (Lift lift : liftList) {
            lift.update();
            lift.display();
        }
    }

    @Override
    public void setup() {
        int realScale = 10;
        int maxFloor = 25;
        int xPow = 25;
        int yPow = 3;
        liftList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Lift lift = new Lift(this, 25,  100f + xPow + i * 256, pixelHeight / 2f, 2f * xPow, 150f * yPow, -PI / 6);
            LiftCar liftCar = new LiftCar(this, lift, new Random().nextInt(maxFloor), new Random().nextInt(3) - 3);
            LiftTrack liftTrack = new LiftTrack(this, lift);
            lift.setApplet(this);
            lift.setTrack(liftTrack);
            lift.setCar(liftCar);
            liftList.add(lift);
        }
        System.out.println(1);
    }

    public static void main(String[] args) {
        PApplet.main(MyLift.class.getName());
    }
}
