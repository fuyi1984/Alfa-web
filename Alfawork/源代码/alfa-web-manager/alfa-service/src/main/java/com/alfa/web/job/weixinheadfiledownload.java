package com.alfa.web.job;

import com.alfa.web.pojo.Vwweixinheadfile;
import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.service.order.OrdersService;
import com.alfa.web.service.weixin.VwweixinheadfileService;
import com.alfa.web.service.weixin.weixin_usersService;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 * 监控微信头像的下载状态,然后下载到本地服务器
 */
public class weixinheadfiledownload {

    private final Logger logger = LoggerFactory.getLogger(weixinheadfiledownload.class);

    @Autowired
    private VwweixinheadfileService vwweixinheadfileService;

    @Autowired
    private weixin_usersService weixin_usersService;

    public void dojob() throws InterruptedException {
        logger.info("weixinheadfiledownload Start !!!");

        if (PropertiesUtil.getProperty("weixin.head.download.open").equals("true")) {

            Criteria criteria = new Criteria();

            List<Vwweixinheadfile> filelist=this.vwweixinheadfileService.selectByParams(criteria);

            if(filelist.size()>0) {
                if (filelist.size() > 1) {
                    for (int i = 0; i < 1; i++) {
                        //region
                        if (!StringUtil.isNullOrEmpty(filelist.get(i).getHeadimgurl())) {

                            String urlString = filelist.get(i).getHeadimgurl();
                            String filename = filelist.get(i).getOpenid() + ".jpg";
                            String savePath = PropertiesUtil.getProperty("platform.root") + PropertiesUtil.getProperty("entuser.path");

                            boolean status = this.download(filelist.get(i).getHeadimgurl(), filename, savePath);

                            if (status == true) {

                                td_weixin_users td_weixin_users = new td_weixin_users();
                                td_weixin_users.setOpenid(filelist.get(i).getOpenid());
                                td_weixin_users.setLocalurl("/upload/" + filename);
                                td_weixin_users.setIsdownload("1");

                                weixin_usersService.updateByPrimaryKeySelective(td_weixin_users);
                            }
                        }
                        //endregion
                    }
                } else {
                    for (Vwweixinheadfile file : filelist) {
                        //region
                        if (!StringUtil.isNullOrEmpty(file.getHeadimgurl())) {

                            String urlString = file.getHeadimgurl();
                            String filename = file.getOpenid() + ".jpg";
                            String savePath = PropertiesUtil.getProperty("platform.root") + PropertiesUtil.getProperty("entuser.path");

                            boolean status = this.download(file.getHeadimgurl(), filename, savePath);

                            if (status == true) {

                                td_weixin_users td_weixin_users = new td_weixin_users();
                                td_weixin_users.setOpenid(file.getOpenid());
                                td_weixin_users.setLocalurl("/upload/" + filename);
                                td_weixin_users.setIsdownload("1");

                                weixin_usersService.updateByPrimaryKeySelective(td_weixin_users);
                            }
                        }
                        //endregion
                    }
                }
            }

        }

        logger.info("weixinheadfiledownload End !!!");
    }


    private boolean download(String urlString, String filename,String savePath){

        try {
            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            //设置请求超时为5s
            con.setConnectTimeout(60 * 1000);
            // 输入流
            InputStream is = con.getInputStream();

            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File sf = new File(savePath);
            if (!sf.exists()) {
                sf.mkdirs();
            }
            OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
        }catch (Exception e){
            logger.info(e.toString());
            return false;
        }

        return true;
    }
}
