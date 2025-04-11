package com.dao;

import com.entity.JinggaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JinggaoView;

/**
 * 警告 Dao 接口
 *
 * @author 
 */
public interface JinggaoDao extends BaseMapper<JinggaoEntity> {

   List<JinggaoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
