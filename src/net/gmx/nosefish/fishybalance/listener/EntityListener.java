package net.gmx.nosefish.fishybalance.listener;
import net.gmx.nosefish.fishylib.entity.MobClass;
import net.gmx.nosefish.fishylib.properties.Properties;

import net.canarymod.api.DamageSource;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.LargeFireball;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.hook.HookHandler;
import net.canarymod.hook.entity.DamageHook;
import net.canarymod.hook.entity.ProjectileHitHook;
import net.canarymod.plugin.PluginListener;
import net.gmx.nosefish.fishybalance.FishyBalance;
import net.gmx.nosefish.fishybalance.properties.Key;


/**
 * A <code>PluginListener</code> that handles protections related to Entities.
 * 
 * Protects <code>HangingEntities</code> like paintings and item frames from
 * destruction by fire, explosions, and mobs. Players are always allowed to
 * destroy them. Uses the same properties as the protection for blocks, so if
 * block damage is allowed for the damage type, <code>HangingEntities</code>
 * will also be destroyed, and the other way round.
 * 
 * @author Stefan Steinheimer (nosefish)
 * 
 */
public class EntityListener implements PluginListener {
	private Properties properties;

	/**
	 * Constructor
	 */
	public EntityListener() {
		this.properties = FishyBalance.properties;
	}

	@HookHandler
	public void onDamage(DamageHook hook) {
		float damageAmount = hook.getDamageDealt();
		Entity defender = hook.getDefender();
		DamageSource damageSource = hook.getDamageSource();
		Entity attacker = damageSource.getDamageDealer();
		if (defender != null && defender instanceof Player) {
			if (attacker != null ) {
//				FishyBalance.logger.logPluginDebug(attacker.getName() + " attacḱed "
//						+ defender.getName() + " dealing " + damageAmount
//						+ " damage");
				// adjust damage dealt by mobs
				World world = attacker.getWorld();
				String attackerClassName = attacker.getClass()
						.getSimpleName();
//				FishyBalance.logger.logPluginDebug("attackerClass: " + attackerClassName);
				try {
					MobClass mobClass = MobClass.valueOf(attackerClassName);
					damageAmount *= getDamageMultiplier(world, mobClass);
//					FishyBalance.logger.logPluginDebug("Adjusted damage to " + damageAmount
//							+ " for " + mobClass);
				} catch (IllegalArgumentException e) {
					FishyBalance.logger
					.warn("Attack by an unknown attacker! Please add "
							+ attackerClassName
							+ " to net.gmx.nosefish.fishyshield.MobClass" + " in FishyLib!");
				}
			} else {
				if (damageSource.isFireDamage()) {
					// adjust fire damage
					damageAmount *= properties.getDouble(defender.getWorld(), Key.ENTITY_DAMAGE_MULT_FIRE);
				}
			}
		}
		hook.setDamageDealt(damageAmount);
	}

	@HookHandler
	public void onProjectileHit(ProjectileHitHook hook) {
		Entity projectile = hook.getProjectile();
		// better explosions for Ghast fireballs
		if (projectile instanceof LargeFireball) {
			World world = projectile.getWorld();
			double power = properties.getDouble(world,
					Key.ENTITY_EXPLOSION_ENHANCER_GHAST);
			if (power > 0.0D) {
				LargeFireball lfb = (LargeFireball)projectile;
				lfb.setPower((float)power);
			}
		}
	}

	private double getDamageMultiplier(World world, MobClass mobClass) {
		if (mobClass != null) {
			switch (mobClass) {
			case CanaryBlaze:
				return properties
						.getDouble(world, Key.ENTITY_DAMAGE_MULT_BLAZE);
			case CanaryCaveSpider:
				return properties.getDouble(world,
						Key.ENTITY_DAMAGE_MULT_CSPIDER);
			case CanaryCreeper:
				return properties.getDouble(world,
						Key.ENTITY_DAMAGE_MULT_CREEPER);
			case CanaryEnderman:
				return properties.getDouble(world,
						Key.ENTITY_DAMAGE_MULT_CREEPER);
			case CanaryGhast:
				return properties
						.getDouble(world, Key.ENTITY_DAMAGE_MULT_GHAST);
			case CanaryGiantZombie:
				return properties
						.getDouble(world, Key.ENTITY_DAMAGE_MULT_GIANT);
			case CanaryIronGolem:
				return properties.getDouble(world,
						Key.ENTITY_DAMAGE_MULT_IGOLEM);
			case CanaryLavaSlime:
				return properties
						.getDouble(world, Key.ENTITY_DAMAGE_MULT_MCUBE);
			case CanaryPigZombie:
				return properties.getDouble(world,
						Key.ENTITY_DAMAGE_MULT_PIGZOMBIE);
			case CanarySilverfish:
				return properties.getDouble(world,
						Key.ENTITY_DAMAGE_MULT_SILVERFISH);
			case CanarySkeleton:
				return properties.getDouble(world,
						Key.ENTITY_DAMAGE_MULT_SKELETON);
			case CanarySlime:
				return properties
						.getDouble(world, Key.ENTITY_DAMAGE_MULT_SLIME);
			case CanarySpider:
				return properties.getDouble(world,
						Key.ENTITY_DAMAGE_MULT_SPIDER);
			case CanaryWither:
				return properties.getDouble(world,
						Key.ENTITY_DAMAGE_MULT_WITHER);
			case CanaryZombie:
				return properties.getDouble(world,
						Key.ENTITY_DAMAGE_MULT_ZOMBIE);
			default:
				break;
			}
		}
		return 1.0D;
	}

}
