package com.zjp.consumer.mapper;

import com.zjp.consumer.domain.N1Record;

import java.util.Date;

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
 * Module Desc:com.zjp.consumer.mapper
 * User: zjprevenge
 * Date: 2017/1/22
 * Time: 15:14
 */

public interface N1RecordMapper {


    /**
     * ������Ϣid��ȡ���Ѽ�¼
     *
     * @param messageId ��Ϣid
     * @return
     */
    N1Record selectN1Record(String messageId);

    /**
     * �����Ϣ���Ѽ�¼
     *
     * @param n1Record ���Ѽ�¼
     * @return
     */
    int addN1Record(N1Record n1Record);

    /**
     * ɾ�����Ѽ�¼
     *
     * @param timeStamp ʱ���
     * @return
     */
    int deleteN1Record(Date timeStamp);
}
