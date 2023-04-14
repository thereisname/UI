package com.example.ui;

/**
 * User Account Information Model Class. The class used for membership registration and login must include the following.
 * <pre>
 *     idToken - About Unique Tokens
 *     userEmail
 *     password
 *     userID
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
    private String password;
    private String userID;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getPassword() {
        return password;
    }

    public String getUserID() {
        return userID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getUserBr() {
        return userBr;
    }
}
