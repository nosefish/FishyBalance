package net.gmx.nosefish.fishybalance.properties;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import net.gmx.nosefish.fishylib.properties.PropertyKey;
import net.gmx.nosefish.fishylib.properties.ValueType;

/**
 * Enum of known property keys, along with the string used in the properties
 * file, the type of their value, and the default value.
 * 
 * @author Stefan Steinheimer
 * 
 */
public enum Key implements PropertyKey{
	DEBUG("debug",
			ValueType.BOOLEAN, false),
	// damage multipliers
	ENTITY_DAMAGE_MULT_BLAZE("damage-multiplier-blaze",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_CSPIDER("damage-multiplier-cavespider",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_CREEPER("damage-multiplier-creeper",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_ENDERMAN("damage-multiplier-enderman",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_GHAST("damage-multiplier-ghast",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_GIANT("damage-multiplier-giantzombie",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_IGOLEM("damage-multiplier-irongolem",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_MCUBE("damage-multiplier-magmacube",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_PIGZOMBIE("damage-multiplier-pigzombie",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_SILVERFISH("damage-multiplier-silverfish",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_SKELETON("damage-multiplier-skeleton",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_SLIME("damage-multiplier-slime",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_SPIDER("damage-multiplier-spider",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_WITHER("damage-multiplier-wither",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_ZOMBIE("damage-multiplier-zombie",
			ValueType.DOUBLE, 1.0D),
	ENTITY_DAMAGE_MULT_FIRE("damage-multiplier-fire",
			ValueType.DOUBLE, 1.0D),
	ENTITY_EXPLOSION_ENHANCER_GHAST("explosion-power-ghast",
			ValueType.DOUBLE, 0.0D);
	
	private static Map<String, PropertyKey> map;
	private final String propertyName;
	private final Object defaultValue;
	private final ValueType propertyType;

    @SuppressWarnings("LeakingThisInConstructor")
	private Key(String propertyName, ValueType type, Object defaultValue) {
		this.propertyName = propertyName;
		this.propertyType = type;
		this.defaultValue = defaultValue;
		addToMap(propertyName, this);
	}

	/**
	 * Gets the name of the property key that is used in the properties file
	 * 
	 * @return property key as it appears in the file
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Gets the <code>ValueType</code> of this property. Used to determine how
	 * to load this property
	 * 
	 * @return the <code>ValueType</code> associated with this property
	 */
	public ValueType getType() {
		return propertyType;
	}

	/**
	 * Gets the default value of this property. This value will be applied if
	 * the property is missing from the properties file and will be written back
	 * to the file.
	 * 
	 * @return the default value
	 */
	public Object getDefault() {
		return defaultValue;
	}

	/**
	 * Gets all known keys. Used to find keys that are missing from the
	 * propertied file
	 * 
	 * @return the set of known keys
	 */
	public static Collection<PropertyKey> getAllKeys() {
		return map.values();
	}

	/**
	 * Gets the <code>Key</code> associated with a property string.
	 * 
	 * @param propertyName
	 *            the key string of the property as it appears in the properties
	 *            file
	 * @return the <code>Key</code> for the given property name
	 */
	public static PropertyKey getKey(String propertyName) {
		return map.get(propertyName);
	}

	/**
	 * Adds a property name/<code>Key</code> pair to the internal Map. Used by
	 * the constructor to make <code>getKey</code> possible.
	 * 
	 * @param propertyName
	 * @param k
	 */
	private static void addToMap(String propertyName, PropertyKey k) {
		if (map == null) {
			map = new TreeMap<String, PropertyKey>();
		}
		map.put(propertyName, k);
	}
}