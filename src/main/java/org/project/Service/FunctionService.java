package org.project.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Dao.GenericDao;
import org.project.Model.Service;
import org.project.Util.JPAUtility;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

@Transactional
@org.springframework.stereotype.Service
public class FunctionService implements IFunctionService {

    private GenericDao<Service> genericDao;

    private Logger log;

    private JPAUtility jpaUtility;


    @Autowired
    public FunctionService(GenericDao<Service> genericDao) {
        this.genericDao = genericDao;
        log = LogManager.getLogger(FunctionService.class);
    }

    @Autowired
    public void setJpaUtility(JPAUtility jpaUtility) {
        this.jpaUtility = jpaUtility;
    }

    @Override
    public Service changeServicePrice(Service service, double price) {
        service.setPrice(price);
        genericDao.update(service);
        log.info(" Цена на услугу " + service.getName() + " составляет  " + price);
        return service;
    }

    @Override
    public Service addService(Service service) {
        genericDao.add(service);
        log.info("Добавлена новая услуга" + service.getName());
        return service;
    }

    @Override
    public List<Service> sortServicePrice() {
        List<Service> list = null;
        EntityManager em = jpaUtility.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Service> query = cb.createQuery(Service.class);
            Root<Service> root = query.from(Service.class);
            query.select(root);
            query.orderBy(cb.asc(root.get("price")));
            list = em.createQuery(query).getResultList();
            Stream<Service> stream = list.stream();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return list;
    }

    @Override
    public Service getService(int index) {
        return genericDao.find(index);
    }

}
