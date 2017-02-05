package com.zjp.consumer.core;

import com.zjp.consumer.domain.N1Record;
import com.zjp.consumer.domain.N2Record;
import com.zjp.consumer.service.N1RecordService;
import com.zjp.consumer.service.N2RecordService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
 * Date: 2017/1/22
 * Time: 22:25
 */
public class RepeatMessageHandle {

    @Resource
    private N1RecordService n1RecordService;

    @Resource
    private N2RecordService n2RecordService;

    /**
     * �ж���Ϣ�Ƿ��ظ�
     *
     * @param params ����
     * @param n2     �Ƿ���n2����
     * @return
     */
    @Transactional
    public void repeatHandle(Map<String, Object> params,
                             MessageMethodHandle methodHandle,
                             boolean n2) throws Exception {
        String data = (String) params.get("data");
        String messageId = (String) params.get("messageId");
        Long timeStamp = Long.valueOf((String) params.get("timeStamp"));
        String topic = (String) params.get("topic");
        StringEvent event = new StringEvent(messageId, topic, data);
        //n1�������Ϣ
        if (!n2) {
            N1Record n1Record = n1RecordService.selectN1Record(messageId);
            //��Ϣ���ظ�
            if (n1Record == null) {
                n1Record = new N1Record();
                n1Record.setMessageId(messageId);
                n1Record.setTimeStamp(timeStamp);
                //�����Ϣ��¼
                n1RecordService.addN1Record(n1Record);
                //ҵ����
                methodHandle.invokeMethod(event);
            }
        } else {
            //n2������Ϣ
            N2Record n2Record = n2RecordService.selectN2Record(params);
            //ҵ��Ψһ��ʶ
            String businessMark = (String) params.get("businessMark");
            //û����Ϣ��¼
            if (n2Record == null) {
                n2Record = new N2Record();
                n2Record.setBusinessMark(businessMark);
                n2Record.setTimeStamp(timeStamp);
                n2Record.setDestName(topic);
                //�����Ϣ��¼
                n2RecordService.addN2Record(n2Record);
                methodHandle.invokeMethod(event);
            } else if (timeStamp > n2Record.getTimeStamp()) {
                n2Record.setTimeStamp(timeStamp);
                //������Ϣ��¼
                n2RecordService.updateN2Record(n2Record);
                methodHandle.invokeMethod(event);
            }
        }
    }

}
