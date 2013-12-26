package com.jeasywebframework.domain;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
public class Global {


    public static final String ENABLED_YES = "Y";
    public static final String ENABLED_NO = "N";

    public static final String SELECT_FIELD_ID = " id, ";
    public static final String SELECT_FIELD_ORDER = " oorder, ";
    public static final String SELECT_FIELD_ENABLED = " enabled, ";
    public static final String SELECT_FIELD_DESCP = " descp, ";
    public static final String SELECT_FIELD_CODE = " code, ";
    public static final String SELECT_FIELD_NAME = " name, ";
    public static final String SELECT_FIELD_TREENODE = " path, level, children_num as childrenNum, parent_id AS parentId, ";
    public static final String SELECT_FIELD_AUDIT = " create_user_id as createUserId, create_time as createTime, update_user_id as updateUserId, update_time as updateTime ";


    public static final String INSERT_FIELD_ORDER = " oorder, ";
    public static final String INSERT_FIELD_ENABLED = " enabled, ";
    public static final String INSERT_FIELD_DESCP = " descp, ";
    public static final String INSERT_FIELD_CODE = " code, ";
    public static final String INSERT_FIELD_NAME = " name, ";
    public static final String INSERT_FIELD_AUDIT = " create_user_id, create_time, update_user_id, update_time ";

    public static final String VALUE_FIELD_ORDER = " #{oorder}, ";
    public static final String VALUE_FIELD_ENABLED = " #{enabled}, ";
    public static final String VALUE_FIELD_DESCP = " #{descp}, ";
    public static final String VALUE_FIELD_CODE = " #{code}, ";
    public static final String VALUE_FIELD_NAME = " #{name}, ";
    public static final String VALUE_FIELD_AUDIT = " #{createUserId}, #{createTime}, #{updateUserId}, #{updateTime} ";


    public static final String UPDATE_FIELD_NAME = " name = #{name}, ";
    public static final String UPDATE_FIELD_CODE = " code = #{code}, ";
    public static final String UPDATE_FIELD_ENABLED = " enabled = #{enabled}, ";
    public static final String UPDATE_FIELD_ORDER = " oorder = #{oorder}, ";
    public static final String UPDATE_FIELD_DESCP = " descp = #{descp}, ";
    public static final String UPDATE_FIELD_AUDIT = " create_user_id = #{createUserId}, create_time = #{createTime}, update_user_id = #{updateUserId}, update_time = #{updateTime} ";


}
