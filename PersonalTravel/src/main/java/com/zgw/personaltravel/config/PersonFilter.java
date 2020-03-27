package com.zgw.personaltravel.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zgw.personaltravel.entity.Person;
import com.zgw.personaltravel.entity.TravelDynamic;
import com.zgw.personaltravel.entity.Travellog;
import org.springframework.stereotype.Component;
@Component
@WebFilter(urlPatterns = "/*")
public class PersonFilter implements Filter{
    //排除的URI
	private final String[] IGNORE_URI= {"/css/"
			                           ,"/js/"
									   ,"/img/"
			                           ,"/overt/"
									   ,"/account/register"
			                           ,"/travel/dynamic"
									   ,"/travel/detail"
			                           ,"/errorPage"
										,"/api/v1/travel/publicData"
										,"/api/v1/comment/load"
			                            ,"/api/v1/account/login"
										,"/person/project"
										,"/api/v1/travel/detail"
	                                   };

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		String requestURI = req.getRequestURI();
		//当前uri是不是在ignore里，是放行
		System.out.println("requestURI:"+requestURI);
		boolean pass =canPassURI(requestURI);
		if(pass) {
			chain.doFilter(request, response);
			return;
		}

		//在session找Account对象
		Person person = (Person)req.getSession().getAttribute("person");
		//不在跳转登录界面
		  if(null==person) {
			  //跳转登陆页面
			  res.sendRedirect("/");
			  return;
		  }
		  if(!canVisit(person,requestURI,req)) {
              res.sendRedirect("/errorPage");
			 // req.getRequestDispatcher("/errorPage").forward(req, res);
			  return;
		  }
		  //找到放行
		  
		chain.doFilter(request, response);	
	}

	private boolean canVisit(Person person, String requestURI,HttpServletRequest req) {
		// TODO Auto-generated method stub



		if (requestURI.startsWith("/private/project")){
			if (!person.getId().toString().equals(requestURI.substring(17,18))){
				System.out.println("创建者编号为"+requestURI.substring(17,18)+"该私密日志不是由"+person.getNickname()+"创建，不能访问");
				return false;
			}
		}
	    else if (requestURI.startsWith("/travel/edit")||requestURI.startsWith("/api/v1/travel/editSubmit")){
			TravelDynamic travelDetail = (TravelDynamic)req.getSession().getAttribute("travelDetail");
			if (travelDetail!=null){
				if (travelDetail.getTravellog().getCreateid()!=person.getId()){
					return false;
				}
			}else{
				return false;
			}
			System.out.println("日志编辑安全");
		}
		return true;
	}

	private boolean canPassURI(String requestURI) {
		// TODO Auto-generated method stub


		for(String uri:IGNORE_URI) {
			if (requestURI.equals("/")) return true;
			if(requestURI.startsWith(uri)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig  filterConfig) throws ServletException {
		
		//加载filter启动之前需要的资源
		System.out.println("AccountFilter init");
		Filter.super.init(filterConfig);
	}
	
}
