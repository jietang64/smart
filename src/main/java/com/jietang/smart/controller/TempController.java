package com.jietang.smart.controller;

import com.jietang.smart.entity.common.JsonResult;
import com.jietang.smart.service.TempService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jietang
 * @create: 2021/2/20-3:26 下午
 **/
@Controller
@RequestMapping("/tmp")
public class TempController {
    @Autowired
    private TempService service;

    @GetMapping("/test")
    @ResponseBody
    @ApiOperation(value = "测试方法")
    public JsonResult test() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return new JsonResult();
    }
}
