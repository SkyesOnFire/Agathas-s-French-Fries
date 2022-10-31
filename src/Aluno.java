import java.util.concurrent.Semaphore;

public class Aluno extends Thread{

    public static int comeu = 0;
    private final String nome;
    private final Semaphore semaforo;
    private final Prateleira prateleira;

    public Aluno(String nome, Semaphore semaforo, Prateleira prateleira) {
        super(nome);
        this.semaforo = semaforo;
        this.nome = nome;
        this.prateleira = prateleira;
    }

    @Override
    public void run() {
        // run by thread A
        if (this.getName().equals("Teilor")) {
            System.out.println("Starting " + nome);
            try {
                // First, get a permit.
                System.out.println(nome + " is waiting for a permit.");

                // acquiring the lock
                semaforo.acquire();

                System.out.println(nome + " gets a permit.");

                // Now, accessing the shared resource.
                // other waiting threads will wait, until this
                // thread release the lock
                for (int i = 0; i < 5; i++) {
                    prateleira.quantidadeDeBatatas = -1;
                    System.out.println(nome + "comeu uma batata frita");

                    // Now, allowing a context switch -- if possible.
                    // for thread B to execute
                    Thread.sleep(10);
                }
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }

            // Release the permit.
            System.out.println(nome + " releases the permit.");
            semaforo.release();
        }

        // run by thread B
        else if (this.getName().equals("Pedro")){
            System.out.println("Starting " + nome);
            try {
                // First, get a permit.
                System.out.println(nome + " is waiting for a permit.");

                // acquiring the lock
                semaforo.acquire();

                System.out.println(nome + " gets a permit.");

                // Now, accessing the shared resource.
                // other waiting threads will wait, until this
                // thread release the lock
                for (int i = 0; i < 5; i++) {
                    prateleira.quantidadeDeBatatas = -1;
                    System.out.println(nome + "comeu uma batata frita");

                    // Now, allowing a context switch -- if possible.
                    // for thread A to execute
                    Thread.sleep(10);
                }
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
            // Release the permit.
            System.out.println(nome + " releases the permit.");
            semaforo.release();
        }else{
            System.out.println("Starting " + nome);
            try {
                // First, get a permit.
                System.out.println(nome + " is waiting for a permit.");

                // acquiring the lock
                semaforo.acquire();

                System.out.println(nome + " gets a permit.");

                // Now, accessing the shared resource.
                // other waiting threads will wait, until this
                // thread release the lock
                for (int i = 0; i < 5; i++) {
                    prateleira.quantidadeDeBatatas = -1;
                    System.out.println(nome + "comeu uma batata frita");

                    // Now, allowing a context switch -- if possible.
                    // for thread A to execute
                    Thread.sleep(10);
                }
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
            // Release the permit.
            System.out.println(nome + " releases the permit.");
            semaforo.release();
        }
    }
}
