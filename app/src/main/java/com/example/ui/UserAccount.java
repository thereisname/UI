package com.example.ui;

/**
 * User Account Information Model Class. The class used for membership registration and login must include the following.
 * <pre>
 *     idToken - About Unique Tokens
 *     userEmail
 *     userNickName
 *     userName
 *     phoneNum
 *     userBr
 * </pre>
 * The above is a key, and the contents of the setter are stored in the Firebase Realtime Database as a key value.
 *
 * @author MIN
 * @since 1.0
 */
public class UserAccount {
    private String idToken;  // Firebase UID(고유 토큰 정보)
    private String userEmail; // 이메일 아이디
    private String userNickName;
    private String userName;
    private String phoneNum;
    private String userBr;

    public UserAccount() {
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setUserBr(String userBr) {
        this.userBr = userBr;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getUserBr() {
        return userBr;
    }
}
