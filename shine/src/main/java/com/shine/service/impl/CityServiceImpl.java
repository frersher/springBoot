package com.shine.service.impl;

import java.util.List;

import com.shine.dao.CityDao;
import com.shine.model.City;
import com.shine.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实现类
 *
 * @author wb-cb236432
 * @create 2018-06-22 14:38
 **/
@Service
public class CityServiceImpl implements CityService{
    @Autowired
    private CityDao cityDto;

    @Override
    public List<City> findAllCity() {
        return cityDto.findAllCity();
    }

    @Override
    public City findCityById(Long id) {
        return cityDto.findById(id);
    }

    @Override
    public Long saveCity(City city) {
        return cityDto.saveCity(city);
    }

    @Override
    public Long updateCity(City city) {
        return cityDto.updateCity(city);
    }

    @Override
    public Long deleteCity(Long id) {
        return cityDto.deleteCity(id);
    }
}
