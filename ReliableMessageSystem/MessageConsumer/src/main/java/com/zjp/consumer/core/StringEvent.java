package com.zjp.consumer.core;

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
 * Date: 2017/1/22
 * Time: 23:13
 */

public class StringEvent implements Event<String> {

    private String messageId;

    private String topic;

    private String data;

    public StringEvent() {
    }

    public StringEvent(String messageId, String topic, String data) {
        this.messageId = messageId;
        this.topic = topic;
        this.data = data;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * ��ϢId
     *
     * @return
     */
    public String messageId() {
        return messageId;
    }

    /**
     * ��Ϣ��ַ
     *
     * @return
     */
    public String topic() {
        return topic;
    }

    /**
     * ��Ϣ����
     *
     * @return
     */
    public String content() {
        return data;
    }
}
