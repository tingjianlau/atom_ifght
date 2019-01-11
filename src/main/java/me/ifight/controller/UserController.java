package me.ifight.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.ifight.model.LoginDetail;
import me.ifight.model.RequestLoginUser;
import me.ifight.model.UserVO;
import me.ifight.model.common.RestResponse;
import me.ifight.model.common.ResultCode;
import me.ifight.service.impl.LoginServiceImpl;
import me.ifight.service.impl.UserServiceImpl;
import me.ifight.service.itf.ITokenDetail;
import me.ifight.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/user/")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LoginServiceImpl loginService;

    @GetMapping("queryUserById")
    @ApiOperation("根据ID查找用户信息")
    @ApiImplicitParam(name = "id", value = "用户主键id", required = true, dataType = "int")
    public RestResponse queryUserById(int id){
        logger.info("#queryUserId, id:" + id);

        UserVO userVO = userService.queryUserById(id);

        if(userVO != null){
            return RestResponse.succuess(userVO);
        }else{
            return RestResponse.fail("Not Found the ID");
        }
    }

    @PostMapping("addUser")
    @ApiOperation("添加用户")
    //@ApiImplicitParam(name = "userVO", value = "用户名称", required = true )
    public RestResponse addUser(String userName, String pwd){
        logger.info("#addUser userName: " + userName + ", pwd: " + pwd);
        UserVO userVO = new UserVO();
        userVO.setUserName(userName);
        userVO.setPwd(pwd);
        boolean isSuccess = userService.addUser(userVO);

        return isSuccess ? RestResponse.succuess() : RestResponse.fail("Insert Fail");
    }

    /*
    @PostMapping("login")
    @ApiOperation("登录")
    public RestResponse login(@RequestBody Map<String, Object> map){
        logger.info("#login, requestMap={}", map.toString());
        UserVO userVO = new UserVO();
        userVO.setToken("123456");

        return RestResponse.succuess(userVO);
    }
    */

    @PostMapping("login")
    @ApiOperation("登录")
    public RestResponse login(@RequestBody @Valid RequestLoginUser requestLoginUser, BindingResult bindindResult){
        logger.info("username={}, password={}", requestLoginUser.getUsername(), requestLoginUser.getPassword());
        if (bindindResult.hasErrors()){
            return RestResponse.fail("缺少参数或者参数格式不对");
        }

        LoginDetail loginDetail = loginService.getLoginDetail(requestLoginUser.getUsername());

        RestResponse restResponse = checkAccount(requestLoginUser, loginDetail);

        if (restResponse.getCode() == ResultCode.SUCCESS.code()){
            Map<String, Object> map = new HashMap<>();
            map.put(this.tokenHeader, loginService.generateToken((ITokenDetail) loginDetail));
            map.put("username", loginDetail.getUsername());
            restResponse.setData(map);
        }

        return restResponse;
    }

    private RestResponse checkAccount(RequestLoginUser requestLoginUser, LoginDetail loginDetail){
        if (loginDetail == null){
            return RestResponse.fail(403, "账号不存在");
        }else {
            if (loginDetail.enable() == false){
                return RestResponse.fail(403, "账号在黑名单");
            }
            if (!UserUtils.match(requestLoginUser.getPassword(), loginDetail.getPassword())){
                //if (!loginDetail.getPassword().equals(requestLoginUser.getPassword())){
                return RestResponse.fail(438, "密码错误");
            }
        }

        return RestResponse.succuess();
    }

    @GetMapping("info")
    @ApiOperation("根据token查询用户信息")
    public RestResponse info(@RequestParam("token") String token){
       UserVO userVO = new UserVO();
       userVO.setToken(token);
       userVO.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
       userVO.setUserName("tjliu");
       userVO.setRoles(Arrays.asList("admin"));

       return RestResponse.succuess(userVO);
    }

    @PostMapping("logout")
    @ApiOperation("登出")
    public RestResponse logout(){
        return RestResponse.succuess();
    }
}
