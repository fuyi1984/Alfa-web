package com.alfa.web.util.constant;

/**
 * Created by Administrator on 2017/4/25.
 */
public interface WebConstants {

    public static final String CURRENT_PLATFORM_USER = "TS_CLOUD_CURRENT_PLATFORM_USER";

    /**
     * 性别
     */
    public interface SEX{
        /** 01 男 */
        String MALE = "01";

        /** 02 女 */
        String FEMALE = "02";

        /** 03 保密 */
        String SECRECY = "03";
    }

    /**
     * 用户状态
     */
    public interface USER_STATUS {

        /** 01 有效 */
        String VALID = "01";

        /** 00 禁用 */
        String INVALID = "00";

        /** 02 待审核 */
        String UNPASSED = "02";

        /** 03 待激活 */
        String INACTIVE = "03";
    }

    /**
     * 账户状态
     */
    public interface ACCOUNT_STATUS {

        /** 01 激活 */
        String ACTIVE = "01";

        /** 02 未激活 */
        String INACTIVE = "02";

        /** 03 冻结 */
        String FREEZE = "03";
    }

    /**
     * 交易方式
     */
    public interface TRADE_TYPE {

        /** 01 消费 */
        String CONSUME = "01";

        /** 02 充值 */
        String RECHARGE = "02";
    }

    /**
     * 账户类型
     * @author chxiaoqi
     *
     */
    public interface ACCOUNT_TYPE{
        /** 01 个人 */
        String PERSONAL = "01";

        /** 02 企业 */
        String ENTERPRISE = "02";

        /** 03 管理员*/
        String ADMINISTRATION = "03";
    }

    /** 调用北向接口返回结果状态 */
    public interface ResultStatus {

        /** success 成功 */
        String SUCCESS = "success";

        /** failure 失败 */
        String FAILURE = "failure";
    }

    /**
     * 消息代码
     */
    public interface MsgCd {
        /* 系统配置 */
        /**
         * 配置插入成功。
         */
        String Configuration_Insert_Success = "Configuration.Insert.Success";

        /**
         * 配置插入失败。
         */
        String Configuration_Insert_Failtrue = "Configuration.Insert.Failture";

        /**
         * 配置更新成功。
         */
        String Configuration_Update_Success = "Configuration.Update.Success";

        /**
         * 配置更新失败。
         */
        String Configuration_Update_Failtrue = "Configuration.Update.Failture";

        /**
         * 配置更新成功。
         */
        String Configuration_Delete_Success = "Configuration.Delete.Success";

        /**
         * 配置更新失败。
         */
        String Configuration_Delete_Failtrue = "Configuration.Delete.Failture";

        /**
         * 配置已经存在。
         */
        String Configuration_Exists_Success = "Configuration.Exists.Success";

        /**
         * 配置已经不存在。
         */
        String Configuration_Exists_Failtrue = "Configuration.Exists.Failture";

        /**
         * 配置查询成功。
         */
        String Configuration_Select_Success = "Configuration.Select.Success";

        /**
         * 配置查询失败。
         */
        String Configuration_Select_Failtrue = "Configuration.Select.Failture";

    }

}
