package me.ifight.service.itf;

import me.ifight.model.weather.WeatherVO;

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
    WeatherVO getDataByCityId(String cityId);

    /**
     * 根据城市名称查询天气数据
     *
     * @param cityName
     * @return
     */
    WeatherVO getDataByCityName(String cityName);
}
