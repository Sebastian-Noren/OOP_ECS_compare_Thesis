package ecs_bank.ecs_core.components;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AccountComponent implements IComponent {
    public float amount;

    @Override
    public String toString() {
        return "AccountComponent{" +
                "amount=" + amount +
                '}';
    }
}
