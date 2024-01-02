package com.mrbysco.telepass.item;

import com.mrbysco.telepass.Constants;
import com.mrbysco.telepass.platform.Services;
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

public class TeleCompass extends Item {

	protected final CompassMaterial material;

	public TeleCompass(Properties properties, CompassMaterial material) {
		super(properties.stacksTo(1).stacksTo(1));
		this.material = material;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand handIn) {
		ItemStack itemstack = player.getItemInHand(handIn);

		if (!level.isClientSide && itemstack.hasTag() && itemstack.getTag().contains(Constants.OWNER_TAG)) {
			String ownerName = itemstack.getTag().getString(Constants.OWNER_TAG);
			if (ownerName.equalsIgnoreCase(player.getGameProfile().getName())) {
				player.sendSystemMessage(Component.translatable("item.telepass.self"));
				return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
			}
			boolean isOnline = false;

			for (Player player1 : level.players()) {
				if (player1.getGameProfile().getName().equalsIgnoreCase(ownerName)) {
					isOnline = true;
					break;
				}
			}

			if (isOnline) {
				Player owner = PlayerUtil.getPlayerEntityByName(level, ownerName);
				if (owner != null && owner.level().dimension().location() != player.level().dimension().location()) {
					player.sendSystemMessage(Component.translatable("item.telepass.dimension", ChatFormatting.RED + ownerName));
					return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
				}

				if (!player.getAbilities().instabuild) {
					itemstack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(handIn));
				}

				if (Services.PLATFORM.notFakePlayer(player) && owner != null) {
					level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.random.nextFloat() * 0.4F + 0.8F));
					player.getCooldowns().addCooldown(this, 20);

					player.teleportTo(owner.getX(), owner.getY(), owner.getZ());
				}
			} else {
				player.sendSystemMessage(Component.translatable("item.telepass.offline", ChatFormatting.RED + ownerName));
			}
			return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
		} else {
			return new InteractionResultHolder<>(InteractionResult.FAIL, itemstack);
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!level.isClientSide) {
			if ((stack.hasTag() && stack.getTag() != null && !stack.getTag().contains(Constants.OWNER_TAG)) || !stack.hasTag() || stack.getTag() == null) {
				CompoundTag tag = stack.getTag() == null ? new CompoundTag() : stack.getTag();
				if (entityIn instanceof Player player && Services.PLATFORM.notFakePlayer(player)) {
					tag.putString(Constants.OWNER_TAG, player.getGameProfile().getName());

					stack.setTag(tag);
				}
			}
		}
		super.inventoryTick(stack, level, entityIn, itemSlot, isSelected);
	}

	@Override
	public Component getName(ItemStack stack) {
		if (stack.hasTag() && stack.getTag() != null && stack.getTag().contains(Constants.OWNER_TAG)) {
			CompoundTag tag = stack.getTag();
			String owner = tag.getString(Constants.OWNER_TAG);
			return Component.literal(owner + "'s ").append(Component.translatable(this.getDescriptionId(stack)));
		} else {
			return super.getName(stack);
		}
	}
}
