package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {

   private final UserService userService;


   public MainApp(UserService userService) {
      this.userService = userService;
   }

   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      MainApp app = context.getBean(MainApp.class);
      app.runApplication();

      context.close();
   }

   private void runApplication() throws SQLException {
      Car car = new Car("Lada", 2007);
      Car car1 = new Car("BMW", 2010);
      Car car2 = new Car("AUDI", 2009);
      Car car3 = new Car("MERS", 2005);

      User user = new User("User1", "Lastname1", "user5@mail.ru", car);
      userService.add(user);
      User user1 = new User("User2", "Lastname2", "user6@mail.ru", car1);
      userService.add(user1);
      User user2 = new User("User3", "Lastname3", "user7@mail.ru", car2);
      userService.add(user2);
      User user3 = new User("User4", "Lastname4", "user8@mail.ru", car3);

      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User userq : users) {
         System.out.println("Id = " + userq.getId());
         System.out.println("First Name = " + userq.getFirstName());
         System.out.println("Last Name = " + userq.getLastName());
         System.out.println("Email = " + userq.getEmail());
         System.out.println("Car Model: " + userq.getCar().getModel());
         System.out.println("Car Series: " + userq.getCar().getSeries());
         System.out.println();
      }
   }
}