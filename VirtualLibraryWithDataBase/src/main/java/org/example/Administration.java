package org.example;

import org.example.AdminInterface.AdminService;
import org.example.BookInterface.BookService;
import org.example.ClassConfiguration.ApplicationConfig;
import org.example.Classes.Book;
import org.example.Classes.User;
import org.example.UserInterface.UserService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Administration {
    private final BookService bookService;
    private final UserService userService;
    private final AdminService adminService;

    public Administration(){
        this.bookService = ApplicationConfig.createBookService();
        this.userService = ApplicationConfig.createUserService();
        this.adminService = ApplicationConfig.createAdminService();
    }
    public void mainMenu(){
        System.out.println("Welcome to demo virtualLibrary with Database");
        System.out.println("Welcome to the main menu");
        System.out.println("Choose your option");
        System.out.println("(r)register | (l)login | (a)admin | (e)xit");
        Scanner s = new Scanner(System.in);
        char choice = s.next().charAt(0);
        s.nextLine();
        switch(choice){
            case 'l':
                System.out.println("Enter username");
                String userNameForLogin = s.nextLine();
                System.out.println("Enter password");
                String passwordForLogin = s.nextLine();
                User user = userService.logUser(userNameForLogin, passwordForLogin);
                if(user != null){
                    System.out.println("Login successful.");
                    userBookMenu(user);
                }
                else{
                    System.out.println("Wrong username or password");
                    return;
                }
                break;

            case 'r':
                System.out.println("Enter username");
                String userNameForRegistration = s.nextLine();
                System.out.println("Enter password");
                String passwordForRegistration = s.nextLine();

                if(!userService.checkPass(passwordForRegistration)){
                    System.out.println("Password must contains one digit , one letter and one special symbol");
                     return;
                }
                if(userService.checkIfUsernameExists(userNameForRegistration)){
                    System.out.println("Username already exists");
                    return;
                }
                userService.regUser(userNameForRegistration, passwordForRegistration, new BigDecimal(0));
                System.out.println("Registration successful.");
                mainMenu();
                break;
            case 'a':
                System.out.println("Welcome to admin panel!");
                System.out.println("Enter username");
                String adminUser = s.nextLine();
                System.out.println("Enter password");
                String adminPassword = s.nextLine();
                if(adminService.logAdmin(adminUser, adminPassword)){
                    adminBookMenu();
                }
                else{
                    System.out.println("Wrong adminUser or password");
                    mainMenu();
                }
                break;
            case 'e':
                System.exit(0);

        }

    }
    public void userBookMenu(User user){
        System.out.println("Welcome to the book menu");
        System.out.println("Choose your option");
        System.out.println(" (r)rent a book (t)return book | (d)deposit | (c)change password | (e)exit");
        Scanner s = new Scanner(System.in);
        char choice = s.next().charAt(0);
        s.nextLine();
        switch (choice){
            case 'r':
                System.out.println("Enter title: ");
                String title = s.nextLine();
                Book book = bookService.select(title);
                if(book != null){
                    if(userService.checkIfHaveEnoughBalance(book, user)){
                        bookService.rentBook(user.getUserName(), book.getSerialNumber());
                        BigDecimal newBalance = user.getBalance().subtract(book.getPriceForRent());
                        userService.updateBalance(user.getUserName(), newBalance);
                        System.out.println("Rent success!");
                    }
                    else{
                        System.out.println("You don't have enough money");
                    }
                }
                else{
                    System.out.println("We don't have this book at the moment");

                }
                userBookMenu(user);
                break;
            case 'd':
                System.out.println("Enter amount for deposit: ");
                double sum = s.nextDouble();
                if(sum <= 0){
                    System.out.println("Invalid sum to deposit");
                    return;
                }
                BigDecimal depositSum = BigDecimal.valueOf(sum);
                userService.updateBalance(user.getUserName(), depositSum);
                System.out.println("Deposit successful");
                userBookMenu(user);
                break;

            case 'c':
                System.out.println("Enter your new password");
                String newPassword = s.nextLine();
                System.out.println("Re-enter new password");
                String secondPasswordType = s.nextLine();

                if(newPassword.equals(secondPasswordType) && userService.checkPass(newPassword)){
                    userService.changePassword(user.getUserName(), user.getPassword(), newPassword);
                    System.out.println("Password change successful");
                    userBookMenu(user);
                }
                else{
                    System.out.println("You are re-entering wrong password");
                    return;
                }
                break;
            case 't':
                System.out.println("Enter title of book: ");
                String titleToReturn = s.nextLine();
                Book bookToReturn = bookService.select(titleToReturn);
                if(bookService.exists(bookToReturn.getSerialNumber())){
                    bookService.returnBook(user.getUserName(), bookToReturn.getSerialNumber());
                    System.out.println("You returned the book successfully");
                    userBookMenu(user);
                }
                else{
                    System.out.println("You didn't take this book.");
                    return;
                }

            case 'e':
                System.exit(0);
        }
    }


    public void adminBookMenu(){
        System.out.println("Welcome to the admin menu");
        System.out.println("Choose your option");
        System.out.println(" (s)save book | (d)delete book | (c)change content | (t)select book | (e)exit to main menu");
        Scanner s = new Scanner(System.in);
        char choice = s.next().charAt(0);
        s.nextLine();
        switch(choice){
            case 's':
                System.out.println("Enter name of the book: ");
                String name  = s.nextLine();
                System.out.println("Enter author of the book: ");
                String author = s.nextLine();
                System.out.println("Enter serialNumber of the book: ");
                String serialNumber = s.nextLine();
                System.out.println("Enter the price for rent:");
                BigDecimal priceForRent = s.nextBigDecimal();
                if(bookService.exists(serialNumber)){
                    System.out.println("This book is already saved in the Database!");
                    adminBookMenu();
                }
                Book book = new Book(name, author, serialNumber, priceForRent);
                book.setRented(false);
                adminService.saveBook(book);
                System.out.println("The book is saved");
                adminBookMenu();
                break;
            case 'd':
                System.out.println("Enter serialNumber of the book: ");
                String serialNumberForDelete = s.nextLine();
                if(bookService.exists(serialNumberForDelete)){
                    adminService.delete(serialNumberForDelete);
                    System.out.println("The book is deleted");
                }
                else{
                    System.out.println("Invalid serial number");
                    return;

                }
                adminBookMenu();
                break;

            case 'c':
                System.out.println("Enter serialNumber of the book: ");
                String serialNumberOfBook = s.nextLine();
                System.out.println("Enter the column of the book: ");
                String column  = s.nextLine();
                System.out.println("Enter the newValue of the book: ");
                String newValue  = s.nextLine();
                if(bookService.exists(serialNumberOfBook)){
                    adminService.changeContent(serialNumberOfBook, column, newValue);
                    System.out.println("Content changed");
                }
                else{
                    System.out.println("Invalid serial number");
                    return;
                }
                adminBookMenu();
                break;
            case 'e':
                mainMenu();
                break;

        }
    }
}
