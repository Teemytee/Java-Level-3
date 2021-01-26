import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler {
    private String name;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private Chat chat;
    private boolean authorization;
    private StringBuilder history = new StringBuilder();

    public ClientHandler(Socket socket, Chat chat) {
        this.socket = socket;
        this.chat = chat;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }
        authTimeOut();
        listen();
    }

    public String getName() {
        return name;
    }

    private void listen() {
        new Thread(() -> {
            doAuth();
            receiveMessage();
        }).start();
    }

    private void doAuth() {
        sendMessage("Please enter credentials. Sample [-auth login password]");
        authorization = false;
        try {
            /**
             * -auth login password
             * sample: -auth l1 p1
             */
            while (true) {
                String mayBeCredentials = in.readUTF();
                if (mayBeCredentials.startsWith("-auth")) {
                    String[] credentials = mayBeCredentials.split("\\s");
                    String mayBeNickname = chat.getAuthenticationService()
                            .findNicknameByLoginAndPasswordWithDataBase(credentials[1], credentials[2]);
                    if (mayBeNickname != null) {
                        if (!chat.isNicknameOccupied(mayBeNickname)) {
                            authorization = true;
                            sendMessage("[INFO] Auth OK");
                            name = mayBeNickname;

                            chat.broadcastMessage(String.format("[%s] logged in", name));
                            chat.subscribe(this);

                            return;
                        } else {
                            sendMessage("[INFO] Current user is already logged in.");
                        }
                    } else {
                        sendMessage("[INFO] Wrong login or password.");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    public void receiveMessage() {
        while (true) {
            try {
                String message = in.readUTF();
                if (message.startsWith("-exit")) {
                    chat.unsubscribe(this);
                    chat.broadcastMessage(String.format("[%s] logged out", name));
                    history.setLength(history.length() - 2);
                    makeHistoryChat(history);
                    break;
                }
                history.append(String.format("[%s]: %s \n", name, message));
                //history.append("\n");
                chat.broadcastMessage(String.format("[%s]: %s", name, message));

            } catch (IOException e) {
                throw new RuntimeException("SWW", e);
            }
        }
    }

    private void authTimeOut(){
        new Thread(() -> {
            long m = System.currentTimeMillis();
            long timeSpent;
            while(!authorization){
                timeSpent = System.currentTimeMillis() - m;
                if (timeSpent > 120000){
                    try {
                        System.out.println("[ERROR]: Time for authorization has expired");
                        socket.close();
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Alright, you connected");

        }).start();

    }

    private void makeHistoryChat(StringBuilder stringBuilder){
        File file = new File("D:\\games 2\\JAVA\\IdeaProjects\\Level3\\HomeWork8\\src\\history.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.newLine();
            bw.write(String.valueOf(stringBuilder));
            bw.flush();
        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }

    }

}
