package fr.cmuagab.sheepwars.handler;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerData {
    private UUID uuid;
    private String name;
    private int wins;
    private int kills;
    private int deaths;
    private int games;
}
