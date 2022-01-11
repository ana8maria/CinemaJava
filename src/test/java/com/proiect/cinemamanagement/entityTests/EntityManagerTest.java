package com.proiect.cinemamanagement.entityTests;

import com.proiect.cinemamanagement.dao.Employee;
import com.proiect.cinemamanagement.dao.Room;
import com.proiect.cinemamanagement.entity.CardEntity;
import com.proiect.cinemamanagement.entity.EmployeeEntity;
import com.proiect.cinemamanagement.entity.MovieEntity;
import com.proiect.cinemamanagement.entity.RoomEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static junit.framework.TestCase.assertEquals;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Rollback(false)
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void findRoom(){
        RoomEntity roomFound= entityManager.find(RoomEntity.class, 1L);
        assertEquals(roomFound.getName(), "Sala 5");
    }

    @Test
    public void findCard(){
        CardEntity cardFound = entityManager.find(CardEntity.class, 1L);
        assertEquals(cardFound.getFirstName(), "Ioana");
    }

    @Test
    public void findMovie(){
        MovieEntity movieEntity = entityManager.find(MovieEntity.class, 1L);
        assertEquals(movieEntity.getName(), "Nasul");
    }

    @Test
    public void findEmployee(){
        EmployeeEntity employeeEntity = entityManager.find(EmployeeEntity.class, 1L);
        assertEquals(employeeEntity.getFirstName(), "Popescu");
    }




}
