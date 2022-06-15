package xyz.democracybot.data;

public class RoleData {

    private String roledata;
    private String roleid;

    public RoleData(String data, String id){
        this.roledata = data;
        this.roleid = id;
    }

    public String getRoledata() {
        return roledata;
    }

    public String getRoleid() {
        return roleid;
    }
}
