package com.lift;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author benwq
 * @create 2023/3/23 9:39
 * @desc
 **/
@Getter
@Setter
@AllArgsConstructor
public class RealTimeFloorData {
    private String id;
    private Integer currentFloor;
    private Integer state;
}
