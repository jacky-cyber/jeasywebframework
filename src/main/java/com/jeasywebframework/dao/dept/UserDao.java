package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.dept.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
public interface UserDao {

    String TABLE_NAME = " sys_dept_user ";

    String ALL_FIELDS = "";
    String INSERT_FIELDS = "";
    String VALUE_FIELDS = "";
    String UPDATE_FIELDS = "";


    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE username = #{username} ")
    User findByUsername(@Param("username") String username);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE id = #{id}")
    void delete(@Param("id") Long id);

    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    void save(User user);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE id = #{id} ")
    User findOne(@Param("id") Long id);

    @Update("UPDATE " + TABLE_NAME + " SET " + UPDATE_FIELDS + " WHERE id = #{id} ")
    void update(User user);

    @Select("SELECT COUNT(id) FROM " + TABLE_NAME + " WHERE " +
            " (username LIKE #{keyword} OR name LIKE #{keyword} OR email LIKE #{keyword}) " +
            " AND department_id = #{deptId} ")
    Long countByKeywordAndDeptId(@Param("keyword") String keyword, @Param("deptId") Long deptId);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE " +
            " (username LIKE #{keyword} OR name LIKE #{keyword} OR email LIKE #{keyword}) " +
            " AND department_id = #{deptId} " +
            " LIMIT #{offset}, #{limit}")
    List<User> findByKeywordAndDeptId(@Param("keyword") String keyword, @Param("deptId") Long deptId, @Param("offset") int offset, @Param("limit") int limit);


}
