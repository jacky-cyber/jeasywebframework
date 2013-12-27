package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.Fields;
import com.jeasywebframework.domain.dept.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
public interface RoleDao {

    String TABLE_NAME = " sys_dept_role ";

    String ALL_FIELDS = Fields.ID_SELECT
            + Fields.NAME_SELECT
            + Fields.CODE_SELECT
            + Fields.ENABLED_SELECT
            + Fields.DESCP_SELECT
            + Fields.ORDER_SELECT
            + Fields.AUDIT_SELECT;

    String INSERT_FIELDS = Fields.NAME_INSERT
            + Fields.CODE_INSERT
            + Fields.ENABLED_INSERT
            + Fields.DESCP_INSERT
            + Fields.ORDER_INSERT
            + Fields.AUDIT_INSERT;

    String VALUE_FIELDS = Fields.NAME_VALUE
            + Fields.CODE_VALUE
            + Fields.ENABLED_VALUE
            + Fields.DESCP_VALUE
            + Fields.ORDER_VALUE
            + Fields.AUDIT_VALUE;

    @Select("SELECT COUNT(id) FROM " + TABLE_NAME)
    Long countAll();

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " LIMIT #{offset}, #{limit} ")
    List<Role> findAll(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT" + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE id = #{id} ")
    Role findOne(Long id);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE id = #{id} ")
    void delete(Long id);

    @Insert("INSERT INTO" + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id ", keyProperty = "id", resultType = Long.class)
    void save(Role role);


}
