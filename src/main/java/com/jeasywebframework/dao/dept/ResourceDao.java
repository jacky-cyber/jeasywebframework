package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.Fields;
import com.jeasywebframework.domain.dept.Resource;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-19.
 */
public interface ResourceDao {
    String TABLE_NAME = " sys_dept_resource ";


    String ALL_FIELDS = Fields.ID_SELECT
            + Fields.NAME_SELECT
            + Fields.CODE_SELECT
            + Fields.DESCP_SELECT
            + Fields.ORDER_SELECT
            + " type, url, method, java_method, ext1, ext2, ext3, ext4, ext5, "
            + Fields.TREENODE_SELECT
            + Fields.AUDIT_SELECT;

    String UPDATE_FIELDS = Fields.NAME_UPDATE
            + Fields.CODE_UPDATE
            + Fields.DESCP_UPDATE
            + Fields.ORDER_UPDATE
            + Fields.TREENODE_UPDATE
            + " type = #{type}, url = #{url}, method = #{method}, java_method = #{javaMethod}, ext1 = #{ext1}, ext2 = #{ext2}, ext3 = #{ext3}, ext4 = #{ext4}, ext5 = #{ext5}, "
            + Fields.AUDIT_UPDATE;

    String INSERT_FIELDS = Fields.NAME_INSERT
            + Fields.CODE_INSERT
            + Fields.DESCP_INSERT
            + Fields.ORDER_INSERT
            + " type, url, method, java_method, ext1, ext2, ext3, ext4, ext5, "
            + Fields.TREENODE_INSERT
            + Fields.AUDIT_INSERT;

    String VALUE_FIELDS = Fields.NAME_VALUE
            + Fields.CODE_VALUE
            + Fields.DESCP_VALUE
            + Fields.ORDER_VALUE
            + " #{type}, #{url}, #{method}, #{javaMethod}, #{ext1}, #{ext2}, #{ext3}, #{ext4}, #{ext5}, "
            + Fields.TREENODE_VALUE
            + Fields.AUDIT_VALUE;


    @Select("SELECT COUNT(id) FROM " + TABLE_NAME + " WHERE parent_id = #{parentId}")
    Long countByParentId(@Param("parentId") Long parentId);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " ORDER BY path ASC, oorder DESC ")
    List<Resource> findAll();


    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE id = #{id} ")
    Resource findOne(@Param("id") Long id);


    @Update("UPDATE " + TABLE_NAME + " SET " + UPDATE_FIELDS + " WHERE id = #{id} ")
    void update(Resource resource);

    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id ", keyProperty = "id", resultType = Long.class)
    void save(Resource resource);


}
