package com.zjp.consumer.annotations;

import java.lang.annotation.*;

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
 * Module Desc:com.zjp.consumer.annotations
 * User: zjprevenge
 * Date: 2017/1/22
 * Time: 14:46
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Listener {

    //��Ϣ���е�����
    String topic() default "";

    //�Ƿ�֧������,Ĭ�ϲ�����
    boolean transaction() default false;

    //�Ƿ�֧��n2�������Ϣ��Ĭ�ϲ�����
    boolean n2() default false;
}
