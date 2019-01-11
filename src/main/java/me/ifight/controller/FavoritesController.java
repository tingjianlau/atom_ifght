package me.ifight.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.ifight.model.FavoritesBean;
import me.ifight.model.WebSiteInfoVo;
import me.ifight.model.common.PageModel;
import me.ifight.model.common.PageVO;
import me.ifight.model.common.RestResponse;
import me.ifight.model.common.ResultCode;
import me.ifight.service.impl.FavoritesServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
@Api(tags = "收藏夹管理")
public class FavoritesController extends BaseController {
    public static final Logger logger = LoggerFactory.getLogger(FavoritesController.class);

    @Autowired
    FavoritesServiceImpl favoritesService;

    @PostMapping("")
    @ApiOperation("添加收藏")
    public RestResponse addFavorite(@RequestBody FavoritesBean favoritesBean){
        boolean flag = favoritesService.insert(favoritesBean);

        return flag ? RestResponse.succuess() : RestResponse.fail("Fail to add");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除指定id的记录")
    @ApiImplicitParam(name="id", value = "id", required = true, dataType = "int")
    public RestResponse deleteFavorite(@PathVariable("id") int id, HttpServletRequest request){
        String userName = getUserName(request);
        if (StringUtils.isEmpty(userName)){
            return RestResponse.fail(ResultCode.FAIL4DELETE.code(), "username is null in cookie");
        }

        logger.info("#deleteFavorite, id={}, username={}", id, userName);
        boolean flag = favoritesService.delete(id, userName);

        return flag ? RestResponse.succuess() : RestResponse.fail(ResultCode.FAIL4DELETE);
    }

    @GetMapping("/categories")
    @ApiOperation("类型列表")
    public RestResponse getCategories(){
        List<Map> list = new ArrayList<>();
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "java");
        map.put(2, "scala");
        map.put(3, "python");

        return RestResponse.succuess(map);
    }

    @GetMapping("")
    @ApiOperation("收藏夹列表")
    public RestResponse favoritesList(PageModel pageModel, HttpServletRequest request){
        PageVO pageVO = favoritesService.select(getUserName(request), pageModel);

        return CollectionUtils.isEmpty(pageVO.getList()) ? RestResponse.fail("favorite list is empty")
                : RestResponse.succuess(pageVO);
    }

    @GetMapping("/webSiteInfo")
    @ApiOperation("根据网址获取网站信息")
    public RestResponse webSiteInfo(String url){
        WebSiteInfoVo webSiteInfoVo = favoritesService.webSiteInfo(url);

        return RestResponse.succuess(webSiteInfoVo);
    }
}

