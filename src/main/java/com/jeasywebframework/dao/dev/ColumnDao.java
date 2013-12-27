package com.jeasywebframework.dao.dev;

import com.jeasywebframework.domain.Fields;
import com.jeasywebframework.domain.dev.ColumnInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
public interface ColumnDao {
    String TABLE_NAME = " sys_dev_gen_column ";

    String ALL_FIELDS = Fields.ID_SELECT
            + " table_name AS tableName, name, small_name AS smallName, big_name AS bigName, "
            + " cname, descp, db_type AS dbType, db_key AS dbKey,"
            + " db_null AS dbNull, java_type AS javaType, html_type AS htmlType,"
            + " required, max_length AS maxLength, min_length AS minLength, "
            + " required_msg AS requiredMsg, max_val AS maxVal, min_val AS minVal, date_format AS dateFormat, "
            + Fields.AUDIT_SELECT;


    String INSERT_FIELDS = " table_name, name, small_name, big_name, "
            + " cname, descp, db_type, db_key,"
            + " db_null, java_type, html_type,"
            + " required, max_length, min_length, "
            + " required_msg, max_val, min_val, date_format, "
            + Fields.AUDIT_INSERT;

    String VALUE_FIELDS = " #{tableName}, #{name}, #{smallName}, #{bigName}, "
            + " #{cname}, #{descp}, #{dbType}, #{dbKey},"
            + " #{dbNull}, #{javaType}, #{htmlType},"
            + " #{required}, #{maxLength}, #{minLength}, "
            + " #{requiredMsg}, #{maxVal}, #{minVal}, #{dateFormat}, "
            + Fields.AUDIT_VALUE;


    String UPDATE_FIELDS = " table_name = #{tableName}, name = #{name}, small_name = #{smallName}, big_name = #{bigName}, "
            + " cname = #{cname}, descp = #{descp}, db_type = #{dbType}, db_key = #{dbKey},"
            + " db_null = #{dbNull}, java_type = #{javaType}, html_type = #{htmlType},"
            + " required = #{required}, max_length = #{maxLength}, min_length = #{minLength}, "
            + " required_msg = #{requiredMsg}, max_val = #{maxVal}, min_val = #{minVal}, date_format = #{dateFormat}, "
            + Fields.AUDIT_UPDATE;

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE table_name = #{tableName}")
    List<ColumnInfo> findByTableName(@Param("tableName") String tableName);

    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id ", keyProperty = "id", resultType = Long.class)
    void save(ColumnInfo columnInfo);

    @Update("UPDATE " + TABLE_NAME + " SET " + UPDATE_FIELDS + " WHERE id = #{id} ")
    void update(ColumnInfo columnInfo);

}
