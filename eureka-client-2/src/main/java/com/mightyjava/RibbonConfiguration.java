package com.mightyjava;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;


public class RibbonConfiguration {

    Logger logger = LoggerFactory.getLogger(RibbonConfiguration.class);

    @Autowired
    private IClientConfig iClientConfig;

    @Bean
    public IPing ribbonPing() {

        String pingPath = "/actuator/health";
        IPing ping = new PingUrl(false, pingPath);
        logger.info("Configuring ping URI to [{}]", pingPath);

        return ping;
    }

    @Bean
    public IRule ribbonRule() {
        return new RoundRobinRule();
    }

    @Bean
    public ServerList<Server> serverList() {

        return new ServerList<Server>() {

            @Override
            public List<Server> getUpdatedListOfServers() {
                List<Server> serverList = Arrays.asList(new Server("http", "localhost", 8003), new Server("http", "localhost", 8004));
                logger.info("Returning updated list of servers [{}]", serverList);
                return serverList;
            }

            @Override
            public List<Server> getInitialListOfServers() {
                return Arrays.asList(new Server("http", "localhost", 8003), new Server("http", "localhost", 8004));
            }
        };
    }

}
