package com.zjp.producer.annotations;

import com.zjp.producer.config.MessageProducerConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
 * Module Desc:com.zjp.producer.annotations
 * User: zjprevenge
 * Date: 2017/1/23
 * Time: 18:41
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MessageProducerConfiguration.class)
@EnableScheduling
public @interface EnableProducer {
}
