package com.jeasywebframework.dao.dev;

import com.jeasywebframework.domain.Fields;
import com.jeasywebframework.domain.dev.TableInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
public interface TableDao {

    String TABLE_NAME = " sys_dev_gen_table ";

    String ALL_FIELDS = Fields.ID_SELECT
            + " name, cname, descp,"
            + " domain_pkg AS domainPkg, domain_class AS domainClass, "
            + " dao_pkg AS daoPkg, dao_class AS daoClass, "
            + " controller_pkg AS controllerPkg, controller_class AS controllerClass, "
            + " url_prefix AS urlPrefix, velocity_path_prefix AS velocityPathPrefix,  "
            + Fields.AUDIT_SELECT;

    String INSERT_FIELDS = " name, cname, descp,  "
            + " domain_pkg, domain_class, "
            + " dao_pkg, dao_class, "
            + " controller_pkg, controller_class, "
            + " url_prefix, velocity_path_prefix,  "
            + Fields.AUDIT_INSERT;

    String VALUE_FIELDS = " #{name}, #{cname}, #{descp}, #{domainPkg}, "
            + " #{domainClass}, "
            + " #{daoPkg}, #{daoClass}, "
            + " #{controllerPkg}, #{controllerClass}, "
            + " #{urlPrefix}, #{velocityPathPrefix},  "
            + Fields.AUDIT_VALUE;

    String UPDATE_FIELDS = " name = #{name}, cname = #{cname}, descp = #{descp},"
            + " domain_pkg = #{domainPkg}, domain_class = #{domainClass}, "
            + " dao_pkg = #{daoPkg}, dao_class = #{daoClass}, "
            + " controller_pkg = #{controllerPkg}, controller_class =  #{controllerClass}, "
            + " url_prefix = #{urlPrefix}, velocity_path_prefix = #{velocityPathPrefix},  "
            + Fields.AUDIT_UPDATE;


    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME + " WHERE name = #{name}")
    TableInfo findByName(@Param("name") String name);

    @Select("SELECT " + ALL_FIELDS + " FROM " + TABLE_NAME)
    List<TableInfo> findAll();


    @Insert("INSERT INTO " + TABLE_NAME + "(" + INSERT_FIELDS + ")VALUES(" + VALUE_FIELDS + ")")
    @SelectKey(before = false, statement = "SELECT LAST_INSERT_ID() AS id ", keyProperty = "id", resultType = Long.class)
    void save(TableInfo tableInfo);

    @Update("UPDATE " + TABLE_NAME + " SET " + UPDATE_FIELDS + " WHERE id = #{id} ")
    void update(TableInfo tableInfo);


}
