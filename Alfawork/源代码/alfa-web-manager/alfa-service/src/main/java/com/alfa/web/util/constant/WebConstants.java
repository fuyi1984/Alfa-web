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

    /**
     * 消息代码
     */
    public interface MsgCd {
        /* 提示消息 */
        /**
         * info.add.success=添加成功。
         */
        String INFO_ADD_SUCCESS = "info.add.success";

        /**
         * info.insert.success=创建成功。
         */
        String INFO_INSERT_SUCCESS = "info.insert.success";

        /**
         * info.register.success=注册成功。
         */
        String INFO_REGISTER_SUCCESS = "info.register.success";

        /**
         * info.update.success=更新成功。
         */
        String INFO_UPDATE_SUCCESS = "info.update.success";

        /**
         * info.edit.success=编辑成功。
         */
        String INFO_EDIT_SUCCESS = "info.edit.success";

        /**
         * info.publish.success=发布成功。
         */
        String INFO_PUBLISH_SUCCESS = "info.publish.success";

        /**
         * info.delete.success=删除成功。
         */
        String INFO_DELETE_SUCCESS = "info.delete.success";

        /**
         * info.operation.success=操作成功。
         */
        String INFO_OPERATION_SUCCESS = "info.operation.success";

        /**
         * info.operation.error=操作失败。
         */
        String INFO_OPERATION_ERROR = "info.operation.error";

        /**
         * info.relation.success=关联操作成功。
         */
        String INFO_RELATION_SUCCESS = "info.relation.success";

        /**
         * info.approve.success=审核成功。
         */
        String INFO_APPROVE_SUCCESS = "info.approve.success";

        /**
         * info.op.success=操作成功。
         */
        String INFO_OP_SUCCESS = "info.op.success";

        /**
         * info.message.success=发送成功。
         */
        String INFO_MESSAGE_SUCCESS = "info.message.success";

        /**
         * info.inventory.success=盘点成功。
         */
        String INFO_INVENTORY_SUCCESS = "info.inventory.success";

        /**
         * info.in.success=入库成功。
         */
        String INFO_IN_SUCCESS = "info.in.success";

        /**
         * info.relate.success=关联成功。
         */
        String INFO_RELATE_SUCCESS = "info.relate.success";

        /**
         * info.out.success=出库成功。
         */
        String INFO_OUT_SUCCESS = "info.out.success";

        /**
         * info.copy.success=复制成功。
         */
        String INFO_COPY_SUCCESS = "info.copy.success";

        /**
         * info.config.success=配置成功。
         */
        String INFO_CONFIG_SUCCESS = "info.config.success";

        /**
         * vm.start.success=启动成功。
         */
        String VM_START_SUCCESS = "vm.start.success";

        /**
         * vm.reconfig.success=调整成功。
         */
        String VM_RECONFIG_SUCCESS = "vm.start.reconfig";

        /**
         * vm.migrate.success=迁移成功。
         */
        String VM_MIGRATE_SUCCESS = "vm.migrate.success";

        /**
         * vm.destory.success=退订成功。
         */
        String VM_DESTORY_SUCCESS = "vm.destory.success";

        /**
         * vm.managed.success=纳管成功。
         */
        String VM_MANAGED_SUCCESS = "vm.managed.success";

        /**
         * vm.sync.success=同步成功。
         */
        String VM_SYNC_SUCCESS = "vm.sync.success";

        /**
         * 移动端
         */
        /**
         * 获取成功。
         */
        String INFO_MOBILE_GET_SUCCESS = "info.mobile.get.success";

        /* 警告消息 */
        /**
         * warning_service_repeat=对不起，该服务不能重复订购。
         */
        String WARNING_SERVICE_REPEAT = "warning_service_repeat";

        /**
         * warning.query.failure=对不起，数据为空。
         */
        String WARNING_QUERY_FAILURE = "warning.query.failure";

        /* 错误消息 */
        /**
         * error.system.exception
         **/
        String ERROR_SYS_EXCEPTION = "error.system.exception";

        /**
         * error.add.failure=添加失败。
         */
        String ERROR_ADD_FAILURE = "error.add.failure";

        /**
         * error.insert.failure=创建失败。
         */
        String ERROR_INSERT_FAILURE = "error.insert.failure";

        String ERROR_ACCOUNT_EXISTS = "error.account.exists";

        /**
         * error.roles.exists=角色名已经存在。
         */
        String ERROR_ROLES_EXISTS = "error.roles.exists";

        /**
         * error.role.used=角色已被使用。
         */
        String ERROR_ROLE_USED = "error.role.used";

        /**
         * 收藏名称同级目录不能重复。
         **/
        String ERROR_COLLECTION_NAME_DUPLICATE = "error.collection.name.duplicate";

        /**
         * error.message.failure=已发送，请勿重复发送。
         */
        String ERROR_MESSAGE_FAILURE2 = "error.message.failure2";

        /**
         * error.message.failure=发送失败。
         */
        String ERROR_MESSAGE_FAILURE = "error.message.failure";

        /**
         * error.query.failure=获取信息失败，数据已被更新或删除。
         */
        String ERROR_QUERY_FAILURE = "error.query.failure";

        /**
         * error.register.failure=注册失败，数据或已存在，请重试。
         */
        String ERROR_REGISTER_FAILURE = "error.register.failure";

        /**
         * error.update.failure=更新失败，数据已被更新或删除。
         */
        String ERROR_UPDATE_FAILURE = "error.update.failure";

        /**
         * error.edit.failure=编辑失败。
         */
        String ERROR_EDIT_FAILURE = "error.edit.failure";

        /**
         * error.publish.success=发布失败。
         */
        String ERROR_PUBLISH_FAILURE = "error.publish.failure";

        /**
         * error.delete.failure=删除失败，数据已被更新或删除。
         */
        String ERROR_DELETE_FAILURE = "error.delete.failure";

        /**
         * error.approve.failure=审核失败。
         */
        String ERROR_APPROVE_FAILURE = "error.approve.failure";

        /**
         * error.inventory.failure=盘点失败。
         */
        String ERROR_INVENTORY_FAILURE = "error.inventory.failure";

        /**
         * error.in.failure=入库失败。
         */
        String ERROR_IN_FAILURE = "error.in.failure";

        /**
         * error.relate.failure=关联失败。
         */
        String ERROR_RELATE_FAILURE = "error.relate.failure";

        /**
         * error.out.failure=出库失败。
         */
        String ERROR_OUT_FAILURE = "error.out.failure";

        /**
         * error.copy.failure=复制失败。
         */
        String ERROR_COPY_FAILURE = "error.copy.failure";

        /**
         * error.config.failure=配置失败。
         */
        String ERROR_CONFIG_FAILURE = "error.config.failure";

        /**
         * error.relation.failure=关联操作失败，数据已被更新或删除。
         */
        String ERROR_RELATION_FAILURE = "error.relation.failure";

        /**
         * error.data.exist={0}已经存在，请重新填写。
         */
        String ERROR_DATA_EXIST = "error.data.exist";

        /**
         * error.data.relation={0}，不能进行删除。
         */
        String ERROR_DATA_RELATION = "error.data.relation";

        /**
         * error.data.relation={0}，数据已被更新或删除。
         */
        String ERROR_DATA_FAILURE = "error.data.failure";

        /**
         * error.file.oversize=您选择的文件过大，请重新选择。
         */
        String ERROR_FILE_OVERSIZE = "error.file.oversize";

        /**
         * error.plan.published=该名称预案已经发布。
         */
        String ERROR_PLAN_PUBLISHED = "error.plan.published";

        /**
         * error.plan.published=该预案已经停用。
         */
        String ERROR_PLAN_DISABLED = "error.plan.disabled";

        /**
         * error.assess.maxnum={0}不能超过十个。
         */
        String ERROR_ASSESS_MAXNUM = "error.assess.maxnum";

        /**
         * 订单取消失败。
         */
        String ERROR_CANCEL_ORDER = "error.cancel.order";

        /**
         * error.vm.start=启动失败。
         */
        String ERROR_VM_START = "error.vm.start";

        /**
         * error.vm.stop=关机失败。
         */
        String ERROR_VM_STOP = "error.vm.stop";

        /**
         * error.vm.restart=重启失败。
         */
        String ERROR_VM_RESTART = "error.vm.restart";

        /**
         * error.vm.reconfig=调整失败。
         */
        String ERROR_VM_RECONFIG = "error.vm.reconfig";

        /**
         * error.vm.migrate=迁移失败。
         */
        String ERROR_VM_MIGRATE = "error.vm.migrate";

        /**
         * error.vm.managed=纳管失败。
         */
        String ERROR_VM_MANAGED = "error.vm.managed";

        /**
         * error.vm.scan=扫描失败。
         */
        String ERROR_VM_SCAN = "error.vm.scan";

        /**
         * error.vm.destory=退订失败。
         */
        String ERROR_VM_DESTORY = "error.vm.destory";

        /**
         * error.vm.sync=同步失败。
         */
        String ERROR_VM_SYNC = "error.vm.sync";

        /**
         * error.license.invalid=License失效。
         */
        String ERROR_LICENSE_INVALID = "error.license.invalid";

        /**
         * error.product.delete.failure = 该产品目前已被使用，不能删除
         */
        String ERROR_PRODUCT_DELETE_FAILURE = "error.product.delete.failure";

        /**
         * 移动端
         */
        /**
         * 获取失败。
         */
        String ERROR_MOBILE_GET_FAILURE = "error.mobile.get.failure";

        /* 警告消息 */
        /**
         * warning_ippool_repeat=对不起，该IP不能重复添加到资源池。
         */
        String WARNING_IPPOOL_REPEAT = "warning_ippool_repeat";

        /* 警告消息 */
        /**
         * warning_ip_repeat=对不起，该IP已经添加到资源。
         */
        String WARNING_IP_REPEAT = "warning_ip_repeat";

        /**
         * 虚拟工作室
         */
        /**
         * 申请成功。
         **/
        String INFO_VR_APPLY_SUCCESS = "info.vr.apply.success";
        /**
         * 修改成功。
         **/
        String INFO_VR_MODIFY_SUCCESS = "info.vr.modify.success";
        /**
         * 回调成功。
         **/
        String INFO_VR_CALLBACK_SUCCESS = "info.vr.callback.success";
        /**
         * 回调失败
         **/
        String ERROR_VR_CALLBACK_FAILURE = "error.vr.callback.failure";
        /**
         * 退订成功。
         **/
        String INFO_VR_VDIRETURN_SUCCESS = "info.vr.vdireturn.success";
        /**
         * 启动成功。
         **/
        String INFO_VR_VDISTART_SUCCESS = "info.vr.vdistart.success";
        /**
         * 关闭成功。
         **/
        String INFO_VR_VDISTOP_SUCCESS = "info.vr.vdistop.success";
        /**
         * 挂起成功。
         **/
        String INFO_VR_VDISUSPENDED_SUCCESS = "info.vr.vdisuspended.success";
        /**
         * 恢复成功。
         **/
        String INFO_VR_VDIRESUME_SUCCESS = "info.vr.vdiresume.success";
        /**
         * 重启成功。
         **/
        String INFO_VR_VDIREBOOT_SUCCESS = "info.vr.vdireboot.success";
        /**
         * 重置密码成功。
         **/
        String INFO_VR_RESETPWD_SUCCESS = "info.vr.resetpwd.success";
        /**
         * 获取密码成功。
         **/
        String INFO_VR_GETPWD_SUCCESS = "info.vr.getpwd.success";

        /**
         * 申请成功。
         **/
        String INFO_VR_MEMBER_APPLY_SUCCESS = "info.vr.member.apply.success";
        /**
         * 取消成功。
         **/
        String INFO_VR_MEMBER_CANCEL_SUCCESS = "info.vr.member.cancel.success";
        /**
         * 审批成功。
         **/
        String INFO_VR_MEMBER_APPROVED_SUCCESS = "info.vr.member.approved.success";
        /**
         * 拒绝成功。
         **/
        String INFO_VR_MEMBER_REJECT_SUCCESS = "info.vr.member.reject.success";

        /**
         * 参数错误。
         **/
        String ERROR_VR_PARAMETER_WRONG = "error.vr.parameter.wrong";
        /**
         * 非法访问。
         **/
        String ERROR_VR_ILLEGAL_ACCESS = "error.vr.illegal.access";
        /**
         * 请先删除工作室成员。
         **/
        String ERROR_VR_DEL_MEMBER_FIRSTLY = "error.vr.del.member.firstly";
        /**
         * 请先删除虚拟桌面。
         **/
        String ERROR_VR_DEL_DESKTOP_FIRSTLY = "error.vr.del.desktop.firstly";
        /**
         * 虚拟工作室名称重复。
         **/
        String ERROR_VR_ROOMNAME_DUPLICATE = "error.vr.roomname.duplicate";
        /**
         * 无权更新当前数据。
         **/
        String ERROR_VR_ILLEGAL_UPDATE = "error.vr.illegal.update";
        /**
         * 系统数据错误。
         **/
        String ERROR_VR_SYSDATA_ERROR = "error.vr.sysdata.error";
        /**
         * 只有虚拟工作室主人才能删除成员。
         **/
        String ERROR_VR_ONLY_OWNER_DEL_MEMBER = "error.vr.only.owner.del.member";
        /**
         * 只有虚拟工作室所有者才能添加成员。
         **/
        String ERROR_VR_ONLY_OWNER_ADD_MEMBER = "error.vr.only.owner.add.member";
        /**
         * 参数无效，主键为空。
         **/
        String ERROR_VR_PRIMARYKEY_MISSING = "error.vr.primarykey.missing";
        /**
         * 无效的参数，成员不存在。
         **/
        String ERROR_VR_MEMBER_NOTFOUND = "error.vr.member.notfound";
        /**
         * 该用户已经是工作室成员了。
         **/
        String ERROR_VR_ALREADY_BE_MEMBER = "error.vr.already.be.member";
        /**
         * 虚拟工作室不存在。
         **/
        String ERROR_VR_ROOM_NOTFOUND = "error.vr.room.notfound";
        /**
         * 镜像已被绑定到产品，不能删除。
         **/
        String ERROR_VR_IMG_PRODBIND_CANNOT_DEL = "error.vr.img.prodbind.cannot.del";
        /**
         * 镜像名称已经存在。
         **/
        String ERROR_VR_IMG_NAME_EXISTED = "error.vr.img.name.existed";
        /**
         * 名称和文件名不能为空。
         **/
        String ERROR_VR_IMG_NAME_CANNOT_NULL = "error.vr.img.name.cannot.null";
        /**
         * 工作室下的虚拟桌面名称不能重复。
         **/
        String ERROR_VR_DESKTOP_NAME_DUPLICATE = "error.vr.desktop.name.duplicate";
        /**
         * 用户工作目录没找到。
         **/
        String ERROR_VR_DESKTOP_WORKFOLDER_NOTFOUND = "error.vr.desktop.workfolder.notfound";
        /**
         * 虚拟机镜像不存在。
         **/
        String EERROR_VR_IMG_NOTFOUND = "error.vr.img.notfound";
        /**
         * 虚拟桌面不存在。
         **/
        String ERROR_VR_DESKTOP_NOTFOUND = "error.vr.desktop.notfound";
        /**
         * 只有所在的虚拟工作室所有者才能归还虚拟桌面。
         **/
        String ERROR_VR_DESKTOP_ONLY_OWNER_CAN_RET = "error.vr.desktop.only.owner.can.ret";

        //成员申请已提交，不能重复申请
        String ERROR_VR_MEMBER_ALREADY_APPLIED = "error.vr.member.already.applied";

        /* Render message*/
        String SYSTEM_USER_NOT_FOUND = "system.user.not.found";
        String MSG_TEMPLATE_RENDERING_COMPLETE_NOTIFICATION = "system.message.render.success";
        String MSG_TEMPLATE_PRERENDERING_COMPLETE_NOTIFICATION = "system.message.prerender.success";
        String MSG_TEMPLATE_SCENEANALYSIS_COMPLETE_NOTIFICATION = "system.message.sceneanalysis.success";
        String MSG_TEMPLATE_ABNORMAL_JOB_DETECTED = "system.message.abnormaljob.detected";

        /* Coupon  **/
        String ERROR_DATE_IS_NULL = "error.date.is.null";
        String ERROR_START_DAY_LT_TODAY = "error.start.day.lt.today";
        String ERROR_END_DAY_LT_START_DAY = "error.end.day.lt.start_day";
        String ERROR_COUPON_DISCOUNT_ERROR = "error.coupon.discount.error";
        String ERROR_COUPON_GENERATE_ERROR = "error.coupon.generate.error";

        String SYSTEM_MESSAGE_COUPON = "system.message.coupon";

        /**
         * 礼品卡
         **/
        //作废成功
        String INFO_GIFT_CARD_INVALID_SUCCESS = "info.gift.card.invalid.success";
        //激活成功
        String INFO_GIFT_CARD_ACTIVATE_SUCCESS = "info.gift.card.activate.success";
        //生成成功
        String INFO_GIFT_CARD_GEN_SUCCESS = "info.gift.card.gen.success";
        //礼品卡无法生成
        String INFO_GIFT_CARD_CANNOT_GEN = "info.gift.card.cannot.gen";
        //参数错误
        String ERROR_PARAMETER_WRONG = "error.parameter.wrong";
        //礼品卡名字为空
        String ERROR_GIFT_CARD_NAME_EMPTY = "error.gift.card.name.empty";
        //面额小于0
        String ERROR_GIFT_CARD_FACE_VALUE_LE_ZERO = "error.gift.card.face.value.le.zero";
        //数量小于0
        String ERROR_GIFT_CARD_QUANTITY_LE_ZERO = "error.gift.card.quantity.le.zero";
        //本批次不能作废
        String ERROR_GIFT_CARD_BATCH_CANNOT_INVALID = "error.gift.card.batch.cannot.invalid";
        //本批次不能更新
        String ERROR_GIFT_CARD_BATCH_CANNOT_UPDATE = "error.gift.card.batch.cannot.update";
        //出账失败
        String BILLING_ERROR_BILLINGGENBILL = "billing.error.billingGenBill";
        //该礼品卡不能作废
        String ERROR_GIFT_CARD_CANNOT_INVALID = "error.gift.card.cannot.invalid";

        //无效的帐号或密码
        String ERROR_GIFT_CARD_INVALID_PIN = "error.gift.card.invalid.pin";
        //礼品卡数据重复
        String ERROR_GIFT_CARD_DATA_DUP = "error.gift.card.data.dup";
        //礼品卡尚未激活
        String ERROR_GIFT_CARD_NOT_ACTIVATE = "error.gift.card.not.activate";
        //礼品卡已被充值
        String ERROR_GIFT_CARD_ALREADY_CHARGED = "error.gift.card.already.charged";
        //无效的礼品卡
        String ERROR_GIFT_CARD_INVALID = "error.gift.card.invalid";

        /*** cms start **/
        //版块代码不能为空
        String ERROR_CMS_SECTION_CODE_REQUIRED = "error.cms.section.code.required";
        //标题不能为空
        String ERROR_CMS_TITLE_REQUIRED = "error.cms.title.required";
        //内容类型不能为null
        String ERROR_CMS_CONTENT_TYPE_REQUIRED = "error.cms.content.type.required";
        //类容类型无效
        String ERROR_CMS_CONTENT_TYPE_INVALID = "error.cms.content.type.invalid";
        //详情链接地址不能为空
        String ERROR_CMS_CONTENT_URL_REQUIRED = "error.cms.content.url.required";
        //详情不能为空
        String ERROR_CMS_CONTENT_REQUIRED = "error.cms.content.required";
        //列表内容类型不能为null
        String ERROR_CMS_CONTENT_lIST_TYPE_REQUIRED = "error.cms.content.list.type.required";
        //列表内容类型无效
        String ERROR_CMS_CONTENT_lIST_TYPE_INVALID = "error.cms.content.list.type.invalid";
        //版块已存在
        String ERROR_CMS_CONTENT_EXIST = "error.cms.content.exist";
        //版块不存在
        String ERROR_CMS_SECTION_NOT_EXIST = "error.cms.section.not.exist";
        //存在版块列表记录，不能删除
        String ERROR_CMS_SECTION_DEL_ERROR_FOR_EXIST_LIST = "error.cms.section.del.error.for.exist.list";
        //版块列表记录不存在
        String ERROR_CMS_SECTION_LIST_NOT_EXIST = "error.cms.section.list.not.exist";
        /**
         * End of cms code
         **/


        String ERROR_STORAGE_CHECK_FILE_NUMBER = "error.storage.check.file.number";

    }

}
