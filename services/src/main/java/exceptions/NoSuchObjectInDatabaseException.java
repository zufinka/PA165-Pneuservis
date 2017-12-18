package exceptions;

import org.springframework.dao.DataAccessException;

public class NoSuchObjectInDatabaseException extends DataAccessException {

    public NoSuchObjectInDatabaseException(String msg) {
        super(msg);
    }

    public NoSuchObjectInDatabaseException( String msg, Throwable cause) {
        super(msg, cause);
    }

}
