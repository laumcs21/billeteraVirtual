package uniquindio.edu.poo.rabbitmq;

import java.util.concurrent.BlockingQueue;

public class TransactionConsumer extends Thread {
    private final BlockingQueue<String> transactionQueue;

    public TransactionConsumer() {
        this.transactionQueue = TransactionProducer.getTransactionQueue();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String transactionMessage = transactionQueue.take();
                processTransaction(transactionMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processTransaction(String transactionMessage) {
        System.out.println("[Consumer] Procesando transacción: " + transactionMessage);
    }
}
