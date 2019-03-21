package Fractal;

import java.util.concurrent.atomic.AtomicInteger;

public class Monkeys {

    //принцип: стараться обходиться без статических методов.
    public static void main(String[] args) {

        new Monkeys(); //создаем одну программу про обезьян
    }

    private AtomicInteger bananas = new AtomicInteger(1_000_000);
    private int total = 0;

    private Monkeys() {
/*
        //здесь основная логика программы

        //обезьяна ест бананы
        int eaten = 0;
        while (bananas > 0) {
            bananas--;
            eaten++;
        }

        System.out.println(String.format("A monkey ate %,d bananas", eaten));
*/

        //Если нужно, чтобы программа одновременно выполняла несколько действий, создаются потоки, по одному на каждое
        //действие. Нам нужно, чтобы две обезьянки ели бананы одновременно, поэтому создадим два потока.

        Object monitor = new Object();

        Runnable monkeyAction = () -> {
            int eaten = 0;
            while (bananas.getAndDecrement() > 0) {
                //в скобках указывается любой объект. Он называется монитор. Если один поток взял монитор (т.е. вошел в
                //блок), то другие потоки ждут, когда монитор будет возвращен. В данном случае this это объект Monkeys,
                //или можно создать специальный объект, который нужен только как монитор
                    eaten++;
            }
            synchronized (monitor) { //используем глобальную переменную, необходима синхронизация
                total += eaten;
            }
            //eaten - эта переменная у каджой обезьянки своя
            //bananas - глобальная переменная

            System.out.println(String.format("A monkey ate %,d bananas, total: %d", eaten, total));
        };

//        Thread monkey1 = new Thread(monkeyAction);
//        Thread monkey2 = new Thread(monkeyAction);

        for (int i = 0; i < 10; i++) {
            Thread monkey = new Thread(monkeyAction);
            monkey.start();
        }

//        monkey1.start();  //start() запускает действие как новый поток
//        monkey2.start();
    }

    //Задание: используйте AtomicInteger вместо int для bananas, он позволяет за одно действие сделать проверку на 0
    //и вычесть единицу.

    //если не синхронизировать работу двух обезьянок,  то в начале каждая решит, что бананов 1_000_000, каждая уменьшит
    //количество до 999_999 и запишет это число в переменную (в поле) bananas. Т.е. бананов 999_999, но каждая
    //обезьянка считает, что она уже съела один банан.

    //участки кода, которые нельзя выполнять одновременно несколими потокам, надо синхронизировать, т.е. явно указывать,
    //что только один поток, может выполнять этот участок кода. Для этого есть ключевое слово synchronize.
    //Программирование с помощью synchronize очень сложно, особенно, если программа большая и потоков много.
}
