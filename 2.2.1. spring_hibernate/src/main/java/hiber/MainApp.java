package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        Car car1 = new Car("ferrari", 1542);
        Car car2 = new Car("volvo", 5764);
        Car car3 = new Car("toyota", 597648);
        Car car4 = new Car("vaz", 2207);

        userService.add(user1.setUserCar(car1).setUser(user1));
        userService.add(user2.setUserCar(car2).setUser(user2));
        userService.add(user3.setUserCar(car3).setUser(user3));
        userService.add(user4.setUserCar(car4).setUser(user4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getUserCar());
        }
        System.out.println(userService.getUserByModelAndSeries("ferrari", 1542));

        context.close();
    }
}
