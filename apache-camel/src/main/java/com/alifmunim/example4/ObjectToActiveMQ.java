package com.alifmunim.example4;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;
import java.util.Date;

public class ObjectToActiveMQ {

    public static void main(String[] args) throws Exception {

        CamelContext context = new DefaultCamelContext();
        ConnectionFactory connection = new ActiveMQConnectionFactory();

        context.addComponent("jms", (Component) JmsComponent.jmsComponentAutoAcknowledge(connection));

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start").to("activemq:queue:my_queue");
            }
        });

        context.start();

        ProducerTemplate producer = context.createProducerTemplate();
        producer.sendBody("direct:start", new Date());
    }
}
