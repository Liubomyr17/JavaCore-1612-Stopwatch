package com.company;

/*

1612 Stopwatch
1. Understand what the program does.
2. Implement the logic of the doStep method so that the speed of the runner is taken into account.
2.1. The getSpeed ​​() method in the Runner class shows how many steps per second the runner takes.
It is necessary for the runner to really take the set number of steps per second.
If Ivanov does 4 steps per second, then in 2 seconds he will do 8 steps.
If Petrov does 2 steps per second, then in 2 seconds he will do 4 steps.
2.2. The Thread class sleep method takes a parameter of type long.
IMPORTANT! Use the Thread.sleep () method, not Stopwatch.sleep ().

Requirements:
1. The getSpeed ​​method must return an int.
2. The speed field of the Runner class must be of type int.
3. The constructor of the Runner class must accept String and int.
4. The doStep method must take into account the speed of the runner. If the speed of the runner is 2 steps per second, the method should run for half a second; if the speed of the runner is 4 steps per second, the method should run for a quarter of a second.
5. The output of the program should reflect how many steps Ivanov and Petrov took in 2 seconds.

 */

import java.util.ArrayList;
import java.util.List;

/*
Horse Racing
*/

public class Solution {
    public static volatile boolean isStopped = false;

    public static void main(String[] args) throws InterruptedException {
        Runner ivanov = new Runner("Ivanov", 4);
        Runner petrov = new Runner("Petrov", 2);
        //на старт!
        //внимание!
        //марш!
        ivanov.start();
        petrov.start();
        Thread.sleep(2000);
        isStopped = true;
        Thread.sleep(1000);
    }

    public static class Stopwatch extends Thread {
        private Runner owner;
        private int stepNumber;

        public Stopwatch(Runner runner) {
            this.owner = runner;
        }

        public void run() {
            try {
                while (!isStopped) {
                    doStep();
                }
            } catch (InterruptedException e) {
            }
        }

        private void doStep() throws InterruptedException {
            stepNumber++;
            Thread.sleep(1000 / owner.getSpeed());
            System.out.println(owner.getName() + " делает шаг №" + stepNumber + "!");
        }
    }

    public static class Runner {
        Stopwatch stopwatch;
        private String name;
        private int speed;

        public Runner(String name, int speed) {
            this.name = name;
            this.speed = speed;
            this.stopwatch = new Stopwatch(this);
        }

        public String getName() {
            return name;
        }

        public int getSpeed() {
            return speed;
        }

        public void start() {
            stopwatch.start();
        }
    }
}
