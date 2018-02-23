package is.hi.teymi9.gefins.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 *
 * @author Einar
 * @date February 2018
 *
 * Main fall sem keyrir upp Spring forritið.
  */

@SpringBootApplication
public class Application extends SpringBootServletInitializer{

    /**
     *
     * @param applicationBuilder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
