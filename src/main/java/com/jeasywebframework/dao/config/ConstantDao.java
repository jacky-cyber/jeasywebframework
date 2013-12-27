package com.jeasywebframework.dao.config;


import com.jeasywebframework.domain.Fields;
import com.jeasywebframework.domain.config.Constant;
import org.apache.ibatis.annotations.*;


/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
public interface ConstantDao {

    String TABLE_NAME = " sys_config_constant ";

    String ALL_FIELDS = Fields.ID_SELECT
            + Fields.NAME_SELECT
            + " val, "
            + Fields.ENABLED_SELECT
            + Fields.DESCP_SELECT
            + Fields.AUDIT_SELECT;

    String INSERT_FIELDS = Fields.NAME_INSERT
            + " val, "
            + Fields.ENABLED_INSERT
            + Fields.DESCP_INSERT
            + Fields.AUDIT_INSERT;

    String VALUE_FIELDS = Fields.NAME_VALUE
            + " #{val}, "
            + Fields.ENABLED_VALUE
            + Fields.DESCP_VALUE
            + Fields.AUDIT_VALUE;

    String UPDATE_FIELDS = Fields.NAME_UPDATE
            + " val = #{val}, "
            + Fields.ENABLED_UPDATE
            + Fields.DESCP_UPDATE
            + Fields.AUDIT_UPDATE;


    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id ", keyProperty = "id", resultType = Long.class)
    void save(Constant constant);

    @Update("UPDATE " + TABLE_NAME + " SET " + UPDATE_FIELDS + " WHERE id = #{id} ")
    void update(Constant constant);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE id = #{id}")
    Constant findOne(@Param("id") Long id);

}
