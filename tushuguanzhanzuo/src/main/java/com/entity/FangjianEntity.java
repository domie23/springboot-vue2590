package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 房间
 *
 * @author 
 * @email
 */
@TableName("fangjian")
public class FangjianEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FangjianEntity() {

	}

	public FangjianEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 房间名称
     */
    @TableField(value = "fangjian_name")

    private String fangjianName;


    /**
     * 房间照片
     */
    @TableField(value = "fangjian_photo")

    private String fangjianPhoto;


    /**
     * 房间类型
     */
    @TableField(value = "fangjian_types")

    private Integer fangjianTypes;


    /**
     * 座位
     */
    @TableField(value = "zuowei_number")

    private Integer zuoweiNumber;


    /**
     * 点击次数
     */
    @TableField(value = "fangjian_clicknum")

    private Integer fangjianClicknum;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "fangjian_delete")

    private Integer fangjianDelete;


    /**
     * 房间介绍
     */
    @TableField(value = "fangjian_content")

    private String fangjianContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：房间名称
	 */
    public String getFangjianName() {
        return fangjianName;
    }


    /**
	 * 获取：房间名称
	 */

    public void setFangjianName(String fangjianName) {
        this.fangjianName = fangjianName;
    }
    /**
	 * 设置：房间照片
	 */
    public String getFangjianPhoto() {
        return fangjianPhoto;
    }


    /**
	 * 获取：房间照片
	 */

    public void setFangjianPhoto(String fangjianPhoto) {
        this.fangjianPhoto = fangjianPhoto;
    }
    /**
	 * 设置：房间类型
	 */
    public Integer getFangjianTypes() {
        return fangjianTypes;
    }


    /**
	 * 获取：房间类型
	 */

    public void setFangjianTypes(Integer fangjianTypes) {
        this.fangjianTypes = fangjianTypes;
    }
    /**
	 * 设置：座位
	 */
    public Integer getZuoweiNumber() {
        return zuoweiNumber;
    }


    /**
	 * 获取：座位
	 */

    public void setZuoweiNumber(Integer zuoweiNumber) {
        this.zuoweiNumber = zuoweiNumber;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getFangjianClicknum() {
        return fangjianClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setFangjianClicknum(Integer fangjianClicknum) {
        this.fangjianClicknum = fangjianClicknum;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getFangjianDelete() {
        return fangjianDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setFangjianDelete(Integer fangjianDelete) {
        this.fangjianDelete = fangjianDelete;
    }
    /**
	 * 设置：房间介绍
	 */
    public String getFangjianContent() {
        return fangjianContent;
    }


    /**
	 * 获取：房间介绍
	 */

    public void setFangjianContent(String fangjianContent) {
        this.fangjianContent = fangjianContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Fangjian{" +
            "id=" + id +
            ", fangjianName=" + fangjianName +
            ", fangjianPhoto=" + fangjianPhoto +
            ", fangjianTypes=" + fangjianTypes +
            ", zuoweiNumber=" + zuoweiNumber +
            ", fangjianClicknum=" + fangjianClicknum +
            ", shangxiaTypes=" + shangxiaTypes +
            ", fangjianDelete=" + fangjianDelete +
            ", fangjianContent=" + fangjianContent +
            ", createTime=" + createTime +
        "}";
    }
}
