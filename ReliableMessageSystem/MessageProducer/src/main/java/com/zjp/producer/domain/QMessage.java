package com.zjp.producer.domain;

import java.io.Serializable;

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
 * Module Desc:com.zjp.producer.domain
 * User: zjprevenge
 * Date: 2017/1/22
 * Time: 13:57
 */

public class QMessage implements Serializable {

    private Integer id;

    //��Ϣid
    private String messageId;

    //ҵ���߱�ʶ
    private String businessMark;

    //��Ϣ����
    private String messageContent;

    //��Ϣ״̬
    private Integer status;

    //���Դ���
    private Integer retry;

    //��ϢͶ�ݵ�ַ
    private String destination;

    //�Ƿ���������Ϣ
    private int transaction;

    //��Ϣ�־û�
    private int persistent;

    //n2����
    private int n2;
    //ʱ���
    private long timeStamp;


    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public int getPersistent() {
        return persistent;
    }

    public void setPersistent(int persistent) {
        this.persistent = persistent;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getBusinessMark() {
        return businessMark;
    }

    public void setBusinessMark(String businessMark) {
        this.businessMark = businessMark;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
