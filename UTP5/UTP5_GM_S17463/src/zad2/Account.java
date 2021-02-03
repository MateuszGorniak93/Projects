/**
 *
 *  @author Górniak Mateusz S17463
 *
 */

package zad2;

import java.beans.*;
import java.io.Serializable;

public class Account implements Serializable {
    private double balance;
    private PropertyChangeSupport changeWatch;
    private VetoableChangeSupport vetoWatch;
    private String name;

    public synchronized void addPropertyWatch(PropertyChangeListener propertyWatch) {
        if (this.changeWatch != null && propertyWatch != null)
            this.changeWatch.addPropertyChangeListener(propertyWatch);
    }

    public synchronized void addVetoWatch(VetoableChangeListener veto) {
        if (vetoWatch != null && veto != null)
            vetoWatch.addVetoableChangeListener(veto);
    }

    public Account() {
        changeWatch = new PropertyChangeSupport(this);
        vetoWatch = new VetoableChangeSupport(this);
        setBalance(0);
        setName("");
    }

    public Account(double balance) {
        setBalance(balance);
        setName("");
        changeWatch = new PropertyChangeSupport(this);
        vetoWatch = new VetoableChangeSupport(this);
    }


    private void setBalance(double balance){
        this.balance = balance;
    }
    public void setName(String name) { this.name = name; }

    public synchronized void withdraw(double value) throws PropertyVetoException {
        vetoWatch.fireVetoableChange(name, 0, balance-value);
        changeWatch.firePropertyChange(name, balance, balance-value);
        setBalance(balance - value);
    }

    public synchronized void deposit(double value){
        changeWatch.firePropertyChange(name, balance, balance+value);
        setBalance(balance + value);
    }

    public synchronized void transfer(Account acc, double value) throws PropertyVetoException {
        vetoWatch.fireVetoableChange(name, 0, balance-value);
        acc.deposit(value);
        withdraw(value);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Acc ");
        sb.append(name);
        sb.append(": ");
        sb.append(balance);
        return sb.toString();
    }


}
