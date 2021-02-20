package com.jietang.smart.service;

import com.jietang.smart.dao.TempDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: jietang
 * @create: 2021/2/20-3:29 下午
 **/
@Service
public class TempService {
    @Resource
    private TempDao tempDao;

    public String test() {
        Map<String, String> test = tempDao.test();
        return "success";
    }
}
