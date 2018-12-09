package me.ifight.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.ifight.model.common.RestResponse;
import me.ifight.service.itf.WeatherDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "天气预报服务")
@RestController
@RequestMapping("/tjliu/weather")
public class WeatherController {
    private static Logger log= LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping("/cityId/{cityId}")
    @ApiOperation("通过城市ID查询天气")
    @ApiImplicitParam(name = "cityId", value = "城市id", required = true, dataType = "String")
    public RestResponse getReportByCityId(@PathVariable("cityId") String cityId) {
        return RestResponse.succuess(weatherDataService.getDataByCityId(cityId));
    }

    @GetMapping("/cityName/{cityName}")
    @ApiOperation("通过城市名称查询天气")
    @ApiImplicitParam(name = "cityName", value = "城市名称", required = true, dataType = "String")
    public RestResponse getReportByCityName(@PathVariable("cityName") String cityName) {
        log.info("#getReportByCityName, cityName:" + cityName);

        return RestResponse.succuess(weatherDataService.getDataByCityName(cityName));
    }
}
