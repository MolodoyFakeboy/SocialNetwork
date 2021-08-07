package org.project.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.project.Configs.ConfigTest;
import org.project.Dao.ServiceDao;
import org.project.Model.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigTest.class)
class FunctionServiceTest {

    @Mock
    private ServiceDao serviceDao;

    private FunctionService functionService;

    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        functionService = new FunctionService(serviceDao);
        Mockito.when(serviceDao.getEntityManager()).thenReturn(entityManager);
    }

    @Test
    void testChangeServicePrice() {
        Service service1 = new Service("Напитки в мини бар", 800, "Принесем  для вас самые вкусные напитки");
        Mockito.when(serviceDao.update(service1)).thenReturn(service1);
        functionService.changeServicePrice(service1, 500);
        Mockito.verify(serviceDao).update(service1);
    }

    @Test
    void testAddService() {
        Service service1 = new Service("Напитки в мини бар", 800, "Принесем  для вас самые вкусные напитки");
        functionService.addService(service1);
        Mockito.verify(serviceDao).add(service1);
        Mockito.verify(serviceDao, Mockito.times(1)).add(service1);
    }

    @Test
    void getService() {
        Service service1 = new Service("Напитки в мини бар", 800, "Принесем  для вас самые вкусные напитки");
        service1.setIdService(9);
        Mockito.when(serviceDao.find(service1.getIdService())).thenReturn(service1);
        functionService.getService(service1.getIdService());
        Mockito.verify(serviceDao).find(service1.getIdService());
    }

    @Test
    void testSortServicePrice() {
        List<Service> services = functionService.sortServicePrice();
        assertNotNull(services);
        Stream<Service> serviceStream = services.stream();
        serviceStream.forEach(System.out::println);
    }

}