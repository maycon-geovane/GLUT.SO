class Filosofo extends Thread {
    private int id;
    private Object garfoEsquerda;
    private Object garfoDireita;

    public Filosofo(int id, Object garfoEsquerda, Object garfoDireita) {
        this.id = id;
        this.garfoEsquerda = garfoEsquerda;
        this.garfoDireita = garfoDireita;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filosofo " + id + " esta pensando.");
        Thread.sleep(3000); // Simulando pensamento
    }

    private void comer() throws InterruptedException {
        System.out.println("Filosofo " + id + " esta comendo.");
        Thread.sleep(3000); // Simulando comer
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                synchronized (garfoEsquerda) {
                    System.out.println("Filosofo " + id + " pegou o garfo esquerdo.");
                    synchronized (garfoDireita) {
                        System.out.println("Filosofo " + id + " pegou o garfo direito.");
                        comer();
                        System.out.println("Filosofo " + id + " largou o garfo direito.");
                    }
                    System.out.println("Filosofo " + id + " largou o garfo esquerdo.");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class JantarDosFilosofos {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Object[] garfos = new Object[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            garfos[i] = new Object();
        }

        Filosofo[] filosofos = new Filosofo[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            int garfoEsquerda = i;
            int garfoDireita = (i + 1) % numFilosofos;

            if (i == numFilosofos - 1) {
                filosofos[i] = new Filosofo(i, garfos[garfoDireita], garfos[garfoEsquerda]);
            } else {
                filosofos[i] = new Filosofo(i, garfos[garfoEsquerda], garfos[garfoDireita]);
            }
            filosofos[i].start();
        }
    }
}
