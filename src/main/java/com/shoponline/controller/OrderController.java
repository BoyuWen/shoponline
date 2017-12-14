package com.shoponline.controller;

import com.alibaba.fastjson.JSONArray;
import com.shoponline.domain.Product;
import com.shoponline.domain.Order;
import com.shoponline.domain.User;
import com.shoponline.service.ProductService;
import com.shoponline.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/order")
    public String order(HttpSession session){
        if (session.getAttribute("nowUser") == null){
            return "login";
        }
        return "order";
    }

    @RequestMapping(value = "/handle")
    public String handle(HttpSession session){
        User nowuser = (User)session.getAttribute("nowUser");
        if (nowuser != null){
            if (nowuser.getRole() == 1){
                return "handle";
            }
        }
        return "main";
    }

    @RequestMapping(value = "/addOrder",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOrder(int userId,int productId,int counts){
        String result;
        Product product = productService.getProduct(productId);
        if(counts<=product.getCounts()) {
            Order order = new Order();
            order.setUserId(userId);
            order.setProductId(productId);
            order.setMoney(product.getPrice() * counts);
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
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/changeOrder",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> changeOrder(int userId,int productId,String time,int orderStatus){
        Order order = orderService.getOrder(userId,productId,time);
        order.setOrderStatus(orderStatus);
        orderService.updateOrder(order);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/getUserOrders",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserOrders(int userId){
        List<Order> orderList = orderService.getUserOrders(userId);
        String userOrders = JSONArray.toJSONString(orderList);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",userOrders);
        return resultMap;
    }

    @RequestMapping(value = "/getAllOrders",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllOrders(){
        List<Order> orderList = orderService.getAllOrders();
        String allOrders = JSONArray.toJSONString(orderList);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",allOrders);
        return resultMap;
    }

    @RequestMapping(value = "/getUserProductOrder",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserProductOrder(int userId,int productId){
        String result = "false";
        if(orderService.getUserProductOrder(userId,productId)){
            result = "true";
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",result);
        return resultMap;
    }
}
