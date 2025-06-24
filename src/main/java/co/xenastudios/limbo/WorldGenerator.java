package co.xenastudios.limbo;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Custom world generator for the Limbo plugin.
 * Generates flat chunks with a configurable block at Y=0.
 */
public class WorldGenerator extends ChunkGenerator {

	// Reference to the main plugin instance for config access
	private final LimboPlugin plugin;

	/**
	 * Constructs a new WorldGenerator.
	 *
	 * @param plugin The main plugin instance.
	 */
	public WorldGenerator(LimboPlugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * Generates chunk data for a given chunk.
	 * Fills the bottom layer (Y=0) with the configured block type.
	 *
	 * @param world  The world being generated.
	 * @param random The random instance for generation.
	 * @param chunkX The X coordinate of the chunk.
	 * @param chunkZ The Z coordinate of the chunk.
	 * @param biome  The biome grid for the chunk.
	 * @return The generated chunk data.
	 */
	@Override
	public @NotNull ChunkData generateChunkData(
			@NotNull World world,
			@NotNull Random random,
			int chunkX,
			int chunkZ,
			@NotNull BiomeGrid biome
	) {
		ChunkData chunkData = createChunkData(world);

		// Get the block type from config, defaulting to WHITE_STAINED_GLASS
		String blockName = plugin.getConfig().getString(
				"world-generator.block",
				"WHITE_STAINED_GLASS"
		);
		Material blockMaterial = Material.getMaterial(blockName);

		// Fallback to WHITE_STAINED_GLASS if the config value is invalid
		if (blockMaterial == null) {
			plugin.getLogger().warning(
					"Invalid block type in config: " + blockName +
							". Defaulting to WHITE_STAINED_GLASS."
			);
			blockMaterial = Material.WHITE_STAINED_GLASS;
		}

		// Fill the bottom layer (Y=0) of the chunk with the chosen block
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				chunkData.setBlock(x, 0, z, blockMaterial);
			}
		}

		return chunkData;
	}
}