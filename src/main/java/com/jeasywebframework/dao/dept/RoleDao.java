package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.Global;
import com.jeasywebframework.domain.dept.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
public interface RoleDao {

    String TABLE_NAME = " sys_dept_role ";

    String ALL_FIELDS = "";
    String INSERT_FIELDS = "";
    String VALUE_FIELDS = "";

    @Select("SELECT COUNT(id) FROM " + TABLE_NAME)
    Long countAll();

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " LIMIT #{offset}, #{limit} ")
    List<Role> findAll(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT" + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE id = #{id} ")
    Role findOne(Long id);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE id = #{id} ")
    void delete(Long id);

    @Insert("INSERT INTO" + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    void save(Role role);


}
