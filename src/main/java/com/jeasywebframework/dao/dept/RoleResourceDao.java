package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.Fields;
import com.jeasywebframework.domain.dept.RoleResource;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
public interface RoleResourceDao {
    String TABLE_NAME = " sys_dept_role_of_resource ";

    String ALL_FIELDS = Fields.ID_SELECT
            + " role_id AS roleId, resource_id AS resourceId, "
            + Fields.AUDIT_SELECT;

    String INSERT_FIELDS = " role_id, resource_id, "
            + Fields.AUDIT_INSERT;

    String VALUE_FIELDS = " #{roleId}, #{resourceId}, "
            + Fields.AUDIT_INSERT;

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE role_id = #{roleId} ")
    List<RoleResource> findByRoleId(@Param("roleId") Long roleId);


    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id ", keyProperty = "id", resultType = Long.class)
    void save(RoleResource roleResource);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE role_id = #{roleId}")
    void deleteByRoleId(@Param("roleId") Long roleId);

}
