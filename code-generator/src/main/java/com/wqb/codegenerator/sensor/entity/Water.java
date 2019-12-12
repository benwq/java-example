package com.wqb.codegenerator.sensor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author benwq
 * @since 2019-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sensor_water")
@ApiModel(value="Water对象", description="")
public class Water implements Serializable {

    private static final long serialVersionUID = 1L;

    private String date;

    @ApiModelProperty(value = "关联基础数据中的id号")
    private String tagName;

    @ApiModelProperty(value = "PH")
    private Float tag1;

    @ApiModelProperty(value = "电导")
    private Float tag2;

    @ApiModelProperty(value = "溶解氧")
    private Float tag3;

    @ApiModelProperty(value = "浊度")
    private Float tag4;

    @ApiModelProperty(value = "温度")
    private Float tag5;


}
