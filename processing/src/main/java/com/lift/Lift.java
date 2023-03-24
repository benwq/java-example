package com.lift;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import processing.core.PApplet;

import java.util.ArrayDeque;
import java.util.Random;

/**
 * @author benwq
 * @create 2023/3/2 13:56
 * @desc 一部电梯整体
 **/
@Setter
@Getter
@NoArgsConstructor
public class Lift {

    /*p5静态属性*/
    private PApplet applet;
    private Float posX;
    private Float posY;
    private Float size;
    private Float high;
    private Float angle;
    /*p5静态属性*/

    /*电梯物理属性*/
    private Float speed;
    private Integer startFloor;
    private Integer endFloor;
    private LiftCar car;
    private LiftTrack track;
    /*电梯物理属性*/

    /*p5动态属性*/
    private Integer windowSize;
    private Boolean singleMove;
    private Float currentMidFloor;
    private Boolean whoMove;
    /*p5动态属性*/

    private ArrayDeque<ExecutionWork> arrayDeque;
    private ExecutionWork currentWork;

    public Lift(PApplet applet, Integer windowsSize, Integer endFloor, Integer startFloor, Integer currentFloor, Integer currentRunState, Float posX, Float posY
            , Float size, Float high, Float angle) {
        arrayDeque = new ArrayDeque<>();
        this.applet = applet;
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.high = high;
        this.angle = angle;
        this.speed = 40f;
        this.endFloor = endFloor;
        this.windowSize = windowsSize;
        this.startFloor = startFloor;
        Integer randomMove = new Random().nextInt(windowsSize / 2) - windowsSize;
        this.currentMidFloor = currentFloor + randomMove + -windowsSize + 0f;
        if (endFloor - startFloor > windowsSize) {
            singleMove = false;
        } else {
            singleMove = true;
        }
        this.car = new LiftCar(applet, this, currentFloor, currentRunState);
        this.track = new LiftTrack(applet, this, 0f);
    }

    public void update() {
        if (currentWork == null) {
            currentWork = arrayDeque.pollLast();
        }
        if (currentWork != null) {
            Integer nextFloor = currentWork.getNextFloor();
//            Integer currentFloor = this.getCar().getCurrentFloor();
            Integer currentFloor = getFloorInteger(calcCurrentFloor());
            if (nextFloor.equals(currentFloor)) {
                currentWork = null;
            } else {
//                if (currentWork.getWhoMove()) {
//                    car.move(currentWork);
//                } else {
//                    track.move(currentWork);
//                }
            }
        }
    }

    public Float calcMidFloor() {
        return currentMidFloor;
    }

    public Float calcCurrentFloor() {
        Float carY = car.getPosY();
        Float trackY = track.getPosY();
        Float trackTopY = trackY - high / 2;
        return (carY - trackTopY) / size;
    }

    public Integer getFloorInteger(Float floor) {
        return floor.intValue();
    }

    public WinObj calcWinObj() {
        WinObj winObj = new WinObj();
        Integer topFloor = (int) (currentMidFloor - windowSize.floatValue() / 2);
        winObj.setWindowTopFloor(topFloor);
        winObj.setWindowBottomFloor(topFloor + windowSize);
        return winObj;
    }

    public void display() {
        track.display();
        car.display();
    }
}
