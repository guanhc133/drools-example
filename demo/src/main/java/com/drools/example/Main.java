package com.drools.example;

import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.io.UnsupportedEncodingException;

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
public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //rule,rule2可以放在数据库中，有个唯一code和他们对于，
        // 代码要执行规则的时候，根据code从数据库获取出来就OK了，
        // 这样自己开发的规则管理系统那边对数据库里的规则进行维护就行了
        String rule = "package com.drools.example\r\n";
        rule += "import com.drools.example.Message;\r\n";
        rule += "rule \"rule1\"\r\n";
        rule += "\twhen\r\n";
        rule += "Message( status == 1, myMessage : message )";
        rule += "\tthen\r\n";
        rule += "\t\tSystem.out.println( 1+\":\"+myMessage );\r\n";
        rule += "end\r\n";


        String rule2 = "package com.drools.example\r\n";
        rule += "import com.drools.example.Message;\r\n";

        rule += "rule \"rule2\"\r\n";
        rule += "\twhen\r\n";
        rule += "Message( status == 2, myMessage : message )";
        rule += "\tthen\r\n";
        rule += "\t\tSystem.out.println( 2+\":\"+myMessage );\r\n";
        rule += "end\r\n";

        System.out.println(rule);

        StatefulKnowledgeSession kSession = null;
        try {


            KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
            //装入规则，可以装入多个
            kb.add(ResourceFactory.newByteArrayResource(rule.getBytes("utf-8")), ResourceType.DRL);
            kb.add(ResourceFactory.newByteArrayResource(rule2.getBytes("utf-8")), ResourceType.DRL);

            KnowledgeBuilderErrors errors = kb.getErrors();
            for (KnowledgeBuilderError error : errors) {
                System.out.println(error);
            }
            KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
            kBase.addKnowledgePackages(kb.getKnowledgePackages());

            kSession = kBase.newStatefulKnowledgeSession();


            Message message1 = new Message();
            message1.setStatus(1);
            message1.setMessage("hello world!");

            Message message2 = new Message();
            message2.setStatus(2);
            message2.setMessage("hi world!");

            kSession.insert(message1);
            kSession.insert(message2);
            kSession.fireAllRules();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (kSession != null)
                kSession.dispose();
        }

    }
}
