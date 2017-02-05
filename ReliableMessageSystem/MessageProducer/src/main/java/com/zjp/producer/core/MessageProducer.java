package com.zjp.producer.core;

import com.google.common.base.Preconditions;
import com.zjp.producer.domain.QMessage;
import com.zjp.producer.service.QMessageService;
import com.zjp.producer.tx.MessageTransactionSynchronizationAdapter;
import com.zjp.producer.utils.MessageHolder;
import com.zjp.producer.utils.MessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

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
 * Module Desc:com.zjp.consumer.core
 * User: zjprevenge
 * Date: 2017/1/23
 * Time: 19:23
 */
public class MessageProducer implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(MessageProducer.class);

    @Resource
    private TransactionMessageProducer transactionMessageProducer;

    @Resource
    private QMessageService qMessageService;

    //��ϢĿ�ĵ�
    private String destName;
    //�Ƿ�������
    private boolean transaction;
    //�Ƿ�־û���Ϣ
    private boolean persistent;
    //�Ƿ���n2�������Ϣ
    private boolean n2;

    public TransactionMessageProducer getTransactionMessageProducer() {
        return transactionMessageProducer;
    }

    public void setTransactionMessageProducer(TransactionMessageProducer transactionMessageProducer) {
        this.transactionMessageProducer = transactionMessageProducer;
    }

    public QMessageService getqMessageService() {
        return qMessageService;
    }

    public void setqMessageService(QMessageService qMessageService) {
        this.qMessageService = qMessageService;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public boolean isTransaction() {
        return transaction;
    }

    public void setTransaction(boolean transaction) {
        this.transaction = transaction;
    }

    public boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    public boolean isN2() {
        return n2;
    }

    public void setN2(boolean n2) {
        this.n2 = n2;
    }

    /**
     * ������Ϣ
     *
     * @param data
     */
    public void sendMessage(Map<String, String> data) {
        Preconditions.checkArgument(data != null && data.size() != 0, "message must not be empty...");
        transactionSynchronize();
        QMessage message = convertMessage(data);
        int result = qMessageService.addQMessage(message);
        if (result != 0) {
            MessageHolder.set(message.getMessageId());
        }
    }

    /**
     * ��Ϣת��
     *
     * @param data
     * @return
     */
    private QMessage convertMessage(Map<String, String> data) {
        QMessage qMessage = new QMessage();
        //ʱ���
        Date date = new Date();
        //������Ϣid
        String messageId = MessageUtils.createMessageId(date);
        qMessage.setMessageId(messageId);
        qMessage.setMessageContent(data.get("message"));
        qMessage.setDestination(destName);
        qMessage.setTimeStamp(date.getTime());
        qMessage.setN2(n2 ? 1 : 0);
        qMessage.setStatus(0);
        qMessage.setRetry(0);
        qMessage.setPersistent(persistent ? 1 : 0);
        qMessage.setTransaction(transaction ? 1 : 0);
        //�����n2�������Ϣ
        if (n2) {
            //businessMark������Ϊ��
            Preconditions.checkArgument(StringUtils.isNotBlank(data.get("businessMark")),
                    "This queueName:" + destName + "is n2,businessMark must not be empty...");
            qMessage.setBusinessMark(data.get("businessMark"));
        }
        return qMessage;
    }

    /**
     * �������ͬ��
     */
    private void transactionSynchronize() {
        MessageTransactionSynchronizationAdapter synchronizationAdapter = new MessageTransactionSynchronizationAdapter();
        synchronizationAdapter.setqMessageService(qMessageService);
        synchronizationAdapter.setTransactionMessageProducer(transactionMessageProducer);
        TransactionSynchronizationManager.registerSynchronization(synchronizationAdapter);
    }

    public void afterPropertiesSet() throws Exception {
        //��Ϣ�������Ʋ�������ack. or ACK.��ʼ
        if (destName.startsWith("ack.")
                || destName.startsWith("ACK.")) {
            log.error("destName must not start with ack. or ACK.");
            throw new RuntimeException("destName must not start with ack. or ACK.");
        }
    }
}
