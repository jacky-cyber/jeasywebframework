package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.Fields;
import com.jeasywebframework.domain.dept.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
public interface UserDao {

    String TABLE_NAME = " sys_dept_user ";

    String ALL_FIELDS = Fields.ID_SELECT
            + " username, password, salt, birthday, department_id AS departmentId, enabled, address, oorder, "
            + " msn, qq, sex, postcode, name, mobile, home_phone AS homePhone, office_phone AS officePhone, "
            + " email, id_card AS idCard, descp, "
            + Fields.AUDIT_SELECT;

    String INSERT_FIELDS = " username, password, salt, birthday, department_id, enabled, address, oorder, "
            + " msn, qq, sex, postcode, name, mobile, home_phone, office_phone, "
            + " email, id_card, descp, "
            + Fields.AUDIT_INSERT;

    String VALUE_FIELDS = " #{username}, #{password}, #{salt}, #{birthday}, #{departmentId}, #{enabled}, #{address}, #{oorder}, "
            + " #{msn}, #{qq}, #{sex}, #{postcode}, #{name}, #{mobile}, #{homePhone}, #{officePhone}, "
            + " #{email}, #{idCard}, #{descp}, "
            + Fields.AUDIT_VALUE;

    String UPDATE_FIELDS = "  username = #{username}, password = #{password}, salt = #{salt}, birthday = #{birthday}, "
            + " department_id = #{departmentId}, enabled = #{enabled}, address = #{address}, oorder = #{oorder}, "
            + " msn = #{msn}, qq = #{qq}, sex = #{sex}, postcode = #{postcode}, name = #{name},"
            + " mobile = #{mobile}, home_phone = #{homePhone}, office_phone = #{officePhone}, "
            + " email = #{email}, id_card = #{idCard}, descp = #{descp}, "
            + Fields.AUDIT_UPDATE;


    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE username = #{username} ")
    User findByUsername(@Param("username") String username);

    @Delete("DELETE FROM " + TABLE_NAME + " WHERE id = #{id}")
    void delete(@Param("id") Long id);

    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id ", keyProperty = "id", resultType = Long.class)
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
