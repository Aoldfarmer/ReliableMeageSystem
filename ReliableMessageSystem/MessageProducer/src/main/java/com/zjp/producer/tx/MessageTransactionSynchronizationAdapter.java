package com.zjp.producer.tx;

import com.zjp.producer.core.TransactionMessageProducer;
import com.zjp.producer.domain.QMessage;
import com.zjp.producer.service.QMessageService;
import com.zjp.producer.utils.MessageHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;

import java.util.List;

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
 * Module Desc:com.zjp.producer.tx
 * User: zjprevenge
 * Date: 2017/1/23
 * Time: 21:01
 */

public class MessageTransactionSynchronizationAdapter extends TransactionSynchronizationAdapter {

    private static final Logger log = LoggerFactory.getLogger(MessageTransactionSynchronizationAdapter.class);

    private TransactionMessageProducer transactionMessageProducer;

    private QMessageService qMessageService;

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

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

    @Override
    public void afterCompletion(int status) {
        try {
            if (STATUS_COMMITTED == status) {
                List<String> messageIds = MessageHolder.get();
                sendMessageToBroker(messageIds);
            } else if (STATUS_ROLLED_BACK == status) {
                log.warn("�����ύʧ�ܣ����ݿ�ع�����ջ����е���Ϣ��{}", MessageHolder.get());
            }
        } finally {
            MessageHolder.clear();
        }
    }

    /**
     * ��broker�з�����Ϣ
     *
     * @param messageIds ��Ϣid����
     */
    private void sendMessageToBroker(List<String> messageIds) {
        if (messageIds == null || messageIds.size() == 0) {
            return;
        }
        try {
            for (String messageId : messageIds) {
                QMessage qMessage = qMessageService.getMessage(messageId);
                if (qMessage == null) {
                    continue;
                }
                transactionMessageProducer.sendMessage(qMessage);
            }
        } catch (Exception e) {
            log.error("send message error:{}", e);
        }
    }
}
