package com.fangruizhang;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class AppInitializer implements WebApplicationInitializer {
	static String resource = "classpath:config/springconfig.xml";
    @Override
public void onStartup(ServletContext servletContext) throws ServletException {
    	 XmlWebApplicationContext appContext = new XmlWebApplicationContext();
         appContext.setConfigLocation(resource);
             /*add self-defined servlet*/
         ServletRegistration.Dynamic dispatcher =
        		 servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
         dispatcher.setLoadOnStartup(1);
         dispatcher.addMapping("*.action");

    }
}