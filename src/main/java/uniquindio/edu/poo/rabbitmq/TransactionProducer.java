package uniquindio.edu.poo.rabbitmq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TransactionProducer extends Thread {
    private static final BlockingQueue<String> transactionQueue = new LinkedBlockingQueue<>();

    // mensaje JSON con los datos de la transaccion
    public void produceTransaction(String transactionMessage) {
        try {
            transactionQueue.put(transactionMessage);
            System.out.println("[Producer] Transacción producida: " + transactionMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // cola de transacciones para el consumidor
    public static BlockingQueue<String> getTransactionQueue() {
        return transactionQueue;
    }
}
