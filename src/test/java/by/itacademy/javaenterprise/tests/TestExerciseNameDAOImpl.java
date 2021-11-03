package by.itacademy.javaenterprise.tests;

import by.itacademy.javaenterprise.dao.impl.ExerciseNameDAOImpl;
import by.itacademy.javaenterprise.dao.impl.MuscleDAOImpl;
import by.itacademy.javaenterprise.entity.ExerciseName;
import by.itacademy.javaenterprise.entity.Muscle;
import by.itacademy.javaenterprise.exception.DAOException;
import by.itacademy.javaenterprise.spring.SpringConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestExerciseNameDAOImpl {

    private static final Logger logger = LoggerFactory.getLogger(TestMuscleDAOImpl.class);


    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ExerciseNameDAOImpl daoImpl = context.getBean(ExerciseNameDAOImpl.class);

    ExerciseName exerciseNamee = ExerciseName.builder().exerciseNameId(8).exerciseName("Chin-ups").build();

    @Test
    public void testGetAllExerciseNamesPagination() throws DAOException {
        assertNotNull(daoImpl.getAllExerciseNamesPagination(1, 0));
        assertEquals(2, daoImpl.getAllExerciseNamesPagination(2, 0).size());
        assertThrows(DAOException.class, () -> daoImpl.getAllExerciseNamesPagination(0, 0));
    }


    @Test
    public void testGetExerciseNameById() throws DAOException {
        assertNotNull(daoImpl.getExerciseNameById(1));
        assertThrows(DAOException.class, () -> daoImpl.getExerciseNameById(0));
    }

    @Test
    public void testAddNameOfExercise() throws DAOException {
        assertThrows(Exception.class, () -> daoImpl.getExerciseNameById(exerciseNamee.getExerciseNameId()));
        daoImpl.addNameOfExercise(exerciseNamee);
        assertEquals(exerciseNamee.getExerciseName(), daoImpl.getExerciseNameById(exerciseNamee.getExerciseNameId()));
        daoImpl.deleteExerciseName(exerciseNamee.getExerciseNameId());

    }

    @Test
    public void testAddNameOfExerciseNull() {
        assertThrows(Exception.class, () -> daoImpl.addNameOfExercise(new ExerciseName()));
    }

    @Test
    public void testAddNameConflict() throws DAOException {
        assertThrows(Exception.class, () -> daoImpl.getExerciseNameById(exerciseNamee.getExerciseNameId()));
        daoImpl.addNameOfExercise(exerciseNamee);
        assertThrows(Exception.class, () -> daoImpl.addNameOfExercise(exerciseNamee));
        daoImpl.deleteExerciseName(exerciseNamee.getExerciseNameId());
    }


    @Test
    public void testUpdateExerciseName() throws DAOException {
        daoImpl.addNameOfExercise(exerciseNamee);
        assertEquals(exerciseNamee.getExerciseName(), daoImpl.getExerciseNameById(exerciseNamee.getExerciseNameId()));
        exerciseNamee.setExerciseName("Plank");
        daoImpl.updateExerciseName(exerciseNamee, exerciseNamee.getExerciseNameId());
        assertEquals("Plank", daoImpl.getExerciseNameById(exerciseNamee.getExerciseNameId()));
        daoImpl.deleteExerciseName(exerciseNamee.getExerciseNameId());
    }

    @Test
    public void testUpdateExerciseNameInvalid() throws DAOException {
        assertNotNull(daoImpl.getExerciseNameById(1));
        assertThrows(Exception.class, () -> {
            daoImpl.updateExerciseName(new ExerciseName(), 1);
        });
    }


    @Test
    public void testDeleteExerciseName() throws DAOException {
        assertThrows(Exception.class, () -> daoImpl.getExerciseNameById(exerciseNamee.getExerciseNameId()));
        daoImpl.addNameOfExercise(exerciseNamee);
        assertEquals(exerciseNamee.getExerciseName(), daoImpl.getExerciseNameById(exerciseNamee.getExerciseNameId()));
        daoImpl.deleteExerciseName(exerciseNamee.getExerciseNameId());
        assertThrows(Exception.class, () -> daoImpl.getExerciseNameById(exerciseNamee.getExerciseNameId()));
    }
}
