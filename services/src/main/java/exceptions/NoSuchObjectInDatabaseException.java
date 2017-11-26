package exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.lang.Nullable;

public class NoSuchObjectInDatabaseException extends DataAccessException {

    public NoSuchObjectInDatabaseException(String msg) {
        super(msg);
    }

    public NoSuchObjectInDatabaseException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }

}
