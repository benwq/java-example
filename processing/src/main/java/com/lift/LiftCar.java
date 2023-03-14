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
    private Float maxSpeedAbs;
    private Float acc;
    private Float speed;
    private Float posY;

    private Integer initFloor;

    public LiftCar(PApplet applet, Lift parent, Integer initFloor, Integer runState) {
        this.applet = applet;
        this.parent = parent;
        this.doorState = 0;
        this.runState = runState;
        this.maxSpeedAbs = 40f;
        this.acc = 20f;
        this.posY = parent.getHigh() / parent.getMaxFloor() * (parent.getMaxFloor() - initFloor - 1);
        this.speed = 0f;
    }

    public void update() {
        //向上为-，向下为+，静止为0
        float speedAdd = runState * acc * (1 / applet.frameRate);
        float curSpeed = speedAdd + speed;
        int dire = curSpeed >= 0 ? 1 : -1;
        float speedAbs = PApplet.abs(curSpeed);
        speed = dire * (speedAbs <= maxSpeedAbs ? speedAbs : maxSpeedAbs);
        posY = posY - speed * (1 / applet.frameRate);
        if (posY < 0) {
            runState = -1;
            speed = 0f;
        } else if (posY > parent.getHigh() - parent.getSize()) {
            runState = 1;
            speed = 0f;
        }
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
