package com.drools.example;

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
public class Message {
    public static final int HELLO = 0;
    public static final int GOODBYE = 1;

    private String message;

    private int status;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
