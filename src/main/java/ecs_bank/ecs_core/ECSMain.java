package ecs_bank.ecs_core;


import ecs_bank.ecs_core.components.AddressComponent;
import ecs_bank.ecs_core.components.PersonDetailComponent;
import ecs_bank.ecs_core.components.PrivateAccountComponent;
import ecs_bank.ecs_core.systems.AccountInitSystem;

import java.time.LocalDate;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 *     Based on Adam Martins ECS theory
 */
public class ECSMain {
    public static void main(String[] args) {


        EntityManagerECS managerECS = new EntityManagerECS();


        // CREATE 4 Entities
        int zero = managerECS.createEntity();
        int one = managerECS.createEntity();
        int two = managerECS.createEntity();
        int three = managerECS.createEntity();

        // ADD COMPONENTS
        managerECS.addComponent(zero, new PersonDetailComponent("Sebastian", "Losto", "198xxxxx-xxxx"));
    //    managerECS.addComponent(zero, new PrivateAccountComponent());
        managerECS.addComponent(zero, new AddressComponent("gatan",1,43545,"Lund","Swe"));

        managerECS.addComponent(one, new PersonDetailComponent("John", "Doe", "19xxxx945-xxxx"));
        managerECS.addComponent(one, new AddressComponent("gatan",1,43545,"Lund","Swe"));

        managerECS.addComponent(two, new PersonDetailComponent("Adolf", "Bodil", "xxxx0112-31xx"));
   //     managerECS.addComponent(two, new PrivateAccountComponent());
        managerECS.addComponent(two, new AddressComponent("gatan",1,43545,"Lund","Swe"));

        managerECS.addComponent(three, new PersonDetailComponent("Arnold", "Chan", "19xxxx01-3xxx"));
   //     managerECS.addComponent(three, new PrivateAccountComponent());
        managerECS.addComponent(three, new AddressComponent("gatan",1,43545,"Lund","Swe"));


        System.out.println(managerECS.getAllEntitiesPossessingComponent(PersonDetailComponent.class));

        // CHANGE VARIABLE COMPONENT
        managerECS.getComponent(zero, PersonDetailComponent.class).lastName = "CHANGE NAME";
        System.out.println(managerECS.getComponent(zero, PersonDetailComponent.class));
        System.out.println();


        // CHECK IF COMPONENT EXIST
        System.out.println(managerECS.getAllComponentsForEntity(zero).toString());
        System.out.println(managerECS.hasComponent(zero,PersonDetailComponent.class));
        managerECS.removeComponent(zero,PersonDetailComponent.class);
        System.out.println(managerECS.getAllComponentsForEntity(0).toString());
        System.out.println(managerECS.hasComponent(zero,PersonDetailComponent.class));
        System.out.println(managerECS.getAllComponentsOfType(PersonDetailComponent.class));
        System.out.println();

        System.out.println(managerECS.getAllEntitiesPossessingComponent(PersonDetailComponent.class));

        // SYSTEM INIT - 100 SEK on every account
        AccountInitSystem sys = new AccountInitSystem();
        sys.process(managerECS.getAllComponentsOfType(PrivateAccountComponent.class));
        System.out.println(managerECS.getAllComponentsOfType(PrivateAccountComponent.class));
        managerECS.getComponent(0, PrivateAccountComponent.class).clearingNbr = 1000;

        System.out.println();
        managerECS.printEntityComponents(1);
        managerECS.destroyEntity(1);
        System.out.println();

        for (int i = 0; i < managerECS.entityList.size(); i++) {
            System.out.println(managerECS.entityList.get(i));
        }


        // CREATE 4 Entities
        int four = managerECS.createEntity();
        int five = managerECS.createEntity();
        int six = managerECS.createEntity();
        managerECS.destroyEntity(3);
        int seven = managerECS.createEntity();


        managerECS.addComponent(four, new PersonDetailComponent("Sebastian", "Losto", "198xxxxx-xxxx"));
    //    managerECS.addComponent(four, new PrivateAccountComponent());
        managerECS.addComponent(four, new AddressComponent("gatan",1,43545,"Lund","Swe"));

        managerECS.addComponent(five, new PersonDetailComponent("John", "Doe", "19xxxx945-xxxx"));
        managerECS.addComponent(five, new AddressComponent("gatan",1,43545,"Lund","Swe"));

        managerECS.addComponent(six, new PersonDetailComponent("Adolf", "Bodil", "xxxx0112-31xx"));
    //    managerECS.addComponent(six, new PrivateAccountComponent());
        managerECS.addComponent(six, new AddressComponent("gatan",1,43545,"Lund","Swe"));

        managerECS.addComponent(seven, new PersonDetailComponent("Arnold", "Chan", "19xxxx01-3xxx"));
    //    managerECS.addComponent(seven, new PrivateAccountComponent());
        managerECS.addComponent(seven, new AddressComponent("gatan",1,43545,"Lund","Swe"));


    }
}
