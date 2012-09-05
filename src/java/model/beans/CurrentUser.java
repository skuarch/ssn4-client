package model.beans;

/**
 *
 * @author skuarch
 */
public class CurrentUser {

    private String name;
    private String password;
    private int level;
    private String description;
    
    //==========================================================================
    private CurrentUser() {
    }
    
    //==========================================================================
    public static CurrentUser getInstance() {
        return CurrentUserHolder.INSTANCE;
    }
    
    //==========================================================================
    private static class CurrentUserHolder {
        private static final CurrentUser INSTANCE = new CurrentUser();
    }
    
    //==========================================================================
    public String getDescription() {
        return description;
    }

    //==========================================================================
    public void setDescription(String description) {
        this.description = description;
    }

    //==========================================================================
    public int getLevel() {
        return level;
    }

    //==========================================================================
    public void setLevel(int level) {
        this.level = level;
    }

    //==========================================================================
    public String getName() {
        return name;
    }

    //==========================================================================
    public void setName(String name) {
        this.name = name;
    }

    //==========================================================================
    public String getPassword() {
        return password;
    }

    //==========================================================================
    public void setPassword(String password) {
        this.password = password;
    }
    
}
