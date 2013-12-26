package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.dept.RoleResource;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
public interface RoleResourceDao {
    String TABLE_NAME = " sys_dept_role ";

    String ALL_FIELDS = "";
    String INSERT_FIELDS = "";
    String VALUE_FIELDS = "";

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE role_id = #{roleId} ")
    List<RoleResource> findByRoleId(@Param("roleId") Long roleId);


    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    void save(RoleResource roleResource);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE role_id = #{roleId}")
    void deleteByRoleId(@Param("roleId") Long roleId);

}
