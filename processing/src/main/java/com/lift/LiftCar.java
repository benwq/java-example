package com.lift;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;

/**
 * @author benwq
 * @create 2023/3/2 13:52
 * @desc 轿厢
 **/
@Setter
@Getter
@AllArgsConstructor
public class LiftCar {

    private PApplet applet;
    private Lift parent;
    private Integer doorState;
    private Integer runState;

    private Float posY;
    private Integer currentFloor;
    private Integer nextFloor;

    public LiftCar(PApplet applet, Lift parent, Integer currentFloor, Integer runState) {
        this.applet = applet;
        this.parent = parent;
        this.doorState = 0;
        this.runState = runState;
        this.currentFloor = currentFloor;
//        this.posY = parent.getHigh() / parent.getEndFloor() * (parent.getEndFloor() - currentFloor - 1);
        this.posY = parent.getHigh()/(parent.getEndFloor()-parent.getStartFloor())*(parent.getEndFloor()-currentFloor-1);
    }

    public void move(ExecutionWork work) {
        Integer targetFloor = work.getNextFloor();
        float speed = parent.getSpeed();
        //向上为-，向下为+，静止为0
        if (targetFloor > currentFloor) {
            speed = speed * -1;
        } else if (targetFloor < currentFloor) {
            speed = speed * 1;
        } else {
            speed = 0;
        }
        posY = posY - speed * (1 / applet.frameRate);
    }

    public void display() {
        float angle = parent.getAngle();
        float curPosX = parent.getPosX();
        float curPosY = parent.getPosY() + parent.getHigh() / 2 - parent.getSize() / 2 - posY;
        applet.pushMatrix();
        applet.translate(curPosX, curPosY, 0);
        applet.stroke(255, 255, 0);
        applet.noFill();
        applet.rotateY(angle);
        float size = parent.getSize() * 0.9f;
        applet.box(size);
        applet.popMatrix();
    }
}
