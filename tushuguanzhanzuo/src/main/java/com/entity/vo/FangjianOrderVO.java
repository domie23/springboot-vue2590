package com.entity.vo;

import com.entity.FangjianOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 房间占座
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("fangjian_order")
public class FangjianOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单号
     */

    @TableField(value = "fangjian_order_uuid_number")
    private String fangjianOrderUuidNumber;


    /**
     * 房间
     */

    @TableField(value = "fangjian_id")
    private Integer fangjianId;


    /**
     * 学生
     */

    @TableField(value = "xuesheng_id")
    private Integer xueshengId;


    /**
     * 订单类型
     */

    @TableField(value = "fangjian_order_types")
    private Integer fangjianOrderTypes;


    /**
     * 占的座位
     */

    @TableField(value = "buy_zuowei_number")
    private String buyZuoweiNumber;


    /**
     * 占座日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "buy_zuowei_time")
    private Date buyZuoweiTime;


    /**
     * 占座时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单号
	 */
    public String getFangjianOrderUuidNumber() {
        return fangjianOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setFangjianOrderUuidNumber(String fangjianOrderUuidNumber) {
        this.fangjianOrderUuidNumber = fangjianOrderUuidNumber;
    }
    /**
	 * 设置：房间
	 */
    public Integer getFangjianId() {
        return fangjianId;
    }


    /**
	 * 获取：房间
	 */

    public void setFangjianId(Integer fangjianId) {
        this.fangjianId = fangjianId;
    }
    /**
	 * 设置：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }


    /**
	 * 获取：学生
	 */

    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getFangjianOrderTypes() {
        return fangjianOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setFangjianOrderTypes(Integer fangjianOrderTypes) {
        this.fangjianOrderTypes = fangjianOrderTypes;
    }
    /**
	 * 设置：占的座位
	 */
    public String getBuyZuoweiNumber() {
        return buyZuoweiNumber;
    }


    /**
	 * 获取：占的座位
	 */

    public void setBuyZuoweiNumber(String buyZuoweiNumber) {
        this.buyZuoweiNumber = buyZuoweiNumber;
    }
    /**
	 * 设置：占座日期
	 */
    public Date getBuyZuoweiTime() {
        return buyZuoweiTime;
    }


    /**
	 * 获取：占座日期
	 */

    public void setBuyZuoweiTime(Date buyZuoweiTime) {
        this.buyZuoweiTime = buyZuoweiTime;
    }
    /**
	 * 设置：占座时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：占座时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
