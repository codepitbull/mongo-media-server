package de.codepitbull.mongodb.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author Jochen Mader
 */
public class MediaServerInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MainConfiguration.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("rest", new DispatcherServlet(ctx));
        dispatcher.addMapping("/rest/*");
    }
}
