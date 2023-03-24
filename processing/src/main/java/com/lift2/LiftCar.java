package com.lift2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;

/**
 * @author benwq
 * @create 2023/3/24 9:50
 * @desc
 **/
@Setter
@Getter
@AllArgsConstructor
public class LiftCar {

    private PApplet p;
    private Lift parent;
    private Float posY;
    private Float speed;
    private Integer runState;

    public void update() {
        Float realSpeed = speed * runState;
        posY = posY - realSpeed / p.frameRate;
    }

    public void display() {
        p.pushMatrix();
        p.translate(0, posY, 0);
        p.stroke(255, 255, 0);
        p.noFill();
        float size = parent.getSize() * 0.95f;
        p.box(size);
        p.popMatrix();
    }
}
