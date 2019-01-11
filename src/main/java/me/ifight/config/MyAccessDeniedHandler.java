package me.ifight.config;

import com.alibaba.fastjson.JSON;
import me.ifight.model.common.RestResponse;
import me.ifight.model.common.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version V1.0.0
 * @Description 无权限访问时触发
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/8/18 9:34
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //返回json形式的错误信息
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setHeader("content-type", "text/html;charset=UTF-8");

        httpServletResponse.getWriter().println(JSON.toJSONString(RestResponse.fail(ResultCode.ACCESS_DENIED)));
        httpServletResponse.getWriter().flush();
    }
}
