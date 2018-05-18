package com.drools.example;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/************************************************************************************
 * Copyright (c) 2017 © Bestpay Co., Ltd.  All Rights Reserved.
 * This software is published under the terms of the Bestpay.
 * Software License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * <p>
 * File name:      
 * Create on:      2018/5/18
 * Author :        官红诚
 * <p>
 * ChangeList
 * -----------------------------------------------------------------------------
 * Date                Editor        ChangeReasons
 * 2018/5/18            官红诚         Create
 ************************************************************************************/
public class Main2 {

    public static void main(String[] args) {
        //从工厂中获得KieServices实例
        KieServices kieServices = KieServices.Factory.get();
        //从KieServices中获得KieContainer实例，其会加载kmodule.xml文件并load规则文件
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //建立KieSession到规则文件的通信管道
        KieSession kSession = kieContainer.newKieSession("ksession-rules");
        Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);
        //将实体类插入执行规则
        kSession.insert(message);
        kSession.fireAllRules();
    }
}
