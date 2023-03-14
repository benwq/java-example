package com.lift;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import processing.core.PApplet;

/**
 * @author benwq
 * @create 2023/3/2 13:56
 * @desc 一部电梯整体
 **/
@Setter
@Getter
@NoArgsConstructor
public class Lift {

    private PApplet applet;
    private LiftCar car;
    private LiftTrack track;
    private Float posX;
    private Float posY;
    private Float size;
    private Float high;
    private Float angle;
    private Integer maxFloor;


    public Lift(PApplet applet, Integer maxFloor, Float posX, Float posY
            , Float size, Float high, Float angle) {
        this.applet = applet;
        this.maxFloor = maxFloor;
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.high = high;
        this.angle = angle;
    }

    public void update() {
        car.update();
    }

    public void display() {
        track.display();
        car.display();
    }
}
