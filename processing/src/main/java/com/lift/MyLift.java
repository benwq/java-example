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

    public static float FRAME_RATE = 48;

    PImage pg;
    List<Lift> liftList;
    static List<Lift> liftList2;


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
        frameRate(FRAME_RATE);
        liftList = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            Integer floor = r.nextInt(24) + 1;
            Lift lift = new Lift(this, 10, 25, 1, floor, 1, 100f + xPow + i * 256, pixelHeight / 2f, 2f * xPow, 150f * yPow, -PI / 6);
            System.out.println((i + 1) + "号梯初始位置:" + floor + "层");
            lift.setApplet(this);
            liftList.add(lift);
        }
        liftList2 = liftList;
        System.out.println(1);
    }

//    public static void setNextFloor(List<RealTimeFloorData> dataList) {
//        for (int i = 0; i < dataList.size(); i++) {
//            RealTimeFloorData data = dataList.get(i);
//            Lift lift = liftList2.get(i);
//            List<ExecutionWork> workList = resolve(data, lift);
//            if (workList != null && workList.size() > 0) {
//                for (ExecutionWork w : workList) {
//                    lift.getArrayDeque().addFirst(w);
//                }
//            }
//        }
//    }
//
//    /**
//     * @author benwq
//     * @create 2023/3/23 14:45
//     * @desc 根据当前电梯状态分解运动
//     **/
//    private static List<ExecutionWork> resolve(RealTimeFloorData data, Lift lift) {
//        List<ExecutionWork> executionWorkList = new ArrayList<>();
//        //目标楼层
//        Integer targetFloor = data.getCurrentFloor();
//        //轿厢目前所处楼层
//        Integer carFloor = lift.getCar().getCurrentFloor();
//        Integer diffFloor = Math.abs(targetFloor - carFloor);
//        WinObj winObj = lift.calcWinObj();
//        //当前窗口顶部楼层
//        Integer windowTopFloor = winObj.getWindowTopFloor();
//        //当前窗口底部楼层
//        Integer windowBottomFloor = winObj.getWindowBottomFloor();
//        Integer startFloor = lift.getStartFloor();
//        Integer endFloor = lift.getEndFloor();
//        //只移动轿厢
//        if (targetFloor <= windowBottomFloor && targetFloor >= windowTopFloor) {
//            ExecutionWork work = new ExecutionWork(carFloor, targetFloor, true);
//            executionWorkList.add(work);
//        } else if (targetFloor > windowBottomFloor) {
//            if (windowTopFloor - diffFloor >= 0) {
//                ExecutionWork work = new ExecutionWork(carFloor, targetFloor, false);
//                executionWorkList.add(work);
//            } else {
//                ExecutionWork work0 = new ExecutionWork(carFloor, carFloor - windowTopFloor + startFloor, false);
//                ExecutionWork work1 = new ExecutionWork(carFloor - windowTopFloor + startFloor, targetFloor, true);
//                executionWorkList.add(work0);
//                executionWorkList.add(work1);
//            }
//        } else if (targetFloor < windowTopFloor) {
//            if (windowBottomFloor + diffFloor <= endFloor) {
//                ExecutionWork work = new ExecutionWork(carFloor, targetFloor, false);
//                executionWorkList.add(work);
//            } else {
//                ExecutionWork work0 = new ExecutionWork(carFloor, carFloor + endFloor - windowTopFloor, false);
//                ExecutionWork work1 = new ExecutionWork(carFloor + endFloor - windowTopFloor, targetFloor, true);
//                executionWorkList.add(work0);
//                executionWorkList.add(work1);
//            }
//        }
//        return executionWorkList;
//    }
//
//    public static class MyThread extends Thread {
//        @Override
//        public void run() {
//            Random r = new Random();
//            while (true) {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                List<RealTimeFloorData> dataList = new ArrayList<>();
//                RealTimeFloorData d1 = new RealTimeFloorData(null, 4, 0);
//                RealTimeFloorData d2 = new RealTimeFloorData(null, 22, 0);
//                RealTimeFloorData d3 = new RealTimeFloorData(null, r.nextInt(25), 0);
//                RealTimeFloorData d4 = new RealTimeFloorData(null, r.nextInt(25), 0);
//                dataList.add(d1);
//                dataList.add(d2);
//                dataList.add(d3);
//                dataList.add(d4);
//                setNextFloor(dataList);
//            }
//        }
//    }

    public static void main(String[] args) {
        PApplet.main(MyLift.class.getName());
//        new Thread(new MyThread()).start();
    }
}
