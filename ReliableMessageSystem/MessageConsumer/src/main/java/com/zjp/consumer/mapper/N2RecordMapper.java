package com.zjp.consumer.mapper;

import com.zjp.consumer.domain.N2Record;

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
 * Module Desc:com.zjp.consumer.mapper
 * User: zjprevenge
 * Date: 2017/1/22
 * Time: 15:14
 */

public interface N2RecordMapper {

    /**
     * ����ҵ���ʶ��ѯN2���͵ļ�¼
     *
     * @param params ��ѯ����
     * @return
     */
    N2Record selectN2RecordByMark(Map<String, Object> params);

    /**
     * ���ݲ�����ѯN2���͵ļ�¼
     *
     * @param params ��ѯ����
     * @return
     */
    N2Record selectN2Record(Map<String, Object> params);

    /**
     * ���N2���͵ļ�¼
     *
     * @param n2Record N2��¼
     * @return
     */
    int addN2Record(N2Record n2Record);

    /**
     * ����N2���͵ļ�¼
     *
     * @param n2Record N2���͵ļ�¼
     * @return
     */
    int updateN2Record(N2Record n2Record);
}
