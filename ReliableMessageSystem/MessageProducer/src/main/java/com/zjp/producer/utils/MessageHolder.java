package com.zjp.producer.utils;

import com.google.common.collect.Lists;

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
 * Module Desc:com.zjp.producer.utils
 * User: zjprevenge
 * Date: 2017/1/23
 * Time: 21:06
 */

public class MessageHolder {

    private static ThreadLocal<List<String>> messageHolder = new ThreadLocal<List<String>>() {

        @Override
        protected List<String> initialValue() {
            return Lists.newArrayList();
        }
    };

    public static List<String> get() {
        return messageHolder.get();
    }

    public static void set(String messageId) {
        List<String> list = messageHolder.get();
        list.add(messageId);
        messageHolder.set(list);
    }

    public static void clear() {
        messageHolder.remove();
    }
}
