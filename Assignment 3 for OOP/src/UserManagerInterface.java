public interface UserManagerInterface {
    void deleteUserData(String username);
    void changePassword(String username, String newPassword);
}
