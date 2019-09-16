package com.mrbysco.telepass.item;

import com.mrbysco.telepass.TelePass;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

public class ItemTeleCompass extends Item {
	public ItemTeleCompass(String registryName, CompassMaterial material) {
		this.maxStackSize = 1;
		this.setCreativeTab(TelePass.teleTab);
		this.setMaxDamage(material.getMaxUses());
		
		this.setTranslationKey(registryName);
		this.setRegistryName(registryName);
		
		this.setNoRepair();
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if(itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("ownerName")) {
			String ownerName = itemstack.getTagCompound().getString("ownerName");
			if(ownerName.equalsIgnoreCase(playerIn.getName())) {
        		playerIn.sendMessage(new TextComponentTranslation("item.telepass.self", new Object[0]));
        		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
			}
			
			if(!worldIn.isRemote) {
				boolean isOnline = false;
				
	        	for(EntityPlayer player : worldIn.playerEntities) {
	        		if(player.getName().equalsIgnoreCase(ownerName)) {
	        			isOnline = true;
	        			break;
	        		}
	        	}
	        	
	        	if(isOnline) {
    		        EntityPlayer owner = worldIn.getPlayerEntityByName(ownerName);
	        		if(owner.dimension != playerIn.dimension) {
		        		playerIn.sendMessage(new TextComponentTranslation("item.telepass.dimension", new Object[] { TextFormatting.RED + ownerName }));
	        			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	        		}
	        			
	        		if (!playerIn.capabilities.isCreativeMode)
			        {
			            itemstack.damageItem(1, playerIn);
			        }
	        		
	        		if(!(playerIn instanceof FakePlayer)) {
	    		        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
	    		        playerIn.getCooldownTracker().setCooldown(this, 20);
	    		        
    		        	playerIn.setPositionAndUpdate(owner.posX, owner.posY, owner.posZ);
	    			}
	        		
	        	} else {
	        		playerIn.sendMessage(new TextComponentTranslation("item.telepass.offline", new Object[] { TextFormatting.RED + ownerName }));
	        	}
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		} else {
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(!stack.hasTagCompound() && !worldIn.isRemote) {
			if(entityIn instanceof EntityPlayer && !(entityIn instanceof FakePlayer)) {
				EntityPlayer player = (EntityPlayer) entityIn;
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("ownerName", player.getName());
				
				stack.setTagCompound(tag);
			}
		}
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(stack.hasTagCompound()) {
			NBTTagCompound tag = stack.getTagCompound();
			String owner = tag.getString("ownerName");
			
			return owner + "'s " +  I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name").trim();
		} else {
			return super.getItemStackDisplayName(stack);
		}
	}
}
