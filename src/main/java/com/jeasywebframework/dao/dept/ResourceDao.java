package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.Global;
import com.jeasywebframework.domain.dept.Resource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-19.
 */
public interface ResourceDao {
    String TABLE_NAME = " sys_dept_resource ";


    String ALL_FIELDS = Global.SELECT_FIELD_ID
            + Global.SELECT_FIELD_NAME
            + Global.SELECT_FIELD_CODE
            + Global.SELECT_FIELD_DESCP
            + Global.SELECT_FIELD_ORDER
            + " type, url, method, java_method, ext1, ext2, ext3, ext4, ext5, "
            + Global.SELECT_FIELD_TREENODE
            + Global.SELECT_FIELD_AUDIT;

    String UPDATE_FIELDS = "";
    String INSERT_FIELDS = "";
    String VALUE_FIELDS = "";


    @Select("SELECT COUNT(id) FROM " + TABLE_NAME + " WHERE parent_id = #{parentId}")
    Long countByParentId(@Param("parentId") Long parentId);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " ORDER BY path ASC, oorder DESC ")
    List<Resource> findAll();


    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE id = #{id} ")
    Resource findOne(@Param("id") Long id);


    @Update("UPDATE " + TABLE_NAME + " SET " + UPDATE_FIELDS + " WHERE id = #{id} ")
    void update(Resource resource);

    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    void save(Resource resource);


}
