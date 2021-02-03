/**
 *
 *  @author Górniak Mateusz S17463
 *
 */

package zad2;

import java.beans.*;

public class AccountChange implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        StringBuilder msg = new StringBuilder(evt.getPropertyName());
        msg.append(": Value changed from ");
        msg.append(evt.getOldValue());
        msg.append(" to ");
        msg.append(evt.getNewValue());
        if ((double) evt.getNewValue() <= 0){
            msg.append(", balance < 0!");
        }
        msg.append("\n");
        System.out.print(msg);
    }
}
