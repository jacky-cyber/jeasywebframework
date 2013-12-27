package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.Fields;
import com.jeasywebframework.domain.dept.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
public interface DepartmentDao {


    String TABLE_NAME = "sys_dept_department";


    String ALL_FIELDS = Fields.ID_SELECT
            + Fields.NAME_SELECT
            + Fields.CODE_SELECT
            + Fields.DESCP_SELECT
            + Fields.ENABLED_SELECT
            + Fields.ORDER_SELECT
            + Fields.TREENODE_SELECT
            + Fields.AUDIT_SELECT;


    String UPDATE_FIELDS = Fields.NAME_UPDATE
            + Fields.CODE_UPDATE
            + Fields.DESCP_UPDATE
            + Fields.ENABLED_UPDATE
            + Fields.ORDER_UPDATE
            + Fields.TREENODE_UPDATE
            + Fields.AUDIT_UPDATE;


    String VALUE_FIELDS = Fields.NAME_VALUE
            + Fields.CODE_VALUE
            + Fields.DESCP_VALUE
            + Fields.ENABLED_VALUE
            + Fields.ORDER_VALUE
            + Fields.TREENODE_VALUE
            + Fields.AUDIT_VALUE;

    String INSERT_FIELDS = Fields.NAME_INSERT
            + Fields.CODE_INSERT
            + Fields.DESCP_INSERT
            + Fields.ENABLED_INSERT
            + Fields.ORDER_INSERT
            + Fields.TREENODE_INSERT
            + Fields.AUDIT_INSERT;


    @Select("SELECT COUNT(id) FROM " + TABLE_NAME + " WHERE  parent_id = #{parentId}")
    Long countByParentId(@Param("parentId") Long parentId);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE code = #{code}")
    Department findByCode(@Param("code") String code);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE id = #{id}")
    Department findOne(@Param("id") Long id);


    @Update("UPDATE " + TABLE_NAME + " SET " + UPDATE_FIELDS + " WHERE id = #{id}")
    void update(Department department);

    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id ", keyProperty = "id", resultType = Long.class)
    void save(Department department);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " ORDER BY path ASC, oorder DESC ")
    List<Department> findAll();


}
