/**
 *
 *  @author Górniak Mateusz S17463
 *
 */

package zad2;

import java.beans.*;

public class AccountLimitator implements VetoableChangeListener {

    private int limit;

    public AccountLimitator(int l){
        setLimit(l);
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if ( limit > ((double) evt.getNewValue())){
            StringBuilder errorString = new StringBuilder(evt.getPropertyName());
            errorString.append(": Unacceptable value change: ");
            errorString.append(evt.getNewValue());
            throw  new PropertyVetoException(errorString.toString(), evt);
        }
    }

    private void setLimit(int i){
       limit = i;
    }
}
