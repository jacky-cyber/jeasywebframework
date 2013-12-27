package com.jeasywebframework.dao.dev;

import com.jeasywebframework.domain.Fields;
import com.jeasywebframework.domain.dev.Tracker;
import org.apache.ibatis.annotations.*;


import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-26.
 */
public interface TrackerDao {
    String TABLE_NAME = " sys_dev_tracker ";

    String ALL_FIELDS = Fields.ID_SELECT
            + " start_time AS startTime, end_time AS endTime, "
            + " thread_name AS threadName, ip, tag, descp, "
            + Fields.TREENODE_SELECT
            + Fields.AUDIT_SELECT;

    String INSERT_FIELDS = " start_time, end_time, "
            + " thread_name, ip, tag, descp, "
            + Fields.TREENODE_INSERT
            + Fields.AUDIT_INSERT;

    String VALUE_FIELDS = " #{startTime}, #{endTime}, "
            + " #{threadName}, #{ip}, #{tag}, #{descp}, "
            + Fields.TREENODE_VALUE
            + Fields.AUDIT_VALUE;

    String UPDATE_FIELDS = " start_time = #{startTime}, end_time = #{endTime}, "
            + " thread_name = #{threadName}, ip = #{ip}, tag = #{tag}, descp = #{descp}, "
            + Fields.TREENODE_UPDATE
            + Fields.AUDIT_UPDATE;

    @Select("SELECT COUNT(id) FROM " + TABLE_NAME + " WHERE parent_id = #{parentId}")
    Long countByParentId(@Param("parentId") Long parentId);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE  parent_id = #{parentId}  LIMIT #{offset}, #{limit}")
    List<Tracker> findByParentId(@Param("parentId") Long parentId, @Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE  path LIKE #{path} ORDER BY start_time ASC ")
    List<Tracker> findByPathLike(@Param("path") String path);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE  id = #{id} ")
    Tracker findOne(@Param("id") Long id);

    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id ", keyProperty = "id", resultType = Long.class)
    void save(Tracker tracker);

    @Update("UPDATE " + TABLE_NAME + " SET " + UPDATE_FIELDS + " WHERE id = #{id} ")
    void update(Tracker tracker);


}
