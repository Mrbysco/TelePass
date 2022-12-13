package com.mrbysco.telepass.item;

import com.mrbysco.telepass.Reference;
import com.mrbysco.telepass.init.TeleGroup;
import com.mrbysco.telepass.util.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
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
		super(properties.stacksTo(1).tab(TeleGroup.TELEPASS).stacksTo(1).setNoRepair());
		this.material = material;
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return material.getMaxUses();
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);

		if (!worldIn.isClientSide && itemstack.hasTag() && itemstack.getTag().contains(Reference.OWNER_TAG)) {
			String ownerName = itemstack.getTag().getString(Reference.OWNER_TAG);
			if (ownerName.equalsIgnoreCase(playerIn.getName().getContents())) {
				playerIn.sendMessage(new TranslatableComponent("item.telepass.self"), Util.NIL_UUID);
				return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
			}
			boolean isOnline = false;

			for (Player player : worldIn.players()) {
				if (player.getName().getContents().equalsIgnoreCase(ownerName)) {
					isOnline = true;
					break;
				}
			}

			if (isOnline) {
				Player owner = PlayerUtil.getPlayerEntityByName(worldIn, ownerName);
				if (owner != null && owner.getCommandSenderWorld().dimension().getRegistryName() != playerIn.getCommandSenderWorld().dimension().getRegistryName()) {
					playerIn.sendMessage(new TranslatableComponent("item.telepass.dimension", ChatFormatting.RED + ownerName), Util.NIL_UUID);
					return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
				}

				if (!playerIn.getAbilities().instabuild) {
					itemstack.hurtAndBreak(1, playerIn, (p_219998_1_) -> p_219998_1_.broadcastBreakEvent(handIn));
				}

				if (!(playerIn instanceof FakePlayer) && owner != null) {
					worldIn.playSound((Player) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 0.5F, 0.4F / (worldIn.random.nextFloat() * 0.4F + 0.8F));
					playerIn.getCooldowns().addCooldown(this, 20);

					playerIn.teleportTo(owner.getX(), owner.getY(), owner.getZ());
				}
			} else {
				playerIn.sendMessage(new TranslatableComponent("item.telepass.offline", ChatFormatting.RED + ownerName), Util.NIL_UUID);
			}
			return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
		} else {
			return new InteractionResultHolder<>(InteractionResult.FAIL, itemstack);
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isClientSide) {
			if ((stack.hasTag() && stack.getTag() != null && !stack.getTag().contains(Reference.OWNER_TAG)) || !stack.hasTag() || stack.getTag() == null) {
				CompoundTag tag = stack.getTag() == null ? new CompoundTag() : stack.getTag();
				if (entityIn instanceof Player player && !(entityIn instanceof FakePlayer)) {
					tag.putString(Reference.OWNER_TAG, player.getName().getContents());

					stack.setTag(tag);
				}
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	@Override
	public Component getName(ItemStack stack) {
		if (stack.hasTag() && stack.getTag() != null && stack.getTag().contains(Reference.OWNER_TAG)) {
			CompoundTag tag = stack.getTag();
			String owner = tag.getString(Reference.OWNER_TAG);
			return new TextComponent(owner + "'s ").append(new TranslatableComponent(this.getDescriptionId(stack)));
		} else {
			return super.getName(stack);
		}
	}
}
