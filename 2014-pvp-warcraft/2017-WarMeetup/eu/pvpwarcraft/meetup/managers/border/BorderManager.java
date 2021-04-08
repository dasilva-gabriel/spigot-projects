package eu.pvpwarcraft.meetup.managers.border;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import eu.pvpwarcraft.meetup.handler.Step;

public class BorderManager {

	public static void decreaseBorder() {

		World ws = Bukkit.getWorld("world");
		WorldBorder wb = ws.getWorldBorder();
		if (wb.getSize() > 100 && (Step.isStep(Step.IN_GAME))) {
			wb.setSize(wb.getSize() - 40.0, 21);
		}

	}

	public static void setBorder(double taille) {

		World ws = Bukkit.getWorld("world");

		WorldBorder wb = ws.getWorldBorder();
		wb.setCenter(0, 0);
		wb.setSize(taille);
		wb.setWarningDistance(10);
		wb.setDamageAmount(0.5);

	}

}
