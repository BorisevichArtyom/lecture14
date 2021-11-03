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
        User user = User.builder().email("griiib@tut.by")
                .password("Bicep").firstName("Denis")
                .lastName("Samusenko").balanceAmount(new BigDecimal("123.44"))
                .role(Role.ATHLETE).build();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserDAOImpl daoImpl = context.getBean(UserDAOImpl.class);

//        daoImpl.addUser(user);
////        daoImpl.deleteUser(user);
//        daoImpl.addUser(user);

//        user.setFirstName("Aleks");
//        System.out.println(daoImpl.getUserById(1));
//        daoImpl.updateUser(user);

        System.out.println(daoImpl.getAllUsersPagination(3, 1));

        System.out.println(daoImpl.getUserById(4));

//        Training training = Training.builder().id(10).date(LocalDateTime.now()).userID(1).build();
//        System.out.println(training);
//        TrainingDAOImpl trainingDAOImpl = context.getBean(TrainingDAOImpl.class);
//        trainingDAOImplem.addTraining(training);
//         trainingDAOImplem.deleteTraining(training);
//        training.setDate(LocalDateTime.of(2014, 9, 19, 14, 5, 20));
//        trainingDAOImpl.updateTraining(training);
//        System.out.println(trainingDAOImpl.getAllTrainingsPagination(2,1));
//             Main runner= context.getBean(Main.class);
//             runner.executeSmth(2);
    }
}
