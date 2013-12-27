package com.jeasywebframework.domain;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
public class Fields {


    public static final String ENABLED_YES = "Y";
    public static final String ENABLED_NO = "N";

    public static final String ID_SELECT = " id, ";
    public static final String ORDER_SELECT = " oorder, ";
    public static final String ENABLED_SELECT = " enabled, ";
    public static final String DESCP_SELECT = " descp, ";
    public static final String CODE_SELECT = " code, ";
    public static final String NAME_SELECT = " name, ";
    public static final String TREENODE_SELECT = " path, level, children_num as childrenNum, parent_id AS parentId, ";
    public static final String AUDIT_SELECT = " create_user_id as createUserId, create_time as createTime, update_user_id as updateUserId, update_time as updateTime ";


    public static final String ORDER_INSERT = " oorder, ";
    public static final String ENABLED_INSERT = " enabled, ";
    public static final String DESCP_INSERT = " descp, ";
    public static final String CODE_INSERT = " code, ";
    public static final String NAME_INSERT = " name, ";
    public static final String AUDIT_INSERT = " create_user_id, create_time, update_user_id, update_time ";
    public static final String TREENODE_INSERT = " path, level, children_num, parent_id, ";

    public static final String ORDER_VALUE = " #{oorder}, ";
    public static final String ENABLED_VALUE = " #{enabled}, ";
    public static final String DESCP_VALUE = " #{descp}, ";
    public static final String CODE_VALUE = " #{code}, ";
    public static final String NAME_VALUE = " #{name}, ";
    public static final String AUDIT_VALUE = " #{createUserId}, #{createTime}, #{updateUserId}, #{updateTime} ";
    public static final String TREENODE_VALUE = " #{path}, #{level}, #{childrenNum}, #{parentId}, ";

    public static final String NAME_UPDATE = " name = #{name}, ";
    public static final String CODE_UPDATE = " code = #{code}, ";
    public static final String ENABLED_UPDATE = " enabled = #{enabled}, ";
    public static final String ORDER_UPDATE = " oorder = #{oorder}, ";
    public static final String DESCP_UPDATE = " descp = #{descp}, ";
    public static final String AUDIT_UPDATE = " create_user_id = #{createUserId}, create_time = #{createTime}, update_user_id = #{updateUserId}, update_time = #{updateTime} ";
    public static final String TREENODE_UPDATE = "path = #{path}, level = #{level}, children_num = #{childrenNum}, parent_id = #{parentId}, ";


}
