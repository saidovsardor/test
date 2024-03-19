import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static Scanner scannerInt = new Scanner(System.in);
    private final static Scanner scannerStr = new Scanner(System.in);
    private static boolean islogin = false;

    public static void main(String[] args) {
        System.out.println("----------Saytimizga xush kelibsiz------------");


        while (true) {

            while (!islogin) {
                showRegistration();
                int auth = getInputAsInt("Choose => ");

                islogin = switch (auth) {
                    case 1 -> signIn();
                    case 2 -> signUp();
                    default -> false;
                };

            }
        }
    }

    private static boolean signUp() {
        System.out.println("---------------Sign Up ------------------");
        String name = getInputAsString("Name : ");
        int age = getInputAsInt("Age : ");
        String password = getInputAsString("Password : ");

        User user = new User(name, age, password);

//        List<User> users = List.of(
//                new User(name, age, password)
//
//        );

        try (
                FileOutputStream fileOutputStream = new FileOutputStream("database/info.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Siz muvaffaqiyatli registratsiya qildingiz!");

        return false;
    }

    @SuppressWarnings("unchecked")
    private static boolean signIn() {
            System.out.println("---------------Sign In ------------------");
            String name = getInputAsString("Name : ");
            String password = getInputAsString("Password : ");

        try (
                FileInputStream fileInputStream = new FileInputStream("database/info.txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {

            User user = (User) objectInputStream.readObject();

//            List<User> users = (List<User>) objectInputStream.readObject();

            if(name.equals(user.getName()) && password.equals(user.getPassword()))
                System.out.println("User muvaffaqiyatli Kirdi.");
            else
                System.out.println("Name or password valid!");


        } catch (Exception e) {
            e.printStackTrace();
        }

            return false;
    }


    public static void showRegistration() {
        System.out.println("\nRegistration");
        System.out.println("1 ==> signIn");
        System.out.println("2 ==> signUP");
    }

    private static int getInputAsInt(String message) {
        System.out.print(message);
        return scannerInt.nextInt();
    }

    private static String getInputAsString(String message) {
        System.out.print(message);
        return scannerStr.nextLine();
    }

}
