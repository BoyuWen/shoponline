package com.shoponline.controller;

import com.alibaba.fastjson.JSONArray;
import com.shoponline.domain.Price;
import com.shoponline.service.PriceService;
import com.shoponline.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class PriceController {
    @Resource
    private PriceService priceService;

    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/addShoponlinePrice",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addShoponlinePrice(int userId, int productId, String content){
        System.out.println("我添加了"+userId+" "+productId);
        String result = null;
        if(orderService.getUserProductOrder(userId,productId)){
            Price price = new Price();
            price.setUserId(userId);
            price.setProductId(productId);
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            price.setTime(sf.format(date));
            price.setContent(content);
            priceService.addPrice(price);
            result = "success";
        }
        else{
            result="noneOrder";
        }

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/getShoponlinePrices",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getShoponlinePrices(int productId){
        List<Price> priceList = priceService.getProductPrice(productId);
        String prices = JSONArray.toJSONString(priceList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",prices);
        return resultMap;
    }
}
