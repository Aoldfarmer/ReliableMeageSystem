�ɿ���Ϣconsumer�ˣ�
ʹ�ò���
1.����consumer��ע��
  @Configuration
  @EnableMessageConsumer
  public class ConsumerConfiguration{

     @Bean
     public ConsumerConfig consumerConfig(){
       return new ConsumerConfig();
     }
  }

2.��дConsumer��
@Consumer
public class ConsumerDemo{

   @Listener(topic="demo.queue",transaction=false,n2=false)
   public void consumeMessage(Event event){
     System.out.println(event.topic());
   }
}