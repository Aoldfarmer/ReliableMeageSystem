package com.zjp.producer.core;

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
 * Module Desc:com.zjp.producer.core
 * User: zjprevenge
 * Date: 2017/1/23
 * Time: 18:53
 */

public interface MessageCallback {

    /**
     * ������Ϣ����ɹ�
     *
     * @param messageId ��Ϣid
     */
    void onSuccess(String messageId);

    /**
     * ������Ϣ����ʧ��
     *
     * @param e         �쳣
     * @param messageId ��Ϣid
     */
    void onFail(Exception e, String messageId);
}
