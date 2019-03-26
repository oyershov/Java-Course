import java.util.List;

public class ParseLog {
    private List<LogInterface> errors;

    public ParseLog(List<LogInterface> errors) {
        this.errors = errors;
    }

    public Cinema cinemaParse(String str) {
    	Cinema cinema = null;
        try {
        	cinema = Main.cinemaParse(str);
        } catch (Exception e) {
            for (LogInterface li : errors) {
                li.writeError(e.getMessage());
            }
        }
        return cinema;
    }
}
