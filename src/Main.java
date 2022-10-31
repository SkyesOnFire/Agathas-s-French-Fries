import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Scanner console = new Scanner(System.in);
        Semaphore semaforo = new Semaphore(1);

        System.out.print("Entre com o número máximo de batatas que cabem na prateleira: ");
        Prateleira prateleira = new Prateleira(console.nextInt());

        Aluno alunoTeilor = new Aluno("Teilor", semaforo, prateleira);
        Aluno alunoPedro = new Aluno("Pedro", semaforo, prateleira);
        Aluno alunaAgatha = new Aluno("Agatha", semaforo, prateleira);

        System.out.print("Entre com o número de vezes que o cozinheiro consegue encher a prateleira até desistir: ");
        Cozinheiro cozinheiro = new Cozinheiro(console.nextInt(), prateleira, semaforo);

        System.out.println("Restaurante foi aberto!");
        alunoTeilor.start();
        alunoPedro.start();
        alunaAgatha.start();
        cozinheiro.start();

        if (random.nextBoolean()){
            System.out.println("Aluno Pedro tenta comer batata mas não encontra nada. Então grita: COZINHEIRO!!!");
            while (cozinheiro.vontadeDeFritar > 0 && prateleira.quantidadeDeBatatas > 0)
            while (prateleira.getQuantidadeDeBatatas() != prateleira.getQuantidadeLimite()){
                cozinheiro.join(cozinheiro.getVelocidadeDeEncherPrateleira());
            }
            cozinheiro.vontadeDeFritar -= 1;
            alunoTeilor.run();
            alunaAgatha.run();
            alunoPedro.run();
        }else{
            alunoTeilor.run();
            alunaAgatha.run();
            alunoPedro.run();
        }
    }

//    // creating a Semaphore object
//    // with number of permits 1
//    Semaphore sem = new Semaphore(1);
//
//    // creating two threads with name A and B
//    // Note that thread A will increment the count
//    // and thread B will decrement the count
//    MyThread mt1 = new MyThread(sem, "A");
//    MyThread mt2 = new MyThread(sem, "B");
//
//    // stating threads A and B
//        mt1.start();
//        mt2.start();
//
//    // waiting for threads A and B
//        mt1.join();
//        mt2.join();
//
//    // count will always remain 0 after
//    // both threads will complete their execution
//        System.out.println("count: " + Shared.count);

}