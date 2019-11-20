package org.springframework.amqp.tutorials.rabbitmqamqptutorials.tut1;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello")
public class Tut1Receiver {

  @RabbitHandler()
  public void receive(Message message) {
    System.out.println("MESSAGE RECEIVED HERE ");
    System.out.println(message.toString());
    System.out.println(message.getBody().length);
    System.out.println(message.getBody());
  }
}
