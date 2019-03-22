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
@TableName("sensor_atmosphere")
@ApiModel(value="Atmosphere对象", description="")
public class Atmosphere implements Serializable {

    private static final long serialVersionUID = 1L;

    private String date;

    @ApiModelProperty(value = "关联基础数据中的id号")
    private String tagName;

    @ApiModelProperty(value = "温度")
    private Float tag1;

    @ApiModelProperty(value = "湿度")
    private Float tag2;

    @ApiModelProperty(value = "PM2.5")
    private Float tag3;

    @ApiModelProperty(value = "PM10")
    private Float tag4;

    @ApiModelProperty(value = "负离子")
    private Float tag5;


}
