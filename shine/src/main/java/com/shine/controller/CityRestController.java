package com.shine.controller;

import com.shine.model.City;
import com.shine.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * 城市管理
 *
 * @author wb-cb236432
 * @create 2018-06-22 11:28
 **/
@RestController
public class CityRestController {
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public City findOneCity(@PathVariable("id") Long id){

         return  cityService.findCityById(id);
    }
}
