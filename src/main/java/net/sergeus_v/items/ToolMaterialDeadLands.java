package net.sergeus_v.items;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.sergeus_v.DeadLands;

public class ToolMaterialDeadLands implements ToolMaterial {

	public ToolMaterialDeadLands() {

	}

	@Override
	public int getDurability() {
		// Прочность инструмента
		return 300;
	}

	@Override
	public float getMiningSpeed() {
		// Скорость добычи
		return 10f;
	}

	@Override
	public float getAttackDamage() {
		// Сила атаки
		return 2.5f;
	}

	@Override
	public int getMiningLevel() {
		// Уровень силы
		return 4;
	}

	@Override
	public int getEnchantability() {
		// Уровень зачарования
		return 30;
	}

	@Override
	public Ingredient getRepairIngredient() {
		// Предмет для ремонта
		return Ingredient.ofItems(DeadLands.DEAD_ITEM, DeadLands.PIECE_OF_CURSED_STONE);
	}

}
