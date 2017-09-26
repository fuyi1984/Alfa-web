package com.alfa.web.service.common;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

/**
 * Created by Administrator on 2017/7/25.
 */
public interface FileResourceService {

    long upload(Attachment file, String path, String fileName);
}
