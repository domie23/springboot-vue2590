package com.thread;

import com.entity.FangjianOrderEntity;
import com.entity.view.FangjianOrderView;
import com.service.FangjianOrderService;
import com.utils.PageUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 线程执行方法（做一些项目启动后 一直要执行的操作，比如根据时间自动更改订单状态，比如订单签收30天自动收货功能，比如根据时间来更改状态）
 */
public class MyThreadMethod extends Thread  {
    private FangjianOrderService fangjianOrderService;
    public void run() {
        while (!this.isInterrupted()) {// 线程未中断执行循环
            try {


                Calendar instance = Calendar.getInstance();
                instance.setTime(new Date());
                instance.add(Calendar.MINUTE,-30);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Map<String, Object> params = new HashMap<>();
                params.put("orderBy","id");
                params.put("page","1");
                params.put("limit","1000");
                params.put("fangjianOrderTypes",1);
                params.put("insertTimeEnd",simpleDateFormat.format(instance.getTime()));
                PageUtils pageUtils = fangjianOrderService.queryPage(params);
                List<FangjianOrderView> list = (List<FangjianOrderView>)pageUtils.getList();
                if(list != null && list.size()>0){
                    List<FangjianOrderEntity> fangjianOrderEntities = new ArrayList<>();
                    for(FangjianOrderEntity f:list){
                        FangjianOrderEntity fangjianOrderEntity = new FangjianOrderEntity();
                        fangjianOrderEntity.setId(f.getId());
                        fangjianOrderEntity.setFangjianOrderTypes(3);
                        fangjianOrderEntities.add(fangjianOrderEntity);
                    }
                    fangjianOrderService.updateBatchById(fangjianOrderEntities);
                    System.out.println("------------更新了"+fangjianOrderEntities.size()+"条数据-------------");
                }
                System.out.println("------------正在更新订单状态-------------");
                Thread.sleep(5000); //每隔2000ms执行一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//			 ------------------ 开始执行 ---------------------------
//            System.out.println("线程执行中:" + System.currentTimeMillis());
        }
    }

    public FangjianOrderService getFangjianOrderService() {
        return fangjianOrderService;
    }

    public void setFangjianOrderService(FangjianOrderService fangjianOrderService) {
        this.fangjianOrderService = fangjianOrderService;
    }
}
