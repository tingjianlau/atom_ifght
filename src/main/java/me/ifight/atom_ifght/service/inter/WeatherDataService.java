package me.ifight.atom_ifght.service.inter;

import me.ifight.atom_ifght.model.weather.WeatherResponse;

public interface WeatherDataService {
    /**
     * 天气数据服务.
     */

    /**
     * 根据城市ID查询天气数据
     *
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称查询天气数据
     *
     * @param cityName
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);
}
