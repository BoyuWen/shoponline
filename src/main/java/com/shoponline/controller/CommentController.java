package com.shoponline.controller;

import com.alibaba.fastjson.JSONArray;
import com.shoponline.domain.Comment;
import com.shoponline.service.CommentService;
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
public class CommentController {
    @Resource
    private CommentService commentService;

    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/addShopComment",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addShopComment(int userId, int productId, String content){
        String result;
        if(orderService.getUserProductOrder(userId,productId)){
            Comment comment = new Comment();
            comment.setUserId(userId);
            comment.setProductId(productId);
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            comment.setTime(sf.format(date));
            comment.setContent(content);
            commentService.addComment(comment);
            result = "success";
        }
        else{
            result="noneOrder";
        }

        Map<String,Object> resultMap = new HashMap();
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping(value = "/getComments",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getComments(int productId){
        List<Comment> commentList = commentService.getComments(productId);
        String comments = JSONArray.toJSONString(commentList);
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("result",comments);
        return resultMap;
    }
}
