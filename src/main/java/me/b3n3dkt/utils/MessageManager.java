package me.b3n3dkt.utils;

import java.util.HashMap;

import me.b3n3dkt.Citybuild;
import org.bukkit.entity.Player;

public class MessageManager {

    Citybuild cb;

    HashMap<Player,Player> conversations = new HashMap<Player,Player>();

    public MessageManager(Citybuild citybuild) {
        cb = citybuild;
    }

    public void setReplyTarget(Player messager, Player reciever){
        conversations.put(messager, reciever);
        conversations.put(reciever, messager);
    }

    public Player getReplyTarget(Player messager){
        return conversations.get(messager);
    }
}