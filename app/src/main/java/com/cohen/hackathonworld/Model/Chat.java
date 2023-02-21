package com.cohen.hackathonworld.Model;

import com.cohen.hackathonworld.Exception.ChatTypeCannotChangedException;

import java.util.ArrayList;

/**
 * Chat - Create chat from different types
 *  chat could be from type
 *   TEAM - only the same team member can talk in that chat room - int teamId
 *   FORUM - chat room for several teams - ArrayList<Integer> chatTeamsList, IDsList chatTeamsList
 *   GENERAL - chat room for all users - ArrayList<Integer> chatTeamsList, IDsList chatTeamsList
 */
public class Chat {
    public static int NUMBER_OF_CHATS = 4000000;
    public int chatId = 0;
    public String chatName = "";
    public String chatImageUrl = "";
    public CHAT_TYPE chatType = null;
    public IDsList chatTeamsList = null;
    public int creatorId = 0;

    public Chat(){

    }

    public Chat withChatId(){
        this.chatId = NUMBER_OF_CHATS++;
        return this;
    }
    public Chat withChatName(String name){
        this.chatName = name + " Chat";
        return this;
    }
    public Chat withChatImageUrl(String chatImageUrl){
        this.chatImageUrl = chatImageUrl;
        return this;
    }
    public Chat withChatType(CHAT_TYPE chatType){
        this.chatType = chatType;
        return this;
    }
    public Chat withChatTeamList(ArrayList<Integer> chatTeamsList){
        this.chatTeamsList = new IDsList();
        this.chatTeamsList
                .withIds(chatTeamsList);
        return this;
    }
    public Chat withChatTeamList(int chatTeamId){
        this.chatTeamsList = new IDsList();
        this.chatTeamsList
                .withIds(chatTeamId);
        return this;
    }
    public Chat withChatCreatorId(int creatorId){
        this.creatorId = creatorId;
        return this;
    }

    public int getChatId() {
        return chatId;
    }

    public String getChatName() {
        return chatName;
    }
    public String getChatEmailUrl() {
        return chatImageUrl;
    }

    public CHAT_TYPE getChatType() {
        return chatType;
    }

    public IDsList getChatTeamsList() {
        return chatTeamsList;
    }

    public int getCreatorId() {
        return creatorId;
    }

    //ValidPermissionCheck
    public void setChatType(CHAT_TYPE chatType) throws ChatTypeCannotChangedException {
        if(this.chatType != null)
            throw new ChatTypeCannotChangedException();
        this.chatType = chatType;
    }
    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
    public void setChatImageUrl(String chatImageUrl) {
        this.chatImageUrl = chatImageUrl;
    }

    public void addTeamsToChatTeamsList(ArrayList<Integer> chatTeamsIdsList) throws ChatTypeCannotChangedException {
        if(this.chatType == CHAT_TYPE.TEAM)
            throw new ChatTypeCannotChangedException();
        else if(this.chatType == CHAT_TYPE.GENERAL)
            return;
        this.chatTeamsList.addIds(chatTeamsIdsList);
    }
    public void addTeamToChatTeamsList(int chatTeamId) throws ChatTypeCannotChangedException {
        if(this.chatType == CHAT_TYPE.TEAM)
            throw new ChatTypeCannotChangedException();
        else if(this.chatType == CHAT_TYPE.GENERAL)
            return;
        this.chatTeamsList.addId(chatTeamId);
    }

    public void removeTeamsFromChatTeamsList(ArrayList<Integer> teamsToRemoveFromChatList){
        this.chatTeamsList.removeIds(teamsToRemoveFromChatList);
    }
    public void removeTeamFromChatTeamsList(int teamToRemoveFromChatList){
        this.chatTeamsList.removeId(teamToRemoveFromChatList);
    }

}
