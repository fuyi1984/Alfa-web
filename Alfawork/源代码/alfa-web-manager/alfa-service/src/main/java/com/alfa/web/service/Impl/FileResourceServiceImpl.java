package com.alfa.web.service.Impl;

import com.alfa.web.service.FileResourceService;
import com.alfa.web.util.FileUtil;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by Administrator on 2017/7/25.
 */
@Service
public class FileResourceServiceImpl implements FileResourceService {

    private static final Logger logger = LoggerFactory.getLogger(FileResourceServiceImpl.class);


    @Override
    public long upload(Attachment file, String path, String fileName) {
        FileUtil.removeFile(path, fileName);
        File f = new File(path, fileName);
        FileUtil.saveFile(file.getDataHandler(), f);
        return f.length();
    }
}
