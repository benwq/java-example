package com.lift2;


import com.lift.ExecutionWork;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyLift2 extends PApplet {

    public static float FRAME_RATE = 48;
    public static Float WINDOW_MOVE_THRESHOLD = 150f;

    PImage pg;
    List<Lift> liftList;
    static List<com.lift2.Lift> liftList2;
    Integer windowFloorSize;


    @Override
    public void settings() {
        size(1024, 910, P3D);

        String path = "D:/workspace/idea/github/java-example/processing/src/main/java/";
        pg = loadImage(path + "com/lift/space.png");
    }

    @Override
    public void draw() {
        background(pg);
        for (com.lift2.Lift lift : liftList) {
            lift.update();
            lift.display();
        }
        stroke(125, 125, 255);
        line(0, pixelHeight / 2 - 170, pixelWidth, pixelHeight / 2 - 170);
        line(0, pixelHeight / 2 + 170, pixelWidth, pixelHeight / 2 + 170);
    }

    @Override
    public void setup() {
        int maxFloor = 25;
        int sizePow = 30;
        frameRate(FRAME_RATE);
        windowFloorSize = 10;
        liftList = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            Integer floor = r.nextInt(24) + 1;
            Lift lift = new Lift(this, windowFloorSize, 125f + i * 256, pixelHeight / 2f, sizePow * 1f, maxFloor * sizePow * 1f, -PI / 6
                    , 1, 25, floor);
            System.out.println((i + 1) + "号梯初始位置:" + floor + "层");
            liftList.add(lift);
        }
        liftList2 = liftList;
        System.out.println(1);
    }

    public static void setNextFloor(List<ExecutionWork> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            ExecutionWork data = dataList.get(i);
            com.lift2.Lift lift = liftList2.get(i);
            System.out.println((i + 1) + "号梯将移动到:" + data.getNextFloor() + "层");
            lift.move(data);
        }
    }


    public static class MyThread extends Thread {
        @Override
        public void run() {
            Random r = new Random();
            while (true) {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<ExecutionWork> dataList = new ArrayList<>();
                ExecutionWork d1 = new ExecutionWork(null, 1);
                ExecutionWork d2 = new ExecutionWork(null, 25);
                ExecutionWork d3 = new ExecutionWork(null, r.nextInt(24) + 1);
                ExecutionWork d4 = new ExecutionWork(null, r.nextInt(24) + 1);
                dataList.add(d1);
                dataList.add(d2);
                dataList.add(d3);
                dataList.add(d4);
                setNextFloor(dataList);
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main(com.lift2.MyLift2.class.getName());
        new Thread(new com.lift2.MyLift2.MyThread()).start();
    }
}
