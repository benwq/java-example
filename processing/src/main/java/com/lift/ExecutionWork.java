package com.lift;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author benwq
 * @create 2023/3/23 11:04
 * @desc 电梯运动动作分解单元
 **/
@Setter
@Getter
@AllArgsConstructor
public class ExecutionWork {
    private Integer previousFloor;
    private Integer nextFloor;
}
