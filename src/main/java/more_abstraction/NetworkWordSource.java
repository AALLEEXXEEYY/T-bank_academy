package more_abstraction;

// Этот класс я создал для того, чтобы показать, что у игры может быть не только консольная реализация, а получения слов из сети или других ресурсов
public class NetworkWordSource implements WordSource {
    @Override
    public String getRandomWord() {
        return "Word";
    }
}
