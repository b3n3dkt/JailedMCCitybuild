package me.b3n3dkt.utils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class FileBuilder
{
    private File file;
    private YamlConfiguration config;

    public FileBuilder(String Speicherort, String Dateiname) {
        this.file = new File(Speicherort, Dateiname);
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileBuilder setValue(String ordnungwert, Object wert) {
        this.config.set(ordnungwert, wert);
        return this;
    }

    public FileBuilder removeValue(String ordnungswert) {
        this.config.set(ordnungswert, null);
        return this;
    }

    public boolean exist() { return this.file.exists(); }

    public Object getObject(String ValuePath) { return this.config.get(ValuePath); }

    public void delete() { this.file.delete(); }

    public int getInt(String ValuePath) { return this.config.getInt(ValuePath); }

    public String getString(String ValuePath) { return this.config.getString(ValuePath); }

    public ItemStack getItemStack(String ValuePath) { return this.config.getItemStack(ValuePath); }

    public double getDouble(String ValuePath) { return this.config.getDouble(ValuePath); }

    public boolean getBoolean(String ValuePath) { return this.config.getBoolean(ValuePath); }

    public long getLong(String ValuePath) { return this.config.getLong(ValuePath); }

    public List<String> getStringList(String ValuePath) { return this.config.getStringList(ValuePath); }

    public Set<String> getKeys(boolean deep) { return this.config.getKeys(deep); }

    public ConfigurationSection getConfigurationSection(String Section) { return this.config.getConfigurationSection(Section); }

    public FileBuilder save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
