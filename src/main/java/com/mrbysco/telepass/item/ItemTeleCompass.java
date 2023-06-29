package com.mrbysco.telepass.item;

import com.mrbysco.telepass.Reference;
import com.mrbysco.telepass.util.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;

public class ItemTeleCompass extends Item {
	private final CompassMaterial material;

	public ItemTeleCompass(Item.Properties properties, CompassMaterial material) {
		super(properties.stacksTo(1).stacksTo(1).setNoRepair());
		this.material = material;
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return material.getMaxUses();
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);

		if (!level.isClientSide && itemstack.hasTag() && itemstack.getTag().contains(Reference.OWNER_TAG)) {
			String ownerName = itemstack.getTag().getString(Reference.OWNER_TAG);
			if (ownerName.equalsIgnoreCase(playerIn.getGameProfile().getName())) {
				playerIn.sendSystemMessage(Component.translatable("item.telepass.self"));
				return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
			}
			boolean isOnline = false;

			for (Player player : level.players()) {
				if (player.getGameProfile().getName().equalsIgnoreCase(ownerName)) {
					isOnline = true;
					break;
				}
			}

			if (isOnline) {
				Player owner = PlayerUtil.getPlayerEntityByName(level, ownerName);
				if (owner != null && owner.level().dimension().location() != playerIn.level().dimension().location()) {
					playerIn.sendSystemMessage(Component.translatable("item.telepass.dimension", ChatFormatting.RED + ownerName));
					return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
				}

				if (!playerIn.getAbilities().instabuild) {
					itemstack.hurtAndBreak(1, playerIn, (p_219998_1_) -> p_219998_1_.broadcastBreakEvent(handIn));
				}

				if (!(playerIn instanceof FakePlayer) && owner != null) {
					level.playSound((Player) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.random.nextFloat() * 0.4F + 0.8F));
					playerIn.getCooldowns().addCooldown(this, 20);

					playerIn.teleportTo(owner.getX(), owner.getY(), owner.getZ());
				}
			} else {
				playerIn.sendSystemMessage(Component.translatable("item.telepass.offline", ChatFormatting.RED + ownerName));
			}
			return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
		} else {
			return new InteractionResultHolder<>(InteractionResult.FAIL, itemstack);
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!level.isClientSide) {
			if ((stack.hasTag() && stack.getTag() != null && !stack.getTag().contains(Reference.OWNER_TAG)) || !stack.hasTag() || stack.getTag() == null) {
				CompoundTag tag = stack.getTag() == null ? new CompoundTag() : stack.getTag();
				if (entityIn instanceof Player player && !(entityIn instanceof FakePlayer)) {
					tag.putString(Reference.OWNER_TAG, player.getGameProfile().getName());

					stack.setTag(tag);
				}
			}
		}
		super.inventoryTick(stack, level, entityIn, itemSlot, isSelected);
	}

	@Override
	public Component getName(ItemStack stack) {
		if (stack.hasTag() && stack.getTag() != null && stack.getTag().contains(Reference.OWNER_TAG)) {
			CompoundTag tag = stack.getTag();
			String owner = tag.getString(Reference.OWNER_TAG);
			return Component.literal(owner + "'s ").append(Component.translatable(this.getDescriptionId(stack)));
		} else {
			return super.getName(stack);
		}
	}
}
