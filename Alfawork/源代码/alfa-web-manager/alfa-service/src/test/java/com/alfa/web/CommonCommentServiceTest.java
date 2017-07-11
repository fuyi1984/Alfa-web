package com.alfa.web;

import com.alfa.web.pojo.CommonComment;
import com.alfa.web.service.CommonCommentService;
import com.alfa.web.service.HistoryAddressService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
public class CommonCommentServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(CommonCommentServiceTest.class);

    @Autowired
    private CommonCommentService commonCommentService;

    @Test
    public void insertCommonComment(){

        CommonComment commonComment=new CommonComment();
        commonComment.setContent("121212");

        Criteria criteria=new Criteria();

        int count=this.commonCommentService.countByParams(criteria);

        if(count< Integer.parseInt(PropertiesUtil.getProperty("Comment.Maxnum"))) {

            //region test

            criteria.put("Content", commonComment.getContent());

            List<CommonComment> commonCommentList = this.commonCommentService.selectByParams(criteria);

            if (commonCommentList.size() == 0) {

                int result = this.commonCommentService.insertSelective(commonComment);

                if (result >= 1) {
                    //常用评语插入成功
                    System.out.println("1");
                } else {
                    //常用评语插入失败
                   System.out.println("2");
                }
            } else {
                //数据已存在
                System.out.println("3");
            }

            //endregion

        }else{
            //常用评语大于规定的范围
            System.out.println("4");
        }
    }

}
