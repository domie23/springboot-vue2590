
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 房间占座
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fangjianOrder")
public class FangjianOrderController {
    private static final Logger logger = LoggerFactory.getLogger(FangjianOrderController.class);

    @Autowired
    private FangjianOrderService fangjianOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private FangjianService fangjianService;
    @Autowired
    private XueshengService xueshengService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("xueshengId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = fangjianOrderService.queryPage(params);

        //字典表数据转换
        List<FangjianOrderView> list =(List<FangjianOrderView>)page.getList();
        for(FangjianOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FangjianOrderEntity fangjianOrder = fangjianOrderService.selectById(id);
        if(fangjianOrder !=null){
            //entity转view
            FangjianOrderView view = new FangjianOrderView();
            BeanUtils.copyProperties( fangjianOrder , view );//把实体数据重构到view中

                //级联表
                FangjianEntity fangjian = fangjianService.selectById(fangjianOrder.getFangjianId());
                if(fangjian != null){
                    BeanUtils.copyProperties( fangjian , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setFangjianId(fangjian.getId());
                }
                //级联表
                XueshengEntity xuesheng = xueshengService.selectById(fangjianOrder.getXueshengId());
                if(xuesheng != null){
                    BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXueshengId(xuesheng.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody FangjianOrderEntity fangjianOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fangjianOrder:{}",this.getClass().getName(),fangjianOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            fangjianOrder.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        fangjianOrder.setInsertTime(new Date());
        fangjianOrder.setCreateTime(new Date());
        fangjianOrderService.insert(fangjianOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FangjianOrderEntity fangjianOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,fangjianOrder:{}",this.getClass().getName(),fangjianOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            fangjianOrder.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<FangjianOrderEntity> queryWrapper = new EntityWrapper<FangjianOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangjianOrderEntity fangjianOrderEntity = fangjianOrderService.selectOne(queryWrapper);
        if(fangjianOrderEntity==null){
            fangjianOrderService.updateById(fangjianOrder);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        fangjianOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<FangjianOrderEntity> fangjianOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            FangjianOrderEntity fangjianOrderEntity = new FangjianOrderEntity();
//                            fangjianOrderEntity.setFangjianOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            fangjianOrderEntity.setFangjianId(Integer.valueOf(data.get(0)));   //房间 要改的
//                            fangjianOrderEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            fangjianOrderEntity.setFangjianOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            fangjianOrderEntity.setBuyZuoweiNumber(data.get(0));                    //占的座位 要改的
//                            fangjianOrderEntity.setBuyZuoweiTime(new Date(data.get(0)));          //占座日期 要改的
//                            fangjianOrderEntity.setInsertTime(date);//时间
//                            fangjianOrderEntity.setCreateTime(date);//时间
                            fangjianOrderList.add(fangjianOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("fangjianOrderUuidNumber")){
                                    List<String> fangjianOrderUuidNumber = seachFields.get("fangjianOrderUuidNumber");
                                    fangjianOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> fangjianOrderUuidNumber = new ArrayList<>();
                                    fangjianOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("fangjianOrderUuidNumber",fangjianOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<FangjianOrderEntity> fangjianOrderEntities_fangjianOrderUuidNumber = fangjianOrderService.selectList(new EntityWrapper<FangjianOrderEntity>().in("fangjian_order_uuid_number", seachFields.get("fangjianOrderUuidNumber")));
                        if(fangjianOrderEntities_fangjianOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FangjianOrderEntity s:fangjianOrderEntities_fangjianOrderUuidNumber){
                                repeatFields.add(s.getFangjianOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        fangjianOrderService.insertBatch(fangjianOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = fangjianOrderService.queryPage(params);

        //字典表数据转换
        List<FangjianOrderView> list =(List<FangjianOrderView>)page.getList();
        for(FangjianOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FangjianOrderEntity fangjianOrder = fangjianOrderService.selectById(id);
            if(fangjianOrder !=null){


                //entity转view
                FangjianOrderView view = new FangjianOrderView();
                BeanUtils.copyProperties( fangjianOrder , view );//把实体数据重构到view中

                //级联表
                    FangjianEntity fangjian = fangjianService.selectById(fangjianOrder.getFangjianId());
                if(fangjian != null){
                    BeanUtils.copyProperties( fangjian , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setFangjianId(fangjian.getId());
                }
                //级联表
                    XueshengEntity xuesheng = xueshengService.selectById(fangjianOrder.getXueshengId());
                if(xuesheng != null){
                    BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXueshengId(xuesheng.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody FangjianOrderEntity fangjianOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,fangjianOrder:{}",this.getClass().getName(),fangjianOrder.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("学生".equals(role)){
            FangjianEntity fangjianEntity = fangjianService.selectById(fangjianOrder.getFangjianId());
            if(fangjianEntity == null){
                return R.error(511,"查不到该房间");
            }

            List<String> buyZuoweiNumberList = new ArrayList<>(Arrays.asList(fangjianOrder.getBuyZuoweiNumber().split(",")));//这次购买的座位
            List<String> beforeBuyZuoweiNumberList = new ArrayList<>();//之前已经购买的座位


            if(buyZuoweiNumberList.size()<=0)
                return R.error("必须要选择座位哦");
            else if(buyZuoweiNumberList.size()>1)
                return R.error("只能预约一个座位");


            FangjianOrderEntity fangjianOrderEntity = fangjianOrderService.selectOne(new EntityWrapper<FangjianOrderEntity>()
                    .eq("fangjian_id", fangjianOrder.getFangjianId())
                    .eq("xuesheng_id", fangjianOrder.getXueshengId())
                    .in("fangjian_order_types", Arrays.asList(new Integer[]{1, 2}))
                    .eq("buy_zuowei_time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
            );//查询当天该房间该用户是否有已经在占的座位

            if(fangjianOrderEntity != null){
                return R.error("当前该学生已经有在此房间预约或者签到的座位,无法预约第二个座位");
            }

            List<FangjianOrderEntity> fangjianOrderEntityList = fangjianOrderService.selectList(new EntityWrapper<FangjianOrderEntity>()
                .eq("fangjian_id", fangjianOrder.getFangjianId()).notIn("fangjian_order_types", Arrays.asList(new Integer[]{3, 4}))
                .eq("buy_zuowei_time",new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
            );//查询当天该房间的占位情况


            for(FangjianOrderEntity d:fangjianOrderEntityList){
                beforeBuyZuoweiNumberList.addAll(Arrays.asList(d.getBuyZuoweiNumber().split(",")));
            }

            buyZuoweiNumberList.retainAll(beforeBuyZuoweiNumberList);//判断当前购买list包含已经被购买的list中是否有重复的  有的话 就保留
            if(buyZuoweiNumberList != null && buyZuoweiNumberList.size()>0 ){
                return R.error(511,buyZuoweiNumberList.toString()+" 的座位已经被使用");
            }


            //计算所获得积分
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            fangjianOrder.setXueshengId(userId); //设置订单支付人id
            fangjianOrder.setFangjianOrderTypes(1);
            fangjianOrder.setInsertTime(new Date());
            fangjianOrder.setCreateTime(new Date());
                fangjianOrderService.insert(fangjianOrder);//新增订单
            return R.ok();
        }else{
            return R.error(511,"您没有权限支付订单");
        }
    }




    /**
     * 签到
     */
    @RequestMapping("/qiandao")
    public R qiandao(Integer id, HttpServletRequest request){
        logger.debug("qiandao:,,Controller:{},,ids:{}",this.getClass().getName(),id);
        FangjianOrderEntity fangjianOrderEntity = new FangjianOrderEntity();
        fangjianOrderEntity.setId(id);
        fangjianOrderEntity.setFangjianOrderTypes(2);
        fangjianOrderService.updateById(fangjianOrderEntity);
        return R.ok();
    }


    /**
     * 退出
     */
    @RequestMapping("/tuichu")
    public R tuichu(Integer id, HttpServletRequest request){
        logger.debug("tuichu:,,Controller:{},,ids:{}",this.getClass().getName(),id);
        FangjianOrderEntity fangjianOrderEntity = new FangjianOrderEntity();
        fangjianOrderEntity.setId(id);
        fangjianOrderEntity.setFangjianOrderTypes(4);
        fangjianOrderService.updateById(fangjianOrderEntity);
        return R.ok();
    }























}
