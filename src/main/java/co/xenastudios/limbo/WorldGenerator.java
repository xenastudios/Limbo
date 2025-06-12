package co.xenastudios.limbo;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class WorldGenerator extends ChunkGenerator {
    private LimboPlugin plugin;

    public WorldGenerator(LimboPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int chunkX, int chunkZ, @NotNull BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunkData.setBlock(x, 0, z, Objects.requireNonNull(Material.getMaterial(this.plugin.getConfig().getString("world-generator.block", "WHITE_STAINED_GLASS"))));
            }
        }

        return chunkData;
    }
}
