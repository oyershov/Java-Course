public class ConsoleLog implements LogInterface {
    @Override
    public void writeError(String text) {
        System.out.println(text);
    }
}
