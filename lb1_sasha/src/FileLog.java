public class FileLog implements LogInterface {
    private String f;

    public FileLog(String fileName) {
        f = fileName;
    }

    @Override
    public void writeError(String text) {
        Main.writeToFile(text, f);
    }
}
