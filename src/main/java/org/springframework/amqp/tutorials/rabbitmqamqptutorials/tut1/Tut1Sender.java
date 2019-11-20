package org.springframework.amqp.tutorials.rabbitmqamqptutorials.tut1;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut1Sender {

  @Autowired
  private RabbitTemplate template;

  @Autowired
  private Queue queue;

  @Scheduled(fixedDelay = 5000, initialDelay = 500)
  public void send() {
    System.out.println("SEND HERE");
    System.out.println("SEND HERE");
    File file = new File("/Users/nexusninja/Documents/amCharts.png");
    try {
      BufferedImage bufferedImage = ImageIO.read(file);
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      ImageIO.write(bufferedImage, "png", output);
      byte[] data = output.toByteArray();
      System.out.println("BYTE DATA HERE");
      System.out.println(data.length);

      String base64 = Base64.encode(data);
      System.out.println("BASE 64 here");
      System.out.println(base64);

      URLConnection connection = file.toURL().openConnection();
      String mimeType = connection.getContentType();
      Message message = MessageBuilder.withBody(data).setHeader("Content-Type", mimeType).build();
      this.template.convertAndSend(queue.getName(), message);
      System.out.println("DONE SENDING SMALL MESSAGE HERE");
    } catch (IOException e) {
      e.printStackTrace();
    }

//    String message = "Hello world from spring";
//    this.template.convertAndSend(queue.getName(), message);
//    System.out.println(" [x] Sent '" + message + "'");
  }
}
