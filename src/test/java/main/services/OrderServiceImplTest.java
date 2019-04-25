package main.services;

import main.controllers.AppConfig;
import main.dao.OrderDBHandler;
import main.entity.Order;
import main.entity.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Mock
    OrderDBHandler orderDBHandler;

    @Test
    public void create() throws SQLException {
        Order orderBefore = new Order("AnnaKarenina", Status.NEW);
        Mockito.doReturn(orderBefore).when(orderDBHandler).save(orderBefore);
        Order orderAfter = orderService.create(orderBefore);
        Assert.assertEquals(true, orderAfter.getId() > 0);
        Assert.assertEquals(orderBefore.getDescription(), orderAfter.getDescription());
    }

    @Test
    public void updateStatusByID() {
        long id = 1;
        int updatedLines = 1;
        Mockito.doReturn(updatedLines).when(orderDBHandler).updateStatusByID(Status.IN_ACTION, id);
        int updatedLinesReal = orderService.updateStatusByID(Status.IN_ACTION, id);
        Assert.assertEquals(updatedLines, updatedLinesReal);
    }

    @Test
    public void delete() {
        Order orderBefore = new Order("AnnaKarenina", Status.NEW);
        Mockito.doNothing().when(orderDBHandler).delete(orderBefore);
        orderService.delete(orderBefore); //не нашла, что верифаить/ассертить. Так что просто проверяю, что не кинут никакой эксепшн
    }

    @Before
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
    }

}