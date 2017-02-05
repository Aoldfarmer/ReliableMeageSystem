package com.zjp.consumer.core;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.lang.reflect.Method;
import java.util.Map;
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
 * Module Desc:com.zjp.consumer.config
 * User: zjprevenge
 * Date: 2017/1/22
 * Time: 21:17
 */

public class MessageMethodHandle implements MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageMethodHandle.class);

    //@Consumerע��Ķ���
    private Object bean;

    //@Listenerע��ķ���
    private Method method;

    //broker����session
    private Session session;

    //��ϢͶ�ݵ�ַ
    private String destination;

    //������Ϣ���̳߳�
    private Executor executor;

    //�Ƿ�֧������
    private boolean transaction;

    //�Ƿ���n2�������Ϣ
    private boolean n2;

    //��Ϣ�ݵȴ���
    private RepeatMessageHandle repeatMessageHandle;

    public MessageMethodHandle() {
    }

    public RepeatMessageHandle getRepeatMessageHandle() {
        return repeatMessageHandle;
    }

    public MessageMethodHandle setRepeatMessageHandle(RepeatMessageHandle repeatMessageHandle) {
        this.repeatMessageHandle = repeatMessageHandle;
        return this;
    }

    public boolean isTransaction() {
        return transaction;
    }

    public MessageMethodHandle setTransaction(boolean transaction) {
        this.transaction = transaction;
        return this;
    }

    public Session getSession() {
        return session;
    }

    public MessageMethodHandle setSession(Session session) {
        this.session = session;
        return this;
    }

    public Object getBean() {
        return bean;
    }

    public MessageMethodHandle setBean(Object bean) {
        this.bean = bean;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public MessageMethodHandle setMethod(Method method) {
        this.method = method;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public MessageMethodHandle setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public Executor getExecutor() {
        return executor;
    }

    public MessageMethodHandle setExecutor(Executor executor) {
        this.executor = executor;
        return this;
    }

    public boolean isN2() {
        return n2;
    }

    public MessageMethodHandle setN2(boolean n2) {
        this.n2 = n2;
        return this;
    }

    /**
     * ������Ϣ
     *
     * @param event ��Ϣ
     * @throws Exception
     */
    public void invokeMethod(Event event) throws Exception {
        method.invoke(bean, event);
    }

    /**
     * ������Ϣ����
     *
     * @param message broker�е���Ϣ
     */
    public void onMessage(final Message message) {
        final MapMessage mapMessage = (MapMessage) message;
        try {
            final String messageId = mapMessage.getString("messageId");
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        if (messageId != null) {
                            final Map<String, Object> map = Maps.newHashMap();
                            map.put("data", mapMessage.getString("data"));
                            map.put("messageId", mapMessage.getString("messageId"));
                            map.put("timeStamp", mapMessage.getString("timeStamp"));
                            //�Ƿ���n2����
                            if (isN2()) {
                                map.put("topic", mapMessage.getString("topic"));
                                map.put("businessMark", mapMessage.getString("businessMark"));
                            }
                            repeatMessageHandle.repeatHandle(map, MessageMethodHandle.this, isN2());
                            //�Ƿ�������
                            if (transaction) {
                                session.commit();
                            } else {
                                message.acknowledge();
                            }
                        }
                    } catch (Exception e) {
                        log.error("handle message error:{}", e);
                    }
                }
            });

        } catch (Exception e) {
            log.error("handle message error: {}", e);
        }
    }
}
