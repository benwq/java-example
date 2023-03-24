package com.lift2;

import com.lift.ExecutionWork;
import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;

import java.util.ArrayDeque;

/**
 * @author benwq
 * @create 2023/3/24 9:51
 * @desc 电梯整体，记录电梯在画板中的位置
 **/
@Setter
@Getter
public class Lift {
    private PApplet p;
    private Integer windowFloorSize;
    private Float posX;
    private Float posY;
    private Float size;
    private Float high;
    private Float angle;
    private LiftCar car;
    private LiftTrack track;

    private Float yOffset;

    private Integer startFloor;
    private Integer endFloor;

    private Integer initFloor;

    private ArrayDeque<ExecutionWork> arrayDeque;
    private ExecutionWork currentWork;

    private static Float SAME_FLOOR_DIS = 3f;


    public Lift(PApplet p, Integer windowFloorSize, Float x, Float y, Float size, Float high
            , Float angle, Integer startFloor, Integer endFloor, Integer initFloor) {
        arrayDeque = new ArrayDeque<>();
        this.p = p;
        this.windowFloorSize = windowFloorSize;
        this.posX = x;
        this.posY = y;
        this.size = size;
        this.high = high;
        this.angle = angle;
        this.yOffset = 0f;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.car = new LiftCar(p, this, calcCarPosY(initFloor), MyLift2.FRAME_RATE, 0);
        this.track = new LiftTrack(p, this);
    }

    public void move(ExecutionWork work) {
        arrayDeque.addFirst(work);
    }

    public void update() {
        if (currentWork == null) {
            currentWork = arrayDeque.pollLast();
        }
        if (currentWork != null) {
            Float targetCarY = calcCarPosY(currentWork.getNextFloor());
            Float currentCarY = car.getPosY();
            Float dis = targetCarY - currentCarY;
            Float disAbs = Math.abs(dis);
            if (disAbs <= SAME_FLOOR_DIS) {
                car.setRunState(0);
                currentWork = null;
            } else if (targetCarY > currentCarY) {
                car.setRunState(-1);
            } else if (targetCarY < currentCarY) {
                car.setRunState(1);
            }
            car.update();
        }
        Float windOffset = car.getPosY() + yOffset;
        if (Math.abs(windOffset) > MyLift2.WINDOW_MOVE_THRESHOLD) {
            yOffset -= (windOffset > 0 ? 1 : -1) * car.getSpeed() / p.frameRate;
        }
    }

    public void display() {
        p.pushMatrix();
        p.translate(posX, posY + yOffset, 0);
        p.rotateY(angle);
        car.display();
        track.display();
        p.popMatrix();
    }

    private Float calcCarPosY(Integer floor) {
        Float preFloorPx = high / (endFloor - startFloor + 1);
        Float targetCarY = preFloorPx * (floor - startFloor) + preFloorPx / 2 - high / 2;
        return targetCarY;
    }
}
