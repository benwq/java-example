package com.lift2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;

/**
 * @author benwq
 * @create 2023/3/24 9:51
 * @desc
 **/
@Setter
@Getter
@AllArgsConstructor
public class LiftTrack {

    private PApplet p;
    private Lift parent;

    /**
     * @author benwq
     * @create 2023/3/24 10:22
     * @desc 必须坐标系移动到相对电梯状态
     **/
    public void display() {
        p.stroke(255, 255, 255);
        p.noFill();
        p.box(parent.getSize(), parent.getHigh(), parent.getSize());
    }
}
