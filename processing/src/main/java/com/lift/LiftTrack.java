package com.lift;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;

/**
 * @author benwq
 * @create 2023/3/2 13:53
 * @desc 电梯轨道
 **/
@Setter
@Getter
@AllArgsConstructor
public class LiftTrack {

    private PApplet applet;
    private Lift parent;
    private Float posY;

    public void move(ExecutionWork work) {
        Float midFloor = parent.calcMidFloor();
        Integer targetFloor = work.getNextFloor();
        float speed = parent.getSpeed();
        //向上为-，向下为+
        if (targetFloor > midFloor) {
            speed = speed * 1;
        } else if (targetFloor < midFloor) {
            speed = speed * -1;
        } else {
            speed = 0;
        }
        posY = posY - speed * (1 / applet.frameRate);
    }

    /**
     * @author benwq
     * @create 2023/3/2 14:30
     * @desc 右侧45度投影
     **/
    public void display() {
        float angle = parent.getAngle();
        applet.pushMatrix();
        applet.translate(parent.getPosX(), parent.getPosY() + posY, 0);
        applet.stroke(255, 255, 255);
        applet.noFill();
        applet.rotateY(angle);
        applet.box(parent.getSize(), parent.getHigh(), parent.getSize());
        applet.popMatrix();
    }


}
