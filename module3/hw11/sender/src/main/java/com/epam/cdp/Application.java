package com.epam.cdp;

import com.epam.cdp.entity.Goods;
import com.epam.cdp.entity.Order;
import com.epam.cdp.entity.Product;
import com.epam.cdp.entity.User;

import javax.jms.JMSException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws JMSException, InterruptedException {
        MessageSender messageSender = new MessageSender();

        Scanner scanner = new Scanner(System.in);
        String command = "";

        User user;
        Product product;
        Order order;

        while (!command.equals("stop")) {
            user = new User();
            product = new Product();

            System.out.println("Enter your name: ");
            command = scanner.nextLine();
            user.setName(command);
            System.out.println("Enter your phone number: ");
            command = scanner.nextLine();
            user.setPhone(command);

            System.out.println("Enter name of goods: ");
            command = scanner.nextLine();
            product.setName(Goods.valueOf(command));
            System.out.println("Enter size of goods: ");
            command = scanner.nextLine();
            product.setSize(Integer.parseInt(command));
            System.out.println("Enter count of goods: ");
            command = scanner.nextLine();
            product.setOrderTotal(Integer.parseInt(command));

            order = new Order(user, product);

            messageSender.sendMessage(order.toString());

            System.out.println("Order sent");
        }

    }

}
