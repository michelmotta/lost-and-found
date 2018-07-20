package com.example.michel.lostandfoundufms.interfaces;

import com.example.michel.lostandfoundufms.model.CommentItem;

import org.json.JSONObject;

import java.util.List;

public interface ObjectCommentsInterface {
    interface View{
        void loadObjectComments(List<CommentItem> commentItemList);
        void commentSaveSuccess(String message);
        void commentSaveError(String message);
    }
    interface Presenter{
        void requestObjectComments(String objectId);
        void requestObjectCommentsApiRestponse(JSONObject response);
        void requestSaveComment(String objectId, String objectUserId, String userId ,String comment);
        void requestSaveCommentApiResponse(JSONObject response);
    }
    interface Model{
        void requestObjectCommentsApi(String objectId);
        void requestSaveCommentApi(String objectId, String objectUserId, String userId, String comment);
    }
}
