package com.zjp.producer.core;

import com.google.common.base.Preconditions;
import com.zjp.producer.service.QMessageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
 * Time: 19:43
 */
@Component
public class MessageHandleCallback implements MessageCallback {

    private static final Logger log = LoggerFactory.getLogger(MessageHandleCallback.class);

    @Resource
    private QMessageService qMessageService;

    /**
     * ������Ϣ����ɹ�
     *
     * @param messageId ��Ϣid
     */
    public void onSuccess(String messageId) {
        Preconditions.checkArgument(StringUtils.isNotBlank(messageId), "messageId must not empty");
        qMessageService.deleteQMessage(messageId);
    }

    /**
     * ������Ϣ����ʧ��,������־��¼
     *
     * @param e         �쳣
     * @param messageId ��Ϣid
     */
    public void onFail(Exception e, String messageId) {
        log.error("send tx messageId:{},error:{}", messageId, e);
    }
}
