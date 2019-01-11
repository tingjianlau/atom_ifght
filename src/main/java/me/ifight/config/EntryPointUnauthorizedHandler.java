package me.ifight.config;

import com.alibaba.fastjson.JSON;
import me.ifight.model.common.RestResponse;
import me.ifight.model.common.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

  /**
   * 未登录或token过期或无效时触发的操作
   * @param httpServletRequest
   * @param httpServletResponse
   * @param e
   * @throws IOException
   * @throws ServletException
   */
  @Override
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
      //返回json形式的错误信息
      httpServletResponse.setCharacterEncoding("UTF-8");
      httpServletResponse.setContentType("application/json");
      //通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
      httpServletResponse.setHeader("content-type", "text/html;charset=UTF-8");

      httpServletResponse.getWriter().println(JSON.toJSONString(RestResponse.fail(ResultCode.TOKEN_INVALID)));
      httpServletResponse.getWriter().flush();
  }

}
