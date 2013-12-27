package com.jeasywebframework.dao.dev;


import com.jeasywebframework.domain.Fields;
import com.jeasywebframework.domain.dev.SqlLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by  jeasywebframework@gmail.com on 13-12-25.
 */
public interface SqlLogDao {
    String TABLE_NAME = " sys_dev_sql_log ";

    String ALL_FIELDS = Fields.ID_SELECT
            + " sql_type AS sqlType, sql_text AS sql, "
            + Fields.AUDIT_SELECT;

    String INSERT_FIELDS = " sql_type, sql_text, "
            + Fields.AUDIT_INSERT;

    String VALUE_FIELDS = " #{sqlType}, #{sql}, "
            + Fields.AUDIT_VALUE;


    String UPDATE_FIELDS = " sql_type = #{sqlType}, sql_text = #{sql}, "
            + Fields.AUDIT_UPDATE;


    @Select("SELECT COUNT(id) FROM " + TABLE_NAME)
    Long countAll();

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " LIMIT #{offset}, #{limit} ")
    List<SqlLog> findAll(@Param("offset") int offset, @Param("limit") int limit);


    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id ", keyProperty = "id", resultType = Long.class)
    void save(SqlLog sqlLog);

    @Update("UPDATE " + TABLE_NAME + " SET " + UPDATE_FIELDS + " WHERE id = #{id} ")
    void update(SqlLog sqlLog);

}
