package com.anicloud.sunny.infrastructure.persistence.service.app.stub;

import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubDao;
import com.anicloud.sunny.infrastructure.persistence.domain.app.stub.StubGroupDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Component
public class StubPersistServiceImpl implements StubPersistService {
    private final static Logger LOGGER = LoggerFactory.getLogger(StubPersistServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Resource
    private StubGroupPersistService stubGroupPersistService;

    @Override
    public List<StubDao> findAll() {
        String jpql = "select stub from StubDao stub";
        TypedQuery<StubDao> query = em.createQuery(jpql, StubDao.class);
        List<StubDao> stubDaoList = null;
        return query.getMaxResults() > 0 ? query.getResultList() : null;
    }

    @Override
    public StubDao findById(Long stubId) {
        String jpql = "select stub from StubDao stub where stubId=?id";
        TypedQuery<StubDao> query = em.createQuery(jpql, StubDao.class);
        query.setParameter("id", stubId);
        return query.getMaxResults()>0?query.getSingleResult():null;
    }

    @Override
    public StubDao save(StubDao stubDao) {
        if (stubDao != null) {
            StubGroupDao stubGroupDao = stubGroupPersistService.findById(stubDao.group.groupId);
            if (stubGroupDao == null) {
                stubGroupDao = stubGroupPersistService.save(stubGroupDao);
            } else {
                stubDao.group = stubGroupDao;
            }
            em.persist(stubDao);
            return stubDao;
        }
        return null;
    }

    @Override
    public void delete(Long stubId) {
        String jpql = "select stub from StubDao stub where stubId=?id";
        TypedQuery<StubDao> query = em.createQuery(jpql, StubDao.class);
        query.setParameter("id", stubId);
        StubDao stubDao = null;
        if (query.getMaxResults() > 0) {
            stubDao = query.getSingleResult();
            em.remove(stubDao);
        } else {
            throw new RuntimeException("stubId not exsits.");
        }
    }

    @Override
    public StubDao update(StubDao stubDao) {
        if (stubDao != null) {
            em.merge(stubDao);
            return stubDao;
        }
        return null;
    }
}
