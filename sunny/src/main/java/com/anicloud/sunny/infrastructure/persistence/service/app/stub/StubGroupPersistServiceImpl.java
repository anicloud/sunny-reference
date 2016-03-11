package com.anicloud.sunny.infrastructure.persistence.service.app.stub;

import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubGroupDao;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-9
 * @since JDK 1.7
 */
@Component
public class StubGroupPersistServiceImpl implements StubGroupPersistService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public StubGroupDao save(StubGroupDao groupDao) {
        if (groupDao != null) {
            em.persist(groupDao);
            return groupDao;
        }
        return null;
    }

    @Override
    public StubGroupDao update(StubGroupDao groupDao) {
        if (groupDao != null) {
            em.merge(groupDao);
            return groupDao;
        }
        return null;
    }

    @Override
    public void delete(Integer groupId) {
        String jpql = "select stubgroup from StubGroupDao stubgroup where groupId=:id";
        TypedQuery<StubGroupDao> query = em.createQuery(jpql, StubGroupDao.class);
        query.setParameter("id", groupId);
        StubGroupDao stubGroupDao=null;
        if(query.getMaxResults()>0){
            stubGroupDao=query.getSingleResult();
            em.remove(stubGroupDao);
        }else{
            throw new RuntimeException("groupId not exsits");
        }
    }

    @Override
    public List<StubGroupDao> findAll() {
        String jpql = "select stubgroup from StubGroupDao stubgroup";
        TypedQuery<StubGroupDao> query = em.createQuery(jpql, StubGroupDao.class);
        return query.getMaxResults()>0 ?query.getResultList():null;
    }

    @Override
    public StubGroupDao findById(Integer groupId) {
        String jpql = "select stubgroup from StubGroupDao stubgroup where groupId=:id";
        TypedQuery<StubGroupDao> query = em.createQuery(jpql, StubGroupDao.class);
        query.setParameter("id", groupId);
        return query.getMaxResults()>0 ?query.getSingleResult():null;
    }

    @Override
    public StubGroupDao findByName(String name) {
        String jpql = "select stubgroup from StubGroupDao stubgroup where name=:gname";
        TypedQuery<StubGroupDao> query = em.createQuery(jpql, StubGroupDao.class);
        query.setParameter("gname", name);
        return query.getMaxResults() == 1 ? query.getSingleResult() : null;
    }

    @Override
    public List<StubGroupDao> findByGroupIdIn(Collection<Integer> groupIds) {
        String jpql = "select stubgroup from StubGroupDao stubgroup where stubgroup.groupId IN:gId";
        TypedQuery<StubGroupDao> query = em.createQuery(jpql, StubGroupDao.class);
        query.setParameter("gId", groupIds);
        return query.getMaxResults()>0 ?query.getResultList():null;
    }
}
