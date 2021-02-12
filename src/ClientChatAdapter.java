
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.function.Consumer;

public class ClientChatAdapter {
    private ChatFrame chatFrame;
    private Client client;

    public ClientChatAdapter(String host, int port) {
        client = new Client(host, port);
        chatFrame = new ChatFrame(new Consumer<String>() {
            @Override
            public void accept(String messageFromFormSubmitListener) {
                client.sendMessage(messageFromFormSubmitListener);
            }
        });
        StringBuilder history = loadHistory(100);
        chatFrame.setTextArea(String.valueOf(history));
        read();
    }

    private void read() {
        new Thread(() -> {
            try {
                while (true) {
                    chatFrame.append(
                            client.receiveMessage()
                    );
                }
            } catch (ClientConnectionException e) {
                throw e;
            } finally {
                if (client != null) {
                    client.close();
                }
            }
        }).start();
    }

    public StringBuilder loadHistory(int n){
        File file = new File("D:\\games 2\\JAVA\\IdeaProjects\\Level3\\HomeWork6\\src\\history.txt");
        StringBuilder history = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int counter = 0;
            String line;
            while ((line = br.readLine()) != null && counter < n) {
                counter += 1;
                history.append(line).append("\n");
            }
            history.setLength(history.length() - 1);
            return history;
        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }


    }
}
