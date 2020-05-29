package com.shine.dao;

import java.util.List;

import com.shine.model.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 城市DAO
 *
 * @author wb-cb236432
 * @create 2018-06-22 14:41
 **/
@Mapper
public interface CityDao {
    /**
     * 获取城市信息列表
     *
     * @return
     */
    List<City> findAllCity();

    /**
     * 根据城市 ID，获取城市信息
     *
     * @param id
     * @return
     */
    City findById(@Param("id") Long id);

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);
}
