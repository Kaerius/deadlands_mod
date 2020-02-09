package net.sergeus_v.items;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.sergeus_v.DeadLands;

public class ToolMaterialDeadLands implements ToolMaterial {

	public ToolMaterialDeadLands() {

	}

	@Override
	public int getDurability() {
		// ��������� �����������
		return 300;
	}

	@Override
	public float getMiningSpeed() {
		// �������� ������
		return 10f;
	}

	@Override
	public float getAttackDamage() {
		// ���� �����
		return 2.5f;
	}

	@Override
	public int getMiningLevel() {
		// ������� ����
		return 4;
	}

	@Override
	public int getEnchantability() {
		// ������� �����������
		return 30;
	}

	@Override
	public Ingredient getRepairIngredient() {
		// ������� ��� �������
		return Ingredient.ofItems(DeadLands.DEAD_ITEM, DeadLands.PIECE_OF_CURSED_STONE);
	}

}
