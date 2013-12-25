package com.jeasywebframework.dao.dept.impl;

import com.jeasywebframework.dao.dept.SysDeptUserDaoPlus;
import com.jeasywebframework.domain.dept.SysDeptUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-19.
 */
public class SysDeptUserDaoImpl implements SysDeptUserDaoPlus {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Page<SysDeptUser> findByKeywordAndDeptId(Pageable pageable, String keyword, Long deptId) {

        String jsql = "select count(u.id) from SysDeptUser u where 1=1 ";
        String jsql2 = "select u from SysDeptUser u where 1= 1 ";

        if (StringUtils.isNotEmpty(keyword)) {
            jsql += " and ( username like :k or email like :k or name like :k or mobile like :k ) ";
            jsql2 += " and ( username like :k or email like :k or name like :k or mobile like :k ) ";
        }

        if (deptId > 0) {
            jsql += " and departmentId = :deptId ";
            jsql2 += " and departmentId = :deptId ";
        }

        Query query = entityManager.createQuery(jsql);
        Query query2 = entityManager.createQuery(jsql2);

        if (StringUtils.isNotEmpty(keyword)) {
            String kk = "%" + keyword + "%";
            query.setParameter("k", kk);
            query2.setParameter("k", kk);
        }


        if (deptId > 0) {
            query.setParameter("deptId", deptId);
            query2.setParameter("deptId", deptId);
        }

        Long count = (Long) query.getSingleResult();


        List<SysDeptUser> content = Collections.emptyList();
        if (count > 0L) {
            content = query2.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
        }

        return new PageImpl<SysDeptUser>(content, pageable, count);
    }


}
