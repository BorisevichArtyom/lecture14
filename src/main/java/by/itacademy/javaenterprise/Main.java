package by.itacademy.javaenterprise;

import by.itacademy.javaenterprise.command.Command;
import by.itacademy.javaenterprise.dao.impl.UserDAOImpl;
import by.itacademy.javaenterprise.entity.Role;
import by.itacademy.javaenterprise.entity.User;
import by.itacademy.javaenterprise.exception.DAOException;
import by.itacademy.javaenterprise.spring.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Main {
    private Command<Integer> qualifierExample;

    public Main(Command<Integer> qualifierExample) {
        this.qualifierExample = qualifierExample;
    }

    public void executeSmth(Integer number) throws DAOException {
        qualifierExample.execute(number);
    }

    public static void main(String[] args) throws DAOException {

    }
}
