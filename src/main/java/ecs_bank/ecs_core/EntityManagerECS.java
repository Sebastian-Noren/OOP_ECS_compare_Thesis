package ecs_bank.ecs_core;



import ecs_bank.ecs_core.components.IComponent;

import java.util.*;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 * Based on Adam Martins ECS theory
 */
public class EntityManagerECS {

    //TODO Build a buffer when enitits is removed to reuse it. Then growing the List.

    private static final int INVALID_ENTITY = -1;
    private static final int START_ENTITY_INT = 0;
    private static final int ENTITY_BUFFER_CHECKER = 5;
    private int ENTITY_ID = START_ENTITY_INT;
    public ArrayList<Integer> entityList;
    private HashMap<String, HashMap<Integer, ? extends IComponent>> componentStores;
    private final Deque<Integer> ENTITY_BUFFER = new ArrayDeque<>(10);


    public EntityManagerECS() {
        entityList = new ArrayList<>();
        componentStores = new HashMap<>();
    }

    // Creates the entity ref
    public int createEntity() {
        if (entityList.size() >= ENTITY_BUFFER_CHECKER) {
            if (!ENTITY_BUFFER.isEmpty()) {
                int entityBufferID = ENTITY_BUFFER.removeFirst();
                entityList.set(entityBufferID, entityBufferID);
                return entityBufferID;
            }
        }
        int newEntityID = getNewEntityID();
        if (newEntityID < START_ENTITY_INT) {
            return INVALID_ENTITY;
        } else {
            entityList.add(newEntityID);
            return newEntityID;
        }
    }

    private int getNewEntityID() {
        if (ENTITY_ID < Integer.MAX_VALUE) {
            return ENTITY_ID++;
        } else {
            for (int i = START_ENTITY_INT; i < Integer.MAX_VALUE; i++) {
                if (!entityList.contains(i))
                    return i;
            }

            throw new Error("ERROR: No available Entity IDs; INTEGER MAX_Value reached!");
        }
    }

    // Pass as an object to remove key instead of index
    public void destroyEntity(Integer entity) {
        ENTITY_BUFFER.add(entity);
        entityList.set(entity, null);
        //  entityList.remove(entity);
        for (HashMap<Integer, ? extends IComponent> entityComponentData : componentStores.values()) {
            entityComponentData.remove(entity);
        }
    }


    // Adding new component to an entity
    public <T extends IComponent> void addComponent(int entity, T componentType) {
        HashMap<Integer, ? extends IComponent> entityComponentData = componentStores.get(componentType.getClass().getSimpleName());
        if (entityComponentData == null) {
            entityComponentData = new HashMap<Integer, T>();
            componentStores.put(componentType.getClass().getSimpleName(), entityComponentData);
        }
        ((HashMap<Integer, T>) entityComponentData).put(entity, componentType);
    }

    // Get a specific Component for the entity
    public <T extends IComponent> T getComponent(int entity, Class<T> componentType) {
        T result = componentType.cast(componentStores.get(componentType.getSimpleName()).get(entity));
        if (result == null) {
            throw new IllegalArgumentException("ERROR: " + entity + " does not possess Component: " + componentType.getSimpleName());
        }
        return result;
    }

    public <T extends IComponent> boolean hasComponent(int entity, Class<T> componentType) {
        IComponent component = componentStores.get(componentType.getSimpleName()).get(entity);
        return component != null;

    }

    public <T extends IComponent> void removeComponent(int entity, Class<T> componentType) {
        IComponent component = componentStores.get(componentType.getSimpleName()).remove(entity);
        if (component == null) {
            throw new IllegalArgumentException("REMOVE FAIL: " + entity + " missing: " + componentType.getSimpleName());
        }
    }

    public <T extends IComponent> ArrayList<T> getAllComponentsOfType(Class<T> componentType) {
        HashMap<Integer, ? extends IComponent> entityComponentData = componentStores.get(componentType.getSimpleName());
        if (entityComponentData == null) {
            return new ArrayList<T>();
        } else {
            return new ArrayList<T>((Collection<T>) entityComponentData.values());
        }
    }

    public <T extends IComponent> Set<Integer> getAllEntitiesPossessingComponent(Class<T> componentType) {
        HashMap<Integer, ? extends IComponent> entityComponentData = componentStores.get(componentType.getSimpleName());
        if (entityComponentData == null) {
            return new HashSet<>();
        }
        return entityComponentData.keySet();
    }

    // Print the components the entity use
    public void printEntityComponents(int entity) {
        for (HashMap<Integer, ? extends IComponent> entityComponentData : componentStores.values()) {
            if (entityComponentData.get(entity) != null) {
                System.out.println(entityComponentData.get(entity).toString());
            }
        }
    }

    public <T extends IComponent> ArrayList<T> getAllComponentsForEntity(int entity) {
        ArrayList<T> entityComponentList = new ArrayList<>();
        for (HashMap<Integer, ? extends IComponent> entityComponentData : componentStores.values()) {
            if (entityComponentData.get(entity) != null) {
                T result = (T) entityComponentData.get(entity);
                entityComponentList.add(result);
            }
        }
        return entityComponentList;
    }


}
