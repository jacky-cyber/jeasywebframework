package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.Global;
import com.jeasywebframework.domain.dept.Department;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
public interface DepartmentDao {

    String ALL_FIELDS = Global.SELECT_FIELD_ID
            + Global.SELECT_FIELD_NAME
            + Global.SELECT_FIELD_CODE
            + Global.SELECT_FIELD_DESCP
            + Global.SELECT_FIELD_ENABLED
            + Global.SELECT_FIELD_ORDER
            + Global.SELECT_FIELD_AUDIT;


    String UPDATE_FIELDS = Global.UPDATE_FIELD_NAME
            + Global.UPDATE_FIELD_CODE
            + Global.UPDATE_FIELD_DESCP
            + Global.UPDATE_FIELD_ENABLED
            + Global.UPDATE_FIELD_ORDER
            + Global.UPDATE_FIELD_AUDIT;


    String VALUE_FIELDS = Global.VALUE_FIELD_NAME
            + Global.VALUE_FIELD_CODE
            + Global.VALUE_FIELD_DESCP
            + Global.VALUE_FIELD_ENABLED
            + Global.VALUE_FIELD_ORDER
            + Global.VALUE_FIELD_AUDIT;

    String INSERT_FIELDS = Global.INSERT_FIELD_NAME
            + Global.INSERT_FIELD_CODE
            + Global.INSERT_FIELD_DESCP
            + Global.INSERT_FIELD_ENABLED
            + Global.INSERT_FIELD_ORDER
            + Global.INSERT_FIELD_AUDIT;


    String TABLE_NAME = "sys_dept_department";


    @Select("SELECT COUNT(id) FROM " + TABLE_NAME + " WHERE  parent_id = #{parentId}")
    Long countByParentId(@Param("parentId") Long parentId);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE code = #{code}")
    Department findByCode(@Param("code") String code);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE id = #{id}")
    Department findOne(@Param("id") Long id);


    @Update("UPDATE " + TABLE_NAME + " SET " + UPDATE_FIELDS + " WHERE id = #{id}")
    void update(Department department);

    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    void save(Department department);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " ORDER BY path ASC, oorder DESC ")
    List<Department> findAll();


}
