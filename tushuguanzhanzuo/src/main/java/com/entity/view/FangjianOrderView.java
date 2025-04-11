package com.entity.view;

import com.entity.FangjianOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 房间占座
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("fangjian_order")
public class FangjianOrderView extends FangjianOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 订单类型的值
		*/
		private String fangjianOrderValue;



		//级联表 fangjian
			/**
			* 房间名称
			*/
			private String fangjianName;
			/**
			* 房间照片
			*/
			private String fangjianPhoto;
			/**
			* 房间类型
			*/
			private Integer fangjianTypes;
				/**
				* 房间类型的值
				*/
				private String fangjianValue;
			/**
			* 座位
			*/
			private Integer zuoweiNumber;
			/**
			* 点击次数
			*/
			private Integer fangjianClicknum;
			/**
			* 是否上架
			*/
			private Integer shangxiaTypes;
				/**
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer fangjianDelete;
			/**
			* 房间介绍
			*/
			private String fangjianContent;

		//级联表 xuesheng
			/**
			* 学号
			*/
			private String xueshengUuidNumber;
			/**
			* 学生姓名
			*/
			private String xueshengName;
			/**
			* 学生手机号
			*/
			private String xueshengPhone;
			/**
			* 学生身份证号
			*/
			private String xueshengIdNumber;
			/**
			* 学生头像
			*/
			private String xueshengPhoto;
			/**
			* 电子邮箱
			*/
			private String xueshengEmail;

	public FangjianOrderView() {

	}

	public FangjianOrderView(FangjianOrderEntity fangjianOrderEntity) {
		try {
			BeanUtils.copyProperties(this, fangjianOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 订单类型的值
			*/
			public String getFangjianOrderValue() {
				return fangjianOrderValue;
			}
			/**
			* 设置： 订单类型的值
			*/
			public void setFangjianOrderValue(String fangjianOrderValue) {
				this.fangjianOrderValue = fangjianOrderValue;
			}







				//级联表的get和set fangjian
					/**
					* 获取： 房间名称
					*/
					public String getFangjianName() {
						return fangjianName;
					}
					/**
					* 设置： 房间名称
					*/
					public void setFangjianName(String fangjianName) {
						this.fangjianName = fangjianName;
					}
					/**
					* 获取： 房间照片
					*/
					public String getFangjianPhoto() {
						return fangjianPhoto;
					}
					/**
					* 设置： 房间照片
					*/
					public void setFangjianPhoto(String fangjianPhoto) {
						this.fangjianPhoto = fangjianPhoto;
					}
					/**
					* 获取： 房间类型
					*/
					public Integer getFangjianTypes() {
						return fangjianTypes;
					}
					/**
					* 设置： 房间类型
					*/
					public void setFangjianTypes(Integer fangjianTypes) {
						this.fangjianTypes = fangjianTypes;
					}


						/**
						* 获取： 房间类型的值
						*/
						public String getFangjianValue() {
							return fangjianValue;
						}
						/**
						* 设置： 房间类型的值
						*/
						public void setFangjianValue(String fangjianValue) {
							this.fangjianValue = fangjianValue;
						}
					/**
					* 获取： 座位
					*/
					public Integer getZuoweiNumber() {
						return zuoweiNumber;
					}
					/**
					* 设置： 座位
					*/
					public void setZuoweiNumber(Integer zuoweiNumber) {
						this.zuoweiNumber = zuoweiNumber;
					}
					/**
					* 获取： 点击次数
					*/
					public Integer getFangjianClicknum() {
						return fangjianClicknum;
					}
					/**
					* 设置： 点击次数
					*/
					public void setFangjianClicknum(Integer fangjianClicknum) {
						this.fangjianClicknum = fangjianClicknum;
					}
					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getFangjianDelete() {
						return fangjianDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setFangjianDelete(Integer fangjianDelete) {
						this.fangjianDelete = fangjianDelete;
					}
					/**
					* 获取： 房间介绍
					*/
					public String getFangjianContent() {
						return fangjianContent;
					}
					/**
					* 设置： 房间介绍
					*/
					public void setFangjianContent(String fangjianContent) {
						this.fangjianContent = fangjianContent;
					}



















				//级联表的get和set xuesheng
					/**
					* 获取： 学号
					*/
					public String getXueshengUuidNumber() {
						return xueshengUuidNumber;
					}
					/**
					* 设置： 学号
					*/
					public void setXueshengUuidNumber(String xueshengUuidNumber) {
						this.xueshengUuidNumber = xueshengUuidNumber;
					}
					/**
					* 获取： 学生姓名
					*/
					public String getXueshengName() {
						return xueshengName;
					}
					/**
					* 设置： 学生姓名
					*/
					public void setXueshengName(String xueshengName) {
						this.xueshengName = xueshengName;
					}
					/**
					* 获取： 学生手机号
					*/
					public String getXueshengPhone() {
						return xueshengPhone;
					}
					/**
					* 设置： 学生手机号
					*/
					public void setXueshengPhone(String xueshengPhone) {
						this.xueshengPhone = xueshengPhone;
					}
					/**
					* 获取： 学生身份证号
					*/
					public String getXueshengIdNumber() {
						return xueshengIdNumber;
					}
					/**
					* 设置： 学生身份证号
					*/
					public void setXueshengIdNumber(String xueshengIdNumber) {
						this.xueshengIdNumber = xueshengIdNumber;
					}
					/**
					* 获取： 学生头像
					*/
					public String getXueshengPhoto() {
						return xueshengPhoto;
					}
					/**
					* 设置： 学生头像
					*/
					public void setXueshengPhoto(String xueshengPhoto) {
						this.xueshengPhoto = xueshengPhoto;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getXueshengEmail() {
						return xueshengEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setXueshengEmail(String xueshengEmail) {
						this.xueshengEmail = xueshengEmail;
					}



}
