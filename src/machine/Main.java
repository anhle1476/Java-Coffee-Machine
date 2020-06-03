package machine;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static MachineStates states = MachineStates.MENU;

    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;


    public static void main(String[] args) {
        while (states != MachineStates.OFF) {
            System.out.println( states == MachineStates.MENU ?
                    "\nWrite action (buy, fill, take, remaining, exit): " :
                    "\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:"
            );
            String actionCmd = scanner.next();
            machineActions(states, actionCmd);
        }
        scanner.close();
    }

    private static void printCurrentStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
    }

    private static void machineActions(MachineStates currentState, String actionCmd) {
        if (currentState == MachineStates.BUY) {
            buy(actionCmd);
        } else {
            switch (actionCmd) {
                case "buy": {
                    states = MachineStates.BUY;
                    break;
                }
                case "fill": {
                    fill();
                    break;
                }
                case "take": {
                    take();
                    break;
                }
                case "remaining": {
                    printCurrentStatus();
                    break;
                }
                case "exit": {
                    states = MachineStates.OFF;
                    System.out.println("Bye bye.");
                    break;
                }
                default: {
                    System.out.println("Action invalid, please try again.");
                }
            }
        }
    }

    private static void buy(String type) {
        switch (type) {
            case "1": {
                handleBuyCoffee(250, 0, 16, 4);
                break;
            }
            case "2": {
                handleBuyCoffee(350, 75, 20, 7);
                break;
            }
            case "3": {
                handleBuyCoffee(200, 100, 12, 6);
                break;
            }
            case "back": {
                break;
            }
            default: {
                System.out.println("Action invalid, please try again.");
            }
        }
        states = MachineStates.MENU;
    }
    private static void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        beans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += scanner.nextInt();
    }
    private static void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private static void handleBuyCoffee (int waterNeed, int milkNeed, int beansNeed, int price) {
        int tempWater = water - waterNeed;
        int tempMilk = milk - milkNeed;
        int tempBeans = beans - beansNeed;
        int tempCups = cups - 1;

        String needMore = "";
        if (tempWater < 0) needMore += " water";
        if (tempMilk < 0) needMore += " milk";
        if (tempBeans < 0) needMore += " beans";
        if (tempCups < 0) needMore += " cups";


        if (needMore.length() == 0) {
            System.out.println("I have enough resources, making you a coffee!");
            water = tempWater;
            milk = tempMilk;
            beans = tempBeans;
            cups = tempCups;
            money += price;
        } else {
            System.out.println("Sorry, not enough" + needMore);
        }
    }
}
