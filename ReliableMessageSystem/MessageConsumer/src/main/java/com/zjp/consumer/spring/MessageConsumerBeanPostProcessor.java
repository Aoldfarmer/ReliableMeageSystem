package com.zjp.consumer.spring;

import com.google.common.collect.Lists;
import com.zjp.consumer.annotations.Consumer;
import com.zjp.consumer.annotations.Listener;
import com.zjp.consumer.core.Event;
import com.zjp.consumer.core.MessageMethodHandle;
import com.zjp.consumer.core.RepeatMessageHandle;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executor;


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
 * Time: 15:19
 */

public class MessageConsumerBeanPostProcessor implements BeanPostProcessor, Ordered, BeanFactoryAware, SmartInitializingSingleton {

    private Object primitiveBean;
    //bean����
    private BeanFactory beanFactory;
    //ActiveMQ���ӹ���
    private ActiveMQConnectionFactory activeMQConnectionFactory;
    //�̳߳�
    private Executor executor;
    //��Ϣ�ݵȴ���
    private RepeatMessageHandle repeatMessageHandle;
    //�����߼���
    private List<Object> consumerBeans = Lists.newArrayList();

    public MessageConsumerBeanPostProcessor() {
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        this.primitiveBean = bean;
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //��ȡ��ע@Consumer��ע��
        Consumer consumer = bean.getClass().getAnnotation(Consumer.class);
        //bean��ע��@Consumer
        if (consumer != null) {
            if (AopUtils.isAopProxy(bean)) {
                consumerBeans.add(primitiveBean);
            } else {
                consumerBeans.add(bean);
            }
        }
        return bean;
    }

    /**
     * ע����Ϣ���м�����
     *
     * @param bean
     */
    private void register(Object bean) throws Exception {
        Class<?> clazz = bean.getClass();
        //��ȡ@Listenerע��ķ���
        for (Method method : clazz.getDeclaredMethods()) {
            Class<?>[] types = method.getParameterTypes();
            //����������������Ϊ1�����Ҳ���������ΪEvent������
            if (types.length != 1 || !Event.class.isAssignableFrom(types[0])) {
                continue;
            }
            //��ȡ��Ϣ������
            Listener listener = method.getAnnotation(Listener.class);
            //��������
            Connection connection = activeMQConnectionFactory.createConnection();
            RedeliveryPolicy policy = ((ActiveMQConnection) connection).getRedeliveryPolicy();
            //�������Բ���
            policy.setInitialRedeliveryDelay(1000);
            policy.setBackOffMultiplier(2);
            policy.setUseExponentialBackOff(true);
            policy.setMaximumRedeliveries(2);
            //��������
            connection.start();
            //�����Ự
            Session session = connection.createSession(listener.transaction(), ActiveMQSession.AUTO_ACKNOWLEDGE);
            //��������
            Destination queue = session.createQueue(listener.topic());
            //����������
            MessageConsumer consumer = session.createConsumer(queue);
            //������Ϣ������
            MessageMethodHandle messageMethodHandle = new MessageMethodHandle()
                    .setBean(bean)
                    .setDestination(listener.topic())
                    .setExecutor(executor)
                    .setMethod(method)
                    .setN2(listener.n2())
                    .setSession(session)
                    .setTransaction(listener.transaction())
                    .setRepeatMessageHandle(repeatMessageHandle);
            //������Ϣ������
            consumer.setMessageListener(messageMethodHandle);
        }
    }

    /**
     * ����bean������ɺ�����Ϣ������
     */
    public void afterSingletonsInstantiated() {
        activeMQConnectionFactory = (ActiveMQConnectionFactory) beanFactory.getBean("activeMQConnectionFactory");
        executor = (Executor) beanFactory.getBean("executor");
        repeatMessageHandle = (RepeatMessageHandle) beanFactory.getBean("repeatMessageHandle");
        try {
            for (Object consumerBean : consumerBeans) {
                //���������߼�����
                register(consumerBean);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
