package com.entity.model;

import com.entity.FangjianOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 房间占座
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class FangjianOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String fangjianOrderUuidNumber;


    /**
     * 房间
     */
    private Integer fangjianId;


    /**
     * 学生
     */
    private Integer xueshengId;


    /**
     * 订单类型
     */
    private Integer fangjianOrderTypes;


    /**
     * 占的座位
     */
    private String buyZuoweiNumber;


    /**
     * 占座日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date buyZuoweiTime;


    /**
     * 占座时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getFangjianOrderUuidNumber() {
        return fangjianOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setFangjianOrderUuidNumber(String fangjianOrderUuidNumber) {
        this.fangjianOrderUuidNumber = fangjianOrderUuidNumber;
    }
    /**
	 * 获取：房间
	 */
    public Integer getFangjianId() {
        return fangjianId;
    }


    /**
	 * 设置：房间
	 */
    public void setFangjianId(Integer fangjianId) {
        this.fangjianId = fangjianId;
    }
    /**
	 * 获取：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }


    /**
	 * 设置：学生
	 */
    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getFangjianOrderTypes() {
        return fangjianOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setFangjianOrderTypes(Integer fangjianOrderTypes) {
        this.fangjianOrderTypes = fangjianOrderTypes;
    }
    /**
	 * 获取：占的座位
	 */
    public String getBuyZuoweiNumber() {
        return buyZuoweiNumber;
    }


    /**
	 * 设置：占的座位
	 */
    public void setBuyZuoweiNumber(String buyZuoweiNumber) {
        this.buyZuoweiNumber = buyZuoweiNumber;
    }
    /**
	 * 获取：占座日期
	 */
    public Date getBuyZuoweiTime() {
        return buyZuoweiTime;
    }


    /**
	 * 设置：占座日期
	 */
    public void setBuyZuoweiTime(Date buyZuoweiTime) {
        this.buyZuoweiTime = buyZuoweiTime;
    }
    /**
	 * 获取：占座时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：占座时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
