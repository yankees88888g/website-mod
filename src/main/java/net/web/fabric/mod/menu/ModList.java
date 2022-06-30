package net.web.fabric.mod.menu;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ModList implements Mod{
    public static void main() {
        FabricLoader.getInstance().getAllMods();

    }

    @Override
    public @NotNull String getType() {
        return getType();
    }


    @Override
    public @NotNull String getId() {
        return getId();
    }

    @Override
    public @NotNull String getName() {
        return getName();
    }

    /*@Override
    public @NotNull String getIcon(FabricIconHandler iconHandler, int i) {
        return getIcon();
    }*/

    @Override
    public @NotNull String getSummary() {
        return getSummary();
    }

    @Override
    public @NotNull String getDescription() {
        return getDescription();
    }

    @Override
    public @NotNull String getVersion() {
        return getVersion();
    }

    @Override
    public @NotNull String getPrefixedVersion() {
        return getPrefixedVersion();
    }

    @Override
    @NotNull

    public List<String> getAuthors() {
        return getAuthors();
    }

    @Override
    @NotNull

    public List<String> getContributors() {
        return getContributors();
    }


    @Override
    public @NotNull List<String> getCredits() {
        return getCredits();
    }


    /*public @NotNull Set<net.web.fabric.mod.menu.Mod.Badge> getBadges() {
        return getBadges();
    }*/

    @Override
    public @Nullable String getWebsite() {
        return getWebsite();
    }


    public @Nullable String getIssueTracker() {
        return getIssueTracker();
    }

    @Override
    public @Nullable String getSource() {
        return getSource();
    }
    @Override
    public @Nullable String getParent() {
        return getParent();
    }
    @Override
    public @NotNull Set<String> getLicense() {
        return getLicense();
    }
    @Override
    public @NotNull Map<String, String> getLinks() {
        return getLinks();
    }
}
