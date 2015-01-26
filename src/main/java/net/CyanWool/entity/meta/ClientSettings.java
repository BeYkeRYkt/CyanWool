package net.CyanWool.entity.meta;

import java.util.ArrayList;
import java.util.List;
import org.spacehq.mc.protocol.data.game.values.setting.ChatVisibility;
import org.spacehq.mc.protocol.data.game.values.setting.SkinPart;
import org.spacehq.mc.protocol.packet.ingame.client.ClientSettingsPacket;

public final class ClientSettings {

    private final String locale;
    private final int viewDistance;
    private final boolean chatColors;
    private ChatVisibility chatVisibility;
    private List<SkinPart> skinParts;

    public ClientSettings(ClientSettingsPacket packet) {
        this(packet.getLocale(), packet.getRenderDistance(), packet.getChatVisibility(), packet.getUseChatColors(), packet.getVisibleParts());
    }

    public ClientSettings(String locale, int viewDistance, ChatVisibility chatFlags, boolean chatColors, List<SkinPart> skinParts) {
        this.locale = locale;
        this.viewDistance = viewDistance;
        this.chatVisibility = chatFlags;
        this.chatColors = chatColors;
        this.skinParts = skinParts;
    }

    public String getLocale() {
        return locale;
    }

    public int getViewDistance() {
        return viewDistance;
    }

    public ChatVisibility getChatFlags() {
        return chatVisibility;
    }

    public boolean showChat() {
        return chatVisibility == ChatVisibility.FULL;
    }

    public boolean showCommands() {
        return chatVisibility != ChatVisibility.SYSTEM;
    }

    public boolean showChatColors() {
        return chatColors;
    }

    public List<SkinPart> getSkinParts() {
        return skinParts;
    }

    public static ClientSettings getDEFAULT() {
        List<SkinPart> list = new ArrayList<SkinPart>();
        list.add(SkinPart.CAPE);
        list.add(SkinPart.HAT);
        list.add(SkinPart.JACKET);
        list.add(SkinPart.LEFT_PANTS_LEG);
        list.add(SkinPart.LEFT_SLEEVE);
        list.add(SkinPart.RIGHT_PANTS_LEG);
        list.add(SkinPart.RIGHT_SLEEVE);
        ClientSettings settings = new ClientSettings("en_US", 8, ChatVisibility.FULL, true, list);
        return settings;
    }

    @Override
    public String toString() {
        return "ClientSettings{" + "locale='" + locale + '\'' + ", viewDistance=" + viewDistance + ", chatFlags=" + chatVisibility.name() + ", chatColors=" + chatColors + ", skinFlags=" + skinParts + '}';
    }
}