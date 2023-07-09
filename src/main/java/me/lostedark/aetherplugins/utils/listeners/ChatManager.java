package me.lostedark.aetherplugins.utils.listeners;

public class ChatManager {
   private static boolean chatBlocked = false;

   public static boolean isChatBlocked() {
      return chatBlocked;
   }

   public static void setChatBlocked(boolean blocked) {
      chatBlocked = blocked;
   }
}
