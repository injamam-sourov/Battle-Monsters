import java.util.ArrayList;
import java.util.Scanner;

public class Battle {
    public static void main(String[] args) {
        // open scanner for user input
        Scanner input = new Scanner(System.in);
        // create hero and monster
        Hero H = new Hero("Tom");
        Monster M = new Monster("Mudcrab");

        // list of monsters (ordered) 
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        // add 5 minions to list
        for(int i = 0; i < 5; i++) {
            monsters.add(new Monster());
        }
        // add  monster to list
        monsters.add(M);
    
    // battle scenario

        // fight every monster in list sequentially
        for(Monster m : monsters) {
            // repeat until mosnter dies
            while(!m.isDead()) {
                // check if user wants to heal 
                System.out.print("Would you like to heal Hero? ");
                String option = input.next();
                if(option.equalsIgnoreCase("y") || option.equalsIgnoreCase("yes")) {
                    try{
                        H.heal();
                        System.out.println("💚 Hero was healed!");
                        System.out.println(H);
                    } catch(NullPointerException e) {
                        System.out.println("Already at max health!");
                        // heal fails, continue hero attack, monster takes damage
                        m.takeDamage(H.takeAttack());
                        System.out.println("😎 Hero attacks, monster took damage!");
                        System.out.println(m);
                    }
                } else {
                    // hero attacks, monster takes damage
                    m.takeDamage(H.takeAttack());
                    System.out.println("😎 Hero attacks, monster took damage!");
                    System.out.println(m);
                }
                // check if monster dies
                if(m.isDead()) {
                    // hero levels up, fights next monster
                    System.out.println("🔥 Monster dies! \n Hero leveled up! Heros stats increased!");
                    H.levelUp();
                    System.out.println(H.stats());
                    break;
                }
                // monster attacks, hero takes damage
                H.takeDamage(m.takeAttack());
                System.out.println("👹 Monster attacks, hero takes damage!");
                System.out.println(H);
                // check if hero dies
                if(H.isDead()) {
                    System.out.println("😥 Hero dies! Game over");
                    return;
                }
            }
        }
        // close scanner
        input.close();
    }
}