package com.zhxd.traffic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author jijiezh
 *
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "traffic_violation")
public class TrafficViolation extends Model<TrafficViolation> {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 违章车牌号
     */
    @TableField("plate_number")
    private String plateNumber;
    /**
     * 违章人员
     */
    @TableField("owner")
    private String owner;
    /**
     * 地址
     */
    @TableField("address")
    private String address;
    /**
     * 违章车类型
     */
    @TableField("vehicle_type")
    private String vehicleType;
    /**
     * 品牌型号
     */
    @TableField("model")
    private String model;
    /**
     * 注册日期
     */
    @TableField("register_date")
    private String registerDate;
    /**
     * 发证日期
     */
    @TableField("issue_date")
    private String issueDate;
    /**
     * 车辆识别代号
     */
    @TableField("vin")
    private String vin;
    /**
     * 使用性质
     */
    @TableField("use_character")
    private String useCharacter;
    /**
     * 违章时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 违章次数
     */
    @TableField("count")
    private Integer count;


}
