package by.itacademy.javaenterprise.tests;

import by.itacademy.javaenterprise.dao.impl.MuscleDAOImpl;
import by.itacademy.javaenterprise.dao.impl.UserDAOImpl;
import by.itacademy.javaenterprise.entity.Muscle;
import by.itacademy.javaenterprise.exception.DAOException;
import by.itacademy.javaenterprise.spring.SpringConfig;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import static org.junit.jupiter.api.Assertions.*;

public class TestMuscleDAOImpl {


    private static final Logger logger = LoggerFactory.getLogger(TestMuscleDAOImpl.class);

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    MuscleDAOImpl daoImpl = context.getBean(MuscleDAOImpl.class);
    Muscle musclee = Muscle.builder().muscleId(8).muscleName("Harmstrings").build();

    @Test
    public void testGetAllMusclesPagination() throws DAOException {
        assertNotNull(daoImpl.getAllMusclesPagination(1, 0));
        assertEquals(2, daoImpl.getAllMusclesPagination(2, 0).size());
        assertThrows(DAOException.class, () -> daoImpl.getAllMusclesPagination(0, 0));
    }


    @Test
    public void testGetMuscleById() throws DAOException {
        assertNotNull(daoImpl.getMuscleById(1));
        assertThrows(DAOException.class, () -> daoImpl.getMuscleById(0),
                "Cant find muscle by this id:0");
    }


    @Test
    public void testUpdateMuscle() throws DAOException {
        assertThrows(Exception.class, () -> {
            daoImpl.getMuscleById(musclee.getMuscleId());
        });
        daoImpl.addMuscle(musclee);
        assertEquals(musclee.getMuscleName(), daoImpl.getMuscleById(musclee.getMuscleId()).getMuscleName());
        musclee.setMuscleName("Lower back");
        daoImpl.updateMuscle(musclee, musclee.getMuscleId());
        assertEquals("Lower back", daoImpl.getMuscleById(musclee.getMuscleId()).getMuscleName());
        daoImpl.deleteMuscle(musclee.getMuscleName());
    }



    @Test
    public void testUpdateMuscleInvalid() {
        assertThrows(DAOException.class,
                () -> {
                    daoImpl.updateMuscle(new Muscle(), 6);
                });

    }


    @Test
    public void testAddMuscle() throws DAOException {
        assertThrows(Exception.class, () -> daoImpl.getMuscleById(musclee.getMuscleId()));
        daoImpl.addMuscle(musclee);
        assertEquals(musclee, daoImpl.getMuscleById(musclee.getMuscleId()));
        daoImpl.deleteMuscle(musclee.getMuscleName());

    }

    @Test
    public void testAddMuscleNull() {
        assertThrows(Exception.class, () -> daoImpl.addMuscle(new Muscle()));
    }

    @Test
    public void testAddMuscleConflict() throws DAOException {
        assertThrows(Exception.class, () -> daoImpl.getMuscleById(musclee.getMuscleId()));
        daoImpl.addMuscle(musclee);
        assertThrows(Exception.class, () -> daoImpl.addMuscle(musclee));
        daoImpl.deleteMuscle(musclee.getMuscleName());
    }


    @Test
    public void testDeleteMuscle() throws DAOException {
        assertThrows(Exception.class, () -> daoImpl.getMuscleById(musclee.getMuscleId()));
        daoImpl.addMuscle(musclee);
        assertEquals(musclee, daoImpl.getMuscleById(musclee.getMuscleId()));
        daoImpl.deleteMuscle(musclee.getMuscleName());
        assertThrows(Exception.class, () -> daoImpl.getMuscleById(musclee.getMuscleId()));
    }
}
