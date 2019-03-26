import java.util.List;

public class ParseLog {
    private List<LogInterface> errors;

    public ParseLog(List<LogInterface> errors) {
        this.errors = errors;
    }

    public FlatForSale ffsParse(String str) {
    	FlatForSale ffs = null;
        try {
            ffs = Main.ffsParse(str);
        } catch (Exception e) {
            for (LogInterface li : errors) {
                li.writeError(e.getMessage());
            }
        }
        return ffs;
    }
}
