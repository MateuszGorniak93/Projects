/**
 *
 *  @author Górniak Mateusz S17463
 *
 */

package zad2;

import java.beans.*;

public class Main {
    public static void main(String[] args)
    {
        Account acc1 = new Account(100);
        Account acc2 = new Account();


        acc2.addVetoWatch(new AccountLimitator(-200));
        acc1.addVetoWatch(new AccountLimitator(-200));
        acc2.addPropertyWatch(new AccountChange());
        acc1.addPropertyWatch(new AccountChange());

        acc2.setName("2");
        acc1.setName("1");

        try {
            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println();

            acc2.deposit(1000);
            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println();

            acc1.withdraw(250);
            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println();

            acc2.transfer(acc1, 1200);

            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println();

            acc2.transfer(acc1, 1);
            System.out.println(acc1);
            System.out.println(acc2);

        } catch (PropertyVetoException e) {
            System.out.println(e.getMessage());
            System.out.println(acc1);
            System.out.println(acc2);
        }
    }
}
