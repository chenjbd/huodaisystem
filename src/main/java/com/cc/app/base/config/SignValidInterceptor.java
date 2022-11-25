package com.cc.app.base.config;

import com.cc.app.base.common.Constant;
import com.cc.app.base.common.SignValidIgnore;
import com.cc.app.base.ex.ServiceExcepiton;
import com.cc.app.base.model.LoginUser;
import com.cc.app.base.utils.JwtConfig;
import com.cc.app.base.utils.JwtHelper;
import com.cc.app.base.utils.SpringUtil;
import com.cc.app.pms.service.AccountInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * 验证登录，签名、权限拦截器，需要在WebMvcConfigurerAdapter注册
 *
 */
public class SignValidInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(SignValidInterceptor.class);

	private boolean IS_DEBUG = false;//是否启用调试模式，不鉴权

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requesturl = request.getRequestURI();
		String requestmethod = request.getMethod();
//		logger.debug("拦截请求：{}，方式：{}", requesturl, requestmethod);

		//匹配不到controller请求，会当做对静态资源请求，放行
		if(handler instanceof ResourceHttpRequestHandler){
			return true;
		}

		//error、swagger、验证码，放行
		if(requesturl.contains("/error") ||
				requesturl.contains("swagger") ||
				requesturl.contains("/v2")||
				requesturl.contains("/imgCode")){
			return true;
		}

		//是否忽略签名验证
		HandlerMethod method = (HandlerMethod) handler;
		SignValidIgnore signValidIgnore = method.getMethodAnnotation(SignValidIgnore.class);

		if (signValidIgnore != null) {
			return true;
		}

		if(IS_DEBUG){
			return IS_DEBUG;
		}

		//进行验签
		if (signValidIgnore == null) {
			boolean isOk = true;//验签是否通过
			String token = request.getHeader("X-Token");//token
			token = token == null ? "" : token;
			String rtnCode = "999999";
			String rtnMsg = "验签失败";
			try{
				JwtConfig jwtConfig = SpringUtil.getBean(JwtConfig.class);
				Map<String,Object> jwtMap = JwtHelper.validateJWT(token, jwtConfig.getJwt_secret());
				String loginId = (String) jwtMap.get("sub");
				//查询登录信息
				AccountInfoService accountInfoService = SpringUtil.getBean(AccountInfoService.class);
				LoginUser loginUser = accountInfoService.getLoginUser(loginId);
				if(loginUser == null){
					throw new ServiceExcepiton("100001", "token无效");
				}
				request.setAttribute(Constant.CURRENT_USER, loginUser);
			}catch(ServiceExcepiton e){
				rtnCode = e.getErrCode();
				rtnMsg = e.getErrMsg();
				logger.error(e.getMessage());
				isOk = false;
			}
			if (!isOk) {
				response.setContentType("application/json;charset=UTF-8");
				response.setHeader("Access-Control-Allow-Origin","*");
				PrintWriter out = response.getWriter();
				out.println("{\"rtnCode\":\""+rtnCode+"\",\"rtnMsg\":\""+rtnMsg+"\"}");
				return false;
			}
		}
		return true;
	}

	private boolean urlMath(String url,List<String> list){
		if(list == null){
			return false;
		}
		boolean result = false;
		for(String s : list){
			if(url.contains(s)){
				result = true;
				break;
			}
		}
		return result;
	}

}
