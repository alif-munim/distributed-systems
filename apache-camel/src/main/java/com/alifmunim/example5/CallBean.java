package com.alifmunim.example5;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.SimpleRegistry;

import java.util.Map;

public class CallBean {

    public static void main(String[] args) throws Exception {

        MyService myService = new MyService();
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("myService", (Map<Class<?>, Object>) myService);

        CamelContext context = new DefaultCamelContext(registry);
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start").to("bean:myService?method=doSomething");
            }
        });

        context.start();

        ProducerTemplate producer = context.createProducerTemplate();
        producer.sendBody("direct:start", "hello");

    }
}
