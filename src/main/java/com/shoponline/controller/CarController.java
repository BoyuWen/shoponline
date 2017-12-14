package com.shoponline.controller;

import com.alibaba.fastjson.JSONArray;
import com.shoponline.domain.Car;
import com.shoponline.service.ProductService;
import com.shoponline.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarController {
    @Resource
    private ProductService productService;
    @Resource
    private CarService carService;

    @RequestMapping(value = "/car")
    public String car(HttpSession session){
        if (session.getAttribute("nowUser") == null){
            return "login";
        }
        return "car";
    }

    @RequestMapping(value = "/addCar",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addCar(int userId,int productId,int counts){
        Car car = carService.getCar(userId,productId);
        if(car == null){
            Car car1 = new Car();
            car1.setUserId(userId);
            car1.setProductId(productId);
            car1.setCounts(counts);
            car1.setMoney(productService.getProduct(productId).getPrice()*counts);
            carService.addCar(car1);
        }
        else{
            car.setCounts(car.getCounts()+counts);
            car.setMoney(productService.getProduct(productId).getPrice()* car.getCounts());
            carService.updateCar(car);
        }
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("result","success");
        return resultMap;
    }

    @RequestMapping(value = "/getCars",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getCars(int userId){
        List<Car> carList = carService.getCars(userId);
        String cars = JSONArray.toJSONString(carList);
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("result",cars);
        return resultMap;
    }

    @RequestMapping(value = "/deleteCar",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteCar(int userId,int productId){
        carService.deleteCar(userId,productId);
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("result","success");
        return resultMap;
    }
}
