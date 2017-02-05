package com.zjp.consumer.spring;

import com.zjp.consumer.config.ConsumerConfig;
import com.zjp.consumer.core.RepeatMessageHandle;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * ������������oooo������������
 * ��������������������
 * ���������ߩ��������ߩ�
 * ����������������������
 * ����������������������
 * ���������ש������ס���
 * ����������������������
 * �������������ߡ�������
 * ����������������������
 * ����������������������
 * ������������������stay hungry stay foolish
 * ������������������Code is far away from bug with the animal protecting
 * ��������������������������
 * �������������������������ǩ�
 * ����������������������������
 * �������������������ש�����
 * �������������ϩϡ����ϩ�
 * �������������ߩ������ߩ�
 * �����������������թ�����������
 * Module Desc:com.zjp.consumer.spring
 * User: zjprevenge
 * Date: 2017/1/22
 * Time: 15:17
 */
@Configuration
@ComponentScan("com.zjp.consumer")
public class MessageConsumerConfiguration {

    @Resource
    private ConsumerConfig consumerConfig;

    /**
     * ���������߽�����
     *
     * @return
     */
    @Bean
    public MessageConsumerBeanPostProcessor messageConsumerBeanPostProcessor() {
        return new MessageConsumerBeanPostProcessor();
    }

    /**
     * ����ActiveMQ���ӹ���
     *
     * @return
     */
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        return new ActiveMQConnectionFactory(consumerConfig.getBrokerUrl(),
                consumerConfig.getUserName(), consumerConfig.getPassword());
    }

    /**
     * �����̳߳�
     *
     * @return
     */
    @Bean
    public Executor executor() {
        return Executors.newFixedThreadPool(consumerConfig.getPoolSize());
    }

    /**
     * ������Ϣ�ݵȴ���
     *
     * @return
     */
    @Bean
    public RepeatMessageHandle repeatMessageHandle() {
        return new RepeatMessageHandle();
    }
}
