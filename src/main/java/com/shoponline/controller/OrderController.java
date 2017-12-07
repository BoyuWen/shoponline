package com.shoponline.controller;

import com.alibaba.fastjson.JSONArray;
import com.shoponline.domain.Product;
import com.shoponline.domain.Order;
import com.shoponline.service.ProductService;
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
public class OrderController {
    @Resource
    private ProductService productService;
    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/record")
    public String record(){
        return "record";
    }

    @RequestMapping(value = "/handle")
    public String handle(){
        return "handle";
    }

    @RequestMapping(value = "/addOrder",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOrder(int userId,int productId,int counts){
        System.out.println("我添加了"+userId+" "+productId);
        String result;
        Product product = productService.getProduct(productId);
        if(counts<=product.getCounts()) {
            Order order = new Order();
            order.setUserId(userId);
            order.setProductId(productId);
            order.setProductPrice(product.getPrice() * counts);
            order.setCounts(counts);
            order.setOrderStatus(0);
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            order.setTime(sf.format(date));
            product.setCounts(product.getCounts()-counts);
            productService.updateProduct(product);
            orderService.addOrder(order);
            result = "success";
        }
        else{
            result = "unEnough";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/changeOrder",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> changeOrder(int userId,int productId,String time,int orderStatus){
        System.out.println("我接收了"+userId+" "+productId+" "+time+" "+orderStatus);
        Order order = orderService.getOrder(userId,productId,time);
        System.out.println("我获取到了了"+ order.getTime());
        order.setOrderStatus(orderStatus);
        orderService.updateOrder(order);

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        System.out.println("我成功fanhui了");
        return resultMap;
    }

    @RequestMapping(value = "/getOrders",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getOrders(int userId){
        List<Order> orderList = orderService.getOrders(userId);
        String shoponlineOrders = JSONArray.toJSONString(orderList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoponlineOrders);
        return resultMap;
    }

    @RequestMapping(value = "/getOrdersByOrderStatus",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getOrdersByOrderStatus(int orderStatus){
        List<Order> orderList = orderService.getOrdersByOrderStatus(orderStatus);
        String shoponlineOrders = JSONArray.toJSONString(orderList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoponlineOrders);
        return resultMap;
    }

    @RequestMapping(value = "/getAllOrders",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllOrders(){
//        System.out.println("wo在这里i");
        List<Order> orderList = orderService.getAllOrders();
        String shoponlineOrders = JSONArray.toJSONString(orderList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",shoponlineOrders);
//        System.out.println("我反悔了"+shoponlineOrders);
        return resultMap;
    }

    @RequestMapping(value = "/getUserProductOrder",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserProductOrder(int userId,int productId){
        String result = "false";
        if(orderService.getUserProductOrder(userId,productId)){
            result = "true";
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
}
