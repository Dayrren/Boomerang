package Content;

import Content.Australia.Australia;

// ConfigurableClass using Initialization-on-demand holder idiom
public class ContentManager {
    private static class Holder {
        private static ContentPack INSTANCE = new Australia();

    }

    /**
     * @return the Holders content pack INSTANCE
     */
    public static ContentPack getContentPack() {
        return Holder.INSTANCE;
    }

    /**
     * @param contentPack The content pack to update the Holders INSTANCE to.
     */
    public static void setContentPack(ContentPack contentPack) {
        Holder.INSTANCE = contentPack;
    }
}