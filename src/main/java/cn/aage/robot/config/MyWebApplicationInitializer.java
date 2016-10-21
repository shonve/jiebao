package cn.aage.robot.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by eric on 2014/9/17.
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MvcConfig.class);
        context.register(DataConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.addFilter("HttpMethodFilter", HiddenHttpMethodFilter.class).addMappingForUrlPatterns(null, false, "/*");
//        servletContext.addListener(Test.class);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        //配置UTF-8
        FilterRegistration filterRegistration = servletContext.addFilter("characterEncodingFilter", CharacterEncodingFilter.class);
        filterRegistration.setInitParameter("encoding", "UTF-8");
        filterRegistration.setInitParameter("forceEncoding", "true");
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

//        DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy("shiroFilter", context);
//        shiroFilter.setTargetFilterLifecycle(true);
//        servletContext.addFilter("shiroFilter", shiroFilter).addMappingForUrlPatterns(null, false, "/*");

        /**
         * 启用druid的URI监控、Session监控
         */
        FilterRegistration druidWebStatFilter = servletContext.addFilter("druidWebStatFilter", WebStatFilter.class);
        druidWebStatFilter.setInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        druidWebStatFilter.setInitParameter("profileEnable", "true");
        druidWebStatFilter.setInitParameter("principalSessionName", "realname");
        druidWebStatFilter.addMappingForUrlPatterns(null, false, "/*");

        /**
         * 启用druid servlet进行sql监控
         */
        ServletRegistration.Dynamic druidServlet = servletContext.addServlet("druidServlet", new StatViewServlet());
        druidServlet.setLoadOnStartup(0);
        druidServlet.addMapping("/druid/*");
//


//        DispatcherServlet dispatcherServlet = new DispatcherServlet();
//        ServletRegistration.Dynamic dynamic = servletContext.addServlet(
//                "dispatcherServlet", dispatcherServlet);
//        dynamic.setLoadOnStartup(1);
//        dynamic.addMapping("/*");


        //dispatcher.setMultipartConfig(new MultipartConfigElement("c:/temp", 1024*1024*5, 1024*1024*5*5, 1024*1024));
    }
}
