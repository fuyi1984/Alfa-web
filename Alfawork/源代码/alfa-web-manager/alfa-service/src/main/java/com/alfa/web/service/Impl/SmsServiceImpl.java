package com.alfa.web.service.Impl;

import com.alfa.web.service.SmsService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */
public class SmsServiceImpl implements SmsService {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    public int sendMessage(String mobile, String message) throws IOException {
        return 0;
    }

    @Override
    public int sendMessage(List<String> mobile, String message) throws IOException {
        return 0;
    }

    @Override
    public int left() throws IOException {
        return 0;
    }
}
